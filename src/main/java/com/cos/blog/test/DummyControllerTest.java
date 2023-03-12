package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@RestController
public class DummyControllerTest {
		
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	//save 함수는 id를 전달하지 않으면 insert를 해주고, id를 전달할 시, id에 대한 데이터가 존재할시 update, 존재안할시에 insert 해줌. 
	@Transactional // 더티체킹 - 해당 어노테이션으로 save 메서드호출 하지 않아도 db에 데이터가 적용됨.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {// json데이터를 요청-> java object 
		System.out.println("id:"+ id);
		System.out.println("password:"+ requestUser.getPassword());
		System.out.println("email:"+ requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였음");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//방법1
		//userRepository.save(user);
		return null;
	}
	
	// http://localhost:8080/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		
		return userRepository.findAll();
		
	}
	
	//페이지당 2건의 데이터를 리턴 
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> page =  userRepository.findAll(pageable);
		/*
		 * if (page.isFirst()) {
		 * 
		 * }
		 */
		List<User> user = page.getContent();
		return user;
	}
	
	
	
	
	//{id} 주소를 통해 파라미터 값을 전달 받음. 
	// http://localhost:8080/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
			//return null되면 프로그램에 문제가 되니 optional로 user 객체를 감싸서 가져오면 그것을 null인지 여부를 판단해서 return
			User user =  userRepository.findById(id).orElseGet(new Supplier<User>() {
				@Override
				public User get() {
					// TODO Auto-generated method stub
					return new User();
				}
			});
			 
			User user1 =  userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

				@Override
				public IllegalArgumentException get() {
					// TODO Auto-generated method stub
					return new IllegalArgumentException("해당 유저는 없습니다. "+ id);
				}				
			});
			
			//get() - null 일 일은 없으니 user객체를 뽑아서 무조건 리턴
			
			return user1;
	}
	
	
	 @PostMapping("/dummy/join")
	 public String join(User user) {
		 System.out.println("id : "+ user.getId());
		 System.out.println("username:"+ user.getUsername());
		 System.out.println("password:"+ user.getPassword());
		 System.out.println("email:"+ user.getEmail());
		 System.out.println("role:" + user.getRole());
		 System.out.println("createDate:"+ user.getCreateDate());
		 
		 user.setRole(RoleType.USER);
		 userRepository.save(user);
		 return "회원가입이 완료되었습니다.";
	 }
}
