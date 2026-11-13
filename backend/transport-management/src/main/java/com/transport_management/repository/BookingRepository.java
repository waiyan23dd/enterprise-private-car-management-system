package com.transport_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transport_management.entity.Booking;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingNumberAndDeletedAtIsNull(String bookingNumber);

    List<Booking> findAllByCustomerUserIdAndDeletedAtIsNull(Long customerId);

    List<Booking> findAllByDeletedAtIsNull();

    boolean existsByBookingNumber(String bookingNumber);
}