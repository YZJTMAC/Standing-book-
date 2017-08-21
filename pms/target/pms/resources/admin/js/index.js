//生成菜单
var menuItem = Vue.extend({
	name: 'menu-item',
	props:{item:{}},
	template:[
	          '<li>',
	          '<a v-if="item.type === 0" href="javascript:;">',
	          '<i v-if="item.icon != null" :class="item.icon"></i>',
	          '<span>{{item.name}}</span>',
	          '<i class="fa fa-angle-left pull-right"></i>',
	          '</a>',
	          '<ul v-if="item.type === 0" class="treeview-menu">',
	          '<menu-item :item="item" v-for="item in item.list"></menu-item>',
	          '</ul>',
	          '<a v-if="item.type === 1" :href="\'#\'+item.url"><i v-if="item.icon != null" :class="item.icon"></i><i v-else class="fa fa-circle-o"></i> {{item.name}}</a>',
	          '</li>'
	].join('')
});

//iframe自适应
$(window).on('resize', function() {
	var $content = $('.content');
	$content.height($(this).height() - 120);
	$content.find('iframe').each(function() {
		$(this).height($content.height());
	});
}).resize();

//注册菜单组件
Vue.component('menuItem',menuItem);

var vm = new Vue({
	el:'#rrapp',
	data:{
		user:{},
		menuList:{"1":{"type":0,"icon":"fa fa-cog","name":"系统管理","list":{
						"11":{"type":1,"icon":"fa fa-users","name":"部门管理","url":"admin/department"},
						"12":{"type":1,"icon":"fa fa-user-secret","name":"角色管理","url":"admin/role"},
						"13":{"type":1,"icon":"fa fa-user","name":"用户管理","url":"admin/user"}
					  }
				  }
//				  "2":{"type":0,"icon":"fa fa-navicon","name":"测试菜单","list":{
//					"21":{"type":1,"icon":"fa fa-truck","name":"测试","url":"/ceshi"}
//				  }}
				 },
		main:"admin/main",
		password:'',
		newPassword:'',
        navTitle:"控制台"
	},
	methods: {
		getMenuList: function (event) {
			$.getJSON("sys/menu/user?_"+$.now(), function(r){
				vm.menuList = r.menuList;
			});
		},
		getUser: function(){
			$.getJSON("sys/user/info?_"+$.now(), function(r){
				vm.user = r.user;
			});
		},
		updatePassword: function(){
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "修改密码",
				area: ['550px', '270px'],
				shadeClose: false,
				content: jQuery("#passwordLayer"),
				btn: ['修改','取消'],
				btn1: function (index) {
					layer.load(2);
					var data = "password="+vm.password+"&newPassword="+vm.newPassword;
					$.ajax({
						type: "POST",
					    url: "/updatePassword",
					    data: data,
					    dataType: "json",
					    error: function(){
					    	layer.closeAll('loading');
					    },
					    success: function(result){
					    	layer.closeAll('loading');
							if(result.result == true){
								layer.close(index);
								layer.alert('修改成功', function(index){
									location.href = "/layout";
								});
							}else{
								layer.alert(result.msg);
							}
						}
					});
	            }
			});
		}
	},
	created: function(){
		//this.getMenuList();
		//this.getUser();
	},
	updated: function(){
		// //路由
		// var router = new Router();
		// routerList(router, vm.menuList);
		// router.start();
	}
});

$(function(){
	//路由
	var router = new Router();
	routerList(router, vm.menuList);
	router.start();
})

function routerList(router, menuList){
	for(var key in menuList){
		var menu = menuList[key];
		if(menu.type == 0){
			routerList(router, menu.list);
		}else if(menu.type == 1){
			router.add('#'+menu.url, function() {
				var url = window.location.hash;
				
				//替换iframe的url
			    vm.main = url.replace('#', '');
			    
			    //导航菜单展开
			    $(".treeview-menu li").removeClass("active");
			    $("a[href='"+url+"']").parents("li").addClass("active");
			    
			    vm.navTitle = $("a[href='"+url+"']").text();
			});
		}
	}
}
