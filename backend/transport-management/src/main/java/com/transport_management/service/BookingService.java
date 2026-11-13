package com.transport_management.service;

import com.transport_management.entity.Booking;
import com.transport_management.enums.BookingStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingService {

    Booking createBooking(Booking booking);

    Optional<Booking> getBookingByNumber(String bookingNumber);

    List<Booking> getBookingsByCustomer(Long customerId);

    List<Booking> getBookingsByTravelDate(LocalDate travelDate);

    List<Booking> getBookingsByStatus(BookingStatus status);

    Booking confirmBooking(Long bookingId);

    Booking cancelBooking(Long bookingId);

    Booking completeBooking(Long bookingId);

    List<Booking> searchBookings(String keyword);
}