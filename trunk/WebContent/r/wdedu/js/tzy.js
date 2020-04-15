// JavaScript Document
		
function tzyd(xx,xl){
document.getElementById("J_List").innerHTML+='<li><div class="tzy_list_xs_a f_l">'+xl+'</div><div class="tzy_list_xs f_l" title="'+xx+'">'+xx+'</div><div class="clearfix"></div></li>';
			var div=document.getElementById("tb")
	if (div!=null){
        div.style.display=="none"?"block":"none";
	}

	
	}
		

		$(function(){
		$("#aFloatTools_Show").click(function(){
			$('#divFloatToolsView').animate({width:'show',opacity:'show'},100,function(){$('#divFloatToolsView').show();});
			$('#aFloatTools_Show').hide();
			$('#aFloatTools_Hide').show();				
		});
		$("#aFloatTools_Hide").click(function(){
			$('#divFloatToolsView').animate({width:'hide', opacity:'hide'},100,function(){$('#divFloatToolsView').hide();});
			$('#aFloatTools_Show').show();
			$('#aFloatTools_Hide').hide();	
		});

            $("#aFloatTools_ShowTwo").click(function(){
                $('#divFloatToolsViewTwo').animate({width:'show',opacity:'show'},100,function(){$('#divFloatToolsViewTwo').show();});
                $('#aFloatTools_ShowTwo').hide();
                $('#aFloatTools_HideTwo').show();
            });
            $("#aFloatTools_HideTwo").click(function(){
                $('#divFloatToolsViewTwo').animate({width:'hide', opacity:'hide'},100,function(){$('#divFloatToolsViewTwo').hide();});
                $('#aFloatTools_ShowTwo').show();
                $('#aFloatTools_HideTwo').hide();
            });
	});
			$(".fillin-select-title").click( function(){
			var s=$(this).attr("value")
			var div=document.getElementById(s)
                if (div!=null){
                    div.style.display=="none"?"block":"none";
                }
				});
			$(".tzy_yxpm1").click( function(){
			$(this).addClass("bjtp");
			$(".tzy_yxpm2").removeClass("bjtp");  
			$(".tzy_yxpm3").removeClass("bjtp3");;  
			$(".tzy_yxpm4").removeClass("bjtp4");  
			})
			$(".tzy_yxpm2").click( function(){
			$(this).addClass("bjtp");  
			$(".tzy_yxpm1").removeClass("bjtp");  
			$(".tzy_yxpm3").removeClass("bjtp3");;  
			$(".tzy_yxpm4").removeClass("bjtp4");
			})
			$(".tzy_yxpm3").click( function(){
			$(this).addClass("bjtp3");
			$(".tzy_yxpm2").removeClass("bjtp");  
			$(".tzy_yxpm1").removeClass("bjtp");;  
			$(".tzy_yxpm4").removeClass("bjtp4");  
			})
			$(".tzy_yxpm4").click( function(){
			$(this).addClass("bjtp4");
			$(".tzy_yxpm2").removeClass("bjtp");  
			$(".tzy_yxpm3").removeClass("bjtp3");;  
			$(".tzy_yxpm1").removeClass("bjtp1"); 
			})
    $( document).ready(function() {
        $(".checkbox").click( function(){
			if($(this).hasClass('checked')){
				$(this).removeClass('checked');
				$(this).parent().find("span").removeAttr("style");
			$(this).parent().find("span").css('color','#333333')
				}else{
					$(this).addClass('checked');
					$(this).parent().find("span").css('color','#ff8d00')
					
					}

			})
			
			$(".gyjz_ss_y4_kk").click(function(){
				if($(this).hasClass('fxk')){
					$(this).removeClass('fxk');

					}else{
						$(this).addClass('fxk');
						}
				
				})
			
				
    });
	
	
	
	
	
			$(function () {
                //设置标杆
                var _line = parseInt($(window).height() / 4);
                $(window).scroll(function () {
                    //滚动730px，左侧导航固定定位
                    if ($(window).scrollTop() > 400) {
                        $('.fl_l').css({ 'position': 'fixed', 'top': 0 })
                    } else {
                        $('.fl_l').css({ 'position': '', 'top': "" })
                    };
                    $('.fl_l .follow').eq(0).addClass('active');
                    //滚动到标杆位置,左侧导航加active
                    $('.fl_r .follow').each(function () {
                        var _target = parseInt($(this).offset().top - $(window).scrollTop() - _line);
                        var _i = $(this).index();
                        if (_target <= 0) {
                            $('.fl_l .follow').removeClass('active');
                            $('.fl_l .follow').eq(_i).addClass('active');
                        }
                            //如果到达页面底部，给左侧导航最后一个加active
                        else if ($(document).height() == $(window).scrollTop() + $(window).height()) {
                            $('.fl_l .follow').removeClass('active');
                            $('.fl_l .follow').eq($('.fl_r .follow').length - 1).addClass('active');
                        }
                    });
                });
                $('.fl_l .follow').click(function () {
                    $(this).addClass('active').siblings().removeClass('active');
                    var _i = $(this).index();
                    $('body, html').animate({ scrollTop: $('.fl_r .follow').eq(_i).offset().top - _line }, 500);
                });
            });
				function beforePrint(){
        $('.indexNav').hide(); //隐藏头部
        $('.pdfyc').hide();
        $('.volunteerButton').hide();
        $('.route').hide(); //隐藏面包屑 $('#block-region-side-pre').hide(); //隐藏左侧导航栏
     }

     function afterPrint(){
        $('.indexNav').show();
        $('.pdfyc').show();
        $('.volunteerButton').show();
        $('.route').show();
     }