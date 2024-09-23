package com.ng.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ng.events.common.JwtUtil;
import com.ng.events.dto.LoginRequest;
import com.ng.events.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private JwtUtil jwtUtil; 

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (!adminEmail.equals(loginRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), adminPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        
        String token = jwtUtil.generateToken(adminEmail, "admin");

        return ResponseEntity.ok(new LoginResponse(token, jwtUtil.getExpirationTime()));
    }

}
