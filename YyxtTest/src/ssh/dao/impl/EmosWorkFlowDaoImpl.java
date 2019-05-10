package ssh.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ssh.dao.ICptRoomDao;
import ssh.dao.IDicttypeDao;
import ssh.dao.IEmosWorkFlowDao;
import ssh.dao.IRoleDao;
import ssh.domain.CptRoom;
import ssh.domain.Dicttype;
import ssh.domain.EmosWorkFlow;
import ssh.domain.Role;

public class EmosWorkFlowDaoImpl extends HibernateDaoSupport implements IEmosWorkFlowDao{
	
	private IEmosWorkFlowDao emosWorkFlowDao;
	
	public void setEmosWorkFlowDao(IEmosWorkFlowDao emosWorkFlowDao) {
		this.emosWorkFlowDao = emosWorkFlowDao;
	}

	@Override
	public List<EmosWorkFlow> allworkflow() {
		// TODO Auto-generated method stub
		String hql = "from EmosWorkFlow workflow where 1=1";
		
		List<EmosWorkFlow> emosWorkFlowslist = this.getHibernateTemplate().find(hql);
						
		return emosWorkFlowslist;
	}

	

	

}
