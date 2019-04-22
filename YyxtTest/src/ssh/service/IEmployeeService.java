package ssh.service;

import java.util.List;

import ssh.domain.Department;
import ssh.domain.Employee;


public interface IEmployeeService {

	Employee findEmployeeByName(String name);

	List<String> findEmployeeByManageid(Long manageid, Long departmentid);

	List<String> findEmployRolelist(Long roleid, Long departmentid);

	List<String> findEmployNamelist();
	
	String saveEmploy(String name,String password , String  email ,String department ,String rolename);
	
	String saveEmploy(Employee employee);
	
	List<Employee> findEmploylist();
	
	Employee findEmployeeById(Long id);

	/*List<Department> findOnedepartment();

	List<Department> findTwodepartment();

	List<Department> findThreeDepartment();

	Department finddepartmentdetails(Long id);*/

}
