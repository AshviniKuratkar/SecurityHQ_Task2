package com.app.task2.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.task2.admin.services.AdminService;
import com.app.task2.model.ERole;
import com.app.task2.model.User;
import com.app.task2.response.MessageResponse;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/")
public class AdminController 
{
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private AdminService service;
	
	
	public AdminController() {
		super();
		System.out.println("In Admin Controller");
	}
	
	@PostMapping("/addUser")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addUser(@RequestBody User user)
	{
		
		System.out.println("User Added Successfully :"+service.addUser(user));
		return new ResponseEntity<>(new MessageResponse("User Added successfully!"),HttpStatus.OK);
		
		}
	
	@GetMapping("/getById/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getSingleUser(@Valid @PathVariable Long id)
	{
		System.out.println("Id found");
		return new ResponseEntity<>(service.getUserbyId(id),HttpStatus.OK);
	}

	
	@GetMapping("/getAllAdminRole")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllAdminsRole()
	{
		return new ResponseEntity<>(service.getAllAdmin(),HttpStatus.OK);
	}
	
	@GetMapping("/getAllUsers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllUsers()
	{
		System.out.println("List of Users");
		return new ResponseEntity<>(service.getAllUser(),HttpStatus.OK);
	}
	

	@GetMapping("/getAllModerator")
	@PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<?> getAllModerator(@PathVariable int role)
	{
		System.out.println("List of Moderator");
		return new ResponseEntity<>(service.getAllUser(),HttpStatus.OK);
	}
	
	@GetMapping("/getByFirstName/{firstName}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getByFirstName(@PathVariable String firstName)
	{
		System.out.println("List of Users");
		List<User> sort = service.findByFirstName();
		List<User> res = new ArrayList<User>();
		for(User us: sort)
		{
			if(us.getFirstName().equals(firstName))
			{
				res.add(us);
				System.out.println("User found with First Name :"+firstName);
			}
			else
				System.out.println("User Not found with First Name :"+firstName);
		}
		System.out.println("User List by FirstName :"+res);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/getByLastName/{lastName}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getByLastName(@PathVariable String lastName)
	{
		System.out.println("List of Users");
		List<User> sort = service.findByLastName();
		List<User> res = new ArrayList<User>();
		for(User us: sort)
		{
			if(us.getLastName().equals(lastName))
			{
				res.add(us);
				System.out.println("User found with Last Name :"+lastName);
			}
			else
				System.out.println("User Not found with Last Name :"+lastName);
		}
		System.out.println("User List by Last Name :"+res);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/getByEmail/{email}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getByEmail(@PathVariable String email)
	{
		System.out.println("List of Users");
		List<User> sort = service.findByEmail();
		List<User> res = new ArrayList<User>();
		for(User us: sort)
		{
			if(us.getEmail().equals(email))
			{
				res.add(us);
				System.out.println("User found with Email Id:"+email);
			}
			else
				System.out.println("User Not found with Email Id :"+email);
		}
		System.out.println("User List by Email Id :"+res);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/getBypinCode/{pinCode}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getByPinCode(@PathVariable Integer pinCode)
	{
		System.out.println("List of Users");
		List<User> sort = service.findByPincode();
		List<User> res = new ArrayList<User>();
		
		for(User us: sort)
		{
			
			Integer pathVCode=pinCode;
			System.out.println("PathCod :"+pathVCode);
			
			if(us.getPinCode()==pathVCode.longValue())
			{
				res.add(us);
				System.out.println("User found with PinCode :"+pinCode);
			}
			else
				System.out.println("User Not found with PinCode :"+pinCode);
		}
		System.out.println("User List by PinCode :"+res);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updateUserDetails/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user,@PathVariable Long id)
	{
		System.out.println(user);
		return new ResponseEntity<>(service.updateUserDetails(user, id),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/lockUser/{id}")
	public ResponseEntity<?> lockUserDetails(@Valid @RequestBody User user,@PathVariable Long id)
	{
		System.out.println(user);
		return new ResponseEntity<>(service.lockUser(user, id),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/unlockUser/{id}")
	public ResponseEntity<?> unLockUserDetails(@Valid @RequestBody User user,@PathVariable Long id)
	{
		System.out.println(user);
		return new ResponseEntity<>(service.unLockUser(user, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUserById/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUserById(@PathVariable Long id)
	{
		service.deleteUser(id);
		System.out.println("User deleted with Id : "+id);
		return new ResponseEntity<>("User Delete by ID :"+id,HttpStatus.OK);
	}
	
	@PostMapping("/softdelete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> softDeleteUser(@PathVariable long id,@Valid @RequestBody User user)
	{
		return new ResponseEntity<>(service.softDeleteUser(user, id),HttpStatus.OK);
	}
	
	@GetMapping("/sortUserbyDOB")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> sortUserByDoB()
	{
		System.out.println("User List sorted by Date of Birth");
		service.sortUserByDoBirth();
		return  new ResponseEntity<>(service.sortUserByDoBirth(),HttpStatus.OK);
	}
	
	@GetMapping("/sortUserByDOJ")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> sortUserByDoJ()
	{
		System.out.println("User List sorted by Date of Joining");
		System.out.println(service.sortUserByDateOfJoining());
		return new ResponseEntity<>(service.sortUserByDateOfJoining(),HttpStatus.OK);
	}
	@GetMapping("/getDetails/{field}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getDetailsUsingField(@PathVariable String field)
	{
		System.out.println("List of Users");
		
		List<User> sort = service.findByAnyField();
		List<User> res = new ArrayList<User>();
		for(User us: sort)
		{
			String pincode=String.valueOf(us.getPinCode());
			if(us.getFirstName().equals(field))
			{
				res.add(us);
				System.out.println("User found with FirstName :"+field);
			}
			else if(us.getLastName().equals(field))
			{
				res.add(us);
				System.out.println("User found with LastName :"+field);
			}
			else if(pincode.equals(field))
			{
				res.add(us);
				System.out.println("User found with Pincode :"+field);
			}
			else if(us.getCity().equals(field))
			{
				res.add(us);
				System.out.println("User found with City :"+field);
			}
			
		}
		System.out.println("User found with Field :"+res);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/getDetailUsingDoB/{date}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getDetailsUsingDOB(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") String date) 
	{
		//String dateOfBirth=date;
		List<User> sort = service.findByDateOfBirth();
		List<User> res = new ArrayList<User>();
		try {
		 SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd"); 
		 Date dOB=formatter1.parse(date);
		for(User us: sort)
		{
			
			if(us.getDateOfBirth().equals(dOB))
			{
				res.add(us);
				System.out.println("User found with FirstName :"+date);
			}
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 System.out.println("User found with DateofBirth :"+res);
			return new ResponseEntity<>(res,HttpStatus.OK);
	}
		
	@GetMapping("/getDetailUsingDoJ/{date}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getDetailsUsingDOJ(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") String date) 
	{
		//String dateOfBirth=date;
		List<User> sort = service.findByDateOfBirth();
		List<User> res = new ArrayList<User>();
		try {
		 SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd"); 
		 Date dOB=formatter1.parse(date);
		for(User us: sort)
		{
			
			if(us.getDateOfJoining().equals(dOB))
			{
				res.add(us);
				System.out.println("User found with FirstName :"+date);
			}
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 System.out.println("User found with DateofJoining :"+res);
			return new ResponseEntity<>(new MessageResponse("User found with DateofJoining :"), HttpStatus.OK);
	}

}
