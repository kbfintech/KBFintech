package com.spring.finance.mapper;

import com.spring.finance.domain.MemberVO;

public interface MemberMapper {
	
	void insertMember(MemberVO mVo);
	
	String selectUId(String M_ID);
	
	MemberVO login(MemberVO mVo);
	
}
