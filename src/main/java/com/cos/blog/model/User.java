package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
		
	 @Id   //primary key  
	 @GeneratedValue(strategy = GenerationType.IDENTITY)  // database 넘버링 전략
	 private int  id; // 시퀀스 /auto-increment

	 @Column(nullable = false, length = 30)
	 private String username;

	 @Column(nullable = false, length = 100) // --> 해쉬로 변경 해야 하기 때문에 공간이 넣넣히 필요
	 private String password;

	 @Column(nullable = false, length = 50)
	 private String email;
	 
	 @Enumerated(EnumType.STRING)
	 private RoleType role;

	 @CreationTimestamp // 가입한 시간
	 private Timestamp createDate;

}
