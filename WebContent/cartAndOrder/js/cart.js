/**
 * 
 */


//测试假数据
/*$(function(){
	//var userinfo={"cid":1,"Cname":"Tom","age":28};
	//window.localStorage.setItem("user",JSON.stringify(userinfo));
	var product=[{"id":1,"proId":1,"proName":"手机","price":100,"quantity":1,"isChecked":false,"inventory":5,"purchaseType":"1","productStatus":"1","isLiked":"1","addTime":"2018-07-08 10:10:12","picture":"phone",},
		{"id":2,"proId":1,"proName":"手机","price":100,"quantity":1,"isChecked":false,"inventory":50,"purchaseType":"0","productStatus":"1","isLiked":"1","addTime":"2018-07-08 10:10:12","picture":"phone"},
		{"id":3,"proId":1,"proName":"手机","price":100,"quantity":2,"isChecked":false,"inventory":50,"purchaseType":"1","productStatus":"2","isLiked":"1","addTime":"2018-07-08 10:10:12","picture":"phone"}]
	var product=[];
	window.localStorage.setItem("products",JSON.stringify(product));
})*/

//用于判断是否登陆，改变前端样式使用
var headLogin = new Vue({
	el:'#headLogin',
	data:{
		user:ininUser(),
	},
	methods:{
		//退出登录的操作
		outLogin:function(){
			//未写完
			window.localStorage.clear();
			window.location.href="../cartAndOrder/cart.html";
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

var cartMain = new Vue({
	el:'#cartMain',
	data:{
		user:ininUser(),
		items:initItems(),
		isAllChecked:false,
	},
	created:function(){	
		setInterval(() => {
			this.modifyStatus();
		},10000)
	},
	methods:{
		//增加商品数量
		addnumber: function(product,num){
			//查询库存
			product.inventory=selectInventory(product.proId);
			if(product.quantity<=1 && num<0){
        		return;
    		}
			if(product.inventory<=product.quantity&&num!=-1){
				alert("库存不足!");
			}else{
				product.quantity +=num;
			}
			updateQuantity(product);
		},
		//复选款全选
		selectAllCheck:function(){
			this.isAllChecked=!this.isAllChecked;
			for(var i=0;i<this.items.length;i++){
				if(this.isAllChecked){
					this.items[i].isChecked = true; 
				} else{
					this.items[i].isChecked = false;
				}
		   }
		},
		//复选框单选
		selectCheck:function(event){
			var item =event.target.getAttribute("data-value");
		    for(var i=0;i<this.items.length;i++){
		    	if(item==this.items[i].id){
		    		this.items[i].isChecked = !this.items[i].isChecked; 
		    	} 
		    	console.log(this.items[i]);
		    }
		},
		//获得选中的商品数量
		getAllSelected:function(){
			var count=0;
			for(var i=0;i<this.items.length;i++){
				if(this.items[i].isChecked&&this.items[i].productStatus=="1"){
					count++;
				}
			}
			return count;
		},
		//计算购物车商品总价格
		getTotalPrice:function(){
			var totalPrice=0;
			for(var i=0;i<this.items.length;i++){
				if(this.items[i].isChecked&&this.items[i].productStatus=="1"){
					totalPrice += this.items[i].price*this.items[i].quantity;
				}
			}
			return totalPrice;
		},
		//删除购物车商品
		removeItem:function(index){
			//从数据库删除
			deleteSCProduct(this.items[index]);
			//更新items
			this.items.splice(index,1);
			//更新localStorage
			window.localStorage.removeItem("products");
			window.localStorage.setItem("products",JSON.stringify(this.items));
		},
		//判断是否为闪购商品
		isFlashSales:function(productItem){
			var status = false;
			if(productItem.purchaseType==1){
				status = true;
			}
			return status;
		},
		//判断是否为失效商品
		isExpiry:function(productItem){
			var status = false;
			if(productItem.productStatus==2){
				status = true;
			}
			return status;
		},
		//计算闪购的倒计时
		getCountDown:function(product){
			var startTime =new Date(product.addTime).getTime();
			var time = new Date(startTime+15*60*1000);
			return time.toLocaleString( );
		},
		//更新用户喜爱商品的状态
		updateFavor:function(product,status){
			product.isLiked=status;
			updateFavor(product,status,this.user.id);
		},
		//设置图片地址
		setImgSrc:function(product){
			var imgSrc = "img/"+product.picture+"/cart.jpg";
			return imgSrc;
		},
		//跳转到订单界面
		settlement:function(){
			if(this.user==""||this.user==null||this.user==undefined){
				alert("请登录账号!");
				window.location.href="../signin.html"
			}else{
				var order=[];
				for(var i=0;i<this.items.length;i++){
					if(this.items[i].isChecked&&this.items[i].productStatus=="1"){
						order.push(this.items[i]);
					}
				}
				//alert(order);
				window.localStorage.setItem("order",JSON.stringify(order));
				//alert(JSON.parse(window.localStorage.getItem("order"))[0].price);
				window.location.href="../cartAndOrder/order.html"
			}
		},
		modifyStatus:function(){
			var currentDate = new Date();
			for(var i=0;i<this.items.length;i++){
				var addTime = new Date(this.items[i].addTime).getTime();
				if(currentDate.getTime()>addTime+15*60*1000&&this.items[i].purchaseType=="1"&&this.items[i].productStatus=="1"){
					this.items[i].productStatus="2";
					updateProductStatus(this.items[i]);
				}
			}
		}
	},
});

//查询库存量
function selectInventory(proId){
	var inventory=0;
	$.ajax({
		type:"post",
		url:"selectInventory.action",
		async:false,
		//contentType:'application/json;charset=utf-8',
		data:{proId:proId},
		success:function(data){
			inventory=data;
		}
	});	
	return inventory
}

//删除购物车商品
function deleteSCProduct(product){
	$.ajax({
		type:"post",
		url:"deleteSCProduct.action",
		async:true,
		contentType:'application/json;charset=utf-8',
		data:JSON.stringify(product),
		success:function(data){
			console.log(data);
		}
	});
}

//修改购物车的商品数量
function updateQuantity(product){
	//alert(product.id);
	$.ajax({
		type:"post",
		url:"updateQuantity.action",
		async:true,
		contentType:'application/json;charset=utf-8',
		data:JSON.stringify(product),
		success:function(data){
			console.log(data);
		}
	});
}

//修改数据库的喜欢的商品
function updateFavor(product,status,cid){
	//alert(product.id+" "+status+" "+cid+" "+product.proId);
	$.ajax({
		type:"post",
		url:"updateFavor.action",
		async:true,
		contentType:'application/json;charset=utf-8',
		data:JSON.stringify({"SCId":product.id,"status":status,"cid":cid,"proid":product.proId}),
		success:function(data){
			console.log(data);
		}
	});
}

//闪购失效修改数据库数据
function updateProductStatus(product){
	$.ajax({
		type:"post",
		url:"updateProductStatus.action",
		async:true,
		contentType:'application/json;charset=utf-8',
		data:JSON.stringify(product),
		success:function(data){
			console.log(data);
		}
	});
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

//初始化Vue.jsData数据
function initItems(){
	var items=[];
	var user=ininUser();
	if(this.user==""||this.user==null||this.user==undefined){
		var temp=window.localStorage.getItem("products");
		console.log(temp);
		console.log("00");
		if(temp==""||temp==null||temp==undefined){
			items=[];
		}else{
			items=JSON.parse(temp);
		}
	} else{		
		items=selectShoppingcart(user.id);
	}
	return items;
}

//初始化user数据
function ininUser(){
	var user=window.localStorage.getItem("user");
	if(user==""||user==undefined||user==null){
		user=null;
	}else{
		user=JSON.parse(user);
	}
	return user;
}

