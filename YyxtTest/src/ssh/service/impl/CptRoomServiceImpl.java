package ssh.service.impl;

import java.util.List;

import ssh.dao.ICptRoomDao;
import ssh.domain.CptRoom;
import ssh.domain.Dicttype;
import ssh.service.ICptRoomService;

public class CptRoomServiceImpl implements ICptRoomService{
	
	
	private ICptRoomDao cptRoomDao;
	
	public void setCptRoomDao(ICptRoomDao cptRoomDao) {
		this.cptRoomDao = cptRoomDao;
	}


	@Override
	public List<CptRoom> findCptRoomNamelist() {
		// TODO Auto-generated method stub
		List<CptRoom> cptRoomslist = cptRoomDao.allcptroom();
		return cptRoomslist;
	}

	@Override
	public List<CptRoom> cptRoomByid(String id) {
		// TODO Auto-generated method stub
		List<CptRoom> cptRoomslist = cptRoomDao.cptRoomByid(id);
		
		return cptRoomslist;
	}

	@Override
	public String deleteByCptRoomid(String id) {
		// TODO Auto-generated method stub
		String conditon = cptRoomDao.deleteCptRoomid(id);
		return conditon;
	}

	@Override
	public List<CptRoom> findCptRoomByPid(String parentid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updatearea(CptRoom updatecptroom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveaddcptroom(Dicttype addcptroom) {
		// TODO Auto-generated method stub
		return null;
	}

}
