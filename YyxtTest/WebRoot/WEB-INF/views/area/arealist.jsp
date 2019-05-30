<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>地域管理</title>
<%@ include file="/js/commons.jspf"%>

<link type="text/css" rel="stylesheet"
	href="assets/css/jquery.treeview.css" />
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="${basePath}/css/demo.css" type="text/css">
<link rel="stylesheet" href="${basePath}/css/zTreeStyle/zTreeStyle.css"
	type="text/css">

<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="assets/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="assets/js/jquery.ztree.excheck.js"></script>
<style>
</style>
<script type="text/javascript">
	//1.进行ztree树形菜单设置，开启简单json数据支持 
	var setting = {
		view : {
			selectedMulti : false
		},
		edit : {
			enable : true,
			showRemoveBtn : false,
			showRenameBtn : false
		},
		data : {
			keep : {
				parent : true,
				leaf : true
			},
			simpleData : {
				enable : true
			}
		},
		callback : {
			//onClick : zTreeOnClick
			onRightClick: zTreeOnClick
		}
	};
	//2.提供ztree树形菜单数据
	var zTree;
	$(document).ready(function() {
		//将获取到的json数据进行解析转化下，直接传过来的数据，ztree不认
		var json = eval("(" + $('input[name="emlist"]').val() + ")");
		var zNodes = json;
		$.fn.zTree.init($("#treeDemo"), setting, zNodes); //加载数据

	});

	// 单击事件
	function zTreeOnClick(event, treeId, treeNode) {
		var treeid = treeNode.id ;
		if (treeid == "-1") {
			return;
		} else {
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			var st = $(document).scrollTop();//滚动条的高度
			//reg=/^[-+]?\d*$/;　　//整数
			 if(treeid.length!=0){    
			        reg=/^[-+]?\d*$/;     
			        if(!reg.test(treeid)){   
			        	showContextMenu(2,treeNode.organId, treeNode.leaf,event.clientX + 40, event.clientY - 10+st); 
			        } else {
			        	showContextMenu(1,treeNode.organId, treeNode.leaf,
								event.clientX + 40, event.clientY - 10+st);
					}   
			  }  
			
			
		}
	}
	
	function showContextMenu(mark,type, leaf, x, y) {
		if (type == -1) {
			$(".dropdown-menu").find("li:not(:first)").hide();
		} else if (leaf) {
			$(".dropdown-menu").find("li:first").hide();
		} else {
			$(".dropdown-menu").find("li").show();
				var datanamelist =
					"<li data-toggle='modal' data-target='#exampleModal1' onclick='editer(this)'><a><i class='fa fa-edit'></i>&nbsp;修改</a></li>"+
					"<li class='divider'></li>"+
					"<li data-toggle='modal' data-target='#exampleModal2' onclick='addarea(this)'><a><i class='fa fa-plus'></i>&nbsp;新建下级地域 </a></li>"+
					"<li class='divider'></li>"+
					"<li data-toggle='modal' data-target='#exampleModal3' onclick='deletedept(this)'><a><i class='fa fa-trash'></i>&nbsp;删除这个地域 </a></li>"	
				$(".dropdown-menu").html(datanamelist); 
		}
		$("#treeContextMenu").css({
			"top" : y + "px",
			"left" : x + "px"
		}).show();
		$("body").on("mousedown", onBodyMouseDown);
	}

	function hideContextMenu() {
		$("#treeContextMenu").hide();
		$("body").off("mousedown", onBodyMouseDown);
	}

	function onBodyMouseDown(event) {
		if (!(event.target.id == "treeContextMenu" || $(event.target).parents(
				"#treeContextMenu").length > 0)) {
			hideContextMenu();
		}
	}
	
	function addarea(o) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		var parentid = treeNode.id;
		//alert(parentid);
		$("#parentid").val(parentid);
	}
	
	function editer(o) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		var username = treeNode.name;
		var id = treeNode.id;
		//alert(username);
		$("#areaname").val(username);
		$.ajax({
			
			type:"post",
			url:"areaAction_areaByAreaId.action",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
			data:{
				//设置数据源
				"areaid":id,
			},
			dataType:"json",//设置需要返回的数据类型
			success:function(data){
				$(data).each(function(){
					 //alert(this.parentareaid);
					 $("#id").val(this.id);
					 $("#areacode").val(username); 
					 $("#remark").val(this.remark);
					 $("#areaid").val(this.areaid);
					 $("#parentareaid").val(this.parentareaid);
					 $("#ordercode").val(this.ordercode);
					 
				});
				
			},
			 error:function(){
			 alert("系统异常，请稍后重试！");
			 }//这里不要加","
			});
	}
	
	function deletedept(o) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		var id = treeNode.id;
		var name = treeNode.name;
		//alert(id);
		$("#deptorempid").val(id);
	}
	
	function deletedeptoremp(o) {
		//传递要删除的deptid或者userid
		var id= $("#deptorempid").val();
		//alert(id);
		location.href = "areaAction_deletedarea.action?areaid="+id;
	}
	
	function addsave_area(o) {
		var name =$("#addareaname").val();
		if (name == "" || name == null) {
			alert("地域名称不能为空！");
			return false;
			}
		 else { 
			var form = document.getElementById('addareaform');
			form.submit();
			return true;
		}
	}
	
	function saveediter_area(o) {
		var name =$("input[name='areaname']").val();
		if (name == "" || name == null) {
			alert("地域名称不能为空！");
			return false;
			}
		 else { 
			var form = document.getElementById('saveareaform');
			form.submit();
			return true;
		}
	}
	
