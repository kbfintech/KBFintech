package com.spring.finance.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.finance.domain.BusinessVO;
import com.spring.finance.domain.LimitVO;
import com.spring.finance.domain.PaymentList;
import com.spring.finance.domain.PaymentTotalVO;
import com.spring.finance.domain.PaymentVO;
import com.spring.finance.domain.PlanVO;
import com.spring.finance.mapper.AccountMapper;
import com.spring.finance.mapper.CardMapper;
import com.spring.finance.mapper.PlanMapper;
import com.spring.finance.util.LoginSessionTool;

@Controller
public class PlanController {
   
   @Autowired
   public SqlSession sqlsession;
   //public String M_ID = "luok377";
   
   @GetMapping("plan/planer")
   public String planer(HttpServletRequest request, HttpSession session, Model model, HttpServletResponse response) {
      
      LoginSessionTool.checkSession(session, model, response);
      String M_ID = (String) session.getAttribute("id");
      if(null == M_ID) {
         return "member/login";
      }
      CardMapper mapperC = sqlsession.getMapper(CardMapper.class);
      AccountMapper mapperA = sqlsession.getMapper(AccountMapper.class);
      String card = "";
      String account = "";
      try {
         card = mapperC.getCardNum(M_ID);
         account = mapperA.getAccountNum(M_ID);         
      }catch(Exception e) {}
      if(null == card || null == account) {
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter out;
         try {
            out = response.getWriter();
            out.println("<script>alert('계좌와 카드를 먼저 등록해주시기 바랍니다.'); location.href='/payRegister'</script>");
            out.flush();
            out.close();
         } catch (IOException e) {
            e.printStackTrace();
            System.out.println("결제 등록 필수로직 에러: "+e.getMessage());
         }
      }
      System.out.println("plan/planer.jsp");
      // 한도가 설정되어 있는 달을 가져와서 한도 설정할 때 설정된건 디비에 저장안되게 체크
      PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
      ArrayList<String> dateList = mapper.dateList(M_ID);
      model.addAttribute("dateList", dateList);
      return "/plan/planer";
   }
   
   @GetMapping("plan/planerOK")
   public String planerOK(HttpServletRequest request, HttpSession session, Model model) {
      System.out.println("plan/planerOK.jsp");
      String M_ID = (String) session.getAttribute("id");
      String year = request.getParameter("year");
      String month = request.getParameter("month");
      int limitCheck = Integer.parseInt(request.getParameter("limit"));
      
      // 설정하려는 년도와 달을 가져와서 디비에 저장
      PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
      String PL_ID = year+month;
      HashMap<String, String> hmap = new HashMap<String, String>();
      hmap.put("PL_ID", PL_ID);
      hmap.put("M_ID", M_ID);
      mapper.insertPlan(hmap);
      if(limitCheck == 1) {
    	  mapper.updateLimitCheck(hmap);
      }
      System.out.println("Plan 테이블에 Plan 저장 성공");
      ArrayList<String> B_CD = mapper.getBusinessCD();
      
      for(int i=0; i<8; i++) {
         PlanVO vo = new PlanVO();
         setPlanVO(request, PL_ID, B_CD, i, vo);
         vo.setM_ID(M_ID);
         mapper.insertPlanDetail(vo);
      }
      
      // 설정한 한도의 년도와 달을 넘겨줘서 설정한도를 조회할 수 있는 페이지로 이동
      model.addAttribute("year", year);
      model.addAttribute("month", month);
      return "redirect:usingPayment";
   }
   
