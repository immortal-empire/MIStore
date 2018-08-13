$(function(){
	//获得闪购活动时间并开始倒计时
	$.ajax({
		type:"post",
		async:false,
		url:"getCurSeckillTime.action",
		dataType:"json",
		success:function(data){
			var startTime = new Date(parseInt(data[0],10));//本期开始时间
			var endTime = new Date(parseInt(data[1],10));//本期结束时间
			var nextStartTime = new Date(parseInt(data[2],10));//下期开始时间
			var t;
			var now = new Date();
			if(data[1]>now){
				//闪购活动进行中
				var hour = checkTime(startTime.getHours());
				var minute = checkTime(startTime.getMinutes());
				$("#time").html(hour+":"+minute+" 场");
				$("#timeInfo").html("距离结束还有");
				t = endTime;
				loadSeckillProducts(startTime);
			}else if(data[1]<=now){
				//闪购活动结束，等待下期活动开始
				var hour = checkTime(nextStartTime.getHours());
				var minute = checkTime(nextStartTime.getMinutes());
				$("#time").html(hour+":"+minute+" 场");
				$("#timeInfo").html("距离开始还有");
				t = nextStartTime;
				loadSeckillProducts(nextStartTime);
			}
			//倒计时
			refreshTime = setInterval(leftTime,1000);
			function leftTime(){
				var leftTime = t - new Date();
				if(leftTime<=0){
					clearInterval(refreshTime);
				}else{
					var leftHours = parseInt(leftTime / 1000 / 60 / 60 % 24, 10); //计算剩余的小时
					var leftMinutes = parseInt(leftTime / 1000 / 60 % 60, 10);//计算剩余的分钟
					var leftSeconds = parseInt(leftTime / 1000 % 60, 10);//计算剩余的秒数
					leftHours = checkTime(leftHours);
					leftMinutes = checkTime(leftMinutes);
					leftSeconds = checkTime(leftSeconds);
					$("#left_hour").html(leftHours);
					$("#left_minute").html(leftMinutes);
					$("#left_second").html(leftSeconds);
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
			alert("error");
		}
	});
	
	function loadSeckillProducts(st){
		//获得闪购商品的信息
		$.ajax({
				type:"post",
				contentType:"application/json",
				url:"getSeckillProducts.action",
				dataType:"json",
				data:JSON.stringify(st),
				success:function(data)
				{
					for(var i=1; i<data.length+1;i++){
						var str = '<li class="item rainbow-item-'+i+'">';
						str+='<a href="seckill.html" target="_blank"><div class="bg"></div></a><div class="content">';
						str+='<a class="thumb exposure"><img src="image/'+data[i-1].picture+'.jpg" alt></a>';
						str+='<h3 class="title" id="productName"><a>'+data[i-1].productName+' '+data[i-1].color+' '+data[i-1].configuration+'</a></h3>';
						str+='<p class="desc" id="description">'+data[i-1].description+'</p>';
						str+='<p class="price"><span id="seckillPrice">'+data[i-1].seckillPrice+' 元   </span>';
						str+='<del id="sellingPrice">'+data[i-1].sellingPrice+'元</del></p></div></li>';
						
						$("#items").append(str);
					}
				}
				
			});
	}

});
