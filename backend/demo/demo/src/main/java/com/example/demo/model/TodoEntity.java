package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Todo")
public class TodoEntity{
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy ="uuid")
	private String id; //오브젝트의 아이디
	private String userId; //이 오브젝트를 생성한 사용자의 아이디
	private String title; //example: 운동하기
	private boolean done; //true: todo를 완료한 경우
	
	@Version
    	private Long version;
}
