package com.project.Image.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.Image.Entity.ImageData;


@Repository
@Transactional
public interface ImageRepository extends JpaRepository <ImageData, Long> {

	ImageData save(ImageData imageData);
	
	@Query(value="SELECT * FROM Image_Data where Image_Hash = :ImageHash", nativeQuery = true)
	ImageData findImagebyHash(@Param("ImageHash") String ImageHash);

    @Modifying	
	@Query(value="DELETE FROM ImageData where ImageHash = :imageHash", nativeQuery = true)
	int deleteImageByHash(@Param("imageHash") String imageHash);
}
