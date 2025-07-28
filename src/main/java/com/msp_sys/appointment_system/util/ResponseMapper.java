package com.msp_sys.appointment_system.util;

import com.msp_sys.appointment_system.dto.*;
import com.msp_sys.appointment_system.model.*;

public class ResponseMapper {

    public static AppointmentResponse toAppointmentResponse(Appointment a) {
        AppointmentResponse r = new AppointmentResponse();
        r.setId(a.getId());
        r.setCustomerId(a.getCustomer().getId());
        r.setProviderId(a.getProvider().getId());
        r.setProviderBusinessName(a.getProvider().getBusinessName());
        r.setDate(a.getDate());
        r.setTime(a.getTime());
        r.setReason(a.getReason());
        r.setServed(a.isServed());
        return r;
    }

    public static UserResponse toUserResponse(User u) {
        UserResponse r = new UserResponse();
        r.setId(u.getId());
        r.setName(u.getName());
        r.setEmail(u.getEmail());
        r.setRole(u.getRole());
        r.setBusinessName(u.getBusinessName());
        r.setApproved(u.isApproved());
        r.setCategory(u.getCategory());
        r.setAgencyImageUrl(u.getAgencyImageUrl());
        return r;
    }

    public static ReviewResponse toReviewResponse(Review review) {
        ReviewResponse r = new ReviewResponse();
        r.setId(review.getId());
        r.setAppointmentId(review.getAppointment().getId());
        r.setRating(review.getRating());
        r.setComment(review.getComment());
        return r;
    }
}
