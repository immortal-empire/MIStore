var user = window.localStorage.getItem("user");
var cid = JSON.parse(user).id;

$(document).ready(function(){
	getGroupOrder();
	inputSelectGroupOrder();
	confirmationOfreceipt();//确认收货
});

function getGroupOrder(){
	
	$.ajax({
		type:"post",
		url:"getGroupOrder.action",
		async:false,
        dataType: 'json',
        data:{"customerId":cid},
        success:function(data){
        	var groupOrderState;
        	var time;
        	var payment;
        	console.log(data);
        	for(var i =0;i<data.length;i++){
        		if(data[i].state == '0'){
        			groupOrderState="未付款";
        			time=new Date(data[i].startTime).toLocaleString();
        			payment = "在线支付"
        		}else if(data[i].state == '1'){
        			groupOrderState="已付款";
        			time=new Date(data[i].payTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '2'){
        			groupOrderState="已配货";
        			time=new Date(data[i].sendGoodsTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '3'){
        			groupOrderState="已发货";
        			time=new Date(data[i].sendGoodsTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '4'){
        			groupOrderState="交易完成";
        			time=new Date(data[i].endTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '5'){
        			groupOrderState="正在退款";
        			time=new Date(data[i].refundTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '6'){
        			groupOrderState="已退款";
        			time=new Date(data[i].refundTime).toLocaleString();
        			payment= data[i].payment;
        		}else{
        			groupOrderState="已关闭";
        			time=new Date(data[i].closeTime).toLocaleString();
        			payment= "在线支付";
        		}
        		var groupOrder;
        		var groupOrder1="<div style='border:1px solid #8B8B8B; margin: 50px 0px 10px 0px;' >"+
	        						"<div style='margin: 0px 0px 5px 10px;margin-top: 10px;'>" +
										"<h3>"+groupOrderState+"</h3>" +
									"</div>" +
									"<div style='margin: 0px 0px 5px 10px; height: 40px;' >" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>"+time+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;"+data[i].address.host+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;订单号:"+data[i].id+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;"+payment+"</font> " +
										"</div>" +
										"<div style='float: left; margin-left: 220px;'>" +
											"<font size='4' color='#4C5760'>订单金额:"+data[i].account+"元</font> " +
										"</div>" +
									"</div>	" +
									"<hr style='color: #000000; height: 2px;'/>" +
									"<div style='margin: 0px 0px 5px 10px; padding: 15px; height: 100px;'>" +
										"<div style='float: left;'>" +
											"<img src='images/brands/"+data[i].group.groupPurchase.product.picture+"/groupOrder80x80.jpg' alt='' />" +
										"</div>" +
										"<div style='float: left; margin-top: 10px;'>" +
											"<font size='3' color='#222222'>"+data[i].group.groupPurchase.product.proName+"</font><br />" +
											"<font size='3' color='#222222'>"+data[i].group.groupPurchase.groupPrice+"元&nbsp;&nbsp;x&nbsp;&nbsp;"+data[i].quantity+"</font>" +
										"</div>";
        			var groupOrder2 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; '>" +
											"<a href='pay.html?groupOrderId="+data[i].id+"'><button style='color: orangered;'>立即支付</button></a>" +
										"</div>";
					var groupOrder4 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; '>" +
											"<a id = 'confirmationOfreceipt'>" +
											"<a href='groupOrderProfile.html'><button style='color: orangered;'>确认收货</button></a>" +
											"<input type='hidden' value='"+data[i].id+"'>" +
											"</a>" +
										"</div>";
        			var groupOrder3 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; margin-top: 10px;'>" +
											"<a href='groupOrderDetail.html?groupOrderId="+data[i].id+"'><button>订单详情</button></a> " +
										"</div>" +
									"</div>" +
								"</div>";
        			if(data[i].state == '0'){
        				groupOrder = groupOrder1+groupOrder2+groupOrder3;
        			}else if(data[i].state == '2' || data[i].state == '3'){
        				groupOrder = groupOrder1+groupOrder4+groupOrder3;
        			}else{
        				groupOrder = groupOrder1+groupOrder3;
        			}
        		$("#getGroupOrder").append(groupOrder);
        	}
        	
        }
	});
}

//查询全部有效订单
$("#getAllEffectGroupOrder").click(function(){
	$("#getGroupOrder").html("");
	$.ajax({
		type:"post",
		url:"getGroupOrder.action",
		async:false,
        dataType: 'json',
        data:{"customerId":cid},
        success:function(data){
        	var groupOrderState;
        	var time;
        	var payment;
        	for(var i =0;i<data.length;i++){
        		if(data[i].state == '0'){
        			groupOrderState="未付款";
        			time=new Date(data[i].startTime).toLocaleString();
        			payment = "在线支付"
        		}else if(data[i].state == '1'){
        			groupOrderState="已付款";
        			time=new Date(data[i].payTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '2'){
        			groupOrderState="已配货";
        			time=new Date(data[i].sendGoodsTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '3'){
        			groupOrderState="已发货";
        			time=new Date(data[i].sendGoodsTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '4'){
        			groupOrderState="交易完成";
        			time=new Date(data[i].endTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '5'){
        			groupOrderState="正在退款";
        			time=new Date(data[i].refundTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '6'){
        			groupOrderState="已退款";
        			time=new Date(data[i].refundTime).toLocaleString();
        			payment= data[i].payment;
        		}else{
        			groupOrderState="已关闭";
        			time=new Date(data[i].closeTime).toLocaleString();
        			payment= "在线支付";
        		}
        		var groupOrder;
        		var groupOrder1="<div style='border:1px solid #8B8B8B; margin: 50px 0px 10px 0px;' >"+
	        						"<div style='margin: 0px 0px 5px 10px;margin-top: 10px;'>" +
										"<h3>"+groupOrderState+"</h3>" +
									"</div>" +
									"<div style='margin: 0px 0px 5px 10px; height: 40px;' >" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>"+time+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;"+data[i].address.host+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;订单号:"+data[i].id+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;"+payment+"</font> " +
										"</div>" +
										"<div style='float: left; margin-left: 220px;'>" +
											"<font size='4' color='#4C5760'>订单金额:"+data[i].account+"元</font> " +
										"</div>" +
									"</div>	" +
									"<hr style='color: #000000; height: 2px;'/>" +
									"<div style='margin: 0px 0px 5px 10px; padding: 15px; height: 100px;'>" +
										"<div style='float: left;'>" +
											"<img src='images/brands/"+data[i].group.groupPurchase.product.picture+"/groupOrder80x80.jpg' alt='' />" +
										"</div>" +
										"<div style='float: left; margin-top: 10px;'>" +
											"<font size='3' color='#222222'>"+data[i].group.groupPurchase.product.proName+"</font><br />" +
											"<font size='3' color='#222222'>"+data[i].group.groupPurchase.groupPrice+"元&nbsp;&nbsp;x&nbsp;&nbsp;"+data[i].quantity+"</font>" +
										"</div>";
        			var groupOrder2 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; '>" +
											"<a href='pay.html?groupOrderId="+data[i].id+"'><button style='color: orangered;'>立即支付</button></a>" +
									  "</div>";
        			var groupOrder4 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; '>" +
											"<a id = 'confirmationOfreceipt'>" +
											"<a href='groupOrderProfile.html'><button style='color: orangered;'>确认收货</button></a>" +
											"<input type='hidden' value='"+data[i].id+"'>" +
											"</a>" +
										"</div>";
        			var groupOrder3 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; margin-top: 10px;'>" +
											"<a href='groupOrderDetail.html?groupOrderId="+data[i].id+"'><button>订单详情</button></a> " +
									  "</div>" +
									"</div>" +
								"</div>";
        			if(data[i].state == '0'){
        				groupOrder = groupOrder1+groupOrder2+groupOrder3;
        			}else if(data[i].state == '2' || data[i].state == '3'){
        				groupOrder = groupOrder1+groupOrder4+groupOrder3;
        			}else{
        				groupOrder = groupOrder1+groupOrder3;
        			}
        		$("#getGroupOrder").append(groupOrder);
        	}
        	
        }//结束
	});
});

//查询未支付订单
$("#getNotPayGroupOrder").click(function(){
	$("#getGroupOrder").html("");
	$.ajax({
		type:"post",
		url:"getNotPayGroupOrder.action",
		async:false,
        dataType: 'json',
        data:{"customerId":cid},
        success:function(data){
        	var groupOrderState;
        	var time;
        	var payment;
        	for(var i =0;i<data.length;i++){
        		if(data[i].state == '0'){
        			groupOrderState="未付款";
        			time=new Date(data[i].startTime).toLocaleString();
        			payment = "在线支付"
        		}else if(data[i].state == '1'){
        			groupOrderState="已付款";
        			time=new Date(data[i].payTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '2'){
        			groupOrderState="已配货";
        			time=new Date(data[i].sendGoodsTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '3'){
        			groupOrderState="已发货";
        			time=new Date(data[i].sendGoodsTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '4'){
        			groupOrderState="交易完成";
        			time=new Date(data[i].endTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '5'){
        			groupOrderState="正在退款";
        			time=new Date(data[i].refundTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '6'){
        			groupOrderState="已退款";
        			time=new Date(data[i].refundTime).toLocaleString();
        			payment= data[i].payment;
        		}else{
        			groupOrderState="已关闭";
        			time=new Date(data[i].closeTime).toLocaleString();
        			payment= "在线支付";
        		}
        		var groupOrder;
        		var groupOrder1="<div style='border:1px solid #8B8B8B; margin: 50px 0px 10px 0px;' >"+
	        						"<div style='margin: 0px 0px 5px 10px;margin-top: 10px;'>" +
										"<h3>"+groupOrderState+"</h3>" +
									"</div>" +
									"<div style='margin: 0px 0px 5px 10px; height: 40px;' >" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>"+time+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;"+data[i].address.host+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;订单号:"+data[i].id+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;"+payment+"</font> " +
										"</div>" +
										"<div style='float: left; margin-left: 220px;'>" +
											"<font size='4' color='#4C5760'>订单金额:"+data[i].account+"元</font> " +
										"</div>" +
									"</div>	" +
									"<hr style='color: #000000; height: 2px;'/>" +
									"<div style='margin: 0px 0px 5px 10px; padding: 15px; height: 100px;'>" +
										"<div style='float: left;'>" +
											"<img src='images/brands/"+data[i].group.groupPurchase.product.picture+"/groupOrder80x80.jpg' alt='' />" +
										"</div>" +
										"<div style='float: left; margin-top: 10px;'>" +
											"<font size='3' color='#222222'>"+data[i].group.groupPurchase.product.proName+"</font><br />" +
											"<font size='3' color='#222222'>"+data[i].group.groupPurchase.groupPrice+"元&nbsp;&nbsp;x&nbsp;&nbsp;"+data[i].quantity+"</font>" +
										"</div>";
        			var groupOrder2 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; '>" +
											"<a href='pay.html?groupOrderId="+data[i].id+"'><button style='color: orangered;'>立即支付</button></a>" +
										"</div>";
        			var groupOrder3 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; margin-top: 10px;'>" +
											"<a href='groupOrderDetail.html?groupOrderId="+data[i].id+"'><button>订单详情</button></a> " +
										"</div>" +
									"</div>" +
								"</div>";
        			if(data[i].state == '0'){
        				groupOrder = groupOrder1+groupOrder2+groupOrder3;
        			}else{
        				groupOrder = groupOrder1+groupOrder3;
        			}
        		$("#getGroupOrder").append(groupOrder);
        	}
        	
        }
	});
});

//查询待收货订单
$("#getReceivingGroupOrder").click(function(){
	$("#getGroupOrder").html("");
	$.ajax({
		type:"post",
		url:"getReceivingGroupOrder.action",
		async:false,
        dataType: 'json',
        data:{"customerId":cid},
        success:function(data){
        	var groupOrderState;
        	var time;
        	var payment;
        	for(var i =0;i<data.length;i++){
        		if(data[i].state == '0'){
        			groupOrderState="未付款";
        			time=new Date(data[i].startTime).toLocaleString();
        			payment = "在线支付"
        		}else if(data[i].state == '1'){
        			groupOrderState="已付款";
        			time=new Date(data[i].payTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '2'){
        			groupOrderState="已配货";
        			time=new Date(data[i].sendGoodsTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '3'){
        			groupOrderState="已发货";
        			time=new Date(data[i].sendGoodsTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '4'){
        			groupOrderState="交易完成";
        			time=new Date(data[i].endTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '5'){
        			groupOrderState="正在退款";
        			time=new Date(data[i].refundTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '6'){
        			groupOrderState="已退款";
        			time=new Date(data[i].refundTime).toLocaleString();
        			payment= data[i].payment;
        		}else{
        			groupOrderState="已关闭";
        			time=new Date(data[i].closeTime).toLocaleString();
        			payment= "在线支付";
        		}
        		var groupOrder;
        		var groupOrder1="<div style='border:1px solid #8B8B8B; margin: 50px 0px 10px 0px;' >"+
	        						"<div style='margin: 0px 0px 5px 10px;margin-top: 10px;'>" +
										"<h3>"+groupOrderState+"</h3>" +
									"</div>" +
									"<div style='margin: 0px 0px 5px 10px; height: 40px;' >" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>"+time+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;"+data[i].address.host+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;订单号:"+data[i].id+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;"+payment+"</font> " +
										"</div>" +
										"<div style='float: left; margin-left: 220px;'>" +
											"<font size='4' color='#4C5760'>订单金额:"+data[i].account+"元</font> " +
										"</div>" +
									"</div>	" +
									"<hr style='color: #000000; height: 2px;'/>" +
									"<div style='margin: 0px 0px 5px 10px; padding: 15px; height: 100px;'>" +
										"<div style='float: left;'>" +
											"<img src='images/brands/"+data[i].group.groupPurchase.product.picture+"/groupOrder80x80.jpg' alt='' />" +
										"</div>" +
										"<div style='float: left; margin-top: 10px;'>" +
											"<font size='3' color='#222222'>"+data[i].group.groupPurchase.product.proName+"</font><br />" +
											"<font size='3' color='#222222'>"+data[i].group.groupPurchase.groupPrice+"元&nbsp;&nbsp;x&nbsp;&nbsp;"+data[i].quantity+"</font>" +
										"</div>";
        			var groupOrder2 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; '>" +
											"<a href='pay.html?groupOrderId="+data[i].id+"'><button style='color: orangered;'>立即支付</button></a>" +
										"</div>";
        			var groupOrder4 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; '>" +
											"<a id = 'confirmationOfreceipt'>" +
											"<a href='groupOrderProfile.html'><button style='color: orangered;'>确认收货</button></a>" +
											"<input type='hidden' value='"+data[i].id+"'>" +
											"</a>" +
									 "</div>";
        			var groupOrder3 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; margin-top: 10px;'>" +
											"<a href='groupOrderDetail.html?groupOrderId="+data[i].id+"'><button>订单详情</button></a> " +
										"</div>" +
									"</div>" +
								"</div>";
        			if(data[i].state == '0'){
        				groupOrder = groupOrder1+groupOrder2+groupOrder3;
        			}else if(data[i].state == '2' || data[i].state == '3'){
        				groupOrder = groupOrder1+groupOrder4+groupOrder3;
        			}else{
        				groupOrder = groupOrder1+groupOrder3;
        			}
        		$("#getGroupOrder").append(groupOrder);
        	}
        }
	});	
});

//查询已关闭订单
$("#getClosedGroupOrder").click(function(){
	$("#getGroupOrder").html("");
	$.ajax({
		type:"post",
		url:"getClosedGroupOrder.action",
		async:false,
        dataType: 'json',
        data:{"customerId":cid},
        success:function(data){
        	var groupOrderState;
        	var time;
        	var payment;
        	for(var i =0;i<data.length;i++){
        		if(data[i].state == '0'){
        			groupOrderState="未付款";
        			time=new Date(data[i].startTime).toLocaleString();
        			payment = "在线支付"
        		}else if(data[i].state == '1'){
        			groupOrderState="已付款";
        			time=new Date(data[i].payTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '2'){
        			groupOrderState="已配货";
        			time=new Date(data[i].sendGoodsTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '3'){
        			groupOrderState="已发货";
        			time=new Date(data[i].sendGoodsTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '4'){
        			groupOrderState="交易完成";
        			time=new Date(data[i].endTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '5'){
        			groupOrderState="正在退款";
        			time=new Date(data[i].refundTime).toLocaleString();
        			payment= data[i].payment;
        		}else if(data[i].state == '6'){
        			groupOrderState="已退款";
        			time=new Date(data[i].refundTime).toLocaleString();
        			payment= data[i].payment;
        		}else{
        			groupOrderState="已关闭";
        			time=new Date(data[i].closeTime).toLocaleString();
        			payment= "在线支付";
        		}
        		var groupOrder;
        		var groupOrder1="<div style='border:1px solid #8B8B8B; margin: 50px 0px 10px 0px;' >"+
	        						"<div style='margin: 0px 0px 5px 10px;margin-top: 10px;'>" +
										"<h3>"+groupOrderState+"</h3>" +
									"</div>" +
									"<div style='margin: 0px 0px 5px 10px; height: 40px;' >" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>"+time+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;"+data[i].address.host+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;订单号:"+data[i].id+"&nbsp;|</font> " +
										"</div>" +
										"<div style='float: left; margin-top: 3px;'>" +
											"<font size='3' color='#8B8B8B'>&nbsp;"+payment+"</font> " +
										"</div>" +
										"<div style='float: left; margin-left: 220px;'>" +
											"<font size='4' color='#4C5760'>订单金额:"+data[i].account+"元</font> " +
										"</div>" +
									"</div>	" +
									"<hr style='color: #000000; height: 2px;'/>" +
									"<div style='margin: 0px 0px 5px 10px; padding: 15px; height: 100px;'>" +
										"<div style='float: left;'>" +
											"<img src='images/brands/"+data[i].group.groupPurchase.product.picture+"/groupOrder80x80.jpg' alt='' />" +
										"</div>" +
										"<div style='float: left; margin-top: 10px;'>" +
											"<font size='3' color='#222222'>"+data[i].group.groupPurchase.product.proName+"</font><br />" +
											"<font size='3' color='#222222'>"+data[i].group.groupPurchase.groupPrice+"元&nbsp;&nbsp;x&nbsp;&nbsp;"+data[i].quantity+"</font>" +
										"</div>";
        			var groupOrder2 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; '>" +
											"<a href='pay.html?groupOrderId="+data[i].id+"'><button style='color: orangered;'>立即支付</button></a>" +
										"</div>";
        			var groupOrder3 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; margin-top: 10px;'>" +
											"<a href='groupOrderDetail.html?groupOrderId="+data[i].id+"'><button>订单详情</button></a> " +
										"</div>" +
									"</div>" +
								"</div>";
        			if(data[i].state == '0'){
        				groupOrder = groupOrder1+groupOrder2+groupOrder3;
        			}else{
        				groupOrder = groupOrder1+groupOrder3;
        			}
        		$("#getGroupOrder").append(groupOrder);
        	}
        }
	});	
});

//搜索框查询团购订单
function inputSelectGroupOrder(){
	$("#selectButton").click(function(){
		var result = $("#selectGroupOrderByInput").val();
		$("#getGroupOrder").html("");
		$.ajax({
			type:"post",
			url:"selectGroupOrderByInput.action",
			async:false,
	        dataType: 'json',
	        data:{"customerId":cid,
				 "result":result},
			success:function(data){
			        	var groupOrderState;
			        	var time;
			        	var payment;
			        	for(var i =0;i<data.length;i++){
			        		if(data[i].state == '0'){
			        			groupOrderState="未付款";
			        			time=new Date(data[i].startTime).toLocaleString();
			        			payment = "在线支付"
			        		}else if(data[i].state == '1'){
			        			groupOrderState="已付款";
			        			time=new Date(data[i].payTime).toLocaleString();
			        			payment= data[i].payment;
			        		}else if(data[i].state == '2'){
			        			groupOrderState="已配货";
			        			time=new Date(data[i].sendGoodsTime).toLocaleString();
			        			payment= data[i].payment;
			        		}else if(data[i].state == '3'){
			        			groupOrderState="已发货";
			        			time=new Date(data[i].sendGoodsTime).toLocaleString();
			        			payment= data[i].payment;
			        		}else if(data[i].state == '4'){
			        			groupOrderState="交易完成";
			        			time=new Date(data[i].endTime).toLocaleString();
			        			payment= data[i].payment;
			        		}else if(data[i].state == '5'){
			        			groupOrderState="正在退款";
			        			time=new Date(data[i].refundTime).toLocaleString();
			        			payment= data[i].payment;
			        		}else if(data[i].state == '6'){
			        			groupOrderState="已退款";
			        			time=new Date(data[i].refundTime).toLocaleString();
			        			payment= data[i].payment;
			        		}else{
			        			groupOrderState="已关闭";
			        			time=new Date(data[i].closeTime).toLocaleString();
			        			payment= "在线支付";
			        		}
			        		var groupOrder;
			        		var groupOrder1="<div style='border:1px solid #8B8B8B; margin: 50px 0px 10px 0px;' >"+
				        						"<div style='margin: 0px 0px 5px 10px;margin-top: 10px;'>" +
													"<h3>"+groupOrderState+"</h3>" +
												"</div>" +
												"<div style='margin: 0px 0px 5px 10px; height: 40px;' >" +
													"<div style='float: left; margin-top: 3px;'>" +
														"<font size='3' color='#8B8B8B'>"+time+"&nbsp;|</font> " +
													"</div>" +
													"<div style='float: left; margin-top: 3px;'>" +
														"<font size='3' color='#8B8B8B'>&nbsp;"+data[i].address.host+"&nbsp;|</font> " +
													"</div>" +
													"<div style='float: left; margin-top: 3px;'>" +
														"<font size='3' color='#8B8B8B'>&nbsp;订单号:"+data[i].id+"&nbsp;|</font> " +
													"</div>" +
													"<div style='float: left; margin-top: 3px;'>" +
														"<font size='3' color='#8B8B8B'>&nbsp;"+payment+"</font> " +
													"</div>" +
													"<div style='float: left; margin-left: 220px;'>" +
														"<font size='4' color='#4C5760'>订单金额:"+data[i].account+"元</font> " +
													"</div>" +
												"</div>	" +
												"<hr style='color: #000000; height: 2px;'/>" +
												"<div style='margin: 0px 0px 5px 10px; padding: 15px; height: 100px;'>" +
													"<div style='float: left;'>" +
														"<img src='images/brands/"+data[i].group.groupPurchase.product.picture+"/groupOrder80x80.jpg' alt='' />" +
													"</div>" +
													"<div style='float: left; margin-top: 10px;'>" +
														"<font size='3' color='#222222'>"+data[i].group.groupPurchase.product.proName+"</font><br />" +
														"<font size='3' color='#222222'>"+data[i].group.groupPurchase.groupPrice+"元&nbsp;&nbsp;x&nbsp;&nbsp;"+data[i].quantity+"</font>" +
													"</div>";
			        			var groupOrder2 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; '>" +
														"<a href='pay.html?groupOrderId="+data[i].id+"'><button style='color: orangered;'>立即支付</button></a>" +
													"</div>";
			        			var groupOrder4 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; '>" +
														"<a id = 'confirmationOfreceipt'>" +
														"<a href='groupOrderProfile.html'><button style='color: orangered;'>确认收货</button></a>" +
														"<input type='hidden' value='"+data[i].id+"'>" +
														"</a>" +
												 "</div>";
			        			var groupOrder3 = "<div style='margin: 0px 0px 5px 10px; margin-left: 85%; margin-top: 10px;'>" +
														"<a href='groupOrderDetail.html?groupOrderId="+data[i].id+"'><button>订单详情</button></a> " +
													"</div>" +
												"</div>" +
											"</div>";
			        			if(data[i].state == '0'){
			        				groupOrder = groupOrder1+groupOrder2+groupOrder3;
			        			}else if(data[i].state == '2' || data[i].state == '3'){
			        				groupOrder = groupOrder1+groupOrder4+groupOrder3;
			        			}else{
			        				groupOrder = groupOrder1+groupOrder3;
			        			}
			        		$("#getGroupOrder").append(groupOrder);
			        	}
			        	
			        }
		});
	});
}

function confirmationOfreceipt(){
	$("#confirmationOfreceipt").click(function(){
		var groupOrderId=$(this).find("input[type=hidden]").val();
		$.ajax({
			type:"post",
			url:"confirmationOfreceipt.action",
			async:false,
	        dataType: 'json',
	        data:{"groupOrderId":groupOrderId},
	        success:function(data){
	        	alert("已确认收货");
	        }
		});
	});
}
