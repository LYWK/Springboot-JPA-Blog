let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ // arrow 사용 이유 => this를 바인딩....
			this.save();
		});
	},
	
	save: function(){
		alert("user의 save 호출 ");
		let data = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val()
		}
		console.log(data);
		$.ajax({}).done({}).fail({}); //ajax통신을 이용해서  3개의 파라미터를 json으로 변경하여 insert 요청
		//ajax 1_요청에 대한 응답을 html이 아닌 data(json)로 받기 위해...
		//     브라우져로 서버에 요청 ->  서버는 응답을 html로 함  보통은..... 
		//   html은 browser가 이해함.  1. 서버를 하나로 통일(웹용 앱용 따로 필요가 없음-데이터통신 하니까 ..)
		//2. 비동기 통신. ==>  ex)  
	},
} //그냥 object

index.init();// 함수 호출하고 함수 바인딩