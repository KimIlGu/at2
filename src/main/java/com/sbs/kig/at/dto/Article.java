package com.sbs.kig.at.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 인자 있는 생성자
@Data // Getter, Setter, toString, equals, hashcode
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	// 삭제상태 : 여기에선 true, false가 들어가는데 MySQL에서 0,1로 들어간다. 
	private boolean delStatus;
	private String delDate;
	private boolean displayStatus;
	private String title; 
	private String body;
}
