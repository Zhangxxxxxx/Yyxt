package ssh.web.action;

import ssh.domain.Department;
import ssh.domain.EmosUser;
import ssh.domain.JsonDepartment;
import ssh.service.IDepartmentService;
import ssh.service.IEmosUserService;
import ssh.utils.SessionContext;
import ssh.utils.ValueContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public void setEmosUserService(IEmosUserService emosUserService) {
		this.emosUserService =emosUserService;
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
		
		//System.out.println(OneDepartment.size());
		
		ValueContext.putValueContext("onedepartment", OneDepartment);
		ValueContext.putValueContext("twoDepartment", TwoDepartment);
		ValueContext.putValueContext("threeDepartment", ThreeDepartment);
		
		return "userlist";
	}

	/**
	 * 根据deptid得到部门下的人员
	 * @return
	 */
	public void userByDeptId() throws IOException {
		
		String deptid = emosUser.getDEPTID();
		System.out.println("得到传递过来的部门id是"+deptid);
		
		List<EmosUser> userlist = emosUserService.userByDeptId(deptid);

		if(userlist != null) {

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		
		String userStr =JSON.toJSONString(userlist);//使用fastjson将数据转换成json格式
		PrintWriter writer =  ServletActionContext.getResponse().getWriter();
		
		writer.write(userStr);

		System.out.println(userStr);

		writer.flush();

		writer.close();

		}else {

		System.out.println("失败");

		}
	}
	
	/**
	 *得到部门和员工的列表
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
		
		//System.out.println("总共的部门有"+departmentsall.size());
		 // 返回一个JSONArray对象  
		List<JsonDepartment> departments = new ArrayList<>();
		//添加部门
		for (int i = 0; i < departmentsall.size(); i++) {
			JsonDepartment jsonDepartment = new JsonDepartment();
			jsonDepartment.setId(departmentsall.get(i).getDeptid());
			jsonDepartment.setpId(departmentsall.get(i).getParentdeptid());
			jsonDepartment.setName(departmentsall.get(i).getDeptname());
			departments.add(jsonDepartment);
		}
		
		//添加部门下的人员
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
		
		String userStr =JSON.toJSONString(departments);//使用fastjson将数据转换成json格式
		
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
		
		System.out.println("总共的部门有"+departmentsall.size());
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
		
		String userStr =JSON.toJSONString(departments);//使用fastjson将数据转换成json格式
		PrintWriter writer =  ServletActionContext.getResponse().getWriter();
		
		writer.write(userStr);
		System.out.println(userStr);
		writer.flush();
		writer.close();
		
	}
	
	
	
}
