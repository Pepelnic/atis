package com.example.atis.pass_permit.pass;

import java.util.List;

public interface PassService {
    Pass createOrUpdate(Pass pass);
    Pass getById(Long id);
    void deleteById(Long id);
    List<Pass> getAll();
}
