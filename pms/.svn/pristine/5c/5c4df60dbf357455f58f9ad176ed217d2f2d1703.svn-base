var layer;
layui.use('layer', function(){
  layer = layui.layer;
});

//重写alert
window.alert = function(msg, callback){
	layer.alert(msg, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//重写confirm式样框
window.confirm = function(msg, callback){
	layer.confirm(msg, {btn: ['确定','取消']},
	function(){//确定事件
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//全局配置
$.ajaxSetup({
	dataType: "json",
	contentType: "application/json;charset=utf-8",
	cache: false,
	complete:function(XMLHttpRequest,textStatus){     
		var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，  
		if(sessionstatus=="timeout"){     
            alert("登录已超时，请重新登录",function(){
            	window.top.location.href="/index"; 
            });   
		}
    }
});
$(function(){
	/*限制文本框只能输入数字和小数点,小数点只能有1个，小数点后只能有2位*/  
	$(".NumDecText").keyup(function(){
		 $(this).val($(this).val().replace(/[^0-9.]/g,'').replace(/^\./g,"").replace(/\.{2,}/g,".").replace(".","$#$").replace(/\./g,"").replace("$#$",".").replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'));
	})
	/*限制文本框只能输入数字*/  
	$(".NumText").keyup(function(){
		 $(this).val($(this).val().replace(/\D/gi,""));
	})
	//日期选择插件
	layui.use('laydate', function(){

		// 使用方法：class="layui-input" placeholder="请选择日期" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"
		  var laydate = layui.laydate;
		  
		  var start = {
		    min: laydate.now()
		    ,max: '2099-06-16 23:59:59'
		    ,istoday: false
		    ,choose: function(datas){
		      end.min = datas; //开始日选好后，重置结束日的最小日期
		      end.start = datas //将结束日的初始值设定为开始日
		    }
		  };
		  
		  var end = {
		    min: laydate.now()
		    ,max: '2099-06-16 23:59:59'
		    ,istoday: false
		    ,choose: function(datas){
		      start.max = datas; //结束日选好后，重置开始日的最大日期
		    }
		  };
		  
		  $('#layDateStart').on('click',function(){
			  start.min = laydate.now();
			  start.elem = this;
			  start.format="YYYY-MM-DD";
			  start.istime=false;
			  laydate(start);
		  })
		  $('#layDateEnd').on('click',function(){
			  end.elem = this;
			  end.format="YYYY-MM-DD";
			  end.istime=false;
			  laydate(end);
		  })
		  $('#layDateStartForQuery').on('click',function(){
			  start.min = "2000-01-01 00:00:00";
			  start.max = "2099-06-16 23:59:59";
			  start.elem = this;
			  start.format="YYYY-MM-DD";
			  start.istime=false;
			  laydate(start);
		  })
		  $('#layDateEndForQuery').on('click',function(){
			  end.elem = this;
			  end.max = "2099-06-16 23:59:59";
			  end.format="YYYY-MM-DD";
			  end.istime=false;
			  laydate(end);
		  })
		  $('#layTimeStart').on('click',function(){
			  start.min = "2000-01-01 00:00:00";
			  start.elem = this;
			  start.format="YYYY-MM-DD hh:mm:ss";
			  start.istime=true;
			  laydate(start);
		  })
		  $('#layTimeEnd').on('click',function(){
			  end.elem = this;
			  end.format="YYYY-MM-DD hh:mm:ss";
			  end.istime=true;
			  laydate(end);
		  })
		  
	});
})

function openWin(url){
	layer.load(1);
	window.open(url,'','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-80)+',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no')
	layer.closeAll('loading');
}

$.mergeJsonObject = function (jsonbject1, jsonbject2) {
	var resultJsonObject={};
	for(var attr in jsonbject1){
	resultJsonObject[attr]=jsonbject1[attr];
	}
	for(var attr in jsonbject2){
	resultJsonObject[attr]=jsonbject2[attr];
	}
	return resultJsonObject;
};
