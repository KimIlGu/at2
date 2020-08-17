package com.sbs.kig.at.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.kig.at.dao.ArticleDao;
import com.sbs.kig.at.dto.Article;
import com.sbs.kig.at.dto.ArticleReply;
import com.sbs.kig.at.util.Util;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	public int getCount() {
		return 5;
	}
	
	public List<Article> getForPrintArticles() {
		return articleDao.getForPrintArticles();
	}

	public Article getForPrintArticleById(int id) {
		return articleDao.getForPrintArticleById(id);
	}

	public int write(Map<String, Object> param) {
		// param (title, body, redirectUrl)
		articleDao.write(param);
		// param (title, body, redirectUrl, id)
		
		return Util.getAsInt(param.get("id"));
	}

	public int writeReply(Map<String, Object> param) {
		articleDao.writeReply(param);

		return Util.getAsInt(param.get("id"));
	}

	public List<ArticleReply> getForPrintArticleReplies(Map<String, Object> param) {
		return articleDao.getForPrintArticleReplies(param);
	}

	public void deleteReply(int id) {
		articleDao.deleteReply(id);
	}
}
