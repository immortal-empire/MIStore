/*
 * ============================================
 * all_product_category.js
 * 展示全部分类下商品
 * By 刘星星
 * ============================================
 */
$(document).ready(function() {
	categories();
	toggle();
});

//折叠或收起某一分类
var toggle = function() {
		//当某一分类标题被点击
		$("h3").click(function() {
			//1、切换图标
			if ($(this).find('i').attr('class') == 'fa fa-chevron-circle-down') {
				$(this).find('i').removeClass("fa-chevron-circle-down");
				$(this).find('i').addClass("fa-chevron-circle-up");
			} else {
				$(this).find('i').removeClass("fa-chevron-circle-up");
				$(this).find('i').addClass("fa-chevron-circle-down");
			}
			//2、隐藏下方的div
			var div = $(this).parents("section").find(".t-center");
			div.fadeToggle(300);
		});
	};



//加载商品分类信息
var categories = function() {
		$.ajax({
			type: "post",
			url: "all_product_category.action",
			async: false,
			dataType: 'json',
			success: function(data) {
				$.each(data, function(item) {
					if (data[item].length > 0) {

						//1、针对每个分类名，首先先生成工具条
						$("#category").tmpl(null, {
							getCategoryName: function() {
								return item;
							}
						}).appendTo("#wrapContainer");

						//2、获得新生成的工具条
						var section = $("#" + item).find(".t-center");

						//3、对工具条下的div，动态添加商品数据
						$("#products").tmpl(data[item], {
							getProName: function() {
								return this.data.twoType.comttyName;
							}
						}).appendTo(section);
					}
				});
			}
		});
	}