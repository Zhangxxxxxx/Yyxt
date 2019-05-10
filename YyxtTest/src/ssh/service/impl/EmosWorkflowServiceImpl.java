package ssh.service.impl;

import java.util.List;

import ssh.dao.ICptRoomDao;
import ssh.dao.IEmosWorkFlowDao;
import ssh.dao.IRoleDao;
import ssh.domain.CptRoom;
import ssh.domain.Dicttype;
import ssh.domain.EmosWorkFlow;
import ssh.domain.Role;
import ssh.service.ICptRoomService;
import ssh.service.IEmosWorkflowService;
import ssh.service.IRoleService;

public class EmosWorkflowServiceImpl implements IEmosWorkflowService{
	
	
	private IEmosWorkFlowDao emosWorkFlowDao;
	
	public void setEmosWorkFlowDao(IEmosWorkFlowDao emosWorkFlowDao) {
		this.emosWorkFlowDao = emosWorkFlowDao;
	}

	
	@Override
	public List<EmosWorkFlow> findWorkflowlist() {
		// TODO Auto-generated method stub
		List<EmosWorkFlow> emosWorkFlowslist = emosWorkFlowDao.allworkflow();
		
		return emosWorkFlowslist;
	}


	

}
