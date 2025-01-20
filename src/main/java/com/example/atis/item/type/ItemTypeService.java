package com.example.atis.item.type;

import java.util.List;

public interface ItemTypeService {
    ItemType createItemType(String name, String description);
    List<ItemType> getAllItemTypes();
    ItemType getItemTypeById(Long id);
    void deleteItemType(Long id);
}
