package com.sbs.kig.at.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.kig.at.dto.Article;
import com.sbs.kig.at.service.ArticleService;

@Controller
public class ArticleController {

	/*
	 * ArticleController() { ArticleService articleService = new ArticleService() }
	 * 
	 * 생성자를 통해 서비스를 만들어야 했는데, Autowired는 스스로가 알아서 찾아간다. 대신 Service에서 @Service를 써주어야
	 * 한다. 보통은 @Component를 써주는데 서비스는 특별하다.
	 * 
	 * 크게 신경쓰고 있지 않았는데 @Controller도 같은 경우로 보면 될듯 하다.
	 */
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/article/list1")
	public String showList1(HttpServletRequest req) {
		int count = articleService.getCount();
		List<Article> articles = articleService.getForPrintArticles();

		req.setAttribute("count", count);
		req.setAttribute("articles", articles);

		return "article/list";
	}

	// Model이 더 진보된 방식이라는데 이건 아직은 잘 모르겠다
	@RequestMapping("/article/list2")
	public String showList2(Model model) {
		int count = articleService.getCount();
		List<Article> articles = articleService.getForPrintArticles();

		model.addAttribute("count", count);
		model.addAttribute("articles", articles);

		return "article/list";
	}

}