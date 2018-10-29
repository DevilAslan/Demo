;;
//多行文本溢出//限制字符个数
$.fn.textOverflow = function(o){
	var defaults = {
        lineHeight : 18,
        row : 3,
        textSize: 'auto',
        background:'#fff'
    };
    o = $.extend({},defaults,o);
    return this.each(function(){
    	var $this = $(this);
    	if($this.closest('a').css('backgroundColor') != 'rgba(0, 0, 0, 0)'){
    		o.background = $this.closest('a').css('backgroundColor');
    	};
    	if(o.textSize == 'auto'){
    		if(o.lineHeight == 'auto'){
	    		$this.css({
	            	'overflow': 'hidden'
	            }).append('<span class="ellipsis">...</span>');
	           
	           $this.find('.ellipsis').css({'backgroundColor':o.background});
	    	}else{
				if($this.height() > (o.lineHeight*o.row)){
		            $this.css({
		            	'height': o.lineHeight*o.row,
		            	'overflow': 'hidden'
		            }).append('<span class="ellipsis">...</span>');
		            $this.find('.ellipsis').css({'backgroundColor':o.background});
		        };
			};
    	}else if(o.textSize >= 0){
    		var txtLen = $this.text().length;
	    	if(txtLen > o.textSize){
	    		$this.css({
	            	'overflow': 'hidden',
	            	'height': $this.parent('a').height()
	            }).append('<span class="ellipsis">...</span>');
	            $this.find('.ellipsis').css({'backgroundColor':o.background});
	    	}
		}
    });
};

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

//查询条件label的行高变化
function lineHeight(){
	$("label.query-label").each(function(){
		if($(this).height() > 35){
			$(this).css("lineHeight",1.3);
		};
	});
};

//iframe自适应
function iFrameHeight(id) {   
	var vHeight = $("#" + id).contents().find("body > div").height();
	$("#" + id).height(vHeight);
	$("#" + id).attr("width","100%");
};

var posTop;

function boxTop(obj){
	//在窗口变化后对main-table-outer不同上一级的移动位置
	if(obj == ('.tree-and-table-inner')){
		posTop = $(obj).prev('*').position().top + $(obj).prev('*').height() + 12;
	}else{
		if($(obj).prev('div').is('.main-control')){
			posTop = $('.main-control').position().top + $('.main-control').height();
		}else if($(obj).prev('div').is('.main-search')){
			if($(obj).hasClass('dialog-table-outer')){
				posTop = $('.main-search').offset().top + $('.main-search').height() + 5;
			}else{
				posTop = $(obj).prev().position().top + $(obj).prev().height();
			};
		}else if($(obj).prev('div').is('.main-bread-crumb')){
			posTop = $('.main-bread-crumb').height() + 4;
		}else if($(obj).prev('*').is('form')){
			posTop = $(obj).prev('form').height() ;
		};
	}
	$(obj).css({'margin-top' : posTop});
};
//marginTop
function marginTop(){
	boxTop('.main-table-outer');
	//tab里的top
	$('.tab-table-inner').each(function(){
		var $this = $(this);
		if($this.attr('class') != 'tab-table-inner tab-form'){
			var _innerParent = $this.parent('.tab-content');
			var _innerSiblings = _innerParent.children().length;
			switch (_innerSiblings) 
				{ 
					case 1:
						$this.css({
							'top':$this.parents('.tab-title-box').height() + 5
						});
					break; 
					case 2:
						$this.css({
							'top':function(){
								if($this.siblings().is('.main-control')){
									var paddingTop = $this.siblings('.main-control').css("padding-top"),
										paddingBottom = $this.siblings('.main-control').css("padding-bottom"),
										topLen = paddingTop.length,
										bottomLen = paddingBottom.length,
										num = Number(paddingTop.substring(0,topLen - 2)) +Number(paddingBottom.substring(0,topLen - 2));
									return $this.siblings().position().top + $this.siblings().height() + num;
								}else if($this.siblings().is('.main-search')){
									return $this.siblings().position().top + $this.siblings().height() + 12;
								}else{
									return $this.siblings().position().top + $this.siblings().height() + 4;
								}
								
							}
						});
					break; 
					case 3:
						$this.css({
							'top': $this.siblings('.main-control').position().top + $this.siblings('.main-control').height() + 12
						});
					break; 
				};
		}
		
	});
};

