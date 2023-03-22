package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
		//JPA Naming query
	// select * from user where username =  ? and password = ?
	   User findbyUsernameAndPassword(String username, String password);
}
 