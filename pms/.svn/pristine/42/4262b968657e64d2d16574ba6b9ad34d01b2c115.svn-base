$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/department/list',
        datatype: "json",
        colModel: [			
			{ label: '部门ID', name: 'id', width: 45, key: true },
			{ label: '部门名称', name: 'dmName', width: 75 },
			{ label: '上级部门', name: 'extInfo.pdname', width: 75 },
			{ label: '所属省份', name: 'extInfo.province', width: 75 },
			{ label: '部门描述', name: 'description', width: 100 },
			{ label: '主管', name: 'extInfo.directors', width: 70 },
			{ label: '状态', name: 'directorId', width: 60, formatter: function(value, options, row){
				return (value == null || value == "")? 
					'<span class="label label-danger">未配置主管</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '创建时间', name: 'genTime', width: 80 }               
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title:null,
		userList:{},
		department:{},
		dmList:{},
		options:{},
		companys:{}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增部门";
			vm.userList = {};
			vm.department = {};
			this.getDMList();
			this.getOptions();
			this.getCompanys();
		},
		update: function () {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改部门信息";
			//获取部门信息
			//this.getSelectInfo(id);
            $.get("/admin/department/getCompanys", function(r){
				vm.companys = r.list;
	            $.get("/admin/department/getProvince", function(r){
					vm.options = r.list;
					$.get("/admin/department/list2?flag=1", function(r){
						vm.dmList = r.list;
						$.get("/admin/department/selectOne/?id="+id, function(r){
							vm.department = r.dm;
							vm.userList = r.userList;
						});
					});
				});
            });
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
				    url: "/admin/department/delete",
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
			var url = vm.department.id == null ? "/admin/department/save" : "/admin/department/update";
			$.ajax({
				type: "POST",
			    url: url,
			    async: false,
			    data: JSON.stringify(vm.department),
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
			$.get("/admin/department/selectOne/?id="+id, function(r){
				vm.department = r.dm;
				vm.userList = r.userList;
			});
		},
		getDMList: function(){
			$.get("/admin/department/list2?flag=1", function(r){
				vm.dmList = r.list;
			});
		},
		getOptions: function(){
			$.get("/admin/department/getProvince", function(r){
				vm.options = r.list;
			});
		},
		getCompanys: function(){
			$.get("/admin/department/getCompanys", function(r){
				vm.companys = r.list;
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