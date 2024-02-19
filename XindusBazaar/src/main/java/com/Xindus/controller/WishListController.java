package com.Xindus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Xindus.model.Items;
import com.Xindus.model.WishLists;
import com.Xindus.service.WishListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/wishlists")
public class WishListController {
	@Autowired
	private WishListService wishListService;

	// Method for fetching WishLists by wishListId.
	@GetMapping
	public ResponseEntity<WishLists> getWishList() {
		log.info("Inside getWishList Method of WishListController.");
		WishLists wishList = wishListService.getWishList();

		return new ResponseEntity<WishLists>(wishList, HttpStatus.ACCEPTED);
	}

	// Method for Adding item to User's WishList.
	@PostMapping
	public ResponseEntity<WishLists> addWishListItem(@RequestBody Items item) {
		log.info("Inside addWishListItem Method of WishListController.");

		WishLists wishList = wishListService.addToWishList(item);
		return new ResponseEntity<WishLists>(wishList, HttpStatus.CREATED);
	}

	// Method for Removing item from User's WishList.
	@DeleteMapping("/{id}")
	public ResponseEntity<WishLists> deleteWishListItem(@PathVariable(value = "id") Integer itemId) {
		log.info("Inside deleteWishListItem Method of WishListController.");

		WishLists wishList = wishListService.removeFromWishList(itemId);
		return new ResponseEntity<WishLists>(wishList, HttpStatus.OK);
	}

}
