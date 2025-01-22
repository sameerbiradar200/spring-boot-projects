package com.reactive.web.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {


    @Id
    private String id;
    private String name;
    private String qty;
    private double price;



}
