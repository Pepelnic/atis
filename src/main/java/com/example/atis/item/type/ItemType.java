package com.example.atis.item.type;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Название типа, например Material, Hardware

    private String description; // Описание типа
}