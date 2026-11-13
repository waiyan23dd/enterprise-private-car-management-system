package com.transport_management.entity;



import com.transport_management.entity.base.BaseEntity;
import com.transport_management.enums.UserStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle_owners")
@Getter
@Setter
@NoArgsConstructor
public class VehicleOwner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    private String fullName;

    private String phone;

    private String email;

    private String address;

    private String bankAccountName;

    private String bankAccountNumber;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
    
    @Column(name = "profile_image")
    private String profileImage;
}
