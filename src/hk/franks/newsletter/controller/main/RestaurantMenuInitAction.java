package hk.franks.newsletter.controller.main;

import hk.franks.newsletter.logic.CoreInfoDisplayLogic;
import hk.franks.newsletter.logic.map.MapRelatedLogic;
import hk.franks.newsletter.pojo.RestaurantPojo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;

/**
 * 餐馆菜单初始化控制类
 * 
 * @author 胡圣朗
 * 
 */
public class RestaurantMenuInitAction extends ActionSupport {

	private static Logger logger = Logger
			.getLogger(RecommendFoodRestaurantInitAction.class.getName()); // 日志对象;

	private CoreInfoDisplayLogic logic = new CoreInfoDisplayLogic(); // 逻辑操作类.
	private MapRelatedLogic maplogic = new MapRelatedLogic(); // 逻辑操作类.
	// 输入参数
	private String restaurantid; // 餐馆ID 来自页面参数.
	private String locationstr;// 当前位置，如果是从主页直接进入 则要初始化SESSION。

	// 输出参数
	private List categoryList;
	private List categoryPojoList;
	private List sortedFoodList;
	private RestaurantPojo restaurantPojo;

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		logger.info("餐厅餐单初始化");
		String x_coordinate = null;
		String y_coordinate = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if (locationstr != null) { // 说明是从首页直接进入的，SESSION需要初始化.
			// 加上香港前缀.
			request = ServletActionContext.getRequest();
			String coordinates[] = maplogic.googleMapGeodeCoding("香港,"
					+ locationstr);
			String areaid = null; /*maplogic.checkScopeAvaliable(coordinates[0],
					coordinates[1]);*/
			x_coordinate = coordinates[0];
			y_coordinate = coordinates[1];
			session = request.getSession();
			String areaname = null;
			String address[] = maplogic.googleMapGeoCoding(x_coordinate,
					y_coordinate);
			areaname = address[0];
			String location = null;
			session.setAttribute("areaid", areaid);
			session.setAttribute("location", locationstr);
			session.setAttribute("areaname", areaname);
			session.setAttribute("x_coordinate", x_coordinate);
			session.setAttribute("y_coordinate", y_coordinate);
		} else {
			x_coordinate = (String) session.getAttribute("x_coordinate");
			y_coordinate = (String) session.getAttribute("y_coordinate");
		}

		List foodrelatedList = logic.getFood(restaurantid);
		restaurantPojo = logic.getRestaurant(restaurantid);
		int distance = logic.calclulateDistance(new Double(x_coordinate),
				new Double(y_coordinate),
				new Double(restaurantPojo.getX_coordinate()), new Double(
						restaurantPojo.getY_coordinate()));
		restaurantPojo.setDistance(new Integer(distance).toString());
		categoryList = (List) foodrelatedList.get(0);// 食物类别集合.
		sortedFoodList = (List) foodrelatedList.get(1);// 食物对象集合的集合index和食物类别对应.
		categoryPojoList = (List) foodrelatedList.get(2);
		return SUCCESS;
	}

	public String getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(String restaurantid) {
		this.restaurantid = restaurantid;
	}

	public List getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List categoryList) {
		this.categoryList = categoryList;
	}

	public String getLocationstr() {
		return locationstr;
	}

	public void setLocationstr(String locationstr) {
		this.locationstr = locationstr;
	}

	public List getCategoryPojoList() {
		return categoryPojoList;
	}

	public void setCategoryPojoList(List categoryPojoList) {
		this.categoryPojoList = categoryPojoList;
	}

	public List getSortedFoodList() {
		return sortedFoodList;
	}

	public void setSortedFoodList(List sortedFoodList) {
		this.sortedFoodList = sortedFoodList;
	}

	public RestaurantPojo getRestaurantPojo() {
		return restaurantPojo;
	}

	public void setRestaurantPojo(RestaurantPojo restaurantPojo) {
		this.restaurantPojo = restaurantPojo;
	}

}
