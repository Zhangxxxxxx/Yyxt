package ssh.web.action;

import ssh.domain.Department;
import ssh.domain.EmosUser;
import ssh.service.IDepartmentService;
import ssh.service.IEmosUserService;
import ssh.utils.SessionContext;
import ssh.utils.ValueContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.request.SessionScope;

import com.alibaba.fastjson.JSON;
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
	
	
	
}
