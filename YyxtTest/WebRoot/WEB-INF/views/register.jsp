<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isThreadSafe="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<%@ include file="/js/commons.jspf"%>

<link rel="stylesheet" type="text/css" href="assets/css/register.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">

<script type="text/javascript">
	function register() {

		var name = $('input[name="name"]').val();
		var password = $('input[name="password"]').val();
		var email = $('input[name="email"]').val();
		var department = $('input[name="department"]').val();
		
		if (name == "" || name == null) {
			alert("用户名不能为空！");
			return false;
		}
		if (password == "" || password == null) {
			alert("密码不能为空！");
			return false;
		}
		if (email == "" || email == null) {
			alert("邮箱不能为空！");
			return false;
		}
		if (department == "" || department == null) {
			alert("部门不能为空！");
			return false;
		} else {
			
			form.submit();
			return true;
		}

	}
</script>
</head>
<body>

	<div id="container">
		<div id="left">
			<div id="title">用户注册</div>
			<div id="form1">
				<form action="loginAction_save.action" method="POST"
					name="zc" id="form" onsubmit="return register()">
					<input type="text" placeholder="用户名" name="name" id="username"><br>
					<input type="password" placeholder="密码" name="password"
						class="password"><br> 
					<input type="text"
						placeholder="电子邮件" name="email" class="password"><br>
					<input type="text" placeholder="部门" name="department"
						class="password"><br>
					 <select
					 	name="role"
						class="selectpicker show-tick form-control"
						data-live-search="true"
						style="margin-left: 40px; margin-right: 40px; margin-top: 30px; width: 300px; height: 32px; font-size: 13px;">
						<option>员工</option>
						<option>项目经理助理</option>
						<option>项目经理</option>
					</select>
					 <input type="submit" value="注册" id="bt_register">
				</form>
			</div>
		</div>
	</div>
</body>
</html>
