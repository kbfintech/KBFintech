package com.spring.finance.domain;

import lombok.Data;

@Data
public class AccountVO {

	private String M_ID;
	private String M_NAME;
	private String ACCOUNT_NUMBER;
	private String PHONE;
	private int ACCOUNT_MONEY;

	public AccountVO() {
		// TODO Auto-generated constructor stub
	}

	public AccountVO(String m_ID, String m_NAME, String aCCOUNT_NUMBER, String pHONE, int aCCOUNT_MONEY) {
		super();
		M_ID = m_ID;
		M_NAME = m_NAME;
		ACCOUNT_NUMBER = aCCOUNT_NUMBER;
		PHONE = pHONE;
		ACCOUNT_MONEY = aCCOUNT_MONEY;
	}

	public String getM_ID() {
		return M_ID;
	}

	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}

	public String getM_NAME() {
		return M_NAME;
	}

	public void setM_NAME(String m_NAME) {
		M_NAME = m_NAME;
	}

	public String getACCOUNT_NUMBER() {
		return ACCOUNT_NUMBER;
	}

	public void setACCOUNT_NUMBER(String aCCOUNT_NUMBER) {
		ACCOUNT_NUMBER = aCCOUNT_NUMBER;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}

	public int getACCOUNT_MONEY() {
		return ACCOUNT_MONEY;
	}

	public void setACCOUNT_MONEY(int aCCOUNT_MONEY) {
		ACCOUNT_MONEY = aCCOUNT_MONEY;
	}

}