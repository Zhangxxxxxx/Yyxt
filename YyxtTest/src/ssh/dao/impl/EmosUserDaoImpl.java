package ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ssh.dao.IEmosUserDao;
import ssh.domain.Department;
import ssh.domain.EmosUser;

public class EmosUserDaoImpl extends HibernateDaoSupport implements IEmosUserDao {

	private IEmosUserDao emosUserDao;
	
	public void setEmosUserDao(IEmosUserDao emosUserDao) {
		this.emosUserDao = emosUserDao;
	}
	
	@Override
	public List<EmosUser> userByDeptId(String deptid) {
		
		String hql = "from EmosUser user where user.DEPTID = ?";
		
		List<EmosUser> userlist = this.getHibernateTemplate().find(hql, deptid);
		
		return userlist;
	}

	@Override
	public List<EmosUser> allemployee() {
		// TODO Auto-generated method stub
		String hql = "from EmosUser user where 1 = 1";
		
		List<EmosUser> userlist = this.getHibernateTemplate().find(hql);
		
		return userlist;
	}
	
	
}
