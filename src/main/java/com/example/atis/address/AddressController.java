package com.example.atis.address;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> createOrUpdate(@Valid @RequestBody Address address) {
        return ResponseEntity.ok(addressService.createOrUpdate(address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.ok(addressService.getAll());
    }
}
