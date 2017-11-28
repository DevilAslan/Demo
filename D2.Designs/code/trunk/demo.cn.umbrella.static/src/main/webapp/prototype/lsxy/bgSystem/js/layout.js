;;
//头部用户信息下拉
//var $parent = $(".fi-arrow-down").parent('a');
//下拉显示
function slideShow(){
	$('.user-control').css({
		'top':$(".fi-arrow-down").parent('a').offset().top + 10 + $(".fi-arrow-down").parent('a').outerHeight(),
		'left':$(".fi-arrow-down").parent('a').offset().left + 30 + 'px'
	}).slideDown().show();
};
//下拉隐藏
function slideHide(){
	$('.user-control').fadeOut("fast");
};

//home page中icon的行高
function homeIcon(){
	$(".home .main i").css({
		'line-height':$(".home .main i").parent('span').height() + 'px'
	});
};

//iframe自适应
function iFrameHeight(id) {   
	var ifm= document.getElementById(id);   
	var subWeb = document.frames ? document.frames[id].document : ifm.contentDocument;   
	if(ifm != null && subWeb != null) {
	   ifm.height = subWeb.body.scrollHeight;
	   ifm.width = subWeb.body.scrollWidth;
	};
	$("#" + id).attr("width","100%");
};

var posTop,
	newSearchH,
	oldSearchH = $(".main-search").height() + 12;
	
function mainSearch(){
	//mainsearch高度变化
	if(newSearchH != oldSearchH){
		//在窗口变化后对main-table-outer不同上一级的移动位置
		if($('.main-table-outer').prev('div').is('.main-control')){
			posTop = $('.main-control').offset().top;
		}else if($('.main-table-outer').prev('div').is('.main-search')){
			if($('.main-table-outer').attr('class') == 'main-table-outer dialog-table-outer'){
				posTop = $('.main-search').offset().top + $('.main-search').height() + 5;
			}else{
				posTop = $('.main-search').offset().top;
			};
		}else if($('.main-table-outer').prev('div').is('.main-bread-crumb')){
			posTop = $('.main-bread-crumb').height() + 4;
		}else if($('.main-table-outer').prev('*').is('form')){
			posTop = $('.main-search').parents('form').height() ;
		};
		$('.main-table-outer').css({'margin-top' : posTop});
	};
	//datagrid宽高
	$('.user-datagrid').datagrid('resize', {
		height:'100%',
		width:'100%'
	});
};

