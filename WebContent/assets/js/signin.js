$(document).ready(function(){
	if(typeof(Storage)!=="undefined")
	{
	    // 是的! 支持 localStorage  sessionStorage 对象!
	    // 一些代码.....
	    alert("支持");
	   	window.localStorage.setItem("products","");
	} else {
		alert("抱歉! 不支持 web 存储");
	    // 抱歉! 不支持 web 存储。
	}
});

var Ifname=0;
var Ifpassword=0;
$("#username").blur(function(){
	var name=$("#username").val();
	//alert(name);
	if(name.length == 0) {
		$("#username").addClass('input-blur');
		$("#username").val("请输入用户名或手机号");
		Ifname=0;
	}else if($("#username").val() != "请输入用户名或手机号"){
		Ifname=1;
	}
});

$("#username").focus(function(){
	//alert("haha");
	Ifname=0;
	$("#username").removeClass('input-blur');
	$("#message").css("display","none");
	if($("#username").val() == "请输入用户名或手机号") {
		$("#username").val("");
	}
});

$("#password").blur(function(){
	var password=$(this).val();
	//alert(name);
	var regPwd = /^[a-zA-Z]\w{3,7}$/;
	if(regPwd.test(password)) {		
		//alert(password);
		Ifpassword = 1;
		//Ifname=0;
	}else {
		Ifpassword = 0;
		$(this).addClass('input-blur');		
		$(this).val("");
	}
});

$("#password").focus(function(){
	//alert("haha");
	$("#password").removeClass('input-blur');
	$("#message").css("display","none");
	
	Ifpassword = 0;
});


$("#login").click(function(){
	//判断复选框是否选中
	var name=$("#username").val();
	var password=$("#password").val(); 
	if($("#remember_account").prop('checked')) {
		alert("记住用户名密码");		
	}
	//location.href="addShoppingCart.html?"+"comttyId="+1;
	if(Ifname ==1 && Ifpassword ==1) {
		//alert("符合条件，可发起请求");
		
		$.ajax({
			type:"post",
			url:"login",
			async:true,
			dataType:"json",
			data:{
				Cname: name,
				Cpassword: password,
			},
			success:function(data)
			{
				console.log(data);
				//alert(data.cname);
				window.localStorage.setItem("user",JSON.stringify(data));
				var user = window.localStorage.getItem("user");
				console.log(user);
	            console.log(typeof window.localStorage["user"]);
	            //updateCart(data.id);
	            var old = window.localStorage.getItem("products");
	            alert(old);
				if( old== null || old.length == 0) {
					getProductInCart(data.id);
					console.log("LocalStorage中没有商品,从数据库中读取");
				}else {
					alert("LocalStorage中有商品, 更新数据库");
					updateCart(data.id,JSON.parse(old));
				}
				//location.href="addShoppingCart.html?"+"comttyId="+1;
			},
//	        error : function(XMLHttpRequest, textStatus, errorThrown) {
//	        	//这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
//	        	alert(XMLHttpRequest.responseText); 
//	        	alert(XMLHttpRequest.status);
//	        	alert(XMLHttpRequest.readyState);
//	        	alert(textStatus); // parser error;
//      	}
		});	
		//location.href="signupDetail.html?"+"phone="+phone;
	}else {
		alert("有未填项");
	}
});

//JSON.parse() 字符串-》对象
//JSON.stringify() 对象-》字符串
function getProductInCart(cid){
	$.ajax({
		type:"post",
		url:"getProductInCart/"+cid,
		async:true,
		success:function(data){
			//alert("haha");
			console.log(data);
			window.localStorage.setItem("products",JSON.stringify(data));
			console.log(window.localStorage.getItem("products"));
		},
	});	
};

function updateCart(cid,old) {
	console.log(old);
	$.ajax({
		type:"post",
		url:"updateCart/"+cid,
		async:true,
		dataType:"json",
		contentType:"application/json",//@RequestBody识别json字符串
		data:JSON.stringify(old),
		success:function(data)
		{
			console.log("更新后localStorage");
			window.localStorage.setItem("products",JSON.stringify(data));
			console.log(window.localStorage.getItem("products"));			
			//alert(data.cname);
			//window.localStorage.setItem("user",JSON.stringify(data));
            //console.log(typeof window.localStorage["user"]);
            //getProductInCart(data.id);
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

