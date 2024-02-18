package com.Xindus.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Xindus.model.Users;

@SpringBootTest
public class UserRepositoryTesting {
	
    @Autowired
	private UserRepository userRepo;
    
    
    @Test
    public void isUserExist() {
    	
    	Users user = new Users(1, "Sunil", "sunil03@gmail.com", "1234");
    	
    	userRepo.save(user);
    	
    	Users actualResult = userRepo.findByEmail(user.getEmail()).orElseThrow();
    	
    	assertNotNull(actualResult);
    	
    	userRepo.delete(actualResult);
    	
    }
    
    
    
    
}
