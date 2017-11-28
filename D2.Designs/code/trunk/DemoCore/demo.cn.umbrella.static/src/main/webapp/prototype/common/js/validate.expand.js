$.validator.setDefaults({
	errorPlacement: function(error, element) {
		var h = error.text().length;
		var n = h < 18 ? 1 : parseInt(h / 18);
		var b = - 30 -(n - 1) * 18;
		var labelWrapper = $("<div class='error-wrapper' style='bottom:"+ b +"px;'>").append('<span class="arrow-outer"></span><span class="arrow-inner"></span>').append($(error));
		if (element.is(":radio")) {
			labelWrapper.appendTo(element.parents("td"));
		} else if (element.is(":checkbox")) {
			labelWrapper.appendTo(element.parents("td"));
		} else {
	    	labelWrapper.appendTo(element.parents("td"));
	    }
	},
	success: function(label) {
		label.parents(".error-wrapper").remove();
	},
	highlight: function(element, errorClass) {
		
	}
});

jQuery.validator.addMethod("regexp", function(value, element, params) {
	var reg = new RegExp(params);
    return this.optional(element) || reg.test(value);
}, ""); 

jQuery.validator.addMethod("decimal", function(value, element, params) {
	var reg1 = new RegExp("^\d{0," + params[0] + "}\\.\d{0," + params[1] + "}$");
    return this.optional(element) || reg.test(value);
}, ""); 

jQuery.validator.addMethod("extis", function(value, element, params) {
	var result = true;
	if(!eval(params.disable)){
		var urlData = {};
		urlData[element.name]=value;
		if(params.params){
			$.each(params.params.split(","),function(i,o){
				var itemType = $("[name="+o+"]");
				if("radio" == itemType) {
					urlData[o] = $("[name="+o+"][checked]").val();
				} else {
					urlData[o]=$("[name="+o+"]").val();
				}
			});
		}
		$.ajax({
			type : "post",
			url : ossBasePath + params.url,
			data : urlData,
			async : false,
			success : function(data){
				result = data === false;
			}
		});
	}
	
	return result;
}, ""); 

