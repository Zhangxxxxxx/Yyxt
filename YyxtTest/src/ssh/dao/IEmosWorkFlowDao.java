package ssh.dao;

import java.util.List;

import ssh.domain.Area;
import ssh.domain.CptRoom;
import ssh.domain.Dicttype;
import ssh.domain.EmosWorkFlow;
import ssh.domain.Role;


public interface IEmosWorkFlowDao {

	List<EmosWorkFlow> allworkflow();
}
