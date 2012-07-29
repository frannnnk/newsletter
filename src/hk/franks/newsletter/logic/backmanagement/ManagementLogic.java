package hk.franks.newsletter.logic.backmanagement;

import hk.franks.newsletter.dao.CoreInfoDisplayDao;
import hk.franks.newsletter.dao.backmanagement.ManagementDao;
import hk.franks.newsletter.pojo.DeliverScopePojo;
import hk.franks.newsletter.pojo.FoodCategoryPojo;
import hk.franks.newsletter.pojo.RestaurantDeliverScopePojo;
import hk.franks.newsletter.pojo.RestaurantPojo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 
 * @author 胡圣朗
 * 
 */
public class ManagementLogic {
	private static Logger logger = Logger.getLogger(ManagementLogic.class
			.getName()); // 日志对象;
	private ManagementDao dao = new ManagementDao();
	private CoreInfoDisplayDao coreinfodao = new CoreInfoDisplayDao();

	/**
	 * 获取派送位置范围列表
	 * 
	 * @return
	 */
	public List getDeliverScopeList() {
		logger.debug("获取派送位置范围列表");
		return dao.getdeliverscope();
	}

	/**
	 * 获取餐厅派送位置范围列表
	 * 
	 * @return
	 */
	public List getRestaurantDeliverScopeList(String resid) {
		logger.debug("获取派送位置范围列表");
		return dao.getRestaurantdeliverscope(resid);
	}

	/**
	 * 获取餐厅可选择新增的派送位置范围列表
	 * 
	 * @return
	 */
	public List getRestaurantAvailChooseDeliverScopeList(
			List alldeliveryScopeList, List resdeliveryScopeList) {
		logger.debug("获取餐厅可选择新增的派送位置范围列表");
		List resultList = new ArrayList();

		for (int i = 0; i < alldeliveryScopeList.size(); i++) {
			DeliverScopePojo scope = (DeliverScopePojo) alldeliveryScopeList
					.get(i);
			boolean flag = true;
			for (int j = 0; j < resdeliveryScopeList.size(); j++) {
				RestaurantDeliverScopePojo rescope = (RestaurantDeliverScopePojo) resdeliveryScopeList
						.get(j);
				if ((scope.getId()).equals(rescope.getId())) {
					flag = false;
					break;
				}
			}
			if (flag) {
				resultList.add(scope);
			}
		}
		return resultList;

	}

	/**
	 * 保存餐館信息和保存餐館配送範圍
	 * 
	 * @param name
	 * @param send_price
	 * @param description
	 * @param leaguetime
	 * @param arrivetime
	 * @param shophour
	 * @param address
	 * @param picture_url
	 * @param scopeStr
	 * @param coordinate
	 *            餐厅坐标
	 */
	public boolean saveRestaurantRelatedInfo(String name, String send_price,
			String description, String leaguetime, String arrivetime,
			String shophour, String address, String scopeStr,
			String coordinate, String phone, String email, String fax) {
		logger.debug("保存餐館信息和保存餐館配送範圍");
		boolean flag = false;
		String coordinates[] = coordinate.split(",");
		String x_coor = coordinates[0];
		String y_coor = coordinates[1];
		int restaurantid = this.saveRestaurant(name, send_price, description,
				leaguetime, arrivetime, shophour, address, x_coor, y_coor,
				phone, email, fax);
		if (restaurantid != -1 && !scopeStr.equals("")) {
			flag = this.saveRestaurantDeliverScope(restaurantid, scopeStr);
		}
		return flag;
	}

	/**
	 * 保存餐館信息
	 * 
	 * @param name
	 * @param send_price
	 * @param description
	 * @param leaguetime
	 * @param arrivetime
	 * @param shophour
	 * @param address
	 * @param picture_url
	 * @param x_coor
	 * @param y_coor
	 * @return 餐廳ID
	 */
	private int saveRestaurant(String name, String send_price,
			String description, String leaguetime, String arrivetime,
			String shophour, String address, String x_coor, String y_coor,
			String phone, String email, String fax) {
		logger.debug("保存餐館信息");
		return dao.saveRestaurant(name, send_price, description, leaguetime,
				arrivetime, shophour, address, x_coor, y_coor, phone, email,
				fax);
	}

	/**
	 * 保存餐館配送範圍
	 * 
	 * @param restaurantid
	 * @param scopeStr
	 * @return
	 */
	private boolean saveRestaurantDeliverScope(int restaurantid, String scopeStr) {
		String[] scopeArray = scopeStr.split(",");
		List scopeList = new ArrayList();
		for (int i = 0; i < scopeArray.length; i++) {
			scopeList.add(scopeArray[i]);
		}
		return dao.saveRestaurantDeliverScope(restaurantid, scopeList);
	}

