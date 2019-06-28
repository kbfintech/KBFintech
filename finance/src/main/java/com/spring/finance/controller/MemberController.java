package com.spring.finance.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.spring.finance.domain.CardVO;
import com.spring.finance.domain.MemberVO;
import com.spring.finance.domain.PaymentVO;
import com.spring.finance.domain.PlanVO;
import com.spring.finance.domain.ProductVO;
import com.spring.finance.mapper.MemberMapper;
import com.spring.finance.util.LoginSessionTool;

@Controller
public class MemberController {

	@Autowired
	public SqlSession sqlsession;

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping("/member/login")
	public String login(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인페이지");

		LoginSessionTool.checkOnlyNavBar(session, model);

		String success = "";

		try {
			success = request.getParameter("success");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		model.addAttribute("success", success);

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
			if (mv.getREGISTER_YN().equals("0")) {

				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('가입인증이 되지않은 계정입니다. 입력하신 이메일에서 가입인증 완료해주시기 바랍니다.'); location.href='/member/login'</script>");
				out.flush();
				out.close();

			} else {
				// 로그인 실패(디비에 계정이 없는 경우)
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('로그인에 실패하셨습니다. 아이디와 비밀번호를 다시 확인해주세요.'); location.href='/member/login'</script>");
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
		out.println("<script>alert('로그아웃되셨습니다.'); location.href='/'</script>");
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
				+ "<a href='http://localhost:8080/member/register_complete?id=" + mv.getM_ID()
				+ "' align: center>가입 완료</a>";

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			// SimpleMailMessage message = new SimpleMailMessage();

			messageHelper.setFrom(fromEmail, "월렛버핏"); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(email); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content, true); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(
				"<script>alert('가입인증메일 발송이 완료되었습니다. 입력하신 메일에서 인증 완료 후 로그인 하시기 바랍니다.'); location.href='/member/login'</script>");
		out.flush();
		out.close();

		return "/member/login";

	}

	@RequestMapping(value = "/member/isValid")
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

	@RequestMapping("member/memInfo")
	public String memInfo(HttpSession session, HttpServletRequest request, Model model, HttpServletResponse response) {

		LoginSessionTool.checkSession(session, model, response);

		String M_ID = (String) session.getAttribute("id");

		if (null != M_ID && !M_ID.equals("")) {

			Map<String, Object> param = new HashMap<String, Object>();

			NumberFormat formatter = new DecimalFormat("###,###,###");
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyM");
			SimpleDateFormat formatter3 = new SimpleDateFormat("yyyyMM");
			List<String> pld_price = new ArrayList();
			String[] s_price = { "", "", "", "", "", "", "", "", "" };
			double[] s_price_cal = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
			

			MemberVO memberVO = new MemberVO();
			CardVO cardVO = new CardVO();
			ProductVO productVO = new ProductVO();
			String dt_temp = "";
			String pl_id = "";
			String cardNo = "", cardCom = "", cardType = "";
			String prd_transfer = "";
			int pld_sum = 0;
			List<PlanVO> planVO;
			List<PaymentVO> paymentVO;

			MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);

			param.put("M_ID", M_ID);
			dt_temp = formatter.format(Calendar.getInstance().get(Calendar.MONTH) + 1);
			if (dt_temp.length() == 1) {
				param.put("PL_PERIOD", formatter2.format(new Date())); // 20196
			} else {
				param.put("PL_PERIOD", formatter3.format(new Date())); // 201910
			}
			pl_id = (String) param.get("PL_PERIOD");
			param.put("PL_ID", pl_id); // 20196
			param.put("P_DATE", pl_id.substring(0, 4) + "-" + pl_id.substring(4, 5)); // 2019-6

			// memberVO
			memberVO = mapper.getMemberObject(M_ID);

			// cardVO
			cardVO = mapper.getMCard(M_ID);
			if (cardVO.getC_ID() != null) {
				cardNo = cardVO.getC_ID().substring(0, 4) + '-' + cardVO.getC_ID().substring(4, 8) // null exception
						+ '-' + cardVO.getC_ID().substring(8, 12) + '-' + cardVO.getC_ID().substring(12, 16);
				cardType = (cardVO.getCARD_TYPE() == 0) ? "체크" : "신용";
				prd_transfer = formatter.format(productVO.getPRD_TRANSFER());

				switch (cardVO.getC_COMPANY()) {
				case "1":
				default:
					cardCom = "국민";
				}
			}

			// productVO
			productVO = mapper.getProduct(M_ID);

			planVO = mapper.getPlan(param);
			paymentVO = mapper.getPayment(param);

			if (paymentVO.size() != 0 && null != paymentVO) {

				for (int i = 0; i < paymentVO.size(); i++) {
					switch (paymentVO.get(i).getB_CD()) {
					case "552100":
						s_price_cal[0] = s_price_cal[0] + paymentVO.get(i).getPAY_PRICE();
						break;
					case "552101":
						s_price_cal[1] = s_price_cal[1] + paymentVO.get(i).getPAY_PRICE();
						break;
					case "552102":
						s_price_cal[2] = s_price_cal[2] + paymentVO.get(i).getPAY_PRICE();
						break;
					case "552103":
						s_price_cal[3] = s_price_cal[3] + paymentVO.get(i).getPAY_PRICE();
						break;
					case "552104":
						s_price_cal[4] = s_price_cal[4] + paymentVO.get(i).getPAY_PRICE();
						break;
					case "552105":
						s_price_cal[5] = s_price_cal[5] + paymentVO.get(i).getPAY_PRICE();
						break;
					case "552106":
						s_price_cal[6] = s_price_cal[6] + paymentVO.get(i).getPAY_PRICE();
						break;
					case "552107":
						s_price_cal[7] = s_price_cal[7] + paymentVO.get(i).getPAY_PRICE();
						break;
					}

				}
			}

			if (planVO.size() != 0 && !planVO.isEmpty()) {
				for (int i = 0; i < planVO.size(); i++) {

					pld_price.add(formatter.format(planVO.get(i).getPLD_PRICE()));
					pld_sum += planVO.get(i).getPLD_PRICE();
				}

				if (s_price_cal.length != 0) {
					for (int i = 0; i < s_price_cal.length - 1; i++) {
						s_price[i] = formatter.format(s_price_cal[i]);
						s_price_cal[8] += s_price_cal[i];
					}
					s_price[8] = formatter.format(s_price_cal[8]);
				}
				
			}

			model.addAttribute("memberVO", memberVO);
			model.addAttribute("cardVO", cardVO);
			model.addAttribute("productVO", productVO);
			model.addAttribute("prd_transfer", prd_transfer);
			model.addAttribute("cardNo", cardNo);
			model.addAttribute("cardCom", cardCom);
			model.addAttribute("cardType", cardType);
			model.addAttribute("planVO", planVO);
			model.addAttribute("pld_price", pld_price);
			model.addAttribute("pld_sum", formatter.format(pld_sum));
			model.addAttribute("s_price", s_price);
			model.addAttribute("paymentVO", paymentVO);

		}

		return "/member/memInfo";

	}

