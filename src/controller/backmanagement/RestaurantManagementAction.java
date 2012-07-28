package controller.backmanagement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import logic.backmanagement.ManagementLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 餐馆管理控制类(用于后台管理)
 * 
 * @author 胡圣朗
 * 
 */
public class RestaurantManagementAction extends ActionSupport {

	private static Logger logger = Logger
			.getLogger(RestaurantManagementAction.class.getName()); // 日志对象;
	private ManagementLogic logic = new ManagementLogic();

	private String name;
	private String description;
	private String sendprice;
	private String arrivetime;
	private String shopstarttime;
	private String shopendtime;
	private String shopaddress;
	private String leaguetime;
	private String deliverscope = "";// 多个区域用逗号分隔开;
	private String type;
	private String coordinate;// 坐标 x和y坐标 逗号分隔.
	private String phone = "";
	private String email = "";
	private String fax = "";

	String message;// 输出参数

	public String execute() throws Exception {
		logger.info("餐馆管理");

		List list = logic.getDeliverScopeList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("deliverscopelist", list);
		String shoptime = shopstarttime + "-" + shopendtime;
		if ((type != null) && (type.equals("add"))) {// 添加餐厅操作
			boolean flag = logic.saveRestaurantRelatedInfo(name, sendprice,
					description, leaguetime, arrivetime, shoptime, shopaddress,
					deliverscope, coordinate, phone, email, fax);
			if (flag)
				message = "上一条位置信息保存成功";
			else
				message = "上一条保存失败，请联系圣朗";
			request.setAttribute("responsemessage", message);

		}
		return this.SUCCESS;
	}

	public ManagementLogic getLogic() {
		return logic;
	}

	public void setLogic(ManagementLogic logic) {
		this.logic = logic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSendprice() {
		return sendprice;
	}

	public void setSendprice(String sendprice) {
		this.sendprice = sendprice;
	}

	public String getArrivetime() {
		return arrivetime;
	}

	public void setArrivetime(String arrivetime) {
		this.arrivetime = arrivetime;
	}

	public String getShopstarttime() {
		return shopstarttime;
	}

	public void setShopstarttime(String shopstarttime) {
		this.shopstarttime = shopstarttime;
	}

	public String getShopendtime() {
		return shopendtime;
	}

	public void setShopendtime(String shopendtime) {
		this.shopendtime = shopendtime;
	}

	public String getShopaddress() {
		return shopaddress;
	}

	public void setShopaddress(String shopaddress) {
		this.shopaddress = shopaddress;
	}

	public String getLeaguetime() {
		return leaguetime;
	}

	public void setLeaguetime(String leaguetime) {
		this.leaguetime = leaguetime;
	}

	public String getDeliverscope() {
		return deliverscope;
	}

	public void setDeliverscope(String deliverscope) {
		this.deliverscope = deliverscope;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

}
