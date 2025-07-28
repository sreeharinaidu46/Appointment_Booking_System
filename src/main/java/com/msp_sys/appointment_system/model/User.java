package com.msp_sys.appointment_system.model;

import jakarta.persistence.*;

// User.java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String businessName; // Only for providers

    private boolean isApproved = false; // Approval flag for providers
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
private BusinessCategory category; // ✅ New field

@Lob
private String agencyImageUrl; // ✅ URL to agency image (stored as string for now)


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public boolean isApproved() { return isApproved; }
    public void setApproved(boolean approved) { isApproved = approved; }
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
}