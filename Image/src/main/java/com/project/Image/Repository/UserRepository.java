package com.project.Image.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.project.Image.Entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

	User save(User user);
	
	//@Query(select * from users where username = :username);
	User findByUserName(String username);
}
