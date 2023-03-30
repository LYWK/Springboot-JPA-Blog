package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	
	Optional<User>  findByUsername(String username);
	
	
	//JPA Naming query
	// select * from user where username =  ? and password = ?
	  // User findByUsernameAndPassword(String username, String password);
}
 