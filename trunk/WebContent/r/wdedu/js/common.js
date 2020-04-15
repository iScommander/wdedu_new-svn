$(function(){
		
    /*tab标签切换*/
    function tabs(tabTit,on,tabCon){
	$(tabCon).each(function(){
	  $(this).children().eq(0).show();
	 
	  });
/*	$(tabTit).each(function(){
	  $(this).children().eq(0).addClass(on);
	  });*/
     $(tabTit).children().click(function(){
        $(this).addClass(on).siblings().removeClass(on);
         var index = $(tabTit).children().index(this);
         $(tabCon).children().eq(index).show().siblings().hide();
    });
     }
  tabs(".sxghfw_title","on6",".sxghfw_con");
   tabs(".sxk_title","on5",".sxk_con");
  tabs(".investment_title","on",".investment_con");
  tabs(".yxjs_table_title","on",".yxjs_table_con");
  tabs(".zyjs_table_title","on",".zyjs_table_con");
  tabs(".gwzz_table_title","on",".gwzz_table_con");
 })
 




	
