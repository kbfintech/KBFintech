package com.spring.finance.domain;

public class PaymentTotalVO {
	
	private String M_ID;
	private int PAY_TOTAL;
	private String PAY_MONTH;
	
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		this.M_ID = m_ID;
	}
	public int getPAY_TOTAL() {
		return PAY_TOTAL;
	}
	public void setPAY_TOTAL(int pAY_TOTAL) {
		this.PAY_TOTAL = pAY_TOTAL;
	}
	public String getPAY_MONTH() {
		return PAY_MONTH;
	}
	public void setPAY_MONTH(String pAY_MONTH) {
		this.PAY_MONTH = pAY_MONTH;
	}
	
	@Override
	public String toString() {
		return "PaymentTotalVO [M_ID=" + M_ID + ", PAY_TOTAL=" + PAY_TOTAL + ", PAY_MONTH=" + PAY_MONTH + "]";
	}
}
