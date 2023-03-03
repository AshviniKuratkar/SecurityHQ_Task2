package com.app.task2.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.task2.model.User;
import com.app.task2.respository.UserRepository;

//Spring Security will load User details to perform authentication & authorization. So it has UserDetailsService
//interface that we need to implement

//UserDetailsService :Core interface which loads user-specific data. 
///It is used throughout the framework as a user DAO and is the strategy used by the DaoAuthenticationProvider. 
//The interface requires only one read-only method, which simplifies support for newdata-access strategies.


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

	  @Override
	  @Transactional
	  public UserDetails loadUserByUsername(String username)
	  {
			  User user = userRepository.findByUsername(username);
			       // .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
			  if(user != null && user.isActive() && !user.isDeleted()) {//here you can check that
	              return UserDetailsImpl.build(user);
	          } 
			  else {
              throw new UsernameNotFoundException("username not found");
          }
	  }
  
  
}

