package com.Xindus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xindus.exception.NotFoundException;
import com.Xindus.model.Items;
import com.Xindus.repository.ItemsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemsRepository itemRepo;

	@Override
	public Items createItem(Items item) {
		log.info("Creating a new item: ", item.getItemName());

		// Saving the item to the database
		Items savedItem = itemRepo.save(item);

		log.info("Item created successfully. Item ID: ", savedItem.getItemId());
		return savedItem;
	}

	@Override
	public Items getItemById(Integer itemId) {
		log.info("Fetching item with ID: ", itemId);

		// Fetching item from the database by ID
		Items item = itemRepo.findById(itemId)
				.orElseThrow(() -> new NotFoundException("Item not found with given itemId " + itemId));

		log.info("Item fetched successfully. Item ID: ", itemId);
		return item;
	}

	@Override
	public List<Items> getItemList() {
		log.info("Fetching item List from Database");

		// Fetching item list from database
		List<Items> itemList = itemRepo.findAll();

		if (itemList.isEmpty())
			throw new NotFoundException("Item list not found!");

		log.info("Item List fetched successfully.");
		return itemList;
	}

}
