package com.spring.finance.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public class LoginSessionTool {
	
	public static void checkSession(HttpSession session, Model model, HttpServletResponse response) {
		
		if(session.getAttribute("id") == null) {
			model.addAttribute("loginFlag", "0");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('로그인을 먼저 해주시기 바랍니다.'); location.href='/finance/member/login'</script>");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("로그인 필수로직 에러: "+e.getMessage());
			}
		} else {
			model.addAttribute("loginFlag", "1");
		}
		
	}
	
	public static void checkOnlyNavBar(HttpSession session, Model model) {
		
		if(session.getAttribute("id") == null) {
			model.addAttribute("loginFlag", "0");
		} else {
			model.addAttribute("loginFlag", "1");
		}
		
	}

}
