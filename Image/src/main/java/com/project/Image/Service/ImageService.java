package com.project.Image.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.project.Image.Entity.ImageData;
import com.project.Image.Entity.User;
import com.project.Image.Repository.ImageRepository;
import com.project.Image.Repository.UserRepository;
import org.json.JSONObject;

@Service
public class ImageService {
	
	    
	    @Autowired
	    private ImageRepository imageRepository;
	    
	    @Autowired
	    private UserRepository userRepository;

	    public ImageService() {
	       	    }

	    private static final Logger log = LoggerFactory.getLogger(ImageService.class);
	    
	    public void uploadImage(MultipartFile file, String username) throws IOException, DataAccessException {
	      	        	
	        	log.info("Upload Image Request received from user "+username);
	        	
	        	ResponseEntity<JSONObject> json = uploadImageToImgur(file, username);
		    	    String link = json.getBody().getJSONObject("data").getString("link");    	    
	    	    String imageHash = json.getBody().getJSONObject("data").getString("deletehash");
	        	
	        	User user = userRepository.findByUserName(username);

	            ImageData imageData = new ImageData();
	            imageData.setImageName(file.getOriginalFilename());
	            imageData.setImageType(file.getContentType());
	            imageData.setImageUrl(link);
	            imageData.setImageHash(imageHash);
	            imageRepository.save(imageData);
	            List<ImageData> imageList = new ArrayList(2);
	            imageList.add(imageData);
	            user.setImageData(imageList);
	            userRepository.save(user);
           
	            
	        
	    }
	    
	    public ResponseEntity<JSONObject> uploadImageToImgur(MultipartFile file, String username) throws IOException {
	    	
	    	log.info("Image upload to Imgur started by user :"+username);
	    		String clientId = "56b45c33a0e8480";

	    		
	    		RestTemplate restTemplate = new RestTemplate();

	    		
	    	    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
	    	    body.add("image", file.getResource());
	    		

	    		HttpHeaders headers = new HttpHeaders();
	    		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	    		headers.set("Authorization","Client-ID " + clientId);
	    		
	    		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

	    		ResponseEntity<String> response = restTemplate.exchange("https://api.imgur.com/3/image", HttpMethod.POST, requestEntity, String.class);
	    		JSONObject json = new JSONObject(response.getBody());
	    	    
	    	    return new ResponseEntity<>(json, HttpStatus.OK);

	      

    	
	    }
	    
	   
	    
	    public ResponseEntity<String> deleteImage(String imageHash) throws IOException, DataAccessException {
	    	
	    	log.info("Image Deletion started for imagehash "+imageHash);
    		String clientId = "56b45c33a0e8480";

    		
    		RestTemplate restTemplate = new RestTemplate();
		
			HttpHeaders headers = new HttpHeaders();
    	    headers.setContentType(MediaType.APPLICATION_JSON);
    	    headers.set("Authorization", "Client-ID " + clientId);

    	    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

    	    ResponseEntity<String> response = restTemplate.exchange("https://api.imgur.com/3/image/" + imageHash, HttpMethod.DELETE, entity, String.class);

    	    if (response.getStatusCode().is2xxSuccessful()) {
    	        ImageData imageData = imageRepository.findImagebyHash(imageHash);
    	        imageRepository.delete(imageData);
    	        return new ResponseEntity<>("Image deleted successfully", HttpStatus.OK);  
    	        
    	     
    	    } else {
    	    	return new ResponseEntity<>("Image deletion Failed", HttpStatus.BAD_REQUEST);    
    	    }

    	         
	
    }
	    
	}


