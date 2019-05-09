<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
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
			onClick : zTreeOnClick
		}
	};
	//2.提供ztree树形菜单数据
	var zTree;
	$(document).ready(function() {
		//将获取到的json数据进行解析转化下，直接传过来的数据，ztree不认
		var json = eval("(" + $('input[name="emlist"]').val() + ")");
		var zNodes = json;
		//alert(zNodes)
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
			//alert(treeNode.name);
			//showContextMenu(treeNode.organId, treeNode.leaf);
			reg=/^[-+]?\d*$/;　　//整数
			 if(treeid.length!=0){    
			        reg=/^[-+]?\d*$/;     
			        if(!reg.test(treeid)){   
			        	showContextMenu(2,treeNode.organId, treeNode.leaf,event.clientX - 40, event.clientY - 10); 
			        } else {
			        	showContextMenu(1,treeNode.organId, treeNode.leaf,
								event.clientX + 40, event.clientY - 10);
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
			
			if(mark==1){	
				var datanamelist =
					"<li data-toggle='modal' data-target='#exampleModal2' onclick='deletedept(this)'><a><i class='fa fa-trash'></i>&nbsp;删除</a></li>"+
					"<li class='divider'></li>"+
					"<li data-toggle='modal' data-target='#exampleModal3' onclick='addemploy(this)'><a><i class='fa fa-plus'></i>&nbsp;添加子人员 </a></li>"	
				$(".dropdown-menu").html(datanamelist); 				
			}else {
				var datanamelist =
					"<li data-toggle='modal' data-target='#exampleModal1' onclick='editer(this)'><a id='editerdep'><i class='fa fa-edit'></i>&nbsp;编辑</a></li>"+
					"<li class='divider'></li>"+
					"<li data-toggle='modal' data-target='#exampleModal2' onclick='deletedept(this)'><a><i class='fa fa-trash'></i>&nbsp;删除</a></li>"

				$(".dropdown-menu").html(datanamelist); 
			}
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
	
	function addemploy(o) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		var deptpartmentname = treeNode.name;
		var deptpartmentid = treeNode.id;
		
		//alert(deptpartmentid);
		$("#belongdept").val(deptpartmentname);
		$("#adddeptid").val(deptpartmentid);
	}
	
	function editer(o) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		var username = treeNode.name;
		var id = treeNode.id;
		//alert(username);
		$("#empname").val(username);
		$.ajax({
			
			type:"post",
			url:"emosUserAction_userByUserId.action",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
			data:{
				//设置数据源
				"USERID":id,
			},
			dataType:"json",//设置需要返回的数据类型
			success:function(data){
				$(data).each(function(){
					 //alert(this.uSERID);
					 $("#userid").val(this.uSERID);
					 $("#deptpartment").val(this.dEPTNAME);
					 $("#deptid").val(this.dEPTID);
					 $("#useremail").val(this.eMAIL);
					 $("#usermobile").val(this.mOBILE);
					 $("#userhone").val(this.pHONE);
					 $("#remark").val(this.rEMARK); 
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
		location.href = "emosUserAction_deletedeporemp.action?ID="+id;
		
	}
	
	function addsave_employee(o) {
		var name =$("input[name='addempname']").val();
		var id =$("input[name='adduserid']").val();
		var username = document.getElementById("empname").value;
		alert(name);
		if (name == "" || name == null) {
			alert("用户名称不能为空！");
			return false;
			}
		if (id == "" || id == null) {
				alert("用户ID不能为空！");
				return false;
			} 
		 else { 
			var form = document.getElementById('addempform');
			form.submit();
			return true;
		}
	}
	
</script>
</head>
<body>

	<div id="light" class="white_content">
		<div id="Employeelist">
			<input type="hidden" name="emlist" id="emlist"
				value="<s:property value="employeelist"/>" />
			<div id="treeDemo" class="ztree"></div>

			<div class="dropdown open" id="treeContextMenu"
				style="display: none; position: absolute">
				<ul class="dropdown-menu">

				</ul>
			</div>

		</div>

		<!-- 编辑人员的模态框（Modal） -->
		<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">编辑人员</h4>
					</div>
					<div class="modal-body">
						<form id="addempform" role="form"
							action="emosUserAction_adddemployee" method="post">
							<div id="left">

								<input type="hidden" size="20" autocomplete="off"
									id="deptid" name="deptid"
									class=" x-form-text x-form-field" style="width: 150px;"
									readonly="">
								<div class="x-form-item ">
									<label for="ext-comp-1001" style="width: auto;">用户名称:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1001"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off" id="empname"
											name="empname" value="" class=" x-form-text x-form-field "
											placeholder="名称不能为空" style="width: 150px;">
									</div>
								</div>

								<div class="x-form-item ">
									<label for="deptmanager" style="width: auto;">用户ID（字母开头，允许5-16字节，允许字母数字下划线）:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1002"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off"
											id="userid" name="userid"
											class=" x-form-text x-form-field" style="width: 150px;"
											readonly="">
									</div>
								</div>	

									<div class="x-form-clear-left"></div>
									<div class="x-form-item ">
										<label for="deptmanager" style="width: auto;">所属部门:</label>
										<div class="x-form-element" id="x-form-el-ext-comp-1002"
											style="padding-left: 0;">
											<input type="text" size="20" autocomplete="off"
												id="deptpartment" name="deptpartment" readonly=""
												class=" x-form-text x-form-field" style="width: 150px;">
										</div>
									</div>

									<div class="x-form-clear-left"></div>
									<div class="x-form-item ">
										<label for="ext-comp-1004" style="width: auto;">用户EMAIL:</label>
										<div class="x-form-element" id="x-form-el-ext-comp-1004"
											style="padding-left: 0;">
											<input type="text" size="20" autocomplete="off"
												id="useremail" name=""useremail"
												class=" x-form-text x-form-field" style="width: 150px;">
										</div>
									</div>

									<div class="x-form-clear-left"></div>
									<div class="x-form-item ">
										<label for="ext-comp-1006" style="width: auto;">手机号:</label>
										<div class="x-form-element" id="x-form-el-ext-comp-1006"
											style="padding-left: 0;">
											<input type="text" size="20" autocomplete="off"
												id="usermobile" name="usermobile"
												class=" x-form-text x-form-field" style="width: 150px;">
										</div>
									</div>
									<div class="x-form-clear-left"></div>
									<div class="x-form-item ">
										<label for="ext-comp-1007" style="width: auto;">座机号:</label>
										<div class="x-form-element" id="x-form-el-ext-comp-1007"
											style="padding-left: 0;">
											<input type="text" size="20" autocomplete="off"
												id="userhone" name="userhone"
												class=" x-form-text x-form-field" style="width: 150px;">
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
							onclick="addsave_employee(this)">确认修改</button>
					</div>
				</div>
			</div>
		</div>

		<!-- 删除的模态框（Modal） -->
		<div>
		<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">确定删除此部门或人员？</h4>
					</div>
					<input type="hidden" size="20" autocomplete="off" id="deptorempid"
						name="deptorempid" class=" x-form-text x-form-field"
						style="width: 150px;">
					<div class="modal-body">注：此部门删除其下子部门或人员不会删除</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" class="btn btn-primary"
							onclick=deletedeptoremp(this)>删除</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		</div>
		
		
		<!-- 添加子人员的模态框（Modal） -->
		<div>
		<div class="modal fade" id="exampleModal3" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">添加人员</h4>
					</div>
					<div class="modal-body">
						<form id="addempform" role="form"
							action="emosUserAction_adddemployee.action" method="post">
							<div id="left">

								<input type="hidden" size="20" autocomplete="off"
									id="adddeptid" name="adddeptid"
									class=" x-form-text x-form-field" style="width: 150px;"
									readonly="">
								<div class="x-form-item ">
									<label for="ext-comp-1001" style="width: auto;">用户名称:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1001"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off" id="username"
											name="addempname" value="" class=" x-form-text x-form-field "
											placeholder="名称不能为空" style="width: 150px;">
									</div>
								</div>

								<div class="x-form-item ">
									<label for="deptmanager" style="width: auto;">用户ID（字母开头，允许5-16字节，允许字母数字下划线）:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1002"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off"
											id="userid" name="adduserid""
											class=" x-form-text x-form-field" style="width: 150px;"
											placeholder="ID不能为空">
									</div>

									<div class="x-form-clear-left"></div>
									<div class="x-form-item ">
										<label for="deptmanager" style="width: auto;">所属部门:</label>
										<div class="x-form-element" id="x-form-el-ext-comp-1002"
											style="padding-left: 0;">
											<input type="text" size="20" autocomplete="off"
												id="belongdept" name="belongdept"
												class=" x-form-text x-form-field" style="width: 150px;" readonly="">
										</div>
									</div>

									<div class="x-form-clear-left"></div>
									<div class="x-form-item ">
										<label for="ext-comp-1004" style="width: auto;">用户EMAIL:</label>
										<div class="x-form-element" id="x-form-el-ext-comp-1004"
											style="padding-left: 0;">
											<input type="text" size="20" autocomplete="off"
												id="deptemail" name="deptemail"
												class=" x-form-text x-form-field" style="width: 150px;">
										</div>
									</div>

									<div class="x-form-clear-left"></div>
									<div class="x-form-item ">
										<label for="ext-comp-1006" style="width: auto;">手机号:</label>
										<div class="x-form-element" id="x-form-el-ext-comp-1006"
											style="padding-left: 0;">
											<input type="text" size="20" autocomplete="off"
												id="deptmobile" name="deptmobile"
												class=" x-form-text x-form-field" style="width: 150px;">
										</div>
									</div>
									<div class="x-form-clear-left"></div>
									<div class="x-form-item ">
										<label for="ext-comp-1007" style="width: auto;">座机号:</label>
										<div class="x-form-element" id="x-form-el-ext-comp-1007"
											style="padding-left: 0;">
											<input type="text" size="20" autocomplete="off"
												id="deptphone" name="deptphone"
												class=" x-form-text x-form-field" style="width: 150px;">
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
							onclick="addsave_employee(this)">确认添加</button>
					</div>
				</div>
			</div>
		</div>
		</div>
		
	</div>
</body>
</html>