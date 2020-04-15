var dlNum = $("#selectList").find("dl");
for(i = 0; i < dlNum.length; i++) {
	$(".hasBeenSelected .clearList").append("<div class=\"selectedInfor selectedShow\" style=\"display:none\"><span></span><label></label><em></em></div>");
}

var refresh = "true";

$(".listIndex a").on("click", function() {
	var text = $(this).find("label").html();
	var selectedShow = $(".selectedShow");
	var textTypeIndex = $(this).parents("dl").index();
	var textType = $(this).parent("dd").siblings("dt").text();
	//	index = textTypeIndex - (2);
	index = textTypeIndex;
	$(".clearDd").show();
	$(".selectedShow").eq(index).show();
	$(this).addClass("selected").siblings().removeClass("selected");
	selectedShow.eq(index).find("span").text(textType);
	selectedShow.eq(index).find("label").text(text);
	var show = $(".selectedShow").length - $(".selectedShow:hidden").length;
	if(show > 1) {
		$(".eliminateCriteria").show();
	}

});

$(".listIndex span.more").on("click", function() {    
	var list = $(this).parent().parent();    
	if(list.hasClass("active")) {        
		$(this).parent().parent().removeClass("active");    
	} else {        
		$(this).parent().parent().addClass("active");    
	}
});

$(".selectedShow em").on("click", function() {
	$(this).parents(".selectedShow").hide();
	var textTypeIndex = $(this).parents(".selectedShow").index();
	index = textTypeIndex;
	$(".listIndex").eq(index).find("a").removeClass("selected");

	if($(".listIndex .selected").length < 2) {
		$(".eliminateCriteria").hide();
	}
});

$(".eliminateCriteria").on("click", function() {
	$(".selectedShow").hide();
	$(this).hide();
	$(".listIndex a ").removeClass("selected");
});