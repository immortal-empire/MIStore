/**
 * 
 */
$(document).ready(function(){
	init_city_select($("#citySelect"));
})


//删除地址
/*$("#delete").click(function(){
	var user=windows.LocalStorage.getItem("user");
	var cid=JSON.parse(user).id;
	$.ajax({
		type: "post",
		url: "delete/"+cid+".action",
		async: false,
		dataType: 'json',//返回值类型
		contentType: "application/json",
		
		success: function(data) {//请求成功的回调函数，data是返回的数据
			alert("haha")
			//刷新页面
			window.location.href="address.html";
			
			
		}
	});
});*/




//全局变量保存选择的配送时间
var sendTime;
var invoiceType;
var giftCardId=0;
//校验地址输入框
function validateTel(){
	var res=/^[1][3,4,5,7,8][0-9]{9}$/;
	var result=$("#tel").val();
	//alert(result);
	if(!res.test(result)){
		alert(res.test(result));
		$("#telError").show();
		//按钮失效
		$("#bton").attr("disabled",  true);
	} else{
		$("#telError").hide();
		//alert(res.test(result))
		$("#bton").attr("disabled",  false);
	}
}
//校验邮编
function validatePostcode(){
	var res=/^[1-9][0-9]{5}$/;
	var result=$("#postcode").val();
	//alert(result);
	if(!res.test(result)){
		//alert(res.test(result));
		$("#postcodeError").show();
		//按钮失效
		$("#bton").attr("disabled",  true);
	} else{
		$("#postcodeError").hide();
		//alert(res.test(result))
		$("#bton").attr("disabled",  false);
	}
}



/*用户信息的滑动显示*/
$("#user").mouseenter(function(){
	$("#userinfo").slideDown();
})
$("#user").mouseleave(function(){
	$("#userinfo").slideUp();
})


var orderBody = new Vue({
	el:'#orderBody',
	data:{
		user:JSON.parse(window.localStorage.getItem("user")),//获得用户信息
		addresses:getAddress(),//获得用户已有地址/*[{"consumerName":"hhh","telephone":"1234567","addressDetail":"asffdfsdfds"}]*/
		addressPx:0,//动态设置地址栏的高度
	},
	mounted(){
		//设置地址栏的height
		this.addressPx=Math.ceil((this.addresses.length+1)/4)*240;
		/*alert(this.size);*/
	},
	methods:{
		//选择地址
		selectItem:function(addressItem){
			var count=0;
			for(var i=0;i<this.addresses.length;i++){
				if(this.addresses[i].isSelected){
					count++;
				}
			}
			//alert(count);
			if(count!=0){
				for(var i=0;i<this.addresses.length;i++){
					this.addresses[i].isSelected=false;
				}
			}
			addressItem.isSelected=!addressItem.isSelected;
			//alert(addressItem.isSelected);
		},
		//当修改地址时将数据加到input输入框中
		initInput:function(addressItem,index,status){
			//alert(address[0]+" "+address[1]);
			if(status==1){
				//alert(1);
				var address=addressItem.addressDetail.split("区");
				$("#name").attr("value",addressItem.consumerName);
				$("#tel").attr("value",addressItem.telephone);
				$("#citySelect").attr("value",address[0]+"区");
				$("#detail").attr("value",address[1]);
				$("#postcode").attr("value",addressItem.postcode);
				$("#index").attr("value",index);
			} else{
				//alert(0);
				$("#body input").attr("value","");
			}
		},
		//保存修改的地址和新增的地址
		saveAddress:function(){
			var index=$("#index").val();
			var consumerName=$("#name").val();
			//alert(consumerName);
			var telephone=$("#tel").val();
			var addressDetail=$("#citySelect").val()+""+$("#detail").val();
			var postcode=$("#postcode").val();
			if(consumerName==""||consumerName==null||consumerName==undefined||
					telephone==null||telephone==""||telephone==undefined||
					addressDetail==null||addressDetail==""||addressDetail==undefined||
					postcode==null||postcode==""||postcode==undefined){
				alert("填写信息错误！");
				$("#bton").attr("disabled",  true);
			} else{
				$("#bton").attr("disabled",  false);
				//alert(index);
				//alert($("#name").val()+" "+$("#tel").val()+" "+$("#citySelect").val()+""+$("#detail").val()+" "+$("#postcode").val());
				if(index==""||index==null||index==undefined){
					//alert(2);
					var newAddress = new createAddress(0, this.user.id, consumerName, telephone, postcode, addressDetail, false);
					//alert(JSON.stringify(newAddress));
					this.addresses.push(newAddress);
					//alert(JSON.stringify(this.addresses));
					updateAddress(newAddress);
				} else {
					//alert(1);
					this.addresses[index].consumerName=consumerName;
					this.addresses[index].telephone=telephone;
					this.addresses[index].addressDetail=addressDetail;
					this.addresses[index].postcode=postcode;
					updateAddress(this.addresses[index]);
				}
			}
		},
		removeAddress:function(addressId){
			$.ajax({
				type: "post",
				url: "deleteAddress/"+addressId+".action",
				async: false,
				dataType: 'json',//返回值类型
				//contentType: "application/json",
				success: function(data) {//请求成功的回调函数，data是返回的数据
					alert("haha")
					//刷新页面
					window.location.href="address.html";
				},
				error: function(){
					window.location.href="address.html";
				}
			});
		}
	}
})	
	
	
	
		
	
	

//创建地址对象
function createAddress(addressId,consumerId,consumerName,telephone,postcode,addressDetail,isSelected){
	this.addressId=addressId;
	this.consumerId=consumerId;
	this.consumerName=consumerName;
	this.telephone=telephone;
	this.postcode=postcode;
	this.addressDetail=addressDetail;
	this.isSelected=isSelected;
}

//获取用户的地址信息
function getAddress(){
	//alert("address");
	var address=[];
	/*alert(1);
	alert(JSON.parse(window.localStorage.getItem("user")));*/
	$.ajax({
		type:"post",
		url:"getAddressBai.action",
		async:false,
		//contentType:'application/json;charset=utf-8',
		data:{userId: JSON.parse(window.localStorage.getItem("user")).id},
		success:function(data){
			//alert("111");
			address=data;
		}
	})
	//alert(address[0].consumerName);
	return address;
}

//保存用户修改的地址
function updateAddress(addressItem){
	//alert("进来了");
	$.ajax({
		type:"post",
		url:"updateAddress.action",
		async:false,
		contentType:'application/json;charset=utf-8',
		data:JSON.stringify(addressItem),
		success:function(data){
			console.log(data);
			window.location.href="address.html";
		}
	})
}
