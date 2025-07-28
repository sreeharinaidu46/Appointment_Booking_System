package com.msp_sys.appointment_system.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentResponse {
    private Long id;
    private Long customerId;
    private Long providerId;
    private String providerBusinessName;
    private LocalDate date;
    private LocalTime time;
    private String reason;
    private boolean served;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Long getProviderId() {
        return providerId;
    }
    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }
    public String getProviderBusinessName() {
        return providerBusinessName;
    }
    public void setProviderBusinessName(String providerBusinessName) {
        this.providerBusinessName = providerBusinessName;
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

    // Getters & Setters
    // ...
}
