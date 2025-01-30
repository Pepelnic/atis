package com.example.atis.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address createOrUpdate(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address getById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
    }

    @Override
    public void deleteById(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new RuntimeException("Address not found");
        }
        addressRepository.deleteById(id);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}
