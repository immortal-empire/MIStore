/*
 * ============================================
 * favor.js
 * 我喜爱的商品管理
 * By 刘星星
 * ============================================
 */

var user = "";
var favors = [];

$(document).ready(function() {
	//获取用户信息
	user = window.localStorage.getItem("user");
	//如果还没有用户信息，则先存储一个空字符串
	if(user==null){
		user="";
		user = window.localStorage.setItem("user","");
	}
	
	//加载页面头部尾部
	$("#header").load("header.html");
	$("#footer").load("footer.html");
	
	//发送请求，获取用户喜爱的商品
	getFavorItems();
});

var getFavorItems = function() {

		//若未登录，则提示用户先登录
		if (user == "") {
			$("#notLogin").tmpl(null).appendTo("#favorlist");
		} else {
			//获取用户ID
			var userid = 0;
			if (user != "") {
				userid = JSON.parse(user).id;
			}
			$.ajax({
				type: "post",
				url: "getFavors.action",
				async: false,
				dataType: 'json',
				data: {
					"userid": userid
				},
				success: function(data) {
					$("#favorlist").html("");
					favors = data;

					//结果中包含有信息，则分页进行展示
					if (data.length > 0) {
						pagination(data, data.length);
					} else {
						//若无结果，则展示空白信息
						$("#Pagination").hide();
						$("#noItem").tmpl(null).appendTo("#favorlist");
					}
				}
			});
		}

	};

var pagination = function(data, count) {
		$(".page-pagination").show();
		layui.use('laypage', function() {
			if (count > 0) {
				displayResultByPage(data, 1, 5);
			}

			var laypage = layui.laypage;
			laypage.render({
				elem: 'pagination',
				//ID，不用加 # 号			
				theme: '#26CC71',
				layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
				count: count,
				limit: 5,
				limits: [5, 10],
				jump: function(obj, first) {
					if (!first) {
						displayResultByPage(data, obj.curr, obj.limit);
					}
				}
			});
		});
	}

var displayResultByPage = function(data, curr, limit) {
		$("#favorlist").html("");
		var newData = new Array();
		for (var i = ((curr - 1) * limit); i < data.length && i < (curr * limit); i++) {
			newData.push(data[i]);
		}

		$("#favorItem").tmpl(newData, {}).appendTo("#favorlist");
		favor_delete_item();
	}


var favor_delete_item = function() {
		$(".close").on("click", function() {
			if (confirm("确定要取消收藏吗 ?") === false) {
				return false;
			} else {
				var userid = 0;
				if (user != "") {
					userid = JSON.parse(user).id;
				}
				var proid = $(this).parents("tr").find("input[type=hidden]").val();

				var arr = $.grep(favors, function(item) {
					if (item.proId == proid) {
						return null;
					} else {
						return item;
					}
				});
				favors = arr;
				pagination(favors, favors.length);
				removeFromFavor(userid, proid);
			}
		});
	};

var removeFromFavor = function(userid, proid) {
		$.ajax({
			type: "post",
			url: "removeFromFavor.action",
			async: false,
			data: {
				"userid": userid,
				"proid": proid
			}
		});
	}