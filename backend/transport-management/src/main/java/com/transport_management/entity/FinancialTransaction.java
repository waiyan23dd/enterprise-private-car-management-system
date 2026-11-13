package com.transport_management.entity;

import com.transport_management.entity.base.BaseEntity;
import com.transport_management.enums.FinancialTransactionStatus;
import com.transport_management.enums.FinancialTransactionType;
import com.transport_management.enums.PaymentMethod;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(
    name = "financial_transactions",
    indexes = {
        @Index(name = "idx_financial_trip", columnList = "trip_id"),
        @Index(name = "idx_financial_type", columnList = "transaction_type"),
        @Index(name = "idx_financial_date", columnList = "transaction_date")
    }
)
public class FinancialTransaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private FinancialTransactionType transactionType;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status", nullable = false)
    private FinancialTransactionStatus transactionStatus;

    @Column(name = "expense_category", length = 100)
    private String expenseCategory;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "receipt_image")
    private String receiptImage;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private VehicleOwner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recorded_by", nullable = false)
    private User recordedBy;
}