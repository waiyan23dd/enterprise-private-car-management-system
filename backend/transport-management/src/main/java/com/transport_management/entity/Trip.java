package com.transport_management.entity;

import com.transport_management.entity.base.BaseEntity;
import com.transport_management.enums.TripStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trips")
public class Trip extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Long tripId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", unique = true)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(name = "actual_departure_time")
    private LocalDateTime actualDepartureTime;

    @Column(name = "actual_arrival_time")
    private LocalDateTime actualArrivalTime;

    @DecimalMin("0.00")
    @Column(name = "total_revenue", precision = 12, scale = 2)
    private BigDecimal totalRevenue;

    @DecimalMin("0.00")
    @Column(name = "owner_fee", precision = 12, scale = 2)
    private BigDecimal ownerFee;

    @DecimalMin("0.00")
    @Column(name = "driver_pay", precision = 12, scale = 2)
    private BigDecimal driverPay;

    @DecimalMin("0.00")
    @Column(name = "gate_fee", precision = 12, scale = 2)
    private BigDecimal gateFee;

    @DecimalMin("0.00")
    @Column(name = "company_profit", precision = 12, scale = 2)
    private BigDecimal companyProfit;

    @Enumerated(EnumType.STRING)
    @Column(name = "trip_status")
    private TripStatus tripStatus;

    @NotNull
    @Min(1)
    @Max(4)
    @Column(name = "passenger_count", nullable = false)
    private Integer passengerCount;

    @Column(columnDefinition = "TEXT")
    private String notes;
}