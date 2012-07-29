package hk.franks.newsletter.controller.backmanagement;

import hk.franks.newsletter.logic.backmanagement.ManagementLogic;
import hk.franks.newsletter.pojo.RestaurantPojo;

import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;

/**
 * 餐厅信息更新管理类。
 * 
 * @author 胡圣朗
 * @createtime 上午11:03:02
 * 
 */
public class RestaurantUpdateManagementAction extends ActionSupport {

	private static Logger logger = Logger
			.getLogger(RestaurantUpdateManagementAction.class.getName()); // 日志对象;
	private ManagementLogic logic = new ManagementLogic();

	// 输入参数
	private int type; // 0-基本信息初始化;1-修改基本信息;2-派送范围新增；3-派送范围修改.
	// 基本信息
	private String resid;
	private String sendprice;
	private String arrivetime;
	private String diliveryfee;
	private String shophour;
	private String ishot="0";
	private String iscoorperation="1"; // 是否加盟未加盟不能网上下单
	private String flag; // 餐廳启用标志1-启用
	private String deliveryscopeids;// 新增的派送区域ID字符串，多个用逗号隔开.
	private String restaurantscopeid;// 餐厅已经指定的SCOPEID。
	private String scopeflag; // 餐廳指定派送位置启用标志1-启用
	private String description; // 餐廳描述
	private String rank; //餐廳排序
	
	// 输出参数
	private List restaurantList;
	private RestaurantPojo restaurantPojo;
	private List deliveryList; // 数据库中所有的派送位置区域
	private List resAvailChooosedeliveryList; // 可供餐厅新增的派送位置区域
	private List restaurantDeliveryList; // 餐厅已经指定的派送区域

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();

		logger.info("餐厅信息更新");
		String path = this.NONE;
		if (type == 0) {// 0-基本信息初始化;
			restaurantList = logic.getRestaurants();
			path = "basicinfo";
		} else if (type == 1) {// 1-修改基本信息;
			logger.info("修改餐厅基本信息");
			int responseinfo = logic.updaterestaurantbasicinfo(resid,
					sendprice, arrivetime, diliveryfee, shophour, ishot, flag,
					description, iscoorperation, rank);
			response.getWriter().print(responseinfo);
			path = this.NONE;
		} else if (type == 2) {// 2-派送范围新增
			logger.info("餐厅派送范围新增");
			boolean flag = logic.addnewDeliveryScope(resid, deliveryscopeids);
			if (flag)
				response.getWriter().print("0");
			else
				response.getWriter().print("-1");
			path = this.NONE;
		} else if (type == 3) {// 3-派送范围修改
			logger.info("餐厅派送范围修改");
			boolean flag = logic.setResDeliveryScopeAvailbiliey(resid,
					restaurantscopeid, scopeflag);
			if (flag)
				response.getWriter().print("0");
			else
				response.getWriter().print("-1");
			path = this.NONE;
		} else if (type == 4) {// 4-派送范围update初始化.
			logger.info("餐厅派送范围update初始化.");
			restaurantPojo = logic.getRestaurantPojoFormanagement(resid);
			deliveryList = logic.getDeliverScopeList();
			restaurantDeliveryList = logic.getRestaurantDeliverScopeList(resid);
			resAvailChooosedeliveryList = logic
					.getRestaurantAvailChooseDeliverScopeList(deliveryList,
							restaurantDeliveryList);
			path = "scopeinfo";
		} else if (type == 5) { // 5-刪除派送範圍
			logger.info("刪除派送範圍");
			if (logic.delDeliveryScope(resid, restaurantscopeid))
				response.getWriter().print("1");
			else
				response.getWriter().print("0");
		}

		return path;
	}

	public int getType() {
		return type;
	}

	public RestaurantPojo getRestaurantPojo() {
		return restaurantPojo;
	}

	public List getDeliveryList() {
		return deliveryList;
	}

	public List getResAvailChooosedeliveryList() {
		return resAvailChooosedeliveryList;
	}

	public void setResAvailChooosedeliveryList(List resAvailChooosedeliveryList) {
		this.resAvailChooosedeliveryList = resAvailChooosedeliveryList;
	}

	public String getRestaurantscopeid() {
		return restaurantscopeid;
	}

	public void setRestaurantscopeid(String restaurantscopeid) {
		this.restaurantscopeid = restaurantscopeid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIscoorperation() {
		return iscoorperation;
	}

	public void setIscoorperation(String iscoorperation) {
		this.iscoorperation = iscoorperation;
	}

	public void setDeliveryList(List deliveryList) {
		this.deliveryList = deliveryList;
	}

	public String getScopeflag() {
		return scopeflag;
	}

	public void setScopeflag(String scopeflag) {
		this.scopeflag = scopeflag;
	}

	public void setRestaurantPojo(RestaurantPojo restaurantPojo) {
		this.restaurantPojo = restaurantPojo;
	}

	public List getRestaurantDeliveryList() {
		return restaurantDeliveryList;
	}

	public void setRestaurantDeliveryList(List restaurantDeliveryList) {
		this.restaurantDeliveryList = restaurantDeliveryList;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List getRestaurantList() {
		return restaurantList;
	}

	public void setRestaurantList(List restaurantList) {
		this.restaurantList = restaurantList;
	}

	public String getResid() {
		return resid;
	}

	public String getDeliveryscopeids() {
		return deliveryscopeids;
	}

	public void setDeliveryscopeids(String deliveryscopeids) {
		this.deliveryscopeids = deliveryscopeids;
	}

	public void setResid(String resid) {
		this.resid = resid;
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

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public void setArrivetime(String arrivetime) {
		this.arrivetime = arrivetime;
	}

	public String getDiliveryfee() {
		return diliveryfee;
	}

	public void setDiliveryfee(String diliveryfee) {
		this.diliveryfee = diliveryfee;
	}

	public String getShophour() {
		return shophour;
	}

	public void setShophour(String shophour) {
		this.shophour = shophour;
	}

	public String getIshot() {
		return ishot;
	}

	public void setIshot(String ishot) {
		this.ishot = ishot;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