   // 한도를 카테고리별로 저장하는 메소드
   private void setPlanVO(HttpServletRequest request, String PL_ID, ArrayList<String> B_CD, int i, PlanVO vo) {
      vo.setPL_ID(PL_ID);
      vo.setB_CD(B_CD.get(i));
      int PLD_PRICE = 0;
      switch(i) {
      case 0:
         if(request.getParameter("cafe").equals("")) {
            vo.setPLD_PRICE(PLD_PRICE);
         }else {
            vo.setPLD_PRICE(Integer.parseInt(request.getParameter("cafe")));             
         }
         break;
      case 1: 
         if(request.getParameter("convenience").equals("")) {
            vo.setPLD_PRICE(PLD_PRICE);
         }else {
            vo.setPLD_PRICE(Integer.parseInt(request.getParameter("convenience"))); 
         }
         break;
      case 2: 
         if(request.getParameter("offlineshopping").equals("")) {
            vo.setPLD_PRICE(PLD_PRICE);
         }else {
            vo.setPLD_PRICE(Integer.parseInt(request.getParameter("offlineshopping")));
         }
         break;
      case 3: 
         if(request.getParameter("onlineshopping").equals("")) {
            vo.setPLD_PRICE(PLD_PRICE);
         }else {
            vo.setPLD_PRICE(Integer.parseInt(request.getParameter("onlineshopping")));
         }
         break;
      case 4: 
         if(request.getParameter("eat").equals("")) {
            vo.setPLD_PRICE(PLD_PRICE);
         }else {
            vo.setPLD_PRICE(Integer.parseInt(request.getParameter("eat"))); 
         }
         break;
      case 5: 
         if(request.getParameter("trip").equals("")) {
            vo.setPLD_PRICE(PLD_PRICE);
         }else {
            vo.setPLD_PRICE(Integer.parseInt(request.getParameter("trip"))); 
         }
         break;
      case 6: 
         if(request.getParameter("cultural").equals("")) {
            vo.setPLD_PRICE(PLD_PRICE);
         }else {
            vo.setPLD_PRICE(Integer.parseInt(request.getParameter("cultural"))); 
         }
         break;
      case 7: 
         if(request.getParameter("other").equals("")) {
            vo.setPLD_PRICE(PLD_PRICE);
         }else {
            vo.setPLD_PRICE(Integer.parseInt(request.getParameter("other"))); 
         }
         break;
      }
   }
   
   
   @GetMapping("plan/planUpdate")
   public String planUpdate(HttpServletRequest request, HttpSession session, Model model) {
      System.out.println("plan/planUpdate.jsp");
      String M_ID = (String) session.getAttribute("id");
      // 수정하기 위해 수정하길 원하는 년도와 달을 얻어와서 현재 저장되어 있는 카테고리별 금액과 총금액을 계산해서 넘겨준다.
      System.out.println(request.getParameter("year"));
      System.out.println(request.getParameter("month"));
      String PL_ID = request.getParameter("year") + request.getParameter("month");
      HashMap<String, String> hmap = new HashMap<String, String>();
      hmap.put("PL_ID", PL_ID);
      hmap.put("M_ID", M_ID);
      PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
      ArrayList<PlanVO> planList = mapper.planSearch(hmap);
      System.out.println(planList);
      int totalCount = 0;
      ArrayList<String> B_CD = new ArrayList<String>();
      B_CD.add("cafe");
      B_CD.add("convenience");
      B_CD.add("offlineshopping");
      B_CD.add("onlineshopping");
      B_CD.add("eat");
      B_CD.add("trip");
      B_CD.add("cultural");
      B_CD.add("other");
      for(int i = 0; i<8; i++) {
         totalCount += planList.get(i).getPLD_PRICE();
         planList.get(i).setB_CD(B_CD.get(i));         
      }
      int limitCheck = mapper.getPlanLimitCheck(hmap);
      model.addAttribute("totalCount", totalCount);
      model.addAttribute("planList", planList);
      model.addAttribute("limitCheck", limitCheck);
      model.addAttribute("plyear", request.getParameter("year"));
      model.addAttribute("plmonth", request.getParameter("month"));      
      return "/plan/planUpdate";
   }
   
