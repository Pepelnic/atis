package com.example.atis.item;

import com.example.atis.item.category.ItemCategory;
import com.example.atis.units.Unit;

import java.util.List;

public interface ItemService {
    Item createItem(String name, ItemCategory category, Unit unit, boolean isActive);
    List<Item> getAllItems();
    Item getItemById(Long id);
    Item updateItem(Long id, String newName, Unit newUnit, String changedBy);
    void deactivateItem(Long id, String changedBy);
    void deleteItem(Long id, String changedBy);
}
