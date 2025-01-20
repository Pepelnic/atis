package com.example.atis.item.log;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemLogRepository extends JpaRepository<ItemLog, Long> {

    // Найти все логи для указанного элемента
    List<ItemLog> findByItemId(Long itemId);

    // Удалить все логи для указанного элемента
    void deleteByItemId(Long itemId);
}
