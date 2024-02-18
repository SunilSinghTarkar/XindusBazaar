package com.Xindus.service;

import com.Xindus.model.WishLists;

public interface WishListService {
	public WishLists getWishList(Integer wishListId);

	public WishLists addToWishList(Integer wishListId, Integer itemId);

	public WishLists removeFromWishList(Integer wishListId, Integer itemId);
}
