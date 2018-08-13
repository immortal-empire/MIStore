var groupOrderId;
$(document).ready(function(){
	
	//获取url中携带的参数
	var url = window.location.search;
	groupOrderId = url.substring(url.lastIndexOf('=')+1, url.length);
	$.ajax({
		type:"post",
		url:"selectGroupOrderDetailById.action",
		async:false,
        dataType: 'json',
        data:{"groupOrderId":groupOrderId},
        success:function(data){
        	var groupOrderState;
        	if(data.state == '0'){
    			groupOrderState="未付款";
    			time=new Date(data.startTime).toLocaleString();
    			payment = "在线支付"
    		}else if(data.state == '1'){
    			groupOrderState="已付款";
    			time=new Date(data.payTime).toLocaleString();
    			payment= data.payment;
    		}else if(data.state == '2'){
    			groupOrderState="已配货";
    			time=new Date(data.sendGoodsTime).toLocaleString();
    			payment= data.payment;
    		}else if(data.state == '3'){
    			groupOrderState="已发货";
    			time=new Date(data.sendGoodsTime).toLocaleString();
    			payment= data.payment;
    		}else if(data.state == '4'){
    			groupOrderState="交易完成";
    			time=new Date(data.endTime).toLocaleString();
    			payment= data.payment;
    		}else if(data.state == '5'){
    			groupOrderState="正在退款";
    			time=new Date(data.refundTime).toLocaleString();
    			payment= data.payment;
    		}else if(data.state == '6'){
    			groupOrderState="已退款";
    			time=new Date(data.refundTime).toLocaleString();
    			payment= data.payment;
    		}else{
    			groupOrderState="已关闭";
    			time=new Date(data.closeTime).toLocaleString();
    			payment= "在线支付";
    		}
        	var detail;
        	var detail1 ="<div style='margin: 5px 5px 20px 5px; padding: 15px;'>" +
        					"<h1>订单详情</h1>" +
        				 "</div>"+
        				 "<div style='margin: 5px 5px 10px 5px; padding: 15px;'>" +
     						"<div style='float: left;'>" +
     							"<font size='5' color='#444444'>订单号:"+data.id+"</font>" +
     							"</div>";
        	var detail2 = "<div style='float: right; margin: 0px 10px 0px 10px; border: 1px solid #576366;' >" +
        						"<a id='quitGroupOrder'><font size='3'>取消订单</font></a>" +
        					"</div>" +
        					"<div style='float: right; margin: 0px 10px 0px 10px; border: 1px solid orangered;'>" +
        						"<a href='pay.html?groupOrderId="+data.id+"'><font size='3' color='orangered' >立即支付</font></a>" +
        					"</div>" +
        				"</div>";
        	var detail3="<div style='float: right; margin: 0px 10px 0px 10px; border: 1px solid #576366;' >" +
								"<a id='refund'><font size='3'>申请退款</font></a>" +
							"</div>" +
						"</div>";
        	var detail4="<hr style='color: #000000; height: 2px;'/>" +
        				 "<div style='margin: 5px 5px 30px 5px; padding: 15px;'>" +
        				 	"<div>" +
        				 		"<font color='#666666' size='4'>"+groupOrderState+"</font>" +
        				 	"</div>" +
	        				 "<div>" +
		        				 "<div style='float: left;'>" +
		        				 	"<img src='images/brands/"+data.group.groupPurchase.product.picture+"/groupOrder80x80.jpg' />" +
		        				 "</div>" +
		        				 "<div style='float: left; padding: 25px;'>" +
		        				 	"<font size='3' color='#444444'>"+data.group.groupPurchase.product.proName+"</font>" +
		        				 "</div>" +
		        				 "<div style='float: left; padding: 25px;'>" +
		        				 	"<font size='3' color='#444444'>"+data.group.groupPurchase.groupPrice+"元 X "+data.quantity+"</font>" +
		        				 "</div>" +
	        				 "</div>" +
	        			 "</div>" +
	        			 "<br />" +
	        			 "<hr style='color: #000000; height: 2px;'/>" +
	        			 "<div style='margin: 5px 5px 25px 5px; padding: 15px;'>" +
	        			 	"<div>" +
	        			 		"<font size='5' color='#444444'>收货信息</font>" +
	        			 	"</div>" +
	        			 	"<font size='3'>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:&nbsp;&nbsp;&nbsp;</font>" +
	        			 	"<font size='3'>"+data.address.host+"</font><br />" +
	        			 	"<font size='3'>联系电话:&nbsp;&nbsp;&nbsp;</font>" +
	        			 	"<font size='3'>"+data.address.tele+"</font><br />" +
	        			 	"<font size='3'>收货地址:&nbsp;&nbsp;&nbsp;</font>" +
	        			 	"<font size='3'>"+data.address.addressDetail+"</font>" +
	        			 "</div>" +
	        			 "<hr style='color: #000000; height: 2px;'/>" +
	        			 "<div style='margin: 5px 5px 25px 5px; padding: 15px;'>" +
	        			 	"<div>" +
	        			 		"<font size='5' color='#444444'>支付方式及配货时间</font>" +
	        			 	"</div>" +
	        			 	"<font size='3'>支付方式:&nbsp;&nbsp;&nbsp;</font>" +
	        			 	"<font size='3'>"+data.payment+"</font><br />" +
	        			 	"<font size='3'>配货时间:&nbsp;&nbsp;&nbsp;</font>" +
	        			 	"<font size='3'>"+data.distributionTime+"</font>" +
	        			 "</div>" +
	        			 "<hr style='color: #000000; height: 2px;'/>" +
	        			 "<div style='margin: 5px 5px 25px 5px; padding: 15px;'>" +
	        			 	"<div>" +
	        			 		"<font size='5' color='#444444'>发票信息</font>" +
	        			 	"</div>" +
	        			 	"<font size='3'>发票类型:&nbsp;&nbsp;&nbsp;</font>" +
	        			 	"<font size='3'>电子发票</font><br />" +
	        			 	"<font size='3'>发票内容:&nbsp;&nbsp;&nbsp;</font>" +
	        			 	"<font size='3'>购买商品明细</font><br />" +
	        			 	"<font size='3'>发票抬头:&nbsp;&nbsp;&nbsp;</font>" +
	        			 	"<font size='3'>个人</font><br />" +
	        			 "</div>";
        	if(data.state == '0'){
        		detail=detail1+detail2+detail4;
        	}else if(data.state == '1'){
        		detail=detail1+detail3+detail4;
        	}else{
        		detail=detail1+detail4;
        	}
        	$("#detail").html(detail);
        	
        }
	});
	
	//取消订单
	$("#quitGroupOrder").click(function(){
		alert("真的要取消订单吗？");
		$.ajax({
			type:"post",
			url:"quitGroupOrder.action",
			async:false,
	        dataType: 'text',
	        data:{"groupOrderId":groupOrderId},
	        success:function(data){
	        	location.href="groupOrderDetail.html?groupOrderId="+groupOrderId;
	        }
		});
	});
	
	//申请退款
	$("#refund").click(function(){
		alert("真的要退款吗？");
		$.ajax({
			type:"post",
			url:"refund.action",
			async:false,
	        dataType: 'text',
	        data:{"groupOrderId":groupOrderId},
	        success:function(data){
	        	location.href="groupOrderDetail.html?groupOrderId="+groupOrderId;
	        }
		});
	});
	
});

