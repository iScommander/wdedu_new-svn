(function($) {
	
	
  'use strict';

  $(function() {
    var $fullText = $('.admin-fullText');
    $('#admin-fullscreen').on('click', function() {
      $.AMUI.fullscreen.toggle();
      $.AMUI.fullscreen.isFullscreen ? $fullText.text('关闭全屏') : $fullText.text('开启全屏');
    });
  });
})(jQuery);


/*if(navigator.appName == "Microsoft Internet Explorer") 
{ 
	if(navigator.appVersion.match(/7./i)=='7.'||navigator.appVersion.match(/8./i)=='8.'||navigator.appVersion.match(/6./i)=='6.') 
	{ 
	 alert('对不起，由于使用了很多最新技术，暂时不支持IE9以下的浏览器！请跟换其他浏览器或者升级浏览器！');
	 location.href='http://www.iefans.net/ie9-xiazai-jianti-zhongwenban/'; 
	} 
}*/