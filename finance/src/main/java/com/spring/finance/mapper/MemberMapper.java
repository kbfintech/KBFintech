package com.spring.finance.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring.finance.domain.MemberVO;

public interface MemberMapper {

	String getMember(String m_ID);

	ArrayList<String> getMemberNotTransfer();

	int getOpenPage(String m_ID);

	ArrayList<String> getMemberList();

	void setOpenPageInit(String m_ID);

	void updateOpenPage(String m_ID);

	void updatePoint(MemberVO member);

	int getPrRedIdx(String m_ID);
	
	void insertMember(MemberVO mVo);
	
	String selectUId(String M_ID);
	
	MemberVO login(MemberVO mVo);

	String getEmail(String m_ID);

	String getPassword(String m_ID);
	
	void updateMember(String M_ID);
	
}
