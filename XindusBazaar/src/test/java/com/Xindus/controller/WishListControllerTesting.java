package com.Xindus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Xindus.controller.WishListController;
import com.Xindus.model.WishLists;
import com.Xindus.service.WishListService;
@ExtendWith(MockitoExtension.class)
public class WishListControllerTesting {

	@Mock
	private WishListService wishListService;

	@InjectMocks
	private WishListController wishListController = new WishListController();

	@Test
	public void testGetWishList() {

		int wishListId = 1;
		WishLists expectedWishList = new WishLists(wishListId, new ArrayList<>());

		when(wishListService.getWishList(wishListId)).thenReturn(expectedWishList);

		ResponseEntity<WishLists> responseEntity = wishListController.getWishList(wishListId);

		// Assert
		assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
		assertEquals(expectedWishList, responseEntity.getBody());
	}

	@Test
	public void testAddWishListItem() {

		int wishListId = 1;
		int itemId = 2;
		WishLists expectedWishList = new WishLists(wishListId, new ArrayList<>());

		when(wishListService.addToWishList(wishListId, itemId)).thenReturn(expectedWishList);

		ResponseEntity<WishLists> responseEntity = wishListController.addWishListItem(wishListId, itemId);

		// Assert
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(expectedWishList, responseEntity.getBody());
	}

	@Test
	public void testDeleteWishListItem() {

		int wishListId = 1;
		int itemId = 2;
		WishLists expectedWishList = new WishLists(wishListId, new ArrayList<>());

		when(wishListService.removeFromWishList(wishListId, itemId)).thenReturn(expectedWishList);

		ResponseEntity<WishLists> responseEntity = wishListController.deleteWishListItem(wishListId, itemId);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(expectedWishList, responseEntity.getBody());
	}

	
}
