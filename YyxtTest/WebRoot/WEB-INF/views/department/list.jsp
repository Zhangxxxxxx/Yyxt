<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门管理</title>
<%@ include file="/js/commons.jspf"%>

<link type="text/css" rel="stylesheet"
	href="assets/css/jquery.treeview.css" />
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.css">

<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.treeview.js"></script>
<style>
</style>
<script type="text/javascript">
	$(document).ready(function() {

		$("#browser").treeview({
			toggle : function() {
				console.log("%s was toggled.", $(this).find(">span").text());
			}
		});

	});

	//模态框传值
	function editerdept(o) {

		var departname = $(o).find("input[name='departmentname']").val();
		var id = $(o).find("input[name='deptid']").val();
		//alert(id);
		$("#deptid").val(id);
		$("#deptname").val(departname);
		$("#deptmanager").val($(o).find("input[name='deptmanager']").val());
		$("#deptemail").val($(o).find("input[name='deptemail']").val());
		$("#deptmobile").val($(o).find("input[name='deptmobile']").val());
		$("#deptphone").val($(o).find("input[name='deptphone']").val());
		$("#remark").val($(o).find("input[name='remark']").val());

	}

	function save_department() {
		var name = $('input[name="deptname"]').val();
		if (name == "" || name == null) {
			alert("部门名称不能为空！");
			return false;
		} else {
			var form = document.getElementById('updateform');
			form.submit();
			return true;
		}
	}
	
	function deletedept(o) {
		var id = $(o).find("input[name='departmentid']").val();
		//alert(id);
		$("#deparmentid").val(id);
	}
	
	function deltedepartment() {
		var id= $("#deparmentid").val();
		location.href = "departmentAction_delete.action?id="+id;
	}
	
	function adddept(o) {
		var name = $(o).find("input[name='parentdepname']").val();
		var parentid = $(o).find("input[name='parentdeptid']").val();
		//alert(id);
		$("#deptparent").val(name);
		$("#parentdeptid").val(parentid);
	}
	
	function addsave_department() {
		var name = $('input[name="deptname"]').val();
		/* if (name == "" || name == null) {
			alert("部门名称不能为空！");
			return false;
		} else { */
			var form = document.getElementById('adddepform');
			form.submit();
			return true;
		//}
	}

