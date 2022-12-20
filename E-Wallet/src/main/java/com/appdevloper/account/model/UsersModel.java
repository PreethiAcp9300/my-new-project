package com.appdevloper.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class UsersModel
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false , unique = true)
	@NotBlank(message = "Name is mandatory")
	private String username;
	
	@Column(unique = true , nullable = false)
	@Email
	@NotBlank(message = "Email is mandatory")
	private String email;
	
	@Column(nullable = false)
	@Size(min = 8, message =  "Min lenth is 8 char , Max 15 char")
	private String password;
	
	public UsersModel() {}

	public UsersModel(int id, String username, String email, String password) 
	{
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
}
