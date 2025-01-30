package com.example.atis.pass_permit.history;

import java.util.List;

public interface PassHistoryService {
    PassHistory createOrUpdate(PassHistory passHistory);
    PassHistory getById(Long id);
    void deleteById(Long id);
    List<PassHistory> getAll();
    PassHistory getLastHistoryByPass(Long passId);
}
