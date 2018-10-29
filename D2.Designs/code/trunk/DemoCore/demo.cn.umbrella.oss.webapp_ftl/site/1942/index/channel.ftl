<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="renderer" content="webkit">
		<title>瑞安信用协会</title>
		<meta name="keywords" content=" 瑞安信用协会网">
		<meta name="sitename" content="瑞安信用协会网">
		<meta name="description" content=" 瑞安信用协会网">
		<meta name="title" content="瑞安信用协会网">
<link rel="shortcut icon" type="image/x-icon" href="${basePath}images/creditAssociation.ico">
<link href="${basePath}css/creditAssociation/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${basePath}js/creditAssociation/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${basePath}js/creditAssociation/placeholder.js"></script>
<script type="text/javascript" src="${basePath}js/creditAssociation/jquery.event.drag-1.5.min.js"></script>
<script type="text/javascript" src="${basePath}js/creditAssociation/jquery.touchSlider.js"></script>
<script type="text/javascript" src="${basePath}js/creditAssociation/imgScroll.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	var length_li = $('.main_image ul li').length;
	var str = '';
	for(var i = 0; i <length_li ;i++ ){
		str +='<a href="#"></a>';
	}
    $(".flicking_con").empty().append(str);
	
	$(".banner").hover(function(){
		$("#btn_prev,#btn_next").fadeIn()
	},function(){
		$("#btn_prev,#btn_next").fadeOut()
	});
	
	$dragBln = false;
	
	$(".main_image").touchSlider({
		flexible : true,
		speed : 200,
		btn_prev : $("#btn_prev"),
		btn_next : $("#btn_next"),
		paging : $(".flicking_con a"),
		counter : function (e){
			$(".flicking_con a").removeClass("on").eq(e.current-1).addClass("on");
		}
	});
	
	$(".main_image").bind("mousedown", function() {
		$dragBln = false;
	});
	
	$(".main_image").bind("dragstart", function() {
		$dragBln = true;
	});
	
	$(".main_image a").click(function(){
		if($dragBln) {
			return false;
		}
	});
	
	timer = setInterval(function(){
		$("#btn_next").click();
	}, 5000);
	
	$(".banner").hover(function(){
		clearInterval(timer);
	},function(){
		timer = setInterval(function(){
			$("#btn_next").click();
		},5000);
	});
	
	$(".main_image").bind("touchstart",function(){
		clearInterval(timer);
	}).bind("touchend", function(){
		timer = setInterval(function(){
			$("#btn_next").click();
		}, 5000);
	});
	
});
</script>
<script type="text/javascript">
	$(document).ready(function(e) {
        $('.ul_thing:first').show();
		$('.box_txt_ul li:first').addClass('li_click');
		$('.box_txt_ul li').click(function(){
			$(this).addClass('li_click').siblings().removeClass('li_click');
			$('.ul_thing').hide();
		   	var index = $(this).index();
		   	$('.ul_thing:eq('+index+')').fadeIn();
		});
    });
</script>
</head>

<body>
<!-- top-->
<#include "../common/top.ftl" />
<!--top结束-->

<div class="box m30">
	<div class="share_center">
        <div class="banner">
            <div class="flicking_con"></div>
            <div class="main_image">
                <ul>
                <@contentTag  where="siteId=${siteId},isRecommend=1,hasTitleImg=1,channelId=policyDynamic" page="1" pageSize="5"  sysId="credit">
										<#if contentList??&&contentList?size gt 0>
										<#list contentList as content >
											<li>
												<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
													<img src="${titleImgGet!""}${content.titleImg!""}">
												</a>
												 <div class="banner_txt"><span>${content.description!""}</span></div>
											</li>
										</#list>
									</#if>
								</@contentTag>
       
                </ul>
                <a href="javascript:;" id="btn_prev"></a>
                <a href="javascript:;" id="btn_next"></a>
            </div>
        </div>
        <div class="box_txt">
        	<div class="ul_div">
                <ul class="box_txt_ul">
                    <li class="li_click">政策动态</li>
                    <li>行业资讯</li>
                </ul>
                <a href="${basePath}cms/${siteId}/associationDynamic.htm" class="more_a"></a>
            </div>
            <ul class="ul_txt ul_thing">
                <@contentTag  where="siteId=${siteId},channelId=policyDynamic" page="1" pageSize="5"  sysId="credit">
								<#if contentList??&&contentList?size gt 0>
									<#list contentList as content >
											<li>
											<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
											<span title="${content.title!""}">${content.title!""}</span>
											<em>${content.releaseDate?string("yyyy/MM/dd")}</em>
											</a>
											<#if content_index==0>
											<p class="p_h"><a>${content.description!""}</a></p>
											</#if>
											</li>
									</#list>
								</#if>
							</@contentTag>
            </ul>
            <ul class="ul_txt ul_thing">
                <@contentTag  where="siteId=${siteId},channelId=industryInfo" page="1" pageSize="5"  sysId="credit">
								<#if contentList??&&contentList?size gt 0>
									<#list contentList as content >
											<li>
											<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
											<span title="${content.title!""}">${content.title!""}</span>
											<em>${content.releaseDate?string("yyyy/MM/dd")}</em>
											</a>
											<#if content_index==0>
											<p class="p_h"><a>${content.description!""}</a></p>
											</#if>
											</li>
									</#list>
								</#if>
							</@contentTag>
            </ul>
        </div>
    </div>
