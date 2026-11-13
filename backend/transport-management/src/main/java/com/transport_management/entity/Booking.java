package com.transport_management.entity;

import com.transport_management.entity.base.BaseEntity;
import com.transport_management.enums.BookingPaymentStatus;
import com.transport_management.enums.BookingSource;
import com.transport_management.enums.BookingStatus;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.antlr.v4.runtime.misc.NotNull;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    private LocalDate bookingDate;

    private LocalDate travelDate;

    private LocalTime departureTime;

    @NotNull
    @Min(1)
    @Max(4)
    @Column(nullable = false)
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