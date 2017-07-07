/**
 * 自定义的一些公用组件
 */
$.GD = {};

$(function () {
	_initGD();
});
// 初始化定义
function _initGD() {
	$.GD = {
		// 气泡浮层，可自定义关闭时间
		bubbles: function (opt) {
            var d = dialog({
            	content: opt.msg,
    			quickClose: true// 点击空白处快速关闭
            }).show(document.getElementById(opt.id));
            if(opt.timeout) {
				setTimeout(function () {
				    d.close().remove();
				}, opt.timeout);
			}
        },
        // 消息提示，可自定义标题是否显示，可自定义关闭时间
        alert: function(opt) {
        	var d = dialog({
			    title: opt.title,
			    content: opt.msg,
			    fixed: opt.fixed,
			    okValue: '确定',
			    ok: function () {},
			}).showModal();
			if(opt.timeout) {
				setTimeout(function () {
				    d.close().remove();
				}, opt.timeout);
			}
        },
        // 消息提示，无标题，可自定义关闭时间
        msg: function(msg, timeout) {
        	if(timeout == undefined) {
        		timeout = 2000;
        	}
        	var d = dialog({
			    content: msg
			}).show();
			setTimeout(function () {
			    d.close().remove();
			}, timeout);
       	},
       	// 询问弹窗
		confirm: function(opt) {
			var d = dialog({
				title: opt.title,
				content: opt.msg,
				fixed: opt.fixed,
				okValue: (opt.oktext == "" || opt.oktext == NaN || opt.oktext == undefined) ? "确定" : opt.oktext,
				ok: opt.ok,
				cancelValue: (opt.canceltext == "" || opt.canceltext == NaN || opt.canceltext == undefined) ? '取消' : opt.canceltext,
				cancel: function () {}
			}).showModal();
       	},
       // 弹窗
		dialog: function(opt) {
       		top.dialog({
       			id: opt.id,
                title: opt.title,
                url: opt.url,
                data: opt.data,
                width: opt.width,
                height: opt.height,
                padding: '2px',
                content: opt.content,
                statusbar:opt.statusbar,// 状态栏区域 HTML代码，必须有按钮才会显示
                ok: opt.ok,
                okValue: (opt.oktext == "" || opt.oktext == NaN || opt.oktext == undefined) ? "确定" : opt.oktext,
                onclose: function() {
                	if (opt.onclose != "" && typeof (opt.onclose) == "function") {
                    	opt.onclose(this.returnValue);
                    }
                    if(opt.autoClose){
                    	dialog({id: opt.id}).remove();
                	}
                }
       		}).showModal();
		}
	};
}

