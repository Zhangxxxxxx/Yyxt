package ssh.dao;

import java.util.List;

import ssh.domain.Area;
import ssh.domain.CptRoom;
import ssh.domain.Dicttype;


public interface ICptRoomDao {

	List<CptRoom> allcptroom();

	List<CptRoom> cptRoomByid(String id);

	String deleteCptRoomid(String id);

	List<CptRoom> cptRoomByPid(String parentid);

	String saveaddcptroom(CptRoom addcptroom);
	
	String updatecptroom(CptRoom updatecptroom);
}
