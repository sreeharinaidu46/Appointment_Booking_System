package com.msp_sys.appointment_system.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentRequest {
    private Long providerId;
    private LocalDate date;
    private LocalTime time;
    private String reason;

    public Long getProviderId() {
        return providerId;
    }
    public void setProviderId(Long providerId) {
        this.providerId = providerId;
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
}
