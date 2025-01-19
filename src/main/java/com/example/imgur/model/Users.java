package com.example.imgur.model;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Users {

    @Id  // Primary key annotation
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Automatically generate ID
	private Long id;
    
	private String username;
    
	private String password;
	public Users(String user, String pass) {
		this.username = user;
		this.password = pass;
	}
	public void setUsername(String user) {
		this.username = user;
		
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String encodePass) {
		this.password = encodePass;
		
	}
	public String getPassword() {
		return password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

   
}
