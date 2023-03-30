package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;


@Getter  
public class PrincipalDetail implements UserDetails {
		
		private User user; //콤포지션-

		

		public PrincipalDetail(User user) {
			this.user = user;
		}

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return user.getPassword();
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return user.getUsername();
		}
		
		//계정이 만료되지 않았는지 리턴한다. (true : 만료안됨)
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		//계정이 잠겨 있는지 확인하는 로직(true : 안잠김)
		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		//비밀번호가 만료되지 않았는지 리턴함.(true:만료안됨)
		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		//계정이 활성화(사용가능)인지 리턴한다. (true:활성화)
		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
		
		//계정의 권한목록을 리턴 
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			Collection<GrantedAuthority> collectors = new ArrayList<>();
			
			/*collectors.add(new GrantedAuthority() {
				
				@Override
				public String getAuthority() {
					// TODO Auto-generated method stub
					return "ROLE_" + user.getRole();
				}
			});*/
			
			collectors.add(()->{return "ROLE_"+user.getRole();});
			return collectors;
		}
}
