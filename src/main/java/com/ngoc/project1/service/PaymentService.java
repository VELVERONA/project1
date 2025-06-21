package com.ngoc.project1.service;

import com.ngoc.project1.dto.PaymentDTO;
import com.ngoc.project1.entity.Course;
import com.ngoc.project1.entity.OurUsers;
import com.ngoc.project1.entity.Payment;
import com.ngoc.project1.repository.CourseRepo;
import com.ngoc.project1.repository.PaymentRepo;
import com.ngoc.project1.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.ngoc.project1.enums.PaymentStatus.PAID;
import static com.ngoc.project1.enums.PaymentStatus.PENDING;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private CourseRepo courseRepo;


    public PaymentDTO createPayment(Integer userId, Long courseId) {
        PaymentDTO paymentDTO = new PaymentDTO();
        try{
            Optional<Course> courseOptional = courseRepo.findById(courseId);
            Optional<OurUsers> userOptional = usersRepo.findById(userId);
            if (courseOptional.isEmpty() || userOptional.isEmpty()) {
                throw new RuntimeException("Course or Users not found");
            }
            OurUsers user = userOptional.get();
            Long OurUserId = user.getId();
            Course course = courseOptional.get();
            Double paymentAmount = course.getPrice();

            Payment payment = new Payment();
            payment.setPaymentAmount(paymentAmount);
            payment.setPaymentStatus(PAID);
            payment.setPaymentDate(new Date(System.currentTimeMillis()));



        } catch (Exception e){
            paymentDTO.setStatusCode(500);
            paymentDTO.setError(e.getMessage());
        }

        return paymentDTO;
    }

}
