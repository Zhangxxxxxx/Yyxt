package ssh.web.action;

import ssh.domain.Department;
import ssh.domain.Employee;
import ssh.domain.LeaveBill;
import ssh.service.IDepartmentService;
import ssh.service.IEmployeeService;
import ssh.utils.SessionContext;
import ssh.utils.ValueContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.request.SessionScope;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {

	private Department department = new Department();

	@Override
	public Department getModel() {
		return department;
	}

	private IDepartmentService departmentService;

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	/**
	 * 部门列表管理页
	 * 
	 * @return
	 */
	public String departmentlist() {
		
		List<Department> OneDepartment = departmentService.findOnedepartment();
		List<Department> TwoDepartment = departmentService.findTwodepartment();
		List<Department> ThreeDepartment = departmentService.findThreeDepartment();
		
		ValueContext.putValueContext("onedepartment", OneDepartment);
		ValueContext.putValueContext("twoDepartment", TwoDepartment);
		ValueContext.putValueContext("threeDepartment", ThreeDepartment);
		
		return "departmentlist";
	}

	/**
	 * 编辑部门并保存
	 * @return
	 */
	public String savedepteditor() {
		
		String id = department.getDeptid();
		System.out.println("该部门ID是" + id);
		//System.out.println("该部门领导是" + department.getDeptmanager());
		if(id !=null){
			// 2：使用用户id作为查询条件
			Department dep = departmentService.finddepartmentdetails(id);
			
			dep.setDeptname(department.getDeptname());
			dep.setDeptemail(department.getDeptemail());
			dep.setDeptmanager(department.getDeptmanager());
			dep.setDeptmobile(department.getDeptmobile());
			dep.setDeptphone(department.getDeptphone());
			dep.setRemark(department.getRemark());
			
			String registerconditon = departmentService.saveeditDepartment(dep);
			System.out.println("部门信息更新" + registerconditon);
		}
		
		return "updatedep";
	}
	
	public String delete() throws IOException {
		
		String registerconditon="";
		//获取传递过来的要删除的部门id
		int id = department.getId();
		System.out.println("要删除的部门的ID"+id);
		String delectconditon = departmentService.deleteDepartment(id);
		if (delectconditon == "success") {
			System.out.print("删除成功");
		} else {
			System.out.print("删除失败");
		}
		
		return "updatedep";
		
	}
	
	/**
	 *添加部门 
	 */
	public String adddepartment() {
		String name = department.getDeptname();
		String parentid = department.getParentdeptid();
		String email = department.getDeptemail();
		String departmentmanager = department.getDeptmanager();
		String mobile = department.getDeptmobile();
		String phone = department.getDeptphone();
		String remark = department.getRemark();
		
		System.out.println("添加的部门名称是" + name);
		System.out.println("添加的部门父部门id" + parentid);
		System.out.println("添加的部门email" + email);
		System.out.println("添加的部门领导是" + departmentmanager);
		System.out.println("添加的部门电话是" + mobile);
		System.out.println("添加的部门座机是" + phone);
		
		Random df = new Random();
		int id = df.nextInt(1001);
		String deptid = df.nextInt(100001)+"";
		String parentlinkid = deptid.substring(4);
		
		Department dep = new Department();
		dep.setId(id);
		dep.setDeleted(0);
		dep.setDeptemail(email);
		dep.setDeptid(deptid);
		dep.setDeptmanager(departmentmanager);
		dep.setDeptmobile(mobile);
		dep.setDeptname(name);
		dep.setDeptphone(phone);
		dep.setLinkid(deptid);
		dep.setTmpdeptid(deptid);
		dep.setParentdeptid(parentid);
		dep.setParentlinkid(parentlinkid);
		dep.setIspartners(0+"");
		dep.setIsdaiweiroot(1+"");
		dep.setRemark(remark);
		String registerconditon = departmentService.saveeditDepartment(dep);
		System.out.println("部门信息更新" + registerconditon);
		
		
		
		return "updatedep";
	}
	
}
