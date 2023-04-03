let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ // arrow 사용 이유 => this를 바인딩....
			this.save();
		});
		
		$("#btn-delete").on("click", ()=>{ 
			this.deleteById();
		});
		
		$("#btn-update").on("click", ()=>{ 
			this.update();
		});
	},
	
	save: function(){
		
		let data = {
			title : $("#title").val(),
			content : $("#content").val()			
		};
		
	
		$.ajax({
			//통신을 수행 = 회원가입 수행 요청 
			type: "post",
			url: "/api/board",
			data:JSON.stringify(data),  //json 문자열, http에서 데이터를 보낼때 http body 데이터 
			contentType:"application/json; charset=utf-8",     //body 데이터를 어떤 타입(MIME)으로 보낼지...
			dataType: "json"     // 현재는 default가 됨.
			// 응답이 왔을때  응답결과는 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript 오브젝트로 변경
		}).done(function(resp){
			alert("글쓰기가 완료됨");					
			location.href = "/";		
		}).fail(function(error){
			alert(JSON.stringify(error));	
		});   
	},
	
	deleteById: function(){
		let id = $("#id").text();
				
		$.ajax({
			type: "delete",
			url: "/api/board/"+id,		
			dataType: "json"     // 현재는 default가 됨.
			// 응답이 왔을때  응답결과는 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript 오브젝트로 변경
		}).done(function(resp){
			alert("삭제가 완료됨");					
			location.href = "/";		
		}).fail(function(error){
			alert(JSON.stringify(error));	
		}); 			
	},
	
	update: function(){
		let id = $("#id").val();
		let data = {
			title : $("#title").val(),
			content : $("#content").val()			
		};		
		$.ajax({
			//통신을 수행 = 회원가입 수행 요청 
			type: "put",
			url: "/api/board/"+id,
			data:JSON.stringify(data),  //json 문자열, http에서 데이터를 보낼때 http body 데이터 
			contentType:"application/json; charset=utf-8",     //body 데이터를 어떤 타입(MIME)으로 보낼지...
			dataType: "json"     // 현재는 default가 됨.
			// 응답이 왔을때  응답결과는 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript 오브젝트로 변경
		}).done(function(resp){
			alert("글쓰기가 완료됨");					
			location.href = "/";		
		}).fail(function(error){
			alert(JSON.stringify(error));	
		});   
	}
	
} //그냥 object

index.init();// 함수 호출하고 함수 바인딩