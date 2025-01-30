package com.example.atis.pass_permit.history;

import com.example.atis.pass_permit.pass.Pass;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private String developerName;

    private String buildingName;

    private LocalDateTime validFrom;

    private LocalDateTime validTo;

    private String cost;

    private String costCurrency;

    private String conditions;

    private String status;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "pass_id")
    private Pass pass;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.date = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
