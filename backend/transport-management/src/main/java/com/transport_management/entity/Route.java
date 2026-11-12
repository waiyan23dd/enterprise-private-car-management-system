package com.transport_management.entity;

import com.transport_management.entity.base.BaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
public class Route extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    private String routeName;

    private String departureLocation;

    private String destination;

    @Column(precision = 10, scale = 2)
    private BigDecimal distanceKm;

    private Integer estimatedDuration;

    private String description;

    private Boolean active;
}