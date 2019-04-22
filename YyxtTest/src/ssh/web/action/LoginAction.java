package ssh.web.action;

import ssh.domain.Department;
import ssh.domain.Employee;
import ssh.domain.LeaveBill;
import ssh.service.IEmployeeService;
import ssh.utils.SessionContext;
import ssh.utils.ValueContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.request.SessionScope;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements ModelDriven<Employee> {

	private Employee employee = new Employee();

	@Override
	public Employee getModel() {
		return employee;
	}

	private IEmployeeService employeeService;

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * 登录
	 * 
	 * @return
	 * @throws IOException
	 */
	public String login() throws IOException {
		// 1：获取用户名
		String name = employee.getName();
		String password = employee.getPassword();
		// 2：使用用户名作为查询条件，查询员工表，获取当前用户名对应的信息
		Employee emp = employeeService.findEmployeeByName(name);

		// 在action方法中，方法的返回里
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (emp != null) {
			// 判断密码是否正确
			if (emp.getPassword().equals(password)) {
				// 3：将查询的对象（惟一）放置到Session中
				SessionContext.setUser(emp);
				return "success";
			} else {
				out.print("<script>alert('密码错误，请重新登录');history.go(-1);</script>");
				return null;
			}
		} else {
			out.print("<script>alert('用户名错误，请重新登录');history.go(-1);</script>");
			return null;
		}

	}

	/**
	 * 标题
	 * 
	 * @return
	 */
	public String top() {
		return "top";
	}

	/**
	 * 左侧菜单
	 * 
	 * @return
	 */
	public String left() {
		return "left";
	}

	/**
	 * 主页显示
	 * 
	 * @return
	 */
	public String welcome() {
		return "welcome";
	}

	/** 退出系统 */
	public String logout() {
		// 清空Session
		SessionContext.setUser(null);
		return "login";
	}

	/**
	 * 注册
	 * 
	 * @return
	 */
	public String register() {

		return "register";
	}

	/**
	 * 忘记密码
	 * 
	 * @return
	 */
	public String forgetpw() {

		return "forgetpw";
	}

	/**
	 * 提交注册保存注册的用户
	 * 
	 * @return
	 * @throws IOException
	 */
	public String save() throws IOException {

		// 1：获取用户名
		String name = employee.getName();
		System.out.println("注册的用户姓名" + name);
		String password = employee.getPassword();
		String email = employee.getEmail();
		String department = employee.getDepartment();
		String rolename = employee.getRole();
		System.out.println("注册的员工的角色" + rolename);
		String registerconditon = employeeService.saveEmploy(name, password, email, department, rolename);
		System.out.println(registerconditon);
		// 在action方法中，方法的返回里
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (registerconditon == "success") {
			out.print("<script>alert('注册成功'); window.location='login2.jsp' ;window.close();</script>");
		} else {
			out.print("<script>alert('注册失败,请稍后再试' );window.location='login2.jsp' ;window.close();</script>");
		}
		response.getWriter().flush();

		return null;
	}

	/**
	 * 忘记密码后修改新的密码
	 * 
	 * @return
	 * @throws IOException
	 */
	public String updatepw() throws IOException {

		// 1：获取用户名
		String name = employee.getName();
		String password = employee.getPassword();
		System.out.println("用户名称是" + name);
		System.out.println("更新后的密码是" + password);
		// 2：使用用户名作为查询条件，查询员工表，获取当前用户名对应的信息
		Employee emp = employeeService.findEmployeeByName(name);
		// 设置新的密码
		emp.setPassword(password);
		String registerconditon = employeeService.saveEmploy(emp);
		System.out.println("密码更新" + registerconditon);
		// 在action方法中，方法的返回里

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (registerconditon == "success") {
			out.print("<script>alert('密码重新修改成功'); window.location='login2.jsp' ;window.close();</script>");
		} else {
			out.print("<script>alert('修改密码失败,请稍后再试' );window.location='login2.jsp' ;window.close();</script>");
		}
		response.getWriter().flush();
		return null;

	}

	/**
	 * 员工管理页
	 * 
	 * @return
	 */
	public String employeelist() {
		// 1：查询所有的员工信息（对应a_employee），返回List<Employee>
		List<Employee> list = employeeService.findEmploylist();
		String sessionname =   SessionContext.get().getName();
		//System.out.println("当前登录用户名:"+sessionname);
		// 放置到上下文对象中
		ValueContext.putValueContext("list", list);
		ValueContext.putValueContext("sessionname", sessionname);
		
		return "employeelist";
	}

	/**
	 * 员工编辑页
	 * 
	 * @return
	 */
	public String editor() {
		Long id = employee.getId();
		System.out.println("编辑的用户的ID是" + id);
		// 2：使用用户id作为查询条件，查询员工表，获取当前用户名对应的信息
		Employee emp = employeeService.findEmployeeById(id);
		// 放置到上下文对象中
		ValueContext.putValueContext("employee", emp);

		return "editor";
	}

	/**
	 * 保存员工编辑页
	 * 
	 * @return
	 */
	public String saveeditor() {
		Long id = employee.getId();
		System.out.println("该编辑保存的用户的ID是" + id);
		// 2：使用用户id作为查询条件，查询员工表，获取当前用户名对应的信息
		Employee emp = employeeService.findEmployeeById(id);

		emp.setName(employee.getName());
		emp.setPassword(employee.getPassword());
		emp.setEmail(employee.getEmail());
		emp.setRole(employee.getRole());

		String rolename = employee.getRole();
		emp.setRole(rolename);
		if (rolename.equals("员工")) {
			emp.setRoleid((long) 0);
			emp.setManagerid((long) 2);
		} else if (rolename.equals("项目经理助理")) {
			emp.setRoleid((long) 2);
			emp.setManagerid((long) 1);
		} else {
			emp.setRoleid((long) 1);
		}

		String department = employee.getDepartment();
		if (department.equals("电子运维")) {
			emp.setDepartmentid((long) 1);
		} else if (department.equals("代维")) {
			emp.setDepartmentid((long) 2);
		} else {
			emp.setDepartmentid((long) 3);
		}
		
		String registerconditon = employeeService.saveEmploy(emp);
		System.out.println("员工信息更新" + registerconditon);
		employeelist();
		
		return "employeelist";
	}
	
	/**
	 * 部门列表管理页
	 * 
	 * @return
	 */
	/*public String departmentlist() {
		
		List<Department> OneDepartment = employeeService.findOnedepartment();
		List<Department> TwoDepartment = employeeService.findTwodepartment();
		List<Department> ThreeDepartment = employeeService.findThreeDepartment();
		
		ValueContext.putValueContext("onedepartment", OneDepartment);
		ValueContext.putValueContext("twoDepartment", TwoDepartment);
		ValueContext.putValueContext("threeDepartment", ThreeDepartment);
		
		return "departmentlist";
	}*/
	
}
