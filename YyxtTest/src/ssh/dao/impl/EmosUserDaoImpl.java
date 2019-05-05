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
		String hql = "from EmosUser user where user.DELETED ='0'";
		
		List<EmosUser> userlist = this.getHibernateTemplate().find(hql);
		
		return userlist;
	}

	/**根据userid删除员工，就是改变员工的deleted状态*/
	@Override
	public String deleteUserid(String id) {
		String hql = "update EmosUser user set user.DELETED ='1' where user.USERID ="+"'"+id+"'";
		Session session = this.getSession();
		session.createQuery(hql).executeUpdate();
		
		String delectconditon ="";
		//根据id查询出部门对象，并获得deleted标志看是否改变为1，若改变则说明删除成功
		int delected = userByUserId(id).get(0).getDELETED();
		if(delected==1){
			delectconditon="success";
		}else {
			delectconditon="fail";
		}
		
		return delectconditon;
	}

	//根据userid查询员工
	@Override
	public List<EmosUser> userByUserId(String userid) {
		String hql = "from EmosUser user where user.USERID = ?";
		
		List<EmosUser> userlist = this.getHibernateTemplate().find(hql, userid);
		
		return userlist;
	}

	@Override
	public String saveDepartment(EmosUser emosuser) {
		
		String hql = "from EmosUser user where user.USERID = ?";
		List<EmosUser> emosuserlist = this.getHibernateTemplate().find(hql, emosuser.getUSERID());
		String registercondition;
		if(emosuserlist !=null && emosuserlist.size()>0){
			registercondition="success";
		}else {
			registercondition="fail";
		}
		return registercondition;
	}
}
