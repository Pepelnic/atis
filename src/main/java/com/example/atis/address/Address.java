package com.example.atis.address;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String country;

    private String region;

    @NotBlank
    private String city;

    private String district;

    private String street;

    @NotBlank
    private String buildingType;

    private String buildingNumber;

    private String buildingName;

    private String entrance;

    private String floorNumber;

    private String apartmentNumber;

    private String postalCode;

    private String googleMapLink;

    @Builder.Default
    private boolean isActive = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public String getFullAddress() {
        return String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s",
                country, region, city, district, street, buildingType, buildingNumber, floorNumber, apartmentNumber);
    }
}
