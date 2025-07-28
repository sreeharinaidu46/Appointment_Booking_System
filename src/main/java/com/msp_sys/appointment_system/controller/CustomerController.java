package com.msp_sys.appointment_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msp_sys.appointment_system.model.Role;
import com.msp_sys.appointment_system.model.User;
import com.msp_sys.appointment_system.repository.UserRepository;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/providers")
    public List<User> getApprovedProviders() {
        return userRepository.findByRoleAndIsApproved(Role.PROVIDER, true);
    }
}