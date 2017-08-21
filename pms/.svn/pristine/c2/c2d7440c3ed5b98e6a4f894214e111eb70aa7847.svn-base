$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/user/list',
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'id', width: 70, key: true },
			{ label: '用户名', name: 'loginName', width: 100 },
			{ label: '部门', name: 'extInfo.dmName', width: 100 },
			{ label: '角色', name: 'extInfo.roleName', width: 100 },
			{ label: '姓名', name: 'realname', width: 100 },
			{ label: '性别', name: 'sex', width: 70, formatter: function(value, options, row){
				return (value == null || value == "")? 
						'<span class="label label-danger">未知</span>' : value == "1" ? '男' : '女';
			}},
			{ label: '手机号', name: 'mobile', width: 100 },
			{ label: '身份证号', name: 'idcard', width: 160},
			{ label: '邮箱', name: 'email', width: 220 },
			{ label: '备注', name: 'note', width: 100 },
			{ label: '状态', name: 'available', width: 80, formatter: function(value, options, row){
				return value === 1 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '创建时间', name: 'genTime', width: 160 }               
        ],
		viewrecords: true,
        height: '384',
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        shrinkToFit:false,
        autoScroll: true,
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
        	//$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title:null,
		roleList:{},
		dmList:{},
		user:{}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增用户";
			$("#pwd").show();
			vm.user = {'available':0,'dmIdList':[]};
			this.getRoleList();
			this.getDMList();
		},
		update: function () {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改用户信息";
            $("#pwd").hide();
			//获取角色信息
			this.getSelectInfo(id);
			this.getRoleList();
			this.getDMList();
		},
		del: function () {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
					async: false,
				    url: "/admin/user/delete",
				    data: JSON.stringify(ids),
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
		saveOrUpdate: function (event) {
			var url = vm.user.id == null ? "/admin/user/save" : "/admin/user/update";
			$.ajax({
				type: "POST",
			    url: url,
			    async: false,
			    data: JSON.stringify(vm.user),
			    success: function(r){
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
		getSelectInfo: function(id){
			$.get("/admin/user/selectOne/?id="+id, function(r){
				vm.user = r.user;
			});
		},
		getRoleList: function(){
			$.get("/admin/role/list2", function(r){
				vm.roleList = r.roleList;
			});
		},
		getDMList: function(){
			$.get("/admin/department/list2", function(r){
				vm.dmList = r.list;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
//                postData:{'username': vm.q.username},
                page:page
            }).trigger("reloadGrid");
		}
	}
});