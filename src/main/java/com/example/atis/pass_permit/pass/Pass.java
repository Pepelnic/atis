package com.example.atis.pass_permit.pass;

import com.example.atis.address.Address;
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
public class Pass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String passNumber;

    @NotBlank
    private String passType;

    private LocalDateTime validFrom;

    private LocalDateTime validTo;

    private String status;

    @Builder.Default
    private boolean isActive = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void activate() {
        this.status = "ACTIVE";
        this.isActive = true;
    }

    public void revoke() {
        this.status = "REVOKED";
        this.isActive = false;
    }

    public boolean isCurrentlyValid() {
        LocalDateTime now = LocalDateTime.now();
        return this.isActive && now.isAfter(validFrom) && now.isBefore(validTo);
    }
}
