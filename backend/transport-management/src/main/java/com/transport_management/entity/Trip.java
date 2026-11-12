package com.transport_management.entity;

import com.transport_management.entity.base.BaseEntity;
import com.transport_management.enums.TripStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
public class Trip extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;

    @OneToOne
    @JoinColumn(name = "booking_id", unique = true)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    private LocalDateTime actualDepartureTime;

    private LocalDateTime actualArrivalTime;

    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus;

    private Integer passengerCount;

    @Column(columnDefinition = "TEXT")
    private String notes;
}