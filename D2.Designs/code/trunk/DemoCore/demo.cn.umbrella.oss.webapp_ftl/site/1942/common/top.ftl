<link rel="shortcut icon" type="image/x-icon" href="${basePath}images/creditAssociation.ico">
<link href="${basePath}css/creditAssociation/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${basePath}js/creditAssociation/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${basePath}js/creditAssociation/placeholder.js"></script>
<script type="text/javascript" src="${basePath}js/creditAssociation/jquery.event.drag-1.5.min.js"></script>
<script type="text/javascript" src="${basePath}js/creditAssociation/jquery.touchSlider.js"></script>
<script type="text/javascript" src="${basePath}js/creditAssociation/imgScroll.js"></script>
<!-- top-->
<div class="header header_bg1">
  <div class="header_c">
    	 <div class="head">
    		<a href="${basePath}cms/${siteId}/index.htm"><img src="${basePath}images/creditAssociation/img/logo.png" /></a>
        <div class="search">
        	<input type="text" placeholder="请输入关键字" class="serth_input fl" />
            <a class="search_btn"><img src="${basePath}images/creditAssociation/img/scear_btn.png" /></a>
        </div>
       </div>
        <!--nav-->
        <ul class="nav">
				<@channelTag where="siteId=${siteId},parentId=creditAssociation,exclusive=searchResult" page="1" pageSize="5" sysId="${sysId}">
					<#if channelList??&&channelList?size gt 0>
						<#list channelList as channel>
							<li <#if channelLevel2??&&channelLevel2==channel.channelId> class="li_on" </#if>>
								<a href="<#if channel.hasContent> ${basePath}cms/${siteId}/${channel.channelId}.htm 
							<#else> 
									${channel.link} 
							</#if>" >
							
							<i class="${channel.channelClass!""}"></i>
							${channel.channelName}
							</a>
							
							
							
							<ul class="nav_list">
								<@channelTag where="siteId=${siteId},parentId=${channel.channelId},exclusive=searchResult" page="1" pageSize="5" sysId="${sysId}">
										<#if channelList??&&channelList?size gt 0>
											<#list channelList as channelChild>
												<li>
													<a href="<#if channelChild.hasContent> ${basePath}cms/${siteId}/${channelChild.channelId}.htm 
												<#else>
														${channel.link} 
												</#if>" >
												${channelChild.channelName}
												</a>
											</#list>
									</#if>
								</@channelTag>
							</ul>
							
							</li>
						</#list>
					</#if>
				</@channelTag>
			</ul>
			
			<!--nav结束-->
  </div>
  
</div>





<script type="text/javascript">
$(document).keydown(function(event){ 
if(event.keyCode==13){ 
$(".search_btn").click(); 
} 
}); 
$(".search_btn").click(function(){
	var value = $(this).prev().val();
	if($.trim(value)!=''){
		window.location.href="${basePath}cms/creditAssociation/searchList.htm?searchText="+value;
	}
});
</script>
<!--top结束-->