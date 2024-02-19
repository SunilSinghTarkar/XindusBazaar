package com.Xindus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Xindus.exception.NotFoundException;
import com.Xindus.model.Items;
import com.Xindus.model.Users;
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
	@Autowired
	private UserService userService;

	@Override
	public WishLists getWishList() {
	    log.info("Fetching WishList");

	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String userName = auth.getName();

	    // Fetching user from the database by email
	    Users user = userService.getUserByEmail(userName);

	    // Ensure that the user is not null
	    if (user == null) {
	        throw new NotFoundException("User not found!");
	    }

	    // Ensure that the user has a WishList initialized
	    if (user.getWishList() == null) {
	        user.setWishList(new WishLists()); // Initialize an empty WishList if null
	    }

	    // Fetching WishList from the database by user
	    WishLists wishList = user.getWishList();

	    log.info("WishList fetched successfully.");
	    return wishList;
	}

	@Override
	public WishLists addToWishList(Items item) {
		log.info("Adding item to WishList.");

		// Fetching User from authentication header.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth == null || auth.getName() == null) {
	        log.error("Authentication object or user name is null.");
	        throw new RuntimeException("Authentication object or user name is null.");
	    }

		
		String userName = auth.getName();
		Users user = userService.getUserByEmail(userName);

		if (user.getWishList() == null) {
			user.setWishList(new WishLists()); // empty WishList if null
		}

		// Fetching WishList from the database
		WishLists wishList = user.getWishList();

		// Create and save the item
		Items savedItem = itemService.createItem(item);

		// Adding item to the WishList
		wishList.getItemList().add(savedItem);

		// Saving the updated WishList to the database
		WishLists updatedWishList = wishListRepo.save(wishList);

		log.info("Item added to WishList successfully. Updated WishList.");
		return updatedWishList;
	}

	@Override
	public WishLists removeFromWishList(Integer itemId) {
		log.info("Removing item from WishList with ID :", itemId);

		// Fetching WishList and item from the database
		WishLists wishList = getWishList();
		Items item = itemService.getItemById(itemId);

		// Ensure that the wishList is not null
		if (wishList == null) {
			throw new NotFoundException("WishList not found!");
		}

		// Removing item from the WishList
		List<Items> list = wishList.getItemList();
		if (!list.contains(item))
			throw new NotFoundException("WishListItem not found!");
		list.remove(item);

		// Saving the updated WishList to the database
		WishLists updatedWishList = wishListRepo.save(wishList);

		log.info("Item removed from WishList successfully. Updated WishList.");
		return updatedWishList;
	}

}
