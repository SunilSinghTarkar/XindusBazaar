package com.Xindus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Xindus.model.Items;
import com.Xindus.model.WishLists;
import com.Xindus.repository.WishListRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class WishListServiceTesting {

	@Mock
	private WishListRepository wishListRepositoryMock;

	@Mock
	private ItemService itemServiceMock;

	@InjectMocks
	private WishListService wishListService = new WishListServiceImpl();

	@Test
	public void testGetWishList() {

		int wishListId = 1;
		WishLists expectedWishList = new WishLists(wishListId, new ArrayList<Items>());

		when(wishListRepositoryMock.findById(wishListId)).thenReturn(java.util.Optional.of(expectedWishList));

		WishLists fetchedWishList = wishListService.getWishList(wishListId);

		// Assert
		assertNotNull(fetchedWishList);
		assertEquals(expectedWishList, fetchedWishList);

		  // Log statements for debugging
	    log.info("Expected WishList: ", expectedWishList);
	    log.info("Fetched WishList: ", fetchedWishList);
	    
		verify(wishListRepositoryMock, times(1)).findById(wishListId);
	}

	@Test
	public void testAddToWishList() {

		int wishListId = 1;
		int itemId = 2;

		when(itemServiceMock.getItemById(itemId)).thenReturn(new Items(itemId, "Item02", "Books", 29.99));

		when(wishListRepositoryMock.findById(wishListId))
				.thenReturn(java.util.Optional.of(new WishLists(wishListId, new ArrayList<Items>())));

		when(wishListRepositoryMock.save(any(WishLists.class))).thenAnswer(invocation -> invocation.getArgument(0));

		WishLists updatedWishList = wishListService.addToWishList(wishListId, itemId);

		// Assert
		assertNotNull(updatedWishList);

		 // Log statements for debugging
	    log.info("Original WishList: ", new WishLists(wishListId, new ArrayList<Items>()));
	    log.info("WishList after adding item: ", updatedWishList);
	    
	    
		verify(wishListRepositoryMock, times(1)).findById(wishListId);
		verify(wishListRepositoryMock, times(1)).save(any(WishLists.class));
	}

	@Test
	public void testRemoveFromWishList() {
		
		int wishListId = 1;
		int itemId = 2;

		
		when(itemServiceMock.getItemById(itemId)).thenReturn(new Items(itemId, "Item02", "Books", 29.99));

		
		WishLists wishList = new WishLists(wishListId, new ArrayList<>());
		Items itemToRemove = new Items(itemId, "Item02", "Books", 29.99);
		wishList.getItemList().add(itemToRemove);

		
		when(wishListRepositoryMock.findById(wishListId)).thenReturn(java.util.Optional.of(wishList));

		
		when(wishListRepositoryMock.save(any(WishLists.class))).thenAnswer(invocation -> invocation.getArgument(0));

		
		WishLists updatedWishList = wishListService.removeFromWishList(wishListId, itemId);

		// Assert
		assertNotNull(updatedWishList);

		// Log statements for debugging
		log.info("Original WishList: ", wishList);
		log.info("Updated WishList after removal: ", updatedWishList);

		verify(wishListRepositoryMock, times(1)).findById(wishListId);
		verify(wishListRepositoryMock, times(1)).save(any(WishLists.class));
	}

}
