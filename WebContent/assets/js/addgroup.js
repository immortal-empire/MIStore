var groId;
$(document).ready(function(){
	var url = window.location.search;
	groId = url.substring(url.lastIndexOf('=')+1, url.length);
	
	loadGroupPurchase();
	updateGroupStateAuto();
	loadNumOfPerson();
	loadGrouping();
	createGroup();
});


//在加团界面加载商品信息

function loadGroupPurchase(){
	
	$.ajax({
		type:"post",
		url:"selectGroupPurchaseById.action",
		async:false,
        dataType: 'json',
        data:{"groId":groId},
        success:function(data){
        	console.log(data);
        	console.log(data.product.picture);
//        	$("#myTemplatePicture").tmpl(data).appendTo("#gProByIdPicture");加载图片
//        	$("#myTemplateDetail").tmpl(data).appendTo("#gProByIdDetail");加载信息
        	var gProByIdPicture="<div class='blog-post col-xs-12'>" +
        							"<article class='entry panel'>" +
        								"<figure class='entry-media'>" +
        									"<div class='owl-slider' data-loop='true' data-autoplay='true' data-autoplay-timeout='10000' data-smart-speed='1000' data-nav-speed='false' data-nav='true' data-xxs-items='1' data-xxs-nav='true' data-xs-items='1' data-xs-nav='true' data-sm-items='1' data-sm-nav='true' data-md-items='1' data-md-nav='true' data-lg-items='1' data-lg-nav='true'> " +
        										"<figure class='item'>" +
        											"<img src='images/brands/"+data.product.picture+"/2560x560.jpg' alt=''>" +
        										"</figure>" +
        										"<figure class='item'>" +
        											"<img src='images/brands/"+data.product.picture+"/3560x560.jpg' alt=''>" +
        										" </figure>" +
        									"</div>" +
        								"</figure>" +
        							"</article>" +
        						"</div>";
        	$("#gProByIdPicture").append(gProByIdPicture);
        	
        	var gProByIdDetail = "<div class='widget panel ptb-30 prl-20'>" +
        							"<h4>"+data.product.proName+"&nbsp;&nbsp;"+data.product.configuration+"&nbsp;&nbsp;"+data.product.color+"</h4>" +
        							"<span>"+data.product.proDescriptive+"</span><br>" +
        							"<p></p>" +
        							"<h4><span>"+data.groupPrice+"</span></h4>" +
        						"</div>";
        	$("#gProByIdDetail").append(gProByIdDetail);
        }
	});
}	
	
//自动更新团的状态
function updateGroupStateAuto(){
	$.ajax({
		type:"post",
		url:"updateGroupStateAuto.action",
		async:false,
	});
}

//显示团和团中的人
function loadGrouping(){
	
	$.ajax({
		type:"post",
		url:"selectGroup.action",
		async:false,
		dataType: 'json',
//      data:{"groId":groId},
		success:function(data){
				console.log(JSON.stringify(data));
			for(var i=0;i<data.length;i++){	
				var customerName="";
				var name;
				for(var j=0;j<data[i].groupOrderlist.length;j++){
					name = data[i].groupOrderlist[j].customer.cname;
					
					customerName=customerName+"  "+name;
				}
				var gOrder = "<div style='margin:5px 0px 5px 0px'>" +
								"<div style='float: left; width: 40px;'> <i class='fa fa-user fa-lg' ></i></div>" +
								"<div style='float: left; width: 80px;'><h5 id='customerName'>"+customerName+"<h5></div>" +
								"<div style='float: left; width: 120px;'><h5>差1人可拼单<h5></div>" +
								"<div style='float: left; width: 120px;'>" +
									"<i class='ico fa fa-clock-o mr-10'></i>" +
									"<span class='t-uppercase' data-countdown=' "+data[i].endTimeStr+" '></span>" +
								"</div>" +
								"<div class='demo'>" +
									"<a id='createGroup' href='buynow.html?groupId="+data[i].id+"'"+"class='button orange medium'>去拼单</a>" +
								"</div>	" +
							 "</div>  ";
				
				 
				$("#gOrder").append(gOrder);
			}
		}
	});
}

//显示有多少人拼团
function loadNumOfPerson(){
	$.ajax({
		type:"post",
		url:"selectNumOfPerson.action",
		async:false,
		dataType:'json',
		success:function(data){
			var numOfPerson = "有 "+data+" 个人正在拼团";
			$("#numOfPerson").append(numOfPerson);
		}
	});
}

//新开团
function createGroup(){

	$("#createGroup").click(function(){
		$.ajax({
			type:"post",
			url:"createGroup.action",
			async:false,
			dataType: 'json',
			success:function(data){
				location.href="buynow.html?groupId="+data;
			}
		});
	});
}

