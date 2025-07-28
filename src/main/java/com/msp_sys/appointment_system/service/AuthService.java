package com.msp_sys.appointment_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.msp_sys.appointment_system.config.JwtUtil;
import com.msp_sys.appointment_system.dto.AuthResponse;
import com.msp_sys.appointment_system.dto.LoginRequest;
import com.msp_sys.appointment_system.dto.RegisterRequest;
import com.msp_sys.appointment_system.model.Role;
import com.msp_sys.appointment_system.model.User;
import com.msp_sys.appointment_system.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        if (request.getRole() == Role.PROVIDER) {
            user.setBusinessName(request.getBusinessName());
            user.setCategory(request.getCategory());
            user.setAgencyImageUrl(request.getAgencyImageUrl());
            user.setApproved(false);
        }

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token);
    }
}
