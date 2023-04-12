package com.diplom.second.controller;

import com.diplom.second.model.Product;
import com.diplom.second.model.ProductType;
import com.diplom.second.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/phones")
    public List<Product> getAllPhones() {
        return productService.findProductByType(ProductType.Phone);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/accessories")
    public List<Product> getAccessories() {
        return productService.findProductByType(ProductType.Accessories);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/headphones")
    public List<Product> getHeadPhones() {
        return productService.findProductByType(ProductType.Headphone);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/watches")
    public List<Product> getWatches() {
        return productService.findProductByType(ProductType.Watch);
    }
}