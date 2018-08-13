//var products = [
//	{proId:1,comttyId:1,proName:"小米8",color:"曜石黑",configuration:"4G+64G",picture:"",inventory:100,sellingPrice:2699.00,proDescriptive:"全面屏 / 红外人脸识别",addtime:"2018-07-16 01:07:05",state:"1"},
//	{proId:2,comttyId:1,proName:"小米8",color:"曜石黑",configuration:"4G+128G",picture:"",inventory:100,sellingPrice:2999.00,proDescriptive:"全面屏 / 红外人脸识别",addtime:"2018-07-16 23:07:10",state:"1"},
//	{proId:3,comttyId:1,proName:"小米8",color:"赤焰红",configuration:"4G+64G",picture:"",inventory:100,sellingPrice:2699.00,proDescriptive:"全面屏 / 红外人脸识别",addtime:"2018-07-18 02:05:49",state:"1"}
//];

var colorNum;
var configNum;
var products = [];
$(document).ready(function() {
	//鼠标滚动时上方导航栏固定
    var navOffset=$("#nav").offset().top;  
    $(window).scroll(function(){  
        var scrollPos=$(window).scrollTop();  
        if(scrollPos >=navOffset){  
            $("#nav").addClass("fixed");  
        }else{  
            $("#nav").removeClass("fixed");  
        }  
    });
    
    //模拟用户未登录，之后可删
    //window.localStorage.setItem("user","");
    //window.localStorage.setItem("products","");
    
    var n1 = location.href.length;//地址的总长度
  	var n2 = location.href.indexOf("=");//取得=号的位置
  	var comttyId = location.href.substr(n2+1, n1-n2);//从=号后面的内容
  	//根据商品二级分类id获取商品信息       ??  或许应该根据商品名查
  	console.log(comttyId);
  	$.ajax({
  		type:"post",
  		url:"getProductInfoByComttyId/"+comttyId,
  		async:false,
  		javaType:"json",
  		success:function(data) {
  			$("#photoBody").html();
  			$("#productConfig").html();
  			$("#productColor").html();
  			$("#choice").html();
  			products = data;
  			console.log(products);
  			var photoBody = '<article class="entry panel">'+
                                '<figure class="entry-media">'+
                                    '<div class="owl-slider" data-loop="true" data-autoplay="true" data-autoplay-timeout="10000" data-smart-speed="1000" data-nav-speed="false" data-nav="true" data-xxs-items="1" data-xxs-nav="true" data-xs-items="1" data-xs-nav="true" data-sm-items="1" data-sm-nav="true" data-md-items="1" data-md-nav="true" data-lg-items="1" data-lg-nav="true">'+
                                        '<figure class="item">'+
                                            '<img src="assets/images/products/'+data[0].picture+'/black0!560x560.jpg" alt="">'+
                                        '</figure>'+
                                        '<figure class="item">'+
                                            '<img src="assets/images/products/'+data[0].picture+'/black1!560x560.jpg" alt="">'+
                                        '</figure>'+
                                        '<figure class="item">'+
                                            '<img src="assets/images/products/'+data[0].picture+'/black2!560x560.jpg" alt="">'+
                                        '</figure>'+
                                        '<figure class="item">'+
                                            '<img src="assets/images/products/'+data[0].picture+'/black3!560x560.jpg" alt="">'+
                                        '</figure>'+
                                    '</div>'+
                                '</figure>'+
                            '</article>';
            $("#photoBody").append(photoBody);                
  		}
  	}); 	

	console.log(products[0].proName);
	var productHeader = '<div class="widget panel ptb-30 prl-20">';
	productHeader += '<h4>'+products[0].proName+'</h4>';
	productHeader += '<span>' + products[0].proDescriptive +'</span><br><p></p>';
	productHeader += '<h4><span id=price>'+products[0].sellingPrice+'元</span></h4>';
	productHeader += '<span id="proId" style="display :none">'+products[0].proId+'</span></div>';
  	$("#productHeader").append(productHeader);
  	for(var i=0; i<products.length; i++) {
  		var flag1 = false;
  		var flag2 = false;
  		for(var j=i+1; j<products.length; j++) {
  			if(products[j].color == products[i].color){
  				flag1 = true;
  				break;
  			}
  		}
  		for(var j=i+1; j<products.length; j++) {
  			if(products[j].configuration == products[i].configuration){
  				flag2 = true;
  				break;
  			}
  		}
  		if(flag1 == false) {
  			var productColor = '<div class="phoneColor panel ptb-10 prl-20 col-xs-12 col-sm-5" align="center">';
  			productColor += '<a><span>'+products[i].color+'</span></a></div>';
  			$("#productColor").append(productColor);
  		}
  		if(flag2 == false) {
  			var productConfig = '<div id="config'+i+'" class="config panel ptb-10 prl-20 col-xs-12 col-sm-5">';
	  		productConfig += '<a><span>'+products[i].configuration+'</span><span id="configPrice'+i;
	  		productConfig += '" style="float:right">'+ products[i].sellingPrice + '</span></a></div>';
	  		$("#productConfig").append(productConfig);
	  		//console.log(productConfig);
  		}
  	}
  	
  	var choice = '<span>'+products[0].proName+'</span>&nbsp;&nbsp;';
  	choice += '<span>'+products[0].configuration+'</span>&nbsp;&nbsp;';
  	choice += '<span>'+products[0].color+'</span><p></p>';
  	choice += '<h4>总计：<span id="totalPrice">'+products[0].sellingPrice+'</span>元</h4>';
  	$("#choice").append(choice);
  	
  	$("#nav").find("a").eq(3).attr("href", "comments.html?proId="+products[0].proId);
  	$("#nav").find("a").eq(4).css("display", "none");
});

