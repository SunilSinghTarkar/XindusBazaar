package com.Xindus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Xindus.model.Items;

public interface ItemsRepository extends JpaRepository<Items, Integer> {

}