$(function(){
	//侧边栏滚动条
	if($('.scroll').length > 0){
		$('.scroll').slimscroll({
			height:'100%'
	    });
	};
	
	/*搜索栏下拉*/
	var a = $(".hide-li");
	a.hide();
	
	//针对main-table-outer不同上一级的移动位置
	if($('.main-table-outer').prev('div').is('.main-control')){
		posTop = $('.main-control').offset().top;
	}else if($('.main-table-outer').prev('div').is('.main-search')){
		if($('.main-table-outer').attr('class') == 'main-table-outer dialog-table-outer'){
			posTop = $('.main-search').offset().top + $('.main-search').height() + 5;
		}else{
			posTop = $('.main-search').offset().top;
		};
	}else if($('.main-table-outer').prev('div').is('.main-bread-crumb')){
		posTop = $('.main-bread-crumb').height() + 4;
	}else if($('.main-table-outer').prev('*').is('form')){
		posTop = $('.main-search').parents('form').height() ;
	};
	
	$('.main-table-outer').css({'margin-top' : posTop});
	
	//dataGrid上方有form
	if($('.main-table-outer').find('.mini-form').length > 0){
		var dataGridTop = $('.mini-form').height() + 40;
	};
	$('.tree-and-table-inner').css({'top' : dataGridTop});
	
	/*高级搜索按钮点击*/
	$(".more-text").click(function() {
		$(this).parents("ul").children("li:first-child").toggleClass("w-per100");
		
		//清空值
		var idS = $('.main-search').closest('form').attr('id');
		document.getElementById(idS).reset(); 
		if($('.main-search').find(".been-elected-box").length > 0){
			$(".been-elected-box li").remove();
		};
		
		//高级搜索显示与隐藏
		if (a.is(':visible')) {
			a.hide();
			$(this).removeClass('up');
			/*//版本一
			$(this).html('显示更多查询条件<span class="fi-arrow-down"></span>');*/
			
			//版本二
			$(this).css({
				'border-top': '0 none'
			}).html('<p>显示更多查询条件<span class="fi-arrow-down"></span></p>');
			
			$('.search-btn-group').width('auto').hide();
			
			//弹窗内两种搜索都有
			if($(this).parent('.main-search').prev('.dialog-search').length){
				$(this).parent('.main-search').prev('.dialog-search').show();
			};
			
			if($('.main-table-outer').prev('div').is('.main-control')){
				posTop = $('.main-control').offset().top;
			}else if($('.main-table-outer').prev('div').is('.main-search')){
				if($('.main-table-outer').attr('class') == 'main-table-outer dialog-table-outer'){
					posTop = $('.main-search').offset().top + $('.main-search').height() + 5;
				}else{
					posTop = $('.main-search').offset().top;
				};
			}else if($('.main-table-outer').prev('div').is('.main-bread-crumb')){
				posTop = $('.main-bread-crumb').height() + 4;
			}else if($('.main-table-outer').prev('*').is('form')){
				posTop = $('.main-search').parents('form').height() ;
			};
			
			$('.main-table-outer').css({'margin-top' : posTop});
			
			$('.user-datagrid').datagrid('resize', {
				height:'100%',
				width:'100%'
			});
			
		} else {
			a.show();
			$(this).addClass('up');
			/*//版本一
			$(this).html('隐藏查询条件<span class="fi-arrow-up"></span>');*/
			
			//版本二
			$(this).css({
				'border-top': '1px dashed #e8e8e8'
			}).html('<p>隐藏查询条件<span class="fi-arrow-up"></span></p>');
			
			$('.search-btn-group').width('100%').show();
			
			//弹窗内两种搜索都有
			if($(this).parent('.main-search').prev('.dialog-search').length){
				$(this).parent('.main-search').prev('.dialog-search').hide();
			};
			
			if($('.main-table-outer').prev('div').is('.main-control')){
				posTop = $('.main-control').offset().top;
			}else if($('.main-table-outer').prev('div').is('.main-search')){
				if($('.main-table-outer').attr('class') == 'main-table-outer dialog-table-outer'){
					posTop = $('.main-search').offset().top + $('.main-search').height() + 5;
				}else{
					posTop = $('.main-search').offset().top;
				};
			}else if($('.main-table-outer').prev('div').is('.main-bread-crumb')){
				posTop = $('.main-bread-crumb').height() + 4;
			}else if($('.main-table-outer').prev('*').is('form')){
				posTop = $('.main-search').parents('form').height() ;
			};
			
			$('.main-table-outer').css({'margin-top' : posTop});
			
			$('.user-datagrid').datagrid('resize', {
				height:'100%',
				width:'100%'
			});
		}
	});
	
	//针对tab页面,点击tab标签后dataGrid的自适应
	$('.tab-title-box > div').click(function(){
		$('.user-datagrid').datagrid('resize', {
			height:'100%',
			width:'100%'
		});
	})
	
	//sub-nav控制————有三级菜单
	$('.cur').next('ul').slideDown(500).show();
//	$('.cur').next('ul').children('li').eq(0).children('a').addClass('cur-a');
	$('.sub-nav-inner > div > h3').click(function(){
		$(this).siblings('h3').removeClass('cur');
		$(this).siblings('h4').removeClass('cur');
		$(this).addClass('cur');
		$('.sub-nav-inner ul').hide();
		$('.cur').next('ul').slideDown(500).show();
		$('.cur').next('ul').children('li').eq(0).children('a').addClass('cur-a');
		location.href = $('.cur').next('ul').children('li').eq(0).find('a').attr('href');
		//控制菜单箭头
		if($(this).children('span').attr('class') == 'fi-arrow-right'){
			$('.cur').children('span').removeClass('fi-arrow-right').addClass('fi-arrow-down');
			$('.cur').siblings('h3').children('span').removeClass('fi-arrow-down').addClass('fi-arrow-right');
		};
	});
	
	/*$('.cur').next('dd').slideDown(500).show();
	$('.sub-nav-inner dt').click(function(){
		$(this).siblings('dt').removeClass('cur');
		$(this).addClass('cur');
		$('.sub-nav-inner dd').hide();
		$('.cur').next('dd').slideDown(500).show();
		//控制菜单箭头
		if($(this).children('span').attr('class') == 'fi-arrow-right'){
			$('.cur').children('span').removeClass('fi-arrow-right').addClass('fi-arrow-down');
			$('.cur').siblings('dt').children('span').removeClass('fi-arrow-down').addClass('fi-arrow-right');
		};
	});*/
	//sub-nav控制————无三级菜单
	$('.sub-nav-inner > div > h4').click(function(){
		$(this).siblings('h3').removeClass('cur');
		$(this).siblings('h4').removeClass('cur');
		$(this).addClass('cur');
		$('.sub-nav-inner ul').slideUp(500);
		location.href = $(this).find('a').attr('href');
		//控制菜单箭头
		$('.cur').siblings('h3').children('span').removeClass('fi-arrow-down').addClass('fi-arrow-right');
	});
	
	//sub-nav下h4里没有图标的情况下,
	if($('.sub-nav-inner h4 a').find('i').length  > 0){
		$('.sub-nav-inner i').parents('h4').css({'padding-left':'28px'});
	};
	
	//侧边栏缩进
	$('.fi-menu').click(function(){
		if ($('.sub-nav-outer').is(':visible')) {
			$('.sub-nav-outer').fadeOut('slow').hide();
			$('.main,.main-form-outer,.main-table-outer,.btn-row.fixed').css({'padding-left':0});
			$('.user-datagrid').datagrid('resize', {
				height:'100%',
				width:'100%'
			});
			$('.fi-menu').css({
				'left':0
			});
			$(".logo").css({
				'left':'48px'
			});
		} else{
			$('.sub-nav-outer').fadeOut('slow').show();
			$('.main,.main-form-outer,.main-table-outer,.btn-row.fixed').css({'padding-left':'168px'});
			$('.user-datagrid').datagrid('resize', {
				height:'100%',
				width:'100%'
			});
			$('.fi-menu').css({
				'left':'140px'
			});
			$(".logo").css({
				'left':'10px'
			});
		}
	});
	
	/*//已选内容取消
	$('.cancel-ico').click(function(){
		$(this).parent('li').remove();
	});*/
	
	//头部用户信息下拉
	$('.top-info .fi-arrow-down').parent('a').click(function(){
		slideShow();
	});
	$('.user-control').mouseleave(function() {
		slideHide();
	});
	$("body").bind("click",function(evt){ 
		if($(evt.target).parents(".top-info").length==0) slideHide();
	});
	
	//斑马线
	$(".table-default tbody tr:even td").css({
		"backgroundColor": "#f5f5f5",
		"background": "none\9"
	});
	$(".table-default tbody tr:odd td").css({
		"backgroundColor": "#fff",
		"background": "none\9"
	});
//	$("table").attr({"border":"0", "cellpadding":"1","cellspacing":"1"};

	//home page中icon的行高
	homeIcon();
	
	//mainsearch高度变化
	newSearchH = $(".main-search").height() + 12;
	if(newSearchH != oldSearchH){
		//在窗口变化后对main-table-outer不同上一级的移动位置
		if($('.main-table-outer').prev('div').is('.main-control')){
			posTop = $('.main-control').offset().top;
		}else if($('.main-table-outer').prev('div').is('.main-search')){
			if($('.main-table-outer').attr('class') == 'main-table-outer dialog-table-outer'){
				posTop = $('.main-search').offset().top + $('.main-search').height() + 5;
			}else{
				posTop = $('.main-search').offset().top;
			};
		}else if($('.main-table-outer').prev('div').is('.main-bread-crumb')){
			posTop = $('.main-bread-crumb').height() + 4;
		}else if($('.main-table-outer').prev('*').is('form')){
			posTop = $('.main-search').parents('form').height() ;
		};
		$('.main-table-outer').css({'margin-top' : posTop});
	};
	
	//btn-row fixed滚动条控制
	if ($('.main-form-outer .btn-row.fixed').length <= 0) {
		$('.main-form-outer').css({'paddingBottom':'0'});
	};
});

$(window).resize(function(){
	//在窗口变化后对main-table-outer不同上一级的移动位置
	if($('.main-table-outer').prev('div').is('.main-control')){
		posTop = $('.main-control').offset().top;
	}else if($('.main-table-outer').prev('div').is('.main-search')){
		if($('.main-table-outer').attr('class') == 'main-table-outer dialog-table-outer'){
			posTop = $('.main-search').offset().top + $('.main-search').height() + 5;
		}else{
			posTop = $('.main-search').offset().top;
		};
	}else if($('.main-table-outer').prev('div').is('.main-bread-crumb')){
		posTop = $('.main-bread-crumb').height() + 4;
	}else if($('.main-table-outer').prev('*').is('form')){
		posTop = $('.main-search').parents('form').height() ;
	};
	$('.main-table-outer').css({'margin-top' : posTop});
	
	//头部用户信息下拉
	slideHide();
	//home page中icon的行高
	homeIcon();
})