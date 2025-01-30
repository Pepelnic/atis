package com.example.atis.pass_permit.pass;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassServiceImpl implements PassService {

    private final PassRepository passRepository;

    @Override
    public Pass createOrUpdate(Pass pass) {
        return passRepository.save(pass);
    }

    @Override
    public Pass getById(Long id) {
        return passRepository.findById(id).orElseThrow(() -> new RuntimeException("Pass not found"));
    }

    @Override
    public void deleteById(Long id) {
        if (!passRepository.existsById(id)) {
            throw new RuntimeException("Pass not found");
        }
        passRepository.deleteById(id);
    }

    @Override
    public List<Pass> getAll() {
        return passRepository.findAll();
    }
}
