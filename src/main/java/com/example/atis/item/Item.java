package com.example.atis.item;

import com.example.atis.item.category.ItemCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    // Add Supplier and SKU or Identification
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Название элемента, например ЛДСП Egger W1000

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ItemCategory category; // Связь с категорией

    @Column(nullable = false)
    private String unit; // Единица измерения, например м², шт.

    @Column(nullable = false)
    private Boolean isActive = true; // Активен ли элемент

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Дата создания

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now(); // Дата обновления

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now(); // Обновление времени при изменении
    }
}