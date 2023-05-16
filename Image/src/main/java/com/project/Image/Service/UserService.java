package com.project.Image.Service;

import java.util.Collections;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Image.DTO.UserDTO;
import com.project.Image.Entity.User;
import com.project.Image.Repository.UserRepository;

@Service
public class UserService {
	
	    private Keycloak keycloak = null;
	    
	    @Autowired
	    private UserRepository userRepository;

	    public UserService(Keycloak keycloak) {
	        this.keycloak = keycloak;
	    }


	    
	    public void registerUser(UserDTO userDTO) {
	        try {
//	        	UserRepresentation userRepresentation = new UserRepresentation();
//	        	userRepresentation.setUsername(userDTO.getUsername());
//	        	userRepresentation.setEmail(userDTO.getEmail());
//	        	userRepresentation.setFirstName(userDTO.getFirstName());
//	        	userRepresentation.setLastName(userDTO.getLastName());
//	        	userRepresentation.setEnabled(true);
//
//	        	CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
//	        	credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
//	        	credentialRepresentation.setValue(userDTO.getPassword());
//	        	credentialRepresentation.setTemporary(false);
//
//	        	userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
//ImageData
//	        	keycloak.realm("myrealm").users().create(userRepresentation);
	            
	            User user = saveUser(userDTO);
	            
	        } catch (Exception e) {
	            throw new RuntimeException("Error registering user", e);
	        }
	    }
	    
	    public User saveUser(UserDTO userDTO)  {
	    	User user = new User();
	    	
	    	user.setUsername(userDTO.getUsername());
	    	user.setEmail(userDTO.getEmail());
	    	user.setFirstName(userDTO.getFirstName());
	    	user.setLastName(userDTO.getLastName());
	    	user.setEnabled(true);
	    	user.setPassword(userDTO.getPassword());
	    	
	        return userRepository.save(user);
	    }

	    
	    public User viewUser(String username) {
	    
	    User user = userRepository.findByUserName(username);
		return user;
	    
	    }
	    
	}


