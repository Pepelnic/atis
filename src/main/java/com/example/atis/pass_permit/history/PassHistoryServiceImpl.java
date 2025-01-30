package com.example.atis.pass_permit.history;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassHistoryServiceImpl implements PassHistoryService {

    private final PassHistoryRepository passHistoryRepository;

    @Override
    public PassHistory createOrUpdate(PassHistory passHistory) {
        return passHistoryRepository.save(passHistory);
    }

    @Override
    public PassHistory getById(Long id) {
        return passHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pass history not found"));
    }

    @Override
    public void deleteById(Long id) {
        if (!passHistoryRepository.existsById(id)) {
            throw new RuntimeException("Pass history not found");
        }
        passHistoryRepository.deleteById(id);
    }

    @Override
    public List<PassHistory> getAll() {
        return passHistoryRepository.findAll();
    }

    @Override
    public PassHistory getLastHistoryByPass(Long passId) {
        return passHistoryRepository.findTopByPassIdOrderByDateDesc(passId)
                .orElseThrow(() -> new RuntimeException("No history found for the given pass"));
    }
}
