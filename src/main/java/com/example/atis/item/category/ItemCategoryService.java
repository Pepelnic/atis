package com.example.atis.item.category;

import com.example.atis.item.type.ItemType;

import java.util.List;

public interface ItemCategoryService {
    ItemCategory createItemCategory(String name, ItemType type, String description);
    List<ItemCategory> getAllItemCategories();
    ItemCategory getItemCategoryById(Long id);
    void deleteItemCategory(Long id);
}
