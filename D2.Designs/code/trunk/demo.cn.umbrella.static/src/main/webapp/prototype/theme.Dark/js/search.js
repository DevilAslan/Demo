;;
$(function(){
	//搜索切换
		//自然人 授权查询css控制
		function hasZrr(obj){
			if(obj.hasClass('zrr') && $(".search-bd li").hasClass('selected')){
				$(".search-layout2 .search-hd").css('borderColor','#ccc');
				$('.search-hd input').attr('disabled',true).siblings('.search-btn-bg').css('background','#ccc');
				$("#jiansouBtn").hide();
				$("#shouquanBtn").show();
			} else {
				$(".search-layout2 .search-hd").css('borderColor','#fca700');
				$('.search-hd input').attr('disabled',false).siblings('.search-btn-bg').css('background','#fca700');
				$("#jiansouBtn").show();
				$("#shouquanBtn").hide();
			}
		};
		//自然人 授权查询css控制
		hasZrr($(".search-bd li"));
		//选择后改变placeholder
		var selectedP = $(".search-bd .selected").data('placeholder');
		$("#placeholder").text(selectedP);
		$(".search-bd li").click(function(){
			var $this = $(this);
			$this.addClass('selected').siblings('li').removeClass('selected');
			selectedP = $this.data('placeholder');
			$this.parents('.relation-search').find("#placeholder").text(selectedP);
			if ('personalID' == $this.attr("id")) {
				$("#jiansouBtn").hide();
			} else {
				$("#jiansouBtn").show();
			};
			//自然人 授权查询css控制
			hasZrr($this);
		});
		//input的placeholder
		if($('.search-hd').length > 0){
			$('.search-hd input').each(function(){
				var $this = $(this),val = $(this).val();
				if(val.length > 0){
					$this.siblings('.pholder').hide(0);
				}
			})
		};
		$('.search-hd input').on('input propertychange',function(){
			var $this = $(this),val = $(this).val();
			if(val.length > 0){
				$this.siblings('.pholder').hide(0);
			}else{
				$this.siblings('.pholder').show(0);
			}
		});
});
