package ssh.service.impl;

import java.util.ArrayList;
import java.util.List;

import ssh.dao.IEmployeeDao;
import ssh.domain.Department;
import ssh.domain.Employee;
import ssh.service.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService {

	private IEmployeeDao employeeDao;
//	private IDepartmentDao departmentDao;

	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	/*public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}*/

	/** 使用用户名作为查询条件，查询用户对象 */
	@Override
	public Employee findEmployeeByName(String name) {
		Employee employee = employeeDao.findEmployeeByName(name);
		return employee;
	}

	/** 使用用户管理者id作为查询条件，查询用户有关的领导对象 */
	@Override
	public List<String> findEmployeeByManageid(Long manageid, Long departmentid) {
		// TODO Auto-generated method stub
		List<String> ListManagerName = employeeDao.findEmployeeByManageid(manageid, departmentid);
		return ListManagerName;
	}

	/** 使用用户的角色id作为查询条件，查询用户同部门的员工 */
	@Override
	public List<String> findEmployRolelist(Long roleid, Long departmentid) {

		List<String> ListRoleName = employeeDao.findEmployeelistByRoleid(roleid, departmentid);

		return ListRoleName;
	}

	/** 查询所有的员工 */
	@Override
	public List<String> findEmployNamelist() {
		
		List<String> ListAllName = employeeDao.findEmployeeNamelist();
		return ListAllName;
	}

	/**注册并保存新员工*/
	@Override
	public String saveEmploy(String name, String password, String email, String department, String rolename) {
		// TODO Auto-generated method stub
		Employee employee = new Employee();
		int employeeid = employeeDao.findEmployeelist().size()+1;//新注册的员工id为获取当前所有员工数加1
		employee.setId((long)employeeid);
		employee.setName(name);
		employee.setPassword(password);
		employee.setEmail(email);
		employee.setDepartment(department);
		if(department.equals("电子运维")){
			employee.setDepartmentid((long) 1);
		}
		else if (department.equals("代维")) {
			employee.setDepartmentid((long) 2);
		}else {
			employee.setDepartmentid((long) 3);
		}
		
		employee.setRole(rolename);
		if(rolename.equals("员工")){
			employee.setRoleid((long) 0);
			employee.setManagerid((long)2);
			
		}
		else if (rolename.equals("项目经理助理")) {
			employee.setRoleid((long) 2);
			employee.setManagerid((long)1);
		}else {
			employee.setRoleid((long) 1);
		}
		
		//注册成功与否
		String registercondition = employeeDao.saveEmployee(employee);
		
		return registercondition;
	}

	@Override
	public String saveEmploy(Employee employee) {
		//修改信息是否成功
		String registercondition = employeeDao.saveEmployee(employee);
		return registercondition;
	}

	/**查找出所有员工的信息*/
	@Override
	public List<Employee> findEmploylist() {
		// TODO Auto-generated method stub
		List<Employee> ListAllEmployee = employeeDao.findEmployeelist();
		return ListAllEmployee;
	}

	/** 使用用户名作为查询条件，查询用户对象 */
	@Override
	public Employee findEmployeeById(Long id) {
		Employee employee = employeeDao.findEmployeeById(id);
		return employee;
	}
	/*//最外层的部门的集合
	@Override
	public List<Department> findOnedepartment() {

		List<Department> onedeplist = employeeDao.findOneDepList();
		
		return onedeplist;
	}

	//第二层部门的集合
	@Override
	public List<Department> findTwodepartment() {
		
		List<Department> twodeplist = employeeDao.findTwoDepList();
		
		return twodeplist;
	}
	//第三层的部门的集合
	@Override
	public List<Department> findThreeDepartment() {
		
		List<Department> threedeplist = employeeDao.findThreeDepList();
		
		return threedeplist;
	}

	//根据部门id请求得到数据
	@Override
	public Department finddepartmentdetails(Long id) {
	
		Department department = employeeDao.findDepDetail(id);
		
		return department;
	}*/

}
