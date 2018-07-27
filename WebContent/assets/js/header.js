$(document).ready(function(){
	welcome();
	toggle();
	getProTypes();
	toggle_tags();
	search_product();
});

//生成欢迎消息
var welcome = function(){		
	var now = new Date(),hour = now.getHours();
	var str = '';
	if(hour < 6){str = "凌晨好,";}
	else if(hour < 9){str = "早上好,";}
	else if(hour < 12){str = "上午好,";}
	else if(hour < 14){str = "中午好,";}
	else if(hour < 17){str = "下午好,";}
	else if(hour < 19){str = "傍晚好,";}
	else{str = "晚上好,";}
	$("#wlcmsg").text(str);
	
};

//登录或退出登录以后更改一下右上角的两个选项
var toggle = function(){
	if($("#right2").find('i').hasClass('fa-lock')){
		//更改图标
		$("#right2").find('i').removeClass("fa-lock");	
		$("#right2").find('i').addClass("fa-sign-out");
		//更改文字
		$("#right1").find("p").text("个人中心");
		$("#right2").find("p").text("退出登录");		
		//更改链接
		$("#right1").find("a").attr("href","http://www.baidu.com");
		$("#right2").find("a").attr("href","http://www.zhihu.com");
	}else{
		//更改图标
		$("#right2").find('i').removeClass("fa-sign-out");     
		$("#right2").find('i').addClass("fa-lock");	
		//更改文字
		$("#right1").find("p").text("登录");
		$("#right2").find("p").text("注册");
		//更改链接
		$("#right1").find("a").attr("href","http://www.baidu.com");
		$("#right2").find("a").attr("href","http://www.zhihu.com");
	}
};

var getProTypes = function(){
	var data = {"123":"小米手机","345":"熊春晖"};
	$.each(data, function(item) {  
        jQuery("<option></option>").val(item).text(data[item]).appendTo("#proType");
	}); 
};

var toggle_tags = function(){
	$(window).resize(function () { 
        if ($(window).width() < 943) {
            $(".tags").hide();
        }else{
        	$(".tags").show(); 	        	
        } 		
	});
	
	$("#searchProduct").focus(function(){
        if ($(window).width() >= 943) {
  			$(".tags").hide();	        	
        }	      
	});
	
	$("#searchProduct").blur(function(){
        if ($(window).width() >= 943) {
	  		$(".tags").show();
	  	}
	}); 
};

var search_product = function(){
	$("#searchBtn").on('click', function(){
		alert(decodeURIComponent($(".search-form").serialize(),true));
	    $.ajax({
	        type:"post",
	        url:"searchProduct.action",//对应controller的URL
	        async:false,
	        dataType: 'json',
	        data:decodeURIComponent($(".search-form").serialize(),true),
	        success:function(data) {
	        	alert(123);
	    	}  
	    });
	});
}

function getHistory(){
	localStorage.setItem('keyword', ['熊晓美','熊22']);
	localStorage.removeItem('keyword');
    var searchArr;

    if(localStorage.keyword){
        searchArr= localStorage.keyword.split(",")
    	alert(searchArr);
    }else{
        searchArr = [];
    }
    
    
}