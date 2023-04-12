package com.diplom.second.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;
    private String color;
    @Enumerated(EnumType.STRING)
    private ProductBrand brand;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Nullable
    private String memory;
    @Lob
    private byte[] picture;

    public Product(String name, Double price, String color, ProductBrand brand,
                   ProductType productType, String memory, byte[] picture) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.brand = brand;
        this.productType = productType;
        this.memory = memory;
        this.picture = picture;
    }
}