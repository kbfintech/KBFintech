package com.spring.finance.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.finance.domain.AccountVO;
import com.spring.finance.domain.CardVO;
import com.spring.finance.domain.KCardVO;
import com.spring.finance.mapper.AccountMapper;
import com.spring.finance.mapper.CardMapper;
import com.spring.finance.mapper.KAccountMapper;
import com.spring.finance.mapper.KCardMapper;
import com.spring.finance.util.SHA256;

@Controller
public class CardController {

	@Autowired
	SqlSession sqlSession;

	@RequestMapping(value = "/payRegister", method = RequestMethod.GET)
	public ModelAndView pay(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		AccountMapper AccountMapper = sqlSession.getMapper(AccountMapper.class);

		CardMapper CMapper = sqlSession.getMapper(CardMapper.class);

		try {

			// M_ID 받아와야 함.
			String ACCOUNT_NUMBER = AccountMapper.getAccountNum("ske02154");
			// PHONE 받아와야함/
			HttpSession session = request.getSession();
			session.setAttribute("M_ID", "ske02154");
			session.setAttribute("PHONE", "01087414813");

			System.out.println("ACCOUNT_NUMBER : " + ACCOUNT_NUMBER);
//			System.out.println("C_HASH: " + C_HASH);

			mv.setViewName("payRegister/register");
			// 계좌가 등록 되어있지 않으면 카드도 등록해야 함.
			if (ACCOUNT_NUMBER == null) {
				System.out.println("hi");
				mv.addObject("result", "allNeed");
				return mv;
			} else {
				// 카드가 등록 되어있지 않으면
				if (CMapper.isRegister("ske02154") == null) {
					System.out.println("hihi");
					mv.addObject("result", "card");
					return mv;
				} else {
					// 카드가 등록 되어있으면
					mv.addObject("result", "all");
					return mv;
				}
			}
		} catch (NullPointerException e) {

		}

		return mv;
	}

	// AJAX 사용시 Map<String, String> 만 가능
	@ResponseBody
	@RequestMapping(value = "/account/inquery", method = RequestMethod.POST)
	public Map<String, String> accountRegit(HttpServletRequest request, HttpServletResponse response) {

		// 등록 성공하면
		HttpSession session = request.getSession();
		String M_ID = (String) session.getAttribute("M_ID");

		KAccountMapper KAccountMapper = sqlSession.getMapper(KAccountMapper.class);
		AccountMapper AccountMapper = sqlSession.getMapper(AccountMapper.class);

		Map<String, String> m = new HashMap<String, String>();
		String accountName = request.getParameter("accountName");
		String accountNum = request.getParameter("accountNum");
		System.out.println("accountNum: " + accountNum);
		String phone = (String) session.getAttribute("PHONE");
		String accountMoney = KAccountMapper.getAccountMoney(accountNum);

		AccountVO accountVO = new AccountVO(M_ID, accountName, accountNum, phone, Integer.parseInt(accountMoney));
		int cnt = AccountMapper.regitAccount(accountVO);

		// account 등록 실패
		if (cnt == 0) {
			m.put("resultCode", "0");
		} else {
			m.put("resultCode", "1");
		}

		return m;
	}

	// M_ID 넘겨 받아야함.
	// ajax 호출시 붙여야 하는 어노테이션
	@ResponseBody
	@RequestMapping(value = "/card/inquery", method = RequestMethod.POST)
	public Map<String, String> inquery(HttpServletRequest request) {

		Map<String, String> m = new HashMap<String, String>();
		String cardNum = request.getParameter("cardNum");
		String cardMonth = request.getParameter("cardMonth");
		String cardYear = request.getParameter("cardYear");
		String cardCVC = request.getParameter("cardCVC");
		String selectComp = request.getParameter("selectComp");

		SHA256 sha = new SHA256();
		String shaResult = sha.testSHA256(cardNum + cardMonth + cardYear + cardCVC + selectComp);

		// kb 카드 해쉬 정보 가져오기
		KCardMapper KMapper = sqlSession.getMapper(KCardMapper.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("K_ID", cardNum);
		map.put("K_MM", cardMonth);
		map.put("K_YY", cardYear);
		map.put("K_CVC", cardCVC);
		System.out.println("before regist");
		System.out.println("cardNum : " + cardNum);
		System.out.println("cardMonth : " + cardMonth);
		System.out.println("cardYear : " + cardYear);
		System.out.println("cardCVC : " + cardCVC);

		String K_HASH = KMapper.getCardHash(map);
		System.out.println("K_HASH : " + K_HASH);
		ModelAndView mv = new ModelAndView();
		if (K_HASH.equals(shaResult)) {
			// card 등록
			// M_ID 받아오기
			HttpSession session = request.getSession();
			String M_ID = (String) session.getAttribute("M_ID");
			CardMapper CMapper = sqlSession.getMapper(CardMapper.class);
			AccountMapper AccountMapper = sqlSession.getMapper(AccountMapper.class);
			// 계좌번호랑 카드 타입 가져와야함.
			String ACCOUNT_NUMBER = AccountMapper.getAccountNum(M_ID);
			Map<String, String> map2 = new HashMap();
			map2.put("K_ID", cardNum);
			String CARD_TYPE = KMapper.getCardType(map2);
			System.out.println("before regist2");
			CardVO CardVO = new CardVO(cardNum, cardMonth, cardYear, cardCVC, selectComp, shaResult, M_ID,
					Integer.parseInt(CARD_TYPE), ACCOUNT_NUMBER);
			CMapper.regitCardInfo(CardVO);
			m.put("resultCode", "1");
		} else {
			m.put("resultCode", "0");
		}

		return m;
	}

	// 내 정보 관리 > 카드 관리 처리 의문?
//	@RequestMapping(value="/card/mycard", method = RequestMethod.POST)
//	public String mycard(){
//		
//		return "";
//	}
}