	/**
	 * 获取餐厅
	 * 
	 * @return 餐厅列表
	 */
	public List getRestaurants() {
		logger.debug(" 获取餐厅");
		return dao.getrestaurant();
	}

	/**
	 * 获取某餐厅对象.
	 * 
	 * @param resid
	 * @return
	 */
	public RestaurantPojo getRestaurantPojo(String resid) {
		return coreinfodao.getRestaurantInfo(new Integer(resid));
	}

	/**
	 * 获取某餐厅对象.
	 * 
	 * @param resid
	 * @return
	 */
	public RestaurantPojo getRestaurantPojoFormanagement(String resid) {
		return coreinfodao.getRestaurantInfoFormanagement(new Integer(resid));
	}

	/**
	 * 保存食物类别 ,单个类别保存
	 * 
	 * @param restaurantid
	 *            餐厅ID
	 * @param foodcategory
	 *            多个类别逗号分隔.
	 */
	public boolean saveFoodcategory(String restaurantid, String foodcategory,
			String fcdescription) {
		logger.debug("保存食物类别");
		return dao.savefoodcategory(new Integer(restaurantid), foodcategory,
				fcdescription);
	}

	/**
	 * 全角逗号转半角
	 * 
	 * @param message
	 * @return
	 */
	private String commaSBCConnvertion(String message) {
		if (message.indexOf("；") != -1) {
			message = message.replace("；", ";");
		}
		return message;
	}

	/**
	 * 获取食物类别 返回JSONTEXT
	 * 
	 * @param restaurantid
	 * @return JSONTEXT
	 */
	public String getFoodCategoeryJSONText(String restaurantid) {
		logger.debug("获取食物类别 返回JSONTEXT");
		List resultList = dao.getfoodcategory(new Integer(restaurantid.trim())
				.intValue());

		JSONArray foodcategoryArray = new JSONArray();

		for (int i = 0; i < resultList.size(); i++) {
			FoodCategoryPojo foodcategorypojo = (FoodCategoryPojo) resultList
					.get(i);

			JSONObject foodcategoryObj = new JSONObject();
			try {
				foodcategoryObj.append("id", foodcategorypojo.getId());
				foodcategoryObj.append("name", foodcategorypojo.getName());
				foodcategoryArray.put(foodcategoryObj);
			} catch (JSONException e) {

				e.printStackTrace();
			}
		}

		return foodcategoryArray.toString();
	}

	/**
	 * 获取食物类别
	 * 
	 * @param restaurantid
	 * @return
	 */
	public List getFoodCategoery(String restaurantid) {
		logger.debug("获取食物类别 返回JSONTEXT");
		List resultList = dao.getfoodcategory(new Integer(restaurantid.trim())
				.intValue());
		return resultList;
	}

	/**
	 * 获取食物
	 * 
	 * @param restaurantid
	 * @return
	 */
	public List getFood(String restaurantid) {
		return dao.getfood(new Integer(restaurantid.trim()));
	}

	/**
	 * 保存食物
	 * 
	 * @param restaurantid
	 * @param foodname
	 * @param price
	 * @param description
	 * @param picturename
	 * @param is_recommend
	 * @param foodcategory_id
	 * @return
	 */
	public boolean savefood(String restaurantid, String foodname, String price,
			String description, String is_recommend, String foodcategory_id) {
		logger.debug("保存食物");
		return dao.savefood(restaurantid, foodname, price, description,
				is_recommend, foodcategory_id);
	}

	/**
	 * 保存地图位置区域信息，用于分区域.
	 * 
	 * @param description
	 * @param specificdescription
	 * @param point1_coordinate
	 * @param point2_coordinate
	 * @param point3_coordinate
	 * @param point4_coordinate
	 * @return
	 */
	public boolean saveLocationmapInfo(String description,
			String specificdescription, String point1_coordinate,
			String point2_coordinate, String point3_coordinate,
			String point4_coordinate) {
		String[] point1array = point1_coordinate.split(","); // 0-x坐标;1-y坐标;
		String[] point2array = point2_coordinate.split(","); // 0-x坐标;1-y坐标;
		String[] point3array = point3_coordinate.split(","); // 0-x坐标;1-y坐标;
		String[] point4array = point4_coordinate.split(","); // 0-x坐标;1-y坐标;
		List xList = new ArrayList();
		List yList = new ArrayList();
		xList.add(point1array[0]);
		xList.add(point2array[0]);
		xList.add(point3array[0]);
		xList.add(point4array[0]);
		yList.add(point1array[1]);
		yList.add(point2array[1]);
		yList.add(point3array[1]);
		yList.add(point4array[1]);
		String x_max_coordinate = point1array[0];
		String x_min_coordinate = point1array[0];
		String y_max_coordinate = point1array[1];
		String y_min_coordinate = point1array[1];
		// 找出最大和最小的XY坐标
		for (int i = 1; i < 4; i++) {
			if (new Float(x_max_coordinate.trim()) < new Float(
					((String) xList.get(i)).trim())) {
				x_max_coordinate = (String) xList.get(i);
			}
			if (new Float(x_min_coordinate.trim()) > new Float(
					((String) xList.get(i)).trim())) {
				x_min_coordinate = (String) xList.get(i);
			}
			if (new Float(y_max_coordinate.trim()) < new Float(
					((String) yList.get(i)).trim())) {
				y_max_coordinate = (String) yList.get(i);
			}
			if (new Float(y_min_coordinate.trim()) > new Float(
					((String) yList.get(i)).trim())) {
				y_min_coordinate = (String) yList.get(i);
			}
		}

		return dao.savediliverScope(description, specificdescription,
				point1array, point2array, point3array, point4array,
				x_max_coordinate, x_min_coordinate, y_max_coordinate,
				y_min_coordinate);

	}

