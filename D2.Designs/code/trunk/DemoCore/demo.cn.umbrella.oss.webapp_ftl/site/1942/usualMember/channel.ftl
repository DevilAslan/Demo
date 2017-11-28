<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>瑞安信用协会- ${channelName2}</title>
		<meta name="keywords" content="瑞安信用协会">
		<meta name="sitename" content="瑞安信用协会">
		<meta name="description" content="瑞安信用协会">
		<meta name="title" content="瑞安信用协会">
		<meta name="title" content="瑞安信用协会">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link rel="shortcut icon" type="image/x-icon" href="${basePath}images/creditAssociation.ico">
	
		
	</head>
	<body>
			<!--头部-->
			<#include "../common/top.ftl" />
			
			<div class="box m20 btm_bg02 pad_btm100">
				<div class="share_center">
			    	<h3 class="list_title"><i><img src="${basePath}images/creditAssociation/img/home.png" /></i>
			    	<a href="${basePath}cms/${siteId}/${channelLevel2}.htm">${channelName2!""} </a>
				    <span>></span>
				    <a href="${basePath}cms/${siteId}/${channelLevel2}.htm">${channelName3!""} </a>
			    	</h3>
			   	  <div class="list_box m30">
			        	<div class="menu_left">
			            	<h3>
			            		<div><i><img src="${basePath}images/creditAssociation/img/huiyuanfengcai.png" /></i><span>${channelName2}</span></div>
			            	</h3>
			                <ul class="menu_ul">
			                	<@con.channel_orange where="siteId=${siteId}" channelPath=channelLevel2 curChannelId=channelLevel3 curClass="a_on" basePath=basePath sysId=sysId/>
			                </ul>
			          	</div>
			            <div class="menu_right">
			            		<ul class="introduction_tabe">
	                    	<li><a href="${basePath}cms/${siteId}/viceStandingPresiden.htm">常务副会长</a></li>
	                        <li><a href="${basePath}cms/${siteId}/leaderUnit.htm">副会长单位</a></li>
	                        <li><a href="${basePath}cms/${siteId}/directorMember.htm">理事会员</a></li>
	                        <li class="no_border"><a href="${basePath}cms/${siteId}/usualMember.htm"  class="intro_li">普通会员</a></li>
	                    </ul>
	                      <ul class="ul_txt holder-content blue-border" id="list">
	                        <@con.contents_orange_2 channelPath=channelLevel4 page=page pageSize=pageSize basePath=basePath imgPath=basePath sysId=sysId query=query/>
	                    </ul>
			                <div id="jqueryPage" class="page-outer mg-t-10">
								<a href="javascript:void(0)" id="firstPage">
									<i class="s-icon first-page"></i>
								</a>
								<a href="javascript:void(0)" id="priorPage">
									<i class="s-icon prev-page"></i>
								</a>
								<span>第</span>
								<input type="text" size="3" id="page" value="${page}">
								<span>页/共${sum}页</span>
								<a href="javascript:void(0)" id="pageGo" class="go-btn">GO</a>
								<!--<button id="pageGo" type="button">跳转</button>-->
								<a href="javascript:void(0)" id="nextPage">
									<i class="s-icon next-page"></i>
								</a>
								<a href="javascript:void(0)" id="lastPage">
									<i class="s-icon last-page"></i>
								</a>
							</div>
			            			
			          	</div>
			          
			        </div>
			    </div>
			</div>
<script type="text/javascript">
	$(function(){
		$("#pageGo").click(function(){
			var query = jQuery.trim($(".search-input").val());
			var url = window.location.href;
			url=url.split("?")[0];
			url += "?page=";
			var page = $("#page").val();
			url+=page;
			if(query.length>0){
				url+=("&query="+query);
			}
			window.location.href=url;
		});
		$("#firstPage").click(function(){
			var query = jQuery.trim($(".search-input").val());
			var url = window.location.href;
			url=url.split("?")[0];
			url += "?page=1";
			if(query.length>0){
				url+=("&query="+query);
			}
			window.location.href=url;
		});
		$("#priorPage").click(function(){
			var query = jQuery.trim($(".search-input").val());
			var page =parseInt( '${page}');
			page = (page==1?1:page-1);
			var url = window.location.href;
			url=url.split("?")[0];
			url += "?page=";
			url+=page;
			if(query.length>0){
				url+=("&query="+query);
			}
			window.location.href=url;
		});
		$("#nextPage").click(function(){
			var query = jQuery.trim($(".search-input").val());
			var page =parseInt( '${page}');
			var sum =parseInt( '${sum}');
			page = (page==sum?page:page+1);
			var url = window.location.href;
			url=url.split("?")[0];
			url += "?page=";
			url+=page;
			if(query.length>0){
				url+=("&query="+query);
			}
			window.location.href=url;
		});
		$("#lastPage").click(function(){
			var query = jQuery.trim($(".search-input").val());
			var url = window.location.href;
			url=url.split("?")[0];
			url +="?page=${sum}";
			if(query.length>0){
				url+=("&query="+query);
			}
			window.location.href=url;
		});
	
		//my97
		$(".calendar-icon").click(function() {
			WdatePicker({
				eCont:'calendar',
				skin:'twoer'
			})
		});
		
		/*$(".btn-search").click(function(){
			var url = window.location.href;
			url=url.split("?")[0];
			var query = $(".search-input").val().trim();
			if(query.length>0)
				url+=("?query="+query);
			window.location.href=url;
		});*/
		$(".btn-search").click(function(){
			var value = $(this).prev().val();
			if($.trim(value)!=''){
				window.location.href="${basePath}cms/searchList.htm\?searchText="+value;
			}
		});
	});
	//文本溢出
    $(".dynamic-txt").each(function(i){
       var divH = $(this).height();
       var $p = $("p", $(this)).eq(0);
       while ($p.outerHeight() > divH) {
           $p.text($p.text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));
       };
    });
    
	//点击其他地方消失
	$(".calendar-icon").click(function(e){
       	var ev = e || window.event;
        if(ev.stopPropagation){
            ev.stopPropagation();
        }
        else if(window.event){
            window.event.cancelBubble = true;//兼容IE
        }
	});
	document.onclick = function(){
        $(".calendar-icon iframe").remove();
   	};
   	
   	//监听滚动，窗体变化
	$(window).scroll(function(){
		$(".calendar-icon iframe").remove();
	});
	$(window).resize(function(){
		$(".calendar-icon iframe").remove();
	});
</script>		
<script type="text/javascript">    
$(function () {
//文本溢出    
	$(".ass_txt").each(function (i) {    
		var divH = $(this).height();    
		var $span = $("span", $(this)).eq(0);    
		while ($span.outerHeight() > divH) {    
			$span.text($span.text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));    
		};    
	});    
});    
</script> 
<!--底部-->
<#include "../common/bottom.ftl" />
	</body>
</html>
