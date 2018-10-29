<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>信用瑞安 - ${channelName1} - ${channelName2}</title>
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
					</div>
					<div>
						<div class="blue-border">
							<div class="list-detail min-height">
                            <div class="qtdz">
                                <a href="http://wzfgw.wenzhou.gov.cn/">
                                    <span>瑞安市发改委</span>
                                </a>
                                <a href="http://www.ouhai.gov.cn/" target="_blank">
                                    <span>中国瓯海</span>
                                </a>
                                <a href="http://www.lucheng.gov.cn/?rxLoad=1&_rand=1483582971525" target="_blank">
                                    <span>瑞安鹿城</span>
                                </a>
                                <a href="http://www.longwan.gov.cn/" target="_blank">
                                    <span>中国龙湾</span>
                                </a>
                                <a href="http://www.yueqing.gov.cn/" target="_blank">
                                    <span>中国乐清市</span>
                                </a>
                                <a href="http://www.ruian.gov.cn/" target="_blank">
                                    <span>中国瑞安市</span>
                                </a>
                                <a href="http://www.dongtou.gov.cn/" target="_blank">
                                    <span>中国洞头</span>
                                </a>
                                <a href="http://www.zjpy.gov.cn/" target="_blank">
                                    <span>中国平阳</span>
                                </a>
                                <a href="http://www.cncn.gov.cn/" target="_blank">
                                    <span>中国苍南</span>
                                </a>
                                 <a href="http://www.wencheng.gov.cn/?rxLoad=1" target="_blank">
                                    <span>中国文成</span>
                                </a>
                                <a href="http://www.ts.gov.cn/" target="_blank">
                                    <span>中国泰顺</span>
                                </a>
                                <a href="http://www.creditchina.gov.cn/" target="_blank">
                                    <span>信用中国</span>
                                </a>
                                <a href="http://www.zjcredit.gov.cn/" target="_blank">
                                    <span>信用浙江</span>
                                </a>
                                <a href="http://www.nbcredit.net/zx/index.html" target="_blank">
                                    <span>信用宁波</span>
                                </a>
                                <a href="http://www.zjzwfw.gov.cn/" target="_blank">
                                    <span>浙江政务服务网</span>
                                </a>
                            </div>
                           	</div>
						</div>
					</div>
				</div>
				
			</div>
			
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
