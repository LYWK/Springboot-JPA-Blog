package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@Service   // 1. 트랜잭션 관리,    2. 서비스의 의미 
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
    
	@Transactional
	public void 회원가입(User user){
		
			userRepository.save(user);
	}
}
