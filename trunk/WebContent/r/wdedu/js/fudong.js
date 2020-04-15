 window.onload=function(){
                var oDiv2=document.getElementById("box2");
                oDiv2.style.position="fixed";
				$('#box2').hide();
                oDiv2.style.bottom="0px";
               //监听鼠标滚动后，调用的函数
                 var scrollFunc=function(e){
                        //console.log(e);
                        e=e || window.event;
                        //判断滚动方向
                        var orient=e.deltaY;
                        //鼠标滚动速度
                       var speed=orient/100;
                       //控制top；移动div1，2
					   $('#box2').show();
					   oDiv2.style.bottom ="0px";
                      		var xx = getScrollHeight();
							var yy = getWindowHeight() + getDocumentTop();
							if(xx==yy){
									oDiv2.style.bottom ="253px";
								
								}
                 }
                 //注册监听事件
                 if(document.addEventListener){
                     document.addEventListener('DOMMouseScroll',scrollFunc,false);
                 }
                 window.onmousewheel=document.onmousewheel=scrollFunc;//IE/Opera/Chrome
             }
 //文档高度
function getDocumentTop() {
var scrollTop = 0,
bodyScrollTop = 0,
documentScrollTop = 0;
if (document.body) {
bodyScrollTop = document.body.scrollTop;
}
if (document.documentElement) {
documentScrollTop = document.documentElement.scrollTop;
}
scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
return scrollTop;

}
//可视窗口高度
function getWindowHeight() {
var windowHeight = 0;
if (document.compatMode == "CSS1Compat") {
windowHeight = document.documentElement.clientHeight;
} else {
windowHeight = document.body.clientHeight;
}
return windowHeight;
}

//滚动条滚动高度
function getScrollHeight() {
var scrollHeight = 0,
bodyScrollHeight = 0,
documentScrollHeight = 0;
if (document.body) {
bodyScrollHeight = document.body.scrollHeight;
}
if (document.documentElement) {
documentScrollHeight = document.documentElement.scrollHeight;
}
scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
return scrollHeight;
}