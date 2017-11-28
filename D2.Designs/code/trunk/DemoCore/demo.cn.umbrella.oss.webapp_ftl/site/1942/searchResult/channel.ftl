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

	                        <#if contentList??&&contentList?size gt 0>
	                   		<ul class="ul_txt" id="itemContainer">
							<#list contentList as content >
									<li>
										<dl>
									<#if content.hasTitleImg??&&content.hasTitleImg>
										<dt><img  src="${titleImgGet!""}${content.titleImg!""}"/></dt>
									</#if>
										<dd>
											<#if content.link??>
												<#if content.link!=''>
												<a target="_blank" href="${content.link}" title="${content.title!""}">${content.title!""}</a>
												<#else> 
												<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm">${content.title!""}</a>
												</#if>
											<#else> 
											<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title="${content.title!""}">
												${content.title!""}
											</a>
											</#if>
											<p>发布时间：${content.releaseDate?string("yyyy-MM-dd")}</p>
				              <div class="ass_txt">
				              	<span>
				                  	${content.description!""}
				                  </span>
				              </div>
										</dd>
								</dl>
								</li>
							</#list>
	                    </ul>
	                    
	                    <div class="holder_menu">
			                    <div class="holder"></div>
			                    <div id="legend1" class="legend"></div>
			                    <div id="legend2" class="legend"></div>
			                    <form class="holder_form">
			                        <label>显示条数: </label>
			                        <select>
			                          <option>5</option>
			                          <option>10</option>
			                          <option>20</option>
			                        </select>
			                    </form>
			                    <input type="text" value="1" class="page_input" />
			                    <button class="page_go">go</button>
			                </div>
						<#else>
							<div class="no_having">Sorry! 搜索无相关信息...</div>
						</#if>
	                    
			        </div>
			    </div>
			</div>
<script type="text/javascript" src="${basePath}js/creditAssociation/jPages.js"></script>
<script>
$(function() {
$("div.holder").jPages({
  containerID : "itemContainer",
first       : "首页",
last        : "末尾",
perPage: 5,
  callback    : function( pages, items ){
    $("#legend1").html("第 " + pages.current + " / " + pages.count + "页");
    $("#legend2").html(items.range.start + " - " + items.range.end + " of " + items.count);
  }
});
/* 条数select */
$("select").change(function(){  
  var newPerPage = parseInt( $(this).val() );
  $("div.holder").jPages("destroy").jPages({
    containerID   : "itemContainer",
first       : "首页",
	last        : "末尾",
    perPage       : newPerPage,
 callback    : function( pages, items ){
    $("#legend1").html("第 " + pages.current + "页" + " 共 " + pages.count);
    $("#legend2").html(items.range.start + " - " + items.range.end + " of " + items.count);
  }
  });
});
/* 页面输入跳转 */
$(".page_go").click(function(){
  var page = parseInt( $(".page_input").val() );
  $("div.holder").jPages( page );
});
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
$(function () {
//搜索填值
 var url = window.location.search;
 var loc = url.substring(url.lastIndexOf('=')+1, url.length);
 $('.serth_input').val(decodeURI(loc)); 
}); 

</script> 
<!--底部-->
<#include "../common/bottom.ftl" />
	</body>
</html>
