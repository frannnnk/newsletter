package hk.franks.newsletter.controller.backmanagement;

import hk.franks.newsletter.logic.backmanagement.ManagementLogic;
import hk.franks.newsletter.pojo.RestaurantPojo;

import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author 胡圣朗
 * 
 */
public class FoodmanagementAction extends ActionSupport {

	private static Logger logger = Logger
			.getLogger(RestaurantManagementAction.class.getName()); // 日志对象;

	private ManagementLogic logic = new ManagementLogic();
	private String type; // null-初始化添加食物;initaddcategory-初始化添加食物类别;
							// add-添加食物操作;addfoodcategory-添加食物类别;getfoodcategory-获取食物类别;

	// 输入参数
	public String restaurantid; // 餐厅ID;
	public String foodid;// 食物ID.
	public String foodcategory; // 食物类别；多个类别 逗号分隔开
	public String fcdescription;// 食物类别说明;

	public String foodcategoryid;// 食物类别id
	public String foodname;
	public String description;
	public String price;
	public String isrecommend;// 是否推荐 0-否;1-推荐;
	public String foodflag;// 是否启用 0-否;1-推荐;

	// 傳入參數 修改食物類別信息
	private String categoryid;
	private String categoryname;
	private String categorydescription;
	private String categoryrank;
	private String categoryflag;

	// 输出参数
	private RestaurantPojo restaurantPojo;
	private String message;
	private List foodcategoryList;
	private List foodList;

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		logger.info("食物管理操作");
		String path = NONE;
		HttpServletResponse response = ServletActionContext.getResponse();
		if (type == null) {// 初始化食物管理页面（可做食物添加和修改操作）
			path = "initaddfood";
			foodcategoryList = logic.getFoodCategoery(restaurantid);
			foodList = logic.getFood(restaurantid);
			restaurantPojo = logic.getRestaurantPojo(restaurantid);
		} else if (type.equals("initaddcategory")) {
			path = "initaddcategory";
			restaurantPojo = logic.getRestaurantPojo(restaurantid);
			foodcategoryList = logic.getFoodCategoery(restaurantid);

		} else if (type.equals("add")) { // 添加食物操作;
			boolean flag = logic.savefood(restaurantid, foodname, price,
					description, isrecommend, foodcategoryid);
			if (flag)
				response.getWriter().print("0");
			else
				response.getWriter().print("-1");
			path = this.NONE;
		} else if (type.equals("addfoodcategory")) {// 添加食物类别操作;
			path = "initaddcategory";
			boolean flag = logic.saveFoodcategory(restaurantid, foodcategory,
					fcdescription);
			if (flag)
				message = "添加成功";
			else
				message = "操作失败， 请联系管理员";
			restaurantPojo = logic.getRestaurantPojo(restaurantid);
			foodcategoryList = logic.getFoodCategoery(restaurantid);
		} else if (type.equals("foodupdate")) {// 食物信息修改操作;
			boolean flag = logic.updatefood(foodid, price, isrecommend,
					foodflag, foodcategoryid, foodname);
			if (flag)
				response.getWriter().print("0");
			else
				response.getWriter().print("-1");
		} else if (type.equals("foodcategoryupdate")) {// 修改食物類別相關信息.
			boolean flag = logic.updatefoodcategory(categoryid, categoryname,
					categorydescription, categoryrank, categoryflag);
			if (flag)
				response.getWriter().print("0");
			else
				response.getWriter().print("-1");
		} else if (type.equals("foodcategorydelete")) { // 食物类别连带食物一起删除
			path = "initaddcategory";
			logic.deleteFoodcategory(categoryid);
			restaurantPojo = logic.getRestaurantPojo(restaurantid);
			foodcategoryList = logic.getFoodCategoery(restaurantid);
		}
		return path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RestaurantPojo getRestaurantPojo() {
		return restaurantPojo;
	}

	public List getFoodList() {
		return foodList;
	}

	public void setFoodList(List foodList) {
		this.foodList = foodList;
	}

	public String getFoodid() {
		return foodid;
	}

	public void setFoodid(String foodid) {
		this.foodid = foodid;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getCategorydescription() {
		return categorydescription;
	}

	public void setCategorydescription(String categorydescription) {
		this.categorydescription = categorydescription;
	}

	public String getCategoryrank() {
		return categoryrank;
	}

	public void setCategoryrank(String categoryrank) {
		this.categoryrank = categoryrank;
	}

	public String getFoodflag() {
		return foodflag;
	}

	public void setFoodflag(String foodflag) {
		this.foodflag = foodflag;
	}

	public void setRestaurantPojo(RestaurantPojo restaurantPojo) {
		this.restaurantPojo = restaurantPojo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIsrecommend() {
		return isrecommend;
	}

	public List getFoodcategoryList() {
		return foodcategoryList;
	}

	public void setFoodcategoryList(List foodcategoryList) {
		this.foodcategoryList = foodcategoryList;
	}

	public String getFcdescription() {
		return fcdescription;
	}

	public void setFcdescription(String fcdescription) {
		this.fcdescription = fcdescription;
	}

	public void setIsrecommend(String isrecommend) {
		this.isrecommend = isrecommend;
	}

	public String getFoodcategory() {
		return foodcategory;
	}

	public void setFoodcategory(String foodcategory) {
		this.foodcategory = foodcategory;
	}

	public String getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(String restaurantid) {
		this.restaurantid = restaurantid;
	}

	public String getFoodcategoryid() {
		return foodcategoryid;
	}

	public void setFoodcategoryid(String foodcategoryid) {
		this.foodcategoryid = foodcategoryid;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description.trim();
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price.trim();
	}

	public String getCategoryflag() {
		return categoryflag;
	}

	public void setCategoryflag(String categoryflag) {
		this.categoryflag = categoryflag;
	}

}
