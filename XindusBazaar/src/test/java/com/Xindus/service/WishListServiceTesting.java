package com.Xindus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.Xindus.model.Items;
import com.Xindus.model.Users;
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

	@Mock
	private UserService userServiceMock; // Add UserService mock

	@InjectMocks
	private WishListService wishListService = new WishListServiceImpl();

	@Test
	public void testGetWishList() {
		int wishListId = 1;
		WishLists expectedWishList = new WishLists(wishListId, new ArrayList<>());

		// Set up a simple authentication object with an email for testing
		Authentication auth = new UsernamePasswordAuthenticationToken("testUser", "testPassword");
		SecurityContextHolder.getContext().setAuthentication(auth);

		// Mock the getUserByEmail method to return a user
		when(userServiceMock.getUserByEmail(any(String.class))).thenReturn(new Users());

		// Mock the findById method to return the expectedWishList
		when(wishListRepositoryMock.findById(wishListId)).thenReturn(java.util.Optional.of(expectedWishList));

		// Call the getWishList method
		WishLists fetchedWishList = wishListService.getWishList();

		// Assert
		assertNotNull(fetchedWishList);
		assertEquals(expectedWishList, fetchedWishList);

		// Log statements for debugging
		log.info("Expected WishList: {}", expectedWishList);
		log.info("Fetched WishList: {}", fetchedWishList);

		// Verify method invocations
		verify(wishListRepositoryMock, times(1)).findById(wishListId);
	}

	@Test
	public void testAddToWishList() {
		int wishListId = 1;
		int itemId = 2;

		Items itemToAdd = new Items(itemId, "Item02", "Books", 29.99);

		when(itemServiceMock.getItemById(itemId)).thenReturn(itemToAdd);

		when(userServiceMock.getUserByEmail(any(String.class))).thenReturn(new Users());

		when(wishListRepositoryMock.findById(wishListId))
				.thenReturn(java.util.Optional.of(new WishLists(wishListId, new ArrayList<Items>())));

		when(wishListRepositoryMock.save(any(WishLists.class))).thenAnswer(invocation -> invocation.getArgument(0));

		WishLists updatedWishList = wishListService.addToWishList(itemToAdd);

		// Assert
		assertNotNull(updatedWishList);

		// Log statements for debugging
		log.info("Original WishList: ", new WishLists(wishListId, new ArrayList<Items>()));
		log.info("WishList after adding item: ", updatedWishList);

		verify(wishListRepositoryMock, times(1)).findById(wishListId);
		verify(wishListRepositoryMock, times(1)).save(any(WishLists.class));
	}

	
}