</script>
</head>
<body>
	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="30"><table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td height="24" bgcolor="#353c44"><table width="100%"
								border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><table width="100%" border="0" cellspacing="0"
											cellpadding="0">
											<tr>
												<td width="6%" height="19" valign="bottom"><div
														align="center">
														<img
															src="${pageContext.request.contextPath }/images/tb.gif"
															width="14" height="14" />
													</div></td>
												<td width="94%" valign="bottom"><span class="STYLE1">部门列表</span></td>
											</tr>
										</table></td>
									<td><div align="right">
											<span class="STYLE1"> </span>
										</div></td>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
	</table>
	<div id="light" class="white_content">
		<div id="Departmentlist">
			<ul id="browser" class="filetree treeview-famfamfam">
				<li><span class="folder">所有部门</span>
					<ul>
						<s:if test="#twoDepartment!=null && #twoDepartment.size()>0">
							<s:iterator value="#twoDepartment" var="twoDepartment">
								<li class="closed"><span class="folder"><s:property
											value="deptname" /> <span class="dropdown"> <a
											href="#" class="dropdown-toggle" data-toggle="dropdown">
												<i class="fa fa-cog" style="color: black"></i> <b
												class="caret" style="color: black"></b>
										</a>
											<ul class="dropdown-menu" id="testdrop">

												<li data-toggle="modal" data-target="#exampleModal"
													onclick="editerdept(this)"><a id="editerdep"
													<s:property value="id"/>><i class="fa fa-edit"></i>&nbsp;编辑</a>
													<input type="hidden" name="deptid"
													value="<s:property value="deptid"/>" /> <input
													type="hidden" name="departmentname"
													value="<s:property value="deptname"/>" /> <input
													type="hidden" name="deptmanager"
													value="<s:property value="deptmanager"/>" /> <input
													type="hidden" name="deptemail"
													value="<s:property value="deptemail"/>" /> <input
													type="hidden" name="deptmobile"
													value="<s:property value="deptmobile"/>" /> <input
													type="hidden" name="deptphone"
													value="<s:property value="deptphone"/>" /> <input
													type="hidden" name="remark"
													value="<s:property value="remark"/>" /></li>
												<li class="divider"></li>
												<li data-toggle="modal" data-target="#exampleModal2"
												onclick="deletedept(this)"
												><a><i class="fa fa-trash"
														></i>&nbsp;删除</a>
													<input type="hidden" name="departmentid"
													value="<s:property value="id"/>" /> 
												</li>
												<li class="divider"></li>
												<li data-toggle="modal" data-target="#exampleModal3"
													onclick="adddept(this)"><a><i class="fa fa-plus"></i>&nbsp;添加下级部门</a>
													<input type="hidden" name="parentdepname"
													value="<s:property value="deptname"/>" />
													<input type="hidden" name="parentdeptid"
													value="<s:property value="deptid"/>" />   
												</li>
											</ul>
									</span> </span> </span> <s:if test="#twoDepartment.deptname=='省公司'">
										<ul>
											<s:iterator value="#onedepartment" var="OneDepartment"
												id="OneDepartment">

												<li class="closed"><span class="folder"><s:property
															value="deptname" /> <span class="dropdown"> <a
															href="#" class="dropdown-toggle" data-toggle="dropdown">
																<i class="fa fa-cog" style="color: black"></i> <b
																class="caret" style="color: black"></b>
														</a>
															<ul class="dropdown-menu">

																<li data-toggle="modal" data-target="#exampleModal"
																	onclick="editerdept(this)"><a id="editerdep"
																	<s:property value="id"/>><i class="fa fa-edit"></i>&nbsp;编辑</a>
																	<input type="hidden" name="deptid"
																	value="<s:property value="deptid"/>" /> <input
																	type="hidden" name="departmentname"
																	value="<s:property value="deptname"/>" /> <input
																	type="hidden" name="deptmanager"
																	value="<s:property value="deptmanager"/>" /> <input
																	type="hidden" name="deptemail"
																	value="<s:property value="deptemail"/>" /> <input
																	type="hidden" name="deptmobile"
																	value="<s:property value="deptmobile"/>" /> <input
																	type="hidden" name="deptphone"
																	value="<s:property value="deptphone"/>" /> <input
																	type="hidden" name="remark"
																	value="<s:property value="remark"/>" /></li>
																<li class="divider"></li>
																<li data-toggle="modal" data-target="#exampleModal2"
																onclick="deletedept(this)"
																><a><i class="fa fa-trash" 
																		></i>&nbsp;删除</a>
																	<input type="hidden" name="departmentid"
																	value="<s:property value="id"/>" />
																		</li>
																<li class="divider"></li>
																<li data-toggle="modal" data-target="#exampleModal3"
																onclick="adddept(this)"><a><i class="fa fa-plus"></i>&nbsp;添加下级部门</a>
																<input type="hidden" name="parentdepname"
																value="<s:property value="deptname"/>" /> 
																<input type="hidden" name="parentdeptid"
																value="<s:property value="deptid"/>" /> 
																</li>
															</ul>
													</span> </span> <!-- 双层遍历的list集合，里面的这一个list必须把id设置为inner，意思就是不在栈顶要不然会有问题 -->
													<s:iterator value="#threeDepartment" var="threeDepartment"
														id="inner">
														<s:if test="#OneDepartment.deptid==#inner.parentdeptid">
															<ul>

																<li class="closed"><span class="file"> <s:property
																			value="#inner.deptname" /> <span class="dropdown">
																			<a href="#" class="dropdown-toggle"
																			data-toggle="dropdown"> <i class="fa fa-cog"
																				style="color: black"></i> <b class="caret"
																				style="color: black"></b>
																		</a>
																			<ul class="dropdown-menu">
																				<li data-toggle="modal" data-target="#exampleModal"
																					onclick="editerdept(this)"><a id="editerdep"
																					<s:property value="id"/>><i class="fa fa-edit"></i>&nbsp;编辑</a>
																					<input type="hidden" name="deptid"
																					value="<s:property value="#inner.deptid"/>" /> <input
																					type="hidden" name="departmentname"
																					value="<s:property value="#inner.deptname"/>" /> <input
																					type="hidden" name="deptmanager"
																					value="<s:property value="#inner.deptmanager"/>" />
																					<input type="hidden" name="deptemail"
																					value="<s:property value="#inner.deptemail"/>" />
																					<input type="hidden" name="deptmobile"
																					value="<s:property value="#inner.deptmobile"/>" />
																					<input type="hidden" name="deptphone"
																					value="<s:property value="deptphone"/>" /> <input
																					type="hidden" name="remark"
																					value="<s:property value="#inner.remark"/>" /></li>
																				<li class="divider"></li>
																				<li data-toggle="modal" data-target="#exampleModal2"
																				onclick="deletedept(this)"
																				><a><i class="fa fa-trash" 
																						></i>&nbsp;删除</a>
																						<input type="hidden" name="departmentid"
																						value="<s:property value="#inner.id"/>" /> 
																						</li>
																			</ul>
																	</span>
																</span></li>
															</ul>
														</s:if>
													</s:iterator></li>
											</s:iterator>
										</ul>
									</s:if></li>
							</s:iterator>
						</s:if>
					</ul>
		</div>

		<!-- 编辑的模态框（Modal） -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">编辑部门</h4>
					</div>
					<div class="modal-body">
						<form id="updateform" role="form"
							action="departmentAction_savedepteditor.action" method="post">
							<div id="left">
								<input type="hidden" size="20" autocomplete="off" id="deptid"
									name="deptid" class=" x-form-text x-form-field"
									style="width: 150px;">
								<div class="x-form-item ">
									<label for="ext-comp-1001" style="width: auto;">部门名称:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1001"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off" id="deptname"
											name="deptname"  class=" x-form-text x-form-field "
											style="width: 150px;">
									</div>
								</div>

								<div class="x-form-clear-left"></div>
								<div class="x-form-item ">
									<label for="deptmanager" style="width: auto;">部门负责人:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1002"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off"
											id="deptmanager" name="deptmanager" class=" x-form-text x-form-field"
										 	style="width: 150px;">
									</div>
								</div>

								<div class="x-form-clear-left"></div>
								<div class="x-form-item ">
									<label for="ext-comp-1004" style="width: auto;">部门EMAIL:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1004"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off" id="deptemail"
											name="deptemail" class=" x-form-text x-form-field"
											style="width: 150px;">
									</div>
								</div>

								<div class="x-form-clear-left"></div>
								<div class="x-form-item ">
									<label for="ext-comp-1006" style="width: auto;">部门手机号:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1006"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off"
											id="deptmobile" name="deptmobile"
											class=" x-form-text x-form-field" style="width: 150px;">
									</div>
								</div>
								<div class="x-form-clear-left"></div>
								<div class="x-form-item ">
									<label for="ext-comp-1007" style="width: auto;">部门座机:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1007"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off" id="deptphone"
											name="deptphone" class=" x-form-text x-form-field"
											style="width: 150px;">
									</div>
								</div>
								<!-- <div class="x-form-clear-left"></div>
								<div class="x-form-item ">
									<label for="areaid" style="width: auto;">地市:</label>
									<div class="x-form-element" id="x-form-el-areaid"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off" id="areaid"
											name="areaid" class=" x-form-text x-form-field" readonly=""
											style="width: 150px;">
									</div>
								</div> -->
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
							onclick="save_department()">保存</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 删除的模态框（Modal） -->
		<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">确定删除此部门？</h4>
					</div>
					<input type="hidden" size="20" autocomplete="off"
											id="deparmentid" name="deparmentid"
											class=" x-form-text x-form-field" style="width: 150px;">
					<div class="modal-body">注：此部门删除其下子部门也将删除</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" class="btn btn-primary" onclick=deltedepartment()>
						删除</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		
		<!-- 添加子部门的模态框（Modal） -->
		<div class="modal fade" id="exampleModal3" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">添加部门</h4>
					</div>
					<div class="modal-body">
						<form id="adddepform" role="form"
							action="departmentAction_adddepartment.action" method="post">
							<div id="left">
								
								<input type="hidden" size="20" autocomplete="off"
									id="parentdeptid" name="parentdeptid" class=" x-form-text x-form-field"
									style="width: 150px;" readonly="">
								<div class="x-form-item ">
									<label for="ext-comp-1001" style="width: auto;">部门名称:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1001"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off" id="deptname"
											name="deptname" value="" class=" x-form-text x-form-field "
											placeholder = "部门名称不能为空"
											style="width: 150px;">
									</div>
								</div>
								
								<div class="x-form-item ">
									<label for="deptmanager" style="width: auto;">父部门</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1002"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off"
											id="deptparent" name="deptparent" class=" x-form-text x-form-field"
										 	style="width: 150px;" readonly="">
								</div>
								
								<div class="x-form-clear-left"></div>
								<div class="x-form-item ">
									<label for="deptmanager" style="width: auto;">部门负责人:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1002"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off"
											id="deptmanager" name="deptmanager" class=" x-form-text x-form-field"
										 	style="width: 150px;">
									</div>
								</div>

								<div class="x-form-clear-left"></div>
								<div class="x-form-item ">
									<label for="ext-comp-1004" style="width: auto;">部门EMAIL:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1004"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off" id="deptemail"
											name="deptemail" class=" x-form-text x-form-field"
											style="width: 150px;">
									</div>
								</div>

								<div class="x-form-clear-left"></div>
								<div class="x-form-item ">
									<label for="ext-comp-1006" style="width: auto;">部门手机号:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1006"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off"
											id="deptmobile" name="deptmobile"
											class=" x-form-text x-form-field" style="width: 150px;">
									</div>
								</div>
								<div class="x-form-clear-left"></div>
								<div class="x-form-item ">
									<label for="ext-comp-1007" style="width: auto;">部门座机:</label>
									<div class="x-form-element" id="x-form-el-ext-comp-1007"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off" id="deptphone"
											name="deptphone" class=" x-form-text x-form-field"
											style="width: 150px;">
									</div>
								</div>
								<!-- <div class="x-form-clear-left"></div>
								<div class="x-form-item ">
									<label for="areaid" style="width: auto;">地市:</label>
									<div class="x-form-element" id="x-form-el-areaid"
										style="padding-left: 0;">
										<input type="text" size="20" autocomplete="off" id="areaid"
											name="areaid" class=" x-form-text x-form-field" readonly=""
											style="width: 150px;">
									</div>
								</div> -->
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
							onclick="addsave_department()">确认添加</button>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>