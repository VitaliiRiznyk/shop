package com.diplom.second.controller;

import com.diplom.second.model.*;
import com.diplom.second.service.CustomUserService;
import com.diplom.second.service.OrderService;
import com.diplom.second.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AdminController {
    private final CustomUserService customUserService;
    private final OrderService orderService;
    private final ProductService productService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<CustomUser> getAllUsers(@PathVariable(value = "id") Long userId) {
        return new ResponseEntity<>(customUserService.findCustomUserById(userId), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/orders")
    public List<ProductOrder> getAllProductOrders() {
        return orderService.getAllOrders();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/new-product")
    public void saveNewProduct(@RequestBody Product product) {
        Product product1 = new Product(product.getName(), Double.valueOf(product.getPrice()), product.getColor(),
                product.getBrand(), product.getProductType(), product.getMemory(), product.getPictureURL());
        productService.saveProductToDB(product1);
    }
}