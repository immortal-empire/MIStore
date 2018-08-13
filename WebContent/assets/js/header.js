var user = "";

$(document).ready(function() {
	user = window.localStorage.getItem("user");
	if(user==null || user==undefined){
		user = window.localStorage.setItem("user","");
		window.localStorage.setItem("products","");
	}
	
	welcome(); //生成左上角的欢迎语
	toolbar_right(); //右上角的工具栏
	jumpToFavors();
	cartNumber();
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

		if (user == "" || user==null || user==undefined) {
			str += "请登录"
			$("#welcomelink").attr("href","signin.html");
		} else {
			str += JSON.parse(user).cname;
		}
		str += "！";
		$("#welcomeMessage").text(str);
	};

var toolbar_right = function() {
		if (user == "" || user==null || user==undefined) {
			$("#right2").find('i').removeClass("fa-sign-out");
			$("#right2").find('i').addClass("fa-lock");
			$("#right1").find("p").text("登录");
			$("#right2").find("p").text("注册");
			$("#right1").find("a").attr("href", "signin.html"); //跳转到登录
			$("#right2").find("a").attr("href", "signup.html"); //跳转到注册	
		} else {
			$("#right2").find('i').removeClass("fa-lock");
			$("#right2").find('i').addClass("fa-sign-out");
			$("#right1").find("p").text("个人中心");
			$("#right2").find("p").text("退出登录");
			$("#right1").find("a").attr("href", "person.html"); //跳转到个人中心
			$("#right2").find("a").attr("href", "#");
		}

		$("#right2").on('click', function() {
			if ($(this).find("p").text() == '退出登录') {
				window.localStorage.clear();
				//window.localStorage.removeItem("user");
				//window.localStorage.removeItem("products");
				location.href="index.html";
			}
		});
	};

var jumpToFavors = function() {
		$("#myFavors").on('click', function() {
			if (user == "") {
				$('#notLogin').modal('show');
			} else {
				location.href = "favor.html";
			}
		});
	}

var cartNumber = function(){
	var number = $(".cart-number").text();
	var count = 0;
	var products = window.localStorage.getItem("products");
		
	if(products==null || products=="" || products==undefined){
		console.log(products);
	} else {
		var carts = JSON.parse(products);
		console.log(carts);
		$.each(carts, function(item) {
			count += carts[item].quantity;
		});			
		if(count!=0) number=count;		
		$(".cart-number").text(number);		
	}
}

var getProTypes = function() {
		var proTypes = "";
		proTypes = sessionStorage["proTypes"];
		if (proTypes == null || proTypes == "") {
			$.ajax({
				type: "post",
				url: "getProTypes.action",
				async: false,
				dataType: 'json',
				success: function(data) {
					sessionStorage["proTypes"] = JSON.stringify(data);
					$.each(data, function(item) {
						jQuery("<option></option>").val(item).text(data[item]).appendTo("#proType");
					});
				}
			})
		} else {
			var data = JSON.parse(proTypes);
			$.each(data, function(item) {
				jQuery("<option></option>").val(item).text(data[item]).appendTo("#proType");
			})
		}

	};

var toggle_tags = function() {
		$(window).resize(function() {
			if ($(window).width() < 943 || $("#searchProduct").val() != null) {
				$(".tags").hide();
			} else {
				$(".tags").show();
			}
		});
		$("#searchProduct").focus(function() {
			$(".tags").hide();
		});
		$("#searchProduct").blur(function() {
			if ($(window).width() >= 943 && $("#searchProduct").val() == "") {
				$(".tags").show();
			}
		});
	};

var search_product = function() {
    	$("#searchProduct").keydown(function (e) {
    		var curKey = e.which;
    		if (curKey == 13) {
    			$("#searchBtn").click();
    			return false;
    		}
    	});
        
		$("#searchBtn").on('click', function() {
			if (typeof(Storage) !== "undefined") {
				sessionStorage["keyword"] = $("#searchProduct").val();
				sessionStorage["proType"] = $("#proType").val();
			}
			location.href = "search_product.html";
		})
	};

var drop_down = function() {

		var dropDown = "";
		dropDown = sessionStorage["dropDown"];
		if (dropDown == null || dropDown == "") {
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
					sessionStorage["dropDown"] = JSON.stringify(data);
					dropDown = JSON.stringify(data);
				}
			});
		}

		var data = JSON.parse(dropDown);
		$.each(data, function(item) {
			var products = data[item];
			if (products.length > 0) {
				dropdown_js(item);
				var tempDrop = $(".dropdown-mega-menu > input[value=" + item + "]").parents(".dropdown-mega-menu").find('.mega-menu');
				$("#dropProduct").tmpl(products, {
					getCategoryName: function() {
						return this.data.twoType.comttyName;
					},
	        		getComttyId:function(){
	        			return this.data.comttyId;
	        		}  
				}).appendTo(tempDrop);
			}
		})

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