package ssh.domain;

import java.util.List;

/**
 * 用户表
 */
public class Employee {
	private Long id;//主键ID
	private Long roleid;//角色ID
	private String name;//用户名
	private String password;//密码
	private String email;//电子邮箱
	private String role;//角色
	private String department;//部门
	private Long departmentid;//部门id
	private Employee manager;
	private List<String> APProverlist;//审批人员的集合
	private Long managerid;//领导id
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Long getRoleid() {
		return roleid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	public Long getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Long departmentid) {
		this.departmentid = departmentid;
	}
	public List<String> getAPProverlist() {
		return APProverlist;
	}
	public void setAPProverlist(List<String> aPProverlist) {
		APProverlist = aPProverlist;
	}
	public Long getManagerid() {
		return managerid;
	}
	public void setManagerid(Long managerid) {
		this.managerid = managerid;
	}
	
	
}

