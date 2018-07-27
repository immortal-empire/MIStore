var products = [
	{proId:1,comttyId:1,proName:"小米8",color:"曜石黑",configuration:"4G+64G",picture:"",inventory:100,sellingPrice:2699.00,proDescriptive:"全面屏 / 红外人脸识别",addtime:"2018-07-16 01:07:05",state:"1"},
	{proId:2,comttyId:1,proName:"小米8",color:"曜石黑",configuration:"4G+128G",picture:"",inventory:100,sellingPrice:2999.00,proDescriptive:"全面屏 / 红外人脸识别",addtime:"2018-07-16 23:07:10",state:"1"},
	{proId:3,comttyId:1,proName:"小米8",color:"赤焰红",configuration:"4G+64G",picture:"",inventory:100,sellingPrice:2699.00,proDescriptive:"全面屏 / 红外人脸识别",addtime:"2018-07-18 02:05:49",state:"1"}
];

var colorNum;
var configNum;
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
    
    var n1 = location.href.length;//地址的总长度
  	var n2 = location.href.indexOf("=");//取得=号的位置
  	var comttyId = location.href.substr(n2+1, n1-n2);//从=号后面的内容
  	//根据商品二级分类id获取商品信息       ??  或许应该根据商品名查
//	<div class="widget panel ptb-30 prl-20">
//	    <h4>小米8</h4>
//	    <span>全球首款双频GPS / 骁龙845处理器 / 红外人脸解锁 / AI变焦双摄 / 三星 AMOLED 屏</span><br>
//	    <p></p>
//	    <h4><span id=price>2699元</span></h4>
//	</div>
//	<div id="config1" class="config panel ptb-10 prl-20 col-xs-12 col-sm-5">
//      <a href="#">
//      	<span>4GB+64GB</span>
//      	<span id=configPrice1 style="float:right">2699</span>	
//      </a>            
//  </div>
	console.log(products[0].proName);
	var productHeader = '<div class="widget panel ptb-30 prl-20">';
	productHeader += '<h4>'+products[0].proName+'</h4>';
	productHeader += '<span>' + products[0].proDescriptive +'</span><br><p></p>';
	productHeader += '<h4><span id=price>'+products[0].sellingPrice+'元</span></h4></div>';
  	
  	$("#productHeader").append(productHeader);
  	for(var i=0; i<products.length; i++) {
  		if(products[i].color == products[0].color) {
  			var productConfig = '<div id="config'+i+'" class="config panel ptb-10 prl-20 col-xs-12 col-sm-5">';
	  		productConfig += '<a href="#"><span>'+products[i].configuration+'</span><span id=configPrice'+i;
	  		productConfig += ' style="float:right">'+ products[i].sellingPrice + '</span></a></div>';
	  		$("#productConfig").append(productConfig);
  		}
  		
  	}
  	
});

$(".config").click(function(){
	$(".config").css("border","");
   	window.location=$(this).find("a").attr("href");
   	var config=$(this).find("span").eq(0).text();
   	var price=$(this).find("span").eq(1).text();
   	$("#choice").find("span").eq(1).text(config);
	$("#price").text(price);
	$("#totalPrice").text(price);
   	$(this).css("border","1px solid red");
});

$(".phoneColor").click(function(){
	$(".phoneColor").css("border","");
   	window.location=$(this).find("a").attr("href");
   	var color=$(this).find("span").text();
   	$("#choice").find("span").eq(2).text(color);
   	$(this).css("border","1px solid red");
});