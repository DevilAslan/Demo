<div class="head-outer">
	<div class="head clearfix">
		<a href="${basePath}home.htm"><div class="logo"></div></a>
		<div class="nav-outer">
			<a href="${basePath}home.htm?siteId=${siteId}" 
				<#if channelLevel1??&&channelLevel1=="index">
								class="cur"
				</#if>>
				<i class="nav-icon nav-icon0"></i>
				<p>首页</p>
			</a>
			<@channelTag where="siteId=${siteId},parentId=0" page="1" pageSize="5" sysId="${sysId}">
				<#if channelList??&&channelList?size gt 0>
					<#list channelList as channel>
						<a href="<#if channel.hasContent>
									${basePath}cms/${siteId}/${channel.channelId}.htm
								<#else>
									${channel.link}
								</#if>" 
							<#if channelLevel1??&&channelLevel1==channel.channelId>
								class="cur"
							</#if>>
						<#-- <a href="/front/home.htm?index=${channel?index+1}">-->
							<i class="nav-icon ${channel.channelClass}"></i>
							<p>${channel.channelName}</p>
						</a>
					</#list>
				</#if>
			</@channelTag>
			<div class="user-control">
				<div class="user-info clearfix">
					<#if creditSession.loginname?exists>
						<a href="javascript:" onclick="myCredit_1();" id="myCredit" class="user">
							<span class="head-icon user-icon"></span>
							<span>你好,
								<#if creditSession.creditName?exists>
									${creditSession.creditName}
								<#else>
									${creditSession.creditName}
								</#if>
							</span>
							<i class="s-icon arrow-down-icon"></i>
						</a>
					<#else>
						<a href="javascript:void(0);" onclick="loginGo();" id="loginGo">
							<span class="head-icon user-login-icon"></span>
							<span>登录</span>
						</a>
						<a href="javascript:void(0);" onclick="register();" id="register">
							<span class="head-icon register-icon"></span>
							<span>注册</span>
						</a>
					</#if>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${thirdpartyPath}thirdparty/jquery-1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="${thirdpartyPath}thirdparty/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>

<script type="text/javascript" src="${thirdpartyPath}thirdparty/artDialog/dialog-min.js"></script>
<script type="text/javascript" src="${thirdpartyPath}thirdparty/artDialog/dialog-plus-min.js"></script>
		<!--slimScroll-->
<script type="text/javascript" src="${thirdpartyPath}thirdparty/slimScroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${thirdpartyPath}thirdparty/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="${basePath}js/common.js" ></script>
<script type="text/javascript" src="${basePath}js/public.js"></script>
<script type="text/javascript">
	function register(){
		$.GD.bubbles({
			id: "register", 
			msg: "<div class='bubbles-list'><button onclick='openProtocol(1);'>个人注册</button><button onclick='openProtocol(2);'>法人注册</button></div>"
		});
	};
	function openProtocol(type) {
		$.GD.dialog({
			id: 'showDialog1',
			title: '信用信息服务平台用户服务协议',
	        url: '${basePath}register.htm?type='+type,
	        width:'1000px',
	        height:'650px',
		});
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
			"<a href='${basePath}serviceHall/toMyDissent.htm'><span>我的异议</span></a>"+"<a href='javascript:void(0)' onclick='logout()'><span>退出登录</span></a></div>";
			
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
	$(function(){
		var pageMsg = "${msg!""}";
		if(pageMsg!=""){
			$.GD.msg(pageMsg);
		}
	});
	
	//选择地区
	var topSelect=true;
	$('.top-select i').click(function(){
		if(topSelect){
			$('.top-select .select_nav').show();
			$('.top-select i').removeClass('i_x').addClass('i_s');
			topSelect=!topSelect;
		}else{
			$('.top-select .select_nav').hide();
			$('.top-select i').removeClass('i_s').addClass('i_x');
			topSelect=!topSelect;
		}
		
	})
	$('.select_nav_ul a').click(function(){
		$('.top-select i').removeClass('i_s').addClass('i_x');
		$('.select_nav').hide();
		topSelect=false;
	})
	$('.select_nav').mouseleave(function(){
		$('.top-select i').removeClass('i_s').addClass('i_x');
		$('.select_nav').hide('slow');
		topSelect=false;
	})
</script>
		