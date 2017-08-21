/**
 * 省市县下拉列表框联动
 */
$(document).ready(function(){
	$("#provincial").change(function(){
		$("#city").attr("disabled","disabled");
		$("#city").empty();
		$('#city').append("<option value='0'></option>");
		$("#county").attr("disabled","disabled");
		$("#county").empty();
		$('#county').append("<option value='0'></option>");
		var provinValue = $(this).val();
		if(provinValue != "" && provinValue != 0){
			$.post("/common/getOrgByParentId",JSON.stringify({'parentId':provinValue}),function(data){
				if(data != null){
					var list = data.list;
					if(list.length > 0){
						$("#city").empty();
						$('#city').append("<option value='0'></option>");
						$.each(list, function(i, item) {
							$('#city').append("<option value="+item.id+">" + item.name+ "</option>");
						})
						$("#city").removeAttr("disabled");
					} else {
						alert("未能查询到下一个选择框的数据，请联系管理员");
					}
				} else {
					alert("服务器错误，未能查询到下一个选择框的数据，请联系管理员");
				}
			});
		}
	});
	$("#city").change(function(){
		$("#county").attr("disabled","disabled");
		$("#county").empty();
		$('#county').append("<option value='0'></option>");
		var provinValue = $(this).val();
		if(provinValue != "" && provinValue != 0){
			$.post("/common/getOrgByParentId",JSON.stringify({'parentId':provinValue}),function(data){
				if(data != null){
					var list = data.list;
					if(list.length > 0){
						$("#county").empty();
						$('#county').append("<option value='0'></option>");
						$.each(list, function(i, item) {
							$('#county').append("<option value="+item.id+">" + item.name+ "</option>");
						})
						$("#county").removeAttr("disabled");
					} else {
						alert("未能查询到下一个选择框的数据，请联系管理员");
					}
				} else {
					alert("服务器错误，未能查询到下一个选择框的数据，请联系管理员");
				}
			});
		}
	});
});