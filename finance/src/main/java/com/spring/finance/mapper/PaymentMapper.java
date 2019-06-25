package com.spring.finance.mapper;

import com.spring.finance.domain.BusinessVO;
import com.spring.finance.domain.PaymentVO;

public interface PaymentMapper {
	
	void insertPayment(PaymentVO pVo);
	
	BusinessVO getBusiness(String B_CD);
	
	void updatePaymentTotal(PaymentVO pVo);
	
	String getPaymentTotalAmount(String M_ID);
	
}
