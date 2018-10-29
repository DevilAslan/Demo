<#macro contents where channelPath page pageSize basePath imgPath sysId  query="" exclusive="" flag="1">
	<#if channelPath??&&page??&&pageSize??>
		<@contentTag  where="siteId=${siteId},channelId=${channelPath}" page="${page}" pageSize="${pageSize}"  sysId="${sysId}" exclusive="${exclusive}" query="${query}" flag="${flag}">
				<#if contentList??&&contentList?size gt 0>
				<#list contentList as content >
					<li>
						<#if content.hasTitleImg??&&content.hasTitleImg>
							<div class="left img">
									<img  src="${titleImgGet!""}${content.titleImg!""}"/>
							</div>
							<div class="right text">
								<#if content.link??>
									<#if content.link == "">
										<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" class="font-size18">
											${content.title!""}
										</a>
									<#else>	
										<a target="_blank" href="${content.link}">${content.title!""}</a>
									</#if>
								<#else>
								<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" class="font-size18">
									${content.title!""}
								</a>
								</#if>
								<div class="dynamic-txt"><p class="font-size14">${content.description!""}</p></div>
								<div class="overflow-hidden">
									<div class="right">
										<div>
											<i class="content-icon clock-icon"></i>
											<#if content.releaseDate??>
												<span>${content.releaseDate?string("yyyy-MM-dd")}</span>
											</#if>
										</div>
										<div>
											<i class="content-icon preview-icon"></i>
											<span>${content.viewsCount!0}</span>
										</div>
									</div>
								</div>
							</div>
						<#else>
							<div class="right text w-per100">
								<#if content.link??>
									<#if content.link == "">
										<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" class="font-size18">${content.title!""}</a>
									<#else>	
										<a target="_blank" href="${content.link}">${content.title!""}</a>
									</#if>
								<#else> 
								<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" class="font-size18">${content.title!""}</a>
								</#if>
								<div class="dynamic-txt"><p class="font-size14">${content.description!""}</p></div>
								<div class="overflow-hidden">
									<div class="right">
										<div>
											<i class="content-icon clock-icon"></i>
											<#if content.releaseDate??>
												<span>${content.releaseDate?string("yyyy-MM-dd")}</span>
											</#if>
										</div>
										<div>
											<i class="content-icon preview-icon"></i>
											<span>${content.viewsCount!0}</span>
										</div>
									</div>
								</div>
							</div>
						</#if>
					</li>
				</#list>
			</#if>
		</@contentTag>
	</#if>
</#macro>

<#macro contents_table where channelPath page pageSize basePath imgPath sysId query="" exclusive="" flag="00">
	<#if channelPath??&&page??&&pageSize??>
		<@contentTag  where="siteId=${siteId},channelId=${channelPath}" page="${page}" pageSize="${pageSize}"  sysId="${sysId}" exclusive="${exclusive}" query="${query}" flag="${flag}">
				<#if contentList??&&contentList?size gt 0>
				<#list contentList as content >
					<tr>
						<td><i class="content-icon gray-circle"></i></td>
						<td class="line">
							<#if content.link??>
								<#if content.link!=''>
								<a target="_blank" href="${content.link}" title="${content.title!""}">${content.title!""}</a>
								<#else> 
								<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm">${content.title!""}</a>
								</#if>
							<#else> 
							<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm">${content.title!""}</a>
							</#if>
						</td>
						<td class="t-a-center">${content.releaseDate?string("yyyy/MM/dd")}</td>
					</tr>
				</#list>
			</#if>
		</@contentTag>
	</#if>
</#macro>

<#macro channel where page pageSize channelPath curChannelId curClass basePath sysId>
	<#if channelPath??>
		<@channelTag where="siteId=${siteId},parentId=${channelPath}" page="${page}" pageSize="${pageSize}" sysId="${sysId}">
			<#if channelList??&&channelList?size gt 0>
					<#list channelList as channel >
						<a href=<#if channel.hasContent>
									"${basePath}cms/${siteId}/${channel.channelId}.htm"
								<#else>
									"${channel.link}"
								</#if> 
						<#if curChannelId??&&curChannelId==channel.channelId>
							class="${curClass}"
						</#if>><i class="second-nav-fl"></i><span class="second-nav-fc">
							${channel.channelName}
						</span><i class="second-nav-fr"></i></a>
					</#list>
				</#if>
		</@channelTag>
	</#if>
</#macro>

