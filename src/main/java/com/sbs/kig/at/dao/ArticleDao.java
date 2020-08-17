package com.sbs.kig.at.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.kig.at.dto.Article;
import com.sbs.kig.at.dto.ArticleReply;

/* 인터페이스이기 때문에 객체 생성이 불가하다. 
   인터페이스에 @Component를 해주지 않아도 스프링은 똑똑하게 찾아낸다.
*/

/* @Mapper는 ArticleDao.xml과 자바를 매핑시켜주는 마이바티스이다.*/
@Mapper
public interface ArticleDao {
	List<Article> getForPrintArticles();
	
	// @Param("id") : id란 키로 값을 사용할 수 있다.
	Article getForPrintArticleById(@Param("id") int id);

	void write(Map<String, Object> param);

	void writeReply(Map<String, Object> param);

	List<ArticleReply> getForPrintArticleReplies(Map<String, Object> param);

	Object deleteReply(@Param("id") int id);

	ArticleReply getForPrintArticleReplyById(int id);

	void modifyReply(Map<String, Object> param);
}