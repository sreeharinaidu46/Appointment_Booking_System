package com.msp_sys.appointment_system.dto;

import com.msp_sys.appointment_system.model.BusinessCategory;
import com.msp_sys.appointment_system.model.Role;

import java.util.List;

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private String businessName;
    private boolean approved;
    private BusinessCategory category;
    private String agencyImageUrl;

    // ✅ New field for top reviews
    private List<ReviewResponse> topReviews;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public BusinessCategory getCategory() {
        return category;
    }

    public void setCategory(BusinessCategory category) {
        this.category = category;
    }

    public String getAgencyImageUrl() {
        return agencyImageUrl;
    }

    public void setAgencyImageUrl(String agencyImageUrl) {
        this.agencyImageUrl = agencyImageUrl;
    }

    public List<ReviewResponse> getTopReviews() {
        return topReviews;
    }

    public void setTopReviews(List<ReviewResponse> topReviews) {
        this.topReviews = topReviews;
    }
}
