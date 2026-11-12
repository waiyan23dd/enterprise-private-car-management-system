package com.transport_management.entity;

import com.transport_management.entity.base.BaseEntity;
import com.transport_management.enums.QueueStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "vehicle_queue")
@Getter
@Setter
@NoArgsConstructor
public class VehicleQueue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private Integer queueNumber;

    private LocalDate queueDate;

    private LocalTime arrivalTime;

    private LocalTime calledTime;

    private LocalTime departureTime;

    @Enumerated(EnumType.STRING)
    private QueueStatus queueStatus;
}