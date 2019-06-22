package com.spring.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RankingController {
	
	@GetMapping("/ranking/showRank")
	public String showRank() {
		System.out.println("랭킹확인페이지");
		return "/ranking/ranking";
	}
	

}
