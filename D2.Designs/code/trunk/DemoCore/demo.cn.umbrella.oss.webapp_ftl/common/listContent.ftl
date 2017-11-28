<ul id="list" class="holder-content"><!--有分页时添加这个class,同时一定要有id-->
	<#if channelLevel3??>
		<@contentTag  where="siteId=${siteId},channelId=${channelLevel3}" page="${page}" pageSize="${pageSize}">
				<#if contentList??&&contentList?size gt 0>
				<#list contentList as content >
					<li>
						<#if content.hasTitleImg??&&content.hasTitleImg>
							<div class="left img">
									<img  src="${basePath}${content.titleImg!""}"/>
							</div>
							<div class="right text">
								<a href="infoList-xydt-detail.html" class="font-size18">
									${content.title!""}
								</a>
								<p class="font-size14">${content.description!""}</p>
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
											<span>${content.viewsDay!}</span>
										</div>
									</div>
								</div>
							</div>
						<#else>
							<div class="right text w-per100">
								<a href="###" class="font-size18">${content.title!""}</a>
								<p class="font-size14">${content.description!""}</p>
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
											<span>${content.viewsDay!0}</span>
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
	</ul>
	<div id="jqueryPage" class="page-outer">
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