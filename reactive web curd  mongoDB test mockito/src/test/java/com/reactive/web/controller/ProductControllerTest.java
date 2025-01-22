package com.reactive.web.controller;

import com.reactive.web.dto.ProductDto;
import com.reactive.web.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class ProductControllerTest {

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController productController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetProducts() {
		// Arrange
		ProductDto product1 = new ProductDto("1", "Product1", "0", 100.0);
		ProductDto product2 = new ProductDto("2", "Product2", "0", 200.0);
		when(productService.getProducts()).thenReturn(Flux.just(product1, product2));

		// Act
		Flux<ProductDto> result = productController.getProducts();

		// Assert
		StepVerifier.create(result)
				.expectNext(product1)
				.expectNext(product2)
				.verifyComplete();
		verify(productService, times(1)).getProducts();
	}

	@Test
	void testGetProductById() {
		// Arrange
		String productId = "1";
		ProductDto product = new ProductDto(productId, "Product1", null, 100.0);
		when(productService.getProduct(productId)).thenReturn(Mono.just(product));

		// Act
		Mono<ProductDto> result = productController.getProduct(productId);

		// Assert
		StepVerifier.create(result)
				.expectNext(product)
				.verifyComplete();
		verify(productService, times(1)).getProduct(productId);
	}

	@Test
	void testGetProductBetweenRange() {
		// Arrange
		double min = 50.0;
		double max = 200.0;
		ProductDto product = new ProductDto("1", "Product", "0", 100.0);
		when(productService.getProductInRange(min, max)).thenReturn(Flux.just(product));

		// Act
		Flux<ProductDto> result = productController.getProductBetweenRange(min, max);

		// Assert
		StepVerifier.create(result)
				.expectNext(product)
				.verifyComplete();
		verify(productService, times(1)).getProductInRange(min, max);
	}

	@Test
	void testSaveProduct() {
		// Arrange
        ProductDto product = new ProductDto("1", "Product", null, 100.0);
		when(productService.saveProduct(any(Mono.class))).thenReturn(Mono.just(product));

		// Act
		Mono<ProductDto> result = productController.saveProduct(Mono.just(product));

		// Assert
		StepVerifier.create(result)
				.expectNext(product)
				.verifyComplete();
		verify(productService, times(1)).saveProduct(any(Mono.class));
	}

	@Test
	void testUpdateProduct() {
		// Arrange
		String productId = "1";
		ProductDto product = new ProductDto(productId, "Updated Product", "0", 150.0);
		when(productService.updateProduct(any(Mono.class), eq(productId))).thenReturn(Mono.just(product));

		// Act
		Mono<ProductDto> result = productController.saveProduct(Mono.just(product), productId);

		// Assert
		StepVerifier.create(result)
				.expectNext(product)
				.verifyComplete();
		verify(productService, times(1)).updateProduct(any(Mono.class), eq(productId));
	}

	@Test
	void testDeleteProduct() {
		// Arrange
		String productId = "1";
		when(productService.deleteProduct(productId)).thenReturn(Mono.empty());

		// Act
		Mono<Void> result = productController.deleteProduct(productId);

		// Assert
		StepVerifier.create(result)
				.verifyComplete();
		verify(productService, times(1)).deleteProduct(productId);
	}
}