package com.sbs.kig.at.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	// jsp를 사용하기 위해 pom.xml에서 JSP 엔진(jasper)을 추가한다.
	@RequestMapping("/home/main")
	public String showMain() {
		return "home/main"; 
	}
}