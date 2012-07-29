package hk.franks.newsletter.controller.backmanagement;

import hk.franks.newsletter.logic.backmanagement.ManagementLogic;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class MaplocationManagementAction extends ActionSupport {

	private static Logger logger = Logger
			.getLogger(MaplocationManagementAction.class.getName()); // 日志对象;
	private ManagementLogic logic = new ManagementLogic();

	private String point1; // 格式x_coordinate,y_coordinate
	private String point2; // 格式x_coordinate,y_coordinate
	private String point3; // 格式x_coordinate,y_coordinate
	private String point4; // 格式x_coordinate,y_coordinate
	private String description; // 区域名称
	private String specificdescription; // 区域详细描述.

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		logger.info("保存位置地图信息");
		boolean flag = logic.saveLocationmapInfo(description,
				specificdescription, point1, point2, point3, point4);
		String message;
		if (flag)
			message = "上一条位置信息保存成功";
		else
			message = "上一条保存失败，请联系圣朗";

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("responsemessage", "上一次保存餐館信息成功");

		return this.SUCCESS;
	}

	public String getPoint1() {
		return point1;
	}

	public void setPoint1(String point1) {
		this.point1 = point1;
	}

	public String getPoint2() {
		return point2;
	}

	public void setPoint2(String point2) {
		this.point2 = point2;
	}

	public String getDescription() {
		return description;
	}

	public String getPoint3() {
		return point3;
	}

	public void setPoint3(String point3) {
		this.point3 = point3;
	}

	public String getPoint4() {
		return point4;
	}

	public void setPoint4(String point4) {
		this.point4 = point4;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpecificdescription() {
		return specificdescription;
	}

	public void setSpecificdescription(String specificdescription) {
		this.specificdescription = specificdescription;
	}

}