   @GetMapping("plan/planUpdateOK")
   public String planUpdateOK(HttpServletRequest request, HttpSession session,Model model) {
      // 수정하고자하는 년도와 달을 넘겨받고 수정한 금액을 플랜객체에 저장해서 업데이트를 한 후
      // 업데이트를 하면 계획에 실패한것이므로 플랜 성공 여부를 0으로 변경
      System.out.println("plan/planUpdateOK.jsp");
      String M_ID = (String) session.getAttribute("id");
      String year = request.getParameter("year");
      String month = request.getParameter("month");
      int limitCheck = Integer.parseInt(request.getParameter("limit"));
      String PL_ID = year + month;
      HashMap<String, String> hmap = new HashMap<String, String>();
      hmap.put("PL_ID", PL_ID);
      hmap.put("M_ID", M_ID);
      PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
      ArrayList<String> B_CD = mapper.getBusinessCD();
      
      for(int i=0; i<8; i++) {
         PlanVO vo = new PlanVO();
         setPlanVO(request, PL_ID, B_CD, i, vo);
         vo.setM_ID(M_ID);
         System.out.println(vo);
         mapper.updatePlanDetail(vo);
      }
      mapper.updatePlan(hmap);
      mapper.updatePlanFail(hmap);
      LimitVO limitVO = new LimitVO();
      limitVO.setM_ID(M_ID);
      limitVO.setPL_ID(PL_ID);
      limitVO.setLIMITCHECK(limitCheck);
      mapper.updateChangeLimit(limitVO);
      // 수정 후 수정된 한도를 조회하는 페이지로 이동
      model.addAttribute("year", year);
      model.addAttribute("month", month);
      return "redirect:usingPayment";
   }
   
   @GetMapping("plan/paymentListCheck")
   public String paymentListCheck(HttpServletRequest request, HttpSession session, Model model, HttpServletResponse response) {

      LoginSessionTool.checkSession(session, model, response);
      String M_ID = (String) session.getAttribute("id");
      if(null == M_ID) {
         return "member/login";
      }
      CardMapper mapperC = sqlsession.getMapper(CardMapper.class);
      AccountMapper mapperA = sqlsession.getMapper(AccountMapper.class);
      String card = "";
      String account = "";
      try {
         card = mapperC.getCardNum(M_ID);
         account = mapperA.getAccountNum(M_ID);         
      }catch(Exception e) {}
      if(null == card || null == account) {
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter out;
         try {
            out = response.getWriter();
            out.println("<script>alert('계좌와 카드를 먼저 등록해주시기 바랍니다.'); location.href='/payRegister'</script>");
            out.flush();
            out.close();
         } catch (IOException e) {
            e.printStackTrace();
            System.out.println("결제 등록 필수로직 에러: "+e.getMessage());
         }
      }
      System.out.println("plan/paymentListCheck.jsp");
      // 결제된 내역 전체를 가져와서 조회하고자 하는 년도와 월에 맞는 데이터만 Arraylist에 저장해서 결제내역 페이지로 넘겨준다. 
      Date date = new Date();
      int year = date.getYear()+ 1900;
      int month = date.getMonth() + 1;
      try {
         year = Integer.parseInt(request.getParameter("year"));
         month = Integer.parseInt(request.getParameter("month"));
      }catch (Exception e){}
      String checkdate = String.valueOf(year) + String.valueOf(month);
      System.out.println(checkdate);
      //System.out.println(year + "년 " + month + "월");
      PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
      PaymentList paymentList = new PaymentList();
      paymentList.setPaymentList(mapper.selectPaymentList(M_ID));
      ArrayList<PaymentVO> list = new ArrayList<PaymentVO>();
      HashMap<String, String> dateList = new HashMap<String, String>();
      int key = 0;
      for(int i=0; i<paymentList.getPaymentList().size(); i++) {
         if(paymentList.getPaymentList().get(i).getPAY_DATE().length() == 8) {
            String delete = paymentList.getPaymentList().get(i).getPAY_DATE().substring(6, 8);
            String newdate = paymentList.getPaymentList().get(i).getPAY_DATE().replace(delete, "");
            String finaldate = newdate.replace("-", "");
            if(!dateList.containsValue(finaldate)) {
               dateList.put(key++ + "", finaldate);
            }
            if(finaldate.equals(checkdate)) {
               list.add(paymentList.getPaymentList().get(i));
            }
         }else if(paymentList.getPaymentList().get(i).getPAY_DATE().length() == 9) {
            String delete = paymentList.getPaymentList().get(i).getPAY_DATE().substring(6, 9);
            String newdate = paymentList.getPaymentList().get(i).getPAY_DATE().replace(delete, "");
            String finaldate = newdate.replace("-", "");
            if(!dateList.containsValue(finaldate)) {
               dateList.put(key++ + "", finaldate);
            }
            if(finaldate.equals(checkdate)) {
               list.add(paymentList.getPaymentList().get(i));
            }
         }
      }
      ArrayList<String> checkdateList = new ArrayList<String>();
      for(int i=0; i<dateList.size(); i++) {
         checkdateList.add(dateList.get(i + ""));
      }
      System.out.println(list);
      model.addAttribute("list", list);
      model.addAttribute("checkdateList", checkdateList);
      model.addAttribute("yearnow", year);
      model.addAttribute("monthnow", month);
      return "/plan/paymentListCheck";
   }
   
