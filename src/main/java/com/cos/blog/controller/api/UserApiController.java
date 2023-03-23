package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
    
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private HttpSession session;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨.");
		//실제로 DB에 insert를 하고 아래에서 return
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		
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
	
}
