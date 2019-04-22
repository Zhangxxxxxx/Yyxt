package ssh.dao;

import java.util.List;

import ssh.domain.Department;
import ssh.domain.Employee;


public interface IEmployeeDao {

	Employee findEmployeeByName(String name);

	List<String> findEmployeeByManageid(Long manageid, Long departmentid);

	List<String> findEmployeelistByRoleid(Long roleid, Long departmentid);

	List<String> findEmployeeNamelist();
	
	List<Employee> findEmployeelist();
	
	String saveEmployee(Employee employee);
	
	Employee findEmployeeById(Long id);

	/*List<Department> findOneDepList();
	
	List<Department> findTwoDepList();
	
	List<Department> findThreeDepList();

	Department findDepDetail(Long id);*/
}
