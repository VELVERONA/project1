package com.ngoc.project1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ForgotPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fpid ;

    @Column(nullable = false)
    private int otp;

    @Column(nullable = false)
    private Date experationTime;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private OurUsers user;
}
