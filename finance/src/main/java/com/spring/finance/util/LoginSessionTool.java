package com.spring.finance.util;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public class LoginSessionTool {
	
	public static void checkSession(HttpSession session, Model model) {
		
		if(session.getAttribute("id") == null) {
			model.addAttribute("loginFlag", "0");
		} else {
			model.addAttribute("loginFlag", "1");
		}
		
	}

}
