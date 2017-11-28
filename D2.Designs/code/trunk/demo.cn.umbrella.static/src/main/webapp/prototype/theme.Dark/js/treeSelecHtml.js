/*这个是我自己用的没有ajax,这个的原版是getSysGroup.js*/
;;

/* 删除选中的部门 */
$(document).on("click", ".cancel-ico", function(){
	var $this = $(this);
	var groupId = $this.prev("span").data("group-id");
	var $sysGroupIds = $this.parent("li").parent("ul").siblings("input[class=group-tmp]");
	var ov = $sysGroupIds.val();
	if(ov) {
		var ovarr = ov.split(",");
		ovarr.remove(groupId.toString());
	}
	$sysGroupIds.val(ovarr.join(","));
	$(this).parent("li").remove();
	
	//mainsearch高度变化
	mainSearch();
});

Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val) return i;
	}
	return -1;
};
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};
/*  选择部门 */	
$(".chooseGroupInfo").click(function(){
	// 获取已经添加的部门
	var $this = $(this);
	var tmp = $this.siblings("input[class=group-tmp]");
	var itemName = $(tmp).is('[name^="ARR-"]');
	if(!itemName) {
		itemName = "ARR-" + $(tmp).attr('name');
	};
	
	var origValue = $(tmp).val();
	var groupIds = [];
	if(origValue.length > 0) {
		$.each(origValue.split(","), function(i, v){
			groupIds.push(v);
		});
	}
	
	var htmlContent = '<div class="ztree-module" style="height:390px;">';
		htmlContent += '<ul class="ztree" style="margin: 10px;" id="departmentTree"></ul>';
		htmlContent += '</div>';
		$.GD.dialog({
			title: "选择数据来源部门",
			width: 300,
			height: 400,
			content:htmlContent,
			statusbar: "<p class='color-blue' style='font:12px microsoft yahei'><span class='info-icon'></span>只能选择子节点！</p>",
			oktext:"确定",
			ok: function () {
				var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
		    	var nodes = treeObj.getCheckedNodes(true);
				// 添加选中的部门，并修改input中的值
	    		if(nodes.length > 0) {
	    			var lis = [];
	    			$.each(nodes, function(i, v){
	    				groupIds.push(v.groupId);
	    				var groups = [];
	    				groups.push('<span data-group-id="' + v.groupId +'">' + v.groupName + '</span>');
	    				groups.push('<i class="cancel-ico"></i>');
	    				lis.push($("<li>").append(groups));
	    			});
	    			$this.prev("ul").append(lis);
	    			$(tmp).val(groupIds.join(","));
	    			$(tmp).attr("name", itemName);
	    		};
	    		//mainsearch高度变化
	    		mainSearch();
		    },
		    cancelValue: '取消',
		    cancel: function () {},
			onclose: function(params) {
				if(params) {
					$.GD.msg(params);
				}
			}
		});
		// 项目分类树==================Start============================================
		var setting = {
				view: {
					dblClickExpand: false,
				},
				check : {
					enable : true,
					chkboxType : {"Y" : "","N" : ""}
				},
				data: {
					key:{
						name: "groupName"
					},
					simpleData: {
						enable:true,
						idKey: "groupId",
						pIdKey: "groupPid",
						rootPId: 0
					}
				},
				callback: {
					beforeClick: function(treeId, treeNode) {
						if(!treeNode.isParent) {//当是父节点 返回false 不让选取
							var zTree = $.fn.zTree.getZTreeObj("departmentTree");
							zTree.checkNode(treeNode, !treeNode.checked, null, true);
						}
						return false;
					},
					beforeCheck: function(treeId, treeNode) {
						return !treeNode.isParent;//当是父节点 返回false 不让选取
					}
				}
			};
//		var zNodes = eval(result.data.groups);
		var zNodes =[
					{ groupId:1, groupPid:0, groupName:"根 Root", open:true},
					{ groupId:11, groupPid:1, groupName:"父节点 1-1", open:true},
					{ groupId:111, groupPid:11, groupName:"叶子节点 1-1-1"},
					{ groupId:112, groupPid:11, groupName:"叶子节点 1-1-2"},
					{ groupId:113, groupPid:11, groupName:"叶子节点 1-1-3"},
					{ groupId:114, groupPid:11, groupName:"叶子节点 1-1-4"},
					{ groupId:12, groupPid:1, groupName:"父节点 1-2", open:true},
					{ groupId:121, groupPid:12, groupName:"叶子节点 1-2-1"},
					{ groupId:122, groupPid:12, groupName:"叶子节点 1-2-2"},
					{ groupId:123, groupPid:12, groupName:"叶子节点 1-2-3"},
					{ groupId:124, groupPid:12, groupName:"叶子节点 1-2-4"},
					{ groupId:13, groupPid:1, groupName:"父节点 1-3", open:true},
					{ groupId:131, groupPid:13, groupName:"叶子节点 1-3-1"},
					{ groupId:132, groupPid:13, groupName:"叶子节点 1-3-2"},
					{ groupId:133, groupPid:13, groupName:"叶子节点 1-3-3"},
					{ groupId:134, groupPid:13, groupName:"叶子节点 1-3-4"},
					{ groupId:14, groupPid:1, groupName:"父节点 1-3", open:true},
					{ groupId:141, groupPid:14, groupName:"叶子节点 1-3-1"},
					{ groupId:142, groupPid:14, groupName:"叶子节点 1-3-2"},
					{ groupId:143, groupPid:14, groupName:"叶子节点 1-3-3"},
					{ groupId:144, groupPid:14, groupName:"叶子节点 1-3-4"},
					
				];
		var t = $("#departmentTree");
		t = $.fn.zTree.init(t, setting, zNodes);
		$.fn.zTree.getZTreeObj("departmentTree").expandAll(true);
		// 项目分类树==================End=============================================
	
	
});


