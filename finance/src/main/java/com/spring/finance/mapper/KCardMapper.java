package com.spring.finance.mapper;

import java.util.Map;

import com.spring.finance.domain.KCardVO;

public interface KCardMapper {
	String getCardType(Map<String, String> map);
	String getCardHash(Map<String, String> map);
	void regitCardInfo(KCardVO KCardVO);
}
