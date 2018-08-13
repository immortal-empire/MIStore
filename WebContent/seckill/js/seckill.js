

var user = window.localStorage.getItem("user");
if(user==null||user==""||user==undefined){	//未登录
	var userId = null;	//用户名，用来检验是否有用户登录
}else {
	var userId = JSON.parse(user).id;
}

var remind;	//是否设置提醒
$(function(){
	//获得当前闪购活动时间并开始倒计时
	$.ajax({
		type:"post",
		async:false,
		url:"../getCurSeckillTime.action",
		dataType:"json",
		success:function(data){
			var startTime = new Date(parseInt(data[0],10));//本期开始时间
			var endTime = new Date(parseInt(data[1],10));//本期结束时间
			var nextStartTime = new Date(parseInt(data[2],10));//下期开始时间
			var t;//用于倒计时的变量
			var now = new Date();
			if(data[1]>now){
				//闪购活动进行中
				var hour = checkTime(startTime.getHours());
				var minute = checkTime(startTime.getMinutes());
				$("#curSeckillTime").html(hour+":"+minute);
				$("#curSeckillInfo").html('<em>正在进行<br>距结束<span id="leftTime"></span></em>');
				t = endTime;
				loadSeckillProducts(startTime,0,"on");
				loadAllSeckillTime(startTime);
			}else if(data[1]<=now){
				//闪购活动结束，等待下期活动开始
				var hour = checkTime(nextStartTime.getHours());
				var minute = checkTime(nextStartTime.getMinutes());
				$("#curSeckillTime").html(hour+":"+minute);
				$("#curSeckillInfo").html('<em>即将开始<br>距开始<span id="leftTime"></span></em>');
				t = nextStartTime;
				loadSeckillProducts(nextStartTime,0,"off");
				loadAllSeckillTime(nextStartTime);
			}
			//倒计时
			refreshTime = setInterval(leftTime,1000);
			function leftTime(){
				var leftTime = t - new Date();
				if(leftTime<=0){
					clearInterval(refreshTime);//剩余时间为0时结束倒计时
				}else{
					var leftHours = parseInt(leftTime / 1000 / 60 / 60 % 24, 10); //计算剩余的小时
					var leftMinutes = parseInt(leftTime / 1000 / 60 % 60, 10);//计算剩余的分钟
					var leftSeconds = parseInt(leftTime / 1000 % 60, 10);//计算剩余的秒数
					leftHours = checkTime(leftHours);
					leftMinutes = checkTime(leftMinutes);
					leftSeconds = checkTime(leftSeconds);
					$("#leftTime").html(leftHours+":"+leftMinutes+":"+leftSeconds);
				}						
			}			
			function checkTime(i){ //将0-9的数字前面加上0，例1变为01 
				if(i<10){ 
				    return "0" + i; 
				} 
				return i; 
			}
		},
		error:function(){
			alert("loadingError!");
		}
	});
	
	
	//获得接下开几期闪购活动时间
	function loadAllSeckillTime(st){
		$.ajax({
			type:"post",
	        async:false,
			contentType:"application/json",
			url:"../getAllSeckillTime.action",
			dataType:"json",
			data:JSON.stringify(st),
			success:function(data){
				for(var i=0; i<data.length;i++){
					var startTime = new Date(parseInt(data[i],10));
					var hour = checkTime(startTime.getHours());
					var minute = checkTime(startTime.getMinutes());
					var date = checkTime(startTime.getDate());
					var now = new Date();
					var temp_time = new Date(1900+startTime.getYear(),startTime.getMonth(),startTime.getDate());
					var time_diff = Math.floor((temp_time.getTime()-now.getTime())/(24*60*60*1000));
					var str ='<a><em>'+hour+':'+minute+'</em>';
					if(time_diff==-1||(time_diff==0 && now.getDate()==temp_time.getDate()))
						str+='<span>即将开始</span></a>';
					else if(time_diff==0 && now.getDate()!=temp_time.getDate()) 
						str+='<span>明日开始</span></a>';
					else
						str+='<span>'+startTime.getMonth()+'月'+startTime.getDate()+'日开始</span></a>';
					$("#Seckills li").eq(i+1).append(str);
					loadSeckillProducts(startTime,i+1,"off");
				}
				function checkTime(i){ //将0-9的数字前面加上0，例1变为01 
					if(i<10){ 
					    return "0" + i; 
					} 
					return i; 
				}
			},
			error:function(){
				alert("SeckillTimeError");
			}
		});
	}
	
	//获得闪购商品的信息
	function loadSeckillProducts(st,n,state){
		$.ajax({
			type:"post",
	        async:false,
			contentType:"application/json",
			url:"../getSeckillProducts.action",
			dataType:"json",
			data:JSON.stringify(st),
			success:function(data)
			{
				if(state=="off"){					
					for(var i=0; i<data.length;i++){
						var str = '<li><div class="img-con"><img class="done" src="../cartAndOrder/img/'+data[i].picture+'/cart.jpg" /></div>';
						str+='<div class="pro-con" ><a class="name">'+data[i].productName+' '+data[i].color+' '+data[i].configuration+'</a>';
						str+='<p class="desc tips">'+data[i].description+'</p>';
						str+='<p class="price">'+data[i].seckillPrice+'元  ';
						str+='<del>'+data[i].sellingPrice+'元</del></p>';
						isReminded(data[i].seckillId);
						if(remind){
							str+='<a class="btn btn-green btn-small btn-disabled" onclick="setRemind('+data[i].seckillId+','+n+','+i+')">取消提醒</a>';
						}else{
							str+='<a class="btn btn-green btn-small btn-remind" onclick="setRemind('+data[i].seckillId+','+n+','+i+')">提醒我</a>';
						}
						str+='<p class="person ">已有<a>'+data[i].remindNum+'</a>人设置提醒</p></div></li>';
						$("#items ul").eq(n).append(str);
					}
				}else if(state=="on"){
					var per;
					for(var i=0; i<data.length;i++){					
						per = (1-data[i].seckillRemainNum/data[i].seckillMaxNum)*100	//进度条
						var str = '<li><div class="img-con"><img class="done" src="../cartAndOrder/img/'+data[i].picture+'/cart.jpg" /></div>';
						str+='<div class="pro-con" ><a class="name">'+data[i].productName+' '+data[i].color+' '+data[i].configuration+'</a>';
						str+='<p class="desc tips">'+data[i].description+'</p>';
						str+='<p class="process J_process"><span style="width: '+per+'%;"></span><em>'+parseInt(per)+'%</em></p>';
						str+='<p class="price">'+data[i].seckillPrice+'元  ';
						str+='<del>'+data[i].sellingPrice+'元</del></p>';
						if(userId==null||userId==""||userId==undefined){
							str+='<div class="btn btn-green btn-small btn-primary" onclick="signin()">登录后抢购</div>';
						}else if(per==100){	//判断是否已抢光，并设置相应的按钮
							str+='<div class="btn btn-green btn-small btn-disabled">已抢光</div>';
						}else{
							str+='<div class="btn btn-green btn-small btn-primary" onclick="buy('+data[i].seckillId+')">立即抢购</div>';
						}
						str+='</div></li>';
						$("#items ul").eq(n).append(str);
					}
				}
				
			}		
		});
	}
	
	//根据用户点击，显示相应的商品
	$("#Seckills li").click(function(){
		var content = $(this).html();
		if(content.length!=0){ //若没有相应的闪购活动，则无法点击
			$("#Seckills li.active").removeClass("active");
			$(this).addClass("active");
			for(var i=0;i<5;i++){ //找到当前显示的应为第几期闪购
				if($("#Seckills li").eq(i).hasClass("active")){
					$("#items ul.active").removeClass("active");
					$("#items ul").eq(i).addClass("active");
				}
			}			
		}		
	});
	
	
});

