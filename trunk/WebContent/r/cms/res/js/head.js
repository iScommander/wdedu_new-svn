//head状态展示
function statusActive(localStatus) {
	var index = localStatus.id;
	var subIndex = localStatus.subId;
	if(index == 0) {
		$("#active").addClass("active").siblings().removeClass("active");
		$("#fixed-sub-nav-wrap section").removeClass("swingInX");
	} else {
		$(".head-nav ul li").eq(index).addClass("active").siblings().removeClass("active");
		var $dom = $("#fixed-sub-nav-wrap section").eq(index - 1);
		$dom.addClass("swingInX").show().siblings().hide();
		$dom.find("ul").children().eq(subIndex).addClass("active");
	}
};
$("#index").click(function() {
	$(this).addClass("active").siblings().removeClass("active");
	$("#fixed-sub-nav-wrap").addClass("swingOutX");
	$("#fixed-sub-nav-wrap section").removeClass("swingInX");
});
function navClick(obj, id) {
	$("#fixed-sub-nav-wrap").removeClass("swingOutX");
	$(obj).addClass("active").siblings().removeClass("active");
	var i = $(id).index();
	$("#fixed-sub-nav-wrap section").eq(i).addClass("swingInX").show().siblings().hide();
};