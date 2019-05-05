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


	@Override
	public List<EmosUser> allemployee() {
		
		List<EmosUser> userlist = emosUserDao.allemployee();
		
		return userlist;
	}


	@Override
	public String deleteUser(String id) {
		String  user = emosUserDao.deleteUserid(id);
		
		return user;
	}


	@Override
	public List<EmosUser> userByUserID(String id) {
		// TODO Auto-generated method stub
		List<EmosUser> userList = emosUserDao.userByUserId(id);
		
		return userList;
	}


	@Override
	public String saveeditDepartment(EmosUser emosuser) {
		//编辑成功与否
		String registercondition = emosUserDao.saveDepartment(emosuser);
						
		return registercondition;
	}

	

}
