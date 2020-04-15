//tab跳转
$("ul.nav-pills li a").each(function(i, v) {
	var value = $(v).attr("href"); //v.href;
	var url = location.href;

	if(value && url.indexOf(value) >= 0) {
		$(this).parent().addClass("active");
	}         
})

////下拉弹出框
//$(function() {
//	$('select').comboSelect({
//		"inputClass": "combo-input text-input"
//	});
//	$(".combo-input").attr("readonly", "readonly"); //.css("cursor","pointer")
//});