<#macro channel_orange where channelPath curChannelId curClass basePath sysId>
	<#if channelPath??>
		<@channelTag where="siteId=${siteId},parentId=${channelPath}"  sysId="${sysId}">
			<#if channelList??&&channelList?size gt 0>
					<#list channelList as channel >
						<li>
						<a <#if curChannelId??&&curChannelId==channel.channelId>
							class="${curClass}"
						</#if> href=<#if channel.hasContent>
									"${basePath}cms/${siteId}/${channel.channelId}.htm"
								<#else>
									"${channel.link}" 
								</#if> 
						>${channel.channelName}</a>
						</li>
					</#list>
				</#if>
		</@channelTag>
	</#if>
</#macro>

<#macro contents_orange channelPath page pageSize basePath imgPath sysId query="" exclusive="">
	<#if channelPath??&&page??&&pageSize??>
		<@contentTag  where="siteId=${siteId},channelId=${channelPath}" page="${page}" pageSize="${pageSize}"  sysId="${sysId}" exclusive="${exclusive}" query="${query}">
				<#if contentList??&&contentList?size gt 0>
				<#list contentList as content >
					 <li>
							<i>${(page-1)*pageSize+content_index+1}</i>
							<#if content.link??>
								<#if content.link!=''>
								<a target="_blank" href="${content.link}" title="${content.title!""}">${content.title!""}</a>
								<#else> 
								<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm">${content.title!""}</a>
								</#if>
							<#else> 
							<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm"  title="${content.title!""}">${content.title!""}</a>
							</#if>
						<!--<em>${content.viewsCount!0}</em>-->
						<em class="fr">${content.releaseDate?string("yyyy/MM/dd")}</em>
					 </li>
				</#list>
			</#if>
		</@contentTag>
	</#if>
</#macro>

<#macro contents_orange_1 where channelPath page pageSize basePath imgPath sysId  query="" exclusive="">
	<#if channelPath??&&page??&&pageSize??>
		<@contentTag  where="siteId=${siteId},channelId=${channelPath}" page="${page}" pageSize="${pageSize}"  sysId="${sysId}" exclusive="${exclusive}" query="${query}">
				<#if contentList??&&contentList?size gt 0>
				<#list contentList as content >
						<li>
							<dl>
						<#if content.hasTitleImg??&&content.hasTitleImg>
							<dt><img  src="${titleImgGet!""}${content.titleImg!""}"/></dt>
						</#if>
							<dd>
								<#if content.link??>
									<#if content.link!=''>
									<a class="ass_title" target="_blank" href="${content.link}" title="${content.title!""}">${content.title!""}</a>
									<#else> 
									<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm">${content.title!""}</a>
									</#if>
								<#else> 
								<a class="ass_title" target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title="${content.title!""}">
									${content.title!""}
								</a>
								</#if>
								<p>发布时间：${content.releaseDate?string("yyyy-MM-dd")}</p>
	              <div class="ass_txt">
	              	<span>
	                  	${content.description!""}
	                  </span>
	              </div>
							</dd>
					</dl>
					</li>
				</#list>
			</#if>
		</@contentTag>
	</#if>
</#macro>

<#macro contents_orange_2 channelPath page pageSize basePath imgPath sysId query="" exclusive="">
	<#if channelPath??&&page??&&pageSize??>
		<@contentTag  where="siteId=${siteId},channelId=${channelPath}" page="${page}" pageSize="${pageSize}"  sysId="${sysId}" exclusive="${exclusive}" query="${query}">
				<#if contentList??&&contentList?size gt 0>
				<#list contentList as content >
					 <li>
							<#if content.link??>
								<#if content.link!=''>
								<a target="_blank" href="${content.link}" title="${content.title!""}"><span>${content.title!""}</span>
								<em>[${content.releaseDate?string("yyyy/MM/dd")}]</em></a>
								<#else> 
								<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title="${content.title!""}"><span>${content.title!""}</span>
								<em>[${content.releaseDate?string("yyyy/MM/dd")}]</em>
								</a>
								</#if>
							<#else> 
							<a target="_blank" href="${basePath}cms/${content.siteId}/content/${content.contentId}.htm" title="${content.title!""}"><span>${content.title!""}</span>
							<em>[${content.releaseDate?string("yyyy/MM/dd")}]</em>
							</a>
							</#if>
					 </li>
				</#list>
			</#if>
		</@contentTag>
	</#if>
</#macro>


<#macro site_list where channelPath page pageSize basePath imgPath sysId query="" exclusive="">
	<#if channelPath??&&page??&&pageSize??>
		<@siteTag  where="siteId=${siteId},channelId=${channelPath}" page="${page}" pageSize="${pageSize}"  sysId="${sysId}" exclusive="${exclusive}" query="${query}" flag="${flag}">
				<#if dataList??&&dataList?size gt 0>
				<#list dataList as item >
					<li><a target="_blank" href="${content.indexUrl}">${content.siteName!""}</a></li>
				</#list>
			</#if>
		</@siteTag>
	</#if>
</#macro>