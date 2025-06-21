package com.ngoc.project1.controller;


import com.ngoc.project1.dto.ReqRes;
import com.ngoc.project1.entity.OurUsers;
import com.ngoc.project1.service.UserManagermentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserManagementController {

    @Autowired
    private UserManagermentService userManagermentService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes req) {
        return ResponseEntity.ok(userManagermentService.register(req));
    }

    @PostMapping("auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req) {
        return ResponseEntity.ok(userManagermentService.login(req));
    }
    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req) {
        return ResponseEntity.ok(userManagermentService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers() {
        return ResponseEntity.ok(userManagermentService.getAllUser());
    }
    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUser(@PathVariable int userId) {
        return ResponseEntity.ok(userManagermentService.getUserById(userId));
    }
    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable int userId, @RequestBody OurUsers reqres) {
        return  ResponseEntity.ok(userManagermentService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = userManagermentService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable int userId) {
        return ResponseEntity.ok(userManagermentService.deleteUser(userId));
    }
}
