//function verifyAmount() {
//	// DB에서 가져온 한도값 초과하는지 검사(상품코드 정보로 해당 한도 찾기 -> 가격과 DB 테이블값 더한 후 비교 -> 초과하면 alert창 띄우기 아니면 requestPay()함수 타기)
//	// 임시 Default 한도 = 200000;
//	var flag = true;
//	
//	// DB 테이블에서 가져오기
//	var limit = 40000;
//	limit = Number(limit);
//	var priceS = document.getElementById("bPrice").innerHTML;
//	var priceD = priceS.substring(0, priceS.length-3);
//	var price = priceD.split(',');
//	var fPrice = "";
//	
//	for(var i = 0; i < price.length; i++) {
//		fPrice += price[i];
//	}
//	fPrice = Number(fPrice);
//	
//	console.log(limit+fPrice);
//	if(limit + fPrice > 200000) flag = false;
//	
//	if(flag) requestPay(fPrice);
//	else alert("한도를 초과하여 결제할 수 없습니다.")
//}

function requestPay(price) {
	
	var merchantId = document.getElementById("bCode").innerHTML;
	var bName = document.getElementById("bCustomerName").innerHTML;
	var bPhoneNum = document.getElementById("bPhoneNum").innerHTML;
	var product = document.getElementById("bProductName").innerHTML;
	var cardFlag = $('input[name="card"]:checked').val();
	
	console.log(cardFlag);
	
	var d = new Date();
	var year = d.getFullYear();
	var month = d.getMonth() + 1;
	var day = d.getDate();
	
	var date = "";
	date += year+"-";
	date += month+"-";
	date += day;
	
	var json = {
			"bCode": merchantId,
			"bName": document.getElementById("bCompanyName").innerHTML,
			"pDate": date,
			"pPrice": price,
			"cardFlag" : cardFlag
			// 결제완료내역 영수증 화면을 위한 데이터
	};
	
	var IMP = window.IMP;
	IMP.init('imp90062431');
	IMP.request_pay({
		pg : 'html5_inicis',
		pay_method : 'card',
		merchant_uid : merchantId+new Date().getTime(),
		name : product,
		amount : price,
		buyer_email : '',
		buyer_name : bName,
		buyer_tel : bPhoneNum,
		buyer_addr : '',
		buyer_postcode : '',
		m_redirect_url : 'https://localhost:8080/payments/complete'
	}, function(rsp) {
		if (rsp.success) { // 성공시
			var msg = '결제가 완료되었습니다.';
			msg += '고유ID : ' + rsp.imp_uid + ", ";
			msg += '상점 거래ID : ' + rsp.merchant_uid + ", ";
			msg += '결제 금액 : ' + rsp.paid_amount + ", ";
			msg += '카드 승인번호: ' + rsp.apply_num + ", ";
			msg += '영수증url: ' + rsp.receipt_url;
			$.ajax({
				url:"/payment/complete",
				async:"false",
				method:"get",
				dataType:"json",
				data: json,
			});
			alert('결제가 완료되었습니다.');
			location.href='/payment/finished';
		} else { // 실패시
			var msg = '결제에 실패하였습니다.';
			msg += '에러내용 : ' + rsp.error_msg;
			alert(msg);
		}
	});
}