$(document).on("click", ".config", function(){ 
	$(".config").css("border","");
   	//window.location=$(this).find("a").attr("href");
   	var config=$(this).find("span").eq(0).text();
   	var price=$(this).find("span").eq(1).text();
   	$("#choice").find("span").eq(1).text(config);
	$("#price").text(price);
	console.log(config);
	console.log(price);
	$("#totalPrice").text(price);
   	$(this).css("border","1px solid red");
   	
   	getThisColor(config);
});

$(document).on("click", ".phoneColor", function(){ 
	
	//console.log("这个可以点击！");
	//console.log($("#configPrice1").text());
	$(".phoneColor").css("border","");
   	//window.location=$(this).find("a").attr("href");
   	var color=$(this).find("span").text();
   	console.log(color);
   	$("#choice").find("span").eq(2).text(color);
   	$(this).css("border","1px solid red");
   	//得到商品id
   	var proId;
   	var configuration = $("#choice").find("span").eq(1).text();
   	for(var i=0; i<products.length; i++) {
   		if(products[i].configuration == configuration && products[i].color == color)
   			proId = products[i].proId;
   	}
   	$("#proId").text(proId);
   	$("#nav").find("a").eq(3).attr("href", "comments.html?proId="+proId);
});

$("#addProductToCart").click(function(){
	var proName = $("#choice").find("span").eq(0).text();
	var configuration = $("#choice").find("span").eq(1).text();
	var color = $("#choice").find("span").eq(2).text();
	var proId = $("#proId").text();
	console.log(proId+proName+configuration+color);
	//发起请求，将商品加入购物车   ?? 参数为商品id 或是以上三个参数
	//需判断用户是否登录，以下模拟用户已登录
	var user = window.localStorage.getItem("user");
	if( user== null || user.length == 0) {
		//用户未登录
		console.log("用户未登录");
		saveCartInfoToLocal(parseInt(proId));
	}else {
		//用户已登录
		var cid = JSON.parse(user).id;
		console.log(cid);
		saveCartInfoToDB(cid,parseInt(proId));
	}
	
	location.href="cartAndOrder/cart.html";
//	$.ajax({
//		type:"post",
//		url:"addProductToCart/"+cid,
//		async:true,
//		dataType:"json",
//		data:{
//			proName: proName,
//			configuration: configuration,
//			color: color
//		},
//		//返回的是CartProductInfo对象
//		success:function(data){
//			//更新本地LocalStorage
//			console.log(data);
//			window.localStorage.setItem("products",JSON.stringify(data));
//			console.log(window.localStorage.getItem("products"));
//		},
//	});
});

function getThisColor(config) {
	$("#productColor").html("");//先清空div中的内容
	for(var i=0; i<products.length; i++) {
		if(products[i].configuration == config) {
  			var productColor = '<div class="phoneColor panel ptb-10 prl-20 col-xs-12 col-sm-5" align="center">';
  			productColor += '<a href="#"><span>'+products[i].color+'</span></a></div>';
  			$("#productColor").append(productColor);
  		}
	}
};

function saveCartInfoToLocal(proId) {
	//这里得到的是String，需要转换为JSON对象，最后ajax发请求时应该JSON对象转为JSON字符串
	var old = window.localStorage.getItem("products");
	var dataLocal;
	if( old== null || old.length == 0) {
		dataLocal = null;
	}else {
		dataLocal = JSON.parse(old);
		console.log(JSON.stringify(dataLocal));
	}
	$.ajax({
		type:"post",
		url:"getCartInfoByProId/"+proId,
		async:false,
		dataType:"json",
		contentType:"application/json",//@RequestBody识别json字符串
		data:JSON.stringify(dataLocal),
		//返回的是CartProductInfo对象
		success:function(data){
			//更新本地LocalStorage, 应该为追加, 如果已存在则购买数量加1, 未存在则新增一条记录
			//改为在Service层实现
			window.localStorage.removeItem("products");
			window.localStorage.setItem("products",JSON.stringify(data));
			console.log(window.localStorage.getItem("products"));
		},
	});
};

function saveCartInfoToDB(cid,proId) {
	$.ajax({
		type:"post",
		url:"addProductToCart/"+cid+"/"+proId,
		async:false,
		dataType:"json",
		//返回的是CartProductInfo对象
		success:function(data){
			//更新本地LocalStorage, 应该为追加, 如果已存在则购买数量加1, 未存在则新增一条记录
			//改为在Service层实现
			window.localStorage.removeItem("products");
			window.localStorage.setItem("products",JSON.stringify(data));
			console.log(window.localStorage.getItem("products"));
		},
	});
}
