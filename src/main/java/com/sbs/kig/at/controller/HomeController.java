package com.sbs.kig.at.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 디스패처 서블릿을 만들 필요 없다.

// new HomeController() 객체 생성이 필요 없다.
@Controller
public class HomeController {
	// request.getRequestDispatcher().forward(); 요청된 url 함수 매핑 
	@RequestMapping("/home/main")
	// 함수가 리턴한 값을 응답으로 쓰겠습니다.
	@ResponseBody
	public String showMain() {
		return "안녕";
	}
	
	@RequestMapping("/home/plus")
	@ResponseBody
	public int showPlus(int a, int b) {
	/* 
	여기서 이상한 점은 int는 자바에만 존재하는 녀석이기 때문에 브라우저는 int를 해석할 수 없다.
	중간에 누군가가 가로채서 3 0 이라는 문장으로 변환시켜주고 있다.	
	 */
		return a + b;
	}
	
	@RequestMapping("/home/increase")
	@ResponseBody
	public int showIncrease() {
		return 1;
	}
}
