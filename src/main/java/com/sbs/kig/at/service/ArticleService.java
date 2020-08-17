package com.sbs.kig.at.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.kig.at.dao.ArticleDao;
import com.sbs.kig.at.dto.Article;
import com.sbs.kig.at.dto.ArticleReply;
import com.sbs.kig.at.dto.Member;
import com.sbs.kig.at.dto.ResultData;
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
		List<ArticleReply> articleReplies = articleDao.getForPrintArticleReplies(param);

		Member actor = (Member)param.get("actor");

		for ( ArticleReply articleReply : articleReplies ) {
			// 출력용 부가데이터를 추가한다.
			updateForPrintInfo(actor, articleReply);
		}

		return articleReplies;
	}
	
	private void updateForPrintInfo(Member actor, ArticleReply articleReply) {
		articleReply.getExtra().put("actorCanDelete", actorCanDelete(actor, articleReply));
		articleReply.getExtra().put("actorCanModify", actorCanModify(actor, articleReply));
	}

	public boolean actorCanDelete(Member actor, ArticleReply articleReply) {
		return actorCanModify(actor, articleReply);
	}
	
	public boolean actorCanModify(Member actor, ArticleReply articleReply) {
		return actor != null && actor.getId() == articleReply.getMemberId() ? true : false;
	}

	public void deleteReply(int id) {
		articleDao.deleteReply(id);
	}

	public ArticleReply getForPrintArticleReplyById(int id) {
		return articleDao.getForPrintArticleReplyById(id);
	}

	public ResultData modfiyReply(Map<String, Object> param) {
		articleDao.modifyReply(param);
		return new ResultData("S-1", String.format("%d번 댓글을 수정하였습니다.", Util.getAsInt(param.get("id"))), param);
	}
}