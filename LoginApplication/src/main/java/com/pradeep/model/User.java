package com.pradeep.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "userss_roles", joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

	private Collection<Role> roles;

	public User() {
		System.out.println("User.java - default constructor");
	}

	public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
		super();
		System.out.println("User.java - param constructor");
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		System.out.println("User.java - getId");
		return id;
	}

	public void setId(Long id) {
		System.out.println("User.java - setId");
		this.id = id;
	}

	public String getFirstName() {
		System.out.println("User.java - getFirstName");
		return firstName;
	}

	public void setFirstName(String firstName) {
		System.out.println("User.java - setFirstName");
		this.firstName = firstName;
	}

	public String getLastName() {
		System.out.println("User.java - getLastName");
		return lastName;
	}

	public void setLastName(String lastName) {
		System.out.println("User.java - setLastName");
		this.lastName = lastName;
	}

	public String getEmail() {
		System.out.println("User.java - getEmail");
		return email;
	}

	public void setEmail(String email) {
		System.out.println("User.java - setEmail");
		this.email = email;
	}

	public String getPassword() {
		System.out.println("User.java - getPassword");
		return password;
	}

	public void setPassword(String password) {
		System.out.println("User.java - setPassword");
		this.password = password;
	}

	public Collection<Role> getRoles() {
		System.out.println("User.java - getRoles");
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		System.out.println("User.java - setRoles");
		this.roles = roles;
	}

}