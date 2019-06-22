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
	private int CARD_TYPE;

}
