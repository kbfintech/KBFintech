package com.spring.finance.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.finance.domain.MemberVO;
import com.spring.finance.mapper.MemberMapper;
import com.spring.finance.util.LoginSessionTool;

@Controller
public class MemberController {

	@Autowired
	public SqlSession sqlsession;

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping("/member/login")
	public String login(HttpSession session, Model model, HttpServletResponse response) {
		System.out.println("로그인페이지");

		LoginSessionTool.checkOnlyNavBar(session, model);

		return "/member/login";
	}

	@RequestMapping(value = "/member/loginOk", method = RequestMethod.POST)
	public String loginOk(HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws IOException {

		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);

		String returnUrl = "";

		String M_ID = request.getParameter("userId");
		String M_PW = request.getParameter("password");

		if (session.getAttribute("login") != null) {
			// 기존에 login 세션 값이 존재한다면 제거하기
			session.removeAttribute("id");
			session.removeAttribute("name");
			session.removeAttribute("phone");
			session.removeAttribute("login");
		}

		MemberVO mVo = new MemberVO();
		mVo.setM_ID(M_ID);
		mVo.setM_PW(M_PW);
		MemberVO mv = mapper.login(mVo);

		if (null != mv && mv.getREGISTER_YN().equals("1")) {
			session.setAttribute("id", mv.getM_ID());
			session.setAttribute("name", mv.getM_NAME());
			session.setAttribute("phone", mv.getPHONE());
			session.setAttribute("login", mv);

			returnUrl = "redirect:/";

//			response.sendRedirect(returnUrl);
		} else {
			
			returnUrl = "/member/login";
			// 로그인 실패(가입인증메일 미인증 계정의 경우)
			if(mv.getREGISTER_YN().equals("0")) {
				
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('가입인증이 되지않은 계정입니다. 입력하신 이메일에서 가입인증 완료해주시기 바랍니다.'); location.href='/finance/member/login'</script>");
				out.flush();
				out.close();
				
			} else {
				// 로그인 실패(디비에 계정이 없는 경우)
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('로그인에 실패하셨습니다. 아이디와 비밀번호를 다시 확인해주세요.'); location.href='/finance/member/login'</script>");
				out.flush();
				out.close();
			}
			
		}

		return returnUrl;

	}

	@RequestMapping("/member/logout")
	public void logout(HttpSession session, HttpServletResponse response) throws IOException {

		System.out.println("로그아웃페이지");
		session.invalidate();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('로그아웃되셨습니다.'); location.href='/finance'</script>");
		out.flush();
		out.close();

	}

	@RequestMapping("/member/register")
	public String register(HttpSession session, Model model, HttpServletResponse response) {
		System.out.println("초기회원가입페이지");

		LoginSessionTool.checkOnlyNavBar(session, model);

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
		mv.setREGISTER_YN("0");

		mapper.insertMember(mv);

		// 이메일 인증로직 추가하기
		String fromEmail = "pranne1224@gmail.com";
		String title = "월렛버핏 : 가입확인 메일입니다. 인증 후 가입완료해주시기 바랍니다.";
		String content = "<h1 text-align: center>가입인증메일</h1><br><br>"
				+ "<p text-align: center>하단 링크를 누르셔서 가입완료 하신 후 로그인 하셔서 서비스 이용하시기 바랍니다.</p><br><br><br>"
				+ "<a href='http://localhost:8080/finance/member/register_complete?id="+ mv.getM_ID() +"' align: center>가입 완료</a>";

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			// SimpleMailMessage message = new SimpleMailMessage();

			messageHelper.setFrom(fromEmail, "월렛버핏"); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(email); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(
				"<script>alert('가입인증메일 발송이 완료되었습니다. 입력하신 메일에서 인증 완료 후 로그인 하시기 바랍니다.'); location.href='/finance/member/login'</script>");
		out.flush();
		out.close();

		return "/member/login";

	}

	@RequestMapping(value = "member/isValid")
	@ResponseBody
	public String isValid(HttpServletRequest request) {

		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);

		String M_ID = request.getParameter("userId");

		String flag = mapper.selectUId(M_ID);

		System.out.println(flag);

		if (null != flag) {
			flag = "1";
		} else {
			flag = "0";
		}

		return flag;

	}

	@RequestMapping("member/forget")
	public void forget() {
		System.out.println("비밀번호 찾기 페이지");
	}

	@RequestMapping(value="member/register_complete", method=RequestMethod.GET)
	public String register_complete(@RequestParam String id, HttpSession session, Model model) {
		
		LoginSessionTool.checkOnlyNavBar(session, model);
		
		System.out.println("가입인증완료페이지");

		// 가입인증여부 플래그 업데이트
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		mapper.updateMember(id);
		
		return "/member/register_complete";

	}

}
