package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@Autowired
//	private HttpSession session;
	
	@PostMapping("/auth/joinProc")//@RequestBody- json 형식의 데이터를 받을때ㅣ..
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨.");
		//실제로 DB에 insert를 하고 아래에서 return
		
		
		userService.회원가입(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);// java object 를 json으로 변환해서 리턴 (jackson)
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.회원수정(user);
		//여기서 트랜잭션이 종료됨.db에 변경된값 적용
		//세션에 변경된 값이 적용되지는 않은 상태
		//세션값 변경 - spring security session 의 개념 숙지
		//Authentication 객체를 만들어서 세션에 넣기 위한 일련의 과정 필요(x)
		Authentication authentication = 
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
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
