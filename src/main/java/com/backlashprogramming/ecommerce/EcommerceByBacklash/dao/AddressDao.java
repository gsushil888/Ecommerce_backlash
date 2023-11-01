package com.backlashprogramming.ecommerce.EcommerceByBacklash.dao;

import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressDao extends JpaRepository<Address,Long> {
    List<Address> findByUser_Id(Long userId);
}
