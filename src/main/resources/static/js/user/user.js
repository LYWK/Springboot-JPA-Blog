let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ // arrow 사용 이유 => this를 바인딩....
			this.save();
		});
		
		$("#btn-login").on("click", ()=>{ // arrow 사용 이유 => this를 바인딩....
			this.login();
		});
	},
	
	save: function(){
		alert("user의 save 호출 ");
		let data = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val()
		};
		
		console.log(data);  //javascript object
		
		// ajax호출시 default가 비동기 호출
		// ajax가 통신성공하고 나서 JSON으로 리턴해주고 나면 자동으로 js object 로 변화 해주는데....
		$.ajax({
			//통신을 수행 = 회원가입 수행 요청 
			type: "post",
			url: "/blog/api/user",
			data:JSON.stringify(data),  //json 문자열, http에서 데이터를 보낼때 http body 데이터 
			contentType:"application/json; charset=utf-8",     //body 데이터를 어떤 타입(MIME)으로 보낼지...
			dataType: "json"     // 현재는 default가 됨.
			// 응답이 왔을때  응답결과는 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript 오브젝트로 변경
		}).done(function(resp){
			alert("회원가입이 완료됨");
			//console.log(resp);		
			location.href = "/blog";
			//응답결과가 정상
		}).fail(function(error){
			alert(JSON.stringify(error));
			//응답 실패
		}); //ajax통신을 이용해서  3개의 파라미터를 json으로 변경하여 insert 요청
		//ajax 1_요청에 대한 응답을 html이 아닌 data(json)로 받기 위해...
		//     브라우져로 서버에 요청 ->  서버는 응답을 html로 함  보통은..... 
		//   html은 browser가 이해함.  1. 서버를 하나로 통일(웹용 앱용 따로 필요가 없음-데이터통신 하니까 ..)
		//2. 비동기 통신. ==>  ex)  
	},
	
	login: function(){
		alert("user의 save 호출 ");
		let data = {
			username : $("#username").val(),
			password : $("#password").val()
		};
		
		console.log(data);  //javascript object
		
		// ajax호출시 default가 비동기 호출
		// ajax가 통신성공하고 나서 JSON으로 리턴해주고 나면 자동으로 js object 로 변화 해주는데....
		$.ajax({
			//통신을 수행 = 회원가입 수행 요청 
			type: "post",
			url: "/blog/api/user/login",
			data:JSON.stringify(data),  //json 문자열, http에서 데이터를 보낼때 http body 데이터 
			contentType:"application/json; charset=utf-8",     //body 데이터를 어떤 타입(MIME)으로 보낼지...
			dataType: "json"     // 현재는 default가 됨.
			// 응답이 왔을때  응답결과는 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript 오브젝트로 변경
		}).done(function(resp){
			alert("로그인이 완료됨");
			//console.log(resp);		
			location.href = "/blog";
			//응답결과가 정상
		}).fail(function(error){
			alert(JSON.stringify(error));
			//응답 실패
		}); //ajax통신을 이용해서  3개의 파라미터를 json으로 변경하여 insert 요청
		//ajax 1_요청에 대한 응답을 html이 아닌 data(json)로 받기 위해...
		//     브라우져로 서버에 요청 ->  서버는 응답을 html로 함  보통은..... 
		//   html은 browser가 이해함.  1. 서버를 하나로 통일(웹용 앱용 따로 필요가 없음-데이터통신 하니까 ..)
		//2. 비동기 통신. ==>  ex)  
	}
} //그냥 object

index.init();// 함수 호출하고 함수 바인딩