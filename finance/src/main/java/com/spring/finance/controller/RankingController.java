package com.spring.finance.controller;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.finance.domain.MemberVO;
import com.spring.finance.domain.PaymentVO;
import com.spring.finance.domain.PlanVO;
import com.spring.finance.domain.RankTotalVO;
import com.spring.finance.domain.RankVO;
import com.spring.finance.mapper.RankMapper;
import com.spring.finance.util.LoginSessionTool;

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
	@Scheduled(cron = "* 16 4 * * ?")
	public ArrayList<RankClass> setRealtimeRank() {
		
		RankMapper mapper = sqlsession.getMapper(RankMapper.class);
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH)+1;
		String date = year+""+month;

		ArrayList<String> mList = mapper.getProductRegisterList();
		ArrayList<PaymentVO> pVOList = mapper.getRankRealtime(date);		
		
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
				rc.score = 100000 - (0.01 * pVOList.get(i).getPAY_TOTAL());
				arrRealtime.add(rc);
			}
		}
		
		return arrRealtime;

	}

	// 매월 1일 아무요일 0시 0분 0초에 실행
	@Scheduled(cron = "0 0 0 1 * ?")
	public ArrayList<RankClass> setTotalRank() {
		
		RankMapper mapper = sqlsession.getMapper(RankMapper.class);

		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		String date = year+""+month;
		System.out.println(date);
	
		ArrayList<String> mList = mapper.getProductRegisterList();
		
		PlanVO vo = new PlanVO();
		
		for(int i = 0; i < mList.size(); i++) {
			
			RankVO rVO = new RankVO();
			
			// 연속 플랜 성공여부 검사
			double pScore = 0d;
			for(int j = 1; j <= month; j++) {
				vo.setM_ID(mList.get(i));
				vo.setPL_ID(year+""+j);
				System.out.println(vo.getM_ID() + " / " + vo.getPL_ID());
				String flag = mapper.getSuccess(vo);
				
				if(null == flag) continue;
				
				if(flag.equals("1")) {
					pScore += 1;
				} else if(flag.equals("0")) {
					pScore = 0;
				}
				
			}
			
			PlanVO pv = new PlanVO();
			
			pv.setM_ID(mList.get(i));
			pv.setPL_ID(date);
			
			// 이체금액구간별 가산점 검사 * 0.3
			double transferAmount = mapper.getTransferAmount(pv);
			System.out.println("transferAmount: " + transferAmount);
			if(transferAmount >= 10000d && transferAmount < 100000d) {
				pScore += 10*0.3;
			} else if(transferAmount >= 100000d && transferAmount < 500000d) {
				pScore += 20*0.3;
			} else if(transferAmount >= 500000d && transferAmount < 1000000d) {
				pScore += 30*0.3;
			} else if(transferAmount >= 1000000d) {
				pScore += 40*0.3;
			}
			
			// (실제 이채금액 / (총 한도금액 - 총 지출금액)) * 0.3
			double realtime = mapper.getRealtimeScore(pv);
			ArrayList<Double> mLimitsList = mapper.getPlanTotal(pv);
			double limitsTotal = 0d;
			for(int j = 0; j < mLimitsList.size(); j++) {
				limitsTotal += mLimitsList.get(j);
			}
			pScore += (transferAmount / (limitsTotal - realtime)) * 0.3;
			
			rVO.setM_ID(mList.get(i));
			rVO.setPLUS_SCORE(pScore);
			
			// 일일랭킹 총점수
			rVO.setREALTIME_SCORE((100000 - (0.01 * realtime))*0.4);
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
		
		arrRankTotal.clear();
		int curPoint = 5000;
		ArrayList<RankTotalVO> rankTotalList = mapper.getRankTotal();
		for(int i = 0 ; i < rankTotalList.size(); i++) {
			RankClass rc = new RankClass();
			MemberVO mVo = new MemberVO();
			rc.id = rankTotalList.get(i).getM_ID();
			rc.score = rankTotalList.get(i).getTOTAL_SCORE();
			mVo.setM_ID(rc.id);
			if(curPoint >= 1000) {
				mVo.setM_POINT(curPoint);
			} else {
				mVo.setM_POINT(0);
			}
			curPoint -= 2000;
//			mapper.updateMPoint(mVo);
			arrRankTotal.add(rc);
			
		}
		
		return arrRankTotal;

	}

	@GetMapping("/ranking/showRank")
	public String showRank(HttpSession session, Model model, HttpServletResponse response) {
		System.out.println("랭킹확인페이지");
		
		LoginSessionTool.checkSession(session, model, response);

		// 일일랭킹순위
		for(int i = 0; i < arrRealtime.size(); i++) {
			model.addAttribute("mID_Realtime" + (i+1), arrRealtime.get(i).id);
			model.addAttribute("rankRealtime" + (i+1), arrRealtime.get(i).score);
		}

		// 최종랭킹순위
		for(int i = 0; i < arrRankTotal.size(); i++) {
			model.addAttribute("mID_RankTotal" + (i+1), arrRankTotal.get(i).id);
			model.addAttribute("rankTotal" + (i+1), arrRankTotal.get(i).score);
		}

		return "/ranking/ranking";
	}

}
