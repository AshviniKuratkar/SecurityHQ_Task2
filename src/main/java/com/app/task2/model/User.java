package com.app.task2.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.authentication.AuthenticationProvider;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@SQLDelete(sql = "UPDATE users SET account_deleted=true WHERE id=?")
@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedProductFilter", condition = "deleted = :isDeleted")
public class User implements Comparable<User> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotBlank
	@Size(max = 50)
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@NotBlank
	@Size(max = 50)
	@Column(name = "city", nullable = false)
	private String city;

	@NotNull
	// @NotBlank
	// @Range(min=5,max = 10)
	@Column(name = "pincode")
	private long pinCode;

	@NotNull
	@Column(name = "date_of_Birth")
	private Date dateOfBirth;

	@NotNull
	@Column(name = "date_of_Joining")
	private Date dateOfJoining;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@Column(name = "active_account")
	private boolean isActive;

	@Column(name = "account_deleted")
	private boolean isDeleted = Boolean.FALSE;

	public User() {
		System.out.println("In User ctor");

	}

	public User(@NotBlank @Size(max = 50) String firstName, @NotBlank @Size(max = 50) String lastName,
			@NotBlank @Size(max = 50) String city, @NotNull long pinCode, @NotNull Date dateOfBirth,
			@NotNull Date dateOfJoining, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, Set<Role> roles,
			boolean isActive) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.pinCode = pinCode;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.isActive = isActive;

	}

	public User(Long id, @NotBlank @Size(max = 50) String firstName, @NotBlank @Size(max = 50) String lastName,
			@NotBlank @Size(max = 50) String city, @NotNull long pinCode, @NotNull Date dateOfBirth,
			@NotNull Date dateOfJoining, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, Set<Role> roles,
			boolean isActive, boolean isDeleted) {
		super();
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
		this.roles = roles;
		this.isActive = isActive;
		this.isDeleted = isDeleted;
	}

	public User(@NotBlank @Size(max = 50) String firstName, @NotBlank @Size(max = 50) String lastName,
			@NotBlank @Size(max = 50) String city, @NotNull long pinCode, @NotNull Date dateOfBirth,
			@NotNull Date dateOfJoining, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, Set<Role> roles, boolean isActive) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.pinCode = pinCode;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.isActive = isActive;
	}

////Create Account ctor
	public User(@NotBlank @Size(max = 50) String firstName, @NotBlank @Size(max = 50) String lastName,
			@NotBlank @Size(max = 50) String city, @NotBlank @Size(max = 6) long pinCode, @NotBlank Date dateOfBirth,
			@NotBlank Date dateOfJoining, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.pinCode = pinCode;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city
				+ ", pinCode=" + pinCode + ", dateOfBirth=" + dateOfBirth + ", dateOfJoining=" + dateOfJoining
				+ ", username=" + username + ", email=" + email + ", password=" + password + ", roles=" + roles
				+ ", isActive=" + isActive + ", isDeleted=" + isDeleted + "]";
	}
 
	@Override
	public int compareTo(User o) {

		return o.dateOfJoining.compareTo(this.dateOfJoining);
	}

}
