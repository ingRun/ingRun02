<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<script>
	$(function() {
		storageListInit();
		deleteStorageAction();
	})
	// 表格初始化
	function storageListInit() {
		$('#storageList')
				.bootstrapTable(
						{
							columns : [
								{
									field : 'id',
									title : 'ID',
									visible : false
								},
								{
									field : 'goodsId',
									title : '货物ID',
									visible : false
								},
								{
									field : 'goodsName',
									title : '货物名称'
								},
								{
									field : 'goodsType',
									title : '货物类型'
								},
								{
									field : 'warehouseId',
									title : '仓库ID',
									visible : false
								},
								{
									field : 'warehouseName',
									title : '仓库名称'
								},
								{
									field : 'sum',
									title : '数量(斤)'
								},
								{
									field : 'operation',
									title : '操作',
									formatter : function(value, row, index) {
										var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
										return d;
									},
									events : {
										'click .delete' : function(e,
																   value, row, index) {
											id = row.id;
											$('#deleteWarning_modal').modal(
													'show');
										}
									}
								} ],
							url : 'findWareId?id=2',
							queryParams : queryParams,
							method : 'GET',
							dataType : 'json',
							pagination : true,   //是否分页
							sortName:'',  //undefined
							queryParamsType: "",
							sidePagination : "server",
							pageNumber : 1,
							pageSize : 10,
							pageList : [10, 25, 50, 100 ],

							clickToSelect : true
						});
	}

	//查询参数
	function queryParams(params) {

		var temp = {  //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			//limit: params.limit,  //页面大小
			//page: params.offset, //页码
			pageSize: params.pageSize,
			pageNum: params.pageNumber,
		};
		return temp;
	}

	// 表格刷新
	function tableRefresh() {
		$('#storageList').bootstrapTable('refresh', {
			query : {}
		});
	}
	// 刪除库存信息
	function deleteStorageAction(){
		$('#delete_confirm').click(function(){
			var data = {
				"id":id
			}

			// ajax
			$.ajax({
						type : "GET",
						url : "del",
						dataType : "json",
						contentType : "application/json",
						data : data,
						success : function(response){
							$('#deleteWarning_modal').modal("hide");
							tableRefresh();
						},error : function(response){
							tableRefresh();
						}
					}
			)

			$('#deleteWarning_modal').modal('hide');
		})
	}


</script>

<div class="panel panel-default">
	<ol class="breadcrumb">
		<li>仓库库存查询</li>
	</ol>
	<div class="panel-body">

		<div class="row" style="margin-top: 15px">
			<div class="col-md-12">
				<table id="storageList" class="table table-striped"></table>
			</div>
		</div>
	</div>
</div>

<!-- 删除提示模态框 -->
<div class="modal fade" id="deleteWarning_modal" table-index="-1"
	 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				<h4 class="modal-title" >警告</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-3 col-sm-3" style="text-align: center;">
						<img src="media/icons/warning-icon.png" alt=""
							 style="width: 70px; height: 70px; margin-top: 20px;">
					</div>
					<div class="col-md-8 col-sm-8">
						<h3>是否确认删除该条库存信息</h3>
						<p>(注意：一旦删除该条库存信息，将不能恢复)</p>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" type="button" data-dismiss="modal">
					<span>取消</span>
				</button>
				<button class="btn btn-danger" type="button" id="delete_confirm">
					<span>确认删除</span>
				</button>
			</div>
		</div>
	</div>
</div>
