package ssh.web.action;

import ssh.domain.Area;
import ssh.domain.JsonDepartment;
import ssh.service.IAreaService;
import ssh.utils.SessionContext;
import ssh.utils.ValueContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.request.SessionScope;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class AreaAction extends ActionSupport implements ModelDriven<Area> {

	private Area area = new Area();

	@Override
	public Area getModel() {
		return area;
	}

	private IAreaService areaService;

	public void setAreaService(IAreaService areaService) {
		this.areaService = areaService;
	}
	
	/**
	 * 地域列表
	 * 
	 * @return
	 */
	public String arealist() {
		
		List<ssh.domain.Area> arealist = areaService.findAreaNamelist();
		// 返回一个JSONArray对象
		List<JsonDepartment> areas = new ArrayList<>();
		// 添加部门
		for (int i = 0; i < arealist.size(); i++) {
			JsonDepartment jsonDepartment = new JsonDepartment();
			jsonDepartment.setId(arealist.get(i).getAreaid());
			jsonDepartment.setpId(arealist.get(i).getParentareaid());
			jsonDepartment.setName(arealist.get(i).getAreaname());
			areas.add(jsonDepartment);
		}
		
		JsonDepartment jsonDepartment = new JsonDepartment();
		jsonDepartment.setId("-1");
		jsonDepartment.setpId("00001");
		jsonDepartment.setName("所有地域");
		areas.add(jsonDepartment);

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

		String userStr = JSON.toJSONString(areas);// 使用fastjson将数据转换成json格式

		ValueContext.putValueContext("arealist", userStr);
		
		return "arealist";
	}
	
	/*通过areaid获得area对象*/
	public void areaByAreaId() throws IOException {

		String registerconditon = "";
		// 获取传递过来员工id
		String id = area.getAreaid();
		System.out.println("地域的areaid" + id);
		List<Area> userslist = areaService.areaByAreaid(id);
		Area area = userslist.get(0); 

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

		String userStr = JSON.toJSONString(area);// 使用fastjson将数据转换成json格式
		PrintWriter writer = ServletActionContext.getResponse().getWriter();

		writer.write(userStr);
		System.out.println(userStr);
		writer.flush();
		writer.close();
	}
	
	public String deletedarea() {
		
		String conditon = "";
		// 获取传递过来的要删除的地域的areaid
		String id = area.getAreaid();
		System.out.println("要删除的地域的areaid" + id);
		
		conditon = areaService.deleteAreaByAreaid(id);
	
		return "updatedarea";
	}
	
	/**
	 *添加区域 
	 */
	public String addarea() {
		
		String areaname = area.getAreaname();
		String parentid = area.getParentareaid();
		String remark = area.getRemark();
		
		System.out.println("添加的区域名称是" + areaname);
		System.out.println("添加的区域父类id是" + parentid);
		
		List<Area> arealist = areaService.findAreaNamelist();
		Area areap = arealist.get(0);
		int id = Integer.parseInt(areap.getId())+1;
		
		
		List<Area> arealist2 = areaService.findAreaByPid(parentid);
		
		int areaid = Integer.parseInt(arealist2.get(0).getAreaid())+1;
		int ordercode = arealist2.get(0).getOrdercode();
		System.out.println(arealist2.get(0).getAreaid());
		
		System.out.println("添加的id是" + id);
		System.out.println("添加的areaid是" + areaid);
		System.out.println("添加的ordercode是" + ordercode);
		
		Area addarea = new Area();
		addarea.setId(id+"");
		addarea.setAreaid(areaid+"");
		addarea.setAreaname(areaname);
		addarea.setAreacode(areaname);
		addarea.setOrdercode(ordercode);
		addarea.setLeaf("0");
		addarea.setParentareaid(parentid);
		addarea.setRemark(remark);
		
		String conditon = areaService.saveaddarea(addarea);
		System.out.println("新增的区域保存" + conditon);
		
		return "updatedarea";
	}
	
	//保存修改后的区域
	public String saveediterarea() {
		
		String areaname = area.getAreaname();
		String parentid = area.getParentareaid();
		String remark = area.getRemark();
		String areaid = area.getAreaid();
		int ordercode = area.getOrdercode();
		String id = area.getId();
		
		System.out.println("修改保存的name是" + areaname);
		System.out.println("修改保存的id是" + id);
		System.out.println("修改保存areaid是" + areaid);
		System.out.println("修改保存的ordercode是" + ordercode);
		
		
		Area updatearea = new Area();
		updatearea.setId(id);
		updatearea.setAreaid(areaid);
		updatearea.setAreaname(areaname);
		updatearea.setAreacode(areaname);
		updatearea.setOrdercode(ordercode);
		updatearea.setLeaf("1");
		updatearea.setParentareaid(parentid);
		updatearea.setRemark(remark);
		
		String conditon = areaService.updatearea(updatearea);
		System.out.println("修改区域保存" + conditon);
		
		return "updatedarea";
	}
	
}
