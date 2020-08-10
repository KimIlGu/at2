package com.sbs.kig.at.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Article {
	private int id;
	private String regDate;
	private String title; 
	private String body;
}
