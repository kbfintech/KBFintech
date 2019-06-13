package com.spring.finance.product.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })

public class ProductServiceTest {

//	@Autowired
//	ProductService service;
//
//	@Test
//	public void testGetList() {
//		Criteria cri = new Criteria();
//		ProductVO productVO = service.getProductList(cri);
////		productVO.showBaseInfo();
//	}
//	
//	@Test
//	public void testCalc() {
//		int realEnd = (int) (Math.ceil(119 * 1.0) / 100);
//		
//	}

}
