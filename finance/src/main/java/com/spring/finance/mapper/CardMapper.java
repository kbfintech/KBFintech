package com.spring.finance.mapper;

import java.util.HashMap;

import com.spring.finance.domain.CardVO;

public interface CardMapper {

	void updateCard(CardVO CardVO);

	String isRegister(String M_ID);

	void regitCardInfo(CardVO CardVO);

	String getCardNum(String M_ID);

	void updateCardAccount(HashMap<String, String> hmap);
}
