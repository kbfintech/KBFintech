package com.spring.finance.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.spring.finance.domain.KProductVO;
import com.spring.finance.util.LoginSessionTool;

@Controller
@RequestMapping("/product")
public class ProductController {

	// 적금
	String urlSaving = "http://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json?auth=863bf4eefd43f0892c8195120d849da0&topFinGrpNo=020000&pageNo=1";

	// 예금
	String urlDeposit = "http://finlife.fss.or.kr/finlifeapi/depositProductsSearch.json?auth=863bf4eefd43f0892c8195120d849da0&topFinGrpNo=020000&pageNo=1";

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String productMain(HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response1) {

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
		return "/product/list";
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(HttpSession session, Model model, @RequestParam("fin_prdt_cd") String fin_prdt_cd,
			HttpServletRequest request, HttpServletResponse response) {

		LoginSessionTool.checkSession(session, model, response);
		ArrayList<KProductVO> productListS;
		productListS = (ArrayList) session.getAttribute("productListS");

		
		ArrayList<KProductVO> productListD;
		productListD = (ArrayList) session.getAttribute("productListD");
		
		String[] spcl_cnd;
		for (int x = 0; x < productListS.size(); x++) {
			if (fin_prdt_cd.equals(productListS.get(x).getFin_prdt_cd())) {
				spcl_cnd = new String[productListS.get(x).getSpcl_cnd().length];
				spcl_cnd = productListS.get(x).getSpcl_cnd();
				model.addAttribute("infoList", spcl_cnd);
			}
		}
		
		for (int x = 0; x < productListD.size(); x++) {
			
			if (fin_prdt_cd.equals(productListD.get(x).getFin_prdt_cd())) {
				spcl_cnd = new String[productListD.get(x).getSpcl_cnd().length];
				spcl_cnd = productListD.get(x).getSpcl_cnd();
				model.addAttribute("infoList", spcl_cnd);
			}
		}

		return "/product/info";
	}

}