	@RequestMapping("member/forget")
	public String forget(HttpSession session, HttpServletRequest request, Model model) {

		LoginSessionTool.checkOnlyNavBar(session, model);

		String error = "";
		try {
			error = request.getParameter("Emailerror");
		} catch (Exception e) {
		}
		model.addAttribute("error", error);
		return "/member/forget";

	}

	@RequestMapping("member/passwordSearch")
	public String passwordSearch(HttpServletRequest request, Model model) {

		String inputEmail = request.getParameter("inputEmail");
		String M_ID = request.getParameter("mid");
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		String email = mapper.getEmail(M_ID);
		if (inputEmail.equals(email)) {
			String setfrom = "pranne1224@gmail.com";
			String tomail = request.getParameter("inputEmail"); // 받는 사람 이메일
			String title = "월렛버핏 : " + M_ID + "님의 비밀번호 확인 메일입니다. 비밀번호 확인 후 로그인해주세요."; // 제목
			String content = "비밀번호 : " + mapper.getPassword(M_ID); // 내용

			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				// SimpleMailMessage message = new SimpleMailMessage();

				messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
				messageHelper.setTo(tomail); // 받는사람 이메일
				messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
				messageHelper.setText(content); // 메일 내용

				mailSender.send(message);
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			String error = "해당 이메일이 존재하지 않습니다. 아이디와 이메일을 다시 확인하세요.";
			model.addAttribute("Emailerror", error);
			return "redirect:/member/forget";
		}
		String success = "이메일이 성공적으로 보내졌습니다. 이메일 확인 후 다시 로그인하세요.";
		model.addAttribute("success", success);
		return "redirect:/member/login";
	}

	@RequestMapping(value = "/member/register_complete", method = RequestMethod.GET)
	public String register_complete(@RequestParam String id, HttpSession session, Model model) {

		LoginSessionTool.checkOnlyNavBar(session, model);

		System.out.println("가입인증완료페이지");

		// 가입인증여부 플래그 업데이트
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		mapper.updateMember(id);

		return "/member/register_complete";

	}

}
