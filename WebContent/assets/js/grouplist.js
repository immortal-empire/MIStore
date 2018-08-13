
$(document).ready(function(){ 
  $.ajax({
	  type:"post",
	  url:"selectGroupPurchase.action",
	  async:false,
	  dataType: 'json',
	  success:function(data){
		  $("#myTemplate").tmpl(data).appendTo("#gPro");
	  }
  });
});


