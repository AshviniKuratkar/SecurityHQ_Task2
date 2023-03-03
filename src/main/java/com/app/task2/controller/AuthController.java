package com.app.task2.controller;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.task2.model.ERole;
import com.app.task2.model.Role;
import com.app.task2.model.User;
import com.app.task2.request.LoginRequest;
import com.app.task2.request.SignupRequest;
import com.app.task2.response.JwtResponse;
import com.app.task2.response.MessageResponse;
import com.app.task2.respository.RoleRepository;
import com.app.task2.respository.UserRepository;
import com.app.task2.security.jwt.JwtUtils;
import com.app.task2.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController 
{
	  @Autowired
	  private AuthenticationManager authenticationManager;
	
	  @Autowired
	  private UserRepository userRepository;
	
	  @Autowired
	  private RoleRepository roleRepository;
	
	  @Autowired
	  private PasswordEncoder encoder;
	
	  @Autowired
	  private JwtUtils jwtUtils;
	
	  public AuthController() {
		  System.out.println("Inside AuthController");
	}
	  
  
	  @PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
	  {
		 
	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal(); 
	 
	    List<String> roles = userDetails.getAuthorities().stream()
	        .map(item -> item.getAuthority())
	        .collect(Collectors.toList());
	
		    	return new ResponseEntity<>(new JwtResponse(jwt, 
                        userDetails.getId(), 
                        userDetails.getFirstName(),
                        userDetails.getLastName(),
                        userDetails.getCity(),
                        userDetails.getPinCode(),
                        userDetails.getDateOfBirth(),
                        userDetails.getDateOfJoining(),
                        userDetails.getUsername(), 
                        userDetails.getEmail(), 	              
                        roles),HttpStatus.OK);	    
	  }
	  
	  @PostMapping("/signupwithADMIN")
	  public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Username is already taken!"));
	    }
	
	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Email is already in use!"));
	    }
	
	    // Create new user's account
	    User user = new User(signUpRequest.getFirstName(),
	    		signUpRequest.getLastName(),
	    		signUpRequest.getCity(),
	    		signUpRequest.getPinCode(),
	    		signUpRequest.getDateOfBirth(),
	    		signUpRequest.getDateOfJoining(),
	    		signUpRequest.getUsername(), 
	               signUpRequest.getEmail(),
	              encoder.encode(signUpRequest.getPassword()));
	   
	    System.out.println(signUpRequest.getPassword());
	    user.setActive(true);
	    user.setDeleted(false);
	    Set<String> strRoles = signUpRequest.getRole();
	    System.out.println("strRoles :"+strRoles);
	    Set<Role> roles = new HashSet<>();
	    System.out.println("roles :"+roles);
	  
	    	Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
	    			.orElseThrow(() -> new RuntimeException("Error: Role ADMIN is not found."));
	    	roles.add(userRole);
	   
	    System.out.println("Added Roles"+roles.toString());
	    user.setRoles(roles);
	    
	    User newAdmin =userRepository.save(user);
	
	    //return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	    System.out.println("ADMIN registered successfully!" + newAdmin);
	    return new ResponseEntity<>(new MessageResponse("Admin registered successfully!"),HttpStatus.OK);
	  }
	  
	  @PostMapping("/signupwithUSER")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Username is already taken!"));
	    }
	
	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Email is already in use!"));
	    }
	
	    // Create new user's account
	    User user = new User(signUpRequest.getFirstName(),
	    		signUpRequest.getLastName(),
	    		signUpRequest.getCity(),
	    		signUpRequest.getPinCode(),
	    		signUpRequest.getDateOfBirth(),
	    		signUpRequest.getDateOfJoining(),
	    		signUpRequest.getUsername(), 
	               signUpRequest.getEmail(),
	              encoder.encode(signUpRequest.getPassword()));
	   
	    System.out.println(signUpRequest.getPassword());
	    user.setActive(true);
	    Set<Role> roles = new HashSet<>();
	    System.out.println("roles :"+roles);
	  
	    	Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	    			.orElseThrow(() -> new RuntimeException("Error: Role USER is not found."));
	    	roles.add(userRole);
	   
	    System.out.println("Added Roles"+roles.toString());
	    user.setRoles(roles);
	    
	    User newUser =userRepository.save(user);
	
	    //return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	    System.out.println("User registered successfully!" + newUser);
	    return new ResponseEntity<>(new MessageResponse("User registered successfully!"),HttpStatus.OK);
	  }
	  
	  @PostMapping("/signup")
	  public ResponseEntity<?> registerAdminUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Username is already taken!"));
	    }
	
	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Email is already in use!"));
	    }
	
	    // Create new user's account
	    User user = new User(signUpRequest.getFirstName(),
	    		signUpRequest.getLastName(),
	    		signUpRequest.getCity(),
	    		signUpRequest.getPinCode(),
	    		signUpRequest.getDateOfBirth(),
	    		signUpRequest.getDateOfJoining(),
	    		signUpRequest.getUsername(), 
	               signUpRequest.getEmail(),
	              encoder.encode(signUpRequest.getPassword()));
	   
	    System.out.println(signUpRequest.getPassword());
	    user.setActive(true);
	    Set<String> strRoles = signUpRequest.getRole();
	    Set<Role> roles = new HashSet<>();
	
	    if (strRoles == null) {
	      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	      roles.add(userRole);
	    } else {
	      strRoles.forEach(role -> {
	        switch (role) {
	        case "admin":
	          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(adminRole);
	          break;
	        case "mod":
	          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(modRole);
	          break;
	        default:
	          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(userRole);
	        }
	      });
	    }
	
	    user.setRoles(roles);
	    userRepository.save(user);
	
	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	  }
	  
	  
	  
	  
	  //@Autowired
	  //@Bean
	  
	  
}
