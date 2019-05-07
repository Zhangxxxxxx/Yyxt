package ssh.domain;

import java.util.Date;

/**
 * 地域
 */
public class Area {
	private String id;//主键ID
	private String areaid;
	private String areaname;
	private String leaf;
	private String remark;
	private String parentareaid;
	private int ordercode;
	private String areacode;
	private String capital;
	private String citymanagername;
	private String citymanagerid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getLeaf() {
		return leaf;
	}
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getParentareaid() {
		return parentareaid;
	}
	public void setParentareaid(String parentareaid) {
		this.parentareaid = parentareaid;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getCitymanagername() {
		return citymanagername;
	}
	public void setCitymanagername(String citymanagername) {
		this.citymanagername = citymanagername;
	}
	public String getCitymanagerid() {
		return citymanagerid;
	}
	public void setCitymanagerid(String citymanagerid) {
		this.citymanagerid = citymanagerid;
	}
	public int getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(int ordercode) {
		this.ordercode = ordercode;
	}
	
	
	
	
}
