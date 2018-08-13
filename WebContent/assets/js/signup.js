var Ifphone=0;
var Ifcode=0;
var verifyCode;
$("#phoneNum").blur(function(){
	var phone=$("#phoneNum").val();
	//alert(phone);
	var regMobile=/^1[3,5,8]\d{9}$/;
	if(phone.length == 0) {
		$("#phoneNum").addClass('input-blur');
		$("#phoneNum").val("请输入手机号");
		Ifphone=0;
	}else {
		if(regMobile.test(phone)) {
			//alert("ok");
			Ifphone = 1;
		}else {
			$("#phoneNum").addClass('input-blur');
			$("#phoneNum").val("请输入11位有效手机号");
			Ifphone=0;
		}
	}
});

$("#phoneNum").focus(function(){
	//alert("haha");
	$("#phoneNum").removeClass('input-blur');
	if($("#phoneNum").val() == "请输入手机号") {
		$("#phoneNum").val("");
	}
	if($("#phoneNum").val() == "请输入11位有效手机号") {
		$("#phoneNum").val("");
	}
});

$("#verifyCode").blur(function(){
	var code=$("#verifyCode").val();
	var phone=$("#phoneNum").val();
	//alert(phone);
	var regCode=/^\d{6}$/;//没写前后两个‘/’整个js均不执行，很迷
	if(code.length == 0 && phone == "请输入手机号") {
		$("#verifyCode").addClass('input-blur');
		$("#verifyCode").val("请输入验证码");
		Ifcode=0;
	}else {
		if(regCode.test(code)) {
			//alert("ok");
			Ifcode=1;
		}else {
			$("#verifyCode").addClass('input-blur');
			$("#verifyCode").val("请输入六位验证码");
			Ifcode=0;
		}
	}
});

$("#verifyCode").focus(function(){
	$("#verifyCode").removeClass('input-blur');
	if($("#verifyCode").val() == "请输入验证码") {
		$("#verifyCode").val("");
	}
	if($("#verifyCode").val() == "请输入六位验证码") {
		$("#verifyCode").val("");
	}
});

$("#getCode").click(function(){
	var msg = $(this).text();
	var phone=$("#phoneNum").val();
	$.ajax({
		type:"post",
		url:"getCode/"+phone,
		dataType:"json",
		async:true,
		success: function(data) {
			console.log(data);
			console.log(data.result);
			verifyCode = data.result;
			if(verifyCode == false) {
				alert("不好意思，该用户已注册！");
			}
		}
	});
	//alert(msg);
	$(this).unbind('click');
	settime();
	
});

var myVar;
var countdown = 60;
function settime() {
	
    if(countdown == 0) {
    	//alert("为什么呢");
    	clearTimeout(myVar);
    	$("#getCode").bind('click',function(){
			var msg = $("#getCode").text();
			//alert(msg);
			var phone=$("#phoneNum").val();
			$.ajax({
				type:"post",
				url:"getCode/"+phone,
				dataType:"json",
				async:true,
				success: function(data) {
					console.log(data);
					console.log(data.result);
					verifyCode = data.result;
					if(verifyCode == false) {
						alert("不好意思，该用户已注册！");
					}
				}
			});
			$(this).unbind('click');
			settime();
		});//如果传入的是getCode(),函数将会自动执行
        $("#getCode").text("获取短信验证码");
        countdown = 60;
    } else {
        $("#getCode").text("重新发送(" + countdown + ")");
        countdown--;
        myVar = setTimeout(settime, 1000);
    }
    
};

$("#register").click(function(){
	//判断复选框是否选中
	var code=$("#verifyCode").val();
	var phone=$("#phoneNum").val(); 
	if($("#remember_account").prop('checked')) {
		
		if(Ifcode ==1 && Ifphone ==1) {
			alert("符合条件，可发起请求");
			if(code == verifyCode) {
				alert("注册成功！");
				location.href="signupDetail.html?"+"phone="+phone;
			}else{
				alert("验证码输入有误！");
			}
	
			
		}
	}else {
		alert("复选框没有选中");
	}
});

