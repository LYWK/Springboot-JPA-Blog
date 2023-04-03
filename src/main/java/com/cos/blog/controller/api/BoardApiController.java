package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;

@RestController
public class BoardApiController {
    
	@Autowired
	private BoardService boardService;
	
	
	
//	@Autowired
//	private HttpSession session;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		System.out.println("BoardApiController : save 호출됨.");
		//실제로 DB에 insert를 하고 아래에서 return
		
		boardService.글쓰기(board, principalDetail.getUser());
				
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);// java object 를 json으로 변환해서 리턴 (jackson)
	}
	
	//기본적인 login 방식 - 브라우져에서 넘어온 user정보를 세션에 담아서 응답 데이터로 넘겨줌... 
	/*
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user,HttpSession session){
	 * System.out.println("UserApiController : login 호출됨.");
	 * 
	 * User principal = userService.로그인(user); System.out.println("principal:" +
	 * principal); if (principal != null) { session.setAttribute("principal",
	 * principal); return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); }else
	 * { return new ResponseDto<Integer>(HttpStatus.NOT_FOUND.value(), 1); } }
	 */
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.글삭제(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);	
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board){
		boardService.글수정(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
}
