package com.ngoc.project1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ngoc.project1.entity.Course;
import com.ngoc.project1.entity.OurUsers;
import com.ngoc.project1.entity.Payment;
import com.ngoc.project1.enums.PaymentStatus;
import lombok.Data;

import java.util.Date;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDTO {

    private Long PaymentId;
    private Long UserId;
    private Date date;
    private Double PaymentAmount;
    private PaymentStatus PaymentStatus;
    private String PaymentType;
    private Long CourseId;
    private Integer StatusCode;
    private String message;
    private String error;
    private Payment Payment;


}
