package ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ssh.dao.IEmployeeDao;
import ssh.domain.Department;
import ssh.domain.Employee;

public class EmployeeDaoImpl extends HibernateDaoSupport implements IEmployeeDao {
	
	/**使用用户名作为查询条件，查询用户对象*/
	@Override
	public Employee findEmployeeByName(String name) {
		String hql = "from Employee o where o.name = ?";
		List<Employee> list = this.getHibernateTemplate().find(hql, name);
		Employee employee = null;
		if(list!=null && list.size()>0){
			employee = list.get(0);
		}
		return employee;
	}

	/**使用用户managerid作为查询条件，查询用户上级领导*/
	@Override
	public List<String> findEmployeeByManageid(Long manageid,Long departmentid) {
		String hql = "from Employee o where o.roleid = ?";
		List<Employee> rolelist = this.getHibernateTemplate().find(hql, manageid);
		Employee employee = null;
		List<String> list = new ArrayList<>();
		if(rolelist!=null && rolelist.size()>0){
			for (int i = 0; i < rolelist.size(); i++) {
				//判断所分派的上级是否是同一部门，筛选出同一部门的上级角色进行分配
				if(rolelist.get(i).getDepartmentid().equals(departmentid)){
					list.add(rolelist.get(i).getName());
					System.out.println("分派的领导是"+rolelist.get(i).getName());
				}
				
			}
		}
		return list;
	}
	
	/**使用用户roleid作为查询条件，查询用户同级同部门人员*/
	@Override
	public List<String> findEmployeelistByRoleid(Long roleid, Long departmentid) {
		// TODO Auto-generated method stub
		String hql = "from Employee o where o.roleid = ?";
		List<Employee> rolelist = this.getHibernateTemplate().find(hql, roleid);
		Employee employee = null;
		List<String> list = new ArrayList<>();
		if(rolelist!=null && rolelist.size()>0){
			for (int i = 0; i < rolelist.size(); i++) {
				//判断所分派的上级是否是同一部门，筛选出同一部门的上级角色进行分配
				if(rolelist.get(i).getDepartmentid().equals(departmentid)){
					list.add(rolelist.get(i).getName());
					System.out.println("同部门同级员工是"+rolelist.get(i).getName());
				}
				
			}
		}
		
		return list;
	}

	/**查询出所有人员的姓名*/
	@Override
	public List<String> findEmployeeNamelist() {
		// TODO Auto-generated method stub
		String hql = "from Employee o where 1=1";
		List<Employee> namelist = this.getHibernateTemplate().find(hql);
		List<String> list = new ArrayList<>();
		if(namelist!=null && namelist.size()>0){
			for (int i = 0; i < namelist.size(); i++) {
					list.add(namelist.get(i).getName());
//					System.out.println("员工姓名"+namelist.get(i).getName());
				
			}
		}
		return list;
	}

	/**保存新注册的员工信息*/
	@Override
	public String saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(employee);
		String hql = "from Employee o where o.name = ?";
		List<Employee> list = this.getHibernateTemplate().find(hql, employee.getName());
		
		String registercondition;
		if(list !=null && list.size()>0){
			registercondition="success";
		}else {
			registercondition="fail";
		}
		return registercondition;
	}

	@Override
	public List<Employee> findEmployeelist() {
		// TODO Auto-generated method stub
		String hql = "from Employee o where 1=1";
		List<Employee> employeelist = this.getHibernateTemplate().find(hql);
		
		return employeelist;
	}
	
	/**使用用户ID作为查询条件，查询用户对象*/
	@Override
	public Employee findEmployeeById(Long id) {
		String hql = "from Employee o where o.id = ?";
		List<Employee> list = this.getHibernateTemplate().find(hql, id);
		Employee employee = null;
		if(list!=null && list.size()>0){
			employee = list.get(0);
		}
		return employee;
	}

	/**查询一级部门列表*/
	/*@Override
	public List<Department> findOneDepList() {
		String hql = "from Department dep where dep.parentdeptid='1'";
		List<Department> departmentonelist = this.getHibernateTemplate().find(hql);
//		System.out.println(departmentonelist.size());
		return departmentonelist;
	}

	@Override
	public List<Department> findTwoDepList() {
		String hql = "from Department dep where dep.parentdeptid='-1'";
		List<Department> departmenttwolist = this.getHibernateTemplate().find(hql);
		return departmenttwolist;
	}

	@Override
	public List<Department> findThreeDepList() {
		
		String hql = "from Department dep where dep.parentdeptid !='-1' AND dep.parentdeptid !='1'";
		List<Department> departmentthreelist = this.getHibernateTemplate().find(hql);
		return departmentthreelist;
	}

	@Override
	public Department findDepDetail(Long id) {
		// TODO Auto-generated method stub
		System.out.println(id);
		String hql = "from Department dep where dep.parentdeptid = ?";
		List<Department> departmentthreelist = this.getHibernateTemplate().find(hql, id.toString());
		Department department = null;
		if(departmentthreelist!=null && departmentthreelist.size()>0){
			department = departmentthreelist.get(0);
		}
		
		return department;
	}*/
	
}
