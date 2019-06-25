package com.spring.finance.domain;

public class RankVO {
	
	private String M_ID;
	private double REALTIME_SCORE;
	private double PLUS_SCORE;
	private double TOTAL_SCORE;
	
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public double getREALTIME_SCORE() {
		return REALTIME_SCORE;
	}
	public void setREALTIME_SCORE(double rEALTIME_SCORE) {
		REALTIME_SCORE = rEALTIME_SCORE;
	}
	public double getPLUS_SCORE() {
		return PLUS_SCORE;
	}
	public void setPLUS_SCORE(double pLUS_SCORE) {
		PLUS_SCORE = pLUS_SCORE;
	}
	public double getTOTAL_SCORE() {
		return TOTAL_SCORE;
	}
	public void setTOTAL_SCORE(double tOTAL_SCORE) {
		TOTAL_SCORE = tOTAL_SCORE;
	}
	
}
