package ssh.domain;

import java.util.List;

/*ztree的模型，id，name，父类id就是pid*/
public class JsonDepartment {
	
	private String id;
	private String pId;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
