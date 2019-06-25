package com.spring.finance.mapper;

import java.util.ArrayList;

import com.spring.finance.domain.MemberVO;
import com.spring.finance.domain.PaymentVO;
import com.spring.finance.domain.PlanVO;
import com.spring.finance.domain.RankVO;

public interface RankMapper {
	
	ArrayList<String> getProductRegisterList();
	
	ArrayList<PaymentVO> getRankRealtime();
	
	double getRealtimeScore(String M_ID);
	
	void setFinalRank(RankVO rVo);
	
	String getSuccess(PlanVO vo);
	
	ArrayList<String> getRankTotalID();
	
	void updateRankTotal(RankVO rVo);

}
