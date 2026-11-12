package com.transport_management.entity;


import com.transport_management.entity.base.BaseEntity;
import com.transport_management.enums.DriverStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@NoArgsConstructor
public class Driver extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Column(unique = true)
    private String licenseNumber;

    private LocalDate licenseExpiryDate;

    private Integer experienceYears;

    private String emergencyContact;

    @Enumerated(EnumType.STRING)
    private DriverStatus status;
    
    @OneToMany(
    	    mappedBy = "driver",
    	    cascade = {CascadeType.PERSIST, CascadeType.MERGE},
    	    orphanRemoval = false
    	)
    	private List<DriverImage> driverImages = new ArrayList<>();
}
