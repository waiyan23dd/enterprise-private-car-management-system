package com.transport_management.entity;

import com.transport_management.entity.base.BaseEntity;
import com.transport_management.enums.PaymentMethod;
import com.transport_management.enums.RecipientType;
import com.transport_management.enums.SettlementStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "settlements")
@Getter
@Setter
@NoArgsConstructor
public class Settlement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settlementId;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Enumerated(EnumType.STRING)
    private RecipientType recipientType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private VehicleOwner owner;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(precision = 12, scale = 2)
    private BigDecimal amountDue;

    @Column(precision = 12, scale = 2)
    private BigDecimal amountPaid;

    @Column(precision = 12, scale = 2)
    private BigDecimal remainingBalance;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private LocalDate settlementDate;

    @Enumerated(EnumType.STRING)
    private SettlementStatus settlementStatus;

    @Column(columnDefinition = "TEXT")
    private String notes;
}