package com.msp_sys.appointment_system.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToOne
    private User provider;

    private LocalDate date;
    private LocalTime time;
    private String reason;

    private boolean served = false;

    // Getters & Setters    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;       
    }
    public User getCustomer() {
        return customer;
    }
    public void setCustomer(User customer) {
        this.customer = customer;
    }
    public User getProvider() {
        return provider;
    }
    public void setProvider(User provider) {
        this.provider = provider;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public boolean isServed() {
        return served;
    }
    public void setServed(boolean served) {
        this.served = served;
    }
    
}