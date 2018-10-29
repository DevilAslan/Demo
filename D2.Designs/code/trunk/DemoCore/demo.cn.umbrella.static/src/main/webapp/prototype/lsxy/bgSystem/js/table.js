;
$(function(){
	/*如果tr存在则是新增操作,否则为删除操作
	 * 当为删除操作时,参数---table为表格的table对象,num为被删除的单元行序列数
	 * 
	 * 当为新增操作时,参数---table为表格的table对象,num为新增单元行的位置,tr为新增行的字符串型数组
	 * 
	 */
	var trAct = function(table,num,tr){
		if(!tr){										//如果num不存在则执行删除操作
			var _num = table.rows[num];
			if(_num){					//如果被删除的行对象存在,则删除.返回true
				var $rowIndex = _num.rowIndex;
				table.deleteRow($rowIndex);					//用JavaScript的原生函数删除行
				console.log('true  ' +'_num' + _num + '  _num.rowIndex  ' +  _num.rowIndex);
				return true;
			}else{
				console.log('false  ');
				return false;							//如果被删除的行对象不存在,则删除失败.返回false
			}
		}else{
			var r = table.insertRow(num - 1),   			//在指定位置创建行对象
				i = 0,
				len = tr.length;						//待插入的数据长度
			for(;i < len;i++){							//遍历待插入数据
				r.insertCell(i).innerHTML = tr[i];		//待插入新单元格数据
			}
			return true;								//新增成功返回true
		}
	}
	
	/*动态插入和删除单元行*/
	var _tableAct1 = document.getElementById("tableAct1"),
		_tableAct2 = document.getElementById("tableAct2"),
		_tableAct3 = document.getElementById("tableAct3"),
		_tableAct4 = document.getElementById("tableAct4");
	
	//删除
	$(document).on('click','.delete-row',function(){
		var $thisId = $(this).parents('table').attr('id');
		var $thisIndex = $(this).parent('div').parent('td').parent('tr').index();
		var $trLength = $(this).parents('table[id]').find('tr').length;
		var endStrId = $thisId.charAt($thisId.length - 1); 
		if ($thisIndex == ($trLength - 1)) {
			$(this).parents('tr').prev('tr').find('.btn-group').append('<input type="button" class="color-blue add-row" value="新增" />');
		};
		switch (parseInt(endStrId)) 
		{ 
			case 1:
				trAct(_tableAct1,$thisIndex);
			break; 
			case 2:
				trAct(_tableAct2,$thisIndex);
			break; 
			case 3:
				trAct(_tableAct3,$thisIndex);
			break;
			case 4:
				trAct(_tableAct4,$thisIndex);
			break; 
		};
	});
	
	//新增
	var n = 1,m,s;
	$(document).on('click','.add-row',function(){
		m = n + 1;
		s = m + 1;
		var $thisId = $(this).parents('table').attr('id');
		var trLength = $(this).parents('table[id="' + $thisId +'"]').find('tr').length;
		var inputHtml = '<div class="btn-group">'
						+'<input type="button" class="color-gray unusable" value="保存" />' 
						+'<input type="button" class="color-blue delete-row" value="取消" />'
						+'</div>';
		var endStrId = $thisId.charAt($thisId.length - 1); 
		switch (parseInt(endStrId)) 
		{ 
			case 1:
				trAct(_tableAct1,trLength+1,[
					'<input type="text" class="criteria-text1"/>',
					'<input type="text" class="criteria-text" id="tableAct1TxtBox'+ n +'"/>',
					'<input type="text" class="percentage-text" id="tableAct1TxtBox'+ m +'"/>',
					'<input type="text" class="score-text" id="tableAct1TxtBox'+ s +'"/>',
					'<textarea autoHeight="true"></textarea>',
					inputHtml
				]);
			break; 
			case 2:
				trAct(_tableAct2,trLength+1,[
					'<input type="text" class="criteria-text1"/>',
					'<input type="text" class="criteria-text" id="tableAct2TxtBox'+ n +'"/>',
					'<input type="text" class="percentage-text" id="tableAct2TxtBox'+ m +'"/>',
					'<input type="text" class="score-text" id="tableAct2TxtBox'+ s +'"/>',
					'<textarea autoHeight="true"></textarea>',
					inputHtml
				]);
			break; 
			case 3:
				trAct(_tableAct3,trLength+1,[
					'<input type="text" class="criteria-text1"/>',
					'<input type="text" class="criteria-text" id="tableAct3TxtBox'+ n +'"/>',
					'<input type="text" class="percentage-text" id="tableAct3TxtBox'+ m +'"/>',
					'<input type="text" class="score-text" id="tableAct3TxtBox'+ s +'"/>',
					'<textarea autoHeight="true"></textarea>',
					inputHtml
				]);
			break;
			case 4:
				trAct(_tableAct4,trLength+1,[
					'<input type="text" class="criteria-text1"/>',
					'<input type="text" class="criteria-text" id="tableAct4TxtBox'+ n +'"/>',
					'<input type="text" class="percentage-text" id="tableAct4TxtBox'+ m +'"/>',
					'<input type="text" class="score-text" id="tableAct4TxtBox'+ s +'"/>',
					'<textarea autoHeight="true"></textarea>',
					inputHtml
				]);
			break; 
		};
		$("#txtBox"+ n).focus();
		n = s + 1;
//		$(this).hide();
		$(this).remove();
		$('textarea[autoHeight]').autoHeight();
	});
    /*可以使用js中的rows访问表格中的每一行
     *利用deleteRow()删除指定的行
     * 创建行的基本逻辑是首先创建行的引用对象"r = table.insertRow(num)",然后再填充内容"r.insertCell(i).innerHTML = tr[i]"
     */
    
    //保存
    $(document).on('click','.save-row',function(){
    	var $this = $(this);
    	var $thisTd = $this.parents('td').siblings('td');
    	
    	$thisTd.find(':text').attr('readonly','readonly').addClass('read-only');
    	$thisTd.find('textarea').attr('readonly','readonly').addClass('read-only');
    	
    	var $thisIndex = $(this).parents('tr').index();
		var $trLength = $(this).parents('table[id]').find('tr').length;
		if ($thisIndex == ($trLength - 1)) {
			$this.parents('.btn-group').html(
	    		'<input type="button" class="color-blue edit-row" value="编辑" />'
	    		+'<input type="button" class="color-blue delete-row" value="删除" />'
				+'<input type="button" class="color-blue add-row" value="新增" />'
	    	);	
		}else{
			$this.parents('.btn-group').html(
	    		'<input type="button" class="color-blue edit-row" value="编辑" />'
	    		+'<input type="button" class="color-blue delete-row" value="删除" />'
	    	);	
		};
		
		//两个text相同的合并
		if($thisTd.find('.criteria-text').val() == $thisTd.find('.criteria-text1').val()){
			alert($thisTd.find('.criteria-text').val() == $thisTd.find('.criteria-text1').val());
			$thisTd.find('.criteria-text1').parent('td').attr('colspan','2');
			$thisTd.find('.criteria-text').parent('td').hide();
		};
		
    	if($thisTd.find('.percentage-text').val() == ''){
    		$thisTd.find('.percentage-text').val('0%');
    	};
    	if($thisTd.find('.score-text').val() == ''){
    		$thisTd.find('.score-text').val('0');
    	};
    });
    
    //"保存"按钮可用
    var _val = 0;
    $(document).on('change',':text,textarea',function(){
    	
    	if($(this).is('.criteria-text')){
    		if($(this).val() != ''){
	    		$('.unusable').removeClass('color-gray unusable').addClass('color-blue save-row');
	    	};
    	};
	 	
	 	if($(this).is('.percentage-text')){
	 		var val = $(this).val();
			var endStr = val.charAt(val.length - 1);
			if (endStr == '%') {
				$(this).val(val);
			} else{
				$(this).val(val + '%');
			}
	 	};
	});
    
    //取消
    $(document).on('click','.cancel-row',function(){
    	/*$('.add-row').show();*/
    	var $thisBtn = $(this).parent('.btn-group');
    	$thisBtn.html(
    		'<input type="button" class="color-blue edit-row" value="编辑" />'
    		+'<input type="button" class="color-blue delete-row" value="删除" />'
    	);
    	
    	$thisBtn.parent('td').parent('tr').find(':text').attr('readonly','readonly').addClass('read-only');
		$thisBtn.parent('td').parent('tr').find('textarea').attr('readonly','readonly').addClass('read-only');
    });
    
   //只能输入数字
	function keyNum() {
	   	var keyCode = event.keyCode;
	   	if((keyCode >= 48 && keyCode <= 57)) {
	   		event.returnValue = true;
	   	} else {
	   		event.returnValue = false;
	   	}
	};
	$(document).on('keypress','.score-text,.percentage-text',function(){
		keyNum();
	});
	
	//占比
	per();
	function per(){
		$('.percentage-text').each(function(){
			var val = $(this).val();
			$(this).val(val + '%');
		});
	};
	
    var $readOnly = $('.rating-form-body input[type="text"],.rating-form-body textarea');
  	$readOnly.attr('readonly','readonly').addClass('read-only');
    
    $(document).on('click','.edit-row',function(){
    	var $thisBtn = $(this).parent('.btn-group');
    	$thisBtn.parent('td').parent('tr').find(':text').removeAttr('readonly').removeClass('read-only');
    	$thisBtn.parent('td').parent('tr').find(':text').eq(0).focus();
		$thisBtn.parent('td').parent('tr').find('textarea').removeAttr('readonly').removeClass('read-only');
		
		$thisBtn.html(
    		'<input type="button" class="color-blue save-row" value="保存" />'
    		+'<input type="button" class="color-blue cancel-row" value="取消" />'
    	);	
    });
    
    //textarea自适应文字
	$.fn.autoHeight = function(){
		function autoHeight(elem){
			elem.style.height = 'auto';
			elem.scrollTop = 0; //防抖动
			elem.style.height = elem.scrollHeight + 'px';
		};
		
		this.each(function(){
			autoHeight(this);
			$(this).on('keyup', function(){
				autoHeight(this);
			});
		});
	};
	$('textarea[autoHeight]').autoHeight();
});
	//合并行/列
	//函数说明：合并指定表格（表格id为table_id）指定列（列数为table_colnum）的相同文本的相邻单元格
	//参数说明：table_id 为需要进行合并单元格的表格的id。如在HTMl中指定表格 id="table1" ，此参数应为 #table1
	//参数说明：table_colnum 为需要合并单元格的所在列。为数字，从最左边第一列为1开始算起。
    function table_rowspan(table_id, table_colnum) {
        table_firsttd = "";
        table_currenttd = "";
        table_SpanNum = 0;
        colnum_Obj = $(table_id + " tr td:nth-child(" + table_colnum + ")");
        colnum_Obj.each(function (i) {
            if (i == 0) {
                table_firsttd = $(this);
                table_SpanNum = 1;
            } else {
                table_currenttd = $(this);
                if (table_firsttd.children('*').val() == table_currenttd.children('*').val()) {
                    table_SpanNum++;
                    table_currenttd.hide(); //remove();
                    table_firsttd.attr("rowSpan", table_SpanNum);
                }else {
                    table_firsttd = $(this);
                    table_SpanNum = 1;
                }
            }
        });
    };
     
    //函数说明：合并指定表格（表格id为table_id）指定行（行数为table_rownum）的相同文本的相邻单元格
    //参数说明：table_id 为需要进行合并单元格的表格id。如在HTMl中指定表格 id="table1" ，此参数应为 #table1
    //参数说明：table_rownum 为需要合并单元格的所在行。其参数形式请参考jQuery中nth-child的参数。
    //          如果为数字，则从最左边第一行为1开始算起。
    //          "even" 表示偶数行
    //          "odd" 表示奇数行
    //          "3n+1" 表示的行数为1、4、7、10.......
    //参数说明：table_maxcolnum 为指定行中单元格对应的最大列数，列数大于这个数值的单元格将不进行比较合并。
    //          此参数可以为空，为空则指定行的所有单元格要进行比较合并。
    function table_colspan(table_id, table_rownum, table_maxcolnum) {
        if (table_maxcolnum == void 0) {
            table_maxcolnum = 0;
        }
        table_firsttd = "";
        table_currenttd = "";
        table_SpanNum = 0;
        $(table_id + " tr:nth-child(" + table_rownum + ")").each(function (i) {
            row_Obj = $(this).children();
            row_Obj.each(function (i) {
                if (i == 0) {
                    table_firsttd = $(this);
                    table_SpanNum = 1;
                } else if ((table_maxcolnum > 0) && (i > table_maxcolnum)) {
                    return "";
                } else {
                    table_currenttd = $(this);
                    if (table_firsttd.text() == table_currenttd.text()) {
                        table_SpanNum++;
                        table_currenttd.hide(); //remove();
                        table_firsttd.attr("colSpan", table_SpanNum);
                    } else {
                        table_firsttd = $(this);
                        table_SpanNum = 1;
                    }
               }
            });
        });
    };