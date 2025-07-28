package com.msp_sys.appointment_system.dto;

public class AppointmentEditRequest {
    private String date;
    private String time;
    // Getters & Setters
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}