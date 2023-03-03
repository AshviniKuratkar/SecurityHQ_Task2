package com.app.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.task2.admin.services.UserService;
import com.app.task2.model.User;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/")
public class UserController 
{
	@Autowired
	private UserService service;

	
	public UserController() {
		super();
		System.out.println("In UserController");
	}
	
	@GetMapping("/userGetById/{id}")
	@PreAuthorize("@userSecurity.hasUserId(authentication,#id)")
	public ResponseEntity<?> getSingleUser(@PathVariable Long id,Authentication authentication)
	{
		System.out.println("Id found"+service.getUserById(id).getId());
		return new ResponseEntity<>(service.getUserById(id),HttpStatus.OK);
	}
	
	@PutMapping("/editUserDetails/{id}")
	@PreAuthorize("@userSecurity.hasUserId(authentication,#id)" )
	public ResponseEntity<?> editUserdetails(@RequestBody User user,@PathVariable Long id)
	{
		System.out.println(user);
		System.out.println("Update Id :"+service.updateUserDetails(user, id).getId());
		return new ResponseEntity<>(service.updateUserDetails(user, id),HttpStatus.OK);
	}
	
	
	@GetMapping("/viewProfile/{id}")
	@PreAuthorize("@userSecurity.hasUserId(authentication,#id)")
	public ResponseEntity<?> viewUserProfile(User user,@PathVariable Long id)
	{
		System.out.println("Your profile:"+service.viewProfile(user, id).getId());
		service.viewProfile(user,id);
		return new ResponseEntity<>(service.viewProfile(user, id),HttpStatus.OK);	
	}
	
	@GetMapping("/users/search")
	@PostAuthorize("returnObject.body.userName==authenticated.user")
	public ResponseEntity<User> userDetails(Authentication authentication, @RequestParam("uname") String cName) throws Exception {
		System.out.println(authentication.getName().toString());
		User User=service.findByUserName(cName);
		if(User==null) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		return ResponseEntity.ok().body(User);
	}
}
