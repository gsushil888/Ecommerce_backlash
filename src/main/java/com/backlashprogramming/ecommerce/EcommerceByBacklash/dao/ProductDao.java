package com.backlashprogramming.ecommerce.EcommerceByBacklash.dao;

import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Long> {
}
