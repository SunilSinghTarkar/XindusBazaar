package com.Xindus.service;

import com.Xindus.model.Users;

public interface UserService {
	public Users registerUser(Users user);

//	public Users getUserById(Integer userId);

	public Users getUserByEmail(String name);
}
