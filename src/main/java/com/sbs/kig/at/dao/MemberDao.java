package com.sbs.kig.at.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.kig.at.dto.Member;

@Mapper
public interface MemberDao {
	public Member getMemberById(@Param("id") int id);

}