</div>

<div class="box m30">
	<div class="share_center">
    	<div class="honor">
        	<ul class="honor_ul">
            	<li>
                	<a href="${basePath}cms/${siteId}/associationSurvey.htm"><img src="${basePath}images/creditAssociation/img/case01.png" /></a>
                </li>
              <li>
                	<a href="${basePath}cms/${siteId}/associationLeader.htm"><img src="${basePath}images/creditAssociation/img/case02.png" /></a>
                </li>
              <li>
                	<a href="${basePath}cms/${siteId}/associationArticles.htm"><img src="${basePath}images/creditAssociation/img/case03.png" /></a>
                </li>
              <li class="no_margin">
                	<a href="${basePath}cms/${siteId}/org.htm"><img src="${basePath}images/creditAssociation/img/case04.png" /></a>
                </li>
            </ul>
            <h3 class="honor_h3">
            <i><img src="${basePath}images/creditAssociation/img/honor.png" /></i><span>信用荣誉</span>
            <a href="${basePath}cms/${siteId}/creditHonor.htm" class="more_a"></a>
            </h3>
            <div class="ad-padding">
                <div class="ad-outer">
                    <ul>
                    		
                    		<@contentTag  where="siteId=${siteId},isRecommend=1,hasTitleImg=1,channelId=creditHonor" page="1" pageSize="5"  sysId="credit">
													<#if contentList??&&contentList?size gt 0>
													<#list contentList as content >
														<li>
															<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" alt="${content.title}" title = "${content.title}">
																<img src="${titleImgGet!""}${content.titleImg!""}">
															</a>
														</li>
													</#list>
												</#if>
											</@contentTag>
                    </ul>
                </div>
            </div>
        </div>
        <div class="member">
        	<h3 class="member_h3"><span>会员动态</span><a href="${basePath}cms/${siteId}/memberDynamic.htm" class="more_a"></a></h3>
            <ul class="ul_txt member_ul_txt">
                <@contentTag  where="siteId=${siteId},channelId=memberDynamic" page="1" pageSize="5"  sysId="credit">
									<#if contentList??&&contentList?size gt 0>
										<#list contentList as content >
												<li>
												<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
												<span>${content.title!""}</span>
												</a>
												</li>
										</#list>
									</#if>
								</@contentTag>
            </ul>
        </div>
    </div>
</div>
<div class="box m30"><div class="share_center"><img src="${basePath}images/creditAssociation/img/guodu.png" /></div></div>
<div class="box m30">
	<div class="share_center">
    	<div class="notice">
        	<h3 class="notice_h3"><i><img src="${basePath}images/creditAssociation/img/notice.png" /></i><span>通知公告</span><a href="${basePath}cms/${siteId}/notice.htm" class="more_a"></a></h3>
            <ul class="ul_txt">
            	
                <@contentTag  where="siteId=${siteId},channelId=notice" page="1" pageSize="5"  sysId="credit">
								<#if contentList??&&contentList?size gt 0>
									<#list contentList as content >
											<#if content_index==0>											
											<li>
											
											<dl>
											<#if content.hasTitleImg??&&content.hasTitleImg>
											<dt>
												<img src="${titleImgGet!""}${content.titleImg!""}" />
											</dt>
											<dd>
											<a target="_blank" class="a_nobg" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
											<span title="${content.title!""}">${content.title!""}</span>
											<em>${content.releaseDate?string("yyyy/MM/dd")}</em></a>
											<p class="p_h"><a>${content.description!""}</a></p>
											</dd>
											<#else>
											<dd style="width:100%;margin-left:0;">
											<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
											<span title="${content.title!""}">${content.title!""}</span>
											<em>${content.releaseDate?string("yyyy/MM/dd")}</em></a>
											<p class="p_h"><a>${content.description!""}</a></p>
											</dd>
											</#if>
											</dl>
											
											</li>
											
											<#else>
											
											<li>
											<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
											<span title="${content.title!""}">${content.title!""}</span>
											<em>${content.releaseDate?string("yyyy/MM/dd")}</em>
											</a>
											</li>
											
											</#if>
											
									</#list>
								</#if>
							</@contentTag>
            </ul>
        </div>
        <div class="notice active">
        	<h3 class="notice_h3"><i><img src="${basePath}images/creditAssociation/img/active.png" /></i><span>协会活动</span><a href="${basePath}cms/${siteId}/associationActivity.htm" class="more_a"></a></h3>
            <ul class="ul_txt">
            	<@contentTag  where="siteId=${siteId},channelId=associationActivity" page="1" pageSize="5"  sysId="credit">
								<#if contentList??&&contentList?size gt 0>
									<#list contentList as content >
											<#if content_index==0>											
											<li>
											
											<dl>
											<#if content.hasTitleImg??&&content.hasTitleImg>
											<dt>
												<img src="${titleImgGet!""}${content.titleImg!""}" />
											</dt>
											<dd>
											<a target="_blank" class="a_nobg" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
											<span title="${content.title!""}">${content.title!""}</span>
											<em>${content.releaseDate?string("yyyy/MM/dd")}</em></a>
											<p class="p_h"><a>${content.description!""}</a></p>
											</dd>
											<#else>
											<dd style="width:100%;margin-left:0;">
											<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
											<span title="${content.title!""}">${content.title!""}</span>
											<em>${content.releaseDate?string("yyyy/MM/dd")}</em></a>
											<p class="p_h"><a>${content.description!""}</a></p>
											</dd>
											</#if>
											</dl>
											
											</li>
											
											<#else>
											
											<li>
											<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
											<span title="${content.title!""}">${content.title!""}</span>
											<em>${content.releaseDate?string("yyyy/MM/dd")}</em>
											</a>
											</li>
											
											</#if>
											
									</#list>
								</#if>
							</@contentTag>
            </ul>
        </div>
    </div>
