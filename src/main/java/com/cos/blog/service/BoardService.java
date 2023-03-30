package com.cos.blog.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;


@Service   // 1. 트랜잭션 관리,    2. 서비스의 의미 
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	
	@Transactional
	public void 글쓰기(Board board,User user){
			board.setCount(0);
			board.setUser(user);
			boardRepository.save(board);
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
	
	public List<Board>  글목록(){
		return boardRepository.findAll();
	}
}
