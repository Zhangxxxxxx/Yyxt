package ssh.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ssh.dao.ICptRoomDao;
import ssh.dao.IDicttypeDao;
import ssh.dao.IRoleDao;
import ssh.domain.CptRoom;
import ssh.domain.Dicttype;
import ssh.domain.Role;

public class RoleDaoImpl extends HibernateDaoSupport implements IRoleDao{
	
	private IRoleDao roleDao;
	
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> allrole() {
		// TODO Auto-generated method stub
		String hql = "from Role role where role.deleted='0'";
		
		List<Role> rolelist = this.getHibernateTemplate().find(hql);
						
		return rolelist;
	}

	@Override
	public List<Role> syssubrole() {
		// TODO Auto-generated method stub
		String hql = "from Role role where role.workflow_flag='0'";
		
		List<Role> rolelist = this.getHibernateTemplate().find(hql);
						
		return rolelist;
	}

	

}
