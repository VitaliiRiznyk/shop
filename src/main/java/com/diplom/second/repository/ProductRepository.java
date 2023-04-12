package com.diplom.second.repository;

import com.diplom.second.model.Product;
import com.diplom.second.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductType(ProductType productType);

}