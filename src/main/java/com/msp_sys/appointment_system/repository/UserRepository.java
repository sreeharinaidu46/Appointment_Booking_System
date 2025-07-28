package com.msp_sys.appointment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msp_sys.appointment_system.model.Role;
import com.msp_sys.appointment_system.model.User;

import java.util.List;
import java.util.Optional;


// UserRepository.java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByRoleAndIsApproved(Role provider, boolean b);
}

