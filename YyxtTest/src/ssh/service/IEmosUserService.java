package ssh.service;

import java.util.List;

import ssh.domain.Department;
import ssh.domain.EmosUser;
import ssh.domain.Employee;


public interface IEmosUserService {

	List<EmosUser> userByDeptId(String deptid);

	List<EmosUser> allemployee();

}
