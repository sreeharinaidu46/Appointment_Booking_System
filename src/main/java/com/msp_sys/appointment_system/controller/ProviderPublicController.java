package com.msp_sys.appointment_system.controller;

import com.msp_sys.appointment_system.dto.ReviewResponse;
import com.msp_sys.appointment_system.dto.UserResponse;
import com.msp_sys.appointment_system.model.Role;
import com.msp_sys.appointment_system.model.User;
import com.msp_sys.appointment_system.repository.ReviewRepository;
import com.msp_sys.appointment_system.repository.UserRepository;
import com.msp_sys.appointment_system.util.ResponseMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderPublicController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/approved")
    public List<UserResponse> getApprovedProvidersWithTopReviews() {
        // ✅ Fetch only approved providers directly
        List<User> approvedProviders = userRepository.findByRoleAndIsApproved(Role.PROVIDER, true);

        // ✅ Map to DTO and attach top 3 reviews
        return approvedProviders.stream()
                .map(user -> {
                    UserResponse dto = ResponseMapper.toUserResponse(user);

                    List<ReviewResponse> topReviews = reviewRepository
                            .findTop3ByAppointmentProviderOrderByRatingDesc(user)
                            .stream()
                            .map(review -> ResponseMapper.toReviewResponse(review)) // ✅ Pass correct type
                            .toList();

                    dto.setTopReviews(topReviews);
                    return dto;
                })
                .toList();
    }
}
