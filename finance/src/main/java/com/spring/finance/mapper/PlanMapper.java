package com.spring.finance.mapper;

import java.util.ArrayList;
import java.util.List;

import com.spring.finance.domain.PlanVO;

public interface PlanMapper {

	void insertPlan(String PL_PERIOD);

	void insertPlanDetail(PlanVO vo);

	ArrayList<PlanVO> planSearch(String pL_ID);

	ArrayList<String> dateList();
	
}
