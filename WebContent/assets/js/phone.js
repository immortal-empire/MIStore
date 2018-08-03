$(document).ready(function(){
	
	var n1 = location.href.length;//地址的总长度
  	var n2 = location.href.indexOf("=");//取得=号的位置
  	var comtyId = location.href.substr(n2+1, n1-n2);//从=号后面的内容
	//获取该一级分类下的所有商品
	$("#infoBody").html("");
	$.ajax({
		type:"post",
		url:"getAllProductByComtyId/"+comtyId,
		async:false,//其值为ture时表示可在此请求未完成时执行其他操作
		dataType:"json",
		success:function(data) {
			console.log(data);
			var infoBody;
			for(var i=0; i<data.length; i++) {
				if(i%3 !=2) {
					infoBody = '<div class="blog-post col-xs-12 col-md-6">';
				}else {
					infoBody = '<div class="blog-post col-xs-12 col-md-12">';
				}
				
                infoBody += '<article class="entry panel">' +
                        '<figure class="entry-media post-thumbnail embed-responsive embed-responsive-16by9" data-bg-img="assets/images/products/'+data[i].picture+'/comtty.jpg">'+
                        
                        '</figure>'+
                        '<div class="entry-wrapper prl-20 prl-md-30 pt-20 pt-md-30 pb-15">'+
                            '<header class="entry-header">'+
                                '<h4 class="entry-title mb-10 mb-md-15 t-uppercase">'+
									'<a href="addShoppingCart.html?comttyId='+data[i].comttyId+'">'+data[i].proName+'</a>'+
								'</h4>'+
                                
                            '</header>'+
                            '<div class="entry-content">'+
                                '<p class="entry-summary">'+data[i].proDescriptive+'</p>'+
                                '<h4>'+data[i].sellingPrice+'元起</h4>'+
                            '</div>'+
                            '<footer class="entry-footer text-right">'+
                                '<a href="addShoppingCart.html?comttyId='+data[i].comttyId+'" class="more-link btn btn-link">了解更多 <i class="icon fa fa-long-arrow-right"></i></a>'+
                            '</footer>'+
                        '</div>'+
                    '</article>'+
                '</div>';
                
                $("#infoBody").append(infoBody);
			}
		}
		
	});

});

