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
				<div class="content overflow-hidden">
					<div class="list-top w-per100 clearfix">
						<div class="left list-tab">
							<@con.channel where="siteId=${siteId}" page="1" pageSize="10" channelPath=channelLevel1 curChannelId=channelLevel2 curClass="cur" basePath=basePath sysId=sysId/>
						</div>
						<div class="right list-search">
							<form>
								<input type="text" class="search-input" placeholder="请输入关键字">
								<span class="btn-search"></span>
							</form>
						</div>
					</div>
					<div class="height475">
						<div class="h-per100">
							<div class="height400 pd-0 blue-border">
								<table class="table-list2">
									<colgroup>
                                        <col style="width: 50px;" />
                                        <col style="width: 960px;" />
                                        <col style="width: 100px;" />
                                    </colgroup>
									<thead>
										<tr>
											<th><i class="table-icon listing-icon"></i></th>
											<th></i>信用研究目录</th>
											<th class="t-a-center"><i class="table-icon date-icon"></i>时间</th>
										</tr>
									</thead>
									<tbody class="holder-content" id="list">
										<@con.contents_table flag="0" where="siteId=${siteId}" channelPath=channelLevel2 page=page pageSize=pageSize basePath=basePath imgPath=basePath sysId=sysId query=query/>
									</tbody>
								</table>
							</div>
							<div class="page-outer" style="margin-top:15px;">
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
			<#include "../common/bottom.ftl" />
		</div>
		<script type="text/javascript">
		$(function(){
			$("#pageGo").click(function(){
				var query = $(".search-input").val().trim();
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
				var query = $(".search-input").val().trim();
				var url = window.location.href;
				url=url.split("?")[0];
				url += "?page=1";
				if(query.length>0){
					url+=("&query="+query);
				}
				window.location.href=url;
			});
			$("#priorPage").click(function(){
				var query = $(".search-input").val().trim();
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
				var query = $(".search-input").val().trim();
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
				var query = $(".search-input").val().trim();
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
			
			$(".btn-search").click(function(){
				var value = $(this).prev().val();
				if($.trim(value)!=''){
					window.location.href="${basePath}cms/searchList.htm\?searchText="+value;
				}
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
