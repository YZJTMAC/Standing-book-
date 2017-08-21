$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/role/list',
        datatype: "json",
        colModel: [			
			{ label: '角色ID', name: 'id', width: 45, key: true },
			{ label: '角色名称', name: 'rolename', width: 75 },
			{ label: '角色备注', name: 'description', width: 100 },
			{ label: '状态', name: 'menuIdList', width: 80, formatter: function(value, options, row){
				return (value == null || value == "")? 
					'<span class="label label-danger">未进行授权</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '创建时间', name: 'genTime', width: 80}                   
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var setting = {
	view : {
		showIcon: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	}
};
var ztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title:null,
		role:{}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增角色";
			vm.role = {};
			vm.getMenuTree(null);
		},
		update: function () {
			var roleId = getSelectedRow();
			if(roleId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改角色信息";
            vm.getMenuTree(roleId);
		},
		del: function (event) {
			var roleIds = getSelectedRows();
			if(roleIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
					async: false,
				    url: "/admin/role/delete",
				    data: JSON.stringify(roleIds),
				    success: function(r){
						if(r.result == true){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getRole: function(roleId){
            $.get("/admin/role/selectOne/?id="+roleId, function(r){
            	vm.role = r.role;
                
                //勾选角色所拥有的菜单
    			var menuIds = vm.role.menuIdList;
    			for(var i=0; i<menuIds.length; i++) {
    				var node = ztree.getNodeByParam("id", menuIds[i]);
    				ztree.checkNode(node, true, false);
    			}
    		});
		},
		saveOrUpdate: function (event) {
			layer.load(2);
			//获取选择的菜单
			var nodes = ztree.getCheckedNodes(true);
			var menuIdList = new Array();
			for(var i=0; i<nodes.length; i++) {
				menuIdList.push(nodes[i].id);
			}
			vm.role.menuIdList = menuIdList;
			
			var url = vm.role.id == null ? "/admin/role/save" : "/admin/role/update";
			$.ajax({
				type: "POST",
				//async: false,
			    url: url,
			    data: JSON.stringify(vm.role),
			    success: function(r){
			    	layer.closeAll('loading');
			    	if(r.result == true){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		getMenuTree: function(roleId) {
			//加载菜单树
			$.get("/admin/role/menu", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
				//展开所有节点
				ztree.expandAll(true);
				
				if(roleId != null){
					vm.getRole(roleId);
				}
			});
	    },
	    reload: function (event) {
	    	vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});