;;
$(function(){
	
	//新增分类
	$(document).on('click','.add-category',function(){
		var $tableLen = $('.early-warning-box .editable-table').length;
		$.GD.confirm({
			title: '请输入预警名称',
			msg: '<input type="text" id="categoryName">',
			oktext: '提交',
			canceltext: '取消',
			fixed: true,
			ok: function() {
				$('.clone-table').clone().appendTo('.early-warning-box').show().removeClass('clone-table').find('.third-table-body table').attr('id','tableAct' + ($tableLen + 1));
				var cgName = $("#categoryName").val();
				$('#tableAct' + ($tableLen + 1)).parents('.editable-table').find('.category')
					.attr('readonly','readonly').addClass('read-only').val(cgName)
					.siblings('.btn-group').find('.category-edit').show().siblings(':text').hide();
				/*$('#tableAct' + ($tableLen + 1)).parents('.editable-table').find(".cate-hidden-val").val(rslt.id);
				 * //input type='hidden'*/
				tableNameList($('#tableAct' + ($tableLen + 1)))
			}
		});
	});
	
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
				return true;
			}else{
				return false;							//如果被删除的行对象不存在,则删除失败.返回false
			}
		}else{
			var r = table.insertRow(num - 1),   			//在指定位置创建行对象
				i = 0,
				len = tr.length;						//待插入的数据长度
			for(;i < len;i++){	//遍历待插入数据
				r.insertCell(i).innerHTML = tr[i];		//待插入新单元格数据
			}
			return true;								//新增成功返回true
		}
	};
	var elem=[];
	//按钮隐藏
	$(".category-ok,.category-edit-cancel").hide();
	//页面加载显示
	var selTable=[
			{'firstTotal': 10 ,'secondTotal': 22 ,'thirdTotal': 3,'categoryName':'分类一','tables':[
				{'tableName':'中国电信：自然人欠费信息表','first': 4,'second': 10,'third':8},{'tableName':'公用集团：自然人欠费信息表','first': 11,'second': 0,'third':9},{'tableName':'管道燃气公司：自然人欠费信息表','first': 1,'second': 1,'third':2}
				]
		},
		{'firstTotal': 19,'secondTotal': 31 ,'thirdTotal': 0,'categoryName':'分类二','tables':[
				{'tableName':'中国电信：自然人欠费信息表2','first': 0,'second': 10,'third':2},{'tableName':'公用集团：自然人欠费信息表2','first': 11,'second': 0,'third':3}
				]
			}
		];
	var inputHtml = '<div class="btn-group">'
					+'<span class="table-fi fi-edit table-edit"></span>'
					+'<span class="table-fi fi-delete table-delete"></span>'
					+'</div>';
	var _len = selTable.length;
	//添加数据
	for(var q = 0;q < _len;q++){
		var _table = selTable[q]['tables'],
			_tableLen = _table.length;
		var existingTable =$(".early-warning-box .editable-table").length;
		var tableID = 'tableAct' + (existingTable + 1);
		var $tableName ='_tableAct' + (existingTable + 1);
		//复制
		$('.clone-table').clone().appendTo('.early-warning-box').show().removeClass('clone-table').find('.third-table-body table').attr('id',tableID);
		//定义table相应变量
		elem[q] = document.getElementById("tableAct" + (q + 1));
		//相应table下的tr数量
		var $tr = $('#' + tableID).find('.third-table-body tr');
		var trLength = $tr.length;
		//从selTable里获取分类名,预警合计值
		var _firstT = selTable[q].firstTotal,
			_secondT = selTable[q].secondTotal,
			_thirdT = selTable[q].thirdTotal,
			_cateName = selTable[q].categoryName;
		$('#' + tableID).parents('.editable-table').find('.category').attr('readonly','readonly').addClass('read-only').val(_cateName).before('<input type="hidden" class="cate-hidden-val">');
		$('#' + tableID).parents('.editable-table').find('.first-total input').val(_firstT);
		$('#' + tableID).parents('.editable-table').find('.second-total input').val(_secondT);
		$('#' + tableID).parents('.editable-table').find('.third-total input').val(_thirdT);
		//遍历获取"表名"和对应值并设置
		for (var x = 0;x < _tableLen;x++) {
			var _tableName = _table[x]['tableName'],
				_first = _table[x]['first'],
				_second = _table[x]['second'],
				_third = _table[x]['third'];
			var m = 0;
			var num =existingTable.toString();
			trAct(elem[num],x+1,[
				'<input type="checkbox" class="tr-check" id="tableActCheck' + tableID + (x + 1) +'"/>' 
				+ '<span class="id">' + (x + 1) + '</span>',
				'<input type="hidden" class="table-hidden"><input type="text" class="read-only" readonly value="'+ _tableName +'"/>' + inputHtml,
				'<input type="text" id="tableActFirstTxtBox'+ (x + 1) +'" value="'+ _first +'"/>',
				'<input type="text" id="tableActSecondTxtBox'+ (x + 1) +'" value="'+ _second +'"/>',
				'<input type="text" id="tableActThirdTxtBox'+ (x + 1) +'" value="'+ _third +'"/>',
			]);
			m++;
			$("#"+tableID +" tr").eq(x).find('.btn-group').parent('td').addClass('editable-table-title');
		}
		idNum(tableID);
	};

	/*动态插入和删除单元行*/
	//删除
	$(document).on('click','.fi-delete',function(){
		var $this = $(this);
		//删除行
		if($this.hasClass('table-delete')){
			var $thisId = $this.parents('table').attr('id');
			var $thisIndex = $this.parent('div').parent('td').parent('tr').index();
			var endStrId = $thisId.charAt($thisId.length - 1); 
			$.GD.confirm({
				msg: '确认删除行？',
				oktext: '删除',
				canceltext: '取消',
				fixed: true,
				ok: function() {
					trAct(elem[endStrId],$thisIndex);
					idNum($thisId);
					$('#' + $thisId).find('tr').each(function(){
						$(this).find('.id').html($(this).index() + 1 );
					});
					
					if($('#' + $thisId).find('tr').length <= 0){
						$('#' + $thisId).parents('.editable-table').find('.third-table-head .mc-cancel').removeClass('mc-cancel').addClass('mc-sel').text('多选');
						$('#' + $thisId).parents('.editable-table').find('.third-table-head .mc-sel').siblings().remove();
						$('#' + $thisId).parents('.editable-table').find('.third-table-head .multiple-choice').hide();
			    	}else{
			    		$('#' + $thisId).parents('.editable-table').find('.third-table-head').find(".multiple-choice").show();
			    	};
				}
			});
			
		}else{
			//删除整个
			$.GD.confirm({
				msg: '确认删除？',
				oktext: '删除',
				canceltext: '取消',
				fixed: true,
				ok: function() {
					$this.parents('.editable-table').remove();
				}
			});
		};
	});
	
	//多选启用
    $(document).on('click','.mc-sel',function(){
    	var $this = $(this);
    	$this.removeClass('mc-sel').addClass('mc-cancel').text('取消');
    	$this.after('<h1 class="color-blue mc-del">删除</h1>');
    	$this.after('<h1 class="color-blue mc-sel-all">全选</h1>');
    	$this.parents('tr').find('input[type="text"]').unbind('click');
    	$this.parents('.editable-table').find('.tr-check').show();
    	$this.parents('.editable-table').find('.third-table-body').addClass('select-table');
    	$this.parents('.editable-table').find('.third-table-body .btn-group').hide();
    	$this.parents('.editable-table').find('.third-table-body tr').each(function(){
			$(this).css({
				'background' : 'none',
			}).removeClass('mult-sel-del');
			$(this).find('input[type=checkbox]').prop('checked',false);
		});
    	if($(this).parents('.editable-table').find('tr').length <= 0){
    		$(".multiple-choice").hide();
    	}else{
    		$(".multiple-choice").show();
    	};
    });
    
    //多选取消
    $(document).on('click','.mc-cancel',function(){
    	$(this).removeClass('mc-cancel').addClass('mc-sel').text('多选');
    	$(this).siblings('h1').remove();
    	$(this).parents('.editable-table').find('.tr-check').hide();
    	$(this).parents('.editable-table').find('.third-table-body .btn-group').show();
    	$(this).parents('.editable-table').find('.third-table-body tr').css('background','none');
    });
	
	//多行选择
	$(document).on('click','.select-table tr',function(){
		if($(this).hasClass('mult-sel-del') == false){
			$(this).css({
				'backgroundColor' : '#f1f9fa',
			}).addClass('mult-sel-del');
			$(this).find('input[type=checkbox]').prop('checked',true);
		}else{
			
			$(this).css({
				'background' : 'none',
			}).removeClass('mult-sel-del');
			$(this).find('input[type=checkbox]').prop('checked',false);
			$(this).parents(".select-table").removeClass('select-table');
		};
	});
	
	//全选
	$(document).on("click",".mc-sel-all",function(){
		var $this = $(this);
		var ulHtml = "";
		var liLen,thisYear,thisMonth,selectYM,sPosition,subStr;
		$this.parents('.editable-table').find('.third-table-body tr').each(function(){
			$(this).css({
				'backgroundColor' : '#f1f9fa',
			}).addClass('mult-sel-del');
			$(this).find('input[type=checkbox]').prop('checked',true);
		});
	});
	
	//多行删除
	$(document).on('click','.mc-del',function(){
		var $tableId = $(this).parents('.second-table').find('.third-table-body table').attr('id');
		var $thisIndex = $(this).parent('div').parent('td').parent('tr').index();
		var endStrId = $tableId.charAt($tableId.length - 1); 
		$.GD.confirm({
			msg: '确认删除所选行？',
			oktext: '删除',
			canceltext: '取消',
			fixed: true,
			ok: function() {
				$('#' + $tableId).find('.mult-sel-del').each(function(){
					trAct(elem[endStrId],$(this).index());
				});
				$('#' + $tableId).find('tr').each(function(){
					$(this).find('.id').html($(this).index() + 1 );
				});
				idNum($tableId);
		    	if($('#' + $tableId).find('tr').length <= 0){
					$('#' + $tableId).parents('.editable-table').find('.third-table-head .mc-cancel').removeClass('mc-cancel').addClass('mc-sel').text('多选');
					$('#' + $tableId).parents('.editable-table').find('.third-table-head .mc-sel').siblings().remove();
					$('#' + $tableId).parents('.editable-table').find('.third-table-head .multiple-choice').hide();
		    	}else{
		    		$('#' + $tableId).parents('.editable-table').find('.third-table-head').find(".multiple-choice").show();
		    	};
			}
		});
	});
	
	//新增
	$(document).on('click','.fi-add',function(){
		var $this = $(this);
		var $thisPare = $(this).parents('.editable-table');
		tableNameList($this);
		if($thisPare.find('.third-table-body tr').length == 0){
			$thisPare.find('.third-table-head .mc-cancel').removeClass('mc-cancel').addClass('mc-sel').text('多选');
			$thisPare.find('.third-table-head .mc-sel').siblings().remove();
			$thisPare.find('.third-table-head .multiple-choice').hide();
    	}else{
    		$thisPare.find('.third-table-head').find(".multiple-choice").show();
    	};
	});
	/*可以使用js中的rows访问表格中的每一行
     *利用deleteRow()删除指定的行
     * 创建行的基本逻辑是首先创建行的引用对象"r = table.insertRow(num)",然后再填充内容"r.insertCell(i).innerHTML = tr[i]"
     */
    $('.edittable-table-title input').attr('readonly','readonly');
    //分类---保存
    $(document).on('click','.category-ok',function(){
    	var $this = $(this);
    	var $thisTd = $this.parents('tr');
    	var $len = $this.parent('.btn-group').siblings('input').val().length;
    	var newVal = $this.parent('.btn-group').siblings('input').val();
    	if(newVal != oldVal){
    		$this.parent('.btn-group').siblings('input').attr('readonly','readonly').addClass('read-only').val(newVal);
			$this.hide().siblings('.category-edit-cancel').hide().siblings('.category-edit').show();
    	}else{
    		$this.parent('.btn-group').siblings('input').attr('readonly','readonly').addClass('read-only').val(oldVal);
			$this.hide().siblings('.category-edit-cancel').hide().siblings('.category-edit').show();
    	}
    });
    
    //分类---编辑---取消
    $(document).on('click','.category-edit-cancel',function(){
    	var $thisBtn = $(this).parent('.btn-group');
    	$thisBtn.siblings('input[type=text]').attr('readonly','readonly').addClass('read-only').val(oldVal);
		$(this).hide().siblings('.category-ok').hide().siblings('.category-edit').show();
    	$thisBtn.siblings(':text').attr('readonly','readonly').addClass('read-only');
    });
    
    //表---编辑
    $(document).on('click','.table-edit',function(){
    	var $thisBtn = $(this).parent('.btn-group');
    	var $thisPare = $(this).parents('.editable-table');
		var nLen,rLen;
    	$thisPare.find('.third-table-body tr').each(function(){
    		rLen = 0;
    		if($(this).find('.editable-table-title input.read-only').length == 1){
    			rLen = 0;nLen = 0;
    			if($(this).find('.editable-table-title input[type=text]').val().length == 0){
	    			nLen = 0;
	    		}else{
	    			nLen++;
	    		};
	    		return true
    		}else{
    			rLen++;
    			return false
    		};
    	});
    	if(nLen != 0 && rLen == 0){
//	    	alert($thisBtn.siblings('input[type="hidden"]').val());//获取表名 hidden的值
	    	tableNameList($(this));
    	}
    });
    
    //分类---编辑
    var oldVal;
    $(document).on('click','.category-edit',function(){
    	var $thisBtn = $(this).parent('.btn-group');
    	oldVal = $thisBtn.parents('tr').find('.category').val();
    	$thisBtn.parents('tr').find('.category').removeAttr('readonly').removeClass('read-only');
		$thisBtn.find('.category-ok,.category-edit-cancel').show().siblings('.category-edit').hide();
//		alert($thisBtn.siblings('input[type="hidden"]').val());//获取分类名 hidden的值
    });
    
    //编号
    function idNum(idStr){
    	var tableID = $('#' + idStr);
		$(tableID).find('tr').each(function(index){
			$(this).attr({
				'id':idStr + 'tableTrNum' + (index + 1)
			});
		})
    };
    
	function tableNameList(current){
		var $this = current;
		var htmlContent = '<div class="main-table-outer dialog-table-outer">';
			htmlContent += '<table id="dialogDataGrid" class="user-datagrid easyui-datagrid" style="height:100%;width:100%;"></table>';
			htmlContent += '</div>';
			$.GD.dialog({
				title: "选择数据来源部门",
				width: 500,
				height: 400,
				content:htmlContent,
				oktext:"确定",
				ok: function () {
					var rows = $('#dialogDataGrid').datagrid('getSelections');
					//定义table相应变量
					var _tableLen = $('.early-warning-box .third-table-body').length;
					for(var k = 1;k <= _tableLen;k++){
						elem[k] = document.getElementById("tableAct" + k);
					};
					// 添加选中的部门，并修改input中的值
		    		if(rows.length > 0) {
		    			$.each(rows, function(i, v){
		    				var $thisId = $this.parents('.editable-table').find('.third-table-body table').attr('id');
							var inputHtml = '<div class="btn-group">'
											+'<span class="table-fi fi-edit table-edit"></span>'
											+'<span class="table-fi fi-delete table-delete"></span>'
											+'</div>';
							var endStrId = $thisId.charAt($thisId.length - 1); 
							var $tableName ='_tableAct' + endStrId;
			    			var $tr = $this.parents('.editable-table').find('.third-table-body tr');
							var trLength = $tr.length;
		    				var selectName = v.tablename;
							if($this.attr('class') == 'table-fi fi-edit table-edit'){
			    				var b = 0;
								if (rows){
			    					for(var p = 0;p < trLength;p++){
			    						var $val = $tr.eq(p).find('input[type=text]').val();
				    					if(selectName == $val){
				    						b +=1 ;
				    					}
				    				};
				    				if(b == 0 ){
										$this.parent('.btn-group').siblings('input').val(v.tablename);
									}else{
										$.GD.confirm({
											title: '提示',
											msg: '表名已存在',
											canceltext: '确定',
											fixed: true,
										});
									}
								};
							}else if(($this.attr('class') == 'table-fi fi-add') && (rows.length == 1)){
								$.GD.confirm({
									title: '提示',
									msg: '表名已存在',
									canceltext: '取消',
									fixed: true,
								});
							}else{
								var n = 1,m,s;
								m = n + 1;
								s = m + 1;
			    				var u = 0;
								if (rows){
									if(trLength >= 1){
				    					for(var p = 0;p < trLength;p++){
				    						var $val = $tr.eq(p).find('input[type=text]').val();
					    					if(selectName == $val){
					    						u +=1 ;
					    					}
					    				};
					    				if(u == 0 ){
											trAct(elem[endStrId],trLength+1,[
												'<input type="checkbox" class="tr-check" id="tableActCheck' + $thisId + (trLength+1) +'"/>' + '<span class="id">' + (trLength+1) + '</span>',
												'<input type="hidden" class="table-hidden"><input type="text" class="read-only" readonly value="'+ selectName +'"/>' + inputHtml,
												'<input type="text" id="tableActFirstTxtBox'+  (trLength+1) +'"/>',
												'<input type="text" id="tableActSecondTxtBox'+  (trLength+1) +'"/>',
												'<input type="text" id="tableActThirdTxtBox'+  (trLength+1) +'"/>',
												
											]);
										}
				    				}else{
				    					trAct(elem[endStrId],trLength+1,[
												'<input type="checkbox" class="tr-check" id="tableActCheck' + $thisId + (trLength+1) +'"/>' + '<span class="id">' + (trLength+1) + '</span>',
												'<input type="hidden" class="table-hidden"><input type="text" class="read-only" readonly value="'+ v.tablename +'"/>' + inputHtml,
												'<input type="text" id="tableActFirstTxtBox'+  (trLength+1) +'"/>',
												'<input type="text" id="tableActSecondTxtBox'+  (trLength+1) +'"/>',
												'<input type="text" id="tableActThirdTxtBox'+  (trLength+1) +'"/>',
												
											]);
				    				}
								};
								$("#"+$thisId +" tr").eq(trLength).find('.btn-group').parent('td').addClass('editable-table-title');
								idNum($thisId);
								n = s + 1;
								if(trLength == 0){
									$this.parents('.editable-table').find('.third-table-head .mc-cancel').removeClass('mc-cancel').addClass('mc-sel').text('多选');
									$this.parents('.editable-table').find('.third-table-head .mc-sel').siblings().remove();
									$this.parents('.editable-table').find('.third-table-head .multiple-choice').hide();
						    	}else{
						    		$this.parents('.editable-table').find('.third-table-head').find(".multiple-choice").show();
						    	};
							};
		    			});
		    			$this.parents('.editable-table').find(".category").before('<input type="hidden" class="cate-hidden-val">');//获取预警名称id
		    		};
			    },
			    cancelValue: '取消',
			    cancel: function () {},
				onclose: function(params) {
					if(params) {
						$.GD.msg(params);
					}
				}
			});
			// 列表==================Start============================================
			$('#dialogDataGrid').datagrid({
				url: "js/table_data2.json",
				columns: [
					[
						{field: 'tablename',title: '表名',width: 100},
					]
				],
				frozenColumns: [
					[
						{field: 'ck',checkbox: true}, // 复选框列
					]
				],
				fitColumns: true,//True 就会自动扩大或缩小列的尺寸以适应表格的宽度并且防止水平滚动。
				striped:true,//斑马线
				method: "get",
				pageSize: 10,
				rownumbers: true,
				pagination: true,
				collapsible: true,
	
				singleSelect: false, // 复选
				selectOnCheck: true,
				checkOnSelect: true,
	
				sortName: 'tablename',
				sortOrder: 'desc',
				queryParams: {},// 查询条件
			});
			if($this.attr('class') == 'table-fi fi-edit table-edit'){
				$('.user-datagrid').datagrid({singleSelect: true});
			}
			// 列表==================End=============================================
			$('.user-datagrid').datagrid('resize', {
				height:'100%',
				width:'100%'
			});
	}
});
