;;
$(function(){
	$(".editable-table2 input[type='text'],.editable-table2 textarea").attr('readonly','readonly');
	var creditOwnerType = $("#creditOwnerType").val();
	var path = $("#path").val();
	var totalData=[];
	/*$.ajax({
		type: "POST",
		async:false,
      	url: path+"warning/getListData.json",
	  	data: {'creditOwnerType':creditOwnerType}, 
      	success: function(result) {
      		if(result.status){// 成功
      			var categories = result.categories;
      			var tables = result.tables;
      			for(var i=0;i<categories.length;i++){
      				var first = categories[i].level1Total;
      				var second = categories[i].level2Total;
      				var third = categories[i].level3Total;
      				var id = categories[i].id;
      				var tablejson = [];
      				if(first==null){
      					first='';
      				}
      				if(second==null){
      					second='';
      				}
      				if(third==null){
      					third='';
      				}
  					for(var j=0;j<tables.length;j++){
  						if(id==tables[j].warningTypeId){
	          				var tfirst = tables[j].level1;
	          				var tsecond = tables[j].level2;
	          				var tthird = tables[j].level3;
	          				if(tfirst==null){
	          					tfirst='';
	          				}
	          				if(tsecond==null){
	          					tsecond='';
	          				}
	          				if(tthird==null){
	          					tthird='';
	          				}
	          				var table ={"tableID":tables[j].id,"tableName":tables[j].tableNameCn,"first":tfirst,
	          						"second":tsecond,"third":tthird};
	          				tablejson.push(table);
  						}
      				}
      				var data = {"firstTotal":first,"secondTotal":second,
      						"thirdTotal":third,"categoryID":categories[i].id,"categoryName":categories[i].warningName,
      						"tables":tablejson};
      				totalData.push(data);
      			}
      		}else{
      			$.GD.msg("获取数据失败！");
      		}
      	}
	});*/
	
	//分类信息html
	var categoryHtml = '<div class="main-table-outer dialog-table-outer"><div class="form-table">'
			 		+	'<table id="categoryBox">'
			 		+	'<tr><th>分类名称</th><td><input type="text" id="dialogCategoryName"></td></tr>'
			 		+	'<tr><th>占比</th><td><input type="text" class="per" id="dialogCategoryPer"></td></tr>'
			 		+	'<tr><th>分值</th><td><input type="text" id="dialogCategoryScore" class="read-only" readonly></td></tr>'
			 		+	'</table>'
					+	'</div></div>';
	//评估项信息html
	var evaluateHtml = '<div class="main-table-outer dialog-table-outer"><div class="form-table">'
			 		+	'<table id="categoryBox">'
			 		+	'<tr><th>评估项</th><td><input type="text" id="evaluateName"</td></tr>'
			 		+	'<tr><th>规则</th><td><textarea rows="5" id="evaluateRole"></textarea></tr>'
			 		+	'<tr><th>占比</th><td><input type="text" class="per" id="evaluatePer"</td></tr>'
			 		+	'<tr><th>分值</th><td><input type="text" id="evaluateScore" class="read-only" readonly></td></tr>'
			 		+	'</table>'
					+	'</div></div>';
	//按钮html
	var inputHtml = '<div class="btn-group">'
					+'<span class="table-fi fi-delete table-delete"></span>'
					+'<span class="table-fi fi-edit table-edit"></span>'
					+'<span class="table-fi fi-generate"></span>'
					+'</div>';
	
	//新增分类
	$(document).on('click','.add-category',function(){
		var $tableLen = $('.early-warning-box .editable-table').length;
		$.GD.dialog({
			title: "分类信息",
			width: 500,
			height: 170,
			content:categoryHtml,
			oktext:"确定",
			ok: function() {
				var nameVal = $("#dialogCategoryName").val(),
					perVal = $("#dialogCategoryPer").val(),
					scoreVal =$("#dialogCategoryScore").val();
				var arrayVal = [nameVal,perVal,scoreVal];
				var infoBox = $('#tableAct' + ($tableLen + 1)).parents('.editable-table').find('.category-info');
				var b = 0;
		    	$('.early-warning-box').find('.editable-table').each(function(i){
		    		var $val = $(this).find('.category-name').val();
					if(arrayVal[0] == $val){
						b +=1 ;
					}
		    	});
				if(b == 0 ){
					$('.clone-table').clone().appendTo('.early-warning-box').show().removeClass('clone-table').find('.third-table-body table').attr('id','tableAct' + ($tableLen + 1));
					infoBox.find('input:text').each(function(index){
						$(this).attr('readonly','readonly').val(arrayVal[index])
						.siblings('.btn-group').find('.category-edit').show().siblings(':text').hide();
					});
					infoBox.find('.score-value').text(scoreVal);
					$('#tableAct' + ($tableLen + 1)).parents('.editable-table').find('.category').before('<input type="hidden" class="cate-hidden-val" id="category'+ ($tableLen + 1) +'"/>')
					tableNameList($('#tableAct' + ($tableLen + 1)))
					/*$.ajax({
						type: "POST",
						async:false,
			          	url: path+"warning/doAdd.json",
					  	data: {'creditOwnerType':creditOwnerType,'warningName':cgName}, 
			          	success: function(result) {
			          		if(result.status){// 成功
			          			var rslt = result.data;
								$('.clone-table').clone().appendTo('.early-warning-box').show().removeClass('clone-table').find('.third-table-body table').attr('id','tableAct' + ($tableLen + 1));
			          			
			          			$('#tableAct' + ($tableLen + 1)).parents('.editable-table').find('.category')
								.attr('readonly','readonly').addClass('read-only').val(cgName)
								.siblings('.btn-group').find('.category-edit').show().siblings(':text').hide();
			          			$('#tableAct' + ($tableLen + 1)).parents('.editable-table').find('.category').before('<input type="hidden" id="'+rslt.id  +'">')
			          			//需要传入预警id
			          			tableNameList($('#tableAct' + ($tableLen + 1)),1,rslt.id);
			          		}else{
			          			$.GD.msg("新增失败！");
			          		}
			          	}
					});*/
				}else{
					$.GD.confirm({
						title: '提示',
						msg: '分类名称重复',
						canceltext: '确定',
						fixed: true,
					});
				}
				
			},
			cancelValue: '取消',
		    cancel: function () {},
			
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
	var selTable=[
			{'percentageTotal': 10 ,'scoreTotal': 22 ,'categoryID': 'category1','categoryName':'基础素质','categoryPer':'30%','categoryScore':300,'tables':[
				{'evaluateID':'category1table1','evaluateName':'姓名','rules': '','percentage': '4%','score':12},
				{'evaluateID':'category1table2','evaluateName':'年龄','rules': '7-17岁10分，18岁到25岁之间15分，25岁到45岁之间30分，45到70岁之间20分，70岁以上15分','percentage': '10%','score':30},
				{'evaluateID':'category1table3','evaluateName':'电话号码','rules': '有则加4分，5年之内未变动的6分，5年以上（含5年）未变动的12分','percentage': '4%','score':12},
				{'evaluateID':'category1table4','evaluateName':'政治面貌','rules': '采集，暂不计分','percentage': '0','score':0}
				]
		},
		{'percentageTotal': 19,'scoreTotal': 31,'categoryID': 'category2','categoryName':'合规度','categoryPer':'50%','categoryScore':500,'tables':[
				{'evaluateID':'category2table1','evaluateName':'黑名单','rules': '一年内上黑名单一次扣60分，二次扣90分，三次以上（含三次）扣120分，连续三年都在黑名单内扣120分','percentage': '24%','score':120},
				{'evaluateID':'category2table2','evaluateName':'部门行政处罚','rules': '行政处罚一次扣40分，二次扣60分，三次以上（含三次）扣80分','percentage': '16%','score':80}
				]
			}
		];
	
	var _len = selTable.length;
	//添加数据
	for(var q = 0;q < _len;q++){
		var _table = selTable[q]['tables'],
			_tableLen = _table.length;
		var existingTable =$(".early-warning-box .editable-table").length;
		var tableID = 'tableAct' + (existingTable + 1);
		var $evaluateName ='_tableAct' + (existingTable + 1);
		//复制
		$('.clone-table').clone().appendTo('.early-warning-box').show().removeClass('clone-table').find('.third-table-body table').attr('id',tableID);
		//定义table相应变量
		elem[q] = document.getElementById("tableAct" + (q + 1));
		//从selTable里获取分类名,预警合计值
		var _perT = selTable[q].percentageTotal,
			_scoreT = selTable[q].scoreTotal,
			_cateScore = selTable[q].categoryScore,
			_cateInfo = [selTable[q].categoryName,selTable[q].categoryPer];
			
		var _parents = $('#' + tableID).parents('.editable-table')
		_parents.find('.category-info').before('<input type="hidden" class="cate-hidden-val" id="'+  selTable[q].categoryID+'">');
		
		_parents.find('.category-info input:text').each(function(index){
			$(this).attr('readonly','readonly').val(_cateInfo[index]);
		});
		_parents.find('.category-info .score-value').text(_cateScore);
		_parents.find('.percentage-total input').val(_perT);
		_parents.find('.score-total input').val(_scoreT);
		//遍历获取"表名"和对应值并设置
		for (var x = 0;x < _tableLen;x++) {
			var _evaluateName = _table[x]['evaluateName'],
				_rules = _table[x]['rules'],
				_percentage = _table[x]['percentage'],
				_score = _table[x]['score'];
			var num =existingTable.toString();
			trAct(elem[num],x+1,[
				inputHtml + '<input type="hidden" class="table-hidden" id="'+ _table[x]['evaluateID'] +'"><input type="text" class="read-only" readonly value="'+ _evaluateName +'"/>',
				'<textarea id="'+ tableID +'evaluateRole'+ (x + 1) +'" class="read-only" readonly >'+ _rules +'</textarea>',
				'<input type="text" class="percentage" id="'+ tableID +'PerTxtBox'+ (x + 1) +'" value="'+ _percentage +'"/>',
				'<input type="text" class="score" id="'+ tableID +'ScoreTxtBox'+ (x + 1) +'" value="'+ _score +'"/>',
			]);
			$("#"+tableID +" tr").eq(x).find('.btn-group').parent('td').addClass('editable-table-title');
		}
	};

	/*动态插入和删除单元行*/
	//删除
	$(document).on('click','.fi-delete',function(){
		var $this = $(this);
		//删除行
		if($this.hasClass('table-delete')){
//			var $thisId = $this.parents('table').attr('id');
			var $thisIndex = $this.parent('div').parent('td').parent('tr').index();
			var pIndex =$this.parents('.editable-table').index();
//			var endStrId = $thisId.charAt($thisId.length - 1);
			//一行表的id
			var id=$this.parents('.btn-group').siblings('input[type="hidden"]').attr('id');
			$.GD.confirm({
				msg: '确认删除行？',
				oktext: '删除',
				canceltext: '取消',
				fixed: true,
				ok: function() {
					/*$.ajax({
						type: "POST",
						async:false,
			          	url: path+"warning/doDelete.json",
					  	data: {'type':1,'jsonData':JSON.stringify([id])}, 
			          	success: function(result) {
			          		if(result.status){// 
			          			alert('删除成功')
			          			trAct(elem[pIndex +1 ],$thisIndex);
			          		}else{
			          			$.GD.msg("删除失败！");
			          			return;
			          		}
			          	}
					});*/
					trAct(elem[pIndex +1 ],$thisIndex);
				}
			});
			
		}else{
			var id=$this.parents('.editable-table').find('.category-info').siblings('input[type="hidden"]').attr('id');//获取表名 hidden的值
			//删除整个
			$.GD.confirm({
				msg: '确认删除？',
				oktext: '删除',
				canceltext: '取消',
				fixed: true,
				ok: function() {
					$.ajax({
						type: "POST",
						async:false,
			          	url: path+"warning/doDelete.json",
					  	data: {'type':2,'jsonData':JSON.stringify([id])}, 
			          	success: function(result) {
			          		if(!result.status){// 
			          			$.GD.msg("删除失败！");
			          			return;
			          		}
			          	}
					});
					$this.parents('.editable-table').remove();
				}
			});
		};
	});
	
	//新增
	$(document).on('click','.fi-add',function(){
		var $this = $(this);
		var $thisPare = $(this).parents('.editable-table');
		//需要传入项id和项名称
		tableNameList($this,1,$thisPare.find('.category-info').siblings('input[type="hidden"]').attr('id'));
	});
	/*可以使用js中的rows访问表格中的每一行
     *利用deleteRow()删除指定的行
     * 创建行的基本逻辑是首先创建行的引用对象"r = table.insertRow(num)",然后再填充内容"r.insertCell(i).innerHTML = tr[i]"
     */
    //表---编辑
     var siblingText,siblingsTd,oldArray =[];
    $(document).on('click','.table-edit',function(){
    	var $thisBtn = $(this).parent('.btn-group');
    	siblingText = $thisBtn.siblings(':text');
    	siblingsTd = $thisBtn.parent('td').siblings('td');
		var oldEvaName = siblingText.val(),//评估项
			oldEvaRules = siblingsTd.find('textarea').val(),//规则
			oldEvaPer = siblingsTd.find('.percentage').val(),//占比
			oldEvaScore= siblingsTd.find('.score').val();//分值
		oldArray = [oldEvaName,oldEvaRules,oldEvaPer,oldEvaScore];
		//需要传入对应表关联id
    	tableNameList($(this),2,$thisBtn.siblings('input[type="hidden"]').attr('id'));
    	//获取页面值
		$("#evaluateName").val(oldEvaName),//评估项
		$("#evaluateRole").val(oldEvaRules),//规则
		$("#evaluatePer").val(oldEvaPer);//占比
		$("#evaluateScore").val(oldEvaScore);//分值
    });
    
    //分类---编辑
    $(document).on('click','.category-edit',function(){
    	var $thisBtn = $(this).parent('.btn-group');
		var btnSiblings = $thisBtn.siblings('.category-info');
		var _name = btnSiblings.find('.category-name').val(),//分类名称
			_percentage = btnSiblings.find('.percentage-value').val(),//占比
			_score = btnSiblings.find('.score-value').text();//分值
		var oldArray = [_name,_percentage,_score];
		$.GD.dialog({
			title: "分类信息编辑",
			width: 500,
			height: 170,
			content:categoryHtml,
			oktext:"确定",
			ok: function() {
				var nameVal = $("#dialogCategoryName").val(),
					perVal = $("#dialogCategoryPer").val(),
					scoreVal = $("#dialogCategoryScore").val();
				var newArray = [nameVal,perVal,scoreVal];
				var _parents = $thisBtn.parents('.editable-table');
				var infoBox = _parents.find('.category-info');
		    	_parents.siblings('.editable-table').each(function(){
		    		var otherVal = $(this).find('.category-name').val();
		    		if(newArray[0] == otherVal){
			    		$.GD.msg("分类名称重复！");
			    		return
			    	}else{
			    		$.each(newArray,function(index,value){
						    if(newArray[index] != oldArray[index]){
						    	if(index !=2){
						    		infoBox.children('p').eq(index).find(':text').val(newArray[index]);
						    	}else{
									infoBox.children('p').eq(index).find('.score-value').text(scoreVal);
								}
						    }
						});
			    	}
		    	});
			},
			cancelValue: '取消',
		    cancel: function () {},
		});
		$("#dialogCategoryName").val(_name),//分类名称
		$("#dialogCategoryPer").val(_percentage),//占比
		$("#dialogCategoryScore").val(_score);//分值
    });
    //参数说明：1、当前操作对象，2、操作类型：新增还是修改，3、对应的主键id
	function tableNameList(current,type,tableId){
		var $this = current;
		$.GD.dialog({
			title: "评估项信息",
			width: 500,
			height: 300,
			content:evaluateHtml,
			oktext:"确定",
			ok: function () {
				//定义table相应变量
				var _tableLen = $('.early-warning-box .third-table-body').length;
				for(var k = 1;k <= _tableLen;k++){
					elem[k] = document.getElementById("tableAct" + k);
				};
				var evaNameVal = $("#evaluateName").val(),
					roleVal = $("#evaluateRole").val(),
					perVal = $("#evaluatePer").val(),
					scoreVal = $("#evaluateScore").val();
				var newArray = [evaNameVal,roleVal,perVal,scoreVal];
				
				if(type==1){
					var $thisId = $this.parents('.editable-table').find('.third-table-body table').attr('id');
					var pIndex = $this.parents('.editable-table').index();
	    			var $tr = $this.parents('.editable-table').find('.third-table-body tr');
					var trLength = $tr.length;
					var b = 0;
			    	$tr.each(function(i){
			    		var $val = $(this).find('.editable-table-title input[type=text]').val();
						if(newArray[0] == $val){
							b +=1 ;
						}
			    	});
					var id=$thisBtn.siblings('input[type=hidden]').attr('id');
					if(b == 0 ){
				    	
				    	trAct(elem[pIndex + 1],trLength+1,[
							inputHtml + '<input type="hidden" class="table-hidden" id="'+$thisId +'Evaluate'+ (trLength+1) +'"/>'
							+'<input type="text" class="read-only" readonly value="'+evaNameVal +'"/>',
							'<textarea id="'+ tableID +'evaluateRole'+ (x + 1) +'" class="read-only" readonly >'+ roleVal +'</textarea>',
							'<input type="text" class="percentage" id="'+ $thisId +'PerTxtBox'+  (trLength+1) +'" value="'+ perVal +'"/>',
							'<input type="text" class="score read-only" readonly id="'+ $thisId +'ScoreTxtBox'+  (trLength+1) +'" value="'+ scoreVal +'"/>'
						]);
						$("#"+$thisId +" tr").eq(trLength).find('.btn-group').parent('td').addClass('editable-table-title');
					}else{
						$.GD.confirm({
							title: '提示',
							msg: '评估项名称重复',
							canceltext: '确定',
							fixed: true,
						});
					}
				}else if(type==2){
					//修改
					var b2 = 0;
			    	var cloParents = $this.closest('table');
			    	var _index = $this.closest('tr').index();
			    	cloParents.find('tr').each(function(i){
			    		var $val = $(this).find('.editable-table-title input[type=text]').val();
			    		if(i != _index){
							if(newArray[0] == $val){
								b2 +=1 ;
							}
						}
			    	});
					var id=$this.parent('.btn-group').siblings('input[type=hidden]').attr('id');
					if(b2 == 0 ){
			    		$.each(newArray,function(index,value){
						    if(newArray[index] != oldArray[index]){
						    	if(index == 0){
						    		siblingText.val(newArray[index])
						    	}else if(index == 1){
						    		siblingsTd.find('textarea').val(newArray[index]);
						    	}else{
									siblingsTd.eq(index).find(':text').text(newArray[index]);
								}
						    }
						});
					}else{
						$.GD.confirm({
							title: '提示',
							msg: '修改的评估项名称已存在',
							canceltext: '确定',
							fixed: true,
						});
					}
				}
    			$this.parents('.editable-table').find(".category-info").before('<input type="hidden" class="cate-hidden-val">');//获取预警名称id
		    },
		    cancelValue: '取消',
		    cancel: function () {},
			onclose: function(params) {
				if(params) {
					$.GD.msg(params);
				}
			}
		});
	}
});
