package com.project.Image.Entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;




@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long UserID;
	String userName;
	String FirstName;
	String LastName;
	String Email;
	Boolean Enabled;
	String Password;
	
	
	  @OneToMany(cascade = CascadeType.ALL)	  
	  @JoinColumn(name = "usr_fid", referencedColumnName = "userid")
	  List<ImageData> imageData = new ArrayList<>();
	 
	  

	public User() {
		super();
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String username) {
		userName = username;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Boolean getEnabled() {
		return Enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.Enabled = enabled;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}

	
	  public List<ImageData> getImageData() 
	  { return imageData; } 
	  public void setImageData(List<ImageData> imageData)
	  { this.imageData = imageData; }
	
	  @Override
	public String toString() {
		return "User [UserID=" + UserID + ", Username=" + userName + ", FirstName=" + FirstName + ", LastName="
				+ LastName + ", Email=" + Email + ", Enabled=" + Enabled + ", Password=" + Password + ", imageData="
				+ imageData + "]";
	}
	 
	
}
