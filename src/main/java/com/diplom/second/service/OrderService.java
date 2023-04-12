package com.diplom.second.service;

import com.diplom.second.model.CustomUser;
import com.diplom.second.model.Product;
import com.diplom.second.model.ProductOrder;
import com.diplom.second.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomUserService customUserService;
    private final ProductService productService;

    @Transactional
    public void createOrder(String address, Authentication authentication, Long productId) {
        CustomUser customUser = customUserService.getByLogin(authentication.getName());
        Product product = productService.getProduct(productId);
        orderRepository.save(new ProductOrder(address, customUser, product));
    }
    @Transactional
    public List<ProductOrder> getAllOrders() {
        return orderRepository.findAll();
    }

}