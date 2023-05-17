package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@Service   // 1. 트랜잭션 관리,    2. 서비스의 의미 
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encode;
	
	@Transactional
	public void 회원가입(User user){
			String rawPassword = user.getPassword();
			String encPassword = encode.encode(rawPassword);
			user.setRole(RoleType.USER);
			user.setPassword(encPassword);
			userRepository.save(user);
	}
	
	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 1. 영속성컨텍스트공간에  User 오브젝트를 영속화시키고, 2. 영속화된 User 오브젝트를 수정
		// 1. select를 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서!!
		// 2. 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌. 
		User persistent = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기실패");
		});
		
		String rawPassword = user.getPassword();
		String encPassword = encode.encode(rawPassword);	
		persistent.setPassword(encPassword);
		persistent.setEmail(user.getEmail());
		//회원수정 함수 종료시 = 서비스 종료시  = 트랜잭션 종료= commit 이 자동으로 된다.
		// 영속화된 persistance 객체의 변화가 감지되면 더티ㅔ킹이 되어 update문을 날려줌. 
	}
	
	/*
	 * @Transactional(readOnly = true) /// select 할때 트랜잭션 시작, 해당 서비스 종료시에 트랜잭션 종료
	 * (정합성유지) public User 로그인(User user){
	 * 
	 * User principal = userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword());
	 * 
	 * return principal; }
	 */
	
}
