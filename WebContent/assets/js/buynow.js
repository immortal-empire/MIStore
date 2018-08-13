var quantity = 1;
var account = 0;
var price = 0;
var addressnum;
var distributionTime;
var invoice;
var transportation = 0;
var gResidueSum = 0;
var user = window.localStorage.getItem("user");
var cid = JSON.parse(user).id;

$(document).ready(function(){
	navigationBar();//固定导航栏
	loadGroupPurchase();
	selectConfig();
	selectColor();
	selectQuantity();
	loadAddress();
	selectAddress();
	selectInvoice();
	selectdistributionTime();
	createGroupOrder();
});

//固定导航栏
function navigationBar() {
	//拉动的距离
    var navOffset=$("#nav").offset().top; 
    //加入滚动事件
    $(window).scroll(function(){  
        var scrollPos=$(window).scrollTop();  
        if(scrollPos >=navOffset){ 
        	// 判断窗口的滚动如果大于top的高度就为nav添加fixed
            $("#nav").addClass("fixed");  
        }else{  
        	// 判断窗口的滚动如果小于top的高度就为nav移除fixed
            $("#nav").removeClass("fixed");  
        }  
    }); 
}

//加载团购商品
function loadGroupPurchase(){
	//从网址中读取团Id
	var url = window.location.search;
	var groupId = url.substring(url.lastIndexOf('=')+1, url.length);
	$.ajax({
		type:"post",
		url:"selectGroupPurchaseByGroupId.action",
		async:false,
		dataType: 'json',
		data:{"groupId":groupId},
		success:function(data){
			gResidueSum = data.groupPurchase.gResidueSum;
			var gProByIdPicture="<div class='blog-post col-xs-12'>" +
									"<article class='entry panel'>" +
										"<figure class='entry-media'>" +
											"<div class='owl-slider' data-loop='true' data-autoplay='true' data-autoplay-timeout='10000' data-smart-speed='1000' data-nav-speed='false' data-nav='true' data-xxs-items='1' data-xxs-nav='true' data-xs-items='1' data-xs-nav='true' data-sm-items='1' data-sm-nav='true' data-md-items='1' data-md-nav='true' data-lg-items='1' data-lg-nav='true'> " +
												"<figure class='item'>" +
													"<img src='images/brands/"+data.groupPurchase.product.picture+"/2560x560.jpg' alt=''>" +
												"</figure>" +
												"<figure class='item'>" +
													"<img src='images/brands/"+data.groupPurchase.product.picture+"/3560x560.jpg' alt=''>" +
												" </figure>" +
											"</div>" +
										"</figure>" +
									"</article>" +
								"</div>";
						$("#gProByIdPicture").append(gProByIdPicture);
						
			var gProByIdDetail="<div class='col-xs-12'>" +
									"<div class='widget panel ptb-30 prl-20'>" +
										"<h4>"+data.groupPurchase.product.proName+"</h4>" +
										"<span>"+data.groupPurchase.product.proDescriptive+"</span><br>" +
										"<p></p>" +
										"<h4><span id=price>"+data.groupPurchase.groupPrice+"元</span></h4>" +
									"</div>" +
								"</div>" +
								"<div class='col-xs-12'>" +
									"<div class='widget panel ptb-20 prl-20'>" +
										"<i class='ico fa fa-map-marker mr-5 fa-2x'></i>" +
										"<span>辽宁省</span>&nbsp;&nbsp;" +
										"<span>沈阳市</span>&nbsp;&nbsp;" +
										"<span>浑南新区</span>" +
									"</div>" +
								"</div>" +
								"<div class='col-xs-12'>" +
									"<h4>选择版本</h4>" +
									"<div id='config1' class='config panel ptb-10 prl-20 col-xs-12 col-sm-5'>" +
										"<a >" +
											"<span>"+data.groupPurchase.product.configuration+"</span>" +
											"<span id='configPrice1' style='float:right'>"+data.groupPurchase.groupPrice+"</span>" +
										"</a>" +
									"</div>" +
								"</div>" +
								"<div class='col-xs-12'>" +
									"<h4>选择颜色</h4>" +
									"<div class='phoneColor panel ptb-10 prl-20 col-xs-12 col-sm-5' align='center'>" +
										"<a >" +
											"<span id=configColor1>"+data.groupPurchase.product.color+"</span>" +
										"</a>" +
									"</div>" +
								"</div>" +
								"<div class='col-xs-12'>" +
									"<h4>购买数量</h4>" +
									"<div class='widget panel ptb-10 prl-10'>" +
										"<div class='item col-xs-6 col-lg-6'> " +
											"<div class='row'>" +
												"<div class='col-md-12'>" +
													"<div class='form-group form-group-options'> " +
														"<div id='1' class='input-group input-group-option quantity-wrapper'>" +
															"<span  class='input-group-addon input-group-addon-remove quantity-remove btn'>" +
																"<span class='glyphicon glyphicon-minus'></span>" +
															"</span>" +
																	"<input  id='1inp' type='text' value='1' name='option[]' class='form-control quantity-count' placeholder='1'>" +
																"<span class='input-group-addon input-group-addon-remove quantity-add btn'>" +
																	"<span class='glyphicon glyphicon-plus'></span>" +
																"</span>" +
															"</div>" +
														"</div>" +
													"</div>" +
												"</div>" +
											"</div>" +
										"</div>" +
									"</div>" +
									"<div class='col-xs-12'>" +
										"<div id='choice' class='widget panel ptb-20 prl-20'>" +
											"<span>"+data.groupPurchase.product.proName+"</span>&nbsp;&nbsp;" +
											"<span>"+data.groupPurchase.product.configuration+"</span>&nbsp;&nbsp;" +
											"<span>"+data.groupPurchase.product.color+"</span>" +
											"<p></p>" +
											"<h4>总计：<span id=totalPrice>2499</span>元</h4>" +
										"</div>" +
									"</div>";
			console.log(gProByIdDetail);
			$("#gProByIdDetail").append(gProByIdDetail);
		}
	});
}

