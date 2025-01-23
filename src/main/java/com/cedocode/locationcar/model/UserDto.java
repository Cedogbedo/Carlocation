package com.cedocode.locationcar.model;

public class UserDto {
	
	private String username;
    private String email;
    private String phonenumber;
    private String password;
    private String role;
    private String confirmPassword;

    public UserDto() {}
   
	public UserDto(String username, String email,String phonenumber, String password, String role, String confirmPassword) {
		super();
		this.username = username;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password = password;
		this.role = role;
		this.confirmPassword = confirmPassword;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
	
}
