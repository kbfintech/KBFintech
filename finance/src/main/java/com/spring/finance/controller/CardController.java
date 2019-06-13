package com.spring.finance.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.finance.domain.KCardVO;
import com.spring.finance.mapper.CardMapper;
import com.spring.finance.util.SHA256;

@Controller
@Configuration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

public class CardController {

	@Autowired
	CardMapper CMapper;

	@GetMapping("/card/register")
	public String main() {
		System.out.println("card register");
		return "/card/register";
	}

	//C_ID 넘겨 받아야함.
	
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

		
		/**
		 * db
		 */
//		KCardVO KCardVO = new KCardVO( cardNum, cardMonth, cardYear, cardCVC, selectComp);
//		CMapper.getCardInfo();
		m.put("sha", shaResult);
		System.out.println("in");
		if (true) {
			m.put("resultCode", "1");
		} else {
			m.put("resultCode", "0");
		}

		return m;
	}
}
