package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional(readOnly = true) /// select 할때 트랜잭션 시작, 해당 서비스 종료시에 트랜잭션 종료 (정합성유지)
	public User 로그인(User user){
		
	User principal = userRepository.findbyUsernameAndPassword(user.getUsername(), user.getPassword());
			
			return principal;
	}
	
}
