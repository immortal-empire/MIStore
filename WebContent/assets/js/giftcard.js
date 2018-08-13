
var user = window.localStorage.getItem("user");
var cid = JSON.parse(user).id;
$("#addgiftcard").click(function(){

	password = $("#giftpassword").val();
	alert(password)
	$.ajax({
		url:"gift/"+cid+"/"+password+".action",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			
		}		
	});
});
