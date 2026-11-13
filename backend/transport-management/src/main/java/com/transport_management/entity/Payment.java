package com.transport_management.entity;

import com.transport_management.entity.base.BaseEntity;
import com.transport_management.enums.PaymentMethod;
import com.transport_management.enums.PaymentStatus;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

   

    private LocalDateTime paymentDate;

    private String transactionReference;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "received_by")
    private User receivedBy;

    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @OneToMany(
    	    mappedBy = "payment",
    	    cascade = {CascadeType.PERSIST, CascadeType.MERGE},
    	    orphanRemoval = false,
    	    fetch = FetchType.LAZY
    	)
    	private List<PaymentImage> paymentImages = new ArrayList<>();
}