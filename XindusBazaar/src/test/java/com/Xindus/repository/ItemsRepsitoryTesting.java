package com.Xindus.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.Xindus.model.Items;

@SpringBootTest
public class ItemsRepsitoryTesting {

	@Autowired
	private ItemsRepository itemsRepo;

	@Test
	@Transactional
	public void testSaveAndFindById() {

		Items itemToSave = new Items(1, "Item01", "Electronics", 199.99);

		Items savedItem = itemsRepo.save(itemToSave);

		assertNotNull(savedItem.getItemId());
		assertEquals("Item01", savedItem.getItemName());
		assertEquals("Electronics", savedItem.getCategory());
		assertEquals(199.99, savedItem.getPrice());

		// Fetch the item by ID and assert
		Items foundItem = itemsRepo.findById(savedItem.getItemId()).orElse(null);
		assertNotNull(foundItem);
		assertEquals(savedItem, foundItem);
	}

	@Test
	public void testFindAll() {

		Items item1 = new Items(10, "Item010", "Electronics", 299.99);
		Items item2 = new Items(11, "Item011", "Books", 49.99);

		List<Items> existingData = itemsRepo.findAll();
 
		itemsRepo.save(item1);
		itemsRepo.save(item2);

		// Assert
		List<Items> itemList = itemsRepo.findAll();
		assertNotNull(itemList);
		
		int newSize = itemList.size()- existingData.size();

		assertEquals(2, newSize);

		itemsRepo.delete(item1);
		itemsRepo.delete(item2);
	}

}
