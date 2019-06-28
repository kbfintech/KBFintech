package com.spring.finance.mapper;

import java.util.List;

import com.spring.finance.domain.ProductVO;
import com.spring.finance.domain.TransferVO;

public interface ProductMapper {

	String getPrdID(String m_ID);

	String getProduct(String m_ID);

	void updatePrdTransfer(TransferVO transfer);

}
