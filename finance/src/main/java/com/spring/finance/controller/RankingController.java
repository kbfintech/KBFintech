package com.spring.finance.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.finance.domain.PaymentVO;
import com.spring.finance.domain.PlanVO;
import com.spring.finance.domain.RankVO;
import com.spring.finance.mapper.RankMapper;
//import com.spring.finance.schedule.RankScheduler;

@Controller
@Component
public class RankingController {

	@Autowired
	public SqlSession sqlsession;

//	private static RankScheduler rs = RankScheduler.getInstance();
	
	private static HashMap<String, Double> hsRealtime = new HashMap<>();

	// 매월 매일 아무요일 0시 0분 0초에 실행
	@Scheduled(cron = "0 0 0 * * ?")
	public HashMap<String, Double> setRealtimeRank() {
		
		RankMapper mapper = sqlsession.getMapper(RankMapper.class);

		ArrayList<String> mList = mapper.getProductRegisterList();
		ArrayList<PaymentVO> pVOList = mapper.getRankRealtime();		
		
		int cnt = 0;
		for(int i = 0; i < pVOList.size(); i++) {
			if(cnt > 5) break;
			if(mList.contains(pVOList.get(i).getM_ID())) {
				cnt += 1;
				// 총점 100점에서 지출금액*0.001%만큼 차감
				hsRealtime.put(pVOList.get(i).getM_ID(), 1000000 - (0.01 * pVOList.get(i).getPAY_TOTAL()));
			}
		}
		
		return hsRealtime;

	}

	// 매월 1일 아무요일 0시 0분 0초에 실행
	@Scheduled(cron = "0 0 0 1 * ?")
	public void setTotalRank() {
		
		RankMapper mapper = sqlsession.getMapper(RankMapper.class);

		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH)+1;
		String date = year+""+month;
		System.out.println(date);
	
		ArrayList<String> mList = mapper.getProductRegisterList();
		
		PlanVO vo = new PlanVO();
		
		for(int i = 0; i < mList.size(); i++) {
			
			RankVO rVO = new RankVO();
			
			double pScore = 0d;
			for(int j = 1; j <= month-1; j++) {
				vo.setM_ID(mList.get(i));
				vo.setPL_ID(year+""+j);
				String flag = mapper.getSuccess(vo);
				
				if(null == flag) continue;
				
				if(flag.equals("1")) {
					pScore += 1;
				}
				
			}
			
			rVO.setM_ID(mList.get(i));
			rVO.setPLUS_SCORE(pScore);
			
			double realtime = mapper.getRealtimeScore(mList.get(i));
			rVO.setREALTIME_SCORE(1000000 - (0.01 * realtime));
			rVO.setTOTAL_SCORE(rVO.getPLUS_SCORE()+rVO.getREALTIME_SCORE());
			
			ArrayList<String> mRankTotalList = mapper.getRankTotalID();
			
			if(mRankTotalList.contains(rVO.getM_ID())) {
				mapper.updateRankTotal(rVO);
				System.out.println("Rank_Total DB 업데이트완료!");
			} else {
				mapper.setFinalRank(rVO);
				System.out.println("Rank_Total DB 저장완료!");
			}
			
		}

	}

	@GetMapping("/ranking/showRank")
	public String showRank(Model model) {
		System.out.println("랭킹확인페이지");
		
//		hsRealtime = setRealtimeRank();
	
		HashMap<String, Double> hsTotal = new HashMap<>();

		// 일일랭킹순위
		int idx = 1;
		Iterator<String> keySetR = hsRealtime.keySet().iterator();
		while (keySetR.hasNext()) {
			String key = keySetR.next();
			model.addAttribute("mID_Realtime" + idx, key);
			model.addAttribute("rankRealtime" + idx, hsRealtime.get(key));
			idx += 1;
			System.out.println("key_Realtime: " + key + " value_Realtime: " + hsRealtime.get(key));
		}

		// 최종랭킹순위
		idx = 1;
		Iterator<String> keySetT = hsTotal.keySet().iterator();
		while (keySetT.hasNext()) {
		    String key = keySetT.next();
		    model.addAttribute("mID_Total"+idx, key);
		    model.addAttribute("rankTotal"+idx, hsTotal.get(key));
		    idx += 1;
		    System.out.println("key_Total: " + key + " value_Total: " + hsTotal.get(key));
		}

		return "/ranking/ranking";
	}

}
