package com.backlashprogramming.ecommerce.EcommerceByBacklash.dao;

import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenDao extends JpaRepository<VerificationToken,Long> {

}
