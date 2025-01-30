package com.example.atis.pass_permit.history;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pass-histories")
@RequiredArgsConstructor
public class PassHistoryController {

    private final PassHistoryService passHistoryService;

    @PostMapping
    public ResponseEntity<PassHistory> createOrUpdate(@Valid @RequestBody PassHistory passHistory) {
        return ResponseEntity.ok(passHistoryService.createOrUpdate(passHistory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassHistory> getById(@PathVariable Long id) {
        return ResponseEntity.ok(passHistoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        passHistoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PassHistory>> getAll() {
        return ResponseEntity.ok(passHistoryService.getAll());
    }

    @GetMapping("/last/{passId}")
    public ResponseEntity<PassHistory> getLastHistoryByPass(@PathVariable Long passId) {
        return ResponseEntity.ok(passHistoryService.getLastHistoryByPass(passId));
    }
}
