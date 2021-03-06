package ssh.dao;

import java.util.List;

import ssh.domain.Department;
import ssh.domain.EmosUser;


public interface IEmosUserDao {

	List<EmosUser> userByDeptId(String deptid);
	
	List<EmosUser> userByUserId(String userid);

	List<EmosUser> allemployee();

	String deleteUserid(String id);

	String saveDepartment(EmosUser emosuser);
	

}
