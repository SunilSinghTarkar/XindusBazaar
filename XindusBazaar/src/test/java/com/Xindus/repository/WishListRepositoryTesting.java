package com.Xindus.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.Xindus.model.Items;
import com.Xindus.model.Users;
import com.Xindus.model.WishLists;
import com.Xindus.repository.WishListRepository;
import com.Xindus.service.ItemService;
import com.Xindus.service.UserService;
import com.Xindus.service.WishListService;
import com.Xindus.service.WishListServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class WishListRepositoryTesting {

    @Mock
    private WishListRepository wishListRepo;

    @Mock
    private ItemService itemServiceMock;

    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private WishListService wishListService = new WishListServiceImpl();

    @Test
    void testGetWishList() {
        int wishListId = 1;
        log.info("Testing getWishList method with wishListId: {}", wishListId);

        Users user = new Users(1, "Sunil", "sunil@gmail.com", "1234");
        when(userServiceMock.getUserByEmail(any(String.class))).thenReturn(user);

        WishLists expectedWishList = new WishLists(wishListId, new ArrayList<>());
        when(wishListRepo.findById(wishListId)).thenReturn(Optional.of(expectedWishList));

        // Mock Authentication for testing
        Authentication auth = new UsernamePasswordAuthenticationToken("testuser", "testpassword");
        SecurityContextHolder.getContext().setAuthentication(auth);

        WishLists actualWishList = wishListService.getWishList();

        // Reset the authentication context after the test
        SecurityContextHolder.clearContext();

        // Assert
        assertNotNull(actualWishList);
        assertEquals(expectedWishList, actualWishList);
        log.info("getWishList test completed successfully.");
    }


    @Test
    void testAddToWishList() {
        int wishListId = 1;
        int itemId = 2;
        Items item = new Items(itemId, "Item02", "Books", 29.99);
        log.info("Testing addToWishList method with wishListId: {} and itemId: {}", wishListId, itemId);

        Users user = new Users(); // Create a mock user
        when(userServiceMock.getUserByEmail(any(String.class))).thenReturn(user);

        WishLists wishList = new WishLists(wishListId, new ArrayList<>());
        when(wishListRepo.findById(wishListId)).thenReturn(Optional.of(wishList));
        when(itemServiceMock.getItemById(itemId)).thenReturn(item);
        when(wishListRepo.save(any(WishLists.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Mock Authentication for testing
        Authentication auth = new UsernamePasswordAuthenticationToken("testuser", "testpassword");
        SecurityContextHolder.getContext().setAuthentication(auth);

        WishLists updatedWishList = wishListService.addToWishList(item); // Pass Items object instead of itemId

        // Reset the authentication context after the test
        SecurityContextHolder.clearContext();

        // Assert
        assertNotNull(updatedWishList);
        verify(wishListRepo, times(1)).findById(wishListId);
        verify(itemServiceMock, times(1)).getItemById(itemId);
        verify(wishListRepo, times(1)).save(any(WishLists.class));
        log.info("addToWishList test completed successfully.");
    }


    @Test
    void testRemoveFromWishList() {
        int wishListId = 1;
        int itemId = 2;
        Items item = new Items(itemId, "Item02", "Books", 29.99);
        List<Items> itemList = new ArrayList<>();
        itemList.add(item);
        WishLists wishList = new WishLists(wishListId, itemList);
        log.info("Testing removeFromWishList method with wishListId: {} and itemId: {}", wishListId, itemId);

        // Mock Authentication for testing
        Authentication auth = new UsernamePasswordAuthenticationToken("testuser", "testpassword");
        SecurityContextHolder.getContext().setAuthentication(auth);

        when(wishListRepo.findById(wishListId)).thenReturn(Optional.of(wishList));
        when(itemServiceMock.getItemById(itemId)).thenReturn(item);
        when(wishListRepo.save(any(WishLists.class))).thenAnswer(invocation -> invocation.getArgument(0));

        WishLists updatedWishList = wishListService.removeFromWishList(itemId);

        // Reset the authentication context after the test
        SecurityContextHolder.clearContext();

        // Assert
        assertNotNull(updatedWishList);
        verify(wishListRepo, times(1)).findById(wishListId);
        verify(itemServiceMock, times(1)).getItemById(itemId);
        verify(wishListRepo, times(1)).save(any(WishLists.class));
        log.info("removeFromWishList test completed successfully.");
    }
}