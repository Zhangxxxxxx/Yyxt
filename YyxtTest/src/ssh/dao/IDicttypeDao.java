package ssh.dao;

import java.util.List;

import ssh.domain.Area;
import ssh.domain.Dicttype;


public interface IDicttypeDao {

	List<Dicttype> alldicttype();

	List<Dicttype> dicttypeByDictid(String id);

	String deleteDictid(String id);

	List<Dicttype> DicttypeByPid(String parentid);

	String saveaddarea(Dicttype adddicttype);
	
	String updatearea(Dicttype updatedicttype);
}
