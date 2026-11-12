package com.transport_management.entity;

import com.transport_management.entity.base.BaseEntity;
import com.transport_management.enums.FeedbackStatus;
import com.transport_management.enums.FeedbackType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "feedback")
@Getter
@Setter
@NoArgsConstructor
public class Feedback extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

//    @ManyToOne
//    @JoinColumn(name = "trip_id")
//    private Trip trip;

    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Enumerated(EnumType.STRING)
    private FeedbackType feedbackType;

    @Column(columnDefinition = "TEXT")
    private String response;

    @ManyToOne
    @JoinColumn(name = "responded_by")
    private User respondedBy;

    @Enumerated(EnumType.STRING)
    private FeedbackStatus feedbackStatus;
}