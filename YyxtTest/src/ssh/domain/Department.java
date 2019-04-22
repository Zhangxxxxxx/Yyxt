package ssh.domain;

import java.util.List;

public class Department {
	
	private Integer id;//主键ID
	private Integer deleted;//是否删除状态
	private String deptemail;//部门邮箱
	private String deptfax;//密码
	private String deptid;//部门id
	private String deptmanager;//负责人
	private String deptmobile;//部门电话
	private String deptname;//部门名称
	private String deptphone;//部门座机
	private String depttype;//部门
	private String operremoteip;//部门
	private String opertime;//部门
	private String operuserid;//部门
	private String ordercode;//部门
	private String regionflag;//部门
	private String remark;//备注
	private String tmporarybegintime;//部门
	private String tmporarymanager;//部门
	private String tmporarystoptime;//部门
	private String updatetime;//部门
	private String leaf;//部门
	private String areaid;//部门
	private String linkid;//部门
	private String parentlinkid;//部门
	private String parentdeptid;
	private String tmpdeptid;//部门
	private String ispartners;//部门
	private String isdaiweiroot;//部门
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public String getDeptemail() {
		return deptemail;
	}
	public void setDeptemail(String deptemail) {
		this.deptemail = deptemail;
	}
	public String getDeptfax() {
		return deptfax;
	}
	public void setDeptfax(String deptfax) {
		this.deptfax = deptfax;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getDeptmanager() {
		return deptmanager;
	}
	public void setDeptmanager(String deptmanager) {
		this.deptmanager = deptmanager;
	}
	public String getDeptmobile() {
		return deptmobile;
	}
	public void setDeptmobile(String deptmobile) {
		this.deptmobile = deptmobile;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDeptphone() {
		return deptphone;
	}
	public void setDeptphone(String deptphone) {
		this.deptphone = deptphone;
	}
	public String getDepttype() {
		return depttype;
	}
	public void setDepttype(String depttype) {
		this.depttype = depttype;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public String getParentlinkid() {
		return parentlinkid;
	}
	public void setParentlinkid(String parentlinkid) {
		this.parentlinkid = parentlinkid;
	}
	public String getTmpdeptid() {
		return tmpdeptid;
	}
	public void setTmpdeptid(String tmpdeptid) {
		this.tmpdeptid = tmpdeptid;
	}
	public String getIspartners() {
		return ispartners;
	}
	public void setIspartners(String ispartners) {
		this.ispartners = ispartners;
	}
	public String getIsdaiweiroot() {
		return isdaiweiroot;
	}
	public void setIsdaiweiroot(String isdaiweiroot) {
		this.isdaiweiroot = isdaiweiroot;
	}
	
	public String getParentdeptid() {
		return parentdeptid;
	}
	public void setParentdeptid(String parentdeptid) {
		this.parentdeptid = parentdeptid;
	}
	public String getOperremoteip() {
		return operremoteip;
	}
	public void setOperremoteip(String operremoteip) {
		this.operremoteip = operremoteip;
	}
	public String getOpertime() {
		return opertime;
	}
	public void setOpertime(String opertime) {
		this.opertime = opertime;
	}
	public String getOperuserid() {
		return operuserid;
	}
	public void setOperuserid(String operuserid) {
		this.operuserid = operuserid;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public String getRegionflag() {
		return regionflag;
	}
	public void setRegionflag(String regionflag) {
		this.regionflag = regionflag;
	}
	public String getTmporarybegintime() {
		return tmporarybegintime;
	}
	public void setTmporarybegintime(String tmporarybegintime) {
		this.tmporarybegintime = tmporarybegintime;
	}
	public String getTmporarymanager() {
		return tmporarymanager;
	}
	public void setTmporarymanager(String tmporarymanager) {
		this.tmporarymanager = tmporarymanager;
	}
	public String getTmporarystoptime() {
		return tmporarystoptime;
	}
	public void setTmporarystoptime(String tmporarystoptime) {
		this.tmporarystoptime = tmporarystoptime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getLeaf() {
		return leaf;
	}
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

}
