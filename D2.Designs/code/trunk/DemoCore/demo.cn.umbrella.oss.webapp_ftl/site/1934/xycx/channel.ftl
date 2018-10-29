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
							<a href="infoList-xydt.html"><i class="second-nav-fl"></i><span class="second-nav-fc">组织机构</span><i class="second-nav-fr"></i></a>
							<a href="infoList-bsdt-creditSearch.html" class="cur"><i class="second-nav-fl"></i><span class="second-nav-fc">信用查询</span><i class="second-nav-fr"></i></a>
							<a href="infoList-tssq-txsq.html"><i class="second-nav-fl"></i><span class="second-nav-fc">投诉申请</span><i class="second-nav-fr"></i></a>
							<a href="infoList-zxjd.html"><i class="second-nav-fl"></i><span class="second-nav-fc">咨询解答</span><i class="second-nav-fr"></i></a>
							<a href="infoList-bsdt-wzly.html"><i class="second-nav-fl"></i><span class="second-nav-fc">网站留言</span><i class="second-nav-fr"></i></a>
							<a href="infoList-download.html"><i class="second-nav-fl"></i><span class="second-nav-fc">相关下载</span><i class="second-nav-fr"></i></a>
						</div>
						<div class="right clearfix w-per30">
							<div class="detail-bread-crumb-outer right">
								<div class="bread-crumb-inner w-auto clearfix">
									<span class="head-icon bread-crumb-icon2"></span>
									<span>：</span>
									<a href="###">首页 </a>
									<span>></span>
									<a href="###">办事大厅 </a>
									<span>></span>
									<a href="###">信用查询</a>
								</div>
							</div>
						</div>
					</div>
					<div class="credit-search-box">
						<div class="search-box">
							<div class="head-search index-search">
								<div id="search-bd" class="search-bd">
									<ul class="clearfix">
										<li class="selected">
											<span>企业</span>
											<i class="index-arrow-down"></i>
										</li>
										<li onclick="changeUser();" class="spc-li">
											<span>个人</span>
											<i class="index-arrow-down"></i>
										</li>
										<li>
											<span>政府机关</span>
											<i class="index-arrow-down"></i>
										</li>
										<li>
											<span>事业单位</span>
											<i class="index-arrow-down"></i>
										</li>
										<li>
											<span>社会组织</span>
											<i class="index-arrow-down"></i>
										</li>
									</ul>
								</div>
								<div id="search-hd" class="search-hd">
									<input type="text" id="topSearchInput" class="search-input" style="display: block;">
									<span class="pholder">请输入企业名称或组织机构代码，多关键词用空格隔开</span>
									<a href="infoList-bsdt-cs-list.html" class="btn-search"></a>
									<#if creditSession.creditType?exists>
										<a href="${basePath}creditSearch/getPersonDetailInfo.htm" class="personal-btn blue-btn3">查看个人信用</a>
									<#else>
										<a href="javascript:return false;" class="personal-btn blue-btn3">查看个人信用</a>
									</#if>
								</div>
							</div>
						</div>
						<div class="content display-tr">
							<div class="relative h-per100">
								<ul class="index-content-ul clearfix">
									<li>
										<div>
											<i class="content-icon content-icon1"></i>
											<p>9000万+企业</p>
											<span>支持多种查询，海量信息想查就查</span>
										</div>
									</li>
									<li>
										<div>
											<i class="content-icon content-icon2"></i>
											<p>6000万+个人</p>
											<span>经实名认证可查询更多详细信息，保护你的隐私</span>
										</div>
										
									</li>
									<li>
										<div>
											<i class="content-icon content-icon3"></i>
											<p>权威来源</p>
											<span>数据与浙江政务服务网同步，实时更新</span>
										</div>
									</li>
									<li>
										<div>
											<i class="content-icon content-icon4"></i>
											<p>全量数据</p>
											<span>资质信息、荣誉信息处罚信息等，全量信息库</span>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				
			</div>
			
			<#include "../common/bottom.ftl" />
		</div>
		<script type="text/javascript">
			//my97
			$(".calendar-icon").click(function() {
				WdatePicker({
					eCont:'calendar',
					skin:'twoer'
				})
			});
			
			$(".btn-search").click(function(){
				var path = "${basePath}";
				var queryType = $(".search-bd .clearfix .selected input").val(); 
				if(queryType=='1'){
					return;
				}
				if(param.length<2){
					$.GD.msg("最少输入2个字符 ");
			        return;
				}
				var param = $('#topSearchInput').val();
				if(param.replace(/(^\s*)|(\s*$)/g, "")==''){
					return;
				}
				 var pattern = new RegExp("((?=[\x21-\x7e]+)[^A-Za-z0-9\-])");
				if(pattern.test(param)){
						$.GD.msg("请勿输入特殊字符");
				        return;
				    } 
				var pattern2 = new RegExp("[【】（），《。》（）？——]");
				if(pattern2.test(param)){
					$.GD.msg("请勿输入特殊字符");
			        return;
			    } 
				var url = encodeURI(path+"creditSearch/searchList.htm?param="+param+"&queryType="+queryType);
				window.location.href = url; 
		});
	    $(".btn-search").click(function(){
				var url = window.location.href;
				url=url.split("?")[0];
				var query = $(".search-input").val().trim();
				if(query.length>0)
					url+=("?query="+query);
				window.location.href=url;
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
