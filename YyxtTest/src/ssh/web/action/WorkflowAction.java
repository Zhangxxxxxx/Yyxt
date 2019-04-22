package ssh.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ssh.domain.Employee;
import ssh.domain.LeaveBill;
import ssh.service.IEmployeeService;
import ssh.service.ILeaveBillService;
import ssh.service.IWorkflowService;
import ssh.utils.ManagerTaskHandler;
import ssh.utils.SessionContext;
import ssh.utils.ValueContext;
import ssh.web.form.WorkflowBean;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class WorkflowAction extends ActionSupport implements ModelDriven<WorkflowBean> {

	private WorkflowBean workflowBean = new WorkflowBean();
	private String[] checkboxText;// 审批人

	public String[] getCheckboxText() {
		return checkboxText;
	}

	public void setCheckboxText(String[] checkboxText) {
		this.checkboxText = checkboxText;
	}

	@Override
	public WorkflowBean getModel() {
		return workflowBean;
	}

	private IWorkflowService workflowService;

	private ILeaveBillService leaveBillService;

	public void setLeaveBillService(ILeaveBillService leaveBillService) {
		this.leaveBillService = leaveBillService;
	}

	public void setWorkflowService(IWorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	/**
	 * 部署管理首页显示
	 * 
	 * @return
	 */
	public String deployHome() {
		// 1:查询部署对象信息，对应表（act_re_deployment）
		List<Deployment> depList = workflowService.findDeploymentList();
		// 2:查询流程定义的信息，对应表（act_re_procdef）
		List<ProcessDefinition> pdList = workflowService.findProcessDefinitionList();
		// 放置到上下文对象中
		ValueContext.putValueContext("depList", depList);
		ValueContext.putValueContext("pdList", pdList);
		return "deployHome";
	}

	/**
	 * 发布流程
	 * 
	 * @return
	 */
	public String newdeploy() {
		// 获取页面传递的值
		// 1：获取页面上传递的zip格式的文件，格式是File类型
		File file = workflowBean.getFile();
		// 文件名称
		String filename = workflowBean.getFilename();
		// 完成部署
		workflowService.saveNewDeploye(file, filename);
		return "list";
	}

	/**
	 * 删除部署信息
	 */
	public String delDeployment() {
		// 1：获取部署对象ID
		String deploymentId = workflowBean.getDeploymentId();
		// 2：使用部署对象ID，删除流程定义
		workflowService.deleteProcessDefinitionByDeploymentId(deploymentId);
		return "list";
	}

	/**
	 * 查看流程图
	 * 
	 * @throws Exception
	 */
	public String viewImage() throws Exception {
		// 1：获取页面传递的部署对象ID和资源图片名称
		// 部署对象ID
		String deploymentId = workflowBean.getDeploymentId();
		// 资源图片名称
		String imageName = workflowBean.getImageName();
		// 2：获取资源文件表（act_ge_bytearray）中资源图片输入流InputStream
		InputStream in = workflowService.findImageInputStream(deploymentId, imageName);
		// 3：从response对象获取输出流
		OutputStream out = ServletActionContext.getResponse().getOutputStream();
		// 4：将输入流中的数据读取出来，写到输出流中
		for (int b = -1; (b = in.read()) != -1;) {
			out.write(b);
		}
		out.close();
		in.close();
		// 将图写到页面上，用输出流写
		return null;
	}

	// 启动流程
	public String startProcess() {
		// 更新请假状态，启动流程实例，让启动的流程实例关联业务
		workflowService.saveStartProcess(workflowBean);
		return "listTask";
	}

	/**
	 * 任务管理首页显示
	 * 
	 * @return
	 */
	public String listTask() {
		// 1：从Session中获取当前用户名
		String name = SessionContext.get().getName();
		// 2：使用当前用户名查询正在执行的任务表，获取当前任务的集合List<Task>
		List<Task> list = workflowService.findTaskListByName(name);
		ValueContext.putValueContext("list", list);
		return "task";
	}

	/**
	 * 打开任务表单
	 */
	public String viewTaskForm() {
		// 任务ID
		String taskId = workflowBean.getTaskId();
		// 获取任务表单中任务节点的url连接
		String url = workflowService.findTaskFormKeyByTaskId(taskId);
		url += "?taskId=" + taskId;
		ValueContext.putValueContext("url", url);
		return "viewTaskForm";
	}

	// 准备表单数据
	public String audit() {
		// 获取任务ID
		String taskId = workflowBean.getTaskId();
		/** 一：使用任务ID，查找请假单ID，从而获取请假单信息 */
		LeaveBill leaveBill = workflowService.findLeaveBillByTaskId(taskId);
		ValueContext.putValueStack(leaveBill);
		/**
		 * 二：已知任务ID，查询ProcessDefinitionEntiy对象，从而获取当前任务完成之后的连线名称，并放置到List
		 * <String>集合中
		 */
		List<String> outcomeList = workflowService.findOutComeListByTaskId(taskId);
		ValueContext.putValueContext("outcomeList", outcomeList);
		/** 三：查询所有历史审核人的审核信息，帮助当前人完成审核，返回List<Comment> */
		List<Comment> commentList = workflowService.findCommentByTaskId(taskId);
		ValueContext.putValueContext("commentList", commentList);

		/** 从新查询当前用户 */
		Employee employee = SessionContext.get();
		// 从web中获取spring容器
		WebApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(ServletActionContext.getServletContext());
		IEmployeeService employeeService = (IEmployeeService) ac.getBean("employeeService");
		String department = employee.getDepartment();// 获取该员工对应的部门
		Long departmentid = employee.getDepartmentid();// 获取该员工所在部门id
		Long roleid = employee.getRoleid();// 获取该员工的角色id
		List<String> rolename = employeeService.findEmployRolelist(roleid, departmentid);// 根据角色id和部门id获取相对应的角色名集合
		// 遍历集合
		// System.out.println("同级别同部门员工有多少人:"+rolename.size());
		if (rolename.size() > 1) {
			for (int i = 0; i < rolename.size(); i++) {
				//移除当前审批的员工
				if (rolename.get(i).equals(employee.getName())) {
					rolename.remove(i);
				}
			}
		}
		
		
		List<String> allname = employeeService.findEmployNamelist();// 查询出所有角色名集合
		//将当前操作人移出该集合和移除该任务的请假人
		if (allname.size() > 1) {
			for (int i = 0; i < allname.size(); i++) {
				if (allname.get(i).equals(employee.getName()) || allname.get(i).equals(leaveBill.getUser().getName()) ||  allname.get(i).equals("超级用户")) {
					allname.remove(i);
				}
			}
		} 
		
		ValueContext.putValueContext("department", department);// 部门
		ValueContext.putValueContext("rolename", rolename);// 相同部门员工的集合
		ValueContext.putValueContext("employeeid", employee.getId());// 当前登陆人员的id
		ValueContext.putValueContext("allname", allname);//所有人员姓名
		return "taskForm";
	}

	/**
	 * 提交任务
	 */
	public String submitTask() {
		workflowService.saveSubmitTask(workflowBean);
		
		return "listTask";
	}

	/**
	 * 查看当前流程图（查看当前活动节点，并使用红色的框标注）
	 */
	public String viewCurrentImage() {
		// 任务ID
		String taskId = workflowBean.getTaskId();
		/** 一：查看流程图 */
		// 1：获取任务ID，获取任务对象，使用任务对象获取流程定义ID，查询流程定义对象
		ProcessDefinition pd = workflowService.findProcessDefinitionByTaskId(taskId);
		// workflowAction_viewImage?deploymentId=<s:property
		// value='#deploymentId'/>&imageName=<s:property value='#imageName'/>
		ValueContext.putValueContext("deploymentId", pd.getDeploymentId());
		ValueContext.putValueContext("imageName", pd.getDiagramResourceName());
		/** 二：查看当前活动，获取当期活动对应的坐标x,y,width,height，将4个值存放到Map<String,Object>中 */
		Map<String, Object> map = workflowService.findCoordingByTask(taskId);
		ValueContext.putValueContext("acs", map);
		return "image";
	}

	// 查看历史的批注信息
	public String viewHisComment() {
		// 获取清单ID
		Long id = workflowBean.getId();
		// 1：使用请假单ID，查询请假单对象，将对象放置到栈顶，支持表单回显
		LeaveBill leaveBill = leaveBillService.findLeaveBillById(id);
		ValueContext.putValueStack(leaveBill);
		// 2：使用请假单ID，查询历史的批注信息
		List<Comment> commentList = workflowService.findCommentByLeaveBillId(id);
		ValueContext.putValueContext("commentList", commentList);
		return "viewHisComment";
	}

	/**
	 * 退回到申请人处作废（删除）请假申请
	 * 
	 */
	public String delete() {
		// 1：获取任务ID
		Long id = workflowBean.getId();
		// 执行删除
		workflowService.deleteLeaveBillById(id);
		return "save";
	}

	public String updateCandidateUser() {

		String approver = "";
		List<String> approverlist = new ArrayList<>();
		System.out.println(this.checkboxText.length + "");
		// 获取checkboxtext这个数组并将数组转换为list集合

		for (int i = 0; i < this.checkboxText.length; i++) {
			approver = (this.checkboxText[i]);
			try {
				approver = new String(approver.getBytes("iso-8859-1"), "utf-8");// 将传过来的数据进行转码否则会乱码
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] split = approver.split(",");// 以逗号分割
			System.out.println(split.length);
			for (String name : split) {
				System.out.println("移交的办理部门和人有-->>>" + name);
				approverlist.add(name);
			}
		}
		// 任务ID
		String taskId = workflowBean.getTaskId();
		System.out.println("任务id是" + taskId);
		// 更新审批人的方法
		workflowService.updateCandidateUser(taskId, approverlist);

		return "save";
	}
	
	/**传递并设置分派人员的集合**/
	public void setapproverlist() {
		
		String approver = "";
		List<String> approverlist = new ArrayList<>();
		if(this.checkboxText!=null && !this.checkboxText.equals("")){
			// 获取checkboxtext这个数组并将数组转换为list集合
			for (int i = 0; i < this.checkboxText.length; i++) {
				approver = (this.checkboxText[i]);
				try {
					approver = new String(approver.getBytes("iso-8859-1"), "utf-8");// 将传过来的数据进行转码否则会乱码
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String[] split = approver.split(",");// 以逗号分割
				System.out.println(split.length);
				for (String name : split) {
					System.out.println("分派的人员有" + name);
					approverlist.add(name);
				}
			}
			/**从新查询当前用户，再获取当前用户对应的领导*/
			Employee employee = SessionContext.get();
			employee.setAPProverlist(approverlist);//设置审批的领导集合
		}
		
	}
}
