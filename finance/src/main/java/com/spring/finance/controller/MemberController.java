package com.spring.finance.controller;

import java.io.IOException;
import java.io.PrintWriter;

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

import com.spring.finance.domain.MemberVO;
import com.spring.finance.mapper.MemberMapper;
import com.spring.finance.util.LoginSessionTool;

@Controller
public class MemberController {
	
	@Autowired
	public SqlSession sqlsession;

	@RequestMapping("/member/login")
	public String login(HttpSession session, Model model) {
		System.out.println("로그인페이지");
		
		LoginSessionTool.checkSession(session, model);

		return "/member/login";
	}
	
	@RequestMapping(value = "/member/loginOk", method = RequestMethod.POST)
	public String loginOk(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
		
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		
		String returnUrl = "";
		
		String M_ID = request.getParameter("userId");
		String M_PW = request.getParameter("password");
		
		if ( session.getAttribute("login") != null ){
            // 기존에 login이란 세션 값이 존재한다면
            session.removeAttribute("login"); // 기존값을 제거해 준다.
        }
		
		MemberVO mVo = new MemberVO();
		mVo.setM_ID(M_ID);
		mVo.setM_PW(M_PW);
		MemberVO mv = mapper.login(mVo);
		
		if(null != mv) {
			session.setAttribute("id", mv.getM_ID());
			session.setAttribute("name", mv.getM_NAME());
			
			returnUrl = "redirect:/";
			// 쿠키 체크여부 검사(비밀번호 저장 여부검사)
			
			
//			response.sendRedirect(returnUrl);
		} else {
			// 로그인 실패
			returnUrl = "/member/login";
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인에 실패하셨습니다. 아이디와 비밀번호를 다시 확인해주세요.'); location.href='/finance/member/login'</script>");
			out.flush();
			out.close();
		}
		
		return returnUrl;
		
	}
	
	

	@RequestMapping("/member/register")
	public String register() {
		System.out.println("초기회원가입페이지");

		return "/member/register";

	}

	@RequestMapping(value = "/member/registerOk", method = RequestMethod.POST)
	public String registerOk(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("회원가입성공페이지");
		
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String userId = request.getParameter("userId");
		String phoneNum = request.getParameter("phoneNum");
		
		MemberVO mv = new MemberVO();
		mv.setM_ID(userId);
		mv.setM_NAME(name);
		mv.setM_PW(password);
		mv.setEMAIL(email);
		mv.setPHONE(phoneNum);
		mv.setM_POINT(0);
		mv.setOPENPAGE(0);
		mv.setPR_REG_IDX("0");
		
		mapper.insertMember(mv);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('회원가입에 성공하였습니다. 다시 로그인해주세요.'); location.href='/finance/member/login'</script>");
		out.flush();
		out.close();

		return "/member/login";

	}
	
	@RequestMapping(value="member/isValid")
	@ResponseBody
	public String isValid(HttpServletRequest request) {
		
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		
		String M_ID = request.getParameter("userId");
		
		String flag = mapper.selectUId(M_ID);
		
		System.out.println(flag);
		
		if(null != flag) {
			flag = "1";
		} else {
			flag = "0";
		}
		
		return flag;
		
	}

	@RequestMapping("member/forget")
	public void forget() {
//		log.info("/member/forget.................");
	}
}
