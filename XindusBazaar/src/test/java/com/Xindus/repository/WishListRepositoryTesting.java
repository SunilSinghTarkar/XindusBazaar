package com.Xindus.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.Xindus.model.Items;
import com.Xindus.model.WishLists;
import com.Xindus.service.ItemService;
import com.Xindus.service.WishListService;
import com.Xindus.service.WishListServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class WishListRepositoryTesting {

	@Mock
	private WishListRepository wishListRepo;

	@Mock
	private ItemService itemServiceMock;

	@InjectMocks
	private WishListService wishListService = new WishListServiceImpl();

	@Test
	void testGetWishList() {
		int wishListId = 1;
		log.info("Testing getWishList method with wishListId: {}", wishListId);

		WishLists expectedWishList = new WishLists(wishListId, new ArrayList<>());
		when(wishListRepo.findById(wishListId)).thenReturn(Optional.of(expectedWishList));

		WishLists actualWishList = wishListService.getWishList(wishListId);

		// Assert
		assertNotNull(actualWishList);
		assertEquals(expectedWishList, actualWishList);
		log.info("getWishList test completed successfully.");
	}

	@Test
	void testAddToWishList() {
		int wishListId = 1;
		int itemId = 2;
		Items item = new Items(itemId, "Item02", "Books", 29.99);
		log.info("Testing addToWishList method with wishListId: {} and itemId: {}", wishListId, itemId);

		WishLists wishList = new WishLists(wishListId, new ArrayList<>());
		when(wishListRepo.findById(wishListId)).thenReturn(Optional.of(wishList));
		when(itemServiceMock.getItemById(itemId)).thenReturn(item);
		when(wishListRepo.save(any(WishLists.class))).thenAnswer(invocation -> invocation.getArgument(0));

		WishLists updatedWishList = wishListService.addToWishList(wishListId, itemId);

		// Assert
		assertNotNull(updatedWishList);
		verify(wishListRepo, times(1)).findById(wishListId);
		verify(itemServiceMock, times(1)).getItemById(itemId);
		verify(wishListRepo, times(1)).save(any(WishLists.class));
		log.info("addToWishList test completed successfully.");
	}

	@Test
	void testRemoveFromWishList() {
		int wishListId = 1;
		int itemId = 2;
		Items item = new Items(itemId, "Item02", "Books", 29.99);
		List<Items> itemList = new ArrayList<>();
		itemList.add(item);
		WishLists wishList = new WishLists(wishListId, itemList);
		log.info("Testing removeFromWishList method with wishListId: {} and itemId: {}", wishListId, itemId);

		when(wishListRepo.findById(wishListId)).thenReturn(Optional.of(wishList));
		when(itemServiceMock.getItemById(itemId)).thenReturn(item);
		when(wishListRepo.save(any(WishLists.class))).thenAnswer(invocation -> invocation.getArgument(0));

		WishLists updatedWishList = wishListService.removeFromWishList(wishListId, itemId);

		// Assert
		assertNotNull(updatedWishList);
		verify(wishListRepo, times(1)).findById(wishListId);
		verify(itemServiceMock, times(1)).getItemById(itemId);
		verify(wishListRepo, times(1)).save(any(WishLists.class));
		log.info("removeFromWishList test completed successfully.");
	}

}
