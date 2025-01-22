package com.reactive.web.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private  String id;
    private String name;
    private String qty;
    private double price;
}
