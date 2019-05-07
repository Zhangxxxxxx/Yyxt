package ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ssh.dao.IAreaDao;
import ssh.dao.IDicttypeDao;
import ssh.domain.Area;
import ssh.domain.Department;
import ssh.domain.Dicttype;

public class DicttypeDaoImpl extends HibernateDaoSupport implements IDicttypeDao {

	private IDicttypeDao dicttypeDao;
	
	public void setDicttypeDao(IDicttypeDao dicttypeDao) {
		this.dicttypeDao = dicttypeDao;
	}

	@Override
	public List<Dicttype> alldicttype() {
		// TODO Auto-generated method stub
		// 查询出所有的字典对象并按照id的倒序排序
		String hql = "from Dicttype dicttype where 1=1 ";
				
		List<Dicttype> dictlist = this.getHibernateTemplate().find(hql);
				
		return dictlist;
	}

	@Override
	public List<Dicttype> dicttypeByDictid(String id) {
		// TODO Auto-generated method stub
		String hql = "from Dicttype dicttype where dicttype.dictid =?";
		
		List<Dicttype> dicttypes = this.getHibernateTemplate().find(hql,id);
		
		return dicttypes;
	}

	@Override
	public String deleteDictid(String id) {
		// TODO Auto-generated method stub
		List<Dicttype> dicttypeslist = dicttypeByDictid(id);
		if (dicttypeslist!=null && dicttypeslist.size()>=1) {
			Dicttype dicttype = dicttypeslist.get(0);
			//3：执行删除
			this.getHibernateTemplate().delete(dicttype);
			return "success";
		}else {
			return "fail";
		}
	}

	@Override
	public List<Dicttype> DicttypeByPid(String parentid) {
		// TODO Auto-generated method stub
		String hql = "from Dicttype dicttype where dicttype.parentdictid =?";
		
		List<Dicttype> dicttypes = this.getHibernateTemplate().find(hql,parentid);
		
		return dicttypes;
	}

	//保存新增的字典
	@Override
	public String saveaddarea(Dicttype adddicttype) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(adddicttype);
		String hql = "from Dicttype dicttype where dicttype.dictid =?";
		List<Dicttype> list = this.getHibernateTemplate().find(hql, adddicttype.getDictid());
		String condition;
		if(list !=null && list.size()>0){
			condition="success";
		}else {
			condition="fail";
		}
		return condition;
	}

	//更新字典
	@Override
	public String updatearea(Dicttype updatedicttype) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().update(updatedicttype);;
		String hql = "from Dicttype dicttype where dicttype.dictid =?";
		List<Dicttype> list = this.getHibernateTemplate().find(hql, updatedicttype.getDictid());
		String condition;
		if(list !=null && list.size()>0){
			condition="success";
		}else {
			condition="fail";
		}
		return condition;
	}
	
	
	


}