	/**
	 * 修改餐厅基本信息.
	 * 
	 * @param resid
	 * @param sendprice
	 * @param arrivetime
	 * @param diliveryfee
	 * @param shophour
	 * @param ishot
	 * @param flag
	 * @return true成功.
	 */
	public int updaterestaurantbasicinfo(String resid, String sendprice,
			String arrivetime, String diliveryfee, String shophour,
			String ishot, String availableflag, String description,
			String iscoorperation, String rank) {
		logger.debug("修改餐厅基本信息.resid = " + resid);
		boolean resultflag = dao.updateRestaurantinfo(resid, sendprice,
				arrivetime, diliveryfee, shophour, ishot, availableflag,
				description, iscoorperation, rank);
		if (resultflag)
			return 0;
		else
			return -1;
	}

	/**
	 * 修改食物信息
	 * 
	 * @param foodid
	 * @param foodprice
	 * @param is_recommend
	 * @param availableflag
	 * @return
	 */
	public boolean updatefood(String foodid, String foodprice,
			String is_recommend, String availableflag, String foodcategory_id,
			String foodname) {
		logger.debug("修改食物信息.foodid = " + foodid);
		return dao.updatefoodInfo(foodid, foodprice, is_recommend,
				availableflag, foodcategory_id, foodname);
	}

	/**
	 * 保存新增的餐厅派送范围.
	 * 
	 * @param restaurantid
	 * @param scopeids
	 * @return
	 */
	public boolean addnewDeliveryScope(String restaurantid, String scopeids) {
		logger.debug("保存新增的餐厅派送范围.restaurantid = " + restaurantid);
		List scopeidList = new ArrayList();
		String scopeidArray[] = scopeids.split(",");
		for (int i = 0; i < scopeidArray.length; i++) {
			if (!scopeidArray[i].equals(""))
				scopeidList.add(scopeidArray[i]);
		}
		return dao.saveRestaurantDeliverScope(new Integer(restaurantid),
				scopeidList);
	}

	/**
	 * 设置餐厅指定派送位置是否启用.
	 * 
	 * @param restaurantid
	 * @param scopeid
	 * @param availflag
	 * @return
	 */
	public boolean setResDeliveryScopeAvailbiliey(String restaurantid,
			String scopeid, String availflag) {
		logger.debug("设置餐厅指定派送位置是否启用.restaurantid = " + restaurantid);
		return dao.updateResDeliScopeAvail(restaurantid, scopeid, availflag);
	}

	/**
	 * 刪除某餐廳派送範圍
	 * 
	 * @param restaurantid
	 * @param scopeid
	 * @return
	 */
	public boolean delDeliveryScope(String restaurantid, String scopeid) {
		logger.debug("刪除某餐廳派送範圍 restaurantid= " + restaurantid + "scopeid="
				+ scopeid);
		return dao.deleteDeliveryScope(restaurantid, scopeid);
	}

	/**
	 * 管理员登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean admLoginCheck(String admaccountid, String password) {
		logger.debug("管理员登录 URL来自于");
		return dao.getAdmAccountNum(admaccountid, password);
	}

	/**
	 * 修改食物類別信息
	 * 
	 * @param categoryid
	 * @param categoryname
	 * @param description
	 * @param rank
	 * @return
	 */
	public boolean updatefoodcategory(String categoryid, String categoryname,
			String description, String rank, String categoryflag) {
		logger.debug("修改食物類別信息categoryid= " + categoryid);
		return dao.updatefoodCategoryInfo(categoryid, categoryname,
				description, rank, categoryflag);
	}

	/**
	 * 删除食物类别连带食物.
	 * 
	 * @param categoryid
	 * @return
	 */
	public boolean deleteFoodcategory(String categoryid) {
		logger.debug("删除食物類別信息categoryid= " + categoryid);
		return dao.deleteFoodcategory(categoryid);
	}

}
