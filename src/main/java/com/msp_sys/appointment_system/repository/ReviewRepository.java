package com.msp_sys.appointment_system.repository;

import com.msp_sys.appointment_system.model.Appointment;
import com.msp_sys.appointment_system.model.Review;
import com.msp_sys.appointment_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProvider(User provider);

    Optional<Review> findByAppointment(Appointment appointment);

    List<Review> findTop3ByAppointmentProviderOrderByRatingDesc(User provider);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.appointment.provider.id = :providerId")
    Double findAverageRatingByProvider(@Param("providerId") Long providerId);
}
