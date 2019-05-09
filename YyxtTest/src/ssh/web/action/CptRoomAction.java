package ssh.web.action;

import ssh.domain.Area;
import ssh.domain.CptRoom;
import ssh.domain.Dicttype;
import ssh.domain.JsonDepartment;
import ssh.service.IAreaService;
import ssh.service.ICptRoomService;
import ssh.service.IDicttypeService;
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
public class CptRoomAction extends ActionSupport implements ModelDriven<CptRoom> {

	private CptRoom cptRoom = new CptRoom();

	@Override
	public CptRoom getModel() {
		return cptRoom;
	}

	private ICptRoomService cptRoomService;

	public void setCptRoomService(ICptRoomService cptRoomService) {
		this.cptRoomService = cptRoomService;
	}
	
	/**
	 * 机房列表
	 * 
	 * @return
	 */
	public String cptroomlist() {
		
		List<CptRoom> cptRoomslist = cptRoomService.findCptRoomNamelist();
		// 返回一个JSONArray对象
		List<JsonDepartment> areas = new ArrayList<>();
		// 添加部门
		for (int i = 0; i < cptRoomslist.size(); i++) {
			JsonDepartment jsonDepartment = new JsonDepartment();
			jsonDepartment.setId(cptRoomslist.get(i).getId()+"");
			jsonDepartment.setpId(cptRoomslist.get(i).getParentid());
			jsonDepartment.setName(cptRoomslist.get(i).getRoomname());
			areas.add(jsonDepartment);
		}
		
		JsonDepartment jsonDepartment = new JsonDepartment();
		jsonDepartment.setId("-1");
		jsonDepartment.setpId("00001");
		jsonDepartment.setName("所有机房");
		areas.add(jsonDepartment);

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

		String userStr = JSON.toJSONString(areas);// 使用fastjson将数据转换成json格式

		ValueContext.putValueContext("cptroomlist", userStr);
		
		return "cptroomlist";
	}
	
	
	public void cptRoomById() throws IOException {

		// 获取传递过来机房的id
		String id = cptRoom.getId()+"";
		System.out.println("机房的id" + id);
		List<CptRoom> cptRoomslist = cptRoomService.cptRoomByid(id);
		CptRoom cptRoom = cptRoomslist.get(0); 

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

		String userStr = JSON.toJSONString(cptRoom);// 使用fastjson将数据转换成json格式
		PrintWriter writer = ServletActionContext.getResponse().getWriter();

		writer.write(userStr);
		System.out.println(userStr);
		writer.flush();
		writer.close();
	}
	
	//删除机房
	public String deletedcptroom() {
		
		String conditon = "";
		// 获取传递过来的要删除的机房的id
		String id = cptRoom.getId()+"";
		System.out.println("要删除的机房的id" + id);
		
		conditon = cptRoomService.deleteByCptRoomid(id);
	
		return "updatedcptroom";
	}
	
}
