package com.spring.finance.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.finance.domain.CardVO;
import com.spring.finance.domain.KCardVO;
import com.spring.finance.mapper.CardMapper;
import com.spring.finance.mapper.KCardMapper;
import com.spring.finance.util.SHA256;

@Controller
public class CardController {

	@Autowired
	SqlSession sqlSession;

	@GetMapping("/card/register")
	public String main() {
		System.out.println("card register");
		return "/card/register";
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

		KCardVO KCardVO = new KCardVO(cardNum, cardMonth, cardYear, cardCVC, selectComp);
		KCardMapper KMapper = sqlSession.getMapper(KCardMapper.class);
		String K_HASH = KMapper.getCardInfo(KCardVO);

		if (K_HASH.equals(shaResult)) {
			// card 등록
//			CardMapper CMapper = sqlSession.getMapper(CardMapper.class);
//			CardVO CardVO= new CardVO(cardNum, cardMonth, cardYear, cardCVC, selectComp, shaResult, M_ID);
//			CMapper.regitCardInfo(CardVO);
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
