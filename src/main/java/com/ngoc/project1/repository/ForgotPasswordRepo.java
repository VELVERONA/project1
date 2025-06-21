package com.ngoc.project1.repository;

import com.ngoc.project1.entity.ForgotPassword;
import com.ngoc.project1.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgotPasswordRepo extends JpaRepository<ForgotPassword, Integer> {

    @Query("SELECT fp FROM ForgotPassword fp where fp.otp = ?1 and fp.user = ?2")
    Optional<ForgotPassword> findByOtpAndUser(int otp, OurUsers user);
}
