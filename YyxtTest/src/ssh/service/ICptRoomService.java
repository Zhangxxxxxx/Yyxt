package ssh.service;

import java.util.List;

import ssh.domain.Area;
import ssh.domain.CptRoom;
import ssh.domain.Dicttype;

public interface ICptRoomService {
	
	List<CptRoom> findCptRoomNamelist();

	List<CptRoom> cptRoomByid(String id);

	String deleteByCptRoomid(String id);

	List<CptRoom> findCptRoomByPid(String parentid);
	
	String updatearea(CptRoom updatecptroom);

	String saveaddcptroom(Dicttype addcptroom);
}
