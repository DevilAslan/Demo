<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>信用瑞安 - ${channelName2}</title>
		<meta name="keywords" content="信用瑞安">
		<meta name="sitename" content="信用瑞安">
		<meta name="description" content="信用瑞安">
		<meta name="title" content="信用瑞安">
		<meta name="title" content="信用瑞安">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link rel="shortcut icon" type="image/x-icon" href="${basePath}images/favicon.ico">
		<link rel="stylesheet" type="text/css" href="${basePath}css/base.css"/>
		<link rel="stylesheet" href="${basePath}js/artDialog/ui-dialog.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}css/layout.css"/>
		<style>
			.list-all{
				width:100% !important;
			}
		</style>
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
			<div class="container">
				<div class="content overflow-hidden h-per100">
					<div class="list-top w-per100 clearfix">
						<div class="left list-tab">
							<@con.channel where="siteId=${siteId}" page="1" pageSize="10" channelPath=channelLevel1 curChannelId=channelLevel2 curClass="cur" basePath=basePath sysId=sysId/>
						</div>
						<div class="right clearfix w-per30">
							<div class="detail-bread-crumb-outer right">
								<div class="bread-crumb-inner w-auto clearfix">
									<span class="head-icon bread-crumb-icon2"></span>
									<span>：</span>
									<a href="${basePath}cms/${siteId}/${channelLevel1}.htm">${channelName1!""} </a>
									<span>></span>
									<a href="${basePath}cms/${siteId}/${channelLevel2}.htm">${channelName2!""}</a>
								</div>
							</div>
						</div>
					</div>
					<div class="blue-border">
						<div class="list-detail mg-0auto">
							<div class="detail-title">
								<h3>${content.title!""}</h3>
								<div class="btn-row">
									<span>来源：${content.origin!""}</span>
									<div>
										<i class="content-icon clock-icon"></i>
										<span>
											<#if content.releaseDate??>
												${content.releaseDate?string("yyyy-MM-dd")}
											</#if>
										</span>
									</div>
									<div>
										<i class="content-icon preview-icon"></i>
										<span>${content.viewsCount!}</span>
									</div>
								</div>
							</div>
							<div class="detail-content">
									${content.txt!}
							</div>
							<div class="detail-control">
								<div class="left">
									
								</div>
								<div class="right">
									<a href="${basePath}cms/${siteId}/${channelLevel2}.htm" class="default-btn">返回</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				
			</div>
			
			<!--底部-->
			<#include "../common/bottom.ftl" />
		</div>
		
		<script type="text/javascript">
		$(function(){
			//my97
			$(".calendar-icon").click(function() {
				WdatePicker({
					eCont:'calendar',
					skin:'twoer'
				})
			});
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
	</body>
</html>
