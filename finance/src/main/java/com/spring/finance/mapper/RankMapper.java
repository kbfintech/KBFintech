package com.spring.finance.mapper;

import java.util.ArrayList;

import com.spring.finance.domain.MemberVO;
import com.spring.finance.domain.PaymentVO;
import com.spring.finance.domain.PlanVO;
import com.spring.finance.domain.RankTotalVO;
import com.spring.finance.domain.RankVO;

public interface RankMapper {
	
	ArrayList<String> getProductRegisterList();
	
	ArrayList<PaymentVO> getRankRealtime(String PAY_MONTH);
	
	double getRealtimeScore(PlanVO pVo);
	
	void setFinalRank(RankVO rVo);
	
	String getSuccess(PlanVO vo);
	
	ArrayList<String> getRankTotalID();
	
	ArrayList<RankTotalVO> getRankTotal();
	
	void updateRankTotal(RankVO rVo);
	
	int getTransferAmount(PlanVO pVo);
	
	ArrayList<Double> getPlanTotal(PlanVO pVo);

}
