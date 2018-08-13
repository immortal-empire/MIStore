/**
 * 
 */
$(document).ready(function(){
	init_city_select($("#citySelect"));
})

//全局变量保存选择的配送时间
var sendTime;
var invoiceType;
var giftCardIndex;
giftCardPay=0;
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

//用于判断是否登陆，改变前端样式使用
var headLogin = new Vue({
	el:'#headLogin',
	data:{
		user:JSON.parse(window.localStorage.getItem("user")),
	},
	methods:{
		//退出登录的操作
		outLogin:function(){
			//未写完
			window.localStorage.clear();
		},
	}
})

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
		order:JSON.parse(window.localStorage.getItem("order")),//获得订单信息
		addresses:getAddress(),//获得用户已有地址/*[{"consumerName":"hhh","telephone":"1234567","addressDetail":"asffdfsdfds"}]*/
		addressPx:0,//动态设置地址栏的高度
		expressFee:0,//计算快递费
		totalPrice:0,//计算订单商品总价格
		totalQuantity:0,//计算订单商品总数
		giftCards:getGiftCard(),//获得礼品卡
		giftCardPx:0,//动态设置礼品卡栏的高度
		giftFee:0,//礼品卡使用金额
	},
	mounted(){
		//设置地址栏的height
		this.addressPx=Math.ceil((this.addresses.length+1)/4)*240;
		//设置礼品卡栏的高度
		this.giftCardPx=Math.ceil((this.giftCards.length)/2)*185;
		/*alert(this.size);*/
		//初始化数据
		var totalPrice=0;
		for(var i=0;i<this.order.length;i++){
			totalPrice+=this.order[i].price*this.order[i].quantity;
		}
		this.totalPrice=totalPrice;
		//alert(this.totalPrice);
		this.totalQuantity=this.order.length;
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
				$("#Addressbody input").attr("value","");
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
		//获得用户选择的配送时间
		sendTime:function(status){
			$("#paytime a").css("border","1px solid #d5d5d5");
			if(status==0){
				$("#t0").css("border","1px solid #ff6700");
			} else if(status==1){
				$("#t1").css("border","1px solid #ff6700");
			} else{
				$("#t2").css("border","1px solid #ff6700");
			}
			sendTime=status;
			//alert(sendTime);
		},
		//计算是否包邮
		isFree:function(){
			//alert(JSON.parse(window.localStorage.getItem("order"))[0].price);
			var status=true;
			if(this.totalPrice<100){
				this.expressFee=10;
				status=false;
			}else{
				this.expressFee=0;
			}
			return status;
		},
		//设置图片src
		setImgSrc:function(order){
			var imgSrc = "img/"+order.picture+"/order.jpg";
			return imgSrc;
		},
		//获得用户选择的发票
		getInvoice:function(status){
			$("#invoice a").css("border","1px solid #d5d5d5");
			if(status==0){
				$("#eletriInvoice").css("border","1px solid #ff6700");
			} else{
				$("#generalInvoice").css("border","1px solid #ff6700");
			}
			invoiceType=status;
			//alert(sendTime);
		},
		//礼品卡输入框id的生成
		setInputId:function(index){
			return "gift_"+index;
		},
		//使用礼品卡
		useGift:function(giftCardItem,index){
/*			console.log("layer");
			alert("layer");*/
			var res=/^\+?[1-9][0-9]*$/;
			var fee = $("#gift_"+index+"").val();
			giftCardPay=fee;
			//alert(fee);
			//alert(res.test(fee));
			if(fee==""||fee==null||fee==undefined){
				if(this.totalPrice>giftCardItem.balance){
					this.giftFee = giftCardItem.balance;
					giftCardItem.balance=0;
				    $("#body button").attr("disabled",  true);
				    $("#body div").css({"background-color":"#DDDDDD","color":"#9d9d9d"});
				    $("#body input").val("");
				    //$("#myGiftCardModal").modal('hide');
				}else{
					this.giftFee = this.totalPrice;
					giftCardItem.balance=giftCardItem.balance-this.totalPrice;
				    $("#body button").attr("disabled",  true);
				    $("#body div").css({"background-color":"#DDDDDD","color":"#9d9d9d"});
				    $("#body input").val("");
				    //$("#myGiftCardModal").modal('hide');
				}
			}else{
				if(fee>giftCardItem.balance){
				    alert("礼品卡余额不足!");
				} else if(!res.test(fee)){
				    alert("无效数字!");
				} else if(this.totalPrice>fee) {
				    giftCardItem.balance=giftCardItem.balance-fee;
				    this.giftFee=fee;
				    $("#body button").attr("disabled",  true);
				    $("#body div").css({"background-color":"#DDDDDD","color":"#9d9d9d"});
				    $("#body input").val("");
				    //$("#myGiftCardModal").modal('hide');
				} else{
				    giftCardItem.balance=giftCardItem.balance-this.totalPrice;
				    this.giftFee=this.totalPrice;
				    $("#body button").attr("disabled",  true);
				    $("#body div").css({"background-color":"#DDDDDD","color":"#9d9d9d"});
				    $("#body input").val("");
				    //$("#myGiftCardModal").modal('hide');
				}
			}
			giftCardIndex=index;
		},
		//计算最终应付的价格
		getTotalPrice:function(){
			var total=0;
			total=this.totalPrice+this.expressFee-this.giftFee;
			return total;
		},
		//下单结算
		payment:function(){
			//保存地址
			var count=0;
			var address=[];
			var userId=JSON.parse(window.localStorage.getItem("user")).id;
			for(var i=0;i<this.addresses.length;i++){
				if(this.addresses[i].isSelected){
					count++;
					address=this.addresses[i];
				}
			}
			//alert(count+" "+sendTime);
			if(count==0){
				alert("请选择收货地址!");
				//$(".body").scrollTop(0);
				document.body.scrollTop = document.documentElement.scrollTop = 0;
			} else if(sendTime==undefined){
					alert("请选择收货时间!");
			} else if(invoiceType==undefined){
					alert("请选择发票类型!");
			} else{
				var addressId = address.addressId;
				var consumerId=this.user.id;
				//alert("saveOrder");
				var orderId=saveOrder(this.giftCards[giftCardIndex],this.order,consumerId,addressId,this.totalPrice);
				var cartProducts=JSON.stringify(selectShoppingcart(userId));
				window.localStorage.setItem("orderId",orderId);
				window.localStorage.removeItem("order");
				window.localStorage.removeItem("products");
				window.localStorage.setItem("products",cartProducts);
				window.location.href="../assets/pay.html";
			}
			
			
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
	var address=[];
	/*alert(1);
	alert(JSON.parse(window.localStorage.getItem("user")));*/
	$.ajax({
		type:"post",
		url:"getAddress.action",
		async:false,
		//contentType:'application/json;charset=utf-8',
		data:{userId: JSON.parse(window.localStorage.getItem("user")).id},
		success:function(data){
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
			window.location.href="../cartAndOrder/order.html";
		}
	})
}

//查询用户的礼品卡
function getGiftCard(){
	
	var giftCard=null;
	$.ajax({
		type:"post",
		url:"getGiftCard.action",
		async:false,
		//contentType:'application/json;charset=utf-8',
		data:{userId: JSON.parse(window.localStorage.getItem("user")).id},
		success:function(data){
			giftCard=data;
		}
	})
	return giftCard;
}


//储存订单
function saveOrder(giftCard,products,consumerId,addressId,totalPrice){
	var orderId=0;
	$.ajax({
		type:"post",
		url:"saveOrder.action",
		async:false,
		contentType:'application/json;charset=utf-8',
		data:JSON.stringify({	"invoiceType":invoiceType,
								"orderStatus":0,
								"totalPrice":totalPrice,
								"receiveGoodsTime":sendTime,
								"addressId":addressId,
								"consumerId":consumerId,
								"products":products,
								"giftCard":giftCard,
								"giftCardPay":giftCardPay}),
		success:function(data){
			orderId=data;
			console.log("订单保存成功!");
		}
	})
	return orderId;
}


//查询购物车商品
function selectShoppingcart(userId){
	var items=null;
	$.ajax({
		type:"post",
		url:"selectShoppingcart.action",
		async:false,
		//contentType:'application/json;charset=utf-8',
		data:{userId:userId},
		success:function(data){
			items=data;
			//alert(data[0].isChecked+" "+data[0].proName+" "+data[0].isLiked);
		}
	});
	return items;
}





