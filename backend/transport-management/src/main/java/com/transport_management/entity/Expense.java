package com.transport_management.entity;

import com.transport_management.entity.base.BaseEntity;
import com.transport_management.enums.PaymentMethod;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
public class Expense extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    private String expenseCategory;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(precision = 12, scale = 2)
    private BigDecimal amount;

    private LocalDate expenseDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String receiptNumber;

    @ManyToOne
    @JoinColumn(name = "recorded_by")
    private User recordedBy;
}