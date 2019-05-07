package ssh.web.action;

import ssh.domain.Area;
import ssh.domain.Dicttype;
import ssh.domain.JsonDepartment;
import ssh.service.IAreaService;
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
public class DicttypeAction extends ActionSupport implements ModelDriven<Dicttype> {

	private Dicttype dicttype = new Dicttype();

	@Override
	public Dicttype getModel() {
		return dicttype;
	}

	private IDicttypeService dicttypeService;

	public void setDicttypeService(IDicttypeService dicttypeService) {
		this.dicttypeService = dicttypeService;
	}
	
	/**
	 * 字典列表
	 * 
	 * @return
	 */
	public String dictlist() {
		
		List<Dicttype> dicttypeslist = dicttypeService.findDicttypeNamelist();
		// 返回一个JSONArray对象
		List<JsonDepartment> areas = new ArrayList<>();
		// 添加部门
		for (int i = 0; i < dicttypeslist.size(); i++) {
			JsonDepartment jsonDepartment = new JsonDepartment();
			jsonDepartment.setId(dicttypeslist.get(i).getDictid());
			jsonDepartment.setpId(dicttypeslist.get(i).getParentdictid());
			jsonDepartment.setName(dicttypeslist.get(i).getDictname());
			areas.add(jsonDepartment);
		}
		
		JsonDepartment jsonDepartment = new JsonDepartment();
		jsonDepartment.setId("-1");
		jsonDepartment.setpId("00001");
		jsonDepartment.setName("所有字典");
		areas.add(jsonDepartment);

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

		String userStr = JSON.toJSONString(areas);// 使用fastjson将数据转换成json格式

		ValueContext.putValueContext("dictlist", userStr);
		
		return "dictlist";
	}
	
	/*通过dictid获得dicttype对象*/
	public void dictByDictId() throws IOException {

		// 获取传递过来字典的dictid
		String id = dicttype.getDictid();
		System.out.println("字典的dictid" + id);
		List<Dicttype> dicttypeslist = dicttypeService.areaByDicttypeid(id);
		Dicttype dicttype = dicttypeslist.get(0); 

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

		String userStr = JSON.toJSONString(dicttype);// 使用fastjson将数据转换成json格式
		PrintWriter writer = ServletActionContext.getResponse().getWriter();

		writer.write(userStr);
		System.out.println(userStr);
		writer.flush();
		writer.close();
	}
	
	public String deletedarea() {
		
		String conditon = "";
		// 获取传递过来的要删除的字典的dictid
		String id = dicttype.getDictid();
		System.out.println("要删除的字典的dictid" + id);
		
		conditon = dicttypeService.deleteByDicttypeid(id);
	
		return "updateddict";
	}
	
	/**
	 *添加字典
	 */
	public String adddict() {
		
		String dictname = dicttype.getDictname();
		String parentid = dicttype.getParentdictid();
		String remark = dicttype.getDictremark();
		String dictcode = dicttype.getDictcode();
		
		System.out.println("添加的字典名称是" + dictname);
		System.out.println("添加的字典父类id是" + parentid);
		
		List<Dicttype> dicttypeslist = dicttypeService.findDicttypeByPid(parentid);
		System.out.println("相同父类的几点有"+dicttypeslist.size());
		int num = dicttypeslist.size()-1;
		Dicttype dicttype = dicttypeslist.get(num);
		int id = dicttype.getId()+1;
		int dictid = Integer.parseInt(dicttypeslist.get(num).getDictid())+1;
		String adddictid = dictid+"";
		
		System.out.println("添加的id是" + id);
		System.out.println("添加的dictid是" + dictid);
		System.out.println("添加的dictcode是" + dictcode);
		
		Dicttype adddicttype = new Dicttype();
		adddicttype.setId(id);
		adddicttype.setDictid(adddictid);
		adddicttype.setParentdictid(parentid);
		adddicttype.setDictcode(dictcode);
		adddicttype.setLeaf("0");
		adddicttype.setDictname(dictname);
		adddicttype.setDictremark(remark);
		adddicttype.setSystype("2");
		
		String conditon = dicttypeService.saveadddicttype(adddicttype);
		System.out.println("新增的字典保存" + conditon);
		
		return "updateddict";
	}
	
	//保存修改后的字典
	public String saveediterdict() {
		
		String dictid = dicttype.getDictid();
		String areaname = dicttype.getDictname();
		String remark = dicttype.getDictremark();
		String dictcode = dicttype.getDictcode();
		
		System.out.println("修改保存的name是" + areaname);
		System.out.println("修改保存的备注是" + remark);
		System.out.println("修改保存的字典dictid是" + dictid);
		
		List<Dicttype> dicttypeslist = dicttypeService.areaByDicttypeid(dictid);
		Dicttype dicttype2 = dicttypeslist.get(0);
		dicttype2.setDictname(areaname);
		dicttype2.setDictcode(dictcode);
		dicttype2.setDictremark(remark);
		
		
		String conditon = dicttypeService.updatearea(dicttype2);
		System.out.println("修改字典保存" + conditon);
		
		return "updateddict";
	}
	
}
