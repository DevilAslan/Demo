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
			
			<div class="box m20 btm_bg02 pad_btm100">
				<div class="share_center">
			    	<h3 class="list_title">
			    	<i><img src="${basePath}images/creditAssociation/img/home.png" /></i>
			    	<a href="${basePath}cms/${siteId}/${channelLevel2}.htm">${channelName2!""} </a>
			    	<span>></span>
			    	<a href="${basePath}cms/${siteId}/${channelLevel2}.htm">${channelName3!""} </a>
			    	</h3>
			   	  <div class="list_box m30">
			        	<div class="menu_left">
			            	<h3>
			            	<div><i><img src="${basePath}images/creditAssociation/img/dongtai.png" /></i><span>${channelName2}</span></div>
			            	</h3>
			                <ul class="menu_ul">
												<@con.channel_orange where="siteId=${siteId}" channelPath=channelLevel2 curChannelId=channelLevel3 curClass="a_on" basePath=basePath sysId=sysId/>
			                </ul>
			          	</div>
			            <div class="menu_right">
			            	<h3>${content.title!""}</h3>
			                <div class="txt_tip"><span>来源：${content.origin!""}</span>
			                <span>
			                	<#if content.releaseDate??>
													发布时间：${content.releaseDate?string("yyyy-MM-dd")}
												</#if>
			                </span>
			                <span>浏览量：${content.viewsCount!"0"}</span></div>
			              <div class="txt_box m20">
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
