package ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ssh.dao.IDepartmentDao;
import ssh.domain.Department;

public class DepartmentDaoImpl extends HibernateDaoSupport implements IDepartmentDao {

	private IDepartmentDao departmentDao;
	
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	/**
	 *根据部门ID也就是deptid查询出部门对象 
	 **/
	@Override
	public Department findDepDetail(String id) {
		// TODO Auto-generated method stub
		//System.out.println(id);
		String hql = "from Department dep where dep.deptid = ?";
		List<Department> departmentthreelist = this.getHibernateTemplate().find(hql, id);
		Department department = null;
		if(departmentthreelist!=null && departmentthreelist.size()>0){
			department = departmentthreelist.get(0);
		}
		
		return department;
	}

	@Override
	public String saveDepartment(Department dep) {
		
		this.getHibernateTemplate().save(dep);
		String hql = "from Department o where o.id = ?";
		List<Department> list = this.getHibernateTemplate().find(hql, dep.getId());
		String registercondition;
		if(list !=null && list.size()>0){
			registercondition="success";
		}else {
			registercondition="fail";
		}
		return registercondition;
	}
	
	/**查询一级部门列表也就是省公司下的部门列表*/
	@Override
	public List<Department> findOneDepList() {
		String hql = "from Department dep where dep.parentdeptid='1'AND dep.deleted ='0'";
		List<Department> departmentonelist = this.getHibernateTemplate().find(hql);
//		System.out.println(departmentonelist.size());
		return departmentonelist;
	}
	/**查询最外层部门列表*/
	@Override
	public List<Department> findTwoDepList() {
		String hql = "from Department dep where dep.parentdeptid='-1'AND dep.deleted ='0'";
		List<Department> departmenttwolist = this.getHibernateTemplate().find(hql);
		return departmenttwolist;
	}
	/**查询3级部门列表*/
	@Override
	public List<Department> findThreeDepList() {
		
		String hql = "from Department dep where dep.parentdeptid !='-1' AND dep.parentdeptid !='1'AND dep.deleted ='0'";
		List<Department> departmentthreelist = this.getHibernateTemplate().find(hql);
		return departmentthreelist;
	}

	/**根据id删除部门，就是改变部门的deleted状态*/
	@Override
	public String deleteDep(int id) {
		// TODO Auto-generated method stub
		String hql = "update Department dep set dep.deleted ='1' where dep.id ="+id;
		Session session = this.getSession();
		session.createQuery(hql).executeUpdate();
		
		String delectconditon ="";
		//根据id查询出部门对象，并获得deleted标志看是否改变为1，若改变则说明删除成功
		int delected = findDep(id).getDeleted();
		if(delected==1){
			delectconditon="success";
		}else {
			delectconditon="fail";
		}
		
		return delectconditon;
	}
	
	/**根据id删除部门，就是改变部门的deleted状态*/
	@Override
	public String deleteDepbyDeptid(String id) {
		// TODO Auto-generated method stub
		String hql = "update Department dep set dep.deleted ='1' where dep.deptid ="+id;
		Session session = this.getSession();
		session.createQuery(hql).executeUpdate();
		
		String delectconditon ="";
		//根据id查询出部门对象，并获得deleted标志看是否改变为1，若改变则说明删除成功
		int delected = findDepDetail(id).getDeleted();
		if(delected==1){
			delectconditon="success";
		}else {
			delectconditon="fail";
		}
		
		return delectconditon;
	}

	/*
	 *根据id查询出部门对象 
	 **/
	@Override
	public Department findDep(int id) {
		
		System.out.println(id);
		String hql = "from Department dep where dep.id ="+id;
		List<Department> departmentthreelist = this.getHibernateTemplate().find(hql);
		Department department = null;
		if(departmentthreelist!=null && departmentthreelist.size()>0){
			department = departmentthreelist.get(0);
		}
		
		return department;
	}
	
}
