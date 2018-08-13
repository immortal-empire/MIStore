
//var orderindex = location.href.split("=")[1];
//var type = location.href.split("=")[2];
var oid = location.href.split("=")[1];
//console.log(orderindex);
//console.log(type);
$(function(){
		//var orderData = JSON.parse(sessionStorage.getItem(type));
		//var data = orderData[orderindex];
		//console.log(data);
$.ajax({
		
		url:"OrderDtail/"+oid+".action",
		type:"get",
		async:false,
		dataType:"json",
		success:function(data){
			console.log(data);
			var address = data.address;
			var receiveGoodsTime = {"0":"不限收货时间","1":"只限工作日","2":"只限周末"};
			var invoiceType = {"0":"纸质发票","1":"电子发票","2":"没有发票"};
			var status = {"0":"未付款","1":"已付款","2":"已配货","3":"已发货",
					"4":"交易完成","5":"正在退款","6":"已退款","7":"已关闭"};   	
		   	$("#orderspec-id").html("订单号: "+data.orderId);
			$("#orderspec-status").html(status[data.orderStatus]);
		   	data.productsView.forEach(function(productview,index){
		   	var str ='<div class="orderspec-products">'        	
		   			str+='<div class=" row orderspec-products-detial">'
		   				str+='<div class="col-md-2 orderspec-products-detial-picture">'
		   				
		   						str+='<a target="_blank" href="//item.mi.com/1174100030.html"><img src="cartAndOrder/img/'+productview.picture+'/cart.jpg">'+'</a>'
		   					
		   				str+='</div>'
		   				str+='<div class="col-md-4 orderspec-products-detial-name">'
		   					str+='<a target="_blank" href="//item.mi.com/1174100030.html">'+productview.name+'  '+productview.configuration+'  '+productview.color+'</a>'
		   				str+='</div>'
		   				str+='<div class="col-md-4 orderspec-products-detial-price">'
		   					str+=productview.price+'元 &times; '+productview.amount
		   				str+='</div>'
		   				str+='<td class="col-md-2 orderspec-products-detial-name"> '                                                     
		   				str+='</td>'
	                str+='</div>'
	            str+='</div>'
	            $("#orderspec-product-detial").append(str);
		   	});
		   	$("#orderspec-username").html(address.consumerName);
		   	$("#orderspec-tele").html(address.telephone);
		   	$("#orderspec-address").html(address.addressDetail);
		   	$("#orderspec-payment").html(data.payment);
		   	$("#orderspec-receiveGoodsTime").html(receiveGoodsTime[data.receiveGoodsTime]);
		   	$("#orderspec-invoiceType").html(invoiceType[data.invoiceType]);
		   	$("#order-price").html(data.totalPrice);
			}
		});
		
});
