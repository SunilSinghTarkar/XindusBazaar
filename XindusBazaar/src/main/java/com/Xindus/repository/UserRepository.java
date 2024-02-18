package com.Xindus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Xindus.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	public Optional<Users> findByEmail(String name);

}
