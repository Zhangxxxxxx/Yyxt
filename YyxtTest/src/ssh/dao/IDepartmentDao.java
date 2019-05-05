package ssh.dao;

import java.util.List;

import ssh.domain.Department;


public interface IDepartmentDao {
	
	//根据部门id查询部门对象
	Department findDepDetail(String id);
	
	//根据id查询部门对象
	Department findDep(int id);
	
	String saveDepartment(Department dep);

	List<Department> findOneDepList();

	List<Department> findTwoDepList();

	List<Department> findThreeDepList();

	String deleteDep(int id);

	String deleteDepbyDeptid(String id);

}
