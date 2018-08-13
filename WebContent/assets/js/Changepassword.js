$(function(){
	var user = window.localStorage.getItem("user");
	var cid = JSON.parse(user).id;
	//var cid = "3"
		$("#changepassword").click(function(){
			newPassword=$("#new1").val();
			new2=$("#new2").val();
			oldPassword=$("#oldpassword").val();
		if(newPassword==new2){
			
			$.ajax({
				url:"changepassword/"+cid+"/"+newPassword+"/"+oldPassword+".action",
				type:"post",
				dataType:"json",
				success : function(data)
				{
					alert(data.result);
					
//					$("#customername").text(data.cname);
//					$("#customerphone").text(data.cphone);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
		        	//这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
		        	alert(XMLHttpRequest.responseText); 
		        	//window.location.reload();
		        	location.hredf="person.html";
		        }
			});
		}
		else{
			alert("两次密码不一致");
		}

		
	//获得订单信息
	
});
});
