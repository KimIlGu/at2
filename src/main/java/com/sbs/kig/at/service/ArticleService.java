package com.sbs.kig.at.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.kig.at.dao.ArticleDao;
import com.sbs.kig.at.dto.Article;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	public int getCount() {
		return 5;
	}
	
	public List<Article> getForPrintArticles() {
		List<Article> articles = articleDao.getForPrintArticles();
		
		return articles;
	}
}
