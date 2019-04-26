<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
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

	//加载用户
	function loadinguser(o) {
		
		var id = $(o).find("input[name='deptid']").val();
		var deptname = $(o).find("input[name='deptname']").val();
		//alert(id);
		$.ajax({
			
			type:"post",
			url:"emosUserAction_userByDeptId.action",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
			data:{
				//设置数据源
				"DEPTID":id,
			},
			dataType:"json",//设置需要返回的数据类型
			success:function(data){
				//alert(data.length);
				var datanamelist = "<ul>";
				datanamelist += deptname
				$(data).each(function(){
					console.log(this)
				    //alert(this.uSERNAME);
					datanamelist += "<li class='closed'> <span class='file'>"+ this.uSERNAME + "</span> </li>" 
				});
				datanamelist += "</ul>"; 
				//var datanamelist = "<input type='text' name='deptemail' value='hehe' />"
				$("#"+id).html(datanamelist);  //可以的。利用这个可以写的
			},
			
			 error:function(){
			 alert("系统异常，请稍后重试！");
			 }//这里不要加","
			});
	}
	
	//二级列表加载用户
	function loadinguser2(o) {
		
		var id = $(o).find("input[name='deptid2']").val();
		var deptname = $(o).find("input[name='deptname']").val();
		//alert(id);
		$.ajax({
			
			type:"post",
			url:"emosUserAction_userByDeptId.action",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
			data:{
				//设置数据源
				"DEPTID":id,
			},
			dataType:"json",//设置需要返回的数据类型
			success:function(data){
				//alert(data.length);
				var datanamelist = "<ul>"; 
				datanamelist += deptname  
				$(data).each(function(){
					console.log(this)
				    //alert(this.uSERNAME);
					datanamelist += "<li class='closed'> <span class='file'>"+ this.uSERNAME + "</span> </li>" 
				});
				datanamelist += "</ul>"; 
				$("#"+id).html(datanamelist);  //可以的。利用这个可以写的
			},
			
			 error:function(){
			 alert("系统异常，请稍后重试！");
			 }//这里不要加","
			});
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
												<td width="94%" valign="bottom"><span class="STYLE1">员工列表</span></td>
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
				<li class="closed"><span class="folder">所有部门</span>
					<ul>
						<s:if test="#twoDepartment!=null && #twoDepartment.size()>0">
							<s:iterator value="#twoDepartment" var="twoDepartment">
								<li class="closed"><span class="folder"> <s:property
											value="deptname" />
								</span> <s:if test="#twoDepartment.deptname=='省公司'">
										<ul>
											<s:iterator value="#onedepartment" var="OneDepartment"
												id="OneDepartment">

												<li class="closed" onclick="loadinguser(this)">
												<input type="hidden" name="deptid"
													value="<s:property value="deptid"/>" />
												<input type="hidden" name="deptname"
													value="<s:property value="deptname"/>" /> 	
													 <span
													class="folder" id="<s:property value="deptid"/>"><s:property value="deptname" /> </span> <!-- 双层遍历的list集合，里面的这一个list必须把id设置为inner，意思就是不在栈顶要不然会有问题 -->
													<s:iterator value="#threeDepartment" var="threeDepartment"
														id="inner">
														<s:if test="#OneDepartment.deptid==#inner.parentdeptid">
															<ul>

																<li class="closed" onclick="loadinguser2(this)" >
																<input type="hidden" name="deptid2"
																	value="<s:property value="#inner.deptid"/>" />
																<input type="hidden" name="deptname"
																	value="<s:property value="deptname"/>" /> 
																<span class="folder" id="<s:property value="#inner.deptid"/>"> <s:property
																			value="#inner.deptname" />
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
					</li>
				</ul>
		</div>

	</div>
</body>
</html>