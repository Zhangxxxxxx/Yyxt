package ssh.service.impl;

import java.util.ArrayList;
import java.util.List;


import ssh.dao.IDepartmentDao;
import ssh.domain.Department;
import ssh.service.IDepartmentService;


public class DepartmentServiceImpl implements IDepartmentService {
	
	private IDepartmentDao departmentDao;
	
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	/**
	 *根据提供的id，找到相对应的部门对象 
	 * 
	 */
	@Override
	public Department finddepartmentdetails(String id) {
		
		Department department = departmentDao.findDepDetail(id);
		
		return department;
	}
	/**
	 *保存编辑或者新添加的部门对象
	 */
	@Override
	public String saveeditDepartment(Department dep) {
		/**/
		
		//编辑成功与否
		String registercondition = departmentDao.saveDepartment(dep);
				
		return registercondition;
	}

		//最外层的部门的集合
		@Override
		public List<Department> findOnedepartment() {

			List<Department> onedeplist = departmentDao.findOneDepList();
			
			return onedeplist;
		}

		//第二层部门的集合
		@Override
		public List<Department> findTwodepartment() {
			
			List<Department> twodeplist = departmentDao.findTwoDepList();
			
			return twodeplist;
		}
		//第三层的部门的集合
		@Override
		public List<Department> findThreeDepartment() {
			
			List<Department> threedeplist = departmentDao.findThreeDepList();
			
			return threedeplist;
		}

		/**
		 *删除该部门
		 */
		@Override
		public String deleteDepartment(int id) {
			String delectconditon = departmentDao.deleteDep(id);

			return delectconditon;
		}

	

}
