<div class="footer">
	<div class="footer-inner clearfix">
		<div class="left-footer footer-logo">
			<!--党政机关 代码-->
			<script type="text/javascript">document.write(unescape("%3Cspan id='_ideConac' %3E%3C/span%3E%3Cscript src='http://dcs.conac.cn/js/12/188/0000/60417701/CA121880000604177010001.js' type='text/javascript'%3E%3C/script%3E"));</script>
		</div>
		<div class="center-footer">
			<ul>
				<li><a href="${basePath}cms/${siteId}/wzly.htm">网站留言</a></li>
				<li>|</li>
				<li><a href="${basePath}cms/${siteId}/qtwz.htm">相关链接</a></li>
				<li>|</li>
				<li><a href="${basePath}cms/856/index.htm">信用协会</a></li>
				<li>|</li>
				<li>累计访问：${pageView}次</li>
			</ul>
			<p>瑞安市信用瑞安建设领导小组办公室主办 瑞安市信用信息中心管理</p>
			<p>备案证编号：浙ICP备06021093号  联系方式：<span>0</span><span>5</span><span>7</span><span>7</span>-<span>8</span><span>8</span><span>9</span><span>6</span><span>1</span><span>5</span><span>6</span><span>1</span></p>
		</div>
		<div class="right-footer">
		    <div class="code-box code-box2">
                <p><i class="home-img footer-label-left"></i><span class="footer-label-right">信用瑞安<br/>APP iOS版</span></p>
                <img src="${basePath}images/iOS.jpg" />
            </div>
			<div class="code-box code-box2">
				<p><i class="home-img footer-label-left"></i><span class="footer-label-right">信用瑞安<br/>APP 安卓版</span></p>
				<img src="${basePath}images/android.jpg" />
			</div>
			<div class="code-box code-box2">
				<p><i class="home-img footer-label-left"></i><span class="footer-label-right">信用瑞安<br/>公众号</span></p>
				<img src="${basePath}images/wechat.jpg" />
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	 function setHome(val){
        try{
            //设置或检索对象的DHTML行为
            document.body.style.behavior='url(#default#homepage)';
            document.body.setHomePage(val);
        }
        catch(e){
            if(window.netscape) {
                try {
                    //netscape.security.PrivilegeManager.enablePrivileg ，权限设置，有的浏览器需要用户配置浏览器安全属性才能执行
                    netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                }
                catch (e)  {
                    alert("此操作被浏览器拒绝，请手动设置！");
                }
                //Components.classes 是一个被ContractID类索引的只读对象属性。
                var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService
                        (Components.interfaces.nsIPrefBranch);
                prefs.setCharPref('browser.startup.homepage',val);//浏览器的偏好设置
            }else{
                alert("设置首页失败，请手动设置！");
            }
        }
    };
    
    function addFavorite(fURL, fTitle){
        try {//IE支持的API
            window.external.AddFavorite(fURL, fTitle);
        } catch(e) {//FF支持的API
            try{
                window.sidebar.addPanel(fTitle, fURL, "");
            }catch(error){
                //如果不支持以上两种方案，采用 提示性收藏
                alert("加入收藏失败，请用Ctrl+D 或 手动设置！");
            }
        }
    };
</script>