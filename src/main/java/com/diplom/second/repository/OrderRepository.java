package com.diplom.second.repository;

import com.diplom.second.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ProductOrder,Long> {
}
