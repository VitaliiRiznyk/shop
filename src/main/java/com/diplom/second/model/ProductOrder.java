package com.diplom.second.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomUser customUser;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductOrder(String address, CustomUser customUser, Product product) {
        this.address = address;
        this.customUser = customUser;
        this.product = product;
    }
}