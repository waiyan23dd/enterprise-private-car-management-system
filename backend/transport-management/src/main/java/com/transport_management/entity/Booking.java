package com.transport_management.entity;

import com.transport_management.entity.base.BaseEntity;
import com.transport_management.enums.BookingPaymentStatus;
import com.transport_management.enums.BookingSource;
import com.transport_management.enums.BookingStatus;
import com.transport_management.enums.PaymentStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(unique = true, nullable = false)
    private String bookingNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    private LocalDate bookingDate;

    private LocalDate travelDate;

    private LocalTime departureTime;

    private Integer passengerCount;

    private String contactName;

    private String contactPhone;

    private String pickupAddress;

    private String dropoffAddress;

    @Column(precision = 12, scale = 2)
    private BigDecimal totalFare;

    @Enumerated(EnumType.STRING)
    private BookingSource bookingSource;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

   

    @Column(columnDefinition = "TEXT")
    private String specialRequest;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private BookingPaymentStatus bookingPaymentStatus;
}