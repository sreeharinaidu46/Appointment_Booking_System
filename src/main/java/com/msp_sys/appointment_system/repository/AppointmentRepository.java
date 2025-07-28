package com.msp_sys.appointment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msp_sys.appointment_system.model.Appointment;
import com.msp_sys.appointment_system.model.User;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCustomer(User customer);
     List<Appointment> findByProvider(User provider);
}
