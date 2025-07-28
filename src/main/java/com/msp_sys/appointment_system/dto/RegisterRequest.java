package com.msp_sys.appointment_system.dto;

import com.msp_sys.appointment_system.model.BusinessCategory;
import com.msp_sys.appointment_system.model.Role;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
    private String businessName;
   
    private BusinessCategory category;
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
    private String agencyImageUrl;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }
}