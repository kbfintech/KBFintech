package com.spring.finance.mapper;

import com.spring.finance.domain.CardVO;

public interface CardMapper {

	String isRegister(String M_ID);
	void regitCardInfo(CardVO CardVO);
}
