package com.app.task2.admin.services;

import com.app.task2.model.User;

public interface UserService
{
	//public Long registerUser(User user);
	public User findByUserName(String userName);
	public User updateUserDetails(User user,Long id);
	public User getUserById(Long id);
	public User viewProfile(User user,Long id);
}
