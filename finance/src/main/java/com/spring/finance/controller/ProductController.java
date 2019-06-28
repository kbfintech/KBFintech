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

	String urlFB = "http://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json?auth=863bf4eefd43f0892c8195120d849da0&topFinGrpNo=020000&pageNo=1";

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String productMain(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response1) {

		LoginSessionTool.checkSession(session, model, response1);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlFB);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				String.class);

		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(response.getBody().toString());
		JsonObject getBody = jsonElement.getAsJsonObject();
		JsonObject getResult = getBody.get("result").getAsJsonObject();
		JsonArray getBaseList = getResult.get("baseList").getAsJsonArray();

		ArrayList<JsonObject> jsonArrKb = new ArrayList<>();
		for (int i = 0; i < getBaseList.size(); i++) {
			JsonObject jsonObject = getBaseList.get(i).getAsJsonObject();
			String bankName = jsonObject.get("kor_co_nm").getAsString();
			if (bankName.equals("국민은행"))
				jsonArrKb.add(getBaseList.get(i).getAsJsonObject());
		}

		ArrayList<KProductVO> productList = new ArrayList<KProductVO>();
		ArrayList<String[]> spcl_cndList = new ArrayList<String[]>();
		KProductVO kp;
		
		for (int i = 0; i < jsonArrKb.size(); i++) {
			kp = new KProductVO();
			kp.setFin_prdt_cd(jsonArrKb.get(i).get("fin_prdt_cd").toString().substring(1,
					jsonArrKb.get(i).get("fin_prdt_cd").toString().length() - 1));
			kp.setFin_prdt_nm(jsonArrKb.get(i).get("fin_prdt_nm").toString().substring(1,
					jsonArrKb.get(i).get("fin_prdt_nm").toString().length() - 1));
			kp.setJoin_member(jsonArrKb.get(i).get("join_member").toString().substring(1,
					jsonArrKb.get(i).get("join_member").toString().length() - 1));
			kp.setMtrt_int(jsonArrKb.get(i).get("mtrt_int").toString().substring(1,
					jsonArrKb.get(i).get("mtrt_int").toString().length() - 1));
			String spcl_cnd = jsonArrKb.get(i).get("spcl_cnd").toString().substring(1,
					jsonArrKb.get(i).get("spcl_cnd").toString().length() - 1);

			String[] newOne = spcl_cnd.split("[\\\\r\\\\n]+");
			String[] newOne2 = new String[newOne.length - 1];
			for (int x = 1; x < newOne.length; x++) {
				newOne2[x - 1] = newOne[x];
			}
			kp.setBest_rate(newOne[0]);
			kp.setSpcl_cnd(newOne2);

			spcl_cndList.add(newOne2);
			productList.add(kp);
		}
		session.setAttribute("productList", productList);
		model.addAttribute("productList", productList);
		return "/product/list";
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(HttpSession session, Model model, @RequestParam("fin_prdt_cd") String fin_prdt_cd, HttpServletRequest request, HttpServletResponse response) {

		ArrayList<KProductVO> productList;
		productList = (ArrayList) session.getAttribute("productList");

		LoginSessionTool.checkSession(session, model, response);
		String[] spcl_cnd;
		for (int x = 0; x < productList.size(); x++) {
			if (fin_prdt_cd.equals(productList.get(x).getFin_prdt_cd())) {
				spcl_cnd = new String[productList.get(x).getSpcl_cnd().length];
				spcl_cnd = productList.get(x).getSpcl_cnd();
				model.addAttribute("infoList", spcl_cnd);
			}
		}
		
		
		return "/product/info";
	}

}