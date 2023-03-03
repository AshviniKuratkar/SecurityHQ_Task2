package com.app.task2.admin.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.task2.admin.services.AdminService;
import com.app.task2.exception.ExceptionMessage;
import com.app.task2.exception.ResourceNotFoundException;
import com.app.task2.model.ERole;
import com.app.task2.model.Role;
import com.app.task2.model.SortDates;
import com.app.task2.model.User;
import com.app.task2.request.SignupRequest;
import com.app.task2.response.MessageResponse;
import com.app.task2.respository.RoleRepository;
import com.app.task2.respository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public AdminServiceImpl() {
		super();
		System.out.println("In AdminService");
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		try {
		if (userRepo.existsByUsername(user.getUsername())) {
		      System.out.println("Error: Username is already taken!");
		      throw new ExceptionMessage("Error: Username is already taken!\"");
		    }
		else if (userRepo.existsByEmail(user.getEmail())) {
		      System.out.println("Error: Email is already in use!");
		      throw new ExceptionMessage("Error: Email is already in use!");
		    }
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		User existingUser=new User(
				user.getFirstName(),
				user.getLastName(),
				user.getCity(),
				user.getPinCode(),
				user.getDateOfBirth(),
				user.getDateOfJoining(),
				user.getUsername(),
				user.getEmail(),
				encoder.encode(user.getPassword()),
				user.getRoles(),
				user.isActive());
		return this.userRepo.save(existingUser);

	}

	@Override
	public User updateUserDetails(User user, Long id) {
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
	public List<User> getAllAdmin() {
		 
		List<User> existingUser = this.userRepo.findAll();
		List<User> res = new ArrayList<User>();
		for(User us: existingUser)
		{
			if(us.getRoles().equals(roleRepo.findByName(ERole.ROLE_ADMIN)))
			{
				res.add(us);
			}
			res.add(us);
		}
		
	return this.userRepo.saveAll(res);
	}

	@Override
	public List<User> getAllUser() 
	{
		List<User> existingUser = this.userRepo.findAll();
		List<User> res = new ArrayList<User>();
		for(User us: existingUser)
		{
			if(us.getRoles().equals(roleRepo.findByName(ERole.ROLE_USER)))
			{
				res.add(us);
			}
			res.add(us);
		}
		
	return this.userRepo.saveAll(res);
}

	
	@Override
	public User getUserbyId(Long id) {
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
		existingUser.getPassword();
		return this.userRepo.save(existingUser);
	}

	@Override
	public List<User> findByFirstName() {
		// TODO Auto-generated method stub
		return this.userRepo.findAll();
	}

	@Override
	public List<User> findByLastName()
	{
		// TODO Auto-generated method stub
		return this.userRepo.findAll();
	}

	@Override
	public List<User> findByPincode() {
		// TODO Auto-generated method stub
		return this.userRepo.findAll();
		}

	@Override
	public List<User> findByEmail() {
		// TODO Auto-generated method stub
		return this.userRepo.findAll();
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		this.userRepo.deleteById(id);
	}

	@Override
	public List<User> deleteAdmin() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}
	
	@Override
	public List<User> sortUserByDoBirth() {
		// TODO Auto-generated method stub
		List<User> list=userRepo.findAll().stream()
				.sorted(Comparator.comparing(User::getDateOfBirth).reversed())
				.collect(Collectors.toList());
				return list;
	}

	@Override
	public List<User> sortUserByDateOfJoining() {
//		List<User> list=userRepo.findAll();
//		Collections.sort(list);
//		return list;
		List<User> list=userRepo.findAll().stream()
				.sorted(Comparator.comparing(User::getDateOfJoining).reversed())
				.collect(Collectors.toList());
				return list;
	}

	@Override
	public List<User> getAllAdminRole(ERole name,SignupRequest signUpRequest) {

		return this.userRepo.findAll();
	}

	@Override
	public User lockUser(User user,Long id) 
	{
		System.out.println(userRepo.getById(id));
		User existingUser = this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User Not Found with Id :"+id));
		existingUser.setActive(false);
		return this.userRepo.save(existingUser);

	}

	@Override
	public User unLockUser(User user, Long id) {
		// TODO Auto-generated method stub
		System.out.println(userRepo.getById(id));
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
		existingUser.setPassword(user.getPassword());
		existingUser.setActive(true);
		return this.userRepo.save(existingUser);

	}

@Override
public User softDeleteUser(User user, Long id) {
	// TODO Auto-generated method stub
	User existingUser= this.userRepo.findById(id)
			.orElseThrow(()->new ResourceNotFoundException("User Id not found with Id: "+id));
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
	existingUser.setPassword(user.getPassword());
	existingUser.setActive(true);
	existingUser.setDeleted(true);
	return this.userRepo.save(existingUser);

}

		@Override
		public List<User> findByAnyField() {
			// TODO Auto-generated method stub
			return userRepo.findAll();
		}
		@Override
		public List<User> findByDateOfBirth() {
			// TODO Auto-generated method stub
			return userRepo.findAll();
		}
}
