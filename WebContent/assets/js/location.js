function getLocation(){ 

  if (navigator.geolocation){ 
    navigator.geolocation.getCurrentPosition(showPosition,showError); 
    alert("22");
  }else{ 
    alert("浏览器不支持地理定位。"); 
  } 
} 
function showError(error){ 
  switch(error.code) { 
    case error.PERMISSION_DENIED: 
      alert("定位失败,用户拒绝请求地理定位"); 
      break; 
    case error.POSITION_UNAVAILABLE: 
      alert("定位失败,位置信息是不可用"); 
      break; 
    case error.TIMEOUT: 
      alert("定位失败,请求获取用户位置超时"); 
      break; 
    case error.UNKNOWN_ERROR: 
      alert("定位失败,定位系统失效"); 
      break; 
  } 
} 
function showPosition(position){ 
   //将我们获取到的经纬度保存到变量中
  var latlon = position.coords.latitude+','+position.coords.longitude; 
 
  //baidu接口
  var url = "http://api.map.baidu.com/geocoder/v2/?ak=C93b5178d7a8ebdb830b9b557abce78b&callback=renderReverse&location="+latlon+"&output=json&pois=0" 
  $.ajax({ 
    type: "GET", 
    dataType: "jsonp", 
    url: url, 
    beforeSend: function(){ 
      $("#baidu").html('正在定位...'); 
    }, 
    success: function (data) { 
      if(data.status==0){ 
         $("#baidu").html(data.result.formatted_address); 
       } 
     }, 
     error: function (XMLHttpRequest, textStatus, errorThrown) { 
       $("#baidu").html(latlon+"地址位置获取失败"); 
    } 
  }); 
}