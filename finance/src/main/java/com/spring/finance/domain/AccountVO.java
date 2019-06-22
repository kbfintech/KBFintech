package com.spring.finance.domain;

import lombok.Data;

@Data
public class AccountVO {

	private String M_ID;
	private String M_NAME;
	private String ACCOUNT_NUMBER;
	private String PHONE;
	private int ACCOUNT_MONEY;

	public AccountVO(String m_ID, String m_NAME, String aCCOUNT_NUMBER, String pHONE, int aCCOUNT_MONEY) {
		super();
		M_ID = m_ID;
		M_NAME = m_NAME;
		ACCOUNT_NUMBER = aCCOUNT_NUMBER;
		PHONE = pHONE;
		ACCOUNT_MONEY = aCCOUNT_MONEY;
	}

}
