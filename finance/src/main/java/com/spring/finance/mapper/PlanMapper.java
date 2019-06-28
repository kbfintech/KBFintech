package com.spring.finance.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.spring.finance.domain.BusinessVO;
import com.spring.finance.domain.PaymentTotalVO;
import com.spring.finance.domain.PaymentVO;
import com.spring.finance.domain.PlanVO;
import com.spring.finance.domain.TransferVO;

public interface PlanMapper {

	void insertPlan(HashMap<String, String> hmap);

	void insertPlanDetail(PlanVO vo);

	ArrayList<PlanVO> planSearch(HashMap<String, String> hmap);

	ArrayList<String> dateList(String M_ID);

	ArrayList<String> getBusinessCD();

	void updatePlanDetail(PlanVO vo);

	void updatePlan(HashMap<String, String> hmap);

	ArrayList<PaymentVO> selectPaymentList(String M_ID);

	ArrayList<BusinessVO> getB_CD();

	void updatePaymentBCD(PaymentVO vo);

	void updatePaymentBTYPE(PaymentVO vo);

	ArrayList<Integer> getPlanPrice(HashMap<String, String> hmap);

	void updatePlanSuccess(HashMap<String, String> hmap);

	void insertPaymentTotal(PaymentTotalVO totalVO);

	String getPaymentTotal(HashMap<String, String> hmap2);

	void updatePaymentTotal(PaymentTotalVO totalVO);

	int getPaymentTotalPrice(HashMap<String, String> hmap);

	void updatePlanFail(HashMap<String, String> hmap);

	void insertTransfer(TransferVO transfer);

	String getPlan(HashMap<String, String> hmap);

	int getPlanSuccess(HashMap<String, String> hmap);

	
}