</script>
</head>
<body>

	<div id="light" class="white_content">
		<div id="Arealist">
			<input type="hidden" name="emlist" id="emlist"
				value="<s:property value="arealist"/>" />
			<div id="treeDemo" class="ztree"></div>

			<div class="dropdown open" id="treeContextMenu"
				style="display: none; position: absolute">
				<ul class="dropdown-menu">

				</ul>
			</div>

		</div>

		<!-- 编辑地域的模态框（Modal） -->
		<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">编辑地域</h4>
					</div>
					<div class="modal-body">
						<form id="saveareaform" role="form"
							action="areaAction_saveediterarea" method="post">
							<div id="left">

								<input type="text" size="20" autocomplete="off" id="id"
									name="id" class=" x-form-text x-form-field"
									style="width: 150px;" readonly="">
								<input type="text"
									size="20" autocomplete="off" id="areaid" name="areaid"
									class=" x-form-text x-form-field" style="width: 150px;"
									readonly="">
								<input type="text" size="20"
									autocomplete="off" id="parentareaid" name="parentareaid"
									class=" x-form-text x-form-field" style="width: 150px;"
									readonly=""> 
								<input type="text" size="20"
									autocomplete="off" id="ordercode" name="ordercode"
									class=" x-form-text x-form-field" style="width: 150px;"
									readonly="">
								<div class="x-form-item ">
									<label for="ext-comp-1001" style="width: auto;">地域名称:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1001"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off" id="areaname"
											name="areaname" value="" class=" x-form-text x-form-field "
											placeholder="名称不能为空" style="width: 150px;">
									</div>
								</div>
								<div class="x-form-clear-left"></div>
								<div class="x-form-item ">
									<label for="ext-comp-1007" style="width: auto;">地域:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1007"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off" id="areacode"
											name="areacode" class=" x-form-text x-form-field"
											style="width: 150px;">
									</div>
								</div>
								<div class="x-form-clear-left"></div>
								<div class="x-form-item ">
									<label for="remark" style="width: auto;">备注:</label>
									<div class="x-form-element" id="x-form-el-remark"
										style="padding-left: 0;">
										<textarea
											style="width: 150px; height: 60px; overflow: hidden;"
											autocomplete="off" id="remark" name="remark"
											class=" x-form-textarea x-form-field"></textarea>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary"
							onclick="saveediter_area(this)">确认修改</button>
					</div>
				</div>
			</div>
		</div>


		<!-- 添加子人员的模态框（Modal） -->
		<div>
			<div class="modal fade" id="exampleModal2" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
							<h4 class="modal-title" id="exampleModalLabel">新建下级地域</h4>
						</div>
						<div class="modal-body">
							<form id="addareaform" role="form"
								action="areaAction_addarea.action" method="post">
								<div id="left">

									<input type="text" size="20" autocomplete="off"
										id="parentid" name="parentareaid"
										class=" x-form-text x-form-field" style="width: 150px;"
										readonly="">
									<div class="x-form-item ">
										<label for="ext-comp-1001" style="width: auto;">地域名称:</label>
										<div class="x-form-element" id="x-form-el-ext-comp-1001"
											style="padding-left: 0;">
											<input type="text" size="20" autocomplete="off" id="addareaname"
												name="areaname" value="" class=" x-form-text x-form-field "
												placeholder="名称不能为空" style="width: 150px;">
										</div>
									</div>

									<div class="x-form-item ">
										<label for="deptmanager" style="width: auto;">地域:</label>
										<div class="x-form-element" id="x-form-el-ext-comp-1002"
											style="padding-left: 0;">
											<input type="text" size="20" autocomplete="off" id="areacode"
												name="areacode" class=" x-form-text x-form-field"
												style="width: 150px;">
										</div>

										<div class="x-form-clear-left"></div>
										<div class="x-form-item ">
											<label for="remark" style="width: auto;">备注:</label>
											<div class="x-form-element" id="x-form-el-remark"
												style="padding-left: 0;">
												<textarea
													style="width: 150px; height: 60px; overflow: hidden;"
													autocomplete="off" id="remark" name="remark"
													class=" x-form-textarea x-form-field"></textarea>
											</div>
										</div>
									</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary"
								onclick="addsave_area(this)">确认添加</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 删除地域模态框（Modal） -->
		<div>
			<div class="modal fade" id="exampleModal3" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">确定删除这个地域？</h4>
						</div>
						<input type="hidden" size="20" autocomplete="off" id="deptorempid"
							name="deptorempid" class=" x-form-text x-form-field"
							style="width: 150px;">
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary"
								onclick=deletedeptoremp(this)>删除</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
		</div>

	</div>
</body>
</html>