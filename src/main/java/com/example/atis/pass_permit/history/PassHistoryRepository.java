package com.example.atis.pass_permit.history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassHistoryRepository extends JpaRepository<PassHistory, Long> {
    Optional<PassHistory> findTopByPassIdOrderByDateDesc(Long passId);
}
