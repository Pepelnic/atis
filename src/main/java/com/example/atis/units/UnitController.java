package com.example.atis.units;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @PostMapping
    public ResponseEntity<Unit> createOrUpdate(@Valid @RequestBody Unit unit) {
        return ResponseEntity.ok(unitService.createOrUpdate(unit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unit> getById(@PathVariable Long id) {
        return ResponseEntity.ok(unitService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        unitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Unit>> getAll() {
        return ResponseEntity.ok(unitService.getAll());
    }
}