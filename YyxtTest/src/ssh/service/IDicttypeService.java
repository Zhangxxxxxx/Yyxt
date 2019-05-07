package ssh.service;

import java.util.List;

import ssh.domain.Area;
import ssh.domain.Dicttype;

public interface IDicttypeService {
	
	List<Dicttype> findDicttypeNamelist();

	List<Dicttype> areaByDicttypeid(String id);

	String deleteByDicttypeid(String id);

	List<Dicttype> findDicttypeByPid(String parentid);
	
	String updatearea(Dicttype updatedicttype);

	String saveadddicttype(Dicttype adddicttype);
}
