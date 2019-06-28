package com.spring.finance.domain;

import java.util.ArrayList;

public class PaymentList {
	
	private ArrayList<PaymentVO> paymentList = new ArrayList<PaymentVO>();
	private int pageSize; 		//한 페이지에 표시할 글의 개수
	private int totalCount; 	// 테이블에 저장된 전체 글의 개수
	private int totalPage; 		// 전체 페이지의 개수
	private int currentPage; 	//	현재 화면에 표시되는 페이지 번호
	private int startNo; 		//	현재 화면에 표시되는 글의 시작 일련번호
	private int endNo;			//	현재 화면에 표시되는 글의 마지막 일련번호 
	private int startPage; 		//	페이지 이동 버튼의 시작 페이지 번호
	private int endPage; 		// 페이지 이동 버튼의 마지막 페이지 번호 

	public PaymentList() {}
	
	
	public PaymentList(int pageSize, int totalCount, int currentPage) {
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		
		calculator();
	}
	
//	나머지 5개의 변수에 저장될 값을 계산하는 메소드
	private void calculator() {
		totalPage = (totalCount - 1) / pageSize + 1;
		currentPage = currentPage > totalPage? totalPage : currentPage;
//		mysql은 select 명령 실행 결과 인덱스 값이 0부터 시작되지만 oracle은 인덱스 값이 1부터 시작된다. 
//		mysql은 startNo를 (currentPage - 1) * pageSize와 같이 연산하면 되지만 oracle은 ((currentPage - 1) * pageSize +1과 같이 1을 더해야 한다. 
		
		startNo = (currentPage - 1) * pageSize;
		endNo = startNo + pageSize - 1;
		endNo = endNo > totalCount ? totalCount : endNo;
		
		startPage = (currentPage - 1) / 10 * 10 + 1;
		endPage = startPage + 9;
		endPage = endPage > totalPage ? totalPage : endPage;
	}


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public ArrayList<PaymentVO> getPaymentList() {
		return paymentList;
	}	
	
	public void setPaymentList(ArrayList<PaymentVO> paymentList) {
		this.paymentList = paymentList;
		
	}

	@Override
	public String toString() {
		return "PaymentList [paymentList=" + paymentList + "]";
	}

}
