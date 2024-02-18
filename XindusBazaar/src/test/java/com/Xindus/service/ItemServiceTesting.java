package com.Xindus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Xindus.model.Items;
import com.Xindus.repository.ItemsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ItemServiceTesting {

	@Mock
	private ItemsRepository itemRepo;

	@InjectMocks
	private ItemService itemService = new ItemServiceImpl();

	@Test
	public void testCreateItem() {

		Items itemToCreate = new Items(1, "Item07", "Fashion", 120.20);

		when(itemRepo.save(itemToCreate)).thenReturn(new Items(1, "Item07", "Fashion", 120.20));

		Items createdItem = itemService.createItem(itemToCreate);

		// Assert
		assertNotNull(createdItem);
		assertEquals(itemToCreate.getItemName(), createdItem.getItemName());
		assertEquals(itemToCreate.getCategory(), createdItem.getCategory());
		assertEquals(itemToCreate.getPrice(), createdItem.getPrice());

		// Log statements for debugging
		log.info("Item to create: ", itemToCreate);
		log.info("Created Item: ", createdItem);
		verify(itemRepo, times(1)).save(itemToCreate);
	}

	@Test
	public void testGetItemById() {

		int itemId = 1;
		Items expectedItem = new Items(itemId, "Item07", "Fashion", 120.20);

		when(itemRepo.findById(itemId)).thenReturn(java.util.Optional.of(expectedItem));

		Items fetchedItem = itemService.getItemById(itemId);

		// Assert
		assertNotNull(fetchedItem);
		assertEquals(expectedItem, fetchedItem);

		// Log statements for debugging
		log.info("Expected Item by ID :", itemId, expectedItem);
		log.info("Fetched Item by ID :", itemId, fetchedItem);

		verify(itemRepo, times(1)).findById(itemId);
	}

	@Test
	public void testGetItemList() {

		List<Items> expectedItemList = Arrays.asList(new Items(1, "Item01", "Electronics", 199.99),
				new Items(2, "Item02", "Books", 29.99));

		when(itemRepo.findAll()).thenReturn(expectedItemList);

		List<Items> actualItemList = itemService.getItemList();

		// Assert
		assertNotNull(actualItemList);
		assertEquals(expectedItemList.size(), actualItemList.size());
		assertEquals(expectedItemList, actualItemList);

		// Log statements for debugging
		log.info("Expected Item List: ", expectedItemList);
		log.info("Actual Item List: ", actualItemList);

		verify(itemRepo, times(1)).findAll();
	}

}
