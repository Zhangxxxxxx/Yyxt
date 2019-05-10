package ssh.service;

import java.util.List;

import ssh.domain.Area;

public interface IAreaService {
	
	List<Area> findAreaNamelist();

	List<Area> areaByAreaid(String id);

	String deleteAreaByAreaid(String id);

	List<Area> findAreaByPid(String parentid);

	String saveaddarea(Area addarea);
	
	String updatearea(Area updatearea);
}
