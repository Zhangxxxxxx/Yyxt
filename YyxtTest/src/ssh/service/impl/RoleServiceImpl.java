package ssh.service.impl;

import java.util.List;

import ssh.dao.ICptRoomDao;
import ssh.dao.IRoleDao;
import ssh.domain.CptRoom;
import ssh.domain.Dicttype;
import ssh.domain.Role;
import ssh.service.ICptRoomService;
import ssh.service.IRoleService;

public class RoleServiceImpl implements IRoleService{
	
	
	private IRoleDao roleDao;
	
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> findRoleNamelist() {
		// TODO Auto-generated method stub
		List<Role> rolelist = roleDao.allrole();
		return rolelist;
	}


	

}
