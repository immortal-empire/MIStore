
$(function(){
	var user = window.localStorage.getItem("user");
	var cid = JSON.parse(user).id;
	//var cid = 1;
$.ajax({
		
		url:"getUncommentedProduct/"+cid+".action",
		type:"get",
		dataType:"json",
		success:function(data)
		{
			console.log(data);
			data.forEach(function(productview,index){
			   	var str ='<div class="orderspec-products">'        	
			   			str+='<div class=" row orderspec-products-detial">'
			   				str+='<div class="col-md-2 orderspec-products-detial-picture">'
			   				
			   						str+='<a target="_blank" href="#"><img src="cartAndOrder/img/'+productview.picture+'/cart.jpg">'+'</a>'
			   					
			   				str+='</div>'
			   				str+='<div class="col-md-4 orderspec-products-detial-name">'
			   					str+='<a target="_blank" href="#">'+productview.name+'  '+productview.configuration+'  '+productview.color+'</a>'
			   				str+='</div>'
			   				str+='<div class="col-md-4 orderspec-products-detial-price">'
			   					str+=productview.price+'元 &times; '+productview.amount
			   				str+='</div>'
			   				str+='<div class="col-md-2 order_bottom_right"> '
			   					str+='<span class="orderspec"><a class="btn btn-small btn-line-gray"   >去评论</a><span style="display:none">'+productview.orderId+'</span><span style="display:none">'+productview.proid+'</span></span>';
			   					
			   				str+='</div>'
		                str+='</div>'
		            str+='</div>'
		            $("#products_view").append(str);
			   	});
		}
});
$(document).on("click", ".orderspec", function(){ 
   	var orderId = $(this).find("span").eq(0).text();
   	var proId = $(this).find("span").eq(1).text();
   	window.location.href="makeComments.html?orderId="+orderId+"&proId="+proId;
});
	   	
});














