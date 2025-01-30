package com.example.atis.units;

import java.util.List;

public interface UnitService {
    Unit createOrUpdate(Unit unit);
    Unit getById(Long id);
    void deleteById(Long id);
    List<Unit> getAll();
}
