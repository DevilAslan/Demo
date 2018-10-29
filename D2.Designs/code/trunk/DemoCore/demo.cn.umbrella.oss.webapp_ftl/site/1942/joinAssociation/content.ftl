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
			
			<!--nav-->
			<div class="box nav_bg">
				<div class="share_center">
			    	<ul class="nav">
			        	<@channelTag where="siteId=${siteId},parentId=creditAssociation,exclusive=searchResult" page="1" pageSize="5" sysId="${sysId}">
							<#if channelList??&&channelList?size gt 0>
								<#list channelList as channel>
									<li <#if channelLevel2??&&channelLevel2==channel.channelId> class="nav_on" </#if>>
									<a href="<#if channel.hasContent>
											${basePath}cms/${siteId}/${channel.channelId}.htm 
											<#else> 
											${channel.link} 
											</#if>" >
									<i class="${channel.channelClass!""}"></i>
									${channel.channelName}
									</a>
								</#list>
							</#if>
						</@channelTag>
			        </ul>
			    </div>
			</div>
			<!--nav结束-->
			<div class="banner02">
				<img src="${basePath}images/creditAssociation/img/banner02.png" /> 
			</div>
			<div class="box m_20">
				<div class="share_center">
			    	<h3 class="cont_tit">
			    	<i><img src="${basePath}images/creditAssociation/img/cont.png" /></i>
			    	<span>：</span>
			    	<a href="${basePath}cms/${siteId}/${channelLevel2}.htm">${channelName2!""} </a>
			    	<span>></span>
			    	<a href="${basePath}cms/${siteId}/${channelLevel2}.htm">${channelName3!""} </a>
			    	</h3>
			        <div class="cont_box">
			        	<ul class="cont_ul fl">
			                <div class="left list-tab">
								<li class="li_orange">
			                		<a href="${basePath}cms/${siteId}/${channelLevel2}.htm">${channelName2}</a>
								</li>
								<@con.channel_orange where="siteId=${siteId}" channelPath=channelLevel2 curChannelId=channelLevel3 curClass="li_min" basePath=basePath sysId=sysId/>
							</div>
			            </ul>
			            <div class="cont_txt fr">
			            	<h3 class="text_tit">${content.title!""}</h3>
			                <div class="text_tit_fllow">
			                <span>来源：${content.origin!""}</span>
			                <span class="time">
			                	<#if content.releaseDate??>
									${content.releaseDate?string("yyyy-MM-dd")}
								</#if>
			                </span>
			                <span class="img_10">${content.viewsCount!}</span>
			                </div>
			                <div>
									${content.txt!}
							</div>
			            </div>
			        </div>
			    </div>
			</div>

<!--底部-->
<#include "../common/bottom.ftl" />
	</body>
</html>
