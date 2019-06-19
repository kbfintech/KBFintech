package com.spring.finance.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.finance.domain.PlanVO;
import com.spring.finance.mapper.PlanMapper;

@Controller
public class PlanController {
	
	@Autowired
	public SqlSession sqlsession;
	
	@GetMapping("plan/planer")
	public String planer() {
		System.out.println("plan/planer.jsp");
		return "/plan/planer";
	}
	
	@GetMapping("plan/planerOK")
	public String planerOK(HttpServletRequest request, Model model) {
		System.out.println("plan/planerOK.jsp");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String c1 = request.getParameter("cafe");
		String c2 = request.getParameter("convenience");
		String c3 = request.getParameter("offlineshopping");
		String c4 = request.getParameter("onlineshopping");
		String c5 = request.getParameter("eat");
		String c6 = request.getParameter("traffic");
		String c7 = request.getParameter("cultural");
		String c8 = request.getParameter("other");
		
		String PL_PERIOD = year+month;
		PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
		mapper.insertPlan(PL_PERIOD);
		System.out.println("Plan 테이블에 Plan 저장 성공");
		
		for(int i=1; i<9; i++) {
			PlanVO vo = new PlanVO();
			vo.setPL_ID(PL_PERIOD);
			vo.setB_CD("c" + i);
			switch(i) {
			case 1: vo.setPLD_PRICE(Integer.parseInt(request.getParameter("cafe"))); break;
			case 2: vo.setPLD_PRICE(Integer.parseInt(request.getParameter("convenience"))); break;
			case 3: vo.setPLD_PRICE(Integer.parseInt(request.getParameter("offlineshopping"))); break;
			case 4: vo.setPLD_PRICE(Integer.parseInt(request.getParameter("onlineshopping"))); break;
			case 5: vo.setPLD_PRICE(Integer.parseInt(request.getParameter("eat"))); break;
			case 6: vo.setPLD_PRICE(Integer.parseInt(request.getParameter("traffic"))); break;
			case 7: vo.setPLD_PRICE(Integer.parseInt(request.getParameter("cultural"))); break;
			case 8: vo.setPLD_PRICE(Integer.parseInt(request.getParameter("other"))); break;
			}
			mapper.insertPlanDetail(vo);
		}
		
		ArrayList<String> dateList = mapper.dateList();
		ArrayList<Integer> yearList = new ArrayList<Integer>();
		ArrayList<Integer> monthList = new ArrayList<Integer>();
		//System.out.println(dateList);
		for(int i=0; i<dateList.size(); i++) {
			if(dateList.get(i).length() == 5) {
				int year1 = Integer.parseInt(dateList.get(i).substring(0, 4));
				int month1 = Integer.parseInt(dateList.get(i).substring(4, 5));
				if(yearList.contains(year1) && monthList.contains(month1)) { continue; }
				else if(yearList.contains(year1) && !monthList.contains(month1)) { monthList.add(month1); } 
				else if(!yearList.contains(year1) && monthList.contains(month1)) { yearList.add(year1);	} 
				else {
					yearList.add(year1);										
					monthList.add(month1);					
				}
			} else if(dateList.get(i).length() == 6){
				int year2 = Integer.parseInt(dateList.get(i).substring(0, 4));
				int month2 = Integer.parseInt(dateList.get(i).substring(4, 6));
				if(yearList.contains(year2) && monthList.contains(month2)) { continue; }
				else if(yearList.contains(year2) && !monthList.contains(month2)) { monthList.add(month2); }
				else if(!yearList.contains(year2) && monthList.contains(month2)) { yearList.add(year2); } 
				else {
					yearList.add(year2);										
					monthList.add(month2);					
				}		
			}
		}
		Collections.sort(yearList);
		Collections.sort(monthList);
		model.addAttribute("yearList", yearList);
		model.addAttribute("monthList", monthList);
		model.addAttribute("dateList", dateList);
		return "/plan/planSearch";
	}
	
