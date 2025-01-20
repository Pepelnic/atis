package com.example.atis.item.category;

import com.example.atis.item.type.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCategoryServiceImpl implements ItemCategoryService {

    private final ItemCategoryRepository itemCategoryRepository;

    @Override
    public ItemCategory createItemCategory(String name, ItemType type, String description) {
        ItemCategory category = ItemCategory.builder()
                .name(name)
                .type(type)
                .description(description)
                .build();
        return itemCategoryRepository.save(category);
    }

    @Override
    public List<ItemCategory> getAllItemCategories() {
        return itemCategoryRepository.findAll();
    }

    @Override
    public ItemCategory getItemCategoryById(Long id) {
        return itemCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemCategory not found"));
    }

    @Override
    public void deleteItemCategory(Long id) {
        if (!itemCategoryRepository.existsById(id)) {
            throw new RuntimeException("ItemCategory not found");
        }
        itemCategoryRepository.deleteById(id);
    }
}