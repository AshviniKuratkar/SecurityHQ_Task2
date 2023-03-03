package com.app.task2.request;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.app.task2.model.Role;

public class SignupRequest {
	  
	 @NotBlank
	  @Size(max = 50)
	  private String firstName;
		
	  @NotBlank
	  @Size(max = 50)
	  private String lastName;
	  
	  @NotBlank
	  @Size(max = 50)
	  private String city;
	  
	  //@NotBlank
	  @NotNull
	 // @Range(min=5,max = 10)
	  private long pinCode;
	  
	  @NotNull
	  //@NotBlank
	  private Date dateOfBirth;
		
	  @NotNull
	  //@NotBlank
	  private Date dateOfJoining;

	
	  @NotBlank
	  @Size( min=3,max = 20)
	  private String username;

	  @NotBlank
	  @Size(max = 50)
	  @Email
	  private String email;

	  private Set<String> role;

	  @NotBlank
	  @Size(min=6,max = 40)
	  private String password;

	  
	  private boolean active;

	  public String getUsername() {
	    return username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
	  }

	  public String getEmail() {
	    return email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }

	  public String getPassword() {
	    return password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }

	  public Set<String> getRole() {
	    return this.role;
	  }

	  public void setRole(Set<String> role) {
	    this.role = role;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	  
	  
	}
