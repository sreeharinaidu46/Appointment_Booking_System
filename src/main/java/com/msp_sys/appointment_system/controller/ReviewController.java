// ReviewController.java
package com.msp_sys.appointment_system.controller;

import com.msp_sys.appointment_system.dto.ReviewRequest;
import com.msp_sys.appointment_system.model.*;
import com.msp_sys.appointment_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/submit")
    public ResponseEntity<?> submitReview(@RequestBody ReviewRequest req, Authentication auth) {
        User customer = userRepository.findByEmail(auth.getName()).orElseThrow();
        Appointment appointment = appointmentRepository.findById(req.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (!appointment.getCustomer().getEmail().equals(auth.getName()))
            return ResponseEntity.status(403).body("You can only review your own appointments");

        if (!appointment.isServed())
            return ResponseEntity.badRequest().body("Only served appointments can be reviewed");

        if (reviewRepository.findByAppointment(appointment).isPresent())
            return ResponseEntity.badRequest().body("Review already submitted for this appointment");

        Review review = new Review();
        review.setAppointment(appointment);
        review.setCustomer(customer);
        review.setProvider(appointment.getProvider());
        review.setRating(req.getRating());
        review.setComment(req.getComment());

        reviewRepository.save(review);
        return ResponseEntity.ok("Review submitted successfully");
    }

    @GetMapping("/provider/{providerId}")
    public List<Review> getProviderReviews(@PathVariable Long providerId) {
        User provider = userRepository.findById(providerId).orElseThrow();
        return reviewRepository.findByProvider(provider);
    }

    @GetMapping("/my")
    public List<Review> myReviews(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName()).orElseThrow();
        return reviewRepository.findByProvider(user);
    }
}
