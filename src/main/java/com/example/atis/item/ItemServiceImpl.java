package com.example.atis.item;

import com.example.atis.item.category.ItemCategory;
import com.example.atis.item.log.ItemLogService;
import com.example.atis.units.Unit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemLogService itemLogService;

    @Override
    public Item createItem(String name, ItemCategory category, Unit unit, boolean isActive) {
        Item item = Item.builder()
                .name(name)
                .category(category)
                .unit(unit)
                .isActive(isActive)
                .build();

        item = itemRepository.save(item);

        // Логирование
        itemLogService.logItemAction(item, "Created", null, item.toString(), "System");

        return item;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public Item updateItem(Long id, String newName, Unit newUnit, String changedBy) {
        Item item = getItemById(id);

        // Сохраняем старое значение
        String oldValue = item.toString();

        // Обновляем
        item.setName(newName);
        item.setUnit(newUnit);

        // Логирование
        itemLogService.logItemAction(item, "Updated", oldValue, item.toString(), changedBy);

        return itemRepository.save(item);
    }

    @Override
    public void deactivateItem(Long id, String changedBy) {
        Item item = getItemById(id);

        // Деактивация
        item.setIsActive(false);

        // Логирование
        itemLogService.logItemAction(item, "Deactivated", item.toString(), "Deactivated", changedBy);

        itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id, String changedBy) {
        Item item = getItemById(id);

        // Логируем удаление
        itemLogService.logItemAction(item, "Deleted", item.toString(), null, changedBy);

        // Удаляем логи
        itemLogService.deleteLogsByItemId(id);

        // Удаляем сам элемент
        itemRepository.deleteById(id);
    }
}