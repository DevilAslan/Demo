<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>信用瑞安 - V信用</title>
		<meta name="keywords" content="信用瑞安">
		<meta name="sitename" content="信用瑞安">
		<meta name="description" content="信用瑞安">
		<meta name="title" content="信用瑞安">
		<meta name="title" content="信用瑞安">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link rel="shortcut icon" type="image/x-icon" href="${basePath}images/favicon.ico">
		<link rel="stylesheet" type="text/css" href="${basePath}css/base.css"/>
		<link href="${basePath}js/jquery-easyui-1.4.5/themes/default/easyui.css" rel="stylesheet" />
		<link rel="stylesheet" href="${basePath}js/artDialog/ui-dialog.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}css/layout.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}css/dataGrid.layout.css"/>
	</head>
	<body class="wrapper-static">
		<div class="static-page footer-layout2">
			
			<!--头部-->
			<#include "../common/top.ftl" />
			<!--banner-->
			<div class="page-banner">
				<div class="banner banner1"></div>
				<div class="banner banner2"></div>
			</div>
			
			<!--主体-->
			<div class="container detail">
				<div class="content overflow-hidden h-per100">
					<div class="list-top w-per100 clearfix">
						<div class="left list-tab">
							<@con.channel where="siteId=${siteId}" page="1" pageSize="10" channelPath=channelLevel1 curChannelId=channelLevel2 curClass="cur" basePath=basePath sysId=sysId/>
						</div>
						<div class="right list-search">
							<form>
								<input type="text" class="search-input" placeholder="输入关键字查找">
								<span class="btn-search"></span>
							</form>
						</div>
					</div>
					<div class="video-outer clearfix">
						<div class="left">
							<object data="" height="568" width="780"></object><!--放置视频的位置-->
						</div>
						<div class="right">
							<h5 class="video-list-title"><i class="content-icon hot-icon"></i>热门视频</h5>
							
							<@contentTag flag="0"  where="siteId=${siteId},channelId=${channelLevel2}" page="1" pageSize="3"  sysId="credit" query="${query!''}">
								<#if contentList??&&contentList?size gt 0>
								<#list contentList as content >
									<a href="javascript:void(0)" title="${content.title!''}" onclick="changeVideo('${content.txt!''}')" class="video-box">
										<img src="${titleImgGet!""}${content.titleImg!''}">
										<p>${content.description!""}</p>
									</a>
								</#list>
								</#if>
							</@contentTag>
							
							<a href="#videoList" class="more-video off">更多视频<i class="s-icon white-arrow-down"></i></a>
						</div>
					</div>
					<div class="video-list-outer" style="display: none;">
						<div class="video-list holder-content" id="videoList">
							<@contentTag flag="0"  where="siteId=${siteId},channelId=${channelLevel2}" page="${page}" pageSize="100"  sysId="credit" query="${query!''}">
								<#if contentList??&&contentList?size gt 0>
								<#list contentList as content >
									<div class="video-inner">
										<a href="javascript:void(0)" title="${content.title!''}" class="video-box" onclick="changeVideo('${content.txt!''}')">
											<img src="${titleImgGet!""}${content.titleImg!''}">
											<input type="hidden" value="${content.txt!''}"/>
										</a>
										<a href="javascript:void(0)" class="video-title" onclick="changeVideo('${content.txt!''}')">${content.title!''}</a>
									</div>
								</#list>
								</#if>
							</@contentTag>
						</div>
						<div class="page-box btn-row">
							<div class="holder"></div>
							<input type="text" value="1" id="pageNum"><button type="button" class="jump-btn">跳转</button>
						</div>
					</div>
				</div>
			</div>
			
			<!--底部-->
			<#include "../common/bottom.ftl" />
		</div>
		<script src="${basePath}js/jPages-master/js/jPages.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function changeVideo(url){
				$(".video-outer .left object").attr("data",url);
			}
			//展开视频列表
			$(document).on("click",".more-video.off",function(){
				$(".video-list-outer").show();
				$(this).addClass('on').removeClass('off');
				
				$("div.holder").jPages({
			    	containerID : 'videoList',
			    	previous : "",
			    	next : "",
			    	perPage : 6,//每页显示数据为多少行          
			    	startPage: 1, //起始页 
			    	ndRange: 1, //结束页码为1个
					midRange : 4, //最多显示几个页码页码,其余用..代替  
				/*	callback : function(page, items){
						pages.current
						pages.interval
						pages.count
					}*/
			    });
			});
			//隐藏视频列表
			$(document).on("click",".more-video.on",function(){
				$(".video-list-outer").hide();
				$(this).addClass('off').removeClass('on');
				$('.video-inner').removeAttr('style').removeClass('jp-hidden');
			});
			
			//页面跳转
			$(".jump-btn").click(function(){
		      var page = parseInt( $("#pageNum").val() );
		      $("div.holder").jPages( page );
		   });
		   
		   $(function(){
		   		$(".video-outer .left object").attr("data",$(".video-list-outer .video-list .video-inner a input").val());
		  
		  		$(".btn-search").click(function(){
					var value = $(this).prev().val();
					if($.trim(value)!=''){
						window.location.href="${basePath}cms/searchList.htm\?searchText="+value;
					}
				});
		   });
		   
		</script>
	</body>
</html>
