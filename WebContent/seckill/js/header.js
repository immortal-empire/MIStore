var user = "";

$(document).ready(function() {
	user = window.localStorage.getItem("user");
	if(user==null || user==undefined){
		user = window.localStorage.setItem("user","");
	}
	
	welcome(); //生成左上角的欢迎语
	toolbar_right(); //右上角的工具栏
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
			$("#welcomelink").attr("href","../signin.html");
		} else {
			str += JSON.parse(user).cname;
		}
		str += "！";
		$("#welcomeMessage").text(str);
	};

var toolbar_right = function() {
		if (user == "" || user==null || user==undefined) {
			$("#right1").text("登录");
			$("#right2").text("注册");
			$("#right1").find("a").attr("href", "../signin.html"); //跳转到登录
			$("#right2").find("a").attr("href", "../signup.html"); //跳转到注册	
		} else {
			$("#right1").text("个人中心");
			$("#right2").text("退出登录");
			$("#right1").attr("href", "../person.html"); //跳转到个人中心
			$("#right2").attr("href", "#");
		}

		$("#right2").on('click', function() {
			if ($(this).text() == '退出登录') {
				window.localStorage.clear();
				//window.localStorage.removeItem("user");
				location.href="../index.html";
			}
		});
	};