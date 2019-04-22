<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假管理</title>

<%@ include file="/js/commons.jspf" %>
<script  src="assets/js/jquery.treeview.js"></script>
<link type="text/css" rel="stylesheet" href="assets/css/jquery.treeview.css" charset="utf-8"/>
<link type="text/css" rel="stylesheet" href="assets/css/screen.css" charset="utf-8" />
<style>

 .span.folder {
	background-image: url("images/folder.gif");
	background-repeat: no-repeat;
	font-size: 14px;
	padding-left: 20px;
	height: 17px;
}  


.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 88);
}

.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 25%;
	width: 35%;
	height: 35%;
	padding: 20px;
	border: 10px solid orange;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
</style>

<script type="text/javascript">
	$(function() {
		$("[name=chkbox]").click(
				function() {

					//当选中或取消一个权限时，也同时选中或取消所有的下级权限
					$(this).siblings("ul").find("input").attr("checked",
							this.checked);

					//当选中一个权限时，也要选中所有的直接上级权限
					if (this.checked == true) {
						$(this).parents("li").children("input").attr("checked",
								true);
					}

					//当某一个父权限下的子权限都不选中时，该父权限也不选中
					var elements = $(this).parent("li").parent("ul").find(
							"input");
					var num = elements.length;
					/*alert(num);*/
					var a = 0;
					for (var i = 0; i < num; i++) {
						if (elements[i].checked == false) {
							a++;
						}
					}
					if (a == num) {
						$(this).parent("li").parent("ul").siblings("input")
								.attr("checked", false);
					}

				});

	});

	/*提交表单时判断有些地方是否为空，如果为空则不提交*/
	function check() {
		var leavedays = $('input[name="days"]').val();
		var leavereason = $('input[name="content"]').val();
		var checkname = [];//定义一个数组    
		$('input:checkbox[name="chkbox"]:checked').each(function() {//遍历每一个名字为chkbox的复选框，其中选中的执行函数    
			checkname.push($(this).next().text());//将选中的值添加到数组checkname中    
		});

		if (leavedays == "" || leavedays == null) {
			alert("请假日期不能为空！");
			return false;
		}
		if (leavereason == "" || leavereason == null) {
			alert("请假原因不能为空！");
			return false;
		}
		if (checkname == "" || checkname == null) {
			alert("请至少选择一名审批人员！");
			return false;
		} else {
			//alert(checkname);
			$("#checkboxname").val(checkname);//将获取的审批人员的值传递给id为checkboxname的隐藏域
			//location.href="leaveBillAction_save.action?checkboxText=" + checkname;
			return true;
		}

	}

	/*选择审批人员*/
	function rolecheck() {
		var checkname = [];//定义一个数组    
		$('input:checkbox[name="chkbox"]:checked').each(function() {//遍历每一个名字为chkbox的复选框，其中选中的执行函数    
			checkname.push($(this).next().text());//将选中的值添加到数组checkname中    
		});
		if (checkname == "" || checkname == null) {
			alert("请至少选择一名审批人员！");
			return false;
		} else {
			alert(checkname);
			$("#checkboxname").val(checkname);//将获取的审批人员的值传递给id为checkboxname的隐藏域
			//location.href="leaveBillAction_save.action?checkboxText=" + checkname;
			document.getElementById('light').style.display = 'none';
			document.getElementById('fade').style.display = 'none';
			return true;
		}

	}

	function openDialog() {

		document.getElementById('light').style.display = 'block';
		document.getElementById('fade').style.display = 'block'
	}

	function closeDialog() {
		document.getElementById('light').style.display = 'none';
		document.getElementById('fade').style.display = 'none'
	}
	
	$("#tree").treeview({
		toggle: function() {
			console.log("%s was toggled.", $(this).find(">span").text());
		}
	});
</script>
</head>
<body>
	<form action="leaveBillAction_save.action?checkboxText=" + checkname
		method="POST" id="form" onsubmit="return check()">
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
													<td width="94%" valign="bottom"><span class="STYLE1">
															新增/修改请假申请 </span></td>
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
			<tr>
				<td>
					<div align="left" class="STYLE21">
						<s:hidden name="id"></s:hidden>
						<s:hidden name="user.id"></s:hidden>
						<!-- 增加这个input隐藏域是为了像相对应的action传递选择的审批的部门和人员名称，其中value值在此页面的js代码中获得 -->
						<!-- 需要注意的是action获取相关传递的内容，需要再struts.xml中添加相关的参数 -->
						<input type="hidden" name="checkboxText" id="checkboxname"
							value="" /> 请假天数:
						<s:textfield name="days" cssStyle="width: 200px;" id="leavedays" />
						<br /> <br /> 请假原因:
						<s:textfield name="content" cssStyle="width: 800px;"
							id="leavereason" />
						<br /> <br /> 备&emsp;&emsp;注:
						<s:textarea name="remark" cols="50" rows="5" />
						<br /> <br /> <input type="button" value="选择审批人员"
							id="selectname" onclick="openDialog()">

						<div id="light" class="white_content">
							<div id="ChooseContent">
								<span>请选择审批的人员</span>
								<ul id="tree" class="filetree treeview-famfamfam">
									<li><input type="checkbox" id="cb0" name="chkbox">
										<label for="cb1"><span class="folder" id="department">${department}</span></label>
										<ul>
											<s:if test="#rolename!=null && #rolename.size()>0">
												<s:iterator value="#rolename">
													<li><input type="checkbox" id="cb11" name="chkbox">
														<label for="cb11"><span class="file"
															id="checkname"><s:property /></span></label></li>
												</s:iterator>
											</s:if>
										</ul>
									</li>
								</ul>
								<input type="button" value="确定" style="height: 30px;"
									onclick="rolecheck()" /> <input type="button" value="关闭"
									style="height: 30px;" onclick="closeDialog()" />
							</div>
						</div>

						<div id="fade" class="black_overlay"></div>

						<br /> <br /> <input type="submit" value="提交" class="button_ok"
							style="height: 30px;" />
					</div>
				</td>
			</tr>
		</table>

	</form>
</body>
</html>