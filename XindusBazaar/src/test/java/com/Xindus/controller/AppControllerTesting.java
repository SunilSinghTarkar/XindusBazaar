package com.Xindus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Xindus.controller.AppController;
import com.Xindus.model.Items;
import com.Xindus.model.Users;
import com.Xindus.service.ItemService;
import com.Xindus.service.UserService;

@ExtendWith(MockitoExtension.class)
public class AppControllerTesting {

	@Mock
	private UserService userService;

	@Mock
	private ItemService itemService;
	@Mock
	private PasswordEncoder passwordEncoder;
	@Mock
	private Authentication authentication;

	@InjectMocks
	private AppController appController;

	@Test
	public void testRegisterUser() {
		Users userToRegister = new Users(1, "Sunil", "sunil02@gmail.com", "1234");
		when(userService.registerUser(userToRegister)).thenReturn(userToRegister);

		ResponseEntity<Users> responseEntity = appController.registerUser(userToRegister);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(userToRegister, responseEntity.getBody());
		verify(userService, times(1)).registerUser(userToRegister);
	}

	@Test
	public void testGetUser() {
		int userId = 1;
		Users expectedUser = new Users(userId, "Sunil", "sunil02@gmail.com", "1234");
		when(userService.getUserById(userId)).thenReturn(expectedUser);

		ResponseEntity<Users> responseEntity = appController.getUser(userId);

		assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
		assertEquals(expectedUser, responseEntity.getBody());
		verify(userService, times(1)).getUserById(userId);
	}

	@Test
	public void testCreateItem() {
		Items itemToCreate = new Items(1, "Item01", "Electronics", 199.99);
		when(itemService.createItem(itemToCreate)).thenReturn(itemToCreate);

		ResponseEntity<Items> responseEntity = appController.createItem(itemToCreate);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(itemToCreate, responseEntity.getBody());
		verify(itemService, times(1)).createItem(itemToCreate);
	}

	@Test
	public void testGetItem() {
		int itemId = 1;
		Items expectedItem = new Items(itemId, "Item01", "Electronics", 199.99);
		when(itemService.getItemById(itemId)).thenReturn(expectedItem);

		ResponseEntity<Items> responseEntity = appController.getItem(itemId);

		assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
		assertEquals(expectedItem, responseEntity.getBody());
		verify(itemService, times(1)).getItemById(itemId);
	}

	@Test
	public void testGetItemList() {
		List<Items> expectedItemList = new ArrayList<>();
		expectedItemList.add(new Items(1, "Item01", "Electronics", 199.99));
		expectedItemList.add(new Items(2, "Item02", "Books", 29.99));
		when(itemService.getItemList()).thenReturn(expectedItemList);

		ResponseEntity<List<Items>> responseEntity = appController.getItemList();

		assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
		assertEquals(expectedItemList, responseEntity.getBody());
		verify(itemService, times(1)).getItemList();
	}

	@Test
	public void testGetUserByEmail() {
		String userEmail = "sunil02@gmail.com";
		Users expectedUser = new Users(1, "Sunil", userEmail, "1234");

		when(authentication.getName()).thenReturn(userEmail);
		when(userService.getUserByEmail(userEmail)).thenReturn(expectedUser);

		ResponseEntity<Users> responseEntity = appController.getCustomerByEmail(authentication);

		assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
		assertEquals(expectedUser, responseEntity.getBody());
		verify(userService, times(1)).getUserByEmail(userEmail);
	}
}
