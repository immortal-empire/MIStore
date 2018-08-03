var user="";

$(document).ready(function() {
	var json = {
			"id": 1,
			"birthday": null,
			"gender": null,
			"email": null,
			"profile": null,
			"state": "1",
			"cname": "天元逆刃",
			"cpassword": "d123",
			"cphone": "13234046121"
		};
	window.localStorage.setItem("user", JSON.stringify(json));
	user = window.localStorage.getItem("user");
	welcome();//生成左上角的欢迎语
	toolbar_right();//右上角的工具栏
	jumpToFavors();
	getProTypes();
	toggle_tags();
	search_product();
	drop_down();
});

var welcome = function() {
		var now = new Date(),
			hour = now.getHours();
		var str = '';
		if (hour < 6) {
			str = "凌晨好，"
		} else if (hour < 9) {
			str = "早上好，"
		} else if (hour < 12) {
			str = "上午好，"
		} else if (hour < 14) {
			str = "中午好，"
		} else if (hour < 17) {
			str = "下午好，"
		} else if (hour < 19) {
			str = "傍晚好，"
		} else {
			str = "晚上好，"
		}
		
		if(user!=""){
			str+=JSON.parse(user).cname;
		} else {
			str+="请登录"
		}		
		str+="！";
		$("#welcomeMessage").text(str);
	};
	
var toolbar_right = function() {
	if(user!=""){
		$("#right2").find('i').removeClass("fa-lock");
		$("#right2").find('i').addClass("fa-sign-out");
		$("#right1").find("p").text("个人中心");
		$("#right2").find("p").text("退出登录");
		$("#right1").find("a").attr("href", "http://www.baidu.com");//跳转到个人中心
		$("#right2").find("a").attr("href", "#")		
	} else{
		$("#right2").find('i').removeClass("fa-sign-out");
		$("#right2").find('i').addClass("fa-lock");
		$("#right1").find("p").text("登录");
		$("#right2").find("p").text("注册");
		$("#right1").find("a").attr("href", "http://www.baidu.com");//跳转到登录
		$("#right2").find("a").attr("href", "http://www.zhihu.com");//跳转到注册	
	}
	
	$("#right2").on('click', function() {
		if($(this).find("p").text()=='退出登录'){
			window.localStorage.setItem("user", "");
			window.location.reload();
		}
	});
	};

var jumpToFavors = function(){
	$("#myFavors").on('click', function() {
		if(user==""){
			$('#notLogin').modal('show');
		} else{
			location.href = "favor.html";
		}
	});
}	
	
var getProTypes = function() {
		$.ajax({
			type: "post",
			url: "getProTypes.action",
			async: true,
			dataType: 'json',
			success: function(data) {
				$.each(data, function(item) {
					jQuery("<option></option>").val(item).text(data[item]).appendTo("#proType");
				})
			}
		})
	};
	
var toggle_tags = function() {
		$(window).resize(function() {
			if ($(window).width() < 943 || $("#searchProduct").val()!=null) {
				$(".tags").hide();
			} else {
				$(".tags").show();
			}
		});
		$("#searchProduct").focus(function() {
			$(".tags").hide();
		});
		$("#searchProduct").blur(function() {
			if ($(window).width() >= 943 && $("#searchProduct").val()=="") {
				$(".tags").show();
			}
		});
	};
	
var search_product = function() {
		$("#searchBtn").on('click', function() {
			if (typeof(Storage) !== "undefined") {
				sessionStorage["keyword"] = $("#searchProduct").val();
				sessionStorage["proType"] = $("#proType").val();
			}
			location.href = "search_product.html";// + decodeURIComponent($(".search-form").serialize(), true)
		})
	};
	
var drop_down = function() {
		var arr = [];
		$(".dropdown-mega-menu").each(function() {
			arr.push($(this).find("input").val());
		});
		$.ajax({
			type: "post",
			url: "drop_down.action",
			async: false,
			dataType: 'json',
			traditional: true,
			data: {
				"ids": arr
			},
			success: function(data) {
				$.each(data, function(item) {
					var products = data[item];
					if (products.length > 0) {
						dropdown_js(item);
						var tempDrop = $(".dropdown-mega-menu > input[value=" + item + "]").parents(".dropdown-mega-menu").find('.mega-menu');
						$("#dropProduct").tmpl(products, {
							getCategoryName: function() {
								return this.data.twoType.comttyName;
							}
						}).appendTo(tempDrop);
					}
				})
			}
		});
	};
	
var dropdown_js = function(index) {
		var tempLi = $(".dropdown-mega-menu > input[value=" + index + "]").parents(".dropdown-mega-menu");
		tempLi.find('a').append("<span class=\"indicator\"><i class=\"fa fa-angle-down\"></i></span>");
		tempLi.on('mouseenter', function() {
			if ($(window).width() > 943) {
				$(this).children("ul, .mega-menu").fadeIn(100);
			}
		});
		tempLi.on('mouseleave', function() {
			if ($(window).width() > 943) {
				$(this).children("ul, .mega-menu").fadeOut(100);
			}
		})
	};