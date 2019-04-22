<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/js/commons.jspf"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="assets/css/newmain.css">

<script type="text/javascript">
function editor_employee() {

	var name = $('input[name="name"]').val();
	var password = $('input[name="password"]').val();
	var email = $('input[name="email"]').val();
	var email = $('input[name="email"]').val();
	var role = $('input[name="role"]').val();
	
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
	if (role == "" || role == null) {
		alert("角色不能为空！");
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
<title>修改员工信息</title>
</head>
<body>
 <div class="container4">
    	<div class="register_box">
    			<div class="security">
    				<form action="loginAction_saveeditor.action" method="post" onsubmit="return editor_employee()">
    					<ul class="list">
    						<input name="id" value="${employee.id}" type="hidden"/>
    						<li>
    								<input id="J_euserName" class="long" name="name"  type="text"  value="${employee.name}"/>
    								<div class="name">姓名:</div>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    						</li>
    						<li>
    								<input id="J_euserName" class="long"  name="password"  type="text" value="${employee.password}"/>
    								<div class="name">密码:</div>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    						</li>
    						<li>
    								<input id="J_euserName" class="long" name="email" type="text" value="${employee.email}"/>
    								<div class="name">邮箱:</div>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    						</li>
    						<li>
    								<input id="J_euserName" class="long" name="role"  type="text" value="${employee.role}"/>
    								<div class="name">角色:</div>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    						</li>
    						<li>
    								<input id="J_euserName" class="long" name="department"  type="text" value="${employee.department}"/>
    								<div class="name">部门:</div>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    						</li>
    						
    						<li>
    								<input id="J_euserName" class="long"  value="保存"   style="width:85px;font-size: large;cursor:pointer;text-align:center;line-height:40px;color:blue; font-weight:bolder "  type="submit"/>
    								<div class="name">&nbsp;</div>
    								<input id="J_euserName" class="long"  value="返回"   style="width:85px;font-size: large;cursor:pointer;text-align:center;line-height:40px;color:blue; font-weight:bolder " 
    								 href="javascript:" onclick="javascript:self.location=document.referrer;" />
    								
    						</li>	
    					</ul>
    				</form>
    			</div>
    	</div>
    </div>
</body>
</html>