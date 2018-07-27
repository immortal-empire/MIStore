$(function(){
	
	//获取轮播图片（最新上架商品）
	$.ajax({
		type:"post",
		url:"",
		async:true,
		dataType:"json",
		success:function(data)
		{
			for(var i=0; i<data.length;i++)
					{
						var str = '<figure class="item">';
						str+='<img src="images/'+data[i].imgurl+'" />';
						str+='</figure>';
						$("#broadcastImg").append(str);
					}
			
		}
		
	});
});
