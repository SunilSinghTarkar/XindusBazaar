package com.Xindus.service;

import com.Xindus.model.Items;
import com.Xindus.model.WishLists;

public interface WishListService {
	public WishLists getWishList();

	public WishLists addToWishList(Items item);

	public WishLists removeFromWishList(Integer itemId);
}
