package com.diplom.second.service;

import com.diplom.second.model.Product;
import com.diplom.second.model.ProductType;
import com.diplom.second.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void saveProductToDB(Product product) {
        if (product != null) {
            productRepository.save(product);
        }
    }

    @Transactional
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public List<Product> findProductByType(ProductType productType) {
        return productRepository.findByProductType(productType);
    }
}