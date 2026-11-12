package com.transport_management.entity;

import com.transport_management.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "fares")
public class Fare extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fare_id")
    private Long fareId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @Column(name = "whole_car_fare", precision = 10, scale = 2, nullable = false)
    private BigDecimal wholeCarFare;

    @Column(name = "price_per_passenger", precision = 10, scale = 2, nullable = false)
    private BigDecimal pricePerPassenger;

    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(name = "active", nullable = false)
    private Boolean active = true;
}