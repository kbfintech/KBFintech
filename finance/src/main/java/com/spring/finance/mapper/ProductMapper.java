package com.spring.finance.mapper;

import java.util.List;
import java.util.Map;

import com.spring.finance.domain.AccountVO;
import com.spring.finance.domain.ProductVO;
import com.spring.finance.domain.TransferVO;

public interface ProductMapper {

	String getPrdID(String m_ID);

	String getProduct(String m_ID);

	void updatePrdTransfer(TransferVO transfer);
	
	AccountVO getAccount(String M_ID);
	
	void insertProduct(ProductVO productVO); 
	
	void updateAccount(Map<String, Object> prd_param); 
	
	void updatePoint(String M_ID);

}
