package ssh.service.impl;

import java.util.List;

import ssh.dao.IDicttypeDao;
import ssh.domain.Dicttype;
import ssh.service.IDicttypeService;

public class DicttypeServiceImpl  implements IDicttypeService{

	private IDicttypeDao dicttypeDao;

	public void setDicttypeDao(IDicttypeDao dicttypeDao) {
		this.dicttypeDao = dicttypeDao;
	}
	
	@Override
	public List<Dicttype> findDicttypeNamelist() {
		// TODO Auto-generated method stub
		List<Dicttype> dicttypeslist = dicttypeDao.alldicttype();
		
		return dicttypeslist;
	}

	@Override
	public List<Dicttype> areaByDicttypeid(String id) {
		// TODO Auto-generated method stub
		List<Dicttype> dicttypeslist = dicttypeDao.dicttypeByDictid(id);
		
		return dicttypeslist;
	}

	@Override
	public String deleteByDicttypeid(String id) {
		// TODO Auto-generated method stub
		String conditon = dicttypeDao.deleteDictid(id);
		return conditon;
	}

	@Override
	public List<Dicttype> findDicttypeByPid(String parentid) {
		// TODO Auto-generated method stub
		List<Dicttype> dicttypeslist = dicttypeDao.DicttypeByPid(parentid);
		
		return dicttypeslist;
	}


	@Override
	public String updatearea(Dicttype updatedicttype) {
		// TODO Auto-generated method stub
		String conditon = dicttypeDao.updatearea(updatedicttype);
		return conditon;
	}

	@Override
	public String saveadddicttype(Dicttype adddicttype) {
		// TODO Auto-generated method stub
		String conditon = dicttypeDao.saveaddarea(adddicttype);
		return conditon;
	}

}
