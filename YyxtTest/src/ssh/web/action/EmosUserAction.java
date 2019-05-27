package ssh.web.action;

import ssh.domain.Department;
import ssh.domain.EmosUser;
import ssh.domain.EmosWorkFlow;
import ssh.domain.JsonDepartment;
import ssh.domain.Role;
import ssh.domain.SubRole;
import ssh.service.IDepartmentService;
import ssh.service.IEmosUserService;
import ssh.service.IEmosWorkflowService;
import ssh.service.IRoleService;
import ssh.service.ISubRoleService;
import ssh.utils.SessionContext;
import ssh.utils.ValueContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.request.SessionScope;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class EmosUserAction extends ActionSupport implements ModelDriven<EmosUser> {

	private EmosUser emosUser = new EmosUser();

	@Override
	public EmosUser getModel() {
		return emosUser;
	}

	private IDepartmentService departmentService;
	private IEmosUserService emosUserService;
	private IRoleService roleService;
	private IEmosWorkflowService emosWorkflowService;
	private ISubRoleService subRoleService;

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setEmosUserService(IEmosUserService emosUserService) {
		this.emosUserService = emosUserService;
	}
	
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	
	public void setEmosWorkflowService(IEmosWorkflowService emosWorkflowService) {
		this.emosWorkflowService = emosWorkflowService;
	}
	
	public void setSubRoleService(ISubRoleService subRoleService) {
		this.subRoleService = subRoleService;
	}

	/**
	 * 用户管理页先得到部门列表
	 * 
	 * @return
	 */
	public String userlist() {

		List<Department> OneDepartment = departmentService.findOnedepartment();
		List<Department> TwoDepartment = departmentService.findTwodepartment();
		List<Department> ThreeDepartment = departmentService.findThreeDepartment();

		// System.out.println(OneDepartment.size());

		ValueContext.putValueContext("onedepartment", OneDepartment);
		ValueContext.putValueContext("twoDepartment", TwoDepartment);
		ValueContext.putValueContext("threeDepartment", ThreeDepartment);

		return "userlist";
	}

	/**
	 * 根据deptid得到部门下的人员
	 * 
	 * @return
	 */
	public void userByDeptId() throws IOException {

		String deptid = emosUser.getDEPTID();
		System.out.println("得到传递过来的部门id是" + deptid);

		List<EmosUser> userlist = emosUserService.userByDeptId(deptid);

		if (userlist != null) {

			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

			String userStr = JSON.toJSONString(userlist);// 使用fastjson将数据转换成json格式
			PrintWriter writer = ServletActionContext.getResponse().getWriter();

			writer.write(userStr);

			System.out.println(userStr);

			writer.flush();

			writer.close();

		} else {

			System.out.println("失败");

		}
	}

	/**
	 * 得到部门和员工的列表
	 * 
	 * @return
	 * @throws IOException
	 */
	public String employeelist() throws IOException {

		List<Department> OneDepartment = departmentService.findOnedepartment();
		List<Department> TwoDepartment = departmentService.findTwodepartment();
		List<Department> ThreeDepartment = departmentService.findThreeDepartment();
		List<EmosUser> userlist = emosUserService.allemployee();

		List<Department> departmentsall = new ArrayList<>();
		departmentsall.addAll(OneDepartment);
		departmentsall.addAll(TwoDepartment);
		departmentsall.addAll(ThreeDepartment);

		// System.out.println("总共的部门有"+departmentsall.size());
		// 返回一个JSONArray对象
		List<JsonDepartment> departments = new ArrayList<>();
		// 添加部门
		for (int i = 0; i < departmentsall.size(); i++) {
			JsonDepartment jsonDepartment = new JsonDepartment();
			jsonDepartment.setId(departmentsall.get(i).getDeptid());
			jsonDepartment.setpId(departmentsall.get(i).getParentdeptid());
			jsonDepartment.setName(departmentsall.get(i).getDeptname());
			departments.add(jsonDepartment);
		}

		// 添加部门下的人员
		for (int j = 0; j < userlist.size(); j++) {
			JsonDepartment jsonDepartment = new JsonDepartment();
			jsonDepartment.setId(userlist.get(j).getUSERID());
			jsonDepartment.setpId(userlist.get(j).getDEPTID());
			jsonDepartment.setName(userlist.get(j).getUSERNAME());
			departments.add(jsonDepartment);
		}
		JsonDepartment jsonDepartment = new JsonDepartment();
		jsonDepartment.setId("-1");
		jsonDepartment.setpId("00001");
		jsonDepartment.setName("所有部门");
		departments.add(jsonDepartment);

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

		String userStr = JSON.toJSONString(departments);// 使用fastjson将数据转换成json格式

		ValueContext.putValueContext("employeelist", userStr);

		return "employeelist";
	}

	public void deptandmep() throws IOException {

		List<Department> OneDepartment = departmentService.findOnedepartment();
		List<Department> TwoDepartment = departmentService.findTwodepartment();
		List<Department> ThreeDepartment = departmentService.findThreeDepartment();

		List<Department> departmentsall = new ArrayList<>();
		departmentsall.addAll(OneDepartment);
		departmentsall.addAll(TwoDepartment);
		departmentsall.addAll(ThreeDepartment);

		System.out.println("总共的部门有" + departmentsall.size());
		// 返回一个JSONArray对象
		List<JsonDepartment> departments = new ArrayList<>();
		for (int i = 0; i < departmentsall.size(); i++) {
			JsonDepartment jsonDepartment = new JsonDepartment();
			jsonDepartment.setId(departmentsall.get(i).getDeptid());
			jsonDepartment.setpId(departmentsall.get(i).getParentdeptid());
			jsonDepartment.setName(departmentsall.get(i).getDeptname());
			departments.add(jsonDepartment);
		}

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

		String userStr = JSON.toJSONString(departments);// 使用fastjson将数据转换成json格式
		PrintWriter writer = ServletActionContext.getResponse().getWriter();

		writer.write(userStr);
		System.out.println(userStr);
		writer.flush();
		writer.close();

	}

	public String deletedeporemp() throws IOException {

		String registerconditon = "";
		// 获取传递过来的要删除的部门id
		String id = emosUser.getID();
		System.out.println("要删除的部门或人员的ID" + id);

		// 使用正则判断传递过来的id是否为数字，如果为数字则说明是部门节点，如果不是则是员工节点
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(id);
		String delectconditon;
		if (!isNum.matches()) {
			System.out.print("此id为员工");
			delectconditon = emosUserService.deleteUser(id);
		} else {
			delectconditon = departmentService.deleteDepartmentByDeptid(id);
		}
		if (delectconditon == "success") {
			System.out.print("删除成功");
		} else {
			System.out.print("删除失败");
		}

		return "updatedeporuser";

	}

	public void userByUserId() throws IOException {

		String registerconditon = "";
		// 获取传递过来员工id
		String id = emosUser.getUSERID();
		System.out.println("人员的USERID" + id);
		List<EmosUser> userslist = emosUserService.userByUserID(id);

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

		String userStr = JSON.toJSONString(userslist);// 使用fastjson将数据转换成json格式
		PrintWriter writer = ServletActionContext.getResponse().getWriter();

		writer.write(userStr);
		System.out.println(userStr);
		writer.flush();
		writer.close();
	}
	
	/**
	 *添加员工 
	 */
	public String adddemployee() {
		
		String userid = emosUser.getUSERID();
		String name = emosUser.getUSERNAME();
		String deptid = emosUser.getDEPTID();
		String deptname = emosUser.getDEPTNAME();
		String email = emosUser.getEMAIL();
		String mobile = emosUser.getMOBILE();
		String phone = emosUser.getPHONE();
		String remark = emosUser.getREMARK();
		
		System.out.println("添加的员工名称是" + name);
		System.out.println("添加的部门名称是" + deptname);
		System.out.println("添加的部门的id" + deptid);
		System.out.println("添加的员工email" + email);
		System.out.println("添加的员工电话是" + mobile);
		System.out.println("添加的员工座机是" + phone);
		
		String uuid = UUID.randomUUID().toString();
		
		EmosUser emosuser = new EmosUser();
		emosuser.setID(uuid);
		emosuser.setDELETED(0);
		emosuser.setUSERNAME(name);
		emosuser.setUSERID(userid);
		emosuser.setDEPTID(deptid);
		emosuser.setDEPTNAME(deptname);
		emosuser.setEMAIL(email);
		emosuser.setMOBILE(mobile);
		emosuser.setPHONE(phone);
		emosuser.setREMARK(remark);
		
		String registerconditon = emosUserService.saveeditDepartment(emosuser);
		System.out.println("员工信息更新" + registerconditon);
		
		return "updatedeporuser";
	}
	
	public void subrole() throws IOException {

		List<Role> rolelist = roleService.findRoleSyssublist();
		List<SubRole> subRolelist = subRoleService.findRoleNamelist();
		// 返回一个JSONArray对象
		List<JsonDepartment> areas = new ArrayList<>();
		// 添加部门
		for (int i = 0; i < rolelist.size(); i++) {
			JsonDepartment jsonDepartment = new JsonDepartment();
			jsonDepartment.setId(rolelist.get(i).getRole_id());
			jsonDepartment.setpId(rolelist.get(i).getParent_id());
			jsonDepartment.setName(rolelist.get(i).getRole_name());
			areas.add(jsonDepartment);
		}
		
		//取subrole查询出的集合最后7个
		for (int j = subRolelist.size()-7; j < subRolelist.size(); j++) {
			JsonDepartment jsonDepartment = new JsonDepartment();
			jsonDepartment.setId(subRolelist.get(j).getDeptid());
			jsonDepartment.setpId(subRolelist.get(j).getRoleid());
			jsonDepartment.setName(subRolelist.get(j).getSubrolename());
			areas.add(jsonDepartment);
		}
		
		JsonDepartment jsonDepartment = new JsonDepartment();
		jsonDepartment.setId("0");
		jsonDepartment.setpId("0000");
		jsonDepartment.setName("系统角色");
		areas.add(jsonDepartment);

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

		String userStr = JSON.toJSONString(areas);// 使用fastjson将数据转换成json格式
		PrintWriter writer = ServletActionContext.getResponse().getWriter();

		writer.write(userStr);
		System.out.println(userStr);
		writer.flush();
		writer.close();
	}
	

}
