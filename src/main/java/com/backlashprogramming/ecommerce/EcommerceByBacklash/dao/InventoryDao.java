package com.backlashprogramming.ecommerce.EcommerceByBacklash.dao;

import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryDao  extends JpaRepository<Inventory,Long> {

}
