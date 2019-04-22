<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>登录页面</title>
<%@ include file="/js/commons.jspf"%>
<link href="${basePath}/assets/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	if (parent != window) {
		parent.location.href = window.location.href;
	}
	function func_login() {
		var username = $('input[name="name"]').val();
		var password = $('input[name="password"]').val();
		if (username == "" || username == null) {
			alert("用户名不能为空！");
			return false;
		}
		if (password == "" || password == null) {
			alert("密码不能为空！");
			return false;
		} else {

			document.forms[0].submit();
		}

	}
</script>
</head>
<body style="text-align: center;">
	<hgroup>
		<h1>Activiti控制台</h1>
		<h3>亿阳信通甘肃分公司</h3>
	</hgroup>
	<form action="loginAction_login.action" method="post">
		<div class="group">
			<input name="name" type="text"><span class="highlight"></span><span
				class="bar"></span> <label>用户名</label>
		</div>
		<div class="group">
			<input type="text" name="password"><span class="highlight"></span><span
				class="bar"></span> <label>密码</label>
		</div>
		<button type="button" class="button buttonBlue" onclick="func_login()">
			登录
			<div class="ripples buttonRipples">
				<span class="ripplesCircle"></span>
			</div>
		</button>
		<div>
			<a class="STYLE2" style="text-align:right" href="loginAction_register.action">注册 </a>
			<span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span>
			<a class="STYLE2" style="text-align:left" href="loginAction_forgetpw.action">忘记密码</a> 
		</div>
	</form>

	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src="assets/js/index.js"></script>

</body>
</html>