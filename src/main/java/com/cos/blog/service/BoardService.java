package com.cos.blog.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;
import com.sun.xml.txw2.IllegalAnnotationException;


@Service   // 1. 트랜잭션 관리,    2. 서비스의 의미 
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	@Transactional(readOnly = true)
	public Page<Board>  글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{return new IllegalArgumentException("상세보기실패:아이디를 찾을수 없ㄷ.");
						});
	}
	
	@Transactional
	public void 글삭제(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("상세보기실패:아이디를 찾을수 없ㄷ.");
				});
	
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수 종료 시(service가 종료될때) 트랜잭션이 종료됨 이때 
		//  board가 수정됐기때문에 더티체킹 일어남 - 자동 업데이트가 일어남,DB쪽으로 플러싱
	}
	
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto){
	    
		User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
			return new IllegalArgumentException("user찾기 실패");
		});
		
		Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
	    	return new IllegalArgumentException("댓글쓰기실패");
	    });
		
		Reply reply = Reply.builder()
				.user(user)
				.board(board)
				.content(replySaveRequestDto.getContent())
				.build();
		
		replyRepository.save(reply);
	}
	

}
