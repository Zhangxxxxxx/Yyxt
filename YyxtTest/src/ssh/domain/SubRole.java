package ssh.domain;

public class SubRole {
	private Integer id;
	private String area;
	private String deptid;
	private String parentid;
	private String roleid;
	private String subrolename;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getSubrolename() {
		return subrolename;
	}
	public void setSubrolename(String subrolename) {
		this.subrolename = subrolename;
	}
	

}
