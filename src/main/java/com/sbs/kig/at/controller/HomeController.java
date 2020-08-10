package com.sbs.kig.at.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.kig.at.dto.Article;

// 디스패처 서블릿을 만들 필요 없다.

// new HomeController() 객체 생성이 필요 없다.
@Controller
public class HomeController {
 
	@RequestMapping("/home/boolean")
	@ResponseBody
	public boolean showBoolean() {
		return false;
	}
	
	@RequestMapping("/home/char")
	@ResponseBody
	public char showChar() {
		return '1';
	}
	
	@RequestMapping("/home/byte")
	@ResponseBody
	public byte showByte() {
		return Byte.MAX_VALUE;
	}
	
	@RequestMapping("/home/short")
	@ResponseBody
	public short showShort() {
		return Short.MAX_VALUE;
	}
	
	@RequestMapping("/home/int")
	@ResponseBody
	public int showInt() {
		return Integer.MAX_VALUE;
	}
	
	@RequestMapping("/home/long")
	@ResponseBody
	public long showLong() {
		return Long.MAX_VALUE;
	}
	
	@RequestMapping("/home/float")
	@ResponseBody
	public float showFloat() {
		return Float.MAX_VALUE;
	}
	
	@RequestMapping("/home/double")
	@ResponseBody
	public double showDouble() {
		return Double.MAX_VALUE;
	}
	
	@RequestMapping("/home/string")
	@ResponseBody
	public String showString() {
		return "안녕";
	}
	
	@RequestMapping("/home/intArr")
	@ResponseBody
	public int[] showIntArr() {
		int[] arr = new int[10];
		arr[0] = 10;
		arr[1] = 20;
		arr[2] = 30;
		
		return arr; 
	}
	
	@RequestMapping("/home/intList")
	@ResponseBody
	public List<Integer> showIntList() {
		List<Integer> list = new ArrayList<>();
		list.add(10);
		list.add(20);
		list.add(30);
		return list;
	}
	
	@RequestMapping("/home/article")
	@ResponseBody
	public Article showArticle() {
		return new Article(1, "2020-08-10", "제목", "내용");
	}
	/* 
	  필드의 접근제한자가 private일 때는 lombok이 찾을수 없다.
	  DTO에 Getter 또는 Data를 추가함으로써 해결할 수 있다.
	  Data는 getter, setter, toString, hashcode, equals까지 만들어 준다. 
	*/
	
	@RequestMapping("/home/articleList")
	@ResponseBody
	public List<Article> showArticleList() {
		List<Article> list = new ArrayList<>();
		list.add(new Article(1, "2020-08-10", "제목1", "내용1"));
		list.add(new Article(2, "2020-08-11", "제목2", "내용2"));
		list.add(new Article(3, "2020-08-12", "제목3", "내용3"));
		return list;
	}
	
	@RequestMapping("/home/map1")
	@ResponseBody
	public Map<String, Object> showMap1() {
		Map<String, Object> map = new HashMap<>();
		map.put("철수나이", 12);
		map.put("영희나이", 14);
		map.put("영수나이", 16);
		return map; 
	}
	
	@RequestMapping("/home/map2")
	@ResponseBody
	public Map<String, Object> showMap2() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("철수나이", 12);
		map.put("영희나이", 14);
		map.put("영수나이", 16);
		return map; 
	}
	
	/* 
	  원래 Map이란 순서를 기억하지 못한다.
	  하지만 LinkedHashMap은 순서가 있다.
	 */
}