   @GetMapping("plan/paymentUpdate")
   public String paymentUpdate(HttpServletRequest request, HttpSession session, Model model) {
      System.out.println("plan/planUpdateOK.jsp");
      String M_ID = (String) session.getAttribute("id");
      // 결제 내역 중 카테고리를 수정하고자 하는 내용을 넘겨받는다.
      // 수정하려는 년도와월, 카테고리 타입, 카테고리 코드, 결제 내역의 idx를 넘겨받음
      String year = request.getParameter("year");
      String month = request.getParameter("month");
      String pay_date = request.getParameter("pay_date");
      String b_type = request.getParameter("b_type");
      int idx = Integer.parseInt(request.getParameter("idx"));
      String b_cd = request.getParameter("b_cd");
      System.out.println(year + " " + month + " " + pay_date + " " + b_type + " " + idx + " " + b_cd);
      PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
      PaymentVO vo = new PaymentVO();
      String B_CD = "";
      String B_TYPE = "";
      ArrayList<BusinessVO> businessList = mapper.getB_CD();
      for(int i=0; i< businessList.size(); i++) {
         if(businessList.get(i).getB_TYPE().equals(b_type)) {
            B_CD = businessList.get(i).getB_CD();
            B_TYPE = businessList.get(i).getB_TYPE();
         }
      }
      vo.setB_CD(B_CD);
      vo.setB_TYPE(B_TYPE);
      vo.setP_IDX(idx);
      vo.setM_ID(M_ID);
      // 수정하려는 결제 내역의 카테고리 코드와 카테고리 이름을 수정
      mapper.updatePaymentBCD(vo);
      mapper.updatePaymentBTYPE(vo);
      model.addAttribute("year", year);
      model.addAttribute("month", month);
      return "redirect:paymentListCheck";
   }
   
