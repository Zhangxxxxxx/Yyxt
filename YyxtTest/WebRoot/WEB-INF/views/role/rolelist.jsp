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

	
</script>
</head>
<body>

	<div id="light" class="white_content">
		<div id="Arealist">
			<input type="hidden" name="emlist" id="emlist"
				value="<s:property value="rolelist"/>" />
			<div id="treeDemo" class="ztree"></div>

			<!-- <div class="dropdown open" id="treeContextMenu"
				style="display: none; position: absolute">
				<ul class="dropdown-menu">

				</ul>
			</div> -->

		</div>

	</div>
</body>
</html>