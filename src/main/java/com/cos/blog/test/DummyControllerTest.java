package com.cos.blog.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {
		
	 @PostMapping("/dummy/join")
	 public String join(String username, String password, String email) {
		 System.out.println("username:"+ username);
		 System.out.println("password:"+ password);
		 System.out.println("email:"+ email);
		 return "회원가입이 완료되었습니다.";
	 }
}
