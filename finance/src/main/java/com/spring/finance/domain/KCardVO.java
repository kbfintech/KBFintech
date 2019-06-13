package com.spring.finance.domain;

import lombok.Data;

@Data
public class KCardVO {

	private String K_ID;
	private String K_MM;
	private String K_YY;
	private String K_CVC;
	private String K_COMPANY;
	private String K_HASH;

	public KCardVO(String K_ID, String K_MM, String K_YY, String K_CVC, String K_COMPANY) {
		this.K_ID = K_ID;
		this.K_MM = K_MM;
		this.K_YY = K_YY;
		this.K_CVC = K_CVC;
		this.K_COMPANY = K_COMPANY;
	}
	
//	public KCardVO(String K_ID, String K_MM, String K_YY, String K_CVC, String K_COMPANY, String K_HASH) {
//		this.K_ID = K_ID;
//		this.K_MM = K_MM;
//		this.K_YY = K_YY;
//		this.K_CVC = K_CVC;
//		this.K_COMPANY = K_COMPANY;
//		this.K_HASH = K_HASH;
//	}
}
