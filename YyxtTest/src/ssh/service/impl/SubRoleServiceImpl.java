package ssh.service.impl;

import java.util.List;

import ssh.dao.ICptRoomDao;
import ssh.dao.IRoleDao;
import ssh.dao.ISubRoleDao;
import ssh.domain.CptRoom;
import ssh.domain.Dicttype;
import ssh.domain.Role;
import ssh.domain.SubRole;
import ssh.service.ICptRoomService;
import ssh.service.IRoleService;
import ssh.service.ISubRoleService;

public class SubRoleServiceImpl implements ISubRoleService{
	
	
	private ISubRoleDao subRoleDao;
	
	public void setSubRoleDao(ISubRoleDao subRoleDao) {
		this.subRoleDao = subRoleDao;
	}

	@Override
	public List<SubRole> findRoleNamelist() {
		// TODO Auto-generated method stub
		List<SubRole> subRoles = subRoleDao.allrole();
		return subRoles;
	}

	@Override
	public List<SubRole> findRoleSyssublist() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
