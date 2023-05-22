<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="text" class="form-control" name="username" placeholder="Enter username" id="username">
		</div>		
		
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" name="password" placeholder="Enter password" id="password">
		</div>
		
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=5278f2561aa2f2475242381d95b4f41f&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code">
			<img src="/image/kakao_login_button.png"/>
		</a>
	</form>
	
</div>

<%@ include file="../layout/footer.jsp"%>




