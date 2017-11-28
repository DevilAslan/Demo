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
			<div class="container xfdm-list">
				<div class="content overflow-hidden min-height500">
					<div class="list-top w-per100 clearfix">
						<div class="left list-tab">
							<@con.channel where="site=${siteId}" channelPath=channelLevel1 curChannelId=channelLevel2 curClass="cur" basePath=basePath sysId=sysId/>
						</div>
						<div class="right list-search">
							<form>
								<input type="text" class="search-input" placeholder="输入关键字查找">
								<span class="btn-search"></span>
							</form>
						</div>
					</div>
					<div class="absolute">
						<div class="list-menu left">
							<dl>
								<dt>部门</dt>
								<#if channelLevel2??>
									<@channelTag where="siteId=${siteId},parentId=${channelLevel2}" page="1" pageSize="10"  sysId="${sysId}">
									<#if channelList??&&channelList?size gt 0>
										<#list channelList as channel>
											<dd 	
											<#if channelLevel3??&&channelLevel3==channel.channelId>
												class="cur"
											</#if>
											>
												<a href="
												<#if channel.hasContent>
													${basePath}cms/${siteId}/${channel.channelId}.htm
												<#else>
													${channel.link}
												</#if>
												">
													<span>${channel.channelName}</span><i class="s-icon 
													<#if channelLevel3??&&channelLevel3==channel.channelId>
													arrow-right-white-icon
													<#else>
													arrow-right-gray-icon
											</#if>"></i>
												</a>
											</dd>
										</#list>
									</#if>
								</@channelTag>
								</#if>
							</dl>
						</div>
						<div class="right w-per75 h-per100 datagrid-layout3">
							<table id="datagrid" class="user-datagrid easyui-datagrid clearfix" style="width:100%;"></table>
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
			
			//datagrid
			var oldHei =0 ,newHei = 0;
			$(function() {
				var icon1 = '<i class="table-icon company-icon"></i>',
					icon2 = '<i class="table-icon listing-icon"></i>';;
				$('#datagrid').datagrid({
					url: "${basePath}/publicity/getCorporations.json",
					columns: [
						[
							{field: 'creditEntityName',title: icon1+'信用主体名称',width: 100,
								formatter: function(value) {
									return '<a  href="###">' + value +'</a>';
								}
							},
							{field: 'codes',title: icon2+'统一社会信用代码',width: 30,align:'center'}
						]
					],
					fitColumns: true,//True 就会自动扩大或缩小列的尺寸以适应表格的宽度并且防止水平滚动。
					//striped:true,//斑马线
					method: "post",
					rownumbers:true,
					pagination:true,
					pageSize:10
					//queryParams: {},// 查询条件
					
				});
				// 列表==================End=============================================
				/*$('.user-datagrid').datagrid('resize', {
					height:'100%',
					width:'100%'
				});*/
				$('#datagrid').datagrid({loadFilter:pagerFilter});
				oldHei = $(".datagrid-body").height();
				
			});
			function pagerFilter(data){
				if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
					data = {
						total: data.length,
						rows: data
					}
				};
				var dg = $(this);
				var opts = dg.datagrid('options');
				var pager = dg.datagrid('getPager');
				pager.pagination({
					onSelectPage:function(pageNum, pageSize){
						opts.pageNumber = pageNum;
						opts.pageSize = pageSize;
						pager.pagination('refresh',{
							pageNumber:pageNum,
							pageSize:pageSize
						});
						dg.datagrid('loadData',data);
						newHei = $(".datagrid-body").height();
						var subtraction = newHei - oldHei;
						if(oldHei != newHei){
							$(".content").height($(".content").height()  + subtraction );
							oldHei = newHei;
						}
					}
				});
				if (!data.originalRows){
					data.originalRows = (data.rows);
				}
				var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
				var end = start + parseInt(opts.pageSize);
				data.rows = (data.originalRows.slice(start, end));
				return data;
			};
		</script>
	</body>
</html>
