package com.spring.finance.mapper;

import com.spring.finance.domain.KCardVO;

public interface KCardMapper {
	String getCardInfo(KCardVO KCardVO);
	void regitCardInfo(KCardVO KCardVO);
}