$(function(){
	//侧边栏滚动条
	if($('.scroll').length > 0){
		$('.scroll').slimscroll({
			height:'100%'
	    });
	};
	
	//查询条件label的行高变化
	lineHeight();
	
	/*搜索栏下拉隐藏*/
	$(".hide-li").hide();
	
	//top位置变化
	marginTop();
	if($(".have-search .tree-and-table-inner").length > 0){
		boxTop('.tree-and-table-inner');
	};
	
	//dataGrid上方有form
	if($('.main-table-outer').find('.mini-form').length > 0){
		var dataGridTop = $('.mini-form').height() + 40;
	};
	$('.tree-and-table-inner').css({'top' : dataGridTop});
	
	/*************** input输入框的提示 ****************/
	//input的placeholder
	if($('.input-outer').length > 0){
		$('.input-outer input').each(function(){
			var $this = $(this),val = $(this).val();
			if(val.length > 0){
				$this.siblings('.input-placeholder').hide(0);
			}
		})
	};
	$('.input-outer input').on('input propertychange',function(){
		var $this = $(this),val = $(this).val();
		if(val.length > 0){
			$this.siblings('.input-placeholder').hide(0);
		}else{
			$this.siblings('.input-placeholder').show(0);
		}
	});
	
	/*高级搜索按钮点击*/
	$(".more-text").click(function() {
		$(this).parents("ul").children("li:first-child").toggleClass("w-per100");
		
		//清空值
		/*var idS;
//			$('.main-search').closest('form').attr('id')
		if($('.main-search').closest('form').length > 0){
			idS = $('.main-search').closest('form').attr('id')
		}else if($('.main-search').find('form').length > 0){
			idS = $('.main-search').find('form').attr('id')
		};
		document.getElementById(idS).reset(); 
		if($('.main-search').find(".been-elected-box").length > 0){
			$(".been-elected-box li").remove();
		};*/
//		document.getElementById(idS).reset(); 
		var keyFormId = $('input[name=keyword]').closest('form').attr('id');
		if(keyFormId) {
			document.getElementById(keyFormId).reset(); 
		}
		if($('.main-search').find(".been-elected-box").length > 0){
			$(".been-elected-box li").remove();
		};
		
		//高级搜索显示与隐藏
		if ($(".hide-li").is(':visible')) {
			$(".hide-li").hide();
			$(this).removeClass('up');
			
			//显示更多查询条件
			/*//版本一
			$(this).html('显示更多查询条件<span class="fi-arrow-down"></span>');*/
			
			//版本二
            if($(this).find('.fi-arrow-up').length > 0){
                $(this).css({
                    'border-top': function(){
                        if($(this).closest('.main-search').find('.overflow-hidden').length !=1){
                            return '1px dashed #e8e8e8';

                        }else{
                            return '0 none';
                        }
                    }
                }).html('<p>显示更多查询条件<span class="fi-arrow-down"></span></p>');
            } else {
                $(this).html('<p>显示更多查询条件</p>');
            };
			
			$(".hide-li").find('.search-btn-group').width('auto').hide();
			
			//弹窗内两种搜索都有
			if($(this).parent('.main-search').prev('.dialog-search').length){
				$(this).parent('.main-search').prev('.dialog-search').show();
			};
			
			//查询条件label的行高变化
			lineHeight();
			
			//top位置变化
			marginTop();
			if($(".have-search .tree-and-table-inner").length > 0){
				boxTop('.tree-and-table-inner');
			};
			
			$('.user-datagrid').datagrid('resize', {
				height:'100%',
				width:'100%'
			});
			
		} else {
			$(".hide-li").show();
			$(this).addClass('up');
			
			//隐藏更多查询条件
			/*//版本一
			$(this).html('隐藏查询条件<span class="fi-arrow-up"></span>');*/
			
			//版本二
            if($(this).find('.fi-arrow-down').length > 0){
                $(this).css({
                    'border-top':'1px dashed #e8e8e8'
                }).html('<p>隐藏查询条件<span class="fi-arrow-up"></span></p>');
            } else {
                $(this).html('<p>隐藏查询条件</p>');
            };
			
			$(".hide-li").find('.search-btn-group').width('100%').show();
			
			//弹窗内两种搜索都有
			if($(this).parent('.main-search').prev('.dialog-search').length){
				$(this).parent('.main-search').prev('.dialog-search').hide();
			};
			
			//查询条件label的行高变化
			lineHeight();
			
			//top位置变化
			marginTop();
			if($(".have-search .tree-and-table-inner").length > 0){
				boxTop('.tree-and-table-inner');
			};
			
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
		
		//top位置变化
		marginTop();
	})
	
	//sub-nav控制————有三级菜单
	$('.cur').next('ul').slideDown(500).show();
	$('.sub-nav-inner > div > h3').click(function(){
		$(this).siblings('h3').removeClass('cur');
		$(this).siblings('h4').removeClass('cur');
		$(this).addClass('cur');
		$('.sub-nav-inner ul').hide();
		$('.cur').next('ul').slideDown(500).show();
		$('.cur').next('ul').children('li').eq(0).children('a').addClass('cur-a');
		location.href = $('.cur').next('ul').children('li').eq(0).find('a').attr('href');
	});
	
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
	
	//sub-nav下h4里没有图标的情况下
	if($('.sub-nav-inner h4 a').find('i').length  > 0){
		$('.sub-nav-inner i').parents('h4').css({'padding-left':'60px'});
	};
	//当sub-nav里h4的行数大于1时调整行高
	$('.sub-nav-inner h4 a').each(function(){
		if($(this).text().length > 8){
			$(this).css('lineHeight',1.5);
			$(this).children('i').css('lineHeight',$(this).parent('h4').height() + 'px');
		};
	});
	
	//侧边栏缩进
	$(".nav-control,.nav-control i").click(function(){
		var $this = $(this),
			_parents = $this.parents(".sub-nav-outer");
		if (_parents.offset().left == 0) {
			$('.sub-nav-outer').animate({
				'left': '-200px'
			});
			$(".icon-arrow-left").removeClass('icon-arrow-left').addClass('icon-arrow-right');
			$('.main,.main-form-outer,.main-table-outer,.btn-row.fixed').css({'padding-left':'20px'});
			$('.user-datagrid').datagrid('resize', {
				height:'100%',
				width:'100%'
			});
			$(".tab-content .tab-table-inner,.relation-search").css("left","20px");
			//highcharts宽高
			if($(".highcharts").length > 0){
				highchartsAuto();
			};
		}else{
			$('.sub-nav-outer').animate({
				'left': 0
			});
			$(".icon-arrow-right").removeClass('icon-arrow-right').addClass('icon-arrow-left');
			$('.main,.main-form-outer,.main-table-outer,.btn-row.fixed').css({'padding-left':'220px'});
			$('.user-datagrid').datagrid('resize', {
				height:'100%',
				width:'100%'
			});
			$(".tab-content .tab-table-inner,.relation-search").css("left","220px");
			//highcharts宽高
			if($(".highcharts").length > 0){
				highchartsAuto();
			};
		};
		$('.user-datagrid').datagrid('resize', {
			height:'100%',
			width:'100%'
		});
	});
	//highcharts宽高
	function highchartsAuto(){
		$(".highcharts").each(function(){
			var $this =$(this);
			var $id = $this.attr("id");
			var height = $this.parent("div").height() - 10; //获取父级高度
			var width= $this.parent("div").width() - 10; //获取父级宽度
			
			//动态修改大小
			var hCharts = $("#" + $id).highcharts();
			hCharts.setSize(width, height);
		})
	};
	
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
	//home page中icon的行高
	homeIcon();
	
	//btn-row fixed滚动条控制
	if ($('.main-form-outer .btn-row.fixed').length <= 0) {
		$('.main-form-outer').css({'paddingBottom':'0'});
	};
	
	//在上传按钮上添加一个按钮
    /*$(".uploadify-button").prepend('<i class="fi fi-upload"></i>')*/
});

$(window).resize(function(){
	//top位置变化
	marginTop();
	if($(".have-search .tree-and-table-inner").length > 0){
		boxTop('.tree-and-table-inner');
	};
	//头部用户信息下拉
	slideHide();
	//home page中icon的行高
	homeIcon();
})