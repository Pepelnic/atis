package com.example.atis.item.log;

import com.example.atis.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemLogServiceImpl implements ItemLogService {

    private final ItemLogRepository itemLogRepository;

    @Override
    public void logItemAction(Item item, String action, String oldValue, String newValue, String changedBy) {
        ItemLog log = ItemLog.builder()
                .item(item)
                .action(action)
                .oldValue(oldValue)
                .newValue(newValue)
                .changedBy(changedBy)
                .build();

        itemLogRepository.save(log);
    }

    @Override
    public List<ItemLog> getLogsByItemId(Long itemId) {
        return itemLogRepository.findByItemId(itemId);
    }

    @Override
    public void deleteLogsByItemId(Long itemId) {
        itemLogRepository.deleteByItemId(itemId);
    }
}