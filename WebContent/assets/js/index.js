$(document).on('ready', function() {	
	$("#header").load("header.html");
    $("#footer").load("footer.html");
	banner_product();
	display_products();
	recommend();
	hot_comment_product();
});

//加载侧边栏
var banner_js = function(index) {
	var tempLi = $(".nav-coupon-category > li:has(.pop) > a > input[value=" + index + "]").parents("li");
    tempLi.find('a').append("<i class=\"fa fa-angle-right\"></i>");               
	  
    tempLi.on('mouseenter', function () {
        if ($(window).width() > 943) {
            $(this).children(".pop").fadeIn(500);
        }
    });
    tempLi.on('mouseleave', function () {
        if ($(window).width() > 943) {
            $(this).children(".pop").fadeOut(500);
        }
    });
};

//侧边栏的商品
var banner_product = function(){
	var products = "";
	products = sessionStorage["index_banner_product"];
	
	if(products==null || products==""){
		var arr = [];
		$(".nav-coupon-category > li:has(.pop)").each(function(){
			arr.push($(this).find("input").val());		
		});		
	    $.ajax({
	        type:"post",
	        url:"banner_product.action",
	        async:false,
	        dataType: 'json',
	        traditional: true,
	        data:{
	        	"ids":arr
	        },
	        success:function(data) {
	        	console.log(JSON.stringify(data));
	        	sessionStorage["index_banner_product"] = JSON.stringify(data);
	        	products = JSON.stringify(data);
	    	} 
	    });		
	}
	
	var data = JSON.parse(products);
    $.each(data, function(item) { 
    	var products = data[item];
    	if(products.length>0){
    		//添加图标以及鼠标监听
    		banner_js(item);
    		
    		//往弹出层中添加元素
        	var tempPop = $(".nav-coupon-category > li:has(.pop) > a > input[value=" + item + "]").parents("li").find('.row');
        	$("#popProduct").tmpl(products, {
        		getCategoryName:function() {
        			return this.data.twoType.comttyName;
        		}
        	}).appendTo(tempPop); 
    	}
    }); 

};

//商品分类展示
var display_products = function(){

	var products = "";
	products = sessionStorage["index_display_product"];
	
	if(products==null || products==""){
	    $.ajax({
	        type:"post",
	        url:"display_products.action",
	        async:false,
	        dataType: 'json',
	        success:function(data) {
	        	console.log("分类展示"+JSON.stringify(data));
	        	displayRender(data);
	        	sessionStorage["index_display_product"] = JSON.stringify(data);	
	    	}  
	  
	    });
	} else {
		displayRender(JSON.parse(products));
	}

};

var displayRender = function(data){
    $.each(data, function(item) {
    	if(data[item].length>0){
        	$("#displaySection").tmpl(null, {
        		getSectionName:function() {
        			return item;
        		},
        		getSectionId:function(){
        			var comtyId='#';
        			if(data[item].length>0) comtyId=data[item][0].twoType.comtyId;
        			return comtyId;
        		}
        	}).appendTo("#displayProducts");
        	
            var section = $("#"+item).find(".row-tb-20");
            
        	$("#displayItem").tmpl(data[item], {
        	}).appendTo(section);            		
    	}
    });
}

//热评商品
var hot_comment_product = function() { 
    $.ajax({
        type:"post",
        url:"hot_comment_product.action",
        async:false,
        dataType: 'json',
        success:function(data) {   
        	$("#hotComments").tmpl(data, {
        		getProName:function(){
        			return this.data.product.proName;
        		},
        		getPrice:function(){
        			return this.data.product.sellingPrice;
        		}
        	}).appendTo("#comments"); 
    	} 
    });
};

//为您推荐
var recommend = function() {
    $.ajax({
        type:"post",
        url:"recommend.action?limit=12",
        async:false,
        dataType: 'json',
        success:function(data) { 
        	$("#recommendItem").tmpl(data, {
        	}).appendTo("#recommend"); 
    	} 
    });	
}
