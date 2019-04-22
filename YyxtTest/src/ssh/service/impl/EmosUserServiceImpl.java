package ssh.service.impl;

import java.util.ArrayList;
import java.util.List;


import ssh.dao.IEmosUserDao;
import ssh.domain.EmosUser;
import ssh.service.IEmosUserService;


public class EmosUserServiceImpl implements IEmosUserService {
	
	private IEmosUserDao emosUserDao;
	
	public void setEmosUserDao(IEmosUserDao emosUserDao) {
		this.emosUserDao = emosUserDao;
	}

	
	@Override
	public List<EmosUser> userByDeptId(String deptid) {
		// TODO Auto-generated method stub
		List<EmosUser> userlist = emosUserDao.userByDeptId(deptid);
		
		return userlist;
	}

	

}
