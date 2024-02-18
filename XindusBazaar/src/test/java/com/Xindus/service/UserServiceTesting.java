package com.Xindus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Xindus.model.Users;
import com.Xindus.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserServiceTesting {

	@Mock
	private UserRepository ur;

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Test
	public void registerUser() {
		Users expectedUser = new Users(1, "Sunil", "Sunil@gmail.com", "1234");

		when(ur.save(expectedUser)).thenReturn(expectedUser);

		Users actualUser = userService.registerUser(expectedUser);

		assertNotNull(actualUser);

		assertEquals(expectedUser, actualUser);

		 // Log statements for debugging
        log.info("Expected User: ", expectedUser);
        log.info("Actual User: ", actualUser);
        
		verify(ur, times(1)).save(expectedUser);

	}

	@Test
	public void getUserById() {
		int userId = 1;
		Users expectedUser = new Users(1, "Sunil", "sunil01@gmail.com", "1234");

		when(ur.findById(userId)).thenReturn(java.util.Optional.of(expectedUser));

		Users actualUser = userService.getUserById(userId);

		assertNotNull(actualUser);
		assertEquals(expectedUser, actualUser);

		 // Log statements for debugging
        log.info("Expected User by ID: ", userId, expectedUser);
        log.info("Actual User by ID: ", userId, actualUser);
        
		verify(ur, times(1)).findById(userId);
	}

	@Test
	public void getUserByEmail() {
		String email = "sunil@02gmail.com";
		Users expectedUser = new Users(1, "Sunil", email, "1234");

		when(ur.findByEmail(email)).thenReturn(java.util.Optional.of(expectedUser));

		Users actualUser = userService.getUserByEmail(email);

		assertNotNull(actualUser);
		assertEquals(expectedUser, actualUser);

		log.info("Expected User by Email: ", email, expectedUser);
        log.info("Actual User by Email: ", email, actualUser);

        
		verify(ur, times(1)).findByEmail(email);
	}
}
