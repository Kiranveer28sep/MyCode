package com.project.Image.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.Image.DTO.UserDTO;
import com.project.Image.Entity.User;
import com.project.Image.Service.ImageService;
import com.project.Image.Service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	


	@Autowired
	private UserService userService;
	
	@Autowired
	private ImageService imageService;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

@PostMapping(value="/addUser")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
       try {
    	userService.registerUser(userDTO);
    	log.info("User Registered Successfully");
    	 return ResponseEntity.accepted().body("User Registered Successfully");

       }
    		catch(Exception e) {
    			log.info("User Registeration Failed "+e.getMessage());
    			return ResponseEntity.badRequest().body("User Registeration Failed");
    		}
	
    	
    }

@GetMapping(value="/viewUser")
public ResponseEntity<?> viewUser(@RequestParam("username")  String username) {
	User user = null;
   try {
	 user = userService.viewUser(username);
	 log.info("User and Image Search operation performed for user "+username);
	 return ResponseEntity.accepted().body(user);
   }
		catch(Exception e) {
			log.info("Invalid Request "+e.getMessage());
			return ResponseEntity.badRequest().body("Invalid Request");
		}


	
}
    
	
	  @PostMapping(value="/uploadImage") 
	  public ResponseEntity<String> uploadImage(@RequestParam("file")  MultipartFile file, @RequestParam("username")  String username) 
	  { 
		  try 
		  {   
			  imageService.uploadImage(file, username);
			  log.info("Image Uploaded Successfully by user : "+username);
			  return ResponseEntity.accepted().body("Image Uploaded Successfully");
	  
	  } catch (Exception e) { 
		  log.info("Image Upload Failed. Username "+username+" Error: "+e.getMessage());
			return ResponseEntity.badRequest().body("Image Upload Failed");
			}  }

	  @DeleteMapping(value="/deleteImage") 
	  public ResponseEntity<String> deleteImage( @RequestParam("imageHash")  String imageHash) 
	  { 
		  try 
		  {   
			  imageService.deleteImage(imageHash);
			  log.info("Image Deleted Successfully by for ImageHash : "+imageHash);
			  return ResponseEntity.accepted().body("Image Deleted Successfully");
	  
	  } catch (Exception e) { 
		  log.info("Image Delete Failed for ImageHash : "+imageHash +e.getMessage()+e.getStackTrace());
			return ResponseEntity.badRequest().body("Image Delete Failed for ImageHash : "+imageHash +e.getStackTrace());
			}  }
	 

}






