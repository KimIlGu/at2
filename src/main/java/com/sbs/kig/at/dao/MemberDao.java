package com.sbs.kig.at.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.kig.at.dto.Member;

@Mapper
public interface MemberDao {
	public Member getMemberById(@Param("id") int id);
	
	void join(Map<String, Object> param);

	int getLoginIdDupCount(@Param("loginId") String loginId);

	public Member getMemberByLoginId(@Param("loginId") String loginId);
}
