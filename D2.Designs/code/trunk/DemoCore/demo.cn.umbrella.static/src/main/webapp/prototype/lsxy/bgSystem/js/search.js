;;
$(function(){
	//头部搜索切换
		var index = $('#search-bd li.selected').index();
		$('.search-hd .pholder').eq(index).show().siblings('.pholder').hide(0);
		
		$('.search-hd .search-input').on('input propertychange',function(){
			var val = $(this).val();
			console.log(val.length);
			if(val.length > 0){
				$('.search-hd .pholder').hide(0);
			}else{
				index = $('#search-bd li.selected').index();
				$('.search-hd .pholder').eq(index).show().siblings('.pholder').hide(0);
			}
		})
		
		$('.search-bd li').click(function(){
			var index = $(this).index();
			console.log(index)
			/*if(index == 1){
//				$(".index-search .search-hd").css({"width":"670px"});
//				$(".index-search .search-input").css({"width":"544px"});
				
				$(".btn-search").css({
					'backgroundColor':'#ccc',
					'cursor':'default'
				});
				$(".btn-search").mouseleave(function(){
					$(".btn-search").css({
						'backgroundColor':'#ccc'
					});
				});
				$(".btn-search").mouseover(function(){
					$(".btn-search").css({
						'backgroundColor':'#ccc',
					});
				});
				$(".personal-btn").show();
				$('.search-hd .search-input').eq(index).prop('readonly',true);
			}else{
//				$(".index-search .search-hd").css({"width":"800px"});
//				$(".index-search .search-input").css({"width":"674px"});
				$(".btn-search").css({
					'backgroundColor':'#009ce7',
					'cursor':'pointer'
				});
				$(".btn-search").mouseleave(function(){
					$(".btn-search").css({
						'backgroundColor':'#009ce7'
					});
				});
				$(".btn-search").mouseover(function(){
					$(".btn-search").css({
						'backgroundColor':'#2ab4f7',
					});
				});
				$(".personal-btn").hide();
			};*/
			$('.search-hd .pholder').eq(index).show().siblings('.pholder').hide(0);
			$('.search-hd .search-input').eq(index).show().siblings('.search-input').hide(0);
			$(this).addClass('selected').siblings().removeClass('selected');
			$('.search-hd .search-input').val('');
		});
});
