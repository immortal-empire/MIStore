var keyword = "";
var proType = "";
var user = "";
var products = [];

$(document).ready(function() {
	keyword = sessionStorage["keyword"];
	proType = sessionStorage["proType"];
	sessionStorage["keyword"] = "";
	sessionStorage["proType"] = "";

	user = window.localStorage.getItem("user");

	$("#header").load("header.html", function() {
		$("#searchProduct").val(keyword);
	});
	$("#footer").load("footer.html");

	search_result();
	sortManage();
});

var search_result = function() {

		var isAvailable = $("#isAvailable").is(':checked');
		var userid = 0;
		if (user != "") {
			userid = JSON.parse(user).id;
		}

		if ((keyword != "" && keyword != undefined) || (proType != "" && proType != undefined)) {
			$.ajax({
				type: "post",
				url: "searchProduct.action",
				async: false,
				dataType: 'json',
				data: {
					"keyword": keyword,
					"proType": proType,
					"isAvailable": isAvailable,
					"userid": userid
				},
				success: function(data) {
					$("#resultDisplay").html("");
					products = data;
					console.log(JSON.stringify(data));

					//结果中包含有信息，则进行展示
					if (data.length > 0) {
						pagination(data, data.length);
					} else {
						//若无结果，则展示错误信息
						$("#Pagination").hide();
						$("#controlPanel").hide();
						$("#result404").tmpl(null, {
							getKeyword: function() {
								return keyword;
							}
						}).appendTo("#resultDisplay");
					}
				}
			});
		}
	}

var pagination = function(data, count) {
		$(".page-pagination").show();
		layui.use('laypage', function() {
			if (count > 0) {
				displayResultByPage(data, 1, 8);
			}

			var laypage = layui.laypage;
			laypage.render({
				elem: 'pagination',//ID，不用加 # 号			
				theme: '#26CC71',
				layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
				count: count,
				limit: 8,
				limits: [8, 16],
				jump: function(obj, first) {
					if (!first) {
						displayResultByPage(data, obj.curr, obj.limit);
					}
				}
			});
		});
		favorManage();
	}

var displayResultByPage = function(data, curr, limit) {
		$("#resultDisplay").html("");
		var newData = new Array();
		for (var i = ((curr - 1) * limit); i < data.length && i < (curr * limit); i++) {
			newData.push(data[i]);
		}

		$("#resultItem").tmpl(newData, {}).appendTo("#resultDisplay");
	}

var favorManage = function() {
		$(".fa-pull-left").click(function() {
			if (user == "") {
				$('#collectFailed').modal('show');
			} else {
				if ($(this).hasClass('fa-heart-o')) { //未收藏，切换为收藏
					//更新数据库
					var proid = $(this).parents(".coupon-single").find("input[type=hidden]").val();
					addToFavor(JSON.parse(user).id, proid);

					//更改图标样式
					$(this).removeClass("fa-heart-o");
					$(this).addClass("fa-heart");
					$(this).css("color", "red");
				} else { //已收藏，取消收藏
					//更新数据库
					var proid = $(this).parents(".coupon-single").find("input[type=hidden]").val();
					removeFromFavor(JSON.parse(user).id, proid);

					//更改图标样式
					$(this).removeClass("fa-heart");
					$(this).css("color", "");
					$(this).addClass("fa-heart-o");
				}
			}
		});
	};

var addToFavor = function(userid, proid) {
		$.ajax({
			type: "post",
			url: "addToFavor.action",
			async: false,
			data: {
				"userid": userid,
				"proid": proid
			}
		});
	}

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

var sortManage = function() {
		//按价格排序，通过点击事件触发
		$("#sortByPrice").click(function() {
			$(this).find("a").css("color", "orangered");
			if ($(this).find("i").hasClass('fa-long-arrow-up')) {
				$(this).find("i").removeClass("fa-long-arrow-up");
				$(this).find("i").addClass("fa-long-arrow-down");
				//更改为降序排列
				var tempData = products;
				tempData.sort(sortByPriceDesc);
				pagination(tempData, tempData.length);
			} else {
				$(this).find("i").removeClass("fa-long-arrow-down");
				$(this).find("i").addClass("fa-long-arrow-up");
				//更改为升序排列
				var tempData = products;
				tempData.sort(sortByPriceAsc);
				pagination(tempData, tempData.length);
			}
		});

		//按新品（上架时间进行排序）
		$("#latestProduct").click(function() {
			$(this).find("a").css("color", "orangered");
			var tempData = products;
			tempData.sort(sortLatest);
			pagination(tempData, tempData.length);
		});

		//按销量进行排序
		$("#sortByVolume").click(function() {
			$(this).find("a").css("color", "orangered");
			if ($(this).find("i").hasClass('fa-long-arrow-up')) {
				$(this).find("i").removeClass("fa-long-arrow-up");
				$(this).find("i").addClass("fa-long-arrow-down");
				//更改为降序排列
				var tempData = products;
				tempData.sort(sortByVolumeDesc);
				pagination(tempData, tempData.length);
			} else {
				$(this).find("i").removeClass("fa-long-arrow-down");
				$(this).find("i").addClass("fa-long-arrow-up");
				//更改为升序排列
				var tempData = products;
				tempData.sort(sortByVolumeAsc);
				pagination(tempData, tempData.length);
			}
		});

		//只查看有货的商品
		$("#isAvailable").change(function() {
			search_result();
		});
	};

var sortByPriceDesc = function(x, y) {
		return y.sellingPrice - x.sellingPrice;
	};

var sortByPriceAsc = function(x, y) {
		return x.sellingPrice - y.sellingPrice;
	};

var sortByVolumeDesc = function(x, y) {
		return y.volume - x.volume;
	};

var sortByVolumeAsc = function(x, y) {
		return x.volume - y.volume;
	};

var sortLatest = function(x, y) {
		return y.addtime - x.addtime;
	};