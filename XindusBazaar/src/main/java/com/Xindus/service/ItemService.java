package com.Xindus.service;

import java.util.List;

import com.Xindus.model.Items;

public interface ItemService {
	public Items createItem(Items item);

	public Items getItemById(Integer itemId);

	public List<Items> getItemList();
}
