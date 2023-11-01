package com.backlashprogramming.ecommerce.EcommerceByBacklash.dao;

import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.LocalUser;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface VerificationTokenDao extends JpaRepository<VerificationToken,Long> {

    @Query("select v from VerificationToken v where v.token = ?1")
    Optional<VerificationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("delete from VerificationToken v where v.localUser = ?1")
    void deleteByLocalUser(LocalUser localUser);

    List<VerificationToken> findByUser_IdOrderByIdDesc(Long id);

}
