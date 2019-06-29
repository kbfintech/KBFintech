package com.spring.finance.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
//import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.finance.domain.AccountVO;
import com.spring.finance.domain.BusinessVO;
import com.spring.finance.domain.PaymentList;
import com.spring.finance.domain.PaymentVO;
import com.spring.finance.domain.PlanVO;
import com.spring.finance.mapper.PaymentMapper;
import com.spring.finance.mapper.PlanMapper;

@Controller
public class PaymentController {
	
	@Autowired
	public SqlSession sqlsession;
	
	@GetMapping("/payment/goodslist")
	public String payGoodsList() {
		System.out.println("구매상품목록페이지");
		return "/pay/payment_goodslist";
	}
	
	@GetMapping("/payment")
	   public String pay(HttpSession session, Model model) throws Exception {
	      System.out.println("결제시연페이지");
	      String M_ID = (String)session.getAttribute("id");
	      System.out.println(M_ID);
	      PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
	      ArrayList<PlanVO> limitList = new ArrayList<PlanVO>();
	      Date date = new Date();
	      int year = date.getYear() + 1900;
	      int month = date.getMonth() + 1;
	      String PL_ID = String.valueOf(year) + String.valueOf(month);
	      HashMap<String, String> hmap = new HashMap<String, String>();
	      hmap.put("M_ID", M_ID);
	      hmap.put("PL_ID", PL_ID);
	      limitList = mapper.getPlanDetail(hmap);
	      int limitCheck = mapper.getPlanLimitCheck(hmap);
	         
	      PaymentList paymentList = new PaymentList();
	       paymentList.setPaymentList(mapper.selectPaymentList(M_ID));
	       ArrayList<PaymentVO> list = new ArrayList<PaymentVO>();
	       for(int i=0; i<paymentList.getPaymentList().size(); i++) {
	          if(paymentList.getPaymentList().get(i).getPAY_DATE().length() == 8) {
	             String delete = paymentList.getPaymentList().get(i).getPAY_DATE().substring(6, 8);
	             String newdate = paymentList.getPaymentList().get(i).getPAY_DATE().replace(delete, "");
	             String finaldate = newdate.replace("-", "");
	             if(finaldate.equals(PL_ID)) {
	                list.add(paymentList.getPaymentList().get(i));
	             }
	          }else if(paymentList.getPaymentList().get(i).getPAY_DATE().length() == 9) {
	             String delete = paymentList.getPaymentList().get(i).getPAY_DATE().substring(6, 9);
	             String newdate = paymentList.getPaymentList().get(i).getPAY_DATE().replace(delete, "");
	             String finaldate = newdate.replace("-", "");
	             if(finaldate.equals(PL_ID)) {
	                list.add(paymentList.getPaymentList().get(i));
	             }
	          }
	       }
	         
	       ArrayList<String> B_CD = new ArrayList<String>();
	       ArrayList<BusinessVO> businessList = mapper.getB_CD();
	       for(int i=0; i< businessList.size(); i++) {
	             B_CD.add(businessList.get(i).getB_CD());
	       }
	         
	       ArrayList<Integer> usePriceList = new ArrayList<Integer>();
	       int cafe = 0;
	       int convenience = 0;
	       int offlineshopping = 0;
	       int onlineshopping = 0;
	       int eat = 0;
	       int trip = 0;
	       int cultural = 0;
	       int other = 0;
	       int useTotalCount = 0;
	         
	       for(int i=0; i<list.size(); i++) {
	          switch(list.get(i).getB_CD()) {
	          case "552100": 
	             cafe += list.get(i).getPAY_PRICE();
	             break;
	          case "552101": 
	             convenience += list.get(i).getPAY_PRICE();
	             break;
	          case "552102": 
	             offlineshopping += list.get(i).getPAY_PRICE();
	             break;
	          case "552103": 
	             onlineshopping += list.get(i).getPAY_PRICE();
	             break;
	          case "552104":
	             eat += list.get(i).getPAY_PRICE();
	             break;
	          case "552105": 
	             trip += list.get(i).getPAY_PRICE();
	             break;
	          case "552106": 
	             cultural += list.get(i).getPAY_PRICE();
	             break;
	          case "552107": 
	             other += list.get(i).getPAY_PRICE();
	             break;
	          }
	          useTotalCount += list.get(i).getPAY_PRICE();
	       }
	       
	       usePriceList.add(cafe);
	       usePriceList.add(convenience);
	       usePriceList.add(offlineshopping);
	       usePriceList.add(onlineshopping);
	       usePriceList.add(eat);
	       usePriceList.add(trip);
	       usePriceList.add(cultural);
	       usePriceList.add(other);
	       
	       
	      if(null != limitList) {
	            ArrayList<String> codeList = new ArrayList<String>();         
	            ArrayList<Integer> priceList = new ArrayList<Integer>();         
	            for(int i=0; i < limitList.size(); i++) {
	               codeList.add(limitList.get(i).getB_CD());
	               priceList.add(limitList.get(i).getPLD_PRICE());
	            }
	            model.addAttribute("usePriceList", usePriceList);
	            model.addAttribute("codeList", codeList);
	            model.addAttribute("priceList", priceList);
	            model.addAttribute("limitCheck", limitCheck);
	         }
	      model.addAttribute("name", (String)session.getAttribute("name"));
	      model.addAttribute("phonNum", (String)session.getAttribute("phone"));
	      
	      return "/pay/payment";
	   }
	
