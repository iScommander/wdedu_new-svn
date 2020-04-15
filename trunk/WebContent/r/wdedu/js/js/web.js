var ua = navigator.userAgent.toLowerCase();
var isIE6 = ua.indexOf("msie 6") > -1;//ΪIE6

// remove css image flicker
//IE6
if(isIE6){
    try{
        document.execCommand("BackgroundImageCache", false, true);
    }catch(e){}
}
//png24
if (!!window.DD_belatedPNG) {
    DD_belatedPNG.fix('img,.header .mid .btn_group ul li.login a,.header .mid .searchbox .btn_input,.recommend .recommend_con .item .btn_buy,.routes .routes_left .bd .league ul li .teletext .tip');
};
	            $(function() {					
					
                $("#tzy_yx").click(function() {
					if($(this).hasClass('stylediv')){
						$(this).addClass("stylediv1").removeClass("stylediv1");
						$(".tbzy_3_1").addClass("tbzy_3").removeClass("tbzy_3_1");
						}
						if($(this).hasClass('stylediv1')){
						$(this).addClass("stylediv").removeClass("stylediv1");
						$(".tbzy_3_1").addClass("tbzy_3").removeClass("tbzy_3_1");
						}
				});
				

				$(".tbzy_3").click(function() {
					if($(this).hasClass('tbzy_3')){
						$(this).addClass("tbzy_3_1").removeClass("tbzy_3");
						$("#tzy_yx").addClass("stylediv1").removeClass("stylediv");
						return;
						}
					if($(this).hasClass('tbzy_3_1')){
						
						$(this).addClass("tbzy_3").removeClass("tbzy_3_1");
						
						}
						
				});
				

            });


			