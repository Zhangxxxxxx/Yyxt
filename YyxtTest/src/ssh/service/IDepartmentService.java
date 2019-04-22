package ssh.service;

import java.util.List;

import ssh.domain.Department;
import ssh.domain.Employee;


public interface IDepartmentService {

	Department finddepartmentdetails(String id);

	String saveeditDepartment(Department dep);

	List<Department> findOnedepartment();

	List<Department> findTwodepartment();

	List<Department> findThreeDepartment();

	String deleteDepartment(int id);

}
