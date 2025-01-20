package com.example.atis.item.type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemTypeServiceImpl implements ItemTypeService {

    private final ItemTypeRepository itemTypeRepository;

    @Override
    public ItemType createItemType(String name, String description) {
        ItemType itemType = ItemType.builder()
                .name(name)
                .description(description)
                .build();
        return itemTypeRepository.save(itemType);
    }

    @Override
    public List<ItemType> getAllItemTypes() {
        return itemTypeRepository.findAll();
    }

    @Override
    public ItemType getItemTypeById(Long id) {
        return itemTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemType not found"));
    }

    @Override
    public void deleteItemType(Long id) {
        if (!itemTypeRepository.existsById(id)) {
            throw new RuntimeException("ItemType not found");
        }
        itemTypeRepository.deleteById(id);
    }
}
