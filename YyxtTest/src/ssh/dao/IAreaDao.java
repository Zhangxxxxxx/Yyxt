package ssh.dao;

import java.util.List;

import ssh.domain.Area;


public interface IAreaDao {

	List<Area> allarea();

	List<Area> areaByAreaid(String id);

	String deleteAreaid(String id);

	List<Area> areaByPid(String parentid);

	String saveaddarea(Area addarea);
	
	String updatearea(Area updatearea);
}
