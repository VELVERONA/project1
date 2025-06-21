package com.ngoc.project1.controller;

import com.ngoc.project1.dto.PaymentDTO;
import com.ngoc.project1.entity.Course;
import com.ngoc.project1.entity.OurUsers;
import com.ngoc.project1.entity.Payment;
import com.ngoc.project1.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

}