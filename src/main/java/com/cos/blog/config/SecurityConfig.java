package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

@Configuration
@EnableWebSecurity // 필터를 걸다. 필터 추가 
@EnableGlobalMethodSecurity(prePostEnabled = true ) // 특정 주소 접근시 권한 인증을 미리 체크  
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean    //IOC 가됨. 
	public BCryptPasswordEncoder encodePWD() {
		return new  BCryptPasswordEncoder();
	}
	
	
	//로그인시 비번을 가로채서 해쉬방식을 알아내고 db에 저장된 비번과 비교함
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	// 인증이 필요한 페이지는 loginPage("/auth/loginForm")로 간다. 
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			 http
			 	.csrf().disable()  //csrf 토큰 비활성화 (테스트 걸어 두는게 좋음)
			 	.authorizeRequests()
			 		.antMatchers("/auth/**","/js/**","/css/**","/image/**","/","/dummy/**")
			 		.permitAll()
			 		.anyRequest()
			 		.authenticated()
			 	.and()                
			 		.formLogin()
			 		.loginPage("/auth/loginForm")
			 		.loginProcessingUrl("/auth/loginProc")  //spring security가 해당 주소로 요청하는 로그인을 가로채서 대신 로그인 해줌
			 		.defaultSuccessUrl("/");//로그인 성공시 넘어가는 화면
			 		
		}
}
