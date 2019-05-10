package ssh.web.action;

import ssh.dao.IEmosWorkFlowDao;
import ssh.domain.Area;
import ssh.domain.EmosWorkFlow;
import ssh.domain.JsonDepartment;
import ssh.domain.Role;
import ssh.service.IAreaService;
import ssh.service.IEmosWorkflowService;
import ssh.service.IRoleService;
import ssh.utils.SessionContext;
import ssh.utils.ValueContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.request.SessionScope;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class RoleAction extends ActionSupport implements ModelDriven<Role> {

	private Role role = new Role();

	@Override
	public Role getModel() {
		return role;
	}

	private IRoleService roleService;

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	
	private IEmosWorkflowService emosWorkflowService;

	public void setEmosWorkflowService(IEmosWorkflowService emosWorkflowService) {
		this.emosWorkflowService = emosWorkflowService;
	}
	
	/**
	 * 角色列表
	 * 
	 * @return
	 */
	public String rolelist() {
		
		List<Role> rolelist = roleService.findRoleNamelist();
		List<EmosWorkFlow> emosWorkFlowslist = emosWorkflowService.findWorkflowlist();
		// 返回一个JSONArray对象
		List<JsonDepartment> areas = new ArrayList<>();
		// 添加部门
		for (int i = 0; i < rolelist.size(); i++) {
			JsonDepartment jsonDepartment = new JsonDepartment();
			jsonDepartment.setId(rolelist.get(i).getRole_id());
			jsonDepartment.setpId(rolelist.get(i).getWorkflow_flag());
			jsonDepartment.setName(rolelist.get(i).getRole_name());
			areas.add(jsonDepartment);
		}
		
		for (int i = 0; i < emosWorkFlowslist.size(); i++) {
			JsonDepartment jsonDepartment = new JsonDepartment();
			jsonDepartment.setId(emosWorkFlowslist.get(i).getFlowid());
			jsonDepartment.setpId("00002");
			jsonDepartment.setName(emosWorkFlowslist.get(i).getRemark());
			areas.add(jsonDepartment);
		}
		
		JsonDepartment jsonDepartment = new JsonDepartment();
		jsonDepartment.setId("0");
		jsonDepartment.setpId("0000");
		jsonDepartment.setName("系统角色");
		areas.add(jsonDepartment);
		
		JsonDepartment jsonDepartment2 = new JsonDepartment();
		jsonDepartment2.setId("00002");
		jsonDepartment2.setpId("0000");
		jsonDepartment2.setName("流程角色");
		areas.add(jsonDepartment2);
		

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

		String userStr = JSON.toJSONString(areas);// 使用fastjson将数据转换成json格式
		System.out.println(userStr);

		ValueContext.putValueContext("rolelist", userStr);
		
		return "rolelist";
	}
	
}
