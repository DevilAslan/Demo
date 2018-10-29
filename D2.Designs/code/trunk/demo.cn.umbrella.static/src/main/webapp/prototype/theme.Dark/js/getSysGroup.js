var posTop,
	newSearchH,
	oldSearchH = $(".main-search").height() + 12;

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
/*  选择部门 */	
$(".chooseGroupInfo").click(function(){
	// 获取已经添加的部门
	var $this = $(this);
	var tmp = $this.siblings("input[class=group-tmp]");
	var itemName = $(tmp).attr('name');
	if(!itemName.startsWith("ARR-")) {
		itemName = "ARR-" + $(tmp).attr('name');
	}
	var origValue = $(tmp).val();
	var groupIds = [];
	if(origValue.length > 0) {
		$.each(origValue.split(","), function(i, v){
			groupIds.push(v);
		});
	}
	$.ajax({
		type: "POST",
      	url: ossBasePath + "oss/sysGroup/getSysGroups.json",
      	data: {"excludeGroupIds": groupIds.join(",")},
      	async: false,
	  	error:function(){
			$.GD.msg("请求失败...");
	  	},
      	success: function(result) {
			if(result.status == 1){// 成功
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
				var zNodes = eval(result.data.groups);
				var t = $("#departmentTree");
				t = $.fn.zTree.init(t, setting, zNodes);
				$.fn.zTree.getZTreeObj("departmentTree").expandAll(true);
				// 项目分类树==================End=============================================
			}else{
				$.GD.msg("获取部门失败！");
			}
		}
    });
});
