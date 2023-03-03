package com.app.task2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.app.task2.respository.UserRepository;

@Component("userSecurity")
public class UserSecurity {
	
	@Autowired
	private UserRepository userRepo;
	
	public boolean hasUserId(Authentication authentication, Long userId) {
		
		Long userID=userRepo.findByUsername(authentication.getName()).getId();
//		System.out.println(userId+"  "+userID);
            if(userID==userId)
            	return true;
            
            return false;
       
    }
}
