package ssh.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ssh.dao.ICptRoomDao;
import ssh.dao.IDicttypeDao;
import ssh.domain.CptRoom;
import ssh.domain.Dicttype;

public class CptRoomDaoImpl extends HibernateDaoSupport implements ICptRoomDao{
	
	private ICptRoomDao cptRoomDao;
	
	public void setCptRoomDao(ICptRoomDao cptRoomDao) {
		this.cptRoomDao = cptRoomDao;
	}

	@Override
	public List<CptRoom> allcptroom() {
		// 查询出所有的字典对象并按照id的倒序排序
		String hql = "from CptRoom cptroom where cptroom.deleted='0' ORDER BY cptroom.id DESC";
						
		List<CptRoom> cptRoomslist = this.getHibernateTemplate().find(hql);
						
		return cptRoomslist;
	}

	@Override
	public List<CptRoom> cptRoomByid(String id) {
		// TODO Auto-generated method stub
		String hql = "from CptRoom cptroom where cptroom.id="+id;
		List<CptRoom> cptRoomslist = this.getHibernateTemplate().find(hql);
		return cptRoomslist;
	}

	//删除这个机房就是改变机房的deleted的状态
	@Override
	public String deleteCptRoomid(String id) {
		// TODO Auto-generated method stub
		String hql = "update CptRoom cptroom set cptroom.deleted ='1' where cptroom.id ="+id;
		Session session = this.getSession();
		session.createQuery(hql).executeUpdate();
		String delected = cptRoomByid(id).get(0).getDeleted();
		if(delected=="1"){
			return "success";
		}else {
			return "fail";
		}
	}

	@Override
	public List<CptRoom> cptRoomByPid(String parentid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveaddcptroom(CptRoom addcptroom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updatecptroom(CptRoom updatecptroom) {
		// TODO Auto-generated method stub
		return null;
	}

}
