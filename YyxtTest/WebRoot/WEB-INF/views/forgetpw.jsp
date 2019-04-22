<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isThreadSafe="true"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘记密码</title>
<%@ include file="/js/commons.jspf"%>

<link rel="stylesheet" type="text/css" href="assets/css/register.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">

<script type="text/javascript">
	function forgetpw() {

		var name = $('input[name="name"]').val();
		var password = $('input[name="password"]').val();
		var makesurepw = $('input[name="makesurepw"]').val();
		
		
		if (name == "" || name == null) {
			alert("用户名不能为空！");
			return false;
		}
		if (password == "" || password == null) {
			alert("新密码不能为空！");
			return false;
		}
		if (makesurepw == "" || makesurepw == null) {
			alert("再次确认的密码不能为空！");
			return false;
		}
		if (password != makesurepw ){
			alert("两次输入的密码不一致！");
			return false;
		}
		 else {
			
			form.submit();
			return true;
		}

	}
	
</script>
</head>
<body>

	<div id="container">
		<div id="right">
			<div id="title">忘记密码</div>
			<div id="form1">
				<form action="loginAction_updatepw.action" method="POST"
					name="zc" id="form" onsubmit="return forgetpw()">
					<input type="text" placeholder="用户名" name="name" id="username"><br>
					<input type="password" placeholder="新密码" name="password"
						class="password"><br> 
					<input type="password" placeholder="再次确认密码" name="makesurepw"
						class="password"><br>
					 <input type="submit" value="提交" id="bt_register">
				</form>
			</div>
		</div>
	</div>
</body>
</html>
