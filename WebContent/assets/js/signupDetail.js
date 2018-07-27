var Ifname=0;
var Ifpassword = 0;
var Ifrightpwd = 0;
$("#username").blur(function(){
	var name=$(this).val();
	//alert(name);
	if(name.length == 0) {
		$(this).addClass('input-blur');
		Ifname=0;
	}else {
		//局部刷新，判断昵称是否重复
		Ifname=1;
	}
});

$("#username").focus(function(){
	//alert("haha");
	$(this).removeClass('input-blur');
});

$("#pwd").blur(function(){
	var password=$(this).val();
	//alert(name);
	var regPwd = /^[a-zA-Z]\w{3,7}$/;
	if(regPwd.test(password)) {
		
		//alert(password);
		Ifpassword = 1;
		$("#Ifpwd").css("display","block");
		//Ifname=0;
	}else {
		Ifpassword = 0;
		$(this).addClass('input-blur');
		$("#Ifpwd").css("display","none");		
		$(this).val("");
	}
});

$("#pwd").focus(function(){
	//alert("haha");
	$("#pwd").removeClass('input-blur');
	$("#Ifrpwd").val("");
	$("#Ifrpwd").css("display","none");
	
	Ifrpassword = 0;
});

$("#rpwd").blur(function(){
	var password=$("#pwd").val();
	var rpassword=$(this).val();
	//alert(name);
	var regPwd = /^[a-zA-Z]\w{3,7}$/;
	if(rpassword == password && rpassword.length != 0) {
		
		//alert(password);
		Ifrpassword = 1;
		$("#Ifrpwd").css("display","block");
		//Ifname=0;
	}else {
		Ifrpassword = 0;
		$(this).addClass('input-blur');
		$("#Ifrpwd").css("display","none");
		$(this).val("");
	}
});

$("#rpwd").focus(function(){
	//alert("haha");
	$(this).removeClass('input-blur');
});

$("#register").click(function(){
	if(Ifname == 0 ) {
		$("#username").addClass('input-blur');
	}else if(Ifpassword == 0){
		$("#pwd").addClass('input-blur');
		$("#Ifpwd").css("display","none");
	}else if(Ifrpassword == 0) {
		$("#rpwd").addClass('input-blur');
		$("#Ifrpwd").css("display","none");
	} else {
		//ajax发请求
		var n1 = location.href.length;//地址的总长度
  		var n2 = location.href.indexOf("=");//取得=号的位置
  		var phone = location.href.substr(n2+1, n1-n2);//从=号后面的内容
  		var Cname = $("#username").val();
  		var Cpassword = $("#pwd").val();
		//alert("发起请求"+phone);
		$.ajax({				
			url:"register",
			type:"post",
			dataType:"json",
			data:{
				Cname: Cname,
				Cpassword: Cpassword,
				Cphone: phone
			},
			success:function(data)
			{
				if(data.result) {
					alert("注册成功！");
				}else {
					alert("注册失败！");
				}
				
			},
	        error : function(XMLHttpRequest, textStatus, errorThrown) {
	        	//这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
	        	alert(XMLHttpRequest.responseText); 
	        	alert(XMLHttpRequest.status);
	        	alert(XMLHttpRequest.readyState);
	        	alert(textStatus); // parser error;
        	}
		});
	}
});