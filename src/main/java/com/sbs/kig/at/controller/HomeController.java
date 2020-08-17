package com.sbs.kig.at.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@Autowired
	
	// jsp를 사용하기 위해 pom.xml에서 JSP 엔진(jasper)을 추가한다.
	@RequestMapping("/usr/home/main")
	public String showMain() {
		return "home/main"; 
	}
}