</div>
<div class="box pad_top30 pad_btm30 btm_bg">
	<div class="share_center">
    	<div class="notice">
        	<h3 class="notice_h3"><i><img src="${basePath}images/creditAssociation/img/jieshao.png" /></i><span>会员介绍</span><a href="${basePath}cms/${siteId}/memberIntro.htm" class="more_a"></a></h3>
            <ul class="jieshao_ul">
            	<li><a href="${basePath}cms/${siteId}/viceStandingPresiden.htm">常务副会长</a></li>
                <li class="fr"><a href="${basePath}cms/${siteId}/leaderUnit.htm">副会长单位</a></li>
                <li class="m60"><a href="${basePath}cms/${siteId}/directorMember.htm">理事会员</a></li>
                <li class="fr m60"><a href="${basePath}cms/${siteId}/usualMember.htm">普通会员</a></li>
            </ul>
        </div>
        <div class="notice active">
        	<h3 class="notice_h3"><i><img src="${basePath}images/creditAssociation/img/xiehui.png" /></i><span>信用四项建设情况</span><a href="${basePath}cms/${siteId}/creditBuild.htm" class="more_a"></a></h3>
            <ul class="ul_txt">
            	<@contentTag  where="siteId=${siteId},channelId=creditBuild" page="1" pageSize="5"  sysId="credit">
								<#if contentList??&&contentList?size gt 0>
									<#list contentList as content >
											<#if content_index==0>											
											<li>
											
											<dl>
											<#if content.hasTitleImg??&&content.hasTitleImg>
											<dt>
												<img src="${titleImgGet!""}${content.titleImg!""}" />
											</dt>
											<dd>
											<a target="_blank" class="a_nobg" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
											<span title="${content.title!""}">${content.title!""}</span>
											<em>${content.releaseDate?string("yyyy/MM/dd")}</em></a>
											<p class="p_h"><a>${content.description!""}</a></p>
											</dd>
											<#else>
											<dd style="width:100%;margin-left:0;">
											<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
											<span title="${content.title!""}">${content.title!""}</span>
											<em>${content.releaseDate?string("yyyy/MM/dd")}</em></a>
											<p class="p_h"><a>${content.description!""}</a></p>
											</dd>
											</#if>
											</dl>
											
											</li>
											
											<#else>
											
											<li>
											<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title = "${content.title}">
											<span title="${content.title!""}">${content.title!""}</span>
											<em>${content.releaseDate?string("yyyy/MM/dd")}</em>
											</a>
											</li>
											
											</#if>
											
									</#list>
								</#if>
							</@contentTag>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">    
$(function () {    
	$(".p_h").each(function (i) {    
		var divH = $(this).height();    
		var $a = $("a", $(this)).eq(0);    
		while ($a.outerHeight() > divH) {    
			$a.text($a.text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));    
		};    
	});    
});    
</script>  
<script>
    $(".ad-outer").imgscroll({
        speed: 40,    //图片滚动速度
        amount: 0,    //图片滚动过渡时间
        width: 1,     //图片滚动步数
        dir: "left"   // "left" 或 "up" 向左或向上滚动
    });
	$(".member_ul_txt").imgscroll({
        speed: 40,    //图片滚动速度
        amount: 0,    //图片滚动过渡时间
        width: 1,     //图片滚动步数
        dir: "up"   // "left" 或 "up" 向左或向上滚动
    });
</script>
<!--底部-->
<#include "../common/bottom.ftl" />

</body>
</html>