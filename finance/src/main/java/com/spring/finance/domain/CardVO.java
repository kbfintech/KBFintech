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
	
	public CardVO() {
		
	}

	public String getC_ID() {
		return C_ID;
	}

	public void setC_ID(String c_ID) {
		C_ID = c_ID;
	}

	public String getC_MM() {
		return C_MM;
	}

	public void setC_MM(String c_MM) {
		C_MM = c_MM;
	}

	public String getC_YY() {
		return C_YY;
	}

	public void setC_YY(String c_YY) {
		C_YY = c_YY;
	}

	public String getC_CVC() {
		return C_CVC;
	}

	public void setC_CVC(String c_CVC) {
		C_CVC = c_CVC;
	}

	public String getC_COMPANY() {
		return C_COMPANY;
	}

	public void setC_COMPANY(String c_COMPANY) {
		C_COMPANY = c_COMPANY;
	}

	public String getC_HASH() {
		return C_HASH;
	}

	public void setC_HASH(String c_HASH) {
		C_HASH = c_HASH;
	}

	public String getM_ID() {
		return M_ID;
	}

	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}

	public int getCARD_TYPE() {
		return CARD_TYPE;
	}

	public void setCARD_TYPE(int cARD_TYPE) {
		CARD_TYPE = cARD_TYPE;
	}

	public String getACCOUNT_NUMBER() {
		return ACCOUNT_NUMBER;
	}

	public void setACCOUNT_NUMBER(String aCCOUNT_NUMBER) {
		ACCOUNT_NUMBER = aCCOUNT_NUMBER;
	}
	
}
