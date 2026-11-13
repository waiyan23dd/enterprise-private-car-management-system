package com.transport_management.entity;

import java.util.ArrayList;
import java.util.List;

import com.transport_management.entity.base.BaseEntity;
import com.transport_management.enums.VehicleStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
public class Vehicle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private VehicleOwner owner;

    @Column(unique = true, nullable = false)
    private String vehicleNumber;

    private String vehicleModel;

    private String vehicleType;

    private String color;

    private Integer seatCapacity;

    private Integer manufactureYear;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;
    
    @OneToMany(
    	    mappedBy = "vehicle",
    	    cascade = {CascadeType.PERSIST, CascadeType.MERGE},
    	    orphanRemoval = false,
    	    fetch = FetchType.LAZY
    	)
    	private List<VehicleImage> vehicleImages = new ArrayList<>();
}