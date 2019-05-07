package ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ssh.dao.IAreaDao;
import ssh.domain.Area;
import ssh.domain.Department;

public class AreaDaoImpl extends HibernateDaoSupport implements IAreaDao {

	private IAreaDao areaDao;
	
	public void setAreaDao(IAreaDao areaDao) {
		this.areaDao = areaDao;
	}
	
	
	@Override
	public List<Area> allarea() {
		// 查询出所有的地域对象并按照id的倒序排序
		String hql = "from Area area where 1=1 ORDER BY area.id+1 DESC";
		
		List<Area> arealist = this.getHibernateTemplate().find(hql);
		
		return arealist;
	}


	@Override
	public List<Area> areaByAreaid(String id) {
		String hql = "from Area area where area.areaid =?";
		
		List<Area> area = this.getHibernateTemplate().find(hql,id);
		
		return area;
	}


	@Override
	public String deleteAreaid(String id) {
		// TODO Auto-generated method stub
		List<Area> arealist = areaByAreaid(id);
		if (arealist!=null && arealist.size()>=1) {
			Area area = arealist.get(0);
			//3：执行删除
			this.getHibernateTemplate().delete(area);
			return "success";
		}else {
			return "fail";
		}
		
		
		
	}

	//通过父类id查询出相关的区域列表
	@Override
	public List<Area> areaByPid(String parentid) {
		// TODO Auto-generated method stub
		String hql = "from Area area where area.parentareaid ="+parentid+"ORDER BY area.id+1 DESC";
		List<Area> area = this.getHibernateTemplate().find(hql);
		
		return area;
	}

	//保存新增的区域
	@Override
	public String saveaddarea(Area addarea) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(addarea);
		String hql = "from Area area where area.id = ?";
		List<Area> list = this.getHibernateTemplate().find(hql, addarea.getId());
		String condition;
		if(list !=null && list.size()>0){
			condition="success";
		}else {
			condition="fail";
		}
		return condition;
	}

	//修改区域
	@Override
	public String updatearea(Area updatearea) {
		
		this.getHibernateTemplate().update(updatearea);;
		String hql = "from Area area where area.id = ?";
		List<Area> list = this.getHibernateTemplate().find(hql, updatearea.getId());
		String condition;
		if(list !=null && list.size()>0){
			condition="success";
		}else {
			condition="fail";
		}
		return condition;
	}


}
