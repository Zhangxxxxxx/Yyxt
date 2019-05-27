package ssh.dao;

import java.util.List;

import ssh.domain.Area;
import ssh.domain.CptRoom;
import ssh.domain.Dicttype;
import ssh.domain.Role;
import ssh.domain.SubRole;


public interface ISubRoleDao {

	List<SubRole> allrole();

	List<SubRole> syssubrole();
}
