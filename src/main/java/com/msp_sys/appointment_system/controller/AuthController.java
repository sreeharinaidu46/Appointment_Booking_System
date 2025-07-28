package com.msp_sys.appointment_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.msp_sys.appointment_system.dto.AuthResponse;
import com.msp_sys.appointment_system.dto.LoginRequest;
import com.msp_sys.appointment_system.dto.RegisterRequest;
import com.msp_sys.appointment_system.service.AuthService;

// AuthController.java
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}