package com.spring.finance.mapper;

import com.spring.finance.domain.AccountVO;
import com.spring.finance.domain.BusinessVO;
import com.spring.finance.domain.PaymentVO;

public interface PaymentMapper {
	
	void insertPayment(PaymentVO pVo);
	
	BusinessVO getBusiness(String B_CD);
	
	void updatePaymentTotal(PaymentVO pVo);
	
	String getPaymentTotalAmount(PaymentVO pVo);
	
	void minusAccountMoney(AccountVO aVo);
	
	double getBalance(String M_ID);
	
	String getCardNum(String M_ID);
	
	PaymentVO getPaymentApproved(PaymentVO pVo);
	
}
