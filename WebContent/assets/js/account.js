//var cid="3"

var user = window.localStorage.getItem("user");
var cid = JSON.parse(user).id;

$("#store").click(function(){
	cname = $("#cname").val();
	cname=encodeURI(cname);
	cname=encodeURI(cname);//二次编码
	gender= $("input[name='gender']:checked").val();
	//alert("haha")
	$.ajax({
		type: "post",
		url: "store/"+cid+"/"+cname+"/"+gender+".action",
		async: false,
		dataType: 'json',//返回值类型
		contentType: "application/json",
		
		success: function(data) {//请求成功的回调函数，data是返回的数据
			//alert("haha");
			//window.history.back();
			location.href="person.html";
			console.log(data);
			window.localStorage.setItem("user",JSON.stringify(data));
		},
		error: function(data){
			//alert("lala");
			location.href="person.html";
			
		}
	});
});