//datagrid版选择
$(".chooseData").click(function(){
	// 获取已经添加的部门
	var $this = $(this);
	var tmp = $this.siblings("input[class=group-tmp]");
	var itemName = $(tmp).is('[name^="ARR-"]');
	if(!itemName) {
		itemName = "ARR-" + $(tmp).attr('name');
	};
	
	var origValue = $(tmp).val();
	var groupIds = [];
	if(origValue.length > 0) {
		$.each(origValue.split(","), function(i, v){
			groupIds.push(v);
		});
	}
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

				// 添加选中的部门，并修改input中的值
	    		if(rows.length > 0) {
	    			var lis = [];
	    			$.each(rows, function(i, v){
	    				groupIds.push(v.productid);
	    				var groups = [];
	    				groups.push('<span data-group-id="' + v.productid +'">' + v.productname + '</span>');
	    				var $liHtml = groups + '<i class="cancel-ico"></i>';
	    				groups.push('<i class="cancel-ico"></i>');
	    				
	    				var $li = $(".been-elected-box").find("li");
						var $len = $li.length;
	    				var m = 0;
						if (rows){
							if($len > 0){
		    					for(var p = 0;p < $len;p++){
			    					if($liHtml == $li.eq(p).html()){
			    						m +=1 ;
			    					}
			    				};
			    				if(m != 0 ){
									groups = [];
								}else{
									lis.push($("<li>").append(groups));
								}
		    				}else{
		    					lis.push($("<li>").append(groups));
		    				}
						};
	    			});
					$this.prev("ul").append(lis);
					
					//数组内去重
					var newGroupIds = [];
		            for(var t = 0, gl = groupIds.length; t < gl; t++) {
		              for(var j = t + 1; j < gl; j++)
		                if (groupIds[t] === groupIds[j]) j = ++t; 
		              newGroupIds.push(groupIds[t]);
		            };
		    		$(tmp).val(newGroupIds.join(","));
	    			$(tmp).attr("name", itemName);
	    			
	    		};
				
	    		//mainsearch高度变化
	    		mainSearch();
		    },
		    cancelValue: '取消',
		    cancel: function () {},
			onclose: function(params) {
				if(params) {
					$.GD.msg(params);
				}
			}
		});
		var ale;
		// 列表==================Start============================================
		$('#dialogDataGrid').datagrid({
			url: "js/datagrid_data1.json",
			columns: [
				[
					{field: 'productid',title: 'productid',width: 80},
					{field: 'productname',title: 'productname',width: 100},
					{field: 'listprice',title: 'listprice',width: 80},
					{field: 'unitcost',title: 'unitcost',width: 100},
					{field: 'attr1',title: 'attr1'},
					{field: 'status',title: 'status',width: 60}
				]
			],
			frozenColumns: [
				[
					{field: 'ck',checkbox: true}, // 复选框列
//					{field: 'productname',title: "产品名",width: 100},
//					{field: 'listprice',title: "listprice",width: 80},
//					{field: 'unitcost',title: "unitcost",width: 100},
//					{field: 'attr1',title: "属性"},
//					{field: 'status',title: "状态",width: 60}
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

			sortName: 'itemid,     productname',
			sortOrder: 'desc   ,asc,asc   ',
			queryParams: {},// 查询条件
		});
				
		// 列表==================End=============================================
		$('.user-datagrid').datagrid('resize', {
			height:'100%',
			width:'100%'
		});
	
});