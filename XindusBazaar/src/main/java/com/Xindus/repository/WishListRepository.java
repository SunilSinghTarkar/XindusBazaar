package com.Xindus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Xindus.model.WishLists;

public interface WishListRepository extends JpaRepository<WishLists, Integer> {

}
