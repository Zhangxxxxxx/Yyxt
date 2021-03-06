package ssh.dao;

import java.util.List;

import ssh.domain.LeaveBill;



public interface ILeaveBillDao {

	List<LeaveBill> findLeaveBillList();

	void saveLeaveBill(LeaveBill leaveBill);

	LeaveBill findLeaveBillById(Long id);

	void updateLeaveBill(LeaveBill leaveBill);

	void deleteLeaveBillById(Long id);


}
