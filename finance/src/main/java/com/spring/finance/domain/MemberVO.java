package com.spring.finance.domain;

import lombok.Data;

@Data
public class MemberVO {

	private String M_ID;
	private String M_PW;
	private String M_NAME;
	private int M_POINT;
	private String EMAIL;
	private String PHONE;
	private String PR_REG_IDX;
	private int OPENPAGE;
	private String REGISTER_YN;

	public String getM_ID() {
		return M_ID;
	}

	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}

	public String getM_PW() {
		return M_PW;
	}

	public void setM_PW(String m_PW) {
		M_PW = m_PW;
	}

	public String getM_NAME() {
		return M_NAME;
	}

	public void setM_NAME(String m_NAME) {
		M_NAME = m_NAME;
	}

	public int getM_POINT() {
		return M_POINT;
	}

	public void setM_POINT(int m_POINT) {
		M_POINT = m_POINT;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}

	public String getPR_REG_IDX() {
		return PR_REG_IDX;
	}

	public void setPR_REG_IDX(String pR_REG_IDX) {
		PR_REG_IDX = pR_REG_IDX;
	}

	public int getOPENPAGE() {
		return OPENPAGE;
	}

	public void setOPENPAGE(int oPENPAGE) {
		OPENPAGE = oPENPAGE;
	}

	public String getREGISTER_YN() {
		return REGISTER_YN;
	}

	public void setREGISTER_YN(String rEGISTER_YN) {
		REGISTER_YN = rEGISTER_YN;
	}

}