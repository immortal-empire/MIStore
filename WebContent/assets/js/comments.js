$(document).ready(function(){
	//getComments(proId, rank);
	getComments(2,0);
	getCommTypeNum(2);//获取每种评价的数量
	getNewComments(2);//获取最新评价，一天之内
	
	//鼠标滚动时上方导航栏固定
	var navOffset=$("#nav").offset().top;  
    $(window).scroll(function(){  
        var scrollPos=$(window).scrollTop();  
        if(scrollPos >=navOffset){  
            $("#nav").addClass("fixed");  
        }else{  
            $("#nav").removeClass("fixed");  
        }  
    });
});

$(".badge").click(function(){
	console.log("haha");
	$(".badge").css({'background-color': ''});
	$(this).css({'background-color': '#ff6700'});
	var content = $(this).find("span").text();
	console.log(content);
	if(content.indexOf("全部") > -1) {
		getComments(2, 0);
	}else if(content.indexOf("十分满意") > -1) {
		getComments(2, 5);
	}else if(content.indexOf("还不错") > -1) {
		getComments(2, 3);
	}else if(content.indexOf("差评") > -1) {
		getComments(2, 1);
	}	
	
});

function getComments(proId, rank){
	$("#commentsBody").html("");//先清空原有内容
	$.ajax({
		type:"post",
		url:"getCommentsByProIdAndRank/"+proId+"/"+rank,
		async:true,
		dataType:"json",
		
		//返回的是List<Comments>对象
		success:function(data){
			console.log(data);
			$("#commentsNum").text(data.length+' Comments');
			var commentsBody;
			for(var i=0; i<data.length; i++) {
				console.log(data[i].commImageName);
				commentsBody = createCommBody(data[i]);
                $("#commentsBody").append(commentsBody);
			}
		},
		
	});
};

function getCommTypeNum(proId) {
	$.ajax({
		type:"post",
		url:"getCommTypeNum/"+proId,
		async:true,
		dataType:"json",
		
		//返回的是List<Integer>对象
		success:function(data){
			for(var i=0; i<data.length; i++) {
				$("#content"+i).find("span").eq(1).text(data[i]);
			}
		},
	});
};

function getNewComments(proId) {
	$.ajax({
		type:"post",
		url:"getNewComments/"+proId,
		async:true,
		dataType:"json",
		
		//返回的是List<Comments>对象
		success:function(data){
			console.log(data);
			var newCommBody;
			var figure;
			for(var i=0; i<data.length; i++) {
				console.log(data[i].commImageName);
				newCommBody = createCommBody(data[i]);
                $("#newCommBody").append(newCommBody);
			}
		},
	});
};

function createCommBody(data) {
	var s = '<li class="comment comment-bypostauthor media">'+		
		   		'<figure class="comment-author-thumb media-left">'+
                    '<a href="#"><img class="media-object" src="assets/images/avatars/avatar_07.jpg" alt="" /></a>'+
                '</figure>'+
                '<article class="comment-wrapper media-body">'+
                    '<div class="comment-meta mb-5">'+
                        '<a href="#" class="comment-reply-link btn btn-xs btn-gray btn-rounded">Reply <i class="fa fa-reply"></i></a>'+
                        '<h5 class="comment-author-name mb-5 ">'+data.customer.cname+'</h5>'+
                        '<span class="color-muted">'+data.commdate+'</span>'+
                    '</div>'+
                    '<div class="comment-content color-mid">'+
                        '<p>'+data.comment+'</p>'+
                    '</div>'+
                '</article>'+
                '<ol class="comment-replays">'+
                	'<li class="comment media comment-bypostauthor">';
          				//图片地址为相对地址，需手动拼接，此处为 /img/commId/imagename
                		$.each(data.commImageName, function(index, item) {
                			console.log(item);
                			s += '<figure class="comment-author-thumb media-left">'+
                            					'<a href="#"><img class="commImage media-object" src="img/'+ data.commId+'/'+item +'" alt="" /></a>'+
                        					'</figure>';
                		});
                                                              
                    s += '</li>'+	
                '</ol>'+
            '</li>';
            
    return s;
}


$(document).on("click", ".commImage", function(){ 
	var _this = $(this);//将当前的pimg元素作为_this传入函数
	imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
});

function imgShow(outerdiv, innerdiv, bigimg, _this){
	var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
	$(bigimg).attr("src", src);//设置#bigimg元素的src属性
 
        /*获取当前点击图片的真实大小，并显示弹出层及大图*/
	$("<img/>").attr("src", src).load(function(){
		var windowW = $(window).width();//获取当前窗口宽度
		var windowH = $(window).height();//获取当前窗口高度
		var realWidth = this.width;//获取图片真实宽度
		var realHeight = this.height;//获取图片真实高度
		var imgWidth, imgHeight;
		var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放
		
		if(realHeight>windowH*scale) {//判断图片高度
			imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
			imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
			if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
				imgWidth = windowW*scale;//再对宽度进行缩放
			}
		} else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
			imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
                        imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
		} else {//如果图片真实高度和宽度都符合要求，高宽不变
			imgWidth = realWidth;
			imgHeight = realHeight;
		}
                $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放
		
		var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
		var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
		$(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
		$(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
	});
	
	$(outerdiv).click(function(){//再次点击淡出消失弹出层
		$(this).fadeOut("fast");
	});
}