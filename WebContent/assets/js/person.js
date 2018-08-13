$(function(){
	var user = window.localStorage.getItem("user");
	var cid = JSON.parse(user).id;
	//var cid = "1"

	
	//获得订单信息
	$.ajax({
		
		url:"customer/"+cid+".action",
		type:"get",
		dataType:"json",
		success:function(data)
		{
			//显示个人信息
			console.log(data);
			$("#customername").text(data.cname);
			$("#customerphone").text(data.cphone);
		}		
	});
});
