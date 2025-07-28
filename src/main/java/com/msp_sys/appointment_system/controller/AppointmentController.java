package com.msp_sys.appointment_system.controller;

import com.msp_sys.appointment_system.dto.*;
import com.msp_sys.appointment_system.model.*;
import com.msp_sys.appointment_system.repository.*;
import com.msp_sys.appointment_system.util.ResponseMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/book")
    public ResponseEntity<AppointmentResponse> bookAppointment(@RequestBody AppointmentRequest request, Authentication authentication) {
        String customerEmail = authentication.getName();
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        User provider = userRepository.findById(request.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setProvider(provider);
        appointment.setDate(request.getDate());
        appointment.setTime(request.getTime());
        appointment.setReason(request.getReason());
        appointment.setServed(false);

        appointmentRepository.save(appointment);
        return ResponseEntity.ok(ResponseMapper.toAppointmentResponse(appointment));
    }

    @GetMapping("/my")
    public List<AppointmentResponse> myAppointments(Authentication auth) {
        User customer = userRepository.findByEmail(auth.getName()).orElseThrow();
        return appointmentRepository.findByCustomer(customer).stream()
                .map(ResponseMapper::toAppointmentResponse)
                .toList();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AppointmentResponse> edit(@PathVariable Long id, @RequestBody AppointmentEditRequest req, Authentication auth) {
        Appointment a = appointmentRepository.findById(id).orElseThrow();

        if (a.isServed()) return ResponseEntity.badRequest().build();
        if (!a.getCustomer().getEmail().equals(auth.getName())) return ResponseEntity.status(403).build();

        a.setDate(LocalDate.parse(req.getDate()));
        a.setTime(LocalTime.parse(req.getTime()));
        appointmentRepository.save(a);
        return ResponseEntity.ok(ResponseMapper.toAppointmentResponse(a));
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable Long id, Authentication auth) {
        Appointment a = appointmentRepository.findById(id).orElseThrow();

        if (a.isServed()) return ResponseEntity.badRequest().body("Cannot cancel served appointment");
        if (!a.getCustomer().getEmail().equals(auth.getName())) return ResponseEntity.status(403).build();

        appointmentRepository.delete(a);
        return ResponseEntity.ok("Cancelled");
    }
}
