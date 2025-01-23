package com.cedocode.locationcar.model;

import jakarta.persistence.*;


@Entity
@Table (name = "user", uniqueConstraints = @UniqueConstraint(columnNames= "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String phonenumber;
    private String password;
    private String role;
    
  
    public User() {
		super();
	}
	
     
     
    public User(String username, String email,String phonenumber, String password, String role) {
		super();
		this.username = username;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password = password;
		this.role = role;
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
	
	public String getPhonenumber() {
			return phonenumber;
		}

	public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
    

    
}
