package com.app.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.task2.admin.services.RoleService;
import com.app.task2.model.Role;

@RestController
@RequestMapping("/home/")
public class HomeController
{
	@Autowired
	private RoleService service;
	
	public HomeController() {
		// TODO Auto-generated constructor stub
		System.out.println("In HomeController");
	}
	@GetMapping("/hello")
	public String hello()
	{
		return ("<h1>Hello Welcome</h1>");
	}
	
	@PostMapping("/addRole")
	public ResponseEntity<?> addRoles()
	{
		return new ResponseEntity<>(service.createRole(),HttpStatus.CREATED);
	}
	
	@PostMapping("/addNewRole")
	public ResponseEntity<?> addNewRoles(@RequestBody Role role)
	{
		return new ResponseEntity<>(service.addRole(role),HttpStatus.CREATED);
	}
	
}
