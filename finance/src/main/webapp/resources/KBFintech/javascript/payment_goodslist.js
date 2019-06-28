function loadGoods(idx) {
	var gName = document.getElementsByClassName("title is-4 gName")[idx].innerHTML;
	var gCompany = document.getElementsByClassName("subtitle is-3 gCompany")[idx].innerHTML;
	var gCode = document.getElementsByClassName("subtitle is-6 gCode")[idx].innerHTML;
	var gPrice = document.getElementsByClassName("title is-5 gPrice")[idx].innerHTML;

	$.ajax({
		url: "/payment",
		method: "get",
		async: false,
		success: function(data) {
			$('#Context').html(data);
			$('#bProductName').html(gName);
			$('#bCompanyName').html(gCompany);
			$('#bCode').html(gCode);
			$('#bPrice').html(gPrice);
		}
	})
	
}