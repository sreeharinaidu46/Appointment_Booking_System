package com.msp_sys.appointment_system.controller;

import com.msp_sys.appointment_system.dto.UserResponse;
import com.msp_sys.appointment_system.model.Role;
import com.msp_sys.appointment_system.model.User;
import com.msp_sys.appointment_system.repository.UserRepository;
import com.msp_sys.appointment_system.util.ResponseMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/unapproved-providers")
    public List<UserResponse> getUnapprovedProviders() {
        return userRepository.findAll().stream()
                .filter(u -> u.getRole() == Role.PROVIDER && !u.isApproved())
                .map(ResponseMapper::toUserResponse)
                .toList();
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<UserResponse> approveProvider(@PathVariable Long id) {
        User provider = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        if (provider.getRole() != Role.PROVIDER) {
            return ResponseEntity.badRequest().build();
        }

        provider.setApproved(true);
        userRepository.save(provider);

        return ResponseEntity.ok(ResponseMapper.toUserResponse(provider));
    }
}
