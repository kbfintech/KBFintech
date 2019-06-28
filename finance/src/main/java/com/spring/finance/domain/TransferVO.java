package com.spring.finance.domain;

public class TransferVO {
	
	private String M_ID;
	private String TRANSFER_DATE;
	private int TRANSFER_MONEY;
	
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public String getTRANSFER_DATE() {
		return TRANSFER_DATE;
	}
	public void setTRANSFER_DATE(String tRANSFER_DATE) {
		TRANSFER_DATE = tRANSFER_DATE;
	}
	public int getTRANSFER_MONEY() {
		return TRANSFER_MONEY;
	}
	public void setTRANSFER_MONEY(int tRANSFER_MONEY) {
		TRANSFER_MONEY = tRANSFER_MONEY;
	}
	
	@Override
	public String toString() {
		return "TransferVO [M_ID=" + M_ID + ", TRANSFER_DATE=" + TRANSFER_DATE + ", TRANSFER_MONEY=" + TRANSFER_MONEY
				+ "]";
	}

}
