<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>瑞安信用 - ${channelName2}</title>
		<meta name="keywords" content="信用瑞安">
		<meta name="sitename" content="信用瑞安">
		<meta name="description" content="信用瑞安">
		<meta name="title" content="信用瑞安">
		<meta name="title" content="信用瑞安">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link rel="shortcut icon" type="image/x-icon" href="${basePath}images/favicon.ico">
		<link rel="stylesheet" type="text/css" href="${basePath}css/base.css"/>
		<link rel="stylesheet" type="text/css" href="${thirdpartyPath}thirdparty/Swiper-2.7.6/dist/idangerous.swiper.css"/>
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
			<div class="container xfdm-list">
				<div class="content overflow-hidden min-height500">
					<div class="list-top w-per100 clearfix">
						<div class="left list-tab">
							<@con.channel where="siteId=${siteId}" page="1" pageSize="10" channelPath=channelLevel1 curChannelId=channelLevel2 curClass="cur" basePath=basePath sysId=sysId/>
						</div>
						<!--
						<div class="right list-search">
							<form>
								<input type="text" class="search-input" value="${query!''}" placeholder="请输入关键字">
								<span class="btn-search"></span>
							</form>
						</div>
						-->
					</div>
					<div class="policy">
						<div class="clearfix">
							<div class="left pictures-outer">
								<div class="news-pictures item-box" id="newsPictures">
									<ul class="banner-img">
										<@contentTag flag="0"  where="siteId=${siteId},hasTitleImg=1,channelId=zcwjnews" page="1" pageSize="6"  sysId="credit">
												<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
													<li>
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.titleImg}">
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
							<div class="right list-outer">
								<ul class="list list-have-date">
									<@contentTag flag="0"  where="siteId=${siteId},channelId=zcwjnews" page="1" pageSize="5"  sysId="credit">
										<#if contentList??&&contentList?size gt 0>
											<#list contentList as content >
												<li>
													<i class="list-circle"></i>
													<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
														
														<label>${content.title!""}</label>
														<span>[${(content.releaseDate?string("yyyy-MM-dd"))!""}]</span>
													</a>
												</li>
											</#list>
										</#if>
									</@contentTag>
								</ul>
								<p class="t-a-right mg-0auto more-text"><a href="${basePath}cms/${siteId}/zcwjnews.htm" class="color-blue">更多</a></p>
							</div>
						</div>
						<div class="tab-btn-outer relative">
							<div class="fi-arrow-right"></div>
							<div class="fi-arrow-left"></div>
							<div class="swiper-container">
								<ul class="swiper-wrapper">
									<li class="swiper-slide cur">
										<div class="policy-tab zgfy"></div>
										<div class="policy-tab rect"></div>
									</li>
									<li class="swiper-slide">
										<div class="policy-tab sfc"></div>
									</li>
									<li class="swiper-slide">
										<div class="policy-tab gszj"></div>
									</li>
									<li class="swiper-slide">
										<div class="policy-tab swzj"></div>
									</li>


									<li class="swiper-slide">
										<div class="policy-tab spypjgzj"></div>
									</li>
									<li class="swiper-slide">
										<div class="policy-tab hjbhb"></div>
									</li>
									<li class="swiper-slide">
										<div class="policy-tab aqjgzj"></div>
									</li>
									<li class="swiper-slide">
										<div class="policy-tab hgzs"></div>
									</li>


									<li class="swiper-slide">
										<div class="policy-tab zjzj"></div>
									</li>
									<li class="swiper-slide">
										<div class="policy-tab zywxb"></div>
									</li>
									<li class="swiper-slide">
										<div class="policy-tab czb"></div>
									</li>
									<li class="swiper-slide">
										<div class="policy-tab tjj"></div>
									</li>



									<li class="swiper-slide">
										<div class="policy-tab jtys"></div>
									</li>

									<li class="swiper-slide">
										<div class="policy-tab nyb"></div>
									</li>
									<li class="swiper-slide">
										<div class="policy-tab yjh"></div>
									</li>
									<li class="swiper-slide">
										<div class="policy-tab gyhxxhb"></div>
									</li>


									<li class="swiper-slide">
										<div class="policy-tab gqtzy"></div>
									</li>
								</ul>
							</div>
						</div>
						<div class="tab-content-outer">
							<!--最高法院-->
							<div class="tab-content-box">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>最高人民法院</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发改委</li>
												<li>工信部</li>
												<li>国土资源部</li>
												<li>税务总局</li>
												<li>法制办</li>

												<li>人民银行</li>
												<li>中央宣传部</li>
												<li>交通运输部</li>
												<li>工商总局</li>
												<li>公务员局</li>

												<li>中央组织部</li>
												<li>最高检察院</li>
												<li>农业部</li>
												<li>质检总局</li>
												<li>外汇局</li>

												<li>公安部</li>
												<li>民政部</li>
												<li>商务部</li>
												<li>安监总局</li>
												<li>共青团中央</li>

												<li>中央编办</li>
												<li>司法部</li>
												<li>文化部</li>
												<li>林业局</li>
												<li>全国工商联</li>

												<li>中央文明办</li>
												<li>财政部</li>
												<li>卫计委</li>
												<li>食药监管总局</li>
												<li>网信办</li>

												<li>安全部</li>
												<li>人保部</li>
												<li>国资委</li>
												<li>知识产权局</li>
												<li>银监会</li>

												<li>教育部</li>
												<li>环保部</li>
												<li>海关总署</li>
												<li>旅游局</li>
												<li>证监会</li>

												<li>铁路总公司</li>
												<li>住建部</li>
												<li>保监会</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=zgfy" page="1" pageSize="5"  sysId="credit">
										<#if contentList??&&contentList?size gt 0>
											<#list contentList as content >
													<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
														${content.title}
													</a>
											</#list>
										</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--证监会-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>国家证监会</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>工业和信息化部</li>
												<li>交通运输部</li>
												<li>税务总局</li>
												<li>国家网信办</li>

												<li>人民银行</li>
												<li>公安部</li>
												<li>商务部</li>
												<li>工商总局</li>
												<li>银监会</li>

												<li>中央文明办</li>
												<li>财政部</li>
												<li>国资委</li>
												<li>质检总局</li>
												<li>保监会</li>

												<li>最高人民法院</li>
												<li>环境保护部</li>
												<li>海关总署</li>
												<li>食品药品监管总局</li>
												<li>外汇局</li>

												<li>全国总工会</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=zjh" page="1" pageSize="5"  sysId="credit">
										<#if contentList??&&contentList?size gt 0>
											<#list contentList as content >
													<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
														${content.title}
													</a>
											</#list>
										</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--工商总局-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>国家工商总局</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>发改委</li>
												<li>财政部</li>
												<li>商务部</li>
												<li>税务总局</li>
												<li>旅游局</li>

												<li>中央文明办</li>
												<li>环保部</li>
												<li>文化部</li>
												<li>质检总局</li>
												<li>国家网信办</li>

												<li>最高人民法院</li>
												<li>交通运输部</li>
												<li>卫生计生委</li>
												<li>铁路局</li>
												<li>银监会</li>

												<li>教育部</li>
												<li>住房城乡建设部</li>
												<li>人民银行</li>
												<li>新闻出版广电总局</li>
												<li>证监会</li>

												<li>工信部</li>
												<li>水利部</li>
												<li>国资委</li>
												<li>安全监管总局</li>
												<li>保监会</li>

												<li>公安部</li>
												<li>农业部</li>
												<li>林业局</li>
												<li>食品药品监管总局</li>
												<li>邮政局</li>

												<li>国土资源部</li>
												<li>全国总工会</li>
												<li>海关总署</li>
												<li>民航局</li>
												<li>文物局</li>

												<li>司法部</li>
												<li>人力资源社会保障部</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=gszj" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--税务总局-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>税务总局</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>人民银行</li>
												<li>税务总局</li>
												<li>中央文明办</li>
												<li>中央网信办</li>

												<li>最高人民法院</li>
												<li>工业和信息化部</li>
												<li>公安部</li>
												<li>民政部</li>
												<li>财政部</li>

												<li>国土资源部</li>
												<li>环境保护部</li>
												<li>住房城乡建设部</li>
												<li>交通运输部</li>
												<li>水利部</li>

												<li>商务部</li>
												<li>卫生计生委</li>
												<li>海关总署</li>
												<li>工商总局</li>
												<li>食品药品监管总局</li>

												<li>质检总局</li>
												<li>林业局</li>
												<li>旅游局</li>
												<li>国管局</li>
												<li>外汇局</li>

												<li>银监会</li>
												<li>证监会</li>
												<li>保监会</li>
												<li>民航局</li>
												<li>全国总工会</li>

												<li>共青团中央</li>
												<li>全国妇联</li>
												<li>全国工商联</li>
												<li>中国铁路总公司</li>

											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=swzj" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--食品药品监管总局-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>食品药品监管总局</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>食品药品监管总局</li>
												<li>人民银行</li>
												<li>中央宣传部</li>
												<li>中央文明办</li>
												<li>中央网信办</li>
												<li>最高人民法院</li>
												<li>最高人民检察院</li>
												<li>科技部</li>
												<li>工业和信息化部</li>

												<li>司法部</li>
												<li>财政部</li>
												<li>国土资源部</li>
												<li>商务部</li>
												<li>国家卫生计生委</li>
												<li>国资委</li>
												<li>海关总署</li>
												<li>税务总局</li>
												<li>工商总局</li>
												<li>质检总局</li>

												<li>新闻出版广电总局</li>
												<li>银监会</li>
												<li>证监会</li>
												<li>保监会</li>
												<li>全国总工会</li>
												<li>共青团中央</li>
												<li>全国妇联</li>
												<li>全国工商联</li>

											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=spypjgzj" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--环境保护部-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>环境保护部</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>人民银行</li>
												<li>环境保护部</li>
												<li>中央宣传部</li>
												<li>中央统战部</li>
												<li>中央文明办</li>
												<li>工业和信息化部</li>
												<li>公安部</li>
												<li>财政部</li>
												<li>国土资源部</li>

												<li>住房城乡建设部</li>
												<li>交通运输部</li>
												<li>水利部</li>
												<li>农业部</li>
												<li>商务部</li>
												<li>国资委</li>
												<li>海关总署</li>
												<li>税务总局</li>
												<li>工商总局</li>
												<li>质检总局</li>

												<li>安全监管总局</li>
												<li>法制办</li>
												<li>银监会</li>
												<li>证监会</li>
												<li>保监会</li>
												<li>民航局</li>
												<li>全国总工会</li>
												<li>共青团中央</li>
												<li>全国妇联</li>
												<li>全国工商联</li>

												<li>铁路总公司</li>

											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=hjbhj" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--安全监管总局-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>安全监管总局</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>人民银行</li>
												<li>安全监管总局</li>
												<li>中央文明办</li>
												<li>科技部</li>
												<li>财政部</li>
												<li>人力资源社会保障部</li>
												<li>国土资源部</li>
												<li>环境保护部</li>
												<li>住房城乡建设部</li>
												<li>国资委</li>
												<li>海关总署</li>
												<li>税务总局</li>
												<li>工商总局</li>
												<li>质检总局</li>
												<li>银监会</li>
												<li>证监会</li>
												<li>保监会</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=aqjgzj" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--海关总署-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>海关总署</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>人民银行</li>
												<li>海关总署</li>
												<li>中央宣传部</li>
												<li>中央文明办</li>
												<li>教育部</li>
												<li>工业和信息化部</li>
												<li>公安部</li>
												<li>民政部</li>
												<li>财政部</li>

												<li>人力资源社会保障部</li>
												<li>国土资源部</li>
												<li>环境保护部</li>
												<li>住房城乡建设部</li>
												<li>交通运输部</li>
												<li>水利部</li>
												<li>农业部</li>
												<li>商务部</li>
												<li>文化部</li>
												<li>卫生计生委</li>

												<li>国资委</li>
												<li>税务总局</li>
												<li>工商总局</li>
												<li>质检总局</li>
												<li>安全监管总局</li>
												<li>食品药品监管总局</li>
												<li>林业局</li>
												<li>知识产权局</li>
												<li>旅游局</li>
												<li>法制办</li>

												<li>国家网信办</li>
												<li>银监会</li>
												<li>证监会</li>
												<li>保监会</li>
												<li>外汇局</li>
												<li>共青团中央</li>
												<li>全国妇联</li>
												<li>全国总工会</li>
												<li>全国工商联</li>
												<li>贸促会</li>
												<li>中国铁路总公司</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=hgzs" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--质检总局-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>质检总局</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>人民银行</li>
												<li>质检总局</li>
												<li>中央文明办</li>
												<li>中央网信办</li>
												<li>科技部</li>
												<li>财政部</li>
												<li>人力资源社会保障部</li>
												<li>国土资源部</li>
												<li>国资委</li>

												<li>交通运输部</li>
												<li>农业部</li>
												<li>商务部</li>
												<li>卫生计生委</li>
												<li>海关总署</li>
												<li>税务总局</li>
												<li>工商总局</li>
												<li>新闻出版广电总局</li>
												<li>安全监管总局</li>
												<li>食品药品监管总局</li>

												<li>银监会</li>
												<li>证监会</li>
												<li>外汇局</li>
												<li>全国总工会</li>
												<li>共青团中央</li>
												<li>全国妇联</li>

											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=zjzj" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--中央网信办-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>中央网信办</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>人民银行</li>
												<li>中央网信办</li>
												<li>公安部</li>
												<li>交通运输部</li>
												<li>人力资源社会保障部</li>
												<li>商务部</li>
												<li>海关总署</li>
												<li>税务总局</li>
												<li>工商总局</li>

												<li>质检总局</li>
												<li>公务员局</li>
												<li>银监会</li>
												<li>证监会</li>
												<li>阿里巴巴</li>
												<li>腾讯</li>
												<li>京东</li>
												<li>苏宁易购</li>
												<li>58同城</li>
												<li>滴滴快的</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=zywxb" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--财政部-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>财政部</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>人民银行</li>
												<li>财政部</li>
												<li>中央组织部</li>
												<li>中央宣传部</li>
												<li>中央编办</li>
												<li>中央文明办</li>
												<li>中央网信办</li>
												<li>工业和信息化部</li>
												<li>人力资源社会保障部</li>

												<li>国土资源部</li>
												<li>住房城乡建设部</li>
												<li>交通运输部</li>
												<li>水利部</li>
												<li>商务部</li>
												<li>国资委</li>
												<li>海关总署</li>
												<li>税务总局</li>
												<li>工商总局</li>
												<li>质检总局</li>

												<li>安全监管总局</li>
												<li>银监会</li>
												<li>证监会</li>
												<li>保监会</li>
												<li>公务员局</li>
												<li>民航局</li>
												<li>外汇局</li>
												<li>中国铁路总公司</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=cwb" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--统计局-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>统计局</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>人民银行</li>
												<li>统计局</li>
												<li>中央组织部</li>
												<li>中央宣传部</li>
												<li>中央编办</li>
												<li>中央文明办</li>
												<li>科技部</li>
												<li>工信部</li>
												<li>财政部</li>

												<li>人力资源和社会保障部</li>
												<li>国土资源部</li>
												<li>环境保护部</li>
												<li>商务部</li>
												<li>国资委</li>
												<li>海关总署</li>
												<li>税务总局</li>
												<li>工商总局</li>
												<li>质检总局</li>
												<li>银监会</li>

												<li>证监会</li>
												<li>保监会</li>
												<li>公务员局</li>
												<li>外汇管理局</li>
												<li>全国总工会</li>
												<li>共青团中央</li>
												<li>全国妇联</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=tjj" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--交通运输部-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>交通运输部</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>人民银行</li>
												<li>交通运输部</li>
												<li>中央宣传部</li>
												<li>中央编办</li>
												<li>中央文明办</li>
												<li>中央网信办</li>
												<li>最高人民法院</li>
												<li>工业和信息化部</li>
												<li>公安部</li>

												<li>财政部</li>
												<li>人力资源社会保障部</li>
												<li>国土资源部</li>
												<li>环境保护部</li>
												<li>住房城乡建设部</li>
												<li>水利部</li>
												<li>商务部</li>
												<li>文化部</li>
												<li>国资委</li>
												<li>海关总署</li>

												<li>税务总局</li>
												<li>工商总局</li>
												<li>质检总局</li>
												<li>安全监管总局</li>
												<li>食品药品监管总局</li>
												<li>林业局</li>
												<li>旅游局</li>
												<li>法制办</li>
												<li>银监会</li>
												<li>保监会</li>

												<li>外汇局</li>
												<li>全国总工会</li>
												<li>共青团中央</li>
												<li>全国妇联</li>
												<li>全国工商联</li>
												<li>铁路总公司</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=jtysb" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--农业部-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>农业部</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>人民银行</li>
												<li>农业部</li>
												<li>中央文明办</li>
												<li>中央网信办</li>
												<li>最高人民法院</li>
												<li>最高人民检察院</li>
												<li>科技部</li>
												<li>工业和信息化部</li>
												<li>司法部</li>

												<li>财政部</li>
												<li>人力资源社会保障部</li>
												<li>国土资源部</li>
												<li>商务部</li>
												<li>卫生计生委</li>
												<li>国资委</li>
												<li>海关总署</li>
												<li>税务总局</li>
												<li>工商总局</li>
												<li>质检总局</li>

												<li>新闻出版广电总局</li>
												<li>银监会</li>
												<li>证监会</li>
												<li>供销总社</li>
												<li>外汇局</li>
												<li>全国总工会</li>
												<li>共青团中央</li>
												<li>全国妇联</li>
												<li>全国工商联</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=nyb" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--银监会-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>银监会</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>人民银行</li>
												<li>银监会</li>
												<li>证监会</li>
												<li>保监会</li>
												<li>最高人民法院</li>
												<li>中央宣传部</li>
												<li>中央编办</li>
												<li>中央文明办</li>
												<li>中央网信办</li>

												<li>工业和信息化部</li>
												<li>财政部</li>
												<li>人力资源和社会保障部</li>
												<li>商务部</li>
												<li>国资委</li>
												<li>海关总署</li>
												<li>税务总局</li>
												<li>工商总局</li>
												<li>质检总局</li>
												<li>公务员局</li>

												<li>外汇局</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=yjh" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--工业和信息化部-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>工业和信息化部</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>人民银行</li>
												<li>工业和信息化部</li>
												<li>中央文明办</li>
												<li>科技部</li>
												<li>财政部</li>
												<li>人力资源社会保障部</li>
												<li>国土资源部</li>
												<li>税务总局</li>
												<li>工商总局</li>

												<li>质检总局</li>
												<li>银监会</li>
												<li>证监会</li>
												<li>保监会</li>
												<li>全国总工会</li>
												<li>共青团中央</li>
												<li>全国妇联</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=gyhxxhb" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
							<!--共青团中央-->
							<div class="tab-content-box" style="display: none;">
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon1"></i>
										<label>专项领域</label>
									</div>
									<div class="box-content">
										<div>
											<label>发起部门：</label>
											<ul class="content-list">
												<li>共青团中央</li>
											</ul>
										</div>
										<div>
											<label>配合部门：</label>
											<ul class="content-list clearfix">
												<li>国家发展改革委</li>
												<li>人民银行</li>
												<li>共青团中央</li>
												<li>中央宣传部</li>
												<li>中央政法委</li>
												<li>中央编办</li>
												<li>中央文明办</li>
												<li>中央网信办</li>
												<li>最高人民法院</li>
												<li>最高人民检察院</li>
												<li>教育部</li>
												<li>工业和信息化部</li>
												<li>公安部</li>
												<li>安全部</li>
												<li>民政部</li>
												<li>司法部</li>
												<li>财政部</li>
												<li>人力资源和社会保障部</li>
												<li>环境保护部</li>
												<li>住房城乡建设部</li>
												<li>交通运输部</li>
												<li>农业部</li>
												<li>商务部</li>
												<li>文化部</li>
												<li>卫生计生委</li>
												<li>国资委</li>
												<li>海关总署</li>
												<li>税务总局</li>
												<li>工商总局</li>
												<li>质检总局</li>
												<li>新闻出版广电总局</li>
												<li>体育总局</li>
												<li>安全监管总局</li>
												<li>食品药品监管总局</li>
												<li>知识产权局</li>
												<li>旅游局</li>
												<li>法制办</li>
												<li>银监会</li>
												<li>证监会</li>
												<li>保监会</li>
												<li>公务员局</li>
												<li>民航局</li>
												<li>文物局</li>
												<li>外汇局</li>
												<li>全国总工会</li>
												<li>全国妇联</li>
												<li>中国科协</li>
												<li>中国侨联</li>
												<li>全国工商联</li>
												<li>中国残联</li>
												<li>铁路总公司</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="tab-content-inner">
									<div class="box-title">
										<i class="box-icon box-icon2"></i>
										<label>联合备忘录</label>
									</div>
									<div class="box-content">
										<@contentTag flag="0"  where="siteId=${siteId},channelId=gqtzy" page="1" pageSize="5"  sysId="credit">
											<#if contentList??&&contentList?size gt 0>
												<#list contentList as content >
														<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
															${content.title}
														</a>
												</#list>
											</#if>
										</@contentTag>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
			<#include "../common/bottom.ftl" />
		</div>
		<script type="text/javascript" src="${thirdpartyPath}thirdparty/Swiper-2.7.6/dist/idangerous.swiper.min.js"></script>
		<script type="text/javascript">
            $(function () {
                //轮播图
                $("#newsPictures").newsAnimation();
                //tab
                 $(".tab-btn-outer li").click(function(){
                    var index = $(this).index();
                    $(this).addClass('cur').siblings('li').removeClass('cur');
                    $(".tab-content-outer .tab-content-box").eq(index).show();
                    $(".tab-content-outer .tab-content-box").eq(index).siblings('.tab-content-box').hide();
                    $('.rect').appendTo($(this));
                });

                var swiper = new Swiper('.swiper-container', {
                    slidesPerView: 4, //(number 或 'auto')设置slider容器能够同时显示的slides数量,默认1
                    mousewheelControl: true,
                    resizeReInit: true,

                });
                $('.fi-arrow-left').on('click', function(e){
                    e.preventDefault();
                    swiper.swipePrev();
                });
                $('.fi-arrow-right').on('click', function(e){
                    e.preventDefault();
                    swiper.swipeNext();
                });
            });
		</script>
	</body>
</html>