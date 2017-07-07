;;

var posTop;
$(function(){
	//侧边栏滚动条
	$('.scroll').slimscroll({
		height:'100%'
    });
	
	/*搜索栏下拉*/
	var a = $(".hide-li");
	a.hide();
	
	//针对main-table-outer不同上一级的移动位置
	if($('.main-table-outer').prev('div').is('.main-control')){
		posTop = $('.main-control').offset().top + $('.main-control').height() + 12;
	}else if($('.main-table-outer').prev('div').is('.main-search')){
		posTop = $('.main-search').offset().top + $('.main-search').height() + 12;
	}else if($('.main-table-outer').prev('div').is('.main-bread-crumb')){
		posTop = $('.main-bread-crumb').offset().top + $('.main-bread-crumb').height() + 4;
	};
	
	$('.main-table-outer').css({'margin-top' : posTop});
	
	//dataGrid上有form
	if($('.main-table-outer').find('.mini-form').length > 0){
		var dataGridTop = $('.mini-form').height() + 40;
	};
	$('.tree-and-table-inner').css({'top' : dataGridTop});
	
	/*高级搜索按钮点击*/
	$(".more-text").click(function(e) {
		$(this).parents("ul").children("li:first-child").toggleClass("w-per100");
		
		//清空值
		var idS = $('.main-search form').attr('id');
		document.getElementById(idS).reset(); 
		if($('.main-search form').find(".been-elected-box").length > 0){
			$(".been-elected-box li").remove();
		};
		
		//高级搜索显示与隐藏
		if (a.is(':visible')) {
			a.hide();
			$(this).removeClass('up');
			$(".search-input").show();
			$('.search-btn-group').width('auto');
			
			if($('.main-table-outer').prev('div').is('.main-control')){
				posTop = $('.main-control').offset().top + $('.main-control').height() + 12;
			}else if($('.main-table-outer').prev('div').is('.main-search')){
				posTop = $('.main-search').offset().top + $('.main-search').height() + 12;
			}else if($('.main-table-outer').prev('div').is('.main-bread-crumb')){
				posTop = $('.main-bread-crumb').offset().top + $('.main-bread-crumb').height() + 4;
			};
			
			$('.main-table-outer').css({'margin-top' : posTop});
			
			$('.user-datagrid').datagrid('resize', {
				height:'100%',
				width:'100%'
			});
			
		} else {
			a.show();
			$(this).addClass('up');
			$(".search-input").hide();
			$('.search-btn-group').width('100%');
			
			if($('.main-table-outer').prev('div').is('.main-control')){
				posTop = $('.main-control').offset().top + $('.main-control').height() + 12;
			}else if($('.main-table-outer').prev('div').is('.main-search')){
				posTop = $('.main-search').offset().top + $('.main-search').height() + 12;
			}else if($('.main-table-outer').prev('div').is('.main-bread-crumb')){
				posTop = $('.main-bread-crumb').offset().top + $('.main-bread-crumb').height() + 4;
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
	
	//sub-nav控制
	$('.cur').next('dd').slideDown(500).show();
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
	});
	
	//缩进按钮提示
	$('.breadcrumb-icon').attr('title','点击侧边栏缩进');
	//侧边栏缩进
	$('.breadcrumb-icon').click(function(){
		if ($('.sub-nav-outer').is(':visible')) {
			$('.sub-nav-outer').fadeIn('slow').hide();
			$('.main,.main-form-outer,.main-table-outer,.btn-row.fixed').css({'padding-left':0});
			$('.user-datagrid').datagrid('resize', {
				height:'100%',
				width:'100%'
			});
		} else{
			$('.sub-nav-outer').fadeIn('slow').show();
			$('.main,.main-form-outer,.main-table-outer,.btn-row.fixed').css({'padding-left':'138px'});
			$('.user-datagrid').datagrid('resize', {
				height:'100%',
				width:'100%'
			});
		}
	});
	
	//已选内容取消
	$('.cancel-ico').click(function(){
		$(this).parent('li').remove();
	});
});

$(window).resize(function(){
	//在窗口变化后对main-table-outer不同上一级的移动位置
	if($('.main-table-outer').prev('div').is('.main-control')){
		posTop = $('.main-control').offset().top + $('.main-control').height() + 12;
	}else if($('.main-table-outer').prev('div').is('.main-search')){
		posTop = $('.main-search').offset().top + $('.main-search').height() + 12;
	}else if($('.main-table-outer').prev('div').is('.main-bread-crumb')){
		posTop = $('.main-bread-crumb').offset().top + $('.main-bread-crumb').height() + 4;
	};
	$('.main-table-outer').css({'margin-top' : posTop});
})