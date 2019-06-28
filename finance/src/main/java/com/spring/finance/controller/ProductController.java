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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.finance.domain.AccountVO;
import com.spring.finance.domain.KProductVO;
import com.spring.finance.domain.ProductVO;
import com.spring.finance.mapper.AccountMapper;
import com.spring.finance.mapper.CardMapper;
import com.spring.finance.mapper.ProductMapper;
import com.spring.finance.util.LoginSessionTool;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	public SqlSession sqlsession;

	// 적금
	String urlSaving = "http://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json?auth=863bf4eefd43f0892c8195120d849da0&topFinGrpNo=020000&pageNo=1";

	// 예금
	String urlDeposit = "http://finlife.fss.or.kr/finlifeapi/depositProductsSearch.json?auth=863bf4eefd43f0892c8195120d849da0&topFinGrpNo=020000&pageNo=1";

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String productMain(HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response1) throws IOException {

		String M_ID = (String) session.getAttribute("id");

		// 카드와 계좌를 등록했을 때만 진입 가능하게
		AccountMapper accountMapper = sqlsession.getMapper(AccountMapper.class);
		CardMapper cardMapper = sqlsession.getMapper(CardMapper.class);

		if (null != accountMapper.getAccountNum(M_ID) && null != cardMapper.isRegister(M_ID)) {
			ProductMapper mapper = sqlsession.getMapper(ProductMapper.class);

			if (null == M_ID) {
				response1.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response1.getWriter();
				out.println("<script>alert('로그인을 먼저 해주시기 바랍니다.'); location.href='/member/login'</script>");
				out.flush();
				out.close();
				return "/member/login";
			}

			String prd_nm = mapper.getProduct(M_ID);

			if (prd_nm != null) {
				model.addAttribute("prd_nm", prd_nm);
				response1.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response1.getWriter();
				out.println("<script>alert('이미 가입한 상품이 있습니다.'); location.href='/main/index'</script>");
				out.flush();
				out.close();
				return "/main/index";
			} else {

				LoginSessionTool.checkSession(session, model, response1);
				HttpHeaders headersS = new HttpHeaders();
				HttpEntity<?> entityS = new HttpEntity<>(headersS);
				UriComponentsBuilder builderS = UriComponentsBuilder.fromHttpUrl(urlSaving);
				RestTemplate restTemplateS = new RestTemplate();
				HttpEntity<String> responseS = restTemplateS.exchange(builderS.toUriString(), HttpMethod.GET, entityS,
						String.class);

				HttpHeaders headersD = new HttpHeaders();
				HttpEntity<?> entityD = new HttpEntity<>(headersD);
				UriComponentsBuilder builderD = UriComponentsBuilder.fromHttpUrl(urlDeposit);
				RestTemplate restTemplateD = new RestTemplate();
				HttpEntity<String> responseD = restTemplateD.exchange(builderD.toUriString(), HttpMethod.GET, entityD,
						String.class);

				JsonParser jsonParserS = new JsonParser();
				JsonElement jsonElementS = jsonParserS.parse(responseS.getBody().toString());
				JsonObject getBodyS = jsonElementS.getAsJsonObject();
				JsonObject getResultS = getBodyS.get("result").getAsJsonObject();
				JsonArray getBaseListS = getResultS.get("baseList").getAsJsonArray();

				JsonParser jsonParserD = new JsonParser();
				JsonElement jsonElementD = jsonParserD.parse(responseD.getBody().toString());
				JsonObject getBodyD = jsonElementD.getAsJsonObject();
				JsonObject getResultD = getBodyD.get("result").getAsJsonObject();
				JsonArray getBaseListD = getResultD.get("baseList").getAsJsonArray();

				// 예금
				ArrayList<JsonObject> jsonArrKbD = new ArrayList<>();
				for (int i = 0; i < getBaseListD.size(); i++) {
					JsonObject jsonObject = getBaseListD.get(i).getAsJsonObject();
					String bankName = jsonObject.get("kor_co_nm").getAsString();
					if (bankName.equals("국민은행"))
						jsonArrKbD.add(getBaseListD.get(i).getAsJsonObject());
				}

				ArrayList<KProductVO> productListD = new ArrayList<KProductVO>();
				ArrayList<String[]> spcl_cndListD = new ArrayList<String[]>();
				KProductVO kpD;

				for (int i = 0; i < jsonArrKbD.size(); i++) {
					kpD = new KProductVO();
					kpD.setFin_prdt_cd(jsonArrKbD.get(i).get("fin_prdt_cd").toString().substring(1,
							jsonArrKbD.get(i).get("fin_prdt_cd").toString().length() - 1));
					kpD.setFin_prdt_nm(jsonArrKbD.get(i).get("fin_prdt_nm").toString().substring(1,
							jsonArrKbD.get(i).get("fin_prdt_nm").toString().length() - 1));
					kpD.setJoin_member(jsonArrKbD.get(i).get("join_member").toString().substring(1,
							jsonArrKbD.get(i).get("join_member").toString().length() - 1));
					kpD.setMtrt_int(jsonArrKbD.get(i).get("mtrt_int").toString().substring(1,
							jsonArrKbD.get(i).get("mtrt_int").toString().length() - 1));
					String spcl_cnd = jsonArrKbD.get(i).get("spcl_cnd").toString().substring(1,
							jsonArrKbD.get(i).get("spcl_cnd").toString().length() - 1);

					String[] newOne = spcl_cnd.split("[\\\\r\\\\n]+");
					String[] newOne2 = new String[newOne.length - 1];
					for (int x = 1; x < newOne.length; x++) {
						newOne2[x - 1] = newOne[x];
					}
					kpD.setBest_rate(newOne[0]);
					kpD.setSpcl_cnd(newOne2);

					spcl_cndListD.add(newOne2);
					productListD.add(kpD);
				}
				session.setAttribute("productListD", productListD);
				model.addAttribute("productListD", productListD);

				// 적금
				ArrayList<JsonObject> jsonArrKbS = new ArrayList<>();
				for (int i = 0; i < getBaseListS.size(); i++) {
					JsonObject jsonObjectS = getBaseListS.get(i).getAsJsonObject();
					String bankName = jsonObjectS.get("kor_co_nm").getAsString();
					if (bankName.equals("국민은행"))
						jsonArrKbS.add(getBaseListS.get(i).getAsJsonObject());
				}

				ArrayList<KProductVO> productListS = new ArrayList<KProductVO>();
				ArrayList<String[]> spcl_cndListS = new ArrayList<String[]>();
				KProductVO kpS;

				for (int i = 0; i < jsonArrKbS.size(); i++) {
					kpS = new KProductVO();
					kpS.setFin_prdt_cd(jsonArrKbS.get(i).get("fin_prdt_cd").toString().substring(1,
							jsonArrKbS.get(i).get("fin_prdt_cd").toString().length() - 1));
					kpS.setFin_prdt_nm(jsonArrKbS.get(i).get("fin_prdt_nm").toString().substring(1,
							jsonArrKbS.get(i).get("fin_prdt_nm").toString().length() - 1));
					kpS.setJoin_member(jsonArrKbS.get(i).get("join_member").toString().substring(1,
							jsonArrKbS.get(i).get("join_member").toString().length() - 1));
					kpS.setMtrt_int(jsonArrKbS.get(i).get("mtrt_int").toString().substring(1,
							jsonArrKbS.get(i).get("mtrt_int").toString().length() - 1));
					String spcl_cnd = jsonArrKbS.get(i).get("spcl_cnd").toString().substring(1,
							jsonArrKbS.get(i).get("spcl_cnd").toString().length() - 1);

					String[] newOne = spcl_cnd.split("[\\\\r\\\\n]+");
					String[] newOne2 = new String[newOne.length - 1];
					for (int x = 1; x < newOne.length; x++) {
						newOne2[x - 1] = newOne[x];
					}
					kpS.setBest_rate(newOne[0]);
					kpS.setSpcl_cnd(newOne2);

					spcl_cndListS.add(newOne2);
					productListS.add(kpS);
				}
				session.setAttribute("productListS", productListS);
				model.addAttribute("productListS", productListS);
				model.addAttribute("prd_nm", prd_nm);
				return "/product/list";

			}
		} else {
			if (null == accountMapper.getAccountNum(M_ID)) {
				model.addAttribute("status", "account");
			}
			if (null == cardMapper.isRegister(M_ID)) {
				model.addAttribute("status", "card");
			}
			
			if(null == accountMapper.getAccountNum(M_ID) && null == cardMapper.isRegister(M_ID)) {
				model.addAttribute("status", "allRegist");
			}
			return "/payRegister/register";
		}

	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(@RequestParam("fin_prdt_cd") String fin_prdt_cd, @RequestParam("fin_prdt_nm") String fin_prdt_nm,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();

		LoginSessionTool.checkOnlyNavBar(session, model);

		String M_ID = (String) session.getAttribute("id");
		session.setAttribute("fin_prdt_cd", fin_prdt_cd);
		session.setAttribute("fin_prdt_nm", fin_prdt_nm);

		ArrayList<KProductVO> productListS;
		productListS = (ArrayList) session.getAttribute("productListS");

		ArrayList<KProductVO> productListD;
		productListD = (ArrayList) session.getAttribute("productListD");

		String[] spcl_cnd;

		if (productListS.size() != 0) {
			for (int x = 0; x < productListS.size(); x++) {
				if (fin_prdt_cd.equals(productListS.get(x).getFin_prdt_cd())) {
					spcl_cnd = new String[productListS.get(x).getSpcl_cnd().length];
					spcl_cnd = productListS.get(x).getSpcl_cnd();
					model.addAttribute("infoList", spcl_cnd);
				}
			}
		}

		if (productListD.size() != 0) {
			for (int x = 0; x < productListD.size(); x++) {
				if (fin_prdt_cd.equals(productListD.get(x).getFin_prdt_cd())) {
					spcl_cnd = new String[productListD.get(x).getSpcl_cnd().length];
					spcl_cnd = productListD.get(x).getSpcl_cnd();
					model.addAttribute("infoList", spcl_cnd);
				}
			}
		}

		ProductMapper mapper = sqlsession.getMapper(ProductMapper.class);

		NumberFormat formatter = new DecimalFormat("###,###,###");
		String acc_money;

		AccountVO accountVO = new AccountVO();
		accountVO = mapper.getAccount(M_ID);
		acc_money = formatter.format(accountVO.getACCOUNT_MONEY());

		model.addAttribute("accountVO", accountVO);
		model.addAttribute("acc_money", acc_money);
		model.addAttribute("accountVO_money", accountVO.getACCOUNT_MONEY());
		model.addAttribute("fin_prdt_nm", session.getAttribute("fin_prdt_nm"));
		model.addAttribute("best_rate", session.getAttribute("best_rate"));

		return "/product/info";
	}

	@RequestMapping(value = "/point", method = RequestMethod.POST)
	public String point(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String M_ID = (String) session.getAttribute("id");

		String money = request.getParameter("money");
		String prd_name = (String) session.getAttribute("fin_prdt_nm");
		String dt = "";

		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyM");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMM");

		String dt_temp = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		if (dt_temp.length() == 1) {
			dt = formatter1.format(new Date()); // 20196
		} else {
			dt = formatter2.format(new Date()); // 201910
		}

		ProductMapper mapper = sqlsession.getMapper(ProductMapper.class);

		AccountVO accountVO = mapper.getAccount(M_ID);

		ProductVO productVO = new ProductVO(M_ID, (String) session.getAttribute("fin_prdt_cd"), prd_name, 0.0, prd_name,
				accountVO.getACCOUNT_NUMBER(), dt);
		productVO.setPRD_TRANSFER(Double.parseDouble(money.replaceAll(",", "")));
		System.out.println("ddd");

		int money_tsf = Integer.parseInt(money.replaceAll(",", ""));
		Map<String, Object> prd_param = new HashMap<String, Object>();
		prd_param.put("MONEY_TSF", money_tsf);
		prd_param.put("M_ID", M_ID);

		// 상품 테이블 업데이트
		mapper.insertProduct(productVO);

		// ACCOUNT에서 빼고
		mapper.updateAccount(prd_param);
		System.out.println("updateAccount : 완료");

		if (money_tsf > 10000) {
			mapper.updatePoint(M_ID);
			System.out.println("M_ID : " + M_ID);
		}

		return "/main/index";
	}

}