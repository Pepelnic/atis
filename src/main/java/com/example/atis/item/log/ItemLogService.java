package com.example.atis.item.log;

import com.example.atis.item.Item;

import java.util.List;

public interface ItemLogService {
    void logItemAction(Item item, String action, String oldValue, String newValue, String changedBy);
    List<ItemLog> getLogsByItemId(Long itemId);
    void deleteLogsByItemId(Long itemId);
}
