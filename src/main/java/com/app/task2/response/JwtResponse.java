package com.app.task2.response;

import java.sql.Date;
import java.util.List;

public class JwtResponse 
{
	  private String token;
	  private String type = "Bearer";
	  private Long id;
	  private String firstName;
		
	  private String lastName;
	  
	  private String city;
	  
	  private long pinCode;
	
	  private Date dateOfBirth;
	
	  private Date dateOfJoining;
	  private String username;
	  private String email;
	  private List<String> roles;
	  

	  public String getAccessToken() {
	    return token;
	  }

	  public JwtResponse(String accessToken, Long id, String firstName, String lastName, String city, long pinCode,
		Date dateOfBirth, Date dateOfJoining, String username, String email, List<String> roles) {
	this.token = accessToken;
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.city = city;
	this.pinCode = pinCode;
	this.dateOfBirth = dateOfBirth;
	this.dateOfJoining = dateOfJoining;
	this.username = username;
	this.email = email;
	this.roles = roles;
}

	public void setAccessToken(String accessToken) {
	    this.token = accessToken;
	  }


	  public Long getId() {
	    return id;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	  public String getEmail() {
	    return email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }

	  public String getUsername() {
	    return username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
	  }

	  public List<String> getRoles() {
	    return roles;
	  }


public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	  
	  
	}
