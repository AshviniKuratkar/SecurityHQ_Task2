package com.app.task2.admin.services;

import java.util.List;
import java.util.Optional;

import com.app.task2.model.ERole;
import com.app.task2.model.Role;
import com.app.task2.model.User;
import com.app.task2.request.SignupRequest;


public interface AdminService 
{
	public User addUser(User user);//Done
	public User updateUserDetails(User user,Long id);//Done
	public List<User> getAllAdmin();//Done
	public List<User> getAllUser();//Done
	public User getUserbyId(Long id);//Done
	public List<User> findByFirstName();//Done
	public List<User> findByLastName();//Done
	public List<User> findByPincode();//Done
	public List<User> findByEmail();//Done
	public void deleteUser(Long id);////Done
	public List<User> deleteAdmin();//Done
	public List<User> sortUserByDoBirth();
	public List<User> sortUserByDateOfJoining();
	public List<User> getAllAdminRole(ERole name,SignupRequest signUpRequest);
	public User lockUser(User user,Long id);
	public User unLockUser(User user,Long id);
	public User softDeleteUser(User user,Long id);
	public List<User> findByAnyField();
	public List<User> findByDateOfBirth();
	

}
