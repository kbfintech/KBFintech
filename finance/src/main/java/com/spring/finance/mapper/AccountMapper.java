package com.spring.finance.mapper;

import com.spring.finance.domain.AccountVO;

public interface AccountMapper {
	
	void updateAccount(AccountVO accountVO);
	
	String getAccountNum(String M_ID);

	int regitAccount(AccountVO accountVO);
	
}
