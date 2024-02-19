package com.Xindus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xindus.exception.NotFoundException;
import com.Xindus.model.Users;
import com.Xindus.model.WishLists;
import com.Xindus.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public Users registerUser(Users user) {
		log.info("Registering a new user: ", user.getUserName());

		// Creating a new wishlist for the user
		WishLists wishList = new WishLists();
		user.setWishList(wishList);
		// Saving the user to the database
		Users savedUser = userRepo.save(user);

		log.info("User registered successfully. User ID: ", savedUser.getUserId());
		return savedUser;
	}

//	@Override
//	public Users getUserById(Integer userId) {
//		log.info("Fetching user with ID: ", userId);
//
//		// Fetching user from the database by ID
//		Users user = userRepo.findById(userId)
//				.orElseThrow(() -> new NotFoundException("Users not found with given userId " + userId));
//
//		log.info("User fetched successfully. User ID: ", userId);
//		return user;
//	}

	@Override
	public Users getUserByEmail(String name) {
		Users user = userRepo.findByEmail(name).orElseThrow(()-> new NotFoundException("User not found with given Email!"));
		return user;
	}

}
