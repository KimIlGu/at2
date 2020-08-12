package com.sbs.kig.at.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.kig.at.dto.Article;

/* 인터페이스이기 때문에 객체 생성이 불가하다. 
   인터페이스에 @Component를 해주지 않아도 스프링은 똑똑하게 찾아낸다.
*/

/* @Mapper는 ArticleDao.xml과 자바를 매핑시켜주는 마이바티스이다.*/
@Mapper
public interface ArticleDao {
	List<Article> getForPrintArticles();
}