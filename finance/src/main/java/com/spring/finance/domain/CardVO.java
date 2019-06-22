package com.spring.finance.domain;

import lombok.Data;

@Data
public class CardVO {

	private String C_ID;
	private String C_MM;
	private String C_YY;
	private String C_CVC;
	private String C_COMPANY;
	private String C_HASH;
	private String M_ID;
	private int CARD_TYPE;
	private String ACCOUNT_NUMBER;

	public CardVO(String c_ID, String c_MM, String c_YY, String c_CVC, String c_COMPANY, String c_HASH, String m_ID,
			int cARD_TYPE, String aCCOUNT_NUMBER) {
		super();
		C_ID = c_ID;
		C_MM = c_MM;
		C_YY = c_YY;
		C_CVC = c_CVC;
		C_COMPANY = c_COMPANY;
		C_HASH = c_HASH;
		M_ID = m_ID;
		CARD_TYPE = cARD_TYPE;
		ACCOUNT_NUMBER = aCCOUNT_NUMBER;
	}

}