   @GetMapping("plan/usingPayment")
   public String usingPayment(HttpServletRequest request, HttpSession session, Model model, HttpServletResponse response) {
      // 조회하고자 하는 년도와 달을 넘겨받아 해당 설정한도와 해당 년도와달에 결제 내역이 있을 경우
      // 전체 결제 내역을 가져와서 해당하는 날짜에 맞는 데이터만 뽑아서 카테고리별로 총 금액을 구하고
      // 카테고리별로 다 더한 전체 금액을 구한다.

      LoginSessionTool.checkSession(session, model, response);
      
      String M_ID = (String) session.getAttribute("id");
      if(null == M_ID) {
         return "member/login";
      }
      CardMapper mapperC = sqlsession.getMapper(CardMapper.class);
      AccountMapper mapperA = sqlsession.getMapper(AccountMapper.class);
      String card = "";
      String account = "";
      try {
         card = mapperC.getCardNum(M_ID);
         account = mapperA.getAccountNum(M_ID);         
      }catch(Exception e) {}
      if(null == card || null == account) {
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter out;
         try {
            out = response.getWriter();
            out.println("<script>alert('계좌와 카드를 먼저 등록해주시기 바랍니다.'); location.href='/payRegister'</script>");
            out.flush();
            out.close();
         } catch (IOException e) {
            e.printStackTrace();
            System.out.println("결제 등록 필수로직 에러: "+e.getMessage());
         }
      }
      System.out.println("plan/usingPayment.jsp");
      PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
      Date date = new Date();
            
      int year = date.getYear() + 1900;
      int month = date.getMonth() + 1;
      try{
         year = Integer.parseInt(request.getParameter("year"));
         month = Integer.parseInt(request.getParameter("month"));
      }catch(Exception e) {}
      
      String PL_ID = String.valueOf(year)+String.valueOf(month);
      HashMap<String, String> hmap = new HashMap<String, String>();
      hmap.put("PL_ID", PL_ID);
      hmap.put("M_ID", M_ID);
      ArrayList<PlanVO> planList = mapper.planSearch(hmap);
      if(planList.size() == 0) {
    	  ArrayList<String> plidList = mapper.dateList(M_ID);
    	  if(plidList.size() == 0) {
    		  response.setContentType("text/html; charset=UTF-8");
    	         PrintWriter out;
    	         try {
    	            out = response.getWriter();
    	            out.println("<script>alert('설정된 한도가 존재하지 않습니다. 한도를 먼저 설정해주세요.'); location.href='/plan/planer'</script>");
    	            out.flush();
    	            out.close();
    	         } catch (IOException e) {
    	            e.printStackTrace();
    	            System.out.println("결제 등록 필수로직 에러: "+e.getMessage());
    	         }
    	  }else {
    		  PL_ID = plidList.get(0);
    		  HashMap<String, String> hmap2 = new HashMap<String, String>();
    		  hmap2.put("PL_ID", PL_ID);
    		  hmap2.put("M_ID", M_ID);
    		  planList = mapper.planSearch(hmap2);
    		  year = Integer.parseInt(PL_ID.substring(0, 3));
    		  if(PL_ID.length() == 5) {
    			  month = Integer.parseInt(PL_ID.substring(4, 5));    			  
    		  }else if(PL_ID.length() == 6) {    			  
    			  month = Integer.parseInt(PL_ID.substring(4, 6));    			  
    		  }
    	  }
      }
      if(planList.size() == 0) {
    	  ArrayList<String> plidList = mapper.dateList(M_ID);
    	  if(plidList.size() == 0) {
    		  return "/plan/planer";
    	  }
      }
      //System.out.println(planList);
      int totalCount = 0;
      for(int i = 0; i<8; i++) {
         totalCount += planList.get(i).getPLD_PRICE();
      }
      
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
      
      ArrayList<String> checkdateList = mapper.dateList(M_ID);
      // 해당 년도와 달의 총 사용금액을 구한 후 만약 해당 년도와 달에 대한 총 금액이 저장되어 있으면 
      // 변경된 총 금액을 업데이트하고 만약 존재하지 않으면 새로 집어넣는다.
      PaymentTotalVO totalVO = new PaymentTotalVO();
      totalVO.setM_ID(M_ID);
      totalVO.setPAY_TOTAL(useTotalCount);
      totalVO.setPAY_MONTH(PL_ID);
      HashMap<String, String> hmap2 = new HashMap<String, String>();
      hmap2.put("PL_ID", PL_ID);
      hmap2.put("M_ID", M_ID);
      String checkyear = mapper.getPaymentTotal(hmap2);
      if(PL_ID.equals(checkyear)) {
         mapper.updatePaymentTotal(totalVO);
      }else {
         mapper.insertPaymentTotal(totalVO);            
      }
      int limit = mapper.getPlanLimitCheck(hmap2);
      model.addAttribute("limit", limit);
      model.addAttribute("totalCount", totalCount);
      model.addAttribute("useTotalCount", useTotalCount);
      model.addAttribute("planList", planList);
      model.addAttribute("usePriceList", usePriceList);
      model.addAttribute("checkdateList", checkdateList);
      model.addAttribute("searchyear", year);
      model.addAttribute("searchmonth", month);
      return "/plan/usingPayment";
   }
}




















