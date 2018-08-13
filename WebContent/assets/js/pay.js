var payment;//全局变量、支付方式
var Id;
var type=true;
$(document).ready(function() {
	//获取url中携带的参数
	var url = window.location.search;
	var groupOrderId = url.substring(url.lastIndexOf('=')+1, url.length);
	selectPayment();
	
	
	var orderId=window.localStorage.getItem("orderId");
	
	if(orderId==null||orderId==""||orderId==undefined){
		//alert("lala");
		Id=groupOrderId;
		type=true;
	} else{
		//alert("haha");
		Id=orderId;
		type=false;
		window.localStorage.removeItem("orderId");
	}
	
	updataGroupOrderPayment();
});

function selectPayment(){
	$(".selectpay").click(function() {
		$(".selectpay").css("border","");
		payment=$(this).find("a").text();
		//alert(payment);
		$(this).css("border","1px solid orangered");
	});
}

function updataGroupOrderPayment(){
	$("#pay").click(function() {
		$.ajax({
			type:"post",
			url:"updataGroupOrderPayment.action",
			async:false,
	        dataType: 'text',
	        data:{"groupOrderId":Id,
	        	  "payment":payment,
	        	  "type":type},
	        success:function(data){
	        	location.href="../index.html";
	        }
		});
	});
}

