<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isThreadSafe="true"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath %>">
<head>
  <title>亿阳信通</title>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
 </head>
 <body>
  <div class="header">
    <div class="dl-log">欢迎您，<span class="dl-log-user">来到Activiti管理系统</span><a href="login2.jsp" title="退出系统" class="dl-log-quit">[退出]</a>
    <span class="STYLE6"><b>当前登录用户：${sessionScope.globle_user.name }</b></span>
    </div>
  </div>
   <div class="content">
    <div class="dl-main-nav"> 
      <ul id="J_Nav"  class="nav-list ks-clear">
        <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">Activiti</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-supplier">系统</div></li>
      </ul>
    </div>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">
    </ul>
  <script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="assets/js/bui.js"></script>
  <script type="text/javascript" src="assets/js/config.js"></script>

  <script>
    BUI.use('common/main',function(){
      var config = [{
          id:'menu', 
          homePage : 'leavebill',
          menu:[{
              text:'业务管理',
              items:[
                {id:'leavebill',text:'请假管理',href:'leaveBillAction_home.action'}
              ]
            },{
              text:'流程管理',
              items:[
                {id:'operation',text:'部署管理',href:'workflowAction_deployHome.action'},
                {id:'task',text:'任务管理',href:'workflowAction_listTask.action'}  
              ]
            },{
                text:'组织管理',
                items:[
                  {id:'employee',text:'员工管理',href:'emosUserAction_employeelist.action'},
                  {id:'role',text:'角色管理',href:'roleAction_rolelist.action'},
                  {id:'department',text:'部门管理', href:'departmentAction_departmentlist.action'},
                  {id:'local',text:'地域管理', href:'areaAction_arealist.action'},
                  {id:'dictionary',text:'字典管理', href:'dicttypeAction_dictlist.action'},
                  {id:'machineroom',text:'机房管理', href:'cptRoomAction_cptroomlist.action'},
                ]
              }]
          },{
            id:'detail',
            menu:[{
                text:'系统介绍',
                items:[
                  {id:'code',text:'关于系统',href:'loginAction_welcome.action'},
                  {id:'example',text:'版权归属',href:'loginAction_welcome.action'}
                ]
              }]
          }];
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });
  </script>
 </body>
</html>
