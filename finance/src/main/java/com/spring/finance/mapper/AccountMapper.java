package com.spring.finance.mapper;

import com.spring.finance.domain.AccountVO;
import com.spring.finance.domain.TransferVO;

public interface AccountMapper {
	
	String getAccountNum(String M_ID);

	int regitAccount(AccountVO accountVO);

	AccountVO getAccount(String m_ID);

	int getAccountMoney(String m_ID);

	void updateAccount(TransferVO transfer);
	
}
