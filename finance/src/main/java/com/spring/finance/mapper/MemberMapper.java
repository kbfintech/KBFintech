package com.spring.finance.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.finance.domain.CardVO;
import com.spring.finance.domain.MemberVO;
import com.spring.finance.domain.PaymentVO;
import com.spring.finance.domain.PlanVO;
import com.spring.finance.domain.ProductVO;

public interface MemberMapper {

	String getMember(String m_ID);
	
	MemberVO getMemberObject(String M_ID);

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

	CardVO getMCard(String M_ID);

	ProductVO getProduct(String M_ID);

	String getPid(Map<String, Object> pay_param);

	List<PlanVO> getPlan(Map<String, Object> plan_param);

	List<PaymentVO> getPayment(Map<String, Object> pay_param);

}
