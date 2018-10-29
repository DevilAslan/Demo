<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="renderer" content="webkit">
		<title>信用瑞安</title>
		<meta name="keywords" content=" 瑞安信用网">
		<meta name="sitename" content="瑞安信用网">
		<meta name="description" content=" 瑞安信用网">
		<meta name="title" content="瑞安信用网">
		<link rel="stylesheet" type="text/css" href="${basePath}css/base.css"/>
		<link rel="stylesheet" href="${basePath}js/artDialog/ui-dialog.css" />
		<link rel="shortcut icon" type="image/x-icon" href="${basePath}images/favicon.ico">

		<link rel="stylesheet" type="text/css" href="${basePath}css/web.layout.css"/>
		<script type="text/javascript">
			var basePath = "${basePath!""}";
			var pageView = "${pageView!""}";
		</script>
	</head>
	<body id="home_body">
		<div class="home-wrapper">
			<!--head-->
			<div class="head">
				<div class="logo"></div>
				<div class="head-right">
					<div class="search-box">
						<div class="search-inner">
        					<div class="relation-search">
								<div id="search-bd" class="search-bd">
									<ul class="clearfix">
											<li class="selected" data-placeholder="企业信用查询">
												<label>
													<i class="search-tab-left"></i>
													<span class="search-tab-center">企业</span>
													<i class="search-tab-right"></i>
												</label>
												<i class="index-arrow-down"></i>
												<input type="hidden" value="2"/>
											</li>
											<li onClick="
												<#if creditSession.creditType?exists>
													<#if creditSession.creditType=='2'>
														changeUser();
													<#else>
														<#if creditSession.authRange=='3'>
															avascript:window.location.href=encodeURI('${basePath}creditSearch/getPersonDetailInfo.htm');
														<#else>
															 authentication();
														</#if>
													</#if>
												<#else>
													loginGo();
												</#if>" data-placeholder="个人信用查询">
												<label>
													<i class="search-tab-left"></i>
													<span class="search-tab-center">个人</span>
													<i class="search-tab-right"></i>
												</label>
												<i class="index-arrow-down"></i>
												<input type="hidden" value="1"/>
											</li>
											<li data-placeholder="政府机关信用查询">
												<label>
													<i class="search-tab-left"></i>
													<span class="search-tab-center">政府机关</span>
													<i class="search-tab-right"></i>
												</label>
												<i class="index-arrow-down"></i>
												<input type="hidden" value="3"/>
											</li>
											<li data-placeholder="事业单位信用查询">
												<label>
													<i class="search-tab-left"></i>
													<span class="search-tab-center">事业单位</span>
													<i class="search-tab-right"></i>
												</label>
												<i class="index-arrow-down"></i>
												<input type="hidden" value="4"/>
											</li>
											<li data-placeholder="社会组织信用查询">
												<label>
													<i class="search-tab-left"></i>
													<span class="search-tab-center">社会组织</span>
													<i class="search-tab-right"></i>
												</label>
												<i class="index-arrow-down"></i>
												<input type="hidden" value="0"/>
											</li>
										</ul>
								</div>
								<div id="search-hd" class="search-hd clearfix">
									<div class="search-fl"></div>
									<div class="search-fc">
										<input type="text" id="searchInput" class="search-input">
										<span class="pholder" id="placeholder">企业信用查询</span>
										<a href="###" class="btn-search" id="search1"></a>
									</div>
									<div class="search-fr"></div>
								</div>
							</div>
        				</div>
					</div>
					<div class="user-control">
							<div class="user-info clearfix">
								<#if creditSession?exists&&creditSession.loginname?exists>
									<!--已登录-->
								<a href="javascript:" onclick="myCredit_1();" id="myCredit" class="user">
									<span class="home-img login-icon"></span>
									<span>你好,
										<#if creditSession.creditName?exists&&(creditSession.creditName!='')>
											${creditSession.creditName}
										<#else>
											${creditSession.loginname}
										</#if>
									</span>
									<i class="home-img arrow-down-icon"></i>
								</a>
								<#else>
									<!--未登录-->
									<a href="javascript:void(0);" onclick="loginGo();">
										<span class="home-img login-icon"></span>
										<span>登录</span>
									</a>
									<a href="javascript:void(0);" onclick="register();" id="register">
										<span class="home-img register-icon"></span>
										<span>注册</span>
									</a>
								</#if>
								
								
								
							</div>
						</div>
				</div>
			</div>
			
			<!--container-->
			<div class="container">
		        <!--选项卡-->
		        <div id='bigTabSwitch' class="tab-menu-box five-length">
		            <div data-tabbox='bigTabSwitch1' class="home on">
		            	<i class="tab-title-top"></i>
		            	<span class="tab-title-center">首页</span>
		            	<i class="tab-title-bottom"></i>
		            </div>
		            <div data-tabbox='bigTabSwitch2' id="tabBtn">
		            	<i class="tab-title-top"></i>
		            	<span class="tab-title-center">信息公开</span>
		            	<i class="tab-title-bottom"></i>
		            </div>
		            <div data-tabbox='bigTabSwitch3'>
		            	<i class="tab-title-top"></i>
		            	<span class="tab-title-center">信用百科</span>
		            	<i class="tab-title-bottom"></i>
		            </div>
		            <div data-tabbox='bigTabSwitch4'>
		            	<i class="tab-title-top"></i>
		            	<span class="tab-title-center">联合奖惩</span>
		            	<i class="tab-title-bottom"></i>
		            </div>
		            <div data-tabbox='bigTabSwitch6'>
		            	<i class="tab-title-top"></i>
		            	<span class="tab-title-center">办事大厅</span>
		            	<i class="tab-title-bottom"></i>
		            </div>
		        </div>
		        <!--首页-->
		        <div class="tab-content" style="display: block;" id='bigTabSwitch1'>
            		<div class="flex-box flex2-1 banner-outer">
            			<div class="flex-fl">
            				<div class="news-pictures item-box" id="newsPictures">
								<ul class="banner-img">
								
								<@contentTag flag="0"  where="siteId=${siteId},isRecommend=1" page="1" pageSize="6"  sysId="credit">
										<#if contentList??&&contentList?size gt 0>
										<#list contentList as content >
											<li>
												<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
													<img src="${titleImgGet!""}${content.titleImg!""}">
													<div class="news-text-outer">
														<span class="news-text">${content.title!""}</span>
													</div>
												</a>
											</li>
									
										</#list>
									</#if>
								</@contentTag>
								</ul>
								<ul class="banner-circle"></ul>
							</div>
            			</div>
            			<div class="flex-fc">
            				<div class="small-tab-parent">
						        <!--选项卡-->
						        <div id='bannerListTab' class="small-tab-box">
						            <div data-targent='bannerListTab1' class="on"><i class="title-fl"></i><span class="title-fc">信用动态</span><i class="title-fr"></i></div>
						            <div data-targent='bannerListTab2'><i class="title-fl"></i><span class="title-fc">信用政策</span><i class="title-fr"></i></div>
						        </div>
						        <!--内容-->
						        <div class="small-tab-content banner-list" style="display: block;" id='bannerListTab1'>
						        	<a href="${basePath}cms/${siteId}/xywz.htm" class="more-text">更多</a>
						        	<dl class="list">
									<#if content3??&&content3?size gt 0>
					           			<#list content3 as content>
					           				<dt <#if content_index==0>class="selected-text"</#if>>
												<i class="list-circle"></i><a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">${content.title!""}</a>
											</dt>
											<dd>
											</dd>
					           			</#list>
				           			</#if>
									</dl>
						        </div>
						        <!--内容-->
						        <div class="small-tab-content" id='bannerListTab2'>
						        	<a href="${basePath}cms/${siteId}/xyzc.htm" class="more-text">更多</a>
						           	<ul class="list zcfg-list">
										<#if content4??&&content4?size gt 0>
						           			<#list content4 as content>
						           				<li>
													<i class="list-circle"></i>
													<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
														
														<label>${content.title!""}</label>
														<span>[${(content.releaseDate?string("yyyy-MM-dd"))!""}]</span>
													</a>
												</li>
						           			</#list>
					           			</#if>
									</ul>
						        </div>
						    </div>
            			</div>
            			<div class="flex-fr small-tab-parent">
					        <!--选项卡-->
					        <div id='indexPublicReview' class="small-tab-box">
					            <div data-targent='indexPublicReview1' class="on"><i class="title-fl"></i><span class="title-fc">处罚公示</span><i class="title-fr"></i></div>
					            <div data-targent='indexPublicReview2'><i class="title-fl"></i><span class="title-fc">许可公示</span><i class="title-fr"></i></div>
					        </div>
					        <!--内容-->
					        <div class="small-tab-content" style="display: block;" id='indexPublicReview1'>
					        	<a href="${basePath}publicity/punishList.htm?siteId=${siteId}" class="more-text">更多</a>
					           	<ul class="list">
					           	
									<#if punishList??&&punishList?size gt 0>
					           			<#list punishList as punish>
					           				<li>
					           					<i class="list-circle"></i>
												<a target="_blank" href="${basePath}publicity/punishDetail.htm?uid=${punish.uid!""}&siteId=${siteId}" title = "${punish.lawCaseName}">${punish.lawCaseName!""}</a>
											</li>
					           			</#list>
					           		</#if>					           	
								</ul>
					        </div>
					        <!--内容-->
					        <div class="small-tab-content" id='indexPublicReview2'>
					        	<a href="${basePath}publicity/permitList.htm?siteId=${siteId}" class="more-text">更多</a>
				            	<ul class="list">
				            		<#if permitList??&&permitList?size gt 0>
					           			<#list permitList as permit>
					           				<li>
					           					<i class="list-circle"></i>
												<a href="${basePath}publicity/permitDetail.htm?uid=${permit.uid!""}&siteId=${siteId}" title = "${permit.projectName}">${permit.projectName!""}</a>
											</li>
					           			</#list>
					           		</#if>
								</ul>
					        </div>
					    </div>
            		</div>
            		<div class="flex-box flex2-1 flex2-1-2">
            			<div class="flex-fl home-btn-box">
            				<div class="">
            					<a href="${basePath}cms/${siteId}/tyxydm.htm">
            						<i class="home-img icon1"></i>
            						<span>统一社会信用代码</span>
            					</a>
            				</div>
            				<div class="">
            					<a href="${basePath}cms/${siteId}/vxy.htm">
            						<i class="home-img icon5"></i>
            						<span>V信用</span>
            					</a>
            				</div>
            				<div class="">
            					<a href="${basePath}cms/${siteId}/xycns.htm">
            						<i class="home-img icon3"></i>
            						<span>信用承诺书</span>
            					</a>
            				</div>
            				<div class="">
            					<a href="${basePath}cms/${siteId}/fwrd.htm">
            						<i class="home-img icon4"></i>
            						<span>服务热点</span>
            					</a>
            				</div>
            			</div>
            			<div class="flex-fc">
            				<div class="small-tab-parent">
						        <!--选项卡-->
						        <div id='rewardsAndPunishments' class="small-tab-box">
						            <div data-targent='rewardsAndPunishments1' class="on"><i class="title-fl"></i><span class="title-fc">守信激励</span><i class="title-fr"></i></div>
						            <div data-targent='rewardsAndPunishments2'><i class="title-fl"></i><span class="title-fc">失信惩戒</span><i class="title-fr"></i></div>
						        </div>
						        <!--内容-->
						        <div class="small-tab-content banner-list" style="display: block;" id='rewardsAndPunishments1'>
						        	<a href="${basePath}cms/${siteId}/lhcjdt.htm" class="more-text">更多</a>
						        	<ul class="list">
										<#if content1??&&content1?size gt 0>
						           			<#list content1 as content>
						           				<li>
						           					<i class="list-circle"></i>
													<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">${content.title!""}</a>
												</li>
						           			</#list>
					           			</#if>
									</ul>
						        </div>
						        <!--内容-->
						        <div class="small-tab-content" id='rewardsAndPunishments2'>
						        	<a href="${basePath}cms/${siteId}/sxcj.htm" class="more-text">更多</a>
						           	<ul class="list zcfg-list">
										<#if content2??&&content2?size gt 0>
						           			<#list content2 as content>
						           				<li>
						           					<i class="list-circle"></i>
													<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">${content.title!""}</a>
												</li>
						           			</#list>
					           			</#if>
									</ul>
						        </div>
						    </div>
            			</div>
            			<!--
            			<div class="flex-fc search-outer">
            				<div class="content-title-outer on">
            					<p class="content-title-tc title-box">
            						<i class="title-fl"></i><span class="title-fc">信用查询</span><i class="title-fr"></i>
            					</p>
            				</div>
            				<div class="search-inner">
            					<div class="relation-search enterprise-search">
									<div id="search-bd" class="search-bd">
										<ul class="clearfix">
											<li class="selected" data-placeholder="企业信用查询">
												<label>
													<i class="search-tab-left"></i>
													<span class="search-tab-center">企业</span>
													<i class="search-tab-right"></i>
												</label>
												<i class="index-arrow-down"></i>
												<input type="hidden" value="2"/>
											</li>
											<li onClick="
												<#if creditSession.creditType?exists>
													<#if creditSession.creditType=='2'>
														changeUser();
													<#else>
														<#if creditSession.authRange=='3'>
															avascript:window.location.href=encodeURI('${basePath}creditSearch/getPersonDetailInfo.htm');
														<#else>
															 authentication();
														</#if>
													</#if>
												<#else>
													loginGo();
												</#if>" data-placeholder="个人信用查询">
												<label>
													<i class="search-tab-left"></i>
													<span class="search-tab-center">个人</span>
													<i class="search-tab-right"></i>
												</label>
												<i class="index-arrow-down"></i>
												<input type="hidden" value="1"/>
											</li>
											<li data-placeholder="政府机关信用查询">
												<label>
													<i class="search-tab-left"></i>
													<span class="search-tab-center">政府机关</span>
													<i class="search-tab-right"></i>
												</label>
												<i class="index-arrow-down"></i>
												<input type="hidden" value="3"/>
											</li>
											<li data-placeholder="事业单位信用查询">
												<label>
													<i class="search-tab-left"></i>
													<span class="search-tab-center">事业单位</span>
													<i class="search-tab-right"></i>
												</label>
												<i class="index-arrow-down"></i>
												<input type="hidden" value="4"/>
											</li>
											<li data-placeholder="社会组织信用查询">
												<label>
													<i class="search-tab-left"></i>
													<span class="search-tab-center">社会组织</span>
													<i class="search-tab-right"></i>
												</label>
												<i class="index-arrow-down"></i>
												<input type="hidden" value="0"/>
											</li>
										</ul>
									</div>
									<div id="search-hd" class="search-hd">
										<input type="text" id="searchInput" class="search-input">
										<span class="pholder" id="placeholder">企业信用查询</span>
										<a href="###" class="btn-search" id="search1"></a>
									</div>
								</div>
            					
            				</div>
            			</div>-->
            			<div class="flex-fr small-tab-parent">
            				<!--选项卡-->
					        <div id='listNamesDouble' class="small-tab-box">
					            <div data-targent='listNamesDouble1' class="on"><i class="title-fl"></i><span class="title-fc">黑名单</span><i class="title-fr"></i></div>
					            <div data-targent='listNamesDouble2'><i class="title-fl"></i><span class="title-fc">红名单</span><i class="title-fr"></i></div>
					        </div>
					        <!--内容-->
					        <div class="small-tab-content" style="display: block;" id='listNamesDouble1'>
					        	<a target="_blank" href="${basePath}publicity/blackList.htm?siteId=${siteId}" class="more-text">更多</a>
					           	<ul class="list">
					           		 <#if blackList??&&blackList?size gt 0>
					           			<#list blackList as black>
					           				<li>
					           					<i class="list-circle"></i>
												<a target="_blank" href="${basePath}publicity/toBlackEnterpriseList.htm?tableName=${black.tableName!""}&blackName=${black.blackName!""}&ownerId=${black.ownerId!""}&siteId=${siteId}" title = "${black.ownerId}:${black.blackName}">${black.ownerId!""}:${black.blackName!""}</a>
											</li>
					           			</#list>
					           		</#if>
								</ul>
					        </div>
					        <!--内容-->
					        <div class="small-tab-content" id='listNamesDouble2'>
					        	<a target="_blank" href="${basePath}publicity/redList.htm?siteId=${siteId}" class="more-text">更多</a>
				            	<ul class="list">
										<#if redList??&&redList?size gt 0>
											<#list redList as red >
												<li>
													<i class="list-circle"></i>
													<a target="_blank" href="${basePath}publicity/toRedInfoList.htm?tableName=${red.tableName!""}&redName=${red.tableNameCn!""}&siteId=${siteId}" title = "${red.tableOwnerName}:${red.tableNameCn}">
														${red.tableOwnerName!""}:${red.tableNameCn!""}
													</a>
												</li>
											</#list>
										</#if>
								</ul>
					        </div>
					    </div>
            		</div>
            		<!--广告-->
            		 <div class="advertisement-outer slider">
			        	<span class="prev"><i class="home-img prev-icon"></i></span>
						<span class="next"><i class="home-img next-icon"></i></span>
							
			        	<div class="slider-box">
			        		<div class="content">
			        			<@contentTag flag="0"  where="channelId=1,hasTitleImg=1" page="1" sysId="credit">
									<#if contentList??&&contentList?size gt 0>
										<#list contentList as content >
										<div class="img-box">
				            				<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.titleImg}"><img src="${titleImgGet!""}${content.titleImg!""}" /></a>
				            			</div>
										</#list>
									</#if>
								</@contentTag>
			        		</div>
			        	</div>
		        	</div>
		        </div>
		        <!--信息公开-->
		        <div class="tab-content" id='bigTabSwitch2'>
		        	<div class="flex-box btn-group-box">
		        		<@channelTag where="siteId=${siteId},parentId=xxgk" page="1" pageSize="5" sysId="credit">
							<#if channelList??&&channelList?size gt 0>
								<#list channelList as channel>
									<a href="
									<#if channel.hasContent>
										${basePath}cms/${siteId}/${channel.channelId}.htm
									<#else>
										${channel.link}
									</#if>
									" target="_blank"><i class="btn-fl" title = "${channel.channelName}"></i><span class="btn-fc">${channel.channelName}</span><i class="btn-fr"></i></a>
								</#list>
							</#if>
						</@channelTag>
		        	
		        	</div>
					<div class="flex-box flex3-2">
						<div class="flex-fl">
					        <div class="content-title-outer title-box on">
					        	<i class="title-fl"></i>
					        	<span class="title-fc">
					        		<span class="content-title-fl">处罚公示</span>
	            					<a href="${basePath}publicity/punishList.htm?siteId=${siteId}" target="_blank" class="content-title-fr">更多</a>
	            				</span>
	            				<i class="title-fr"></i>
            				</div>
            				<div class="flex-content">
            					<ul class="list">
            					
            						<#if punishList??&&punishList?size gt 0>
					           			<#list punishList as punish>
					           				<li>
					           					<i class="list-circle"></i>
												<a target="_blank" href="${basePath}publicity/punishDetail.htm?uid=${punish.uid!""}&siteId=${siteId}" title = "${punish.lawCaseName}">${punish.lawCaseName!""}</a>
											</li>
					           			</#list>
					           		</#if>
								</ul>
            				</div>
					    </div>
					    <div class="flex-fc">
            				<div class="content-title-outer title-box on">
					        	<i class="title-fl"></i><span class="title-fc"><span class="content-title-fl">许可公示</span>
					        	
					        	<a href="${basePath}publicity/permitList.htm?siteId=${siteId}" target="_blank" class="content-title-fr">更多</a>
					        	</span><i class="title-fr"></i>
            				</div>
            				<div class="flex-content">
            					<ul class="list">
            						<#if permitList??&&permitList?size gt 0>
					           			<#list permitList as permit>
					           				<li>
					           					<i class="list-circle"></i>
												<a href="${basePath}publicity/permitDetail.htm?uid=${permit.uid!""}&siteId=${siteId}" title = "${permit.projectName}">${permit.projectName!""}</a>
											</li>
					           			</#list>
					           		</#if>
								</ul>
            				</div>
					    </div>
					    <div class="flex-fr cns-box">
					    	<ul class="banner-img">
								<li>
									<a href="${basePath}publicity/list.htm?siteId=${siteId}">
										<img src="images/home/cns.jpg">
										<div class="news-text-outer">
											<span class="news-text">信用承诺书公示</span>
										</div>
									</a>
								</li>
							</ul>
					    </div>
					</div>		           	
		        	<div class="flex-box flex2-3">
		        		<div class="flex-fl tab-table small-tab-parent">
					        <!--选项卡-->
					        <div id='uploadDataList' class="small-tab-box">
					            <div data-targent='uploadDataList1' class="on"><i class="title-fl"></i><span class="title-fc">新上传数据提醒</span><i class="title-fr"></i></div>
					            <div data-targent='uploadDataList2'><i class="title-fl"></i><span class="title-fc">数据上传统计</span><i class="title-fr"></i></div>
					        </div>
					        <!--内容-->
					        <div class="small-tab-content" style="display: block;" id='uploadDataList1'>
				        		<iframe id="uploadDataFrame1" src="${basePath}uploadDataList1.htm" width="686px" height="173px" border="0" cellspacing="0" cellpadding="0" style="border: 0 none;"></iframe>
					        </div>
					        <!--内容-->
					        <div class="small-tab-content" id='uploadDataList2'>
					           	<iframe id="uploadDataFrame2" src="${basePath}uploadDataList2.htm" width="686px" height="173px" border="0" cellspacing="0" cellpadding="0" style="border: 0 none;"></iframe>
					        </div>
		        		</div>
		        		<div class="flex-fr search-outer">
            				<div class="content-title-outer on">
            					<p class="content-title-tc title-box">
            						<i class="title-fl"></i><span class="title-fc">统一社会信用代码</span><i class="title-fr"></i>
            					</p>
            				</div>
            				<div class="flex-content tyxydm">
            					<a href="${basePath}cms/${siteId}/qy.htm">
            						<i class="xydm-btn xydm-icon1"></i>
            						<span class="btn-center">企业</span>
            					</a>
            					<a href="${basePath}cms/${siteId}/zfjg.htm">
            						<i class="xydm-btn xydm-icon2"></i>
            						<span class="btn-center">政府机关</span>
            					</a>
            					<a href="${basePath}cms/${siteId}/shzz1.htm">
            						<i class="xydm-btn xydm-icon3"></i>
            						<span class="btn-center">社会组织</span>
            					</a>
            					<a href="${basePath}cms/${siteId}/sydw.htm">
            						<i class="xydm-btn xydm-icon4"></i>
            						<span class="btn-center">事业单位</span>
            					</a>
            				</div>
		        		</div>
		        	</div>
		        </div>
		        <!--信用百科-->
		        <div class="tab-content" id='bigTabSwitch3'>
		        	<div class="flex-box btn-group-box">
		        		<@channelTag where="siteId=${siteId},parentId=xybk" page="1" pageSize="5" sysId="credit">
							<#if channelList??&&channelList?size gt 0>
								<#list channelList as channel>
									<a title = "${channel.channelName}" href="
									<#if channel.hasContent>
										${basePath}cms/${siteId}/${channel.channelId}.htm
									<#else>
										${channel.link}
									</#if>
									"><i class="btn-fl"></i><span class="btn-fc">${channel.channelName}</span><i class="btn-fr"></i></a>
								</#list>
							</#if>
						</@channelTag>
		        	</div>
		        	<div class="flex-box flex3-3">
		        		<div class="flex-fl">
            				<div class="content-title-outer title-box on">
					        	<i class="title-fl"></i><span class="title-fc"><span class="content-title-fl">信用动态</span><a href="${basePath}cms/${siteId}/xywz.htm" class="content-title-fr">更多</a></span><i class="title-fr"></i>
            				</div>
		        			<div class="flex-content clearfix">
		        				<div class="slider-img slider-img1">
									<div class="container">
										<ul class="content">
											<@contentTag flag="0"  where="siteId=${siteId},channelId=xywz,channelId=xyzj,channelId=xyzg,hasTitleImg=1" page="1" pageSize="4"  sysId="credit">
													<#if contentList??&&contentList?size gt 0>
													<#list contentList as content >
														<li><a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.titleImg}"><img src="${titleImgGet!""}${content.titleImg!""}"></a></li>
													</#list>
												</#if>
											</@contentTag>
										</ul>
									</div>
									<ul class="banner-circle nav">
										<li class="">
											<a href="###"></a>
										</li>
										<li class="">
											<a href="###"></a>
										</li>
										<li class="">
											<a href="###"></a>
										</li>
										<li class="">
											<a href="###"></a>
										</li>
									</ul>
								</div>
            					<ul class="list">
									<#if content3??&&content3?size gt 0>
					           			<#list content3 as content>
					           				<li>
												<i class="list-circle"></i>
												<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">${content.title!""}</a>
											</li>
					           			</#list>
				           			</#if>
								</ul>
		        			</div>
		        		</div>
		        		
		        		<div class="flex-fr">
		        			<a href="${basePath}cms/${siteId}/vxy.htm" class="vxy">
		        				<@contentTag flag="0"  where="siteId=${siteId},channelId=vxy" page="1" pageSize="5"  sysId="credit">
	            					<#if contentList??&&contentList?size gt 0>
										<#list contentList as content >
											<img src="${titleImgGet!""}${content.titleImg!""}">
										</#list>
									</#if>
	            				</@contentTag>
			        			<div class="text-bg">
									<i class="home-img vxy-icon"></i><span class="text">V信用视频</span>
								</div>
		        			</a>
		        		</div>
		        	</div>
		        	<div class="flex-box flex3-3">
		        		<div class="flex-fl">
            				<div class="content-title-outer title-box on">
					        	<i class="title-fl"></i><span class="title-fc"><span class="content-title-fl">信用政策</span>
					        	<a href="${basePath}cms/${siteId}/xyzc.htm" class="content-title-fr">更多</a></span><i class="title-fr"></i>
            				</div>
            				<div class="flex-content">
            					<ul class="list list-have-date">
									<#if content4??&&content4?size gt 0>
					           			<#list content4 as content>
					           				<li>
												<i class="list-circle"></i>
												<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
													<span>${content.title!""}</span>
													<span>[${content.releaseDate?string("yyyy-MM-dd")}]</span>
												</a>
											</li>
					           			</#list>
				           			</#if>
								</ul>
            				</div>
		        		</div>
		        		<div class="flex-fr child-flex xybk-child-flex">
		        			<div class="flex-fl">
								<img src="images/home/xyfw.png">
								<!--<div class="text-bg xyfw">
									<i class="home-img xyfw-icon"></i><span class="text">信用服务</span>
								</div>-->
								<div class="hover-list">
									<div class="transparent-line"></div>
									<div class="text-bg xyfw">
										<i class="home-img xyfw-icon"></i><span class="text">信用服务</span>
									</div>
									<ul>
										<@contentTag flag="0"  where="siteId=${siteId},channelId=fwjg" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
													<li><a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm">${content?index+1}.${content.title!""}</a></li>
												</#list>
											</#if>
										</@contentTag>
									</ul>
								</div>
			        		</div>
			        		<div class="flex-fr">
								<img src="images/home/xyyj.png">
								<!--<div class="text-bg xyyj">
									<i class="home-img xyyj-icon"></i><span class="text">信用研究</span>
								</div>-->
								<div class="hover-list">
									<div class="transparent-line"></div>
									<div class="text-bg xyyj">
										<i class="home-img xyyj-icon"></i><span class="text">信用研究</span>
									</div>
									<ul>
										<@contentTag flag="0"  where="siteId=${siteId},channelId=xyyj" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
													<li><a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm">${content?index+1}.${content.title!""}</a></li>
												</#list>
											</#if>
										</@contentTag>
									</ul>
								</div>
			        		</div>
		        		</div>
		        	</div>
		        </div>
		        <!--联合奖惩-->
		        <div class="tab-content" id='bigTabSwitch4'>
		        	<div class="flex-box btn-group-box">
		        		<@channelTag where="siteId=${siteId},parentId=lhcj" page="1" pageSize="5" sysId="credit">
							<#if channelList??&&channelList?size gt 0>
								<#list channelList as channel>
									<a title = "${channel.channelName}" href="
									<#if channel.hasContent>
										${basePath}cms/${siteId}/${channel.channelId}.htm
									<#else>
										${channel.link}
									</#if>
									"><i class="btn-fl"></i><span class="btn-fc">${channel.channelName}</span><i class="btn-fr"></i></a>
								</#list>
							</#if>
						</@channelTag>
		        	</div>
		        	<div class="flex-box flex2-4 lhjc">
		        		<div class="flex-fl">
		        			<div class="flex-top">
		        				<div class="content-title-outer title-box on">
						        	<i class="title-fl"></i><span class="title-fc"><span class="content-title-fl">政策文件</span><a href="${basePath}cms/${siteId}/zcwj.htm" class="content-title-fr">更多</a></span><i class="title-fr"></i>
	            				</div>
			        			<div class="flex-content clearfix">
			        				<div class="slider-img slider-img2">
										<div class="container">
											<ul class="content">
												<@contentTag flag="0"  where="siteId=${siteId},hasTitleImg=1,channelId=zcwjnews" page="1" pageSize="4" sysId="credit">
														<#if contentList??&&contentList?size gt 0>
														<#list contentList as content >
															<li><a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.titleImg}"><img src="${titleImgGet!""}${content.titleImg!""}"></a></li>
														</#list>
													</#if>
												</@contentTag>
											</ul>
										</div>
										<ul class="banner-circle nav">
											<li class="">
												<a href="###"></a>
											</li>
											<li class="">
												<a href="###"></a>
											</li>
											<li class="">
												<a href="###"></a>
											</li>
											<li class="">
												<a href="###"></a>
											</li>
										</ul>
									</div>
									
									<ul class="list">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=zcwjnews" page="1" pageSize="6"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
													<li>
														<i class="list-circle"></i>
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">${content.title!""}</a>
													</li>
												</#list>
											</#if>
										</@contentTag>
									</ul>
			        			</div>
		        			</div>
		        			<div class="flex-bottom">
		        				<div class="left small-tab-parent">
            				<!--选项卡-->
					        <div id='listNamesTab' class="small-tab-box">
					            <div data-targent='listNamesTab1' class="on"><i class="title-fl"></i><span class="title-fc">黑名单</span><i class="title-fr"></i></div>
					            <div data-targent='listNamesTab2'><i class="title-fl"></i><span class="title-fc">红名单</span><i class="title-fr"></i></div>
					        </div>
					        <!--内容-->
					        <div class="small-tab-content" style="display: block;" id='listNamesTab1'>
					        	<a href="${basePath}publicity/blackList.htm?siteId=${siteId}" class="more-text">更多</a>
					           	<ul class="list">
					           		 <#if blackList??&&blackList?size gt 0>
					           			<#list blackList as black>
					           				<li>
					           					<i class="list-circle"></i>
												<a href="${basePath}publicity/toBlackEnterpriseList.htm?tableName=${black.tableName!""}&blackName=${black.blackName!""}&ownerId=${black.ownerId!""}&siteId=${siteId}" title = "${black.ownerId}:${black.blackName}">${black.ownerId!""}:${black.blackName!""}</a>
											</li>
					           			</#list>
					           		</#if>
								</ul>
					        </div>
					        <!--内容-->
					        <div class="small-tab-content" style="display: none;" id='listNamesTab2'>
					        	<a href="${basePath}publicity/redList.htm?siteId=${siteId}" class="more-text">更多</a>
				            	<ul class="list">
				            		<#if redList??&&redList?size gt 0>
											<#list redList as red >
												<li>
													<i class="list-circle"></i>
													<a href="${basePath}publicity/toRedInfoList.htm?tableName=${red.tableName!""}&redName=${red.tableNameCn!""}&siteId=${siteId}" title = "${red.tableOwnerName}:${red.tableNameCn}">
														${red.tableOwnerName!""}:${red.tableNameCn!""}
													</a>
												</li>
											</#list>
										</#if>
								</ul>
					        </div>
					    </div>
		        				<div class="left">
		        					<div class="content-title-outer title-box on">
							        	<i class="title-fl"></i><span class="title-fc"><span class="content-title-fl">典型案例</span><a href="${basePath}cms/${siteId}/dxal.htm" class="content-title-fr">更多</a></span><i class="title-fr"></i>
		            				</div>
		            				<div class="flex-content">
		            					<ul class="list">
		            						<@contentTag flag="0"  where="siteId=${siteId},channelId=sxjl,channelId=sxcj" page="1" pageSize="6"  sysId="credit">
												<#if contentList??&&contentList?size gt 0>
													<#list contentList as content >
														<li>
															<i class="list-circle"></i>
															<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">${content.title!""}</a>
														</li>
													</#list>
												</#if>
											</@contentTag>	
										</ul>
		            				</div>
		        				</div>
		        			</div>
		        		</div>
		        		<div class="flex-fr">
		        			<div class="small-tab-parent relative">
						        <!--选项卡-->
						        <div class="spec-tab-box clearfix">
						            <div id="internal" class="on">
						            	<i class="home-img internal-icon"></i>
						            </div>
						            <div id="province">
						            	<i class="home-img province-icon"></i>
						            </div>
						            <div id="city">
						            	<i class="home-img city-icon"></i>
						            </div>
						            <p class="tab-line">
						            	 <i class="arrow-top"></i>
						            </p>
						        </div>
						        <!--内容-->
						        <div class="small-tab-content" style="display: block;">
						        	<@contentTag flag="0"  where="siteId=${siteId},channelId=gn1" page="1" pageSize="4"  sysId="credit">
										<#if contentList??&&contentList?size gt 0>
											<#list contentList as content >
												<#if content.hasTitleImg??&&content.hasTitleImg>
													<div class="lhjc-slider-list">
									        			<div class="slider-left list-img-box">
									        				<img src="${titleImgGet!""}${content.titleImg!""}"/>
									        			</div>
												<#else>
													<div class="lhjc-slider-list nothing-img">
												</#if>
												<div class="slider-right">
									        				<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">${content.title!""}</a>
									        				<div class="slider-detail"><p>${content.description!""}</p></div>
									        			</div>
									        		</div>
											</#list>
										</#if>
									</@contentTag>
										
						        </div>
						        <!--内容-->
						        <div class="small-tab-content slider lhjc-slider2">
						        	
						        	<@contentTag flag="0"  where="siteId=${siteId},channelId=bs3" page="1" pageSize="4"  sysId="credit">
										<#if contentList??&&contentList?size gt 0>
											<#list contentList as content >
												<#if content.hasTitleImg??&&content.hasTitleImg>
													<div class="lhjc-slider-list">
									        			<div class="slider-left list-img-box">
									        				<img src="${titleImgGet!""}${content.titleImg!""}"/>
									        			</div>
												<#else>
													<div class="lhjc-slider-list nothing-img">
												</#if>
												<div class="slider-right">
									        				<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">${content.title!""}</a>
									        				<div class="slider-detail"><p>${content.description!""}</p></div>
									        			</div>
									        		</div>
											</#list>
										</#if>
									</@contentTag>
						        </div>
						        
						        <div class="small-tab-content slider lhjc-slider3">
					        		<@contentTag flag="0"  where="siteId=${siteId},channelId=bs4" page="1" pageSize="4"  sysId="credit">
										<#if contentList??&&contentList?size gt 0>
											<#list contentList as content >
												<#if content.hasTitleImg??&&content.hasTitleImg>
													<div class="lhjc-slider-list">
									        			<div class="slider-left list-img-box">
									        				<img src="${titleImgGet!""}${content.titleImg!""}"/>
									        			</div>
												<#else>
													<div class="lhjc-slider-list nothing-img">
												</#if>
												<div class="slider-right">
									        				<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">${content.title!""}</a>
									        				<div class="slider-detail"><p>${content.description!""}</p></div>
									        			</div>
									        		</div>
											</#list>
										</#if>
									</@contentTag>
						        </div>
						    </div>
		        		</div>
		        	</div>
		        </div>
		        <!--办事大厅-->
		        <div class="tab-content" id='bigTabSwitch5'>
		        	<div class="flex-box btn-group-box">
		        	
		        		<@channelTag where="siteId=${siteId},parentId=bsdt" page="1" pageSize="5" sysId="credit">
							<#if channelList??&&channelList?size gt 0>
								<#list channelList as channel>
									<a href="
									<#if channel.hasContent>
										${basePath}cms/${siteId}/${channel.channelId}.htm
									<#else>
										${channel.link}
									</#if>
									"><i class="btn-fl"></i><span class="btn-fc">${channel.channelName}</span><i class="btn-fr"></i></a>
								</#list>
							</#if>
						</@channelTag>
		        	</div>
		        	<div class="flex-box flex2-4">
		        		<div class="flex-fl">
		        			<div class="flex-top search-outer">
	            				<div class="content-title-outer">信用信息查询</div>
	            				<div class="search-inner">
	            					<div class="relation-search enterprise-search">
										<div id="search-bd2" class="search-bd">
											<ul class="clearfix">
												<li  class="selected" data-placeholder="企业信用查询">
													<label>
														<i class="search-tab-left"></i>
														<span class="search-tab-center">企业</span>
														<i class="search-tab-right"></i>
													</label>
													<i class="index-arrow-down"></i>
													<input type="hidden" value="2"/>
												</li>
												<li onClick="
												<#if creditSession.creditType?exists>
													<#if creditSession.creditType=='2'>
														changeUser();
													<#else>
														<#if creditSession.authRange=='3'>
															avascript:window.location.href=encodeURI('${basePath}creditSearch/getPersonDetailInfo.htm');
														<#else>
															 authentication();
														</#if>
													</#if>
												<#else>
													loginGo();
												</#if>" data-placeholder="个人信用查询">
													<label>
														<i class="search-tab-left"></i>
														<span class="search-tab-center">个人</span>
														<i class="search-tab-right"></i>
													</label>
													<i class="index-arrow-down"></i>
													<input type="hidden" value="1"/>
												</li>
												<li data-placeholder="政府机关信用查询">
													<label>
														<i class="search-tab-left"></i>
														<span class="search-tab-center">政府机关</span>
														<i class="search-tab-right"></i>
													</label>
													<i class="index-arrow-down"></i>
													<input type="hidden" value="3"/>
												</li>
												<li data-placeholder="事业单位信用查询">
													<label>
														<i class="search-tab-left"></i>
														<span class="search-tab-center">事业单位</span>
														<i class="search-tab-right"></i>
													</label>
													<i class="index-arrow-down"></i>
													<input type="hidden" value="4"/>
												</li>
												<li data-placeholder="社会组织信用查询">
													<label>
														<i class="search-tab-left"></i>
														<span class="search-tab-center">社会组织</span>
														<i class="search-tab-right"></i>
													</label>
													<i class="index-arrow-down"></i>
													<input type="hidden" value="0"/>
													<i class="index-arrow-down"></i>
												</li>
											</ul>
										</div>
										<div id="search-hd" class="search-hd">
											<input type="text" id="searchInput2" class="search-input">
											<span class="pholder" id="placeholder">企业信用查询</span>
											<a href="###" id="search2" class="btn-search"></a>
										</div>
									</div>
	            					
	            				</div>
		        				
		        			</div>
		        			<div class="flex-bottom">
		        				<div class="left">
		        					<div class="content-title-outer title-box on">
							        	<i class="title-fl"></i><span class="title-fc"><span class="content-title-fl">相关下载</span><a href="${basePath}cms/${siteId}/xgxz.htm" class="content-title-fr">更多</a></span><i class="title-fr"></i>
		            				</div>
		            				<div class="flex-content">
		            					<ul class="list">
		            					
		            						<@contentTag flag="0"  where="siteId=${siteId},channelId=xgxz" page="1" pageSize="6"  sysId="credit">
												<#if contentList??&&contentList?size gt 0>
													<#list contentList as content >
														<li>
															<i class="list-circle"></i>
															<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title= "${content.title}">${content.title!""}</a>
														</li>
													</#list>
												</#if>
											</@contentTag>
										</ul>
		            				</div>
		        				</div>
		        				<div class="left wzly">
		        					<div class="content-title-outer title-box on">
							        	<i class="title-fl"></i><span class="title-fc"><span class="content-title-fl">网站留言</span><a href="${basePath}cms/${siteId}/wzly.htm" class="content-title-fr">更多</a></span><i class="title-fr"></i>
		            				</div>
		            				<div class="flex-content">
		            					<div class="scroll-list" id="scrollList1">
		        							<ul class="list message-list" id="scrollList11">
		        							
		        								<#if message??&&message?size gt 0>
			        								<#list message as messageBoard>
			        									<li>
														<h5>${messageBoard.creditEntityName!""}：</h5>
														<p>
															${messageBoard.content!""}
														</p>
													</li>
			        								</#list>
			        							</#if>
											</ul>
											<ul class="list message-list" id="scrollList12"></ul>
										</div>
		            				</div>
		        					
		        				</div>
		        			</div>
		        		</div>
		        		<div class="flex-fr bsdt">
		        			<div class="flex-top">
		        				<a href="${basePath}cms/${siteId}/zxjd.htm" class="bsdt-link">
		        					<img src="images/home/zxjd.jpg" />
		        				</a>
		        				<a href="${basePath}cms/${siteId}/tssq.htm" class="bsdt-link">
		        					<img src="images/home/tsdh.jpg" />
		        				</a>
		        			</div>
		        			<div class="flex-bottom small-tab-parent">
						        <!--选项卡-->
						        <div id='instFramework' class="small-tab-box">
						            <div data-targent='instFramework1' class="on"><i class="title-fl"></i><span class="title-fc">领导小组</span><i class="title-fr"></i></div>
						            <div data-targent='instFramework2'><i class="title-fl"></i><span class="title-fc">市信用办</span><i class="title-fr"></i></div>
						            <div data-targent='instFramework3'><i class="title-fl"></i><span class="title-fc">区县信用办</span><i class="title-fr"></i></div>
						        </div>
						        <!--内容-->
						        <div class="small-tab-content" style="display: block;" id='instFramework1'>
						        
						        	<@contentTag flag="0"  where="siteId=${siteId},channelId=ldxz" page="1" pageSize="5"  sysId="credit">
										<#if contentList??&&contentList?size gt 0>
											<#list contentList as content >
												<h5 class="look-detail">
								        			<span>${content.title!""}</span>
								        			<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm">[查看详情]</a>
								        		</h5>
												${content.txt!""}
											</#list>
										</#if>
									</@contentTag>
						        </div>
						        <!--内容-->
						        <div class="small-tab-content" id='instFramework2'>
						        	
						        	<@contentTag flag="0"  where="siteId=${siteId},channelId=sxyb" page="1" pageSize="5"  sysId="credit">
									<#if contentList??&&contentList?size gt 0>
										<#list contentList as content >
											<h5 class="look-detail">
							        			<span>${content.title!""}</span>
							        			<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm">[查看详情]</a>
							        		</h5>
											${content.txt!""}
										</#list>
									</#if>
								</@contentTag>
						        </div>
						        <!--内容-->
						        <div class="small-tab-content" id='instFramework3'>
					            		<@contentTag flag="0"  where="siteId=${siteId},channelId=qxxyb" page="1" pageSize="5"  sysId="credit">
												<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
													${content.txt!""}
													<h5 class="look-detail">
									        			<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm">[查看详情]</a>
									        		</h5>
												</#list>
											</#if>
										</@contentTag>
						        </div>
						    </div>
		        		</div>
		        	</div>
		        </div>
		        
		    </div>
			<!--footer-->
			<div class="footer">
				<div class="footer-inner clearfix">
					<div class="left-footer footer-logo">
						<!--党政机关 代码-->
						<script type="text/javascript">document.write(unescape("%3Cspan id='_ideConac' %3E%3C/span%3E%3Cscript src='http://dcs.conac.cn/js/12/188/0000/60417701/CA121880000604177010001.js' type='text/javascript'%3E%3C/script%3E"));</script>
					</div>
					<div class="center-footer">
						<ul>
							<li><a target="_blank" href="${basePath}cms/${siteId}/wzly.htm">网站留言</a></li>
							<li>|</li>
							<li><a target="_blank" href="${basePath}cms/${siteId}/qtwz.htm">相关链接</a></li>
							<li>|</li>
							<li><a target="_blank" href="${basePath}cms/1942/index.htm">信用协会</a></li>
							<li>|</li>
							<li>累计访问：${pageView}次</li>
						</ul>
						<p>瑞安市信用瑞安建设领导小组办公室主办 瑞安市信用信息中心管理</p>
						<p>备案证编号：浙ICP备09070694号-6  联系方式：0577-65817320</p>
					</div>
					<div class="right-footer">
						<div class="code-box code-box2">
							<p><i class="home-img footer-label-left"></i><span class="footer-label-right">信用瑞安<br/>公众号</span></p>
							<img src="images/wechat.jpg" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${basePath}js/jquery-1.11.3/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}js/widget-gh-pages/jquery.slider.js"></script>
		<!--artDialog-->
		<script type="text/javascript" src="${basePath}js/artDialog/dialog-min.js"></script>
		<script type="text/javascript" src="${basePath}js/artDialog/dialog-plus-min.js"></script>
		
		
		<script type="text/javascript" src="${basePath}js/common.js" ></script>
		<script type="text/javascript" src="${basePath}js/home.js"></script>
		<script type="text/javascript">
			$(document).keypress(function(e) {
                var  obj = e.target.id;
                if(e.which == 13) {
                    jQuery("#" + obj).siblings('.btn-search').click();
                }
            });
			
			var value = $("#searchInput").val();
			var value2 = $("#searchInput2").val();
			if(value != "" || value2 != ""){
				$('.pholder').css("display","none");
			};
			$(function(){
				var login = "${login!''}";
				if(login!=""){
					loginGo();
					//$('#loginGo').click();
				}; 
			})
			function loginGo() {
				//个人登录,法人登录
			
				$.GD.dialog({
					id:'loginBox',
			        title: '信用信息服务平台用户服务协议',
					url: '${basePath}protocol.htm',
			        width: '1000px',
			        height: '600px',
			        data: {"buttonId":""},
			    	onclose: function(params) {
			    		if(params) {
			    			var btnText = '';
			    			var type = '';
			        		if(params == 'personLogin'){
								btnText = '个人登录';
								type='1';
							}else if(params == 'companyLogin'){
								btnText = '企业登录';
								type='2';
							};
							$.GD.dialog({
								id:'loginBox2',
						        title: btnText,
								url: '${basePath}login.htm?type='+type,
						        width: '400px',
						        height: '356px',
							});
						}
			        }
				});
			
				
			};
			function changeUser() {
				var url = window.location.href;
				var loginGoHtml = "<div class='login-select-box'>"
								+ "<span class='dialog-img user-img'></span>"
								+ "<div class='btn-row'>"
								+ "<a href='http://puser.zjzwfw.gov.cn/sso/usp.do?action=ssoLogin&servicecode=wzxybfwpt&goto='+url class='w-per40 btn orange-btn'>立即登录</a>"
								+ "</div></div>";
				$.GD.dialog({
			        title: '切换个人账户',
					content: loginGoHtml,
			        width: '520px',
			        height: '259px',
				});
			};
			function authentication() {
				var authHtml = "<div class='login-select-box' style='height:50px;'>"
								+ "<span class='dialog-img authentication-img'></span>"
								+ "<div class='btn-row'>"
								+ "<a href='http://puser.zjzwfw.gov.cn/sso/usp.do?action=idcard' class='btn orange-btn' target='_blank'>立即认证</a>"
								+ "</div></div>";
				$.GD.dialog({
			        title: '请先实名认证',
					content: authHtml,
			        width: '520px',
			        height: '259px',
				});
			};
			$(function(){
				$('#search1').click(function(){
					var path = "${basePath}";
					var queryType = $("#search-bd .clearfix .selected input").val(); 
					if(queryType=='1'){
						return;
					}
					var param = $('#searchInput').val();
					if(param.length<2){
						$.GD.msg("最少输入2个字符 ");
				        return;
					}
					if(param.replace(/(^\s*)|(\s*$)/g, "")==''){
						return;
					}
					if(param.length<2){
						$.GD.msg("最少输入2个字符 ");
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
				
				$('#search2').click(function(){
					var path = "${basePath}";
					var queryType = $("#search-bd2 .clearfix .selected input").val(); 
					if(queryType=='1'){
						return;
					}
					var param = $('#searchInput2').val();
					if(param.length<2){
						$.GD.msg("最少输入2个字符 ");
				        return;
					}
					if(param.replace(/(^\s*)|(\s*$)/g, "")==''){
						return;
					}
					if(param.length<2){
						$.GD.msg("最少输入2个字符 ");
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
				
				$("#cmsSearch").click(function(){
					var value = $(this).prev().val();
					if($.trim(value)!=''){
						window.location.href="${basePath}cms/searchList.htm\?searchText="+value;
					}
				});
				
			});
		
			$(function(){
				//选择后改变placeholder
				var selectedP = $(".search-bd .selected").data('placeholder')
				$("#placeholder").text(selectedP);
				$(".search-bd li").click(function(){
					var $this = $(this);
					$this.addClass('selected').siblings('li').removeClass('selected');
					selectedP = $this.data('placeholder');
					$this.parents('.relation-search').find("#placeholder").text(selectedP);
				});
				//input的placeholder
				if($('.search-hd').length > 0){
					$('search-hd input').each(function(){
						var $this = $(this),val = $(this).val();
						if(val.length > 0){
							$this.siblings('.pholder').hide(0);
						}
					})
				};
				$('.search-hd input').on('input propertychange',function(){
					var $this = $(this),val = $(this).val();
					if(val.length > 0){
						$this.siblings('.pholder').hide(0);
					}else{
						$this.siblings('.pholder').show(0);
					}
				});
				
				//网站留言--跑马灯效果
				var speed = 100 //速度数值越大速度越慢
				scrollList12.innerHTML = scrollList11.innerHTML
	
				function Marquee() {
					if(scrollList12.offsetHeight - scrollList1.scrollTop <= 0)
						scrollList1.scrollTop -= scrollList11.offsetHeight
					else {
						scrollList1.scrollTop++
					}
				}
				var MyMar = setInterval(Marquee, speed);
				scrollList1.onmouseover=function() {clearInterval(MyMar)}
				scrollList1.onmouseout=function() {MyMar=setInterval(Marquee,speed)};
				
				//图片轮播
				$("#newsPictures").newsAnimation();
				//多行文本溢出---轮播图对应list
				$('.banner-list dd').textOverflow();
				
				//多行溢出 联合奖惩
				$(".slider-detail").each(function(i){
                    var divH = $(this).height();
                    var $p = $("p", $(this)).eq(0);
                    while ($p.outerHeight() > divH) {
                        $p.text($p.text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));
                    };
                });
				//联合奖惩
				$(".flex2-4 .banner-list > .list dt:odd").addClass('list-odd');
				//首页广告slider
				$(".slider").slider({inEndEffect:"cycle",steps:1,auto:false,duration:300,delay:5000,hasTriggers:false});
				
				//多行文本溢出
				var a1 = true, a2 = true, a3 = true, a4 = true, a5 = true;
				$('.tab-menu-box > div').click(function(){
                    var $this =$(this),
                        _index = $this.index();
                    $this.addClass('on').siblings('div').removeClass('on');
                    $('.container .tab-content').eq(_index).show().siblings('.tab-content').hide();

                    var $id = $this.attr('data-tabbox');
                    var x = false;
                    if (a1 == true || a2 == true || a3 == true || a4 == true || a5 == true) {
                        switch (_index) {
                            case 1:
                                x = a1;
                                a1 = false;
                                break;
                            case 2:
                                x = a2;
                                a2 = false;
                                break;
                            case 3:
                                x = a3;
                                a3 = false;
                                break;
                            case 4:
                                x = a4;
                                a4 = false;
                                break;
                            case 5:
                                x = a5;
                                a5 = false;
                                break;
                        }
					}
					if ( x ){
					    if($('#' + $id).find('.banner-list dd').length > 0){
                            $('#' + $id + ' .banner-list dd').textOverflow();
                        };
                        if($('#' + $id).find(".slider-img1").length > 0){
                            if($(".slider-img1 .content li").width() != 194){
                                $(".slider-img1").slider({inEndEffect:"cycle",auto:true,activeIndex: 0});
                            }
                        };
                        if($('#' + $id).find(".slider-detail").length > 0){
                            //联合奖惩
                            if($(".slider-img2 .content li").width() != 194){
                                $(".slider-img2").slider({inEndEffect:"cycle",auto:true,activeIndex: 0});
                            }
                            $(".slider-detail").each(function(i){
                                var divH = $(this).height();
                                var $p = $("p", $(this)).eq(0);
                                while ($p.outerHeight() > divH) {
                                    $p.text($p.text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));
                                };
                            });
                        };
                        if($('#' + $id).find(".inst-member p").length > 0){
                            //组织机构
                            $(".inst-member p").textOverflow({
                                height: 28,
                                row: 8
                            });
                        };
                        if($('#' + $id).find(".message-list p").length > 0){
                            //网站留言
                            $(".message-list p").textOverflow({
                                height: 24,
                                row: 2,
                                content:'<span class="ellipsis">... <a href="###">[查看]</a></span>'
                            });
                        };
					}

                })
				
				//"信用百科"下的"信用服务"和"信用研究" 的上移效果
				$(".xybk-child-flex > div").mouseenter(function() {
				 	$(this).find('.hover-list .text-bg').css({
						'height':'74px',
						'lineHeight':'74px',
						'marginBottom':'10px'
					});
				    $(this).find('.hover-list').css({
				    	'zIndex':3,
				    	'-webkit-border-radius': '4px',
				    	'-moz-border-radius': '4px',
				    	'border-radius': '4px'
					}).animate({
						top:'-1px',
					},300).show();
				}).mouseleave(function(){
				  	$(this).find('.hover-list .text-bg').css({
						'height':'66px',
						'lineHeight':'66px',
						'marginBottom':0
					});
				   	$(this).find('.hover-list').css({
				   		'-webkit-border-radius': '0',
				    	'-moz-border-radius': '0',
				    	'border-radius': '0'
				   	}).animate({
						top:'143px',
					},300);
					if($(this).find('.hover-list').css('top') == '143px'){
						$(this).find('.hover-list').hide();
					}
				});
				$('.spec-tab-box > div').click(function () {
					var $this = $(this),
						_index = $this.index();
					$this.addClass('on').siblings().removeClass('on');
                    $this.parent('.spec-tab-box').siblings('.small-tab-content').eq(_index).show();
                    $this.parent('.spec-tab-box').siblings('.small-tab-content').eq(_index).siblings('.small-tab-content').hide();
                    $(".slider-detail").each(function(i){
                        var divH = $(this).height();
                        var $p = $("p", $(this)).eq(0);
                        while ($p.outerHeight() > divH) {
                            $p.text($p.text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));
                        };
                    });
                })
			});
			//register
			function register(){
				//个人注册,法人注册
				var regHtml = "<div class='bubbles-list'>"
							+	"<button onclick='openProtocol(1);'>个人注册</button>"
							+	"<button onclick='openProtocol(2);'>法人注册</button>"
							+	"</div>";
				$.GD.bubbles({
					id: "register", 
					msg: regHtml
				});
			};
			function openProtocol(type) {
				$.GD.dialog({
					id: 'showDialog',
					title: '信用信息服务平台用户服务协议',
			        url: '${basePath}register.htm?type='+type,
			        width:'1000px',
			        height:'600px',
				});
			};
			function companyMyCredit(){
				//退出登录
				var creditHtml = "<div class='bubbles-list'>"
							+	"<a href='myCredit-corporateLogin.html'></span><span>我的信用</span></a>"
							+	"<a href='###'><span>退出登录</span></a>"
							+	"</div>";
				$.GD.bubbles({
					id: "companyMyCredit", 
					msg: creditHtml
				});
			};
			function myCredit_1(){
				var url = '';
				var queryType = '${creditSession.creditType!""}';
				<#if creditSession.creditType?exists>
					<#if creditSession.creditType!='1'>
						var orgCode = '${creditSession.creditCode}';
						var queryName = '${creditSession.creditName}';
					</#if>
				</#if>
				
				if(queryType=='0'||queryType=='2'){
					url =encodeURI( '${basePath}creditSearch/getCompanyDetailInfo.htm?queryType='+queryType+'&orgCode='+orgCode+'&queryName='+queryName);
				}else{
					<#if creditSession.authRange?exists>
						<#if creditSession.authRange=='3'>
							url = encodeURI( '${basePath}creditSearch/getPersonDetailInfo.htm');
						<#else>
							 url='javascript:authentication();';
						</#if>
					</#if>
				}
				
				//我的信用,退出登录
				var creditHtml = "<div class='bubbles-list'><a href='"+url+"'><span>我的信用</span></a>"+
			"<a href='${path}serviceHall/toMyDissent.htm'><span>我的异议</span></a>"+"<a href='javascript:void(0)' onclick='logout()'><span>退出登录</span></a></div>";
			
				$.GD.bubbles({
					id: "myCredit",
					msg: creditHtml
				});
			};
			
			function logout(){
				$.ajax({
					url:'${basePath}logout.json',
					type : "post",
				    dataType: "JSON",
					success: function(data) {
						var url = window.location.href;
						window.location.href = url;
					}
				});
			}
		</script>
		<script type="text/javascript">
			$(function(){
				var pageMsg = "${msg!""}";
				if(pageMsg!=""){
					$.GD.msg(pageMsg);
				}
			});
		</script>
	</body>
</html>