package com.spring.finance.domain;

public class PaymentVO {
	
	private String PAY_DATE; // 결제일
	private double PAY_PRICE; // 결제금액
	private String B_TYPE; // 결제내역 카테고리
	private String B_NAME; // 가맹점명
	private String M_ID; // 회원아이디
	private String C_ID; // 카드번호
	private String B_CD; // 가맹점코드
	
	public String getPAY_DATE() {
		return PAY_DATE;
	}
	public void setPAY_DATE(String pAY_DATE) {
		PAY_DATE = pAY_DATE;
	}
	public double getPAY_PRICE() {
		return PAY_PRICE;
	}
	public void setPAY_PRICE(double pAY_PRICE) {
		PAY_PRICE = pAY_PRICE;
	}
	public String getB_TYPE() {
		return B_TYPE;
	}
	public void setB_TYPE(String b_TYPE) {
		B_TYPE = b_TYPE;
	}
	public String getB_NAME() {
		return B_NAME;
	}
	public void setB_NAME(String b_NAME) {
		B_NAME = b_NAME;
	}
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public String getC_ID() {
		return C_ID;
	}
	public void setC_ID(String c_ID) {
		C_ID = c_ID;
	}
	public String getB_CD() {
		return B_CD;
	}
	public void setB_CD(String b_CD) {
		B_CD = b_CD;
	}

}
