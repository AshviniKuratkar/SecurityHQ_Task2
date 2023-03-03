package com.app.task2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController 
{
	public TestController() {
		System.out.println("Inside TestController");
	}
	
	  @GetMapping("/all")
	  public String allAccess() {
	    return "Public Content.";
	  }
	
	//The @PreAuthorize annotation checks the given expression before entering the method, 
	//whereas the @PostAuthorize annotation verifies it after the execution of the method and could alter the result.

	  @GetMapping("/user")
	  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	  public String userAccess() {
	    return "User Content.";
	  }
	
	  @GetMapping("/mod")
	  @PreAuthorize("hasRole('MODERATOR')")
	  public String moderatorAccess() {
	    return "Moderator Board.";
	  }
	
	  @GetMapping("/admin")
	  @PreAuthorize("hasRole('ADMIN')")
	  public String adminAccess() {
	    return "Admin Board.";
	  }
}
