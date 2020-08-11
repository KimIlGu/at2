package com.sbs.kig.at.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.kig.at.dto.Article;
import com.sbs.kig.at.util.Util;

@Service
public class ArticleService {
	public int getCount() {
		return 5;
	}
	
	public List<Article> getForPrintArticles() {
		List<Article> articles = new ArrayList<>();
		articles.add(new Article(1, Util.getNowDateStr(), Util.getNowDateStr(), false, "", true, "제목1", "내용1"));
		articles.add(new Article(1, Util.getNowDateStr(), Util.getNowDateStr(), false, "", true, "제목2", "내용2"));
		
		return articles;
	}
}