//检查当前用户是否对该商品设置了提醒
function isReminded(seckillId){
	if(userId==null||userId==""||userId==undefined){	//未登录
		remind=false;
	}else{	//已登录
		$.ajax({
			type:"post",
	        async:false,
			url:"../isReminded.action",
			dataType:"json",
			data:{
				"seckillId":seckillId,
				"userId":userId
			},
			success:function(data){
				remind=JSON.parse(data);
			}
		});
	}
};

//设置提醒
function setRemind(seckillId,n,i){
	var state = null;
	var btn = $("#items ul").eq(n).find("li").eq(i).find("a.btn");//所点击的提醒按钮
	var remindNum = $("#items ul").eq(n).find("li").eq(i).find("p.person a");//对应的提醒人数
	if(btn.html()=="提醒我"){
		state = 0;
	}else if(btn.html()=="取消提醒"){
		state = 1;
	}
	if(userId==null||userId==""||userId==undefined){	//未登录
		if(confirm("请先登录")==true){
			signin();
		}
	}else{	//已登录
		$.ajax({
			type:"post",
	        async:false,
			url:"../setRemind.action",
			dataType:"json",
			data:{
				"seckillId":seckillId,
				"userId":userId,
				"state":state
			},
			success:function(data){	//更改界面元素
				if(state==0){
					btn.html("取消提醒");
					btn.removeClass("btn-remind");
					btn.addClass("btn-disabled");
					remindNum.html(parseInt(remindNum.html())+1);
					alert("已设置提醒");
				}else if(state==1){
					btn.html("提醒我");
					btn.removeClass("btn-disabled");
					btn.addClass("btn-remind");
					remindNum.html(parseInt(remindNum.html())-1);
					alert("已取消提醒");
				}
			}
		});
	}
};

//登录
function signin(){
	window.location.href='../signin.html';
}

//抢购商品
function buy(seckillId){
	$.ajax({
		type:"post",
        async:false,
		url:"../buy.action",
		dataType:"json",
		data:{
			"seckillId":seckillId,
			"userId":userId
		},
		success:function(data){
			if(data==-1)
				alert("系统发生错误");
			else if(data==0)
				alert("请勿重复抢购");
			else{
				window.location.href='../cartAndOrder/cart.html';
			}	
		}
	});
}