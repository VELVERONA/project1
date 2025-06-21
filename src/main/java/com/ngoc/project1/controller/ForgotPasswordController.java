package com.ngoc.project1.controller;

import com.ngoc.project1.dto.MailBody;
import com.ngoc.project1.entity.ForgotPassword;
import com.ngoc.project1.entity.OurUsers;
import com.ngoc.project1.repository.ForgotPasswordRepo;
import com.ngoc.project1.repository.UsersRepo;
import com.ngoc.project1.dto.ChangePassword;
import com.ngoc.project1.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;


@RestController
@RequestMapping("/forgotpassword")
public class ForgotPasswordController {

    @Autowired
    private final UsersRepo usersRepo;

    @Autowired
    private final EmailService emailService;

    @Autowired
    private final ForgotPasswordRepo forgotPasswordRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordController(UsersRepo usersRepo, EmailService emailService, ForgotPasswordRepo forgotPasswordRepo, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.emailService = emailService;
        this.forgotPasswordRepo = forgotPasswordRepo;
        this.passwordEncoder = passwordEncoder;
    }

    //send email
    @PostMapping("/verifyEmail/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
       OurUsers user = usersRepo.findByEmail(email)
               .orElseThrow(() -> new UsernameNotFoundException("pls provide a new username"));

       int otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("this is the otp for the forgot password request" + otp)
                .subject("OTP forgot password request")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .experationTime(new Date(System.currentTimeMillis() + 70 * 1000))
                .user(user)
                .build();

        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepo.save(fp);

        return ResponseEntity.ok("Email sent for verification");
    }

    @PostMapping("/verify/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email) {
        OurUsers user = usersRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("pls provide a new username"));

        ForgotPassword fp = forgotPasswordRepo.findByOtpAndUser(otp, user).orElseThrow(() -> new RuntimeException("OTP is not valid"));

        if (fp.getExperationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepo.deleteById(fp.getFpid());
            return new ResponseEntity<>("OTP is Expired", HttpStatus.EXPECTATION_FAILED);
        }

        return ResponseEntity.ok("OTP is valid");
    }

    @PostMapping("/changePassword/{email}")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword
                                                        , @PathVariable String email){
            if(!Objects.equals(changePassword.password(), changePassword.repeatPassword())){
                return new ResponseEntity<>("Please enter your password again" , HttpStatus.EXPECTATION_FAILED);
            }

            String encodedPassword = passwordEncoder.encode(changePassword.password());
            usersRepo.updatePassword(email, encodedPassword);

            return ResponseEntity.ok("Password changed successfully");
    }


    private int otpGenerator() {
            Random random = new Random();
            return random.nextInt(100_000, 999_999);
    }
}
