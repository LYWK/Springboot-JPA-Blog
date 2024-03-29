package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {

	 @Id   //primary key  
	 @GeneratedValue(strategy = GenerationType.IDENTITY)  // 넘버링 전략
	 private int id;

	 @Column(nullable = false, length = 100)
	 private String title;

	 @Lob  //대용량 데이터 
	 private String content;   //썸머노트 라이브러리 <html> 태그가 섞여서 디자인이 됨.


	 @ColumnDefault("0")
	 private int count;   //조회수

	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name= "userId")// db에 저장할 땐  foreign key로 저장됨
	 private User user;  // db는 오브젝트를 저장 할 수 없다. FK, 자바는 오브젝트 저장이 가능하다. 
                         //mappedBy 는 연관관계의 주인이 아니다.(fk가 아니라는..)
	 @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 필요 없을시 lazy 전략으로 설정 
	 @JsonIgnoreProperties({"board"})
	 private List<Reply> replys;
	 
	 @CreationTimestamp
	 private Timestamp createDate;

}
