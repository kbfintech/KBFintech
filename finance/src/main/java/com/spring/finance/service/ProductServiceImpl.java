package com.spring.finance.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.finance.domain.ProductVO;
import com.spring.finance.mapper.ProductMapper;
import com.spring.finance.util.Criteria;
import com.spring.finance.util.RequestAPI;


//https://eblo.tistory.com/62
//https://m.blog.naver.com/PostView.nhn?blogId=compro85&logNo=130184997469&proxyReferer=https%3A%2F%2Fwww.google.com%2F
//https://www.evernote.com/shard/s322/client/snv?noteGuid=377c5494-2e3a-4a57-b5cf-2ef6fafbcb86&noteKey=da0bb1a56462a78623cfeed6c2295795&sn=https%3A%2F%2Fwww.evernote.com%2Fshard%2Fs322%2Fsh%2F377c5494-2e3a-4a57-b5cf-2ef6fafbcb86%2Fda0bb1a56462a78623cfeed6c2295795&title=Spring%25EC%2597%2590%25EC%2584%259C%2BJson%25EC%259C%25BC%25EB%25A1%259C%25EC%259D%2598%2B%25EC%259E%2585%25EC%25B6%259C%25EB%25A0%25A5%2B%25EC%25A0%2595%25EB%25A6%25AC
//https://www.leafcats.com/173
//https://www.baeldung.com/jackson
//https://myjamong.tistory.com/18
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper mapper;

	// https://www.baeldung.com/jackson-nested-values
	// https://thepracticaldeveloper.com/2018/07/31/java-and-json-jackson-serialization-with-objectmapper/
	// http://www.studytrails.com/java/json/java-jackson-serialization-list/

	@Override
	public ProductVO getProductList(Criteria cri) {

		Map<String, String> param = new HashMap<String, String>();
		param.put("topFinGrpNo", "020000");
		param.put("pageNo", String.valueOf(cri.getPageNum()));
		param.put("amount", String.valueOf(cri.getAmount()));
		ResponseEntity<String> response = RequestAPI.getProductList(param);
		ProductVO productVO = new ProductVO();
		try {
			JsonNode productNode = new ObjectMapper().readTree(response.getBody());
			ObjectMapper mapper = new ObjectMapper();
			productVO = mapper.readValue(productNode.get("result").toString(), ProductVO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return productVO;
	}

}
