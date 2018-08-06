$(function(){
	//var user = window.LocalStorage.getItem("user");
	//var cid = JSON.parse(user).id;
	//var result = JSON.parse(sessionStorage.getItem("jsonKey"));
	var index =location.href.lastIndexOf("=");
	var type= location.href.substr(index+1);
	var cname = "天元逆刃"
	var cid = 1;
	if (type=="all"){
		load(0,cid,cname);
		load(1,cid,cname);
		load(2,cid,cname);
		load(3,cid,cname);
		load(4,cid,cname);
		load(5,cid,cname);
		load(6,cid,cname);
	}
	else{
		load(type,cid,cname);
	}
		
});

$(document).on("click", ".orderspec", function(){ 
	var orderData = JSON.parse(sessionStorage.getItem("orderData"));
   	var orderindex = $(this).find("span").eq(0).text();
   	var type = $(this).find("span").eq(1).text();
   	window.location.href="orderDtail.html?orderindex="+orderindex+"="+type;
});
function load(type,cid,cname){
	$.ajax({
		
		url:"OrderViews/"+type+"/"+cid+".action",
		type:"get",
		async:false,
		dataType:"json",
		success:function(data)
		{
			//显示订单信息
			console.log(data);
			//将数据存入session
			sessionStorage.setItem(type,JSON.stringify(data));
			//展示订单
			var status = {"0":"未付款","1":"已付款","2":"已配货","3":"已发货",
							"4":"交易完成","5":"正在退款","6":"已退款","7":"已关闭"};
			var action = {"0":"去付款","1":"订单详情","2":"订单详情","3":"确认收货",
					"4":"订单详情","5":"订单详情","6":"订单详情","7":"订单详情"}
			data.forEach(function(value,index){
					var str = '<div class=order-box>'
					str += '<div class="my-order" > ';
							str+='<div class = "order_top">';
								str+='<div class = "order_status">'+status[value.orderStatus]+'</div>';
								str+='<div class = "row order_info">';
									str+='<div class = "col-md-8 order_info_left">'+value.startTime+' | '+cname+' | '+'订单号:'+value.orderId+' | '+value.payment+'</div>';
									str+='<div class = "col-md-4 order_info_right">'+'订单金额：'+value.totalPrice+'元</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class = "row order_bottom">';
								str+='<div class = "col-md-8 order_bottom_left">';
									value.productsView.forEach(function(productview,index){
										str+='<div class = "row order_bottom_leftviews">';
										str+='<div class="col-md-3 order_bottom_left_picture"><img alt="" src="assets/images/products/'+productview.picture+'/80x80.jpg" ></div>'
										str+='<div class="col-md-9 order_bottom_left_info">'
											str+='<div class="order_bottom_left_info_name">'+productview.name+'  '+productview.configuration+'  '+productview.color+'</div>'
											str+='<div class="order_bottom_left_info_cost">'+productview.price+"元"+ ' &times ' +productview.amount+'</div>'
										str+='</div>'
									str+='</div>';
									});
														
								str+='</div>';
								str+='<div class = "col-md-4 order_bottom_right">'
								
									str+='<span class="orderspec"><a class="btn btn-small btn-line-gray"   >'+action[value.orderStatus]+'</a><span style="display:none">'+index+'</span>'+'<span style="display:none">'+type+'</span></span>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					$("#orders_view").append(str);
				});
			
		}		
	});
}
