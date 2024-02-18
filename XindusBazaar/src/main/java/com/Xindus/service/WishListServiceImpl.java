package com.Xindus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xindus.exception.NotFoundException;
import com.Xindus.model.Items;
import com.Xindus.model.WishLists;
import com.Xindus.repository.WishListRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WishListServiceImpl implements WishListService {
	@Autowired
	private WishListRepository wishListRepo;
	@Autowired
	private ItemService itemService;

	@Override
	public WishLists getWishList(Integer wishListId) {
		log.info("Fetching WishList with ID: ", wishListId);

		// Fetching WishList from the database by ID
		WishLists wishList = wishListRepo.findById(wishListId)
				.orElseThrow(() -> new NotFoundException("WishList not found with given Id"));

		log.info("WishList fetched successfully. WishList ID: ", wishListId);
		return wishList;
	}

	@Override
	public WishLists addToWishList(Integer wishListId, Integer itemId) {
		log.info("Adding item with ID  to WishList with ID: ", itemId, wishListId);

		// Fetching WishList and item from the database
		WishLists wishList = getWishList(wishListId);
		Items item = itemService.getItemById(itemId);

		// Adding item to the WishList
		wishList.getItemList().add(item);

		// Saving the updated WishList to the database
		WishLists updatedWishList = wishListRepo.save(wishList);

		log.info("Item added to WishList successfully. Updated WishList ID: ", updatedWishList.getWishListId());
		return updatedWishList;
	}

	@Override
	public WishLists removeFromWishList(Integer wishListId, Integer itemId) {
		log.info("Removing item with ID  from WishList with ID: ", itemId, wishListId);

		// Fetching WishList and item from the database
		WishLists wishList = getWishList(wishListId);
		Items item = itemService.getItemById(itemId);

		// Removing item from the WishList
		List<Items> list = wishList.getItemList();
		if (!list.contains(item))
			throw new NotFoundException("WishListItem not found!");
		list.remove(item);

		// Saving the updated WishList to the database
		WishLists updatedWishList = wishListRepo.save(wishList);

		log.info("Item removed from WishList successfully. Updated WishList ID: ", updatedWishList.getWishListId());
		return updatedWishList;
	}

}
