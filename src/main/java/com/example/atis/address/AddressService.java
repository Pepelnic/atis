package com.example.atis.address;

import java.util.List;

public interface AddressService {
    Address createOrUpdate(Address address);
    Address getById(Long id);
    void deleteById(Long id);
    List<Address> getAll();
}
