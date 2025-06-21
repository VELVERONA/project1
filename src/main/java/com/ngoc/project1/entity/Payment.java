package com.ngoc.project1.entity;


import com.ngoc.project1.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long PaymentId;

    private Date PaymentDate;

    private Double PaymentAmount;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private OurUsers user;

    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;

    @Enumerated
    private PaymentStatus PaymentStatus;
}
