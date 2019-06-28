package com.spring.finance.domain;

import lombok.Data;

@Data
public class KProductVO {

	private String fin_prdt_cd;
	private String fin_prdt_nm;
	private String best_rate;
	private String[] spcl_cnd;
	private String join_member;
	// 상세조회 이율정보 mtrt_int, spcl_cnd
	private String mtrt_int;
	
	public String getFin_prdt_cd() {
		return fin_prdt_cd;
	}
	public void setFin_prdt_cd(String fin_prdt_cd) {
		this.fin_prdt_cd = fin_prdt_cd;
	}
	public String getFin_prdt_nm() {
		return fin_prdt_nm;
	}
	public void setFin_prdt_nm(String fin_prdt_nm) {
		this.fin_prdt_nm = fin_prdt_nm;
	}
	public String getBest_rate() {
		return best_rate;
	}
	public void setBest_rate(String best_rate) {
		this.best_rate = best_rate;
	}
	public String[] getSpcl_cnd() {
		return spcl_cnd;
	}
	public void setSpcl_cnd(String[] spcl_cnd) {
		this.spcl_cnd = spcl_cnd;
	}
	public String getJoin_member() {
		return join_member;
	}
	public void setJoin_member(String join_member) {
		this.join_member = join_member;
	}
	public String getMtrt_int() {
		return mtrt_int;
	}
	public void setMtrt_int(String mtrt_int) {
		this.mtrt_int = mtrt_int;
	}
	
	
}
