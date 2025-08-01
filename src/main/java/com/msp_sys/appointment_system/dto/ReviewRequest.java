// ReviewRequest.java
package com.msp_sys.appointment_system.dto;

public class ReviewRequest {
    private Long appointmentId;
    private int rating;
    private String comment;

    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
