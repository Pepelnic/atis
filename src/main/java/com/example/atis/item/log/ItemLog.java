package com.example.atis.item.log;

import com.example.atis.item.Item;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "item_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item; // Ссылка на элемент, по которому произошло изменение

    @Column(nullable = false)
    private String action; // Тип действия: Created, Updated, Deactivated

    @Lob
    private String oldValue; // Старое значение (может быть JSON или строка)

    @Lob
    private String newValue; // Новое значение (может быть JSON или строка)

    @Column(nullable = false)
    private String changedBy; // Кто внёс изменения

    @Column(nullable = false, updatable = false)
    private LocalDateTime changedAt = LocalDateTime.now(); // Время изменения
}
