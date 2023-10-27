package com.backlashprogramming.ecommerce.EcommerceByBacklash.dao;

import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LocalUserDao  extends JpaRepository<LocalUser,Long> {

    @Query("select l from LocalUser l where upper(l.userName) = upper(?1)")
    Optional<LocalUser> findByUserNameIgnoreCase(String userName);

    @Query("select l from LocalUser l where upper(l.email) = upper(?1)")
    Optional<LocalUser> findByEmailIgnoreCase(String email);





}
