package com.spring.finance.controller;

import java.util.ArrayList;
import java.util.Calendar;

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

@Controller
@Component
public class RankingController {
	
	public class RankClass {
		public String id;
		public double score;
	}

	@Autowired
	public SqlSession sqlsession;
	
	private static ArrayList<RankClass> arrRealtime = new ArrayList<>();
	private static ArrayList<RankClass> arrRankTotal = new ArrayList<>();

	// 매월 매일 아무요일 0시 0분 0초에 실행
	@Scheduled(cron = "* * * * * ?")
	public ArrayList<RankClass> setRealtimeRank() {
		
		RankMapper mapper = sqlsession.getMapper(RankMapper.class);

		ArrayList<String> mList = mapper.getProductRegisterList();
		ArrayList<PaymentVO> pVOList = mapper.getRankRealtime();		
		
		int cnt = 0;
		arrRealtime.clear();
		for(int i = 0; i < pVOList.size(); i++) {
			if(cnt > 5) break;
			if(mList.contains(pVOList.get(i).getM_ID())) {
				RankClass rc = new RankClass();
				System.out.println("key_Realtime: " + pVOList.get(i).getM_ID() + " value_Realtime: " + pVOList.get(i).getPAY_TOTAL());
				cnt += 1;
				// 총점 100점에서 지출금액*0.001%만큼 차감
				rc.id = pVOList.get(i).getM_ID();
				rc.score = 1000000 - (0.01 * pVOList.get(i).getPAY_TOTAL());
				arrRealtime.add(rc);
			}
		}
		
		return arrRealtime;

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
			
			// 연속 플랜 성공여부 검사
			double pScore = 0d;
			for(int j = 1; j <= month-1; j++) {
				vo.setM_ID(mList.get(i));
				vo.setPL_ID(year+""+j);
				String flag = mapper.getSuccess(vo);
				
				if(null == flag) continue;
				
				if(flag.equals("1")) {
					pScore += 1;
				} else if(flag.equals("0")) {
					pScore = 0;
					break;
				}
				
			}
			
			// 이체금액구간별 가산점 검사
			
			
			
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
	

		// 일일랭킹순위
		for(int i = 0; i < arrRealtime.size(); i++) {
			model.addAttribute("mID_Realtime" + (i+1), arrRealtime.get(i).id);
			model.addAttribute("rankRealtime" + (i+1), arrRealtime.get(i).score);
		}

		// 최종랭킹순위
		for(int i = 0; i < arrRankTotal.size(); i++) {
			model.addAttribute("mID_Realtime" + (i+1), arrRankTotal.get(i).id);
			model.addAttribute("rankRealtime" + (i+1), arrRankTotal.get(i).score);
		}

		return "/ranking/ranking";
	}

}
