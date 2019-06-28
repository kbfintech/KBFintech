package com.spring.finance.mapper;

import com.spring.finance.domain.CardVO;

public interface CardMapper {

	void updateCard(CardVO CardVO);
	String isRegister(String M_ID);
	void regitCardInfo(CardVO CardVO);
	String getCardNum(String M_ID);
}
