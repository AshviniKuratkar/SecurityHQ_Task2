package com.app.task2.security.services;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.app.task2.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserDetailsImpl implements UserDetails {
	  private static final long serialVersionUID = 1L;

	  private Long id;
	  private String firstName;
		
	  private String lastName;
	  
	  private String city;
	  
	  private long pinCode;
	
	  private Date dateOfBirth;
	
	  private Date dateOfJoining;
	  
	  private String username;

	  private String email;

	  @JsonIgnore
	  private String password;

	  private Collection<? extends GrantedAuthority> authorities;

	 private boolean active;


	public UserDetailsImpl(Long id, String firstName, String lastName,String city, long pinCode, Date dateOfBirth,
			Date dateOfJoining, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities,boolean active) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.pinCode = pinCode;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.active=active;
	}

	public static UserDetailsImpl build(User user)
	{
	    List<GrantedAuthority> authorities = user.getRoles().stream()
	        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
	        .collect(Collectors.toList());

	    return new UserDetailsImpl( user.getId(), 
	        user.getFirstName(),
	        user.getLastName(),
	        user.getCity(),
	        user.getPinCode(),
	        user.getDateOfBirth(),
	        user.getDateOfJoining(),
	        user.getUsername(), 
	        user.getEmail(),
	        user.getPassword(), 
	        authorities,user.isActive());
	 }

	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities;
	  }

	  public Long getId() {
	    return id;
	  }

	  public String getEmail() {
	    return email;
	  }

	  @Override
	  public String getPassword() {
	    return password;
	  }

	  @Override
	  public String getUsername() {
	    return username;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }

	  @Override
	  public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    UserDetailsImpl user = (UserDetailsImpl) o;
	    return Objects.equals(id, user.id);
	  }
	}
