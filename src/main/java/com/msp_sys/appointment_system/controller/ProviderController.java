package com.msp_sys.appointment_system.controller;

import com.msp_sys.appointment_system.dto.AppointmentResponse;
import com.msp_sys.appointment_system.model.Appointment;
import com.msp_sys.appointment_system.model.User;
import com.msp_sys.appointment_system.repository.AppointmentRepository;
import com.msp_sys.appointment_system.repository.UserRepository;
import com.msp_sys.appointment_system.util.ResponseMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/provider")
@PreAuthorize("hasAuthority('PROVIDER')")
public class ProviderController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/appointments")
    public List<AppointmentResponse> getProviderAppointments(Authentication auth) {
        User provider = userRepository.findByEmail(auth.getName()).orElseThrow();
        return appointmentRepository.findByProvider(provider).stream()
                .map(ResponseMapper::toAppointmentResponse)
                .toList();
    }

    @PutMapping("/appointments/served/{id}")
    public ResponseEntity<AppointmentResponse> markAsServed(@PathVariable Long id, Authentication auth) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();

        if (!appointment.getProvider().getEmail().equals(auth.getName())) {
            return ResponseEntity.status(403).build();
        }

        if (appointment.isServed()) {
            return ResponseEntity.badRequest().build();
        }

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        if (appointment.getDate().isAfter(today) || 
           (appointment.getDate().isEqual(today) && appointment.getTime() != null && appointment.getTime().isAfter(now))) {
            return ResponseEntity.badRequest().build();
        }

        appointment.setServed(true);
        appointmentRepository.save(appointment);

        return ResponseEntity.ok(ResponseMapper.toAppointmentResponse(appointment));
    }

    @DeleteMapping("/appointments/cancel/{id}")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long id, Authentication auth) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();

        if (!appointment.getProvider().getEmail().equals(auth.getName())) {
            return ResponseEntity.status(403).body("Unauthorized");
        }

        if (appointment.isServed()) {
            return ResponseEntity.badRequest().body("Cannot cancel served appointment");
        }

        appointmentRepository.delete(appointment);
        return ResponseEntity.ok("Appointment cancelled");
    }
}
