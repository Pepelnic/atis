package com.example.atis.units;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    @Override
    public Unit createOrUpdate(Unit unit) {
        return unitRepository.save(unit);
    }

    @Override
    public Unit getById(Long id) {
        return unitRepository.findById(id).orElseThrow(() -> new RuntimeException("Unit not found"));
    }

    @Override
    public void deleteById(Long id) {
        if (!unitRepository.existsById(id)) {
            throw new RuntimeException("Unit not found");
        }
        unitRepository.deleteById(id);
    }

    @Override
    public List<Unit> getAll() {
        return unitRepository.findAll();
    }
}

