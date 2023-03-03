package com.app.task2.admin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.task2.admin.services.UserService;
import com.app.task2.exception.ResourceNotFoundException;
import com.app.task2.model.User;
import com.app.task2.respository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;
	
	public UserServiceImpl() {
		super();
		System.out.println("In UserServiceImpl");
	}
	@Override
	public User findByUserName(String userName) {
		
		User user=userRepo.findByUsername(userName);
		return user;
		
	}


	@Override
	public User updateUserDetails(User user,Long id) {
		// TODO Auto-generated method stub
		User existingUser = this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User Not Found with Id :"+id));
		existingUser.setId(user.getId());
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		existingUser.setCity(user.getCity());
		existingUser.setPinCode(user.getPinCode());
		existingUser.setRoles(user.getRoles());
		existingUser.setDateOfBirth(user.getDateOfBirth());
		existingUser.setDateOfJoining(user.getDateOfJoining());
		existingUser.setUsername(user.getUsername());
		existingUser.setPassword(encoder.encode(user.getPassword()));
		return this.userRepo.save(existingUser);
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		System.out.println(userRepo.getById(id));
		User existingUser = this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User Not Found with Id :"+id));
		existingUser.getId();
		existingUser.getFirstName();
		existingUser.getLastName();
		existingUser.getEmail();
		existingUser.getCity();
		existingUser.getPinCode();
		existingUser.getRoles();
		existingUser.getDateOfBirth();
		existingUser.getDateOfJoining();
		existingUser.getUsername();
		//existingUser.getPassword();
		return this.userRepo.save(existingUser);
	}

	
	
//	public User viewProfile(User user,Long id) {
//		// TODO Auto-generated method stub
//		System.out.println(userRepo.getById(id));
//		User existingUser = this.userRepo.findById(id)
//				.orElseThrow(()->new ResourceNotFoundException("User Not Found with Id :"+id));
//		existingUser.getId();
//		existingUser.getFirstName();
//		existingUser.getLastName();
//		existingUser.getEmail();
//		existingUser.getCity();
//		existingUser.getPinCode();
//		existingUser.getRoles();
//		existingUser.getDateOfBirth();
//		existingUser.getDateOfJoining();
//		existingUser.getUsername();
//		return this.userRepo.save(existingUser);
//	}
	
	public User viewProfile(User user, Long id) {
		// TODO Auto-generated method stub
		System.out.println(userRepo.getById(id));
		User existingUser = this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User Not Found with Id :"+id));
		existingUser.getId();
		existingUser.getFirstName();
		existingUser.getLastName();
		existingUser.getEmail();
		existingUser.getCity();
		existingUser.getPinCode();
		existingUser.getRoles();
		existingUser.getDateOfBirth();
		existingUser.getDateOfJoining();
		existingUser.getUsername();
		return this.userRepo.save(existingUser);
	}



}
