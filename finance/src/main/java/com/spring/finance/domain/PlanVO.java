package com.spring.finance.domain;

public class PlanVO {
	
	private String PL_ID;		// 플랜 아이디
	private String B_CD;	// 가맹점 코드
	private int PLD_PRICE;	// 상한금액
	
	public String getPL_ID() {
		return PL_ID;
	}
	public void setPL_ID(String pL_ID) {
		PL_ID = pL_ID;
	}
	public String getB_CD() {
		return B_CD;
	}
	public void setB_CD(String b_CD) {
		B_CD = b_CD;
	}
	public int getPLD_PRICE() {
		return PLD_PRICE;
	}
	public void setPLD_PRICE(int pLD_PRICE) {
		PLD_PRICE = pLD_PRICE;
	}
	
	@Override
	public String toString() {
		return "PlanVO [PL_ID=" + PL_ID + ", B_CD=" + B_CD + ", PLD_PRICE=" + PLD_PRICE + "]";
	}
}
