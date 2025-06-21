package com.ngoc.project1.repository;


import com.ngoc.project1.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<OurUsers, Integer> {

    Optional<OurUsers> findByEmail(String email);

    Optional<OurUsers> findById(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE OurUsers u set u.password = ?2 where u.email = ?1")
    void updatePassword(String email, String password);
}
