package com.reactive.web.controller;


import com.reactive.web.dto.ProductDto;
import com.reactive.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public Flux<ProductDto> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable String id){
        return service.getProduct(id);
    }


    @GetMapping("/product-range")
    public Flux<ProductDto> getProductBetweenRange(@RequestParam("min") double min,@RequestParam("max") double max){
        return service.getProductInRange(min,max);
    }

    @PostMapping
    public Mono<ProductDto> saveProduct(
           @RequestBody Mono<ProductDto> productDtoMono){
        System.out.println("controller method called ...");
        return service.saveProduct(productDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDto> saveProduct(
            @RequestBody Mono<ProductDto> productDtoMono,@PathVariable String id){
        System.out.println("controller method called ...");
        return service.updateProduct(productDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return service.deleteProduct(id);
    }


}
