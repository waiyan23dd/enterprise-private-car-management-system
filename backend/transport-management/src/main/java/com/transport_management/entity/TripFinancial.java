package com.transport_management.entity;

import com.transport_management.entity.base.BaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "trip_financials")
@Getter
@Setter
@NoArgsConstructor
public class TripFinancial extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long financialId;

    @OneToOne
    @JoinColumn(name = "trip_id", unique = true)
    private Trip trip;

    @Column(precision = 12, scale = 2)
    private BigDecimal totalRevenue;

    @Column(precision = 5, scale = 2)
    private BigDecimal ownerPercentage;

    @Column(precision = 12, scale = 2)
    private BigDecimal ownerFee;

    @Column(precision = 5, scale = 2)
    private BigDecimal driverPercentage;

    @Column(precision = 12, scale = 2)
    private BigDecimal driverPay;

    @Column(precision = 12, scale = 2)
    private BigDecimal gateFee;

    @Column(precision = 12, scale = 2)
    private BigDecimal otherExpenses;

    @Column(precision = 12, scale = 2)
    private BigDecimal companyProfit;
}