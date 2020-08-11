package com.sbs.kig.at.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.kig.at.service.ArticleService;

@Controller
public class ArticleController {
	
	/* 
		ArticleController() {
			ArticleService articleService = new ArticleService()
		}
		
		생성자를 통해 서비스를 만들어야 했는데, Autowired는 스스로가 알아서 찾아간다.
		대신 Service에서 @Service를 써주어야 한다. 
		보통은 @Component를 써주는데 서비스는 특별하다. 
		
		크게 신경쓰고 있지 않았는데 @Controller도 같은 경우로 보면 될듯 하다.
	*/
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/article/list")
	@ResponseBody
	public int showList() {
		return articleService.getCount(); 
	}
	
	// "article/list"
	
}