//滚动条
$(function(){
	$('#sourceDiv').scroll( function() { 
		$('#targetDiv').scrollTop($(this).scrollTop()); 
		$('#targetDiv').scrollLeft($(this).scrollLeft()); 
	}); 

	$('#targetDiv').scroll( function() { 
		$('#sourceDiv').scrollTop($(this).scrollTop()); 
		$('#sourceDiv').scrollLeft($(this).scrollLeft()); 
	}); 
});

//选择型号
function selectConfig(){
	$(".config").click(function(){
		$(".config").css("border","");
		   //	window.location=$(this).find("a").attr("href");
		   	var config=$(this).find("span").eq(0).text();
		   	price=$(this).find("span").eq(1).text();
		   	$("#choice").find("span").eq(1).text(config);
			$("#price").text(price);
			$("#totalPrice").text(price);
			
			account=parseInt(price);
			if(account>100){
	    		transportation = 0;
	    	}else{
	    		transportation=10;
	    	}
			
		   	$(this).css("border","1px solid red");
		   	selectExpress()
	});
}

//选择颜色
function selectColor(){
	$(".phoneColor").click(function(){
		$(".phoneColor").css("border","");
		  // 	window.location=$(this).find("a").attr("href");
		   	var color=$(this).find("span").text();
		   	$("#choice").find("span").eq(2).text(color);
		   	$(this).css("border","1px solid red");
	});
}

//选择购买数量
function selectQuantity(){  
  	
    //Add  
	$(document).on('click', '.quantity-add',function(){  
        //Vars  
        var count = 1;  
        var newcount = 0;          
        var elemID = $(this).parent().attr("id");  
        var countField = $("#"+elemID+'inp');  
        var count = $("#"+elemID+'inp').val();  
        var newcount = parseInt(count) + 1;         
        //Neuen Wert setzen   
        if(newcount > gResidueSum){
        	alert("不能超过库存");
        	newcount=gResidueSum;
        }
        if(newcount > 2){
        	alert("团购最多购买两件");
        	newcount=2;
        }
        $("#"+elemID+'inp').val(newcount);
        account = parseInt($("#price").text()) * newcount;
        quantity=newcount;
        if(account>100){
    		transportation = 0;
    	}else{
    		transportation=10;
    	}
        $("#totalPrice").text(account);
        
    });  
    //Remove  
	$(document).on('click', '.quantity-remove',function(){   
        var count = 1;  
        var newcount = 0;   
        var elemID = $(this).parent().attr("id");  
        var countField = $("#"+elemID+'inp');  
        var count = $("#"+elemID+'inp').val();  
        var newcount = parseInt(count) - 1;  
        count = newcount;
        if(0 >= count){
        	alert("不能为零");
        	count=1;
        }
        account = parseInt($("#price").text()) * count;
        quantity=count;
        if(account>100){
    		transportation = 0;
    	}else{
    		transportation = 10;
    	}
        $("#"+elemID+'inp').val(count); 
        $("#totalPrice").text(account);
          
    });  
  
    //Delete  
    $(".quantity-delete").click(function(e){  
        //Vars  
        var count = 1;  
        var newcount = 0;  
          
        //Wert holen + Rechnen  
        var elemID = $(this).parent().attr("id");  
        var countField = $("#"+elemID+'inp');  
        var count = $("#"+elemID+'inp').val();  
        var newcount = parseInt(count) - 1;  
          
        //Neuen Wert setzen  
        //$('.item').html('');  
          
       var row = $( ".row" );  
        $(event.target).closest(row).html('');  
    });        
}

//加载地址信息
function loadAddress(){
	
	$.ajax({
		type:"post",
		url:"loadAddressByCustomerId.action",
		async:false,
		dataType: 'json',
		data:{"customerId":cid},
		success:function(data){
			for(var i=0;i<data.length;i++){
				var address = "<div class='selectAddress' style=' float: left; margin: 0px 10px 0px 10px;'>" +
								"<input type='hidden' value='"+data[i].addressnum+"' >"+
								"<b><font size='4'>"+data[i].host+"</font></b><br />" +
								"<font size='3'>"+data[i].tele+"</font><br />" +
								"<font size='3'>"+data[i].addressDetail+"</font><br />" +
							  "</div>";
				$("#loadAddress").append(address);
			}
		}
	});
}

//选择地址信息
function selectAddress(){
	$(".selectAddress").click(function(){
		$(".selectAddress").css("border","");
		addressnum=$(this).find("input[type=hidden]").val();
		$(this).css("border","1px solid orangered");
	});
}

//是否可以包邮
function selectExpress(){
	if(account > 100){
		$("#selectExpress").html("快递(包邮)");
	}else{
		$("#selectExpress").html("快递(费用10元)");
	}
}

//选择配送时间
function selectdistributionTime(){
	
	$(".distributionTime").click(function(){
		$(".distributionTime").css("border","");
		distributionTime=$(this).find("a").text();
		$(this).css("border","1px solid orangered");
	});
}

//选择发票类型
function selectInvoice(){
	$(".selectInvoice").click(function(){
		$(".selectInvoice").css("border","");
		invoice=$(this).find("a").text();
		$(this).css("border","1px solid orangered");
		countAccount();
	});
}

//计算总金额
function countAccount(){
	$("#number").html(quantity+"件");
	$("#money").html(account+"元");
	$("#ESPay").html(transportation+"元");
	account = account+transportation;
	$("#totalPay").html(account)
}

//下单
function createGroupOrder(){
	var buybuybuy = "<a id='gotoAcount' class='btn btn-block'>去结算</a>"
		$("#buybuybuy").html(buybuybuy);
			$("#gotoAcount").click(function(){
				if(addressnum == null){
					alert("请选择地址");
				}else if(distributionTime == null){
					alert("请选择送货时间");
				}else if(invoice == null){
					alert("请选择发票类型");
				}else{
					$.ajax({
						type:"post",
						url:"createGroupOrder.action",
						async:false,
						dataType:'json',
						data:{"addressnum":addressnum,
							  "quantity":quantity,
							  "account":account,
							  "distributionTime":distributionTime,
							  "invoice":invoice,
							  "customerId":cid},
					   success:function(data){
							location.href="pay.html?groupOrder="+data;
					    }
					});
				}
			});
}
