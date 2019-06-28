package com.spring.finance.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.finance.domain.AccountVO;
import com.spring.finance.domain.CardVO;
import com.spring.finance.mapper.AccountMapper;
import com.spring.finance.mapper.CardMapper;
import com.spring.finance.mapper.KAccountMapper;
import com.spring.finance.mapper.KCardMapper;
import com.spring.finance.util.LoginSessionTool;
import com.spring.finance.util.SHA256;

@Controller
public class CardController {

	@Autowired
	SqlSession sqlSession;

	@RequestMapping(value = "/payRegister", method = RequestMethod.GET)
	public ModelAndView pay(HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		ModelAndView mv = new ModelAndView();

		AccountMapper AccountMapper = sqlSession.getMapper(AccountMapper.class);

		CardMapper CMapper = sqlSession.getMapper(CardMapper.class);

		try {

			String M_ID = (String) session.getAttribute("id");
			
			if(null == M_ID) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인을 먼저 해주시기 바랍니다.'); location.href='/member/login'</script>");
				out.flush();
				out.close();
				mv.setViewName("/member/login");
				return mv;
			}

			// M_ID 받아와야 함.
			String ACCOUNT_NUMBER = AccountMapper.getAccountNum(M_ID);
			mv.setViewName("payRegister/register");

			// 계좌가 등록 되어있지 않으면 카드도 등록해야 함.
			if (ACCOUNT_NUMBER == null) {

				// 모두 등록되어있지 않으면
				if (CMapper.isRegister(M_ID) == null) {
					mv.addObject("result", "allNeed");
					return mv;
				} else {
					// 계좌는 등록되어있지 않은데 카드가 등록 되어있으면
					mv.addObject("result", "account");
					return mv;
				}
			} else {// 계좌가 등록 되어있는 경우

				// 카드가 등록 되어있지 않으면
				if (CMapper.isRegister(M_ID) == null) {
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

	// 계좌 등록
	// AJAX 사용시 Map<String, String> 만 가능
	@ResponseBody
	@RequestMapping(value = "/account/inquery", method = RequestMethod.POST)
	public Map<String, String> accountInquery(HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		// 등록 성공하면
		String M_ID = (String) session.getAttribute("id");

		KAccountMapper KAccountMapper = sqlSession.getMapper(KAccountMapper.class);
		AccountMapper AccountMapper = sqlSession.getMapper(AccountMapper.class);

		Map<String, String> m = new HashMap<String, String>();
		String accountName = request.getParameter("accountName");
		String accountNum = request.getParameter("accountNum");
		String phone = (String) session.getAttribute("phone");
		int accountMoney = KAccountMapper.getAccountMoney(accountNum);
		
		LoginSessionTool.checkSession(session, model, response);

		AccountVO accountVO = new AccountVO(M_ID, accountName, accountNum, phone, accountMoney);
		int cnt = AccountMapper.regitAccount(accountVO);

		// account 등록 실패
		if (cnt == 0) {
			m.put("resultCode", "0");
		} else {
			m.put("resultCode", "1");
		}

		return m;
	}

	// 카드 등록
	// ajax 호출시 붙여야 하는 어노테이션
	@ResponseBody
	@RequestMapping(value = "/card/inquery", method = RequestMethod.POST)
	public Map<String, String> cardInquery(HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, String> m = new HashMap<String, String>();
		String cardNum = request.getParameter("cardNum");
		String cardMonth = request.getParameter("cardMonth");
		String cardYear = request.getParameter("cardYear");
		String cardCVC = request.getParameter("cardCVC");
		String selectComp = request.getParameter("selectComp");

		String M_ID = (String) session.getAttribute("id");
		SHA256 sha = new SHA256();
		String shaResult = sha.testSHA256(cardNum + cardMonth + cardYear + cardCVC + selectComp);

		LoginSessionTool.checkSession(session, model, response);

		// kb 카드 해쉬 정보 가져오기
		KCardMapper KMapper = sqlSession.getMapper(KCardMapper.class);
		CardMapper CMapper = sqlSession.getMapper(CardMapper.class);		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("K_ID", cardNum);
		map.put("K_MM", cardMonth);
		map.put("K_YY", cardYear);
		map.put("K_CVC", cardCVC);

		String K_HASH = KMapper.getCardHash(map);
		if (K_HASH.equals(shaResult) && null == CMapper.isRegister(M_ID) ) {
			// card 등록
			AccountMapper AccountMapper = sqlSession.getMapper(AccountMapper.class);
			// 계좌번호랑 카드 타입 가져와야함.
			String ACCOUNT_NUMBER = AccountMapper.getAccountNum(M_ID);
			Map<String, String> map2 = new HashMap();
			map2.put("K_ID", cardNum);
			String CARD_TYPE = KMapper.getCardType(map2);
			CardVO CardVO = new CardVO(cardNum, cardMonth, cardYear, cardCVC, selectComp, shaResult, M_ID,
					Integer.parseInt(CARD_TYPE), ACCOUNT_NUMBER);
			CMapper.regitCardInfo(CardVO);
			m.put("resultCode", "1");
		} else {
			m.put("resultCode", "0");
		}

		return m;
	}

	// 카드 수정
	// ajax 호출시 붙여야 하는 어노테이션
	@ResponseBody
	@RequestMapping(value = "/card/update", method = RequestMethod.POST)
	public Map<String, String> cardUpdate(HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, String> m = new HashMap<String, String>();
		String cardNum = request.getParameter("cardNum");
		String cardMonth = request.getParameter("cardMonth");
		String cardYear = request.getParameter("cardYear");
		String cardCVC = request.getParameter("cardCVC");
		String selectComp = request.getParameter("selectComp");

		SHA256 sha = new SHA256();
		String shaResult = sha.testSHA256(cardNum + cardMonth + cardYear + cardCVC + selectComp);

		LoginSessionTool.checkSession(session, model, response);

		// kb 카드 해쉬 정보 가져오기
		KCardMapper KMapper = sqlSession.getMapper(KCardMapper.class);

		Map<String, String> map = new HashMap<String, String>();
		map.put("K_ID", cardNum);
		map.put("K_MM", cardMonth);
		map.put("K_YY", cardYear);
		map.put("K_CVC", cardCVC);
		String K_HASH = KMapper.getCardHash(map);

		String M_ID = (String) session.getAttribute("id");

		// 카드 수정
		if (K_HASH.equals(shaResult)) {
			// card 등록
			// M_ID 받아오기
			CardMapper CMapper = sqlSession.getMapper(CardMapper.class);
			AccountMapper AccountMapper = sqlSession.getMapper(AccountMapper.class);

			// 계좌번호랑 카드 타입 가져와야함.
			String ACCOUNT_NUMBER = AccountMapper.getAccountNum(M_ID);
			Map<String, String> map2 = new HashMap();
			map2.put("K_ID", cardNum);
			String CARD_TYPE = KMapper.getCardType(map2);

			CardVO CardVO = new CardVO(cardNum, cardMonth, cardYear, cardCVC, selectComp, shaResult, M_ID,
					Integer.parseInt(CARD_TYPE), ACCOUNT_NUMBER);
			CMapper.updateCard(CardVO);

			m.put("resultCode", "1");
		} else {
			m.put("resultCode", "0");
		}

		return m;
	}

	// 계좌 수정
	// ajax 호출시 붙여야 하는 어노테이션
	@ResponseBody
	@RequestMapping(value = "/account/update", method = RequestMethod.POST)
	public Map<String, String> accountUpdate(HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		String M_ID = (String) session.getAttribute("id");
		String phone = (String) session.getAttribute("phone");

		KAccountMapper KAccountMapper = sqlSession.getMapper(KAccountMapper.class);
		AccountMapper AccountMapper = sqlSession.getMapper(AccountMapper.class);

		Map<String, String> m = new HashMap<String, String>();
		String accountName = request.getParameter("accountName");
		String accountNum = request.getParameter("accountNum");
		
		String KAccount_num = KAccountMapper.isRegister(phone);
		int accountMoney = KAccountMapper.getAccountMoney(KAccount_num);

		LoginSessionTool.checkSession(session, model, response);

		AccountVO accountVO = new AccountVO(M_ID, accountName, accountNum, phone, accountMoney);

		if (null != KAccount_num ) {
			AccountMapper.updateAccount(accountVO);
			m.put("resultCode", "1");
		} else {
			m.put("resultCode", "0");
		}

		return m;

	}
}
