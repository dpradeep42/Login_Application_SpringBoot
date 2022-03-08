package com.pradeep.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	public Role() {
		System.out.println("Role.java - Constructor default");
	}
	
	public Role(String name) {
		super();
		System.out.println("Role.java - Constructor param");
		
		this.name = name;
	}

	public Long getId() {
		System.out.println("Role.java - getID");
		return id;
	}

	public void setId(Long id) {
		System.out.println("Role.java - setID");
		this.id = id;
	}

	public String getName() {
		System.out.println("Role.java - getName");
		return name;
	}

	public void setName(String name) {
		System.out.println("Role.java - setName");
		this.name = name;
	}
}