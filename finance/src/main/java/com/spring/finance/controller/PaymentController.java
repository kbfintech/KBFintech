package com.spring.finance.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
//import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.finance.domain.BusinessVO;
import com.spring.finance.domain.PaymentVO;
import com.spring.finance.mapper.PaymentMapper;

@Controller
public class PaymentController {
	
	private List<PaymentVO> paymentList;
	
	@Autowired
	public SqlSession sqlsession;
	
	@GetMapping("/payment/goodslist")
	public String payGoodsList() {
		System.out.println("구매상품목록페이지");
		return "/pay/payment_goodslist";
	}
	
	@GetMapping("/payment")
	public String pay() throws Exception {
		System.out.println("결제시연페이지");
		return "/pay/payment";
	}
	
	@ResponseBody
	@RequestMapping(value="/payment/complete", method = RequestMethod.GET)
	public void payComplete(HttpServletRequest request) {
		System.out.println("결제완료페이지");
		
		// 결제내역 카테고리 DB 불러오기
		String B_CD = request.getParameter("bCode");
		
//		System.out.println("B_CD: " + B_CD);
		
		PaymentMapper mapper = sqlsession.getMapper(PaymentMapper.class);
		BusinessVO bVO = mapper.getBusiness(B_CD);
		
		System.out.println("B_CD: " + bVO.getB_CD());
		System.out.println("B_TYPE: " + bVO.getB_TYPE());
		B_CD = bVO.getB_CD();
		
		// 결제내역 DB 저장
		
		PaymentVO pVO = new PaymentVO();
		
		String PAY_DATE = request.getParameter("pDate");
		String PAY_PRICE = request.getParameter("pPrice");
		double price = Double.parseDouble(PAY_PRICE);
		
		String B_TYPE = bVO.getB_TYPE();
		String B_NAME = request.getParameter("bName");
		String M_ID = request.getParameter("mId");
		String C_ID = request.getParameter("cId");
		
		pVO.setB_CD(B_CD);
		pVO.setB_NAME(B_NAME);
		pVO.setB_TYPE(B_TYPE);
		pVO.setC_ID(C_ID);
		pVO.setM_ID(M_ID);
		pVO.setPAY_DATE(PAY_DATE);
		pVO.setPAY_PRICE(price);
		
		mapper.insertPayment(pVO);
		
		System.out.println("Payment DB 저장성공!");
		
	}
	
}
