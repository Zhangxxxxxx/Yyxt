package ssh.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ssh.dao.IEmployeeDao;
import ssh.domain.Employee;
import ssh.domain.LeaveBill;
import ssh.service.IEmployeeService;
import ssh.service.ILeaveBillService;
import ssh.utils.SessionContext;
import ssh.utils.ValueContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class LeaveBillAction extends ActionSupport implements ModelDriven<LeaveBill> {

	private LeaveBill leaveBill = new LeaveBill();

	private String[] checkboxText;// 审批人

	public String[] getCheckboxText() {
		return checkboxText;
	}

	public void setCheckboxText(String[] checkboxText) {
		this.checkboxText = checkboxText;
	}

	@Override
	public LeaveBill getModel() {
		return leaveBill;
	}

	private ILeaveBillService leaveBillService;

	public void setLeaveBillService(ILeaveBillService leaveBillService) {
		this.leaveBillService = leaveBillService;
	}

	/**
	 * 请假管理首页显示
	 * 注：请假单状态0初始录入；1审批中；2已完成；3已作废；4已删除
	 * @return
	 */
	public String home() {
		// 1：查询所有的请假信息（对应a_leavebill），返回List<LeaveBill>
		List<LeaveBill> list = leaveBillService.findLeaveBillList();
		
		for (int i = 0; i < list.size(); i++) {
			//将状态为4的请假单移除出此列表
			if(list.get(i).getState()==4){
				list.remove(i);
			}
		}
		
		// 放置到上下文对象中
		ValueContext.putValueContext("list", list);
		return "home";
	}

	/**
	 * 添加请假申请
	 * 
	 * @return
	 */
	public String input() {
		// 1：获取请假单ID
		Long id = leaveBill.getId();
		// 修改
		if (id != null) {
			// 2：使用请假单ID，查询请假单信息，
			LeaveBill bill = leaveBillService.findLeaveBillById(id);
			// 3：将请假单信息放置到栈顶，页面使用struts2的标签，支持表单回显
			ValueContext.putValueStack(bill);
		}

		/** 从新查询当前用户，再获取当前用户对应的领导 */
		Employee employee = SessionContext.get();
		// 从web中获取spring容器
		WebApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(ServletActionContext.getServletContext());
		IEmployeeService employeeService = (IEmployeeService) ac.getBean("employeeService");
		String department = employee.getDepartment();// 获取该员工对应的部门
		Long departmentid = employee.getDepartmentid();// 获取该员工所在部门id
		
		//判断当前操作人是否是项目经理如果是的话，则把可审批人设定为其同级别人员
		if( employee.getRoleid()!=1){		
//			Long manageid = employee.getManager().getId();// 获取对应的上级领导id
			Long manageid = employee.getManagerid();// 获取对应的上级领导id
			List<String> rolename = employeeService.findEmployeeByManageid(manageid, departmentid);// 根据领导id和部门id获取相对应的角色名集合		
			ValueContext.putValueContext("rolename", rolename);
		}else {
			Long manageid = employee.getRoleid();
			List<String> rolename = employeeService.findEmployeeByManageid(manageid, departmentid);// 根据领导id和部门id获取相对应的角色名集合		
			ValueContext.putValueContext("rolename", rolename);
		}
		ValueContext.putValueContext("department", department);
		// 新增
		return "input";
	}

	/**
	 * 保存/更新，请假申请
	 * 
	 */
	public String save() {
		// 执行保存
		leaveBillService.saveLeaveBill(leaveBill);

		String approver = "";
		List<String> approverlist = new ArrayList<>();
		// 获取checkboxtext这个数组并将数组转换为list集合
		for (int i = 0; i < this.checkboxText.length; i++) {
			approver = (this.checkboxText[i]);
			String[] split = approver.split(",");// 以逗号分割
			System.out.println(split.length);
			for (String name : split) {
				System.out.println("审批部门和人员:" + name);
				approverlist.add(name);
			}
		}
		/**从新查询当前用户，再获取当前用户对应的领导*/
		Employee employee = SessionContext.get();
		employee.setAPProverlist(approverlist);//设置审批的领导集合
		
		return "save";
	}

	/**
	 * 删除，请假申请
	 * 
	 */
	public String delete() {
		// 1：获取请假单ID
		Long id = leaveBill.getId();
		System.out.println("申请请假单id" + id);
		// 执行删除
//		leaveBillService.deleteLeaveBillById(id);
		//执行删除就是将这个请假单改变状态，不能真的删除
		LeaveBill bill = leaveBillService.findLeaveBillById(id);
		bill.setState(4);
		leaveBillService.saveLeaveBill(bill);
		return "save";
	}

}
