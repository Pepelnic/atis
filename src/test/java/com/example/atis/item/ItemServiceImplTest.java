package com.example.atis.item;

import com.example.atis.item.category.ItemCategory;
import com.example.atis.item.log.ItemLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ItemServiceImplTest {

    @Autowired
    private ItemServiceImpl itemService;

    @MockitoBean
    private ItemRepository itemRepository;

    @MockitoBean
    private ItemLogService itemLogService;

    private ItemCategory createCategory(Long id, String name) {
        return ItemCategory.builder()
                .id(id)
                .name(name)
                .type(null) // Assuming ItemType setup is optional here
                .build();
    }

    private Item createItem(Long id, String name, ItemCategory category, String unit, boolean isActive) {
        return Item.builder()
                .id(id)
                .name(name)
                .category(category)
                .unit(unit)
                .isActive(isActive)
                .build();
    }

    private void mockSaveItem(Item itemToReturn) {
        when(itemRepository.save(any(Item.class))).thenReturn(itemToReturn);
    }

    @Test
    void shouldCreateItemSuccessfully() {
        // Arrange
        ItemCategory category = createCategory(1L, "Electronics");
        Item savedItem = createItem(1L, "Laptop", category, "pcs", true);

        mockSaveItem(savedItem);

        // Act
        Item createdItem = itemService.createItem("Laptop", category, "pcs", true);

        // Assert
        assertThat(createdItem.getId()).isEqualTo(savedItem.getId());
        assertThat(createdItem.getName()).isEqualTo(savedItem.getName());
        assertThat(createdItem.getCategory()).isEqualTo(savedItem.getCategory());
        assertThat(createdItem.getUnit()).isEqualTo(savedItem.getUnit());
        assertThat(createdItem.getIsActive()).isTrue();

        verify(itemRepository, times(1)).save(any(Item.class));
        verify(itemLogService, times(1)).logItemAction(
                eq(savedItem), eq("Created"), isNull(), anyString(), eq("System")
        );
    }

    @Test
    void shouldUpdateItemSuccessfully() {
        // Arrange
        ItemCategory category = createCategory(1L, "Furniture");
        Item retrievedItem = createItem(1L, "Table", category, "pcs", true);

        when(itemRepository.findById(1L)).thenReturn(Optional.of(retrievedItem));
        when(itemRepository.save(any(Item.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Item updatedItem = itemService.updateItem(1L, "New Table", "sets", "Admin");

        // Assert
        assertThat(updatedItem.getName()).isEqualTo("New Table");
        assertThat(updatedItem.getUnit()).isEqualTo("sets");

        verify(itemRepository, times(1)).findById(1L);
        verify(itemRepository, times(1)).save(retrievedItem);
        verify(itemLogService, times(1)).logItemAction(
                eq(retrievedItem), eq("Updated"), anyString(), anyString(), eq("Admin")
        );
    }

    @Test
    void shouldFailToCreateItemWhenRepositoryFails() {
        // Arrange
        ItemCategory category = createCategory(1L, "Toys");
        when(itemRepository.save(any(Item.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () ->
                itemService.createItem("Car Toy", category, "sets", true)
        );

        verify(itemRepository, times(1)).save(any(Item.class));
        verify(itemLogService, never()).logItemAction(any(Item.class), anyString(), any(), any(), any());
    }

    @Test
    void shouldDeactivateItemSuccessfully() {
        // Arrange
        ItemCategory category = createCategory(1L, "Electronics");
        Item retrievedItem = createItem(1L, "Smartphone", category, "pcs", true);

        when(itemRepository.findById(1L)).thenReturn(Optional.of(retrievedItem));
        when(itemRepository.save(any(Item.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        itemService.deactivateItem(1L, "Admin");

        // Assert
        assertThat(retrievedItem.getIsActive()).isFalse();

        verify(itemRepository, times(1)).findById(1L);
        verify(itemRepository, times(1)).save(retrievedItem);
        verify(itemLogService, times(1)).logItemAction(
                eq(retrievedItem), eq("Deactivated"), anyString(), eq("Deactivated"), eq("Admin")
        );
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentItem() {
        // Arrange
        when(itemRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () ->
                itemService.updateItem(99L, "Nonexistent", "units", "Admin")
        );

        verify(itemRepository, times(1)).findById(99L);
        verify(itemRepository, never()).save(any(Item.class));
        verify(itemLogService, never()).logItemAction(any(Item.class), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void shouldThrowExceptionWhenItemNotFoundById() {
        // Arrange
        when(itemRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> itemService.getItemById(999L));

        verify(itemRepository, times(1)).findById(999L);
    }

    @Test
    void shouldLogItemCreation() {
        // Arrange
        ItemCategory category = createCategory(1L, "Electronics");
        Item savedItem = createItem(1L, "Laptop", category, "pcs", true);

        mockSaveItem(savedItem);

        // Act
        itemService.createItem("Laptop", category, "pcs", true);

        // Assert
        verify(itemLogService, times(1)).logItemAction(
                eq(savedItem), eq("Created"), isNull(), anyString(), eq("System")
        );
    }
}