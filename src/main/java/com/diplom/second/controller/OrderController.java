package com.diplom.second.controller;

import com.diplom.second.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/newOrder")
    public String userOrder(@RequestParam String address, Authentication authentication,
                            @RequestParam Long productId) {
        orderService.createOrder(address, authentication, productId);
        return null;
    }
}