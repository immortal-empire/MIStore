$(document).on('ready', function() {
	 banner_js();
	 banner_product();
});

//加载侧边栏
var banner_js = function() {
    $('.nav-coupon-category > li:has(.pop) > a').append("<i class=\"fa fa-angle-right\"></i>");               
    
    $(".nav-coupon-category > li:has(.pop)").on('mouseenter', function () {
        if ($(window).width() > 943) {
            $(this).children(".pop").fadeIn(500);
        }
    });
    $(".nav-coupon-category > li:has(.pop)").on('mouseleave', function () {
        if ($(window).width() > 943) {
            $(this).children(".pop").fadeOut(500);
        }
    });
};

//侧边栏的商品
var banner_product = function(){
	var arr = [];
	var a = $(".nav-coupon-category > li:has(.pop)").each(function(){
		arr.push($(this).find("input").val());		
	});
	
    $.ajax({
        type:"post",
        url:"banner_product.action",//对应controller的URL
        async:false,
        dataType: 'json',
        traditional: true,
        data:{
        	"ids":arr
        },
        success:function(data) {
        	alert(123);
    	}  
    });
}

var collection = function() { 
    var products = [{ ID: 'hao1', Name: 'Tony' }, { ID: 'hao2', Name: 'Mary hui'}];
	$("#popProduct").tmpl(products).appendTo("#poprow"); 
}

//获取商品信息
var getProducts = function(){
	
}

//加载下拉栏