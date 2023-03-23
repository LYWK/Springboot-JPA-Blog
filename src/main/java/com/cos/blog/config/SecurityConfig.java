package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // 필터를 걸다. 필터 추가 
@EnableGlobalMethodSecurity(prePostEnabled = true ) // 특정 주소 접근시 권한 인증을 미리 체크  
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		
	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			 http
			 	.authorizeRequests()
			 		.antMatchers("/auth/**")
			 		.permitAll()
			 		.anyRequest()
			 		.authenticated()
			 	.and()
			 		.formLogin()
			 		.loginPage("/auth/loginForm");
		}
}
