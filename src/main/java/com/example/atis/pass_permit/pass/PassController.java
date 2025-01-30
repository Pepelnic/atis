package com.example.atis.pass_permit.pass;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passes")
@RequiredArgsConstructor
public class PassController {

    private final PassService passService;

    @PostMapping
    public ResponseEntity<Pass> createOrUpdate(@Valid @RequestBody Pass pass) {
        return ResponseEntity.ok(passService.createOrUpdate(pass));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pass> getById(@PathVariable Long id) {
        return ResponseEntity.ok(passService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        passService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Pass>> getAll() {
        return ResponseEntity.ok(passService.getAll());
    }
}