	@GetMapping("/payment/finished")
	public String payComplete(HttpSession session, Model model) throws Exception {
		System.out.println("결제완료페이지");
		
//		PaymentMapper mapper = sqlsession.getMapper(PaymentMapper.class);
//		MemberMapper mapper2 = sqlsession.getMapper(MemberMapper.class);
//		CardMapper mapper3 = sqlsession.getMapper(CardMapper.class);
//		
//		int year = Calendar.getInstance().get(Calendar.YEAR);
//		int month = Calendar.getInstance().get(Calendar.MONTH)+1;
//		int day = Calendar.getInstance().get(Calendar.DATE);
//		String date = year+"-"+month+"-"+day;
//		PaymentVO pVo = new PaymentVO();
//		pVo.setM_ID((String)session.getAttribute("id"));
//		pVo.setPAY_DATE(date);
//		pVo = mapper.getPaymentApproved(pVo);
//		MemberVO mVo = mapper2.getMember(pVo.getM_ID());
//		String cardNum = mapper3.getCardNum(pVo.getM_ID());
//		
//		model.addAttribute("name", mVo.getM_NAME());
//		model.addAttribute("bCompany", pVo.getB_NAME());
//		model.addAttribute("price", pVo.getPAY_PRICE());
//		model.addAttribute("pDate", pVo.getPAY_DATE());
//		model.addAttribute("cardNum", cardNum);
		
		return "/pay/payment_complete";
	}
	
	@ResponseBody
	@RequestMapping(value="/payment/complete", method = RequestMethod.GET)
	public void payComplete(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
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
		String M_ID = (String)session.getAttribute("id");
		String C_ID = mapper.getCardNum(M_ID);
		String CHECK_CARD = request.getParameter("cardFlag");
		
		System.out.println("M_ID: " + M_ID);
		
		// 체크카드인지 신용카드인지 구분하여 체크면 계좌잔액에서 차감
		if(CHECK_CARD.equals("1")) {
			double balance = mapper.getBalance(M_ID);
			balance -= price;
			AccountVO aVo = new AccountVO(M_ID, "", "", "", (int) balance);
			
			mapper.minusAccountMoney(aVo);
			
			System.out.println("Account DB 테이블 계좌잔액 업데이트 성공!");
		}
		
		pVO.setB_CD(B_CD);
		pVO.setB_NAME(B_NAME);
		pVO.setB_TYPE(B_TYPE);
		pVO.setC_ID(C_ID);
		pVO.setM_ID(M_ID);
		pVO.setPAY_DATE(PAY_DATE);
		pVO.setPAY_PRICE(price);
		
		mapper.insertPayment(pVO);
		
		System.out.println("Payment DB 저장성공!");
		
		// 총 결제금액 DB 업데이트
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH)+1;
		String date = year+""+month;
		pVO.setPAY_DATE(date);
		String total = mapper.getPaymentTotalAmount(pVO);
		if(null==total || total=="") pVO.setPAY_TOTAL(0);
		else pVO.setPAY_TOTAL(Double.parseDouble(total)+pVO.getPAY_PRICE());
		mapper.updatePaymentTotal(pVO);
		
//		System.out.println(pVO.getM_ID() + pVO.getPAY_TOTAL());
		
		System.out.println("Payment_Total DB 업데이트 성공!");
		
	}
	
}