	@GetMapping("plan/planSearch")
	public String planSearch(HttpServletRequest request, Model model) {
		System.out.println("plan/planSearch.jsp");
		PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
		ArrayList<String> dateList = mapper.dateList();
		ArrayList<Integer> yearList = new ArrayList<Integer>();
		ArrayList<Integer> monthList = new ArrayList<Integer>();
		//System.out.println(dateList);
		for(int i=0; i<dateList.size(); i++) {
			if(dateList.get(i).length() == 5) {
				int year1 = Integer.parseInt(dateList.get(i).substring(0, 4));
				int month1 = Integer.parseInt(dateList.get(i).substring(4, 5));
				if(yearList.contains(year1) && monthList.contains(month1)) { continue; }
				else if(yearList.contains(year1) && !monthList.contains(month1)) { monthList.add(month1); } 
				else if(!yearList.contains(year1) && monthList.contains(month1)) { yearList.add(year1);	} 
				else {
					yearList.add(year1);										
					monthList.add(month1);					
				}
			} else if(dateList.get(i).length() == 6){
				int year2 = Integer.parseInt(dateList.get(i).substring(0, 4));
				int month2 = Integer.parseInt(dateList.get(i).substring(4, 6));
				if(yearList.contains(year2) && monthList.contains(month2)) { continue; }
				else if(yearList.contains(year2) && !monthList.contains(month2)) { monthList.add(month2); }
				else if(!yearList.contains(year2) && monthList.contains(month2)) { yearList.add(year2); } 
				else {
					yearList.add(year2);										
					monthList.add(month2);					
				}		
			}
		}
		Collections.sort(yearList);
		Collections.sort(monthList);
		model.addAttribute("yearList", yearList);
		model.addAttribute("monthList", monthList);
		model.addAttribute("dateList", dateList);
		return "/plan/planSearch";
	}
	
	@GetMapping("plan/planSearchOK")
	public String planSearchOK(HttpServletRequest request, Model model) {
		System.out.println("plan/planSearchOK.jsp");
		PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
		ArrayList<String> dateList = mapper.dateList();
		ArrayList<Integer> yearList = new ArrayList<Integer>();
		ArrayList<Integer> monthList = new ArrayList<Integer>();
		//System.out.println(dateList);
		for(int i=0; i<dateList.size(); i++) {
			if(dateList.get(i).length() == 5) {
				int year1 = Integer.parseInt(dateList.get(i).substring(0, 4));
				int month1 = Integer.parseInt(dateList.get(i).substring(4, 5));
				if(yearList.contains(year1) && monthList.contains(month1)) { continue; }
				else if(yearList.contains(year1) && !monthList.contains(month1)) { monthList.add(month1); } 
				else if(!yearList.contains(year1) && monthList.contains(month1)) { yearList.add(year1);	} 
				else {
					yearList.add(year1);										
					monthList.add(month1);					
				}
			} else if(dateList.get(i).length() == 6){
				int year2 = Integer.parseInt(dateList.get(i).substring(0, 4));
				int month2 = Integer.parseInt(dateList.get(i).substring(4, 6));
				if(yearList.contains(year2) && monthList.contains(month2)) { continue; }
				else if(yearList.contains(year2) && !monthList.contains(month2)) { monthList.add(month2); }
				else if(!yearList.contains(year2) && monthList.contains(month2)) { yearList.add(year2); } 
				else {
					yearList.add(year2);										
					monthList.add(month2);					
				}		
			}
		}
		Collections.sort(yearList);
		Collections.sort(monthList);
		model.addAttribute("yearList", yearList);
		model.addAttribute("monthList", monthList);
		model.addAttribute("dateList", dateList);
		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		
		String PL_ID = year+month;
		ArrayList<PlanVO> planList = mapper.planSearch(PL_ID);
		//System.out.println(planList);
		int totalCount = 0;
		for(int i = 0; i<8; i++) {
			totalCount += planList.get(i).getPLD_PRICE();
		}
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("planList", planList);
		return "/plan/planSearch";
	}
	
	
	@GetMapping("plan/planUpdate")
	public String planUpdate() {
		System.out.println("plan/planUpdate.jsp");
		return "/plan/planUpdate";
	}

}
