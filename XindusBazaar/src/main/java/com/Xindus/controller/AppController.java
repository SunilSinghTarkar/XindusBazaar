package com.Xindus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Xindus.model.Items;
import com.Xindus.model.Users;
import com.Xindus.service.ItemService;
import com.Xindus.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AppController {
	@Autowired
	private UserService userService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Method for User Registration.
	@PostMapping("/registers")
	public ResponseEntity<Users> registerUser(@Valid @RequestBody Users user) {
		log.info("Inside registerUser method of AppController.");
		log.info("Received registration request for user: ", user.getUserName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Users savedUser = userService.registerUser(user);
		log.info("User registered successfully. User ID: ", savedUser.getUserId());
		return new ResponseEntity<Users>(savedUser, HttpStatus.CREATED);
	}

	// Method for Fetching User with userId.
	@GetMapping("/users/{userId}")
	public ResponseEntity<Users> getUser(@PathVariable Integer userId) {
		log.info("Inside getUser method of AppController.");
		log.info("Received request to fetch user with ID: ", userId);
		Users user = userService.getUserById(userId);
		log.info("User retrieved successfully. User ID: ", userId);
		return new ResponseEntity<Users>(user, HttpStatus.ACCEPTED);
	}

	// Method for Creating Item.
	@PostMapping("/items")
	public ResponseEntity<Items> createItem(@Valid @RequestBody Items item) {
		log.info("Inside createItem method of AppController.");
		log.info("Received request to create item: ", item.getItemName());

		Items savedItem = itemService.createItem(item);
		log.info("Item created successfully. Item ID: ", savedItem.getItemId());
		return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
	}

	// Method for fetching Item with itemId.
	@GetMapping("/items/{itemId}")
	public ResponseEntity<Items> getItem(@PathVariable Integer itemId) {
		log.info("Inside getItem method of AppController.");
		log.info("Received request to fetch item with ID: ", itemId);

		Items item = itemService.getItemById(itemId);
		log.info("Item retrieved successfully. Item ID: ", itemId);

		return new ResponseEntity<Items>(item, HttpStatus.ACCEPTED);
	}

	// Method for fetching Item List.
	@GetMapping("/items")
	public ResponseEntity<List<Items>> getItemList() {
		log.info("Inside getItemList method of AppController.");
		log.info("Received request to fetch item list.");

		List<Items> itemList = itemService.getItemList();
		log.info("ItemList retrieved successfully.");

		return new ResponseEntity<>(itemList, HttpStatus.ACCEPTED);
	}

	@GetMapping("/signIn")
	public ResponseEntity<Users> getCustomerByEmail(Authentication auth) {

		System.out.println(auth);

		Users user = userService.getUserByEmail(auth.getName());

		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}

}
