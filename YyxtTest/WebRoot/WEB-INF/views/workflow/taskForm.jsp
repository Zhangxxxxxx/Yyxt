<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/js/commons.jspf"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假任务办理</title>
<style>
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
	})
	function openDialog() {

		document.getElementById('light').style.display = 'block';
		document.getElementById('fade').style.display = 'block'
	}
	function openDialog1() {

		document.getElementById('light1').style.display = 'block';
		document.getElementById('fade1').style.display = 'block'
	}
	function closeDialog() {
		document.getElementById('light').style.display = 'none';
		document.getElementById('fade').style.display = 'none'
	}
	
	function closeDialog1() {
		document.getElementById('light1').style.display = 'none';
		document.getElementById('fade1').style.display = 'none'
	}

	/*点击转派确定时判断是否选择人员为空，如果为空则不提交*/
	function check() {
		var checkname = [];//定义一个数组    
		$('input:checkbox[name="chkbox"]:checked').each(function() {//遍历每一个名字为chkbox的复选框，其中选中的执行函数    
			checkname.push($(this).next().text());//将选中的值添加到数组checkname中    
		});
		if (checkname == "" || checkname == null) {
			alert("请至少选择一名审批人员！");
		} else {
			//	alert(checkname);
			//	$("#checkboxname").val(checkname);//将获取的审批人员的值传递给id为checkboxname的隐藏域
			//请求workflowaction的updateCandidateUser的方法进行人员转派，传递的值有选择的人员和此任务的id
		location.href = "workflowAction_updateCandidateUser.action?checkboxText="+ checkname +"&taskId="+${taskId};
		document.getElementById('light').style.display = 'none';
		document.getElementById('fade').style.display = 'none';
		alert("正在提交中...请勿重复操作");
		}

	}
	
	/*点击分派确定时判断是否选择人员为空，如果为空则不提交*/
	function check1() {
		var checkname1 = [];//定义一个数组    
		$('input:checkbox[name="chkbox"]:checked').each(function() {//遍历每一个名字为chkbox的复选框，其中选中的执行函数    
			checkname1.push($(this).next().text());//将选中的值添加到数组checkname中  
		});
		if (checkname1 == "" || checkname1 == null) {
			alert("请至少选择一名分派审批人员！");
			return false;
		} else {
			location.href = "workflowAction_setapproverlist.action?checkboxText="+ checkname1;
		    var form = document.getElementById('form');
			//再次修改input内容
			form.submit();
			document.getElementById('light1').style.display = 'none';
			document.getElementById('fade1').style.display = 'none';
			return true;
		}

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
												<td width="94%" valign="bottom"><span class="STYLE1">请假申请的任务办理</span></td>
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
				<form action="workflowAction_submitTask.action"  method="POST" id="form">
					<div align="left" class="STYLE21">
						<!-- 任务ID -->
						<s:hidden name="taskId"></s:hidden>
						<!-- 请假单ID -->
						<s:hidden name="id"></s:hidden>
						<input type="hidden" name="checkboxText" id="checkboxname"   value="" />
						请假天数:
						<s:textfield name="days" disabled="true" cssStyle="width: 200px;" />
						<br /> 请假原因:
						<s:textfield name="content" disabled="true"
							cssStyle="width: 800px;" />
						<br /> 请假备注:
						<s:textarea name="remark" disabled="true" cols="30" rows="2" />
						<br /> 请假人：
						<s:textfield name="user.name" disabled="true"
							cssStyle="width: 200px;" />
						<br /> 批&emsp;&emsp;注:
						<s:textarea name="comment" cols="50" rows="5" />
						<br />
						<!-- 使用连线的名称作为按钮 -->
						<s:if test="#outcomeList!=null && #outcomeList.size()>0">
							<s:iterator value="#outcomeList" id="outcome">
								<!-- 遍历连线名称集合后，针对分派进行判断 -->
								<s:if test='#outcome=="分派"'>
									<input type="button" name="outcome" value="<s:property/>"
									class="button_ok" style="height: 30px; width:45px; text-align:center; " onclick="openDialog1()"/>
								</s:if>
								<s:else>
									<input type="submit" name="outcome" value="<s:property/>"
									class="button_ok" style="height: 30px;" />
								</s:else>
							</s:iterator>
						</s:if>
						<!-- 判断当前审批人和请假人是否一致，如果一致则不能进行转派，因为自己的请假审批不能转给别人 -->
						<s:if test="user.id==#employeeid">
							<input type="hidden" name="transfer" id="transferid" value="移交"
								class="button_ok" style="height: 30px;" onclick="openDialog()" />
						</s:if>
						<s:else>
							<input type="button" name="transfer" id="transferid" value="移交"
								class="button_ok" style="height: 30px;" onclick="openDialog()" />
						</s:else>
						<div id="light" class="white_content">
							<div id="ChooseContent">
								<span>请选择移交的人员</span>
								<ul id="tree">
									<li><input type="checkbox" id="cb0" name="chkbox">
										<label for="cb1"><span class="folder" id="department">${department}</span></label>
										<ul>
											<s:if test="#rolename!=null && #rolename.size()>0">
												<s:iterator value="#rolename">
													<li><input type="checkbox" id="cb11" name="chkbox">
														<label for="cb11"><span class="folder"
															id="checkname"><s:property /></span></label></li>
												</s:iterator>
										</ul></li>
									</s:if>
								</ul>
								<input type="button" value="确定" style="height: 30px;"
									onclick="check()" /> <input type="button" value="关闭"
									style="height: 30px;" onclick="closeDialog()" />
							</div>
						</div>
						
						<div id="fade" class="black_overlay"></div>
						
						<div id="light1" class="white_content">
							<div id="ChooseContent">
								<span>请选择分派的人员</span>
								<ul id="tree">
									<li><input type="checkbox" id="cb0" name="chkbox">
										<label for="cb1"><span class="folder" id="department">亿阳信通甘肃分公司</span></label>
										<ul>
											<s:if test="#allname!=null && #allname.size()>0">
												<s:iterator value="#allname">
													<li><input type="checkbox" id="cb11" name="chkbox">
														<label for="cb11"><span class="folder"
															id="checkname"><s:property /></span></label></li>
												</s:iterator>
										</ul></li>
									</s:if>
								</ul>
								<input type="button" value="确定" style="height: 30px;"
									onclick="check1()" /> <input type="button" value="关闭"
									style="height: 30px;" onclick="closeDialog1()" />
							</div>
						</div>
						<div id="fade1" class="black_overlay"></div>
					</div>

				</form>
			</td>
		</tr>

	</table>

	<hr>
	<br>
	<s:if test="#commentList!=null && #commentList.size()>0">
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
													<td width="94%" valign="bottom"><span class="STYLE1">显示请假申请的批注信息</span></td>
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
				<td><table width="100%" border="0" cellpadding="0"
						cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"
						onmouseout="changeback()">
						<tr>
							<td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div
									align="center">
									<span class="STYLE10">时间</span>
								</div></td>
							<td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div
									align="center">
									<span class="STYLE10">批注人</span>
								</div></td>
							<td width="75%" height="20" bgcolor="d3eaef" class="STYLE6"><div
									align="center">
									<span class="STYLE10">批注信息</span>
								</div></td>
						</tr>
						<s:iterator value="#commentList">
							<tr>
								<td height="20" bgcolor="#FFFFFF" class="STYLE6"><div
										align="center">
										<s:date name="time" format="yyyy-MM-dd HH:mm:ss" />
									</div></td>
								<td height="20" bgcolor="#FFFFFF" class="STYLE19"><div
										align="center">
										<s:property value="userId" />
									</div></td>
								<td height="20" bgcolor="#FFFFFF" class="STYLE19"><div
										align="center">
										<s:property value="fullMessage" />
									</div></td>
							</tr>
						</s:iterator>


					</table></td>
			</tr>
		</table>
	</s:if>
	<s:else>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td height="24" bgcolor="#F7F7F7"><table width="100%"
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
													<td width="94%" valign="bottom"><span><b>暂时没有批注信息</b></span></td>
												</tr>
											</table>
										</td>
									</tr>
								</table></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</s:else>

	<%--  --%>
</body>
</html>