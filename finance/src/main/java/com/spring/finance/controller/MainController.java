package com.spring.finance.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.finance.util.LoginSessionTool;

@Controller
public class MainController {

	@GetMapping("/")
	public String main(HttpSession session, Model model) {
		System.out.println("main");
		
		LoginSessionTool.checkSession(session, model);
		
		return "/main/index";
	}

}