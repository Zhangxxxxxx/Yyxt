package ssh.utils;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.Comment;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ssh.domain.Employee;
import ssh.service.IEmployeeService;
import ssh.service.IWorkflowService;
import ssh.service.impl.WorkflowServiceImpl;

/**
 * 员工经理任务分配
 *
 */
@SuppressWarnings("serial")
public class ManagerTaskHandler implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		/** 懒加载异常 */
		// Employee employee = SessionContext.get();
		// //设置个人任务的办理人
		// delegateTask.setAssignee(employee.getManager().getName());
		/** 从新查询当前用户，再获取当前用户对应的领导 */
		Employee employee = SessionContext.get();
		// 当前用户
		String name = employee.getName();
		// 使用当前用户名查询用户的详细信息
		// 从web中获取spring容器
		WebApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(ServletActionContext.getServletContext());
		IEmployeeService employeeService = (IEmployeeService) ac.getBean("employeeService");
		Employee emp = employeeService.findEmployeeByName(name);
		// 设置个人任务的办理人
		// delegateTask.setAssignee(emp.getManager().getName());
		// 设置组任务的办理人
		List<String> rolename = employee.getAPProverlist();
		if (rolename != null) {
			for (int i = 0; i < rolename.size(); i++) {
				delegateTask.addCandidateUser(rolename.get(i).trim());// 将角色名添加进任务组
				System.out.println("添加进的审批人员有" + rolename.get(i).trim());
			}
			if(rolename.size()>1){
				delegateTask.deleteCandidateUser(rolename.get(0).trim());// 再把部门名称给删除掉
			}
		} else {
//			Long manageid = emp.getManager().getId();// 获取对应的上级领导id
			Long manageid = emp.getManagerid();// 获取对应的上级领导id
			Long departmentid = employee.getDepartmentid();// 获取该员工所在部门id
			rolename = employeeService.findEmployeeByManageid(manageid, departmentid);// 根据领导id和部门id获取相对应的角色名集合
			for (int i = 0; i < rolename.size(); i++) {
				delegateTask.addCandidateUser(rolename.get(i).trim());// 将角色名添加进任务组
				System.out.println("默认添加进的审批人员有" + rolename.get(i).trim());
			}
		}

	}

}
