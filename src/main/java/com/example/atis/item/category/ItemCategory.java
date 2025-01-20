package com.example.atis.item.category;

import jakarta.persistence.*;
import lombok.*;
import com.example.atis.item.type.ItemType;

@Entity
@Table(name = "item_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Название категории, например ЛДСП, Шурупы

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private ItemType type; // Связь с типом, например Material или Hardware

    private String description; // Описание категории
}
