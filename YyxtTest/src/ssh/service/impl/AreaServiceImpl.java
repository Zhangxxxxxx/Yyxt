package ssh.service.impl;

import java.util.List;

import ssh.dao.IAreaDao;
import ssh.domain.Area;
import ssh.service.IAreaService;

public class AreaServiceImpl implements IAreaService{
	
	private IAreaDao areaDao;

	public void setAreaDao(IAreaDao areaDao) {
		this.areaDao = areaDao;
	}
	
	
	@Override
	public List<Area> findAreaNamelist() {
		// TODO Auto-generated method stub
		List<Area> arealist = areaDao.allarea();
		
		return arealist;
	}


	@Override
	public List<Area> areaByAreaid(String id) {
		
		List<Area> arealist = areaDao.areaByAreaid(id);
		
		return arealist;
	}


	@Override
	public String deleteAreaByAreaid(String id) {
		// TODO Auto-generated method stub
		String conditon = areaDao.deleteAreaid(id);
		return conditon;
	}


	@Override
	public List<Area> findAreaByPid(String parentid) {
		// TODO Auto-generated method stub
		List<Area> arealist = areaDao.areaByPid(parentid);
		
		return arealist;
	}


	@Override
	public String saveaddarea(Area addarea) {
		// TODO Auto-generated method stub
		String conditon = areaDao.saveaddarea(addarea);
		
		return conditon;
	}


	@Override
	public String updatearea(Area updatearea) {
		// TODO Auto-generated method stub
		String conditon = areaDao.updatearea(updatearea);
		
		return conditon;
	}

}
