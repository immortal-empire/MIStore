//var cid=3;
var user = window.localStorage.getItem("user");
var cid = JSON.parse(user).id;

var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var curCount;//当前剩余秒数
    function sendMessage(){
    	curCount = count;
        $("#btn").attr("disabled", "true");
        $("#btn").val(curCount + "秒后可重新发送");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次请求后台发送验证码 TODO
    }
    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#btn").removeAttr("disabled");//启用按钮
            $("#btn").val("重新发送验证码");
        }
        else {
            curCount--;
            $("#btn").val(curCount + "秒后可重新发送");
        }
    } 
    var sms="";
    $("#btn").click(function(){
        var phone=$("#phone").val();
        if(phone!="")
        {
            $.ajax({
                url:"sendSMS",
                type:"post",
                data:{"phone":phone},
                success:function(result){
                    sms=result;
                }
            });
        }else{
             alert("请输入手机号");
            return false;
        }
    });
    $("#lo").click(function(){
        var code=$("#code").val();
        var phone=$("#phone").val();
        if(code==""){
            alert("请输入验证码");
        }else{
            if(sms==code){
                $.ajax({
                    url:"sendphone/"+cid+"/"+phone+".action",
                    type:"post",
                    success:function(data){
                    	window.localStorage.setItem("user",JSON.stringify(data));
                    	alert("绑定成功！");
                    	location.href="person.html";
                    }
                });
            }else{
                alert("验证码错误");
            };
        };
    });