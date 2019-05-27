package ssh.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ssh.dao.ICptRoomDao;
import ssh.dao.IDicttypeDao;
import ssh.dao.IRoleDao;
import ssh.dao.ISubRoleDao;
import ssh.domain.CptRoom;
import ssh.domain.Dicttype;
import ssh.domain.Role;
import ssh.domain.SubRole;

public class SubRoleDaoImpl extends HibernateDaoSupport implements ISubRoleDao{
	
	private ISubRoleDao subRoleDao;
	
	public void setSubRoleDao(ISubRoleDao subRoleDao) {
		this.subRoleDao = subRoleDao;
	}

	@Override
	public List<SubRole> allrole() {
		// TODO Auto-generated method stub
		
		String hql = "from SubRole subrole where subrole.parentid='100'";
		
		List<SubRole> rolelist = this.getHibernateTemplate().find(hql);
						
		return rolelist;
	}

	@Override
	public List<SubRole> syssubrole() {
		// TODO Auto-generated method stub
		return null;
	}



	

}
