package com.project.Image.Entity;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;




@Entity
@Table(name = "Image_Data")
public class ImageData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long ImageID;
	String ImageName;
	String ImageType;
	String ImageUrl;
	String ImageHash;
    
	public ImageData() {
		super();
	}
	
	public Long getImageID() {
		return ImageID;
	}
	public void setImageID(Long imageID) {
		ImageID = imageID;
	}
	public String getImageName() {
		return ImageName;
	}
	public void setImageName(String imageName) {
		ImageName = imageName;
	}
	public String getImageType() {
		return ImageType;
	}
	public void setImageType(String imageType) {
		ImageType = imageType;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	public String getImageHash() {
		return ImageHash;
	}

	public void setImageHash(String imageHash) {
		ImageHash = imageHash;
	}

	@Override
	public String toString() {
		return "ImageData [ImageID=" + ImageID + ", ImageName=" + ImageName + ", ImageType=" + ImageType + ", ImageUrl="
				+ ImageUrl + ", ImageHash=" + ImageHash + "]";
	}
	
	
	
}
