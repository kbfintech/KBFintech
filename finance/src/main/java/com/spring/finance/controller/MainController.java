package com.spring.finance.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.finance.domain.AccountVO;
import com.spring.finance.domain.KProductVO;
import com.spring.finance.domain.MemberVO;
import com.spring.finance.domain.PaymentList;
import com.spring.finance.domain.PaymentVO;
import com.spring.finance.domain.TransferVO;
import com.spring.finance.mapper.AccountMapper;
import com.spring.finance.mapper.MemberMapper;
import com.spring.finance.mapper.PlanMapper;
import com.spring.finance.mapper.ProductMapper;
import com.spring.finance.util.LoginSessionTool;

@Controller
@Component
public class MainController {

	@Autowired
	public SqlSession sqlsession;
	
	// 적금
	String urlSaving = "http://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json?auth=863bf4eefd43f0892c8195120d849da0&topFinGrpNo=020000&pageNo=1";
	
	//public String M_ID = "luok377";

	@GetMapping("/")
	public String main(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response1) {
		String M_ID = (String) session.getAttribute("id");
		System.out.println("main");
		LoginSessionTool.checkOnlyNavBar(session, model);
		if(M_ID != null) {
			PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
			// 전체 한도 설정에 대해서 설정한 한도보다 전체 사용 금액이 크면
			// 플랜 성공 여부를 0(실패)로 설정한다.
			ArrayList<String> checkdateList = mapper.dateList(M_ID);
			for (int i = 0; i < checkdateList.size(); i++) {
				ArrayList<Integer> planPrice = new ArrayList<Integer>();
				String PL_ID = checkdateList.get(i);
				HashMap<String, String> hmap = new HashMap<String, String>();
				hmap.put("PL_ID", PL_ID);
				hmap.put("M_ID", M_ID);
				planPrice = mapper.getPlanPrice(hmap);
	
				int totalPrice = 0;
	
				for (int j = 0; j < planPrice.size(); j++) {
					totalPrice += planPrice.get(j);
				}
	
				PaymentList paymentList = new PaymentList();
				paymentList.setPaymentList(mapper.selectPaymentList(M_ID));
				ArrayList<PaymentVO> list = new ArrayList<PaymentVO>();
				for (int j = 0; j < paymentList.getPaymentList().size(); j++) {
					if (paymentList.getPaymentList().get(j).getPAY_DATE().length() == 8) {
						String delete = paymentList.getPaymentList().get(j).getPAY_DATE().substring(6, 8);
						String newdate = paymentList.getPaymentList().get(j).getPAY_DATE().replace(delete, "");
						String finaldate = newdate.replace("-", "");
						if (finaldate.equals(PL_ID)) {
							list.add(paymentList.getPaymentList().get(j));
						}
					} else if (paymentList.getPaymentList().get(j).getPAY_DATE().length() == 9) {
						String delete = paymentList.getPaymentList().get(j).getPAY_DATE().substring(6, 9);
						String newdate = paymentList.getPaymentList().get(j).getPAY_DATE().replace(delete, "");
						String finaldate = newdate.replace("-", "");
						if (finaldate.equals(PL_ID)) {
							list.add(paymentList.getPaymentList().get(j));
						}
					}
				}
				int useTotalPrice = 0;
	
				for (int j = 0; j < list.size(); j++) {
					useTotalPrice += list.get(j).getPAY_PRICE();
				}
	
				if (totalPrice < useTotalPrice) {
					mapper.updatePlanFail(hmap);
				}
			}
	
			// 매월 마지막날, 한도 > 사용 금액보다 큰 사용자에 한해서 계좌에 잔액이 0이 아닐경우, 그리고 처음 홈페이지에 들어왔을 경우
			// 메인 페이지 대신에 이체 실행 페이지를 띄우고, 이체를 실행하거나 취소하고 나면 다시 메인페이지를 보여준다. => 코드완성
			// 이체 실행을 취소하면 이번달 이체 금액을 0으로 세팅. => 코드완성 만약 / 지난달보다 이체를 많이 했을 경우에는 포인트를 지급.
			Date date = new Date();
			String PL_ID = String.valueOf(date.getYear() + 1900) + String.valueOf(date.getMonth() + 1);
			switch (date.getMonth() + 1) {
			case 1: case 3: case 5:	case 7:	case 8: case 10: case 12:
				if (date.getDate() == 31) {
				 	int isOK = transferCheck(mapper, PL_ID, M_ID);
				 	if(isOK == 1) {
				 		return "redirect:main/transfer";
				 	}
				}
				break;
			case 4:	case 6:	case 9:	case 11:
				if (date.getDate() == 30) {
					int isOK = transferCheck(mapper, PL_ID, M_ID);
					if(isOK == 1) {
				 		return "redirect:main/transfer";
				 	}
				}
				break;
			case 2:
				if (date.getDate() == 28 || date.getDate() == 29) {
					int isOK = transferCheck(mapper, PL_ID, M_ID);
					if(isOK == 1) {
				 		return "redirect:main/transfer";
				 	}
				}
				break;
			}
		}
		HttpHeaders headersS = new HttpHeaders();
		HttpEntity<?> entityS = new HttpEntity<>(headersS);
		UriComponentsBuilder builderS = UriComponentsBuilder.fromHttpUrl(urlSaving);
		RestTemplate restTemplateS = new RestTemplate();
		HttpEntity<String> responseS = restTemplateS.exchange(builderS.toUriString(), HttpMethod.GET, entityS,
				String.class);
		
		JsonParser jsonParserS = new JsonParser();
		JsonElement jsonElementS = jsonParserS.parse(responseS.getBody().toString());
		JsonObject getBodyS = jsonElementS.getAsJsonObject();
		JsonObject getResultS = getBodyS.get("result").getAsJsonObject();
		JsonArray getBaseListS = getResultS.get("baseList").getAsJsonArray();
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
			String[] newTwo = kpS.getMtrt_int().split("[\\\\r\\\\n]+");
			model.addAttribute("newTwo", newTwo);
		}
		model.addAttribute("productListS", productListS);
		return "/main/index";
	}


	private int transferCheck(PlanMapper mapper, String PL_ID, String M_ID) {
		mapper = sqlsession.getMapper(PlanMapper.class);
		Date date = new Date();
		PL_ID = String.valueOf(date.getYear() + 1900) + String.valueOf(date.getMonth() + 1);
		MemberMapper mapperM = sqlsession.getMapper(MemberMapper.class);
		int openpage = mapperM.getOpenPage(M_ID);
		// 마지막날에 사이트에 처음 들어온 경우 실행
		if(openpage == 0) {
			ProductMapper mapperP = sqlsession.getMapper(ProductMapper.class);
			int prregidx = mapperM.getPrRedIdx(M_ID);
			// 가입한 상품이 있을 경우 실행
			if(prregidx == 1) {
				HashMap<String, String> hmap = new HashMap<String, String>();
				hmap.put("PL_ID", PL_ID);
				hmap.put("M_ID", M_ID);
				String plan = mapper.getPlan(hmap);
				// 해당 날짜에 설정한 한도가 존재할 때 실행
				if(PL_ID.equals(plan)) {
					// 플랜을 성공했을 경우 실행
					if(mapper.getPlanSuccess(hmap) == 1) {
						AccountMapper accmapper = sqlsession.getMapper(AccountMapper.class);
						int account_money = accmapper.getAccountMoney(M_ID);
						// 계좌에 들어있는 돈이 있을 경우
						if(account_money != 0) {
							ArrayList<Integer> pldprice = mapper.getPlanPrice(hmap);
							int totalPrice = 0;
							for (int j = 0; j < pldprice.size(); j++) {
								totalPrice += pldprice.get(j);
							}
							int useTotalPrice = mapper.getPaymentTotalPrice(hmap);
							System.out.println(PL_ID + " : " + totalPrice + " " + useTotalPrice + " " + account_money);
							// 성공했을 경우 다시 확인 => 없어도 될듯..
							if (totalPrice > useTotalPrice) {
								return 1;
							} 
						}	
					}
					
				}
			}
		}
		return 0;
	}

	
	 // 만약 마지막날의 11시 59분전까지 사이트에 들어오지 않았을경우 
	// 한도 > 사용금액보다 큰 사용자에 한해서, 계좌에 잔액이 0이 아닐경우, 한도 - 사용금액 만큼의 금액을 자동으로 이체하거나,
	// 만약 한도 - 사용금액보다 계좌에 있는 금액이 적을 경우 계좌에 있는 금액만큼 자동 이체를 시킨다.
	// 이 부분을 사전에 공지하는 부분 추가.. 어딘가에..
	  
	  @Scheduled(cron = "0 59 23 * * ?") 
	  public void autoTransfer() { 
		  System.out.println("autoTransfer 실행");
		  // 매월 마지막날 11시 59분 00초까지 사이트에 들어오지 않은(openpage = 0)인 사용자들을 얻어온다.
		  Calendar cal = Calendar.getInstance();
		  // 오늘이 월의 마지막 일인지 확인
		  boolean isLastDay = cal.get(Calendar.DATE) == cal.getActualMaximum(Calendar.DATE);
		  if(isLastDay) {
			  PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
			  Date date = new Date();
			  String PL_ID = String.valueOf(date.getYear() + 1900) + String.valueOf(date.getMonth() + 1);
			  MemberMapper mapperM = sqlsession.getMapper(MemberMapper.class); 
			  ProductMapper mapperP = sqlsession.getMapper(ProductMapper.class);
			  AccountMapper accmapper = sqlsession.getMapper(AccountMapper.class);
			  ArrayList<String> members = mapperM.getMemberNotTransfer();
			  // 사용자마다 조건사항 체크해주면서 자동이체 실행
			  for(int i=0; i<members.size(); i++) {
				  String M_ID = members.get(i);
				  // 해당 사용자가 상품에 가입되어 있는지 확인
				  int prregidx = mapperM.getPrRedIdx(M_ID);
				  // 가입한 상품이 있을 경우 실행
				  if(prregidx == 1) {
					  HashMap<String, String> hmap = new HashMap<String, String>();
					  hmap.put("PL_ID", PL_ID);
					  hmap.put("M_ID", M_ID);
					  String plan = mapper.getPlan(hmap);
					  // 해당 날짜에 설정한 한도가 존재할 때 실행
					  if(PL_ID.equals(plan)) {
						  // 플랜을 성공했을 경우 실행
						  if(mapper.getPlanSuccess(hmap) == 1) {
							  int account_money = accmapper.getAccountMoney(M_ID);
							  // 계좌에 들어있는 돈이 있을 경우
							  if(account_money != 0) {
								  ArrayList<Integer> pldprice = mapper.getPlanPrice(hmap);
								  int totalPrice = 0;
								  for (int j = 0; j < pldprice.size(); j++) {
									  totalPrice += pldprice.get(j);
								  }
								  int useTotalPrice = mapper.getPaymentTotalPrice(hmap);
								  System.out.println(PL_ID + " : " + totalPrice + " " + useTotalPrice + " " + account_money);
								  // 성공했을 경우 다시 확인 => 없어도 될듯..
								  if (totalPrice > useTotalPrice) {
									  TransferVO transfer = new TransferVO();
									  transfer.setM_ID(M_ID);
									  transfer.setTRANSFER_DATE(PL_ID);
									  int prd_transfer = 0;
									  if((totalPrice - useTotalPrice) >= account_money) {
										  prd_transfer = account_money;
										  transfer.setTRANSFER_MONEY(prd_transfer);
									  } else if((totalPrice - useTotalPrice) < account_money) {
										  prd_transfer = totalPrice - useTotalPrice;
										  transfer.setTRANSFER_MONEY(prd_transfer);
									  }
									  // 이체 테이블에 아이디, 날짜, 이체금액 저장하는 코드
									  mapper.insertTransfer(transfer);
									  // 상품페이지에 해당 상품의 이체 총 금액에 더하는 코드
									  mapperP.updatePrdTransfer(transfer);
									  // 계좌에서 이체한 금액만큼 뺀다.
									  accmapper.updateTransferAccount(transfer);
									  // 이체 금액별 포인트 지급
									  MemberVO member = new MemberVO();
									  member.setM_ID(M_ID);
									  if(prd_transfer >= 10000) {
										  member.setM_POINT(1000);
									  }
									  mapperM.updatePoint(member);
									  mapperM.updateOpenPage(M_ID);
								  } 
							  }	
						  }
					  }
				  }
			  }
			  
		  }
		 
	  }

	  
	  @Scheduled(cron = "0 0 0 1 * ?")
	  public void setOpenPage() {
		  System.out.println("setOpenPage 실행");
		  MemberMapper mapper = sqlsession.getMapper(MemberMapper.class); 
		  ArrayList<String> members = mapper.getMemberList();
		  for(int i=0; i<members.size(); i++) {
			  String M_ID = members.get(i);
			  mapper.setOpenPageInit(M_ID);
		  }
	  }
	 

	@GetMapping("main/transfer")
	public String transfer(HttpServletRequest request, HttpSession session, Model model) {
		System.out.println("main/transfer.jsp");
		
		LoginSessionTool.checkOnlyNavBar(session, model);
		
		String M_ID = (String) session.getAttribute("id");
		MemberMapper mapper_M = sqlsession.getMapper(MemberMapper.class);
		String M_NAME = mapper_M.getMember(M_ID);
		AccountMapper mapper_A = sqlsession.getMapper(AccountMapper.class);
		AccountVO account = mapper_A.getAccount(M_ID);
		ProductMapper mapper_P = sqlsession.getMapper(ProductMapper.class);
		String productName = mapper_P.getProduct(M_ID);
		System.out.println(M_ID + " " + M_NAME + " " + productName + " " + account.getACCOUNT_NUMBER() + account.getACCOUNT_MONEY());
		model.addAttribute("M_ID", M_ID);
		model.addAttribute("M_NAME", M_NAME);
		model.addAttribute("productName", productName);
		model.addAttribute("account_number", account.getACCOUNT_NUMBER());
		model.addAttribute("account_money", account.getACCOUNT_MONEY());
		return "/main/transfer";
	}

	@GetMapping("main/transferOK")
	public String transferOK(HttpSession session, HttpServletRequest request, Model model) {
		String M_ID = request.getParameter("m_id");
		
		LoginSessionTool.checkOnlyNavBar(session, model);
		
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		try {
			year = Integer.parseInt(request.getParameter("year"));
			month = Integer.parseInt(request.getParameter("month"));
		} catch (Exception e) {
		}
		String prd_transfer_check = request.getParameter("prd_transfer");
		int prd_transfer = Integer.parseInt(request.getParameter("prd_transfer"));
		if (prd_transfer_check == null) {
			prd_transfer = 0;
		}
		System.out.println(M_ID + " " + year + " " + month + " " + prd_transfer);
		String transfer_date = String.valueOf(year) + String.valueOf(month);
		TransferVO transfer = new TransferVO();
		transfer.setM_ID(M_ID);
		transfer.setTRANSFER_DATE(transfer_date);
		transfer.setTRANSFER_MONEY(prd_transfer);
		PlanMapper mapper = sqlsession.getMapper(PlanMapper.class);
		// 이체 테이블에 아이디, 날짜, 이체금액 저장하는 코드
		mapper.insertTransfer(transfer);
		// 상품페이지에 해당 상품의 이체 총 금액에 더하는 코드
		ProductMapper mapper_P = sqlsession.getMapper(ProductMapper.class);
		mapper_P.updatePrdTransfer(transfer);
		// 계좌에서 이체한 금액만큼 뺀다.
		AccountMapper mapper_A = sqlsession.getMapper(AccountMapper.class);
		mapper_A.updateTransferAccount(transfer);
		// 이체 금액별 포인트 지급
		MemberMapper mapper_M = sqlsession.getMapper(MemberMapper.class);
		MemberVO member = new MemberVO();
		member.setM_ID(M_ID);
		if(prd_transfer >= 10000) {
			member.setM_POINT(1000);
		}
		mapper_M.updatePoint(member);
		mapper_M.updateOpenPage(M_ID);
		return "/main/index";
	}

	@GetMapping("main/index")
	public String index(HttpSession session, Model model) {
		System.out.println("index.jsp 실행");
		
		LoginSessionTool.checkOnlyNavBar(session, model);
		
		return "/main/index";
	}

}