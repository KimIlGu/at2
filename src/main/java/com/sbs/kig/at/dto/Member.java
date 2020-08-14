package com.sbs.kig.at.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor
@Data
public class Member {
	private int id;
	private String regDate;
	private String updateDate;
	private boolean delStatus;
	private String delDate;
	private boolean authStatus;
	private String loginId; 
	private String loginPw;
	private String name;
	private String nickname;
	private String email;
	private String phoneNo;
}