var files = [];
(function ($) {  
    //扩展方法获取url参数  
    $.getUrlParam = function (name) {  
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
        if (r != null) 
        	return unescape(r[2]); 
        return null; //返回参数值  
}  
})(jQuery);
var user = window.localStorage.getItem("user");
var cid = JSON.parse(user).id;
var orderId = $.getUrlParam('orderId');
var proId = $.getUrlParam('proId');
console.log(orderId);
console.log(proId);
$(document).ready(function(){
	$("#cid").val(cid);
	$("#orderid").val(orderId);
	$("#proId").val(proId);
	data_rating();
	do_rating();
	$.ajax({		
		url:"getComttyIdByProId/"+proId,
		type:"post",
		dataType:"json",
		async:false,
		success:function(data)
		{
			$("#proName").text(data.proName);
			$("#configuration").text(data.configuration);
			$("#color").text(data.color);
			$("#proDescriptive").text(data.proDescriptive);
			$("#proMakeComments").attr('src',"assets/images/products/"+data.picture+"/proMakeComments.jpg");
		}		
	});
});
function data_rating() {
        $('.rating').each(function () {
            var rating = $(this).find('.rank-stars').attr('data-rating'),
            rating_index = 5 - rating;
            $(this).find('.rank-stars > i').eq(rating_index).addClass('star-active');
        });
    };

function do_rating() {
    var rating_stars_select = $('.rating .rank-stars.rank-allow');
    rating_stars_select.on('mouseenter', function () {
        $(this).find('i').removeClass('star-active');
    });
    rating_stars_select.on('mouseleave', function () {
        data_rating();
    });
    rating_stars_select.on('click', 'i', function () {
        var num_stars = $(this).siblings().length + 1,
        	rating_index = $(this).index(),
            
            rate_value = num_stars - rating_index;

        $(this).parent().attr('data-rating', rate_value);
        console.log(rate_value);
        $("#rank").val(rate_value);
        //alert($("#rank").val());
        data_rating();
    
    });
};

$("#photo").change(function(){
	var file = this.files[0];
	//把当前文件追加到formData对象
	files.push(file);
	//文件预览
	//1. 创建一个filereader对象
	var fileReader = new FileReader();
	//2. 定义filereader的onload方法（base64字符串）
	    //append(<img src="base64字符串">)
	fileReader.onload = function(e)
	{
		var base64 = e.target.result;
		$("#photos").append('<img class="addedphoto" src="'+base64+'"/>');
	}
	//3. 读
	fileReader.readAsDataURL(file);
});
	
$("#postComments").click(function(){
	//1. 创建一个formdata对象
	var formData = new FormData(document.getElementById("myform"));
	//2. 把文件追加到formData中
	for(var i=0; i<files.length;i++)
	{
		formData.append("upload",files[i]);
	}	
	//提交ajax
	$.ajax({		
		url:"addComments",
		type:"post",
		data:formData,
		dataType:"json",
		contentType:false,
		processData:false,
		cache:false,
		success:function(data)
		{
			//{result:true}
			if(data.result) {
				alert("评论成功");
				location.href="comments.html?proId="+proId;
			}
			else {
				alert("评论失败");
			}
		}		
	});
});


$(document).on("click", ".addedphoto", function(){ 
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