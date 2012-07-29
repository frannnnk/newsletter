package hk.franks.newsletter.logic;

import hk.franks.newsletter.common.Constant;
import hk.franks.newsletter.dao.CoreInfoDisplayDao;
import hk.franks.newsletter.pojo.FoodCategoryPojo;
import hk.franks.newsletter.pojo.FoodPojo;
import hk.franks.newsletter.pojo.RestaurantPojo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




/**
 * 餐厅相关信息展示核心操作逻辑操作相关（提供获取推荐外卖，推荐餐厅等数据）
 * 
 * @author 胡圣朗
 * 
 */
public class CoreInfoDisplayLogic {

	private static Logger logger = Logger.getLogger(CoreInfoDisplayLogic.class
			.getName()); // 日志对象;

	private CoreInfoDisplayDao dao = new CoreInfoDisplayDao(); // 数据库操作对象

	/**
	 * 根据区域ID获取推荐外卖
	 * 
	 * @param areaid
	 *            区域ID.
	 * @return 推荐外卖对象列表
	 */
	public List getRecommendFood(String areaid, int requestnum) {
		int numperpage = Constant.FOOD_PAGENUM; // 每页N条记录
		int startindex = requestnum * numperpage - numperpage;
		List restaurantIDlist = dao.getRestaurantIds(areaid);

		return dao.getrecommendFood(restaurantIDlist, startindex, numperpage);
	}

	/**
	 * 获取某区域推荐外卖总页数
	 * 
	 * @param areaid
	 * @return
	 */
	public int getRecommendFoodTotalPagenum(String areaid) {
		List restaurantIDlist = dao.getRestaurantIds(areaid);
		int totalRecordNum = dao
				.getrecommendFoodTotalRecordNum(restaurantIDlist);
		return (totalRecordNum - 1) / Constant.FOOD_PAGENUM + 1;
	}

	/**
	 * 获取某区域餐馆列表.分页获取每页12条记录
	 * 
	 * @param areaid
	 * @param requestpagenum
	 * @param totalnum
	 * @return 区域餐馆列表
	 */
	public List getAreaRestaurant(String areaid, int requestpagenum) {
		int numperpage = Constant.RESTAURANT_PAGENUM; // 每页N条记录
		int startindex = requestpagenum * numperpage - numperpage;
		List restaurantList = dao.getRestaurantsInfo(areaid, startindex,
				numperpage);
		return restaurantList;
	}

	/**
	 * 给每个餐厅构造离用户的距离
	 * 
	 * @param restaurantList
	 * @param x_coordinate
	 * @param y_coordinate
	 */
	public List bulidDistanceForRestaurant(List restaurantList,
			String x_coordinate, String y_coordinate) {

		for (int i = 0; i < restaurantList.size(); i++) {
			RestaurantPojo restaurantpojo = (RestaurantPojo) restaurantList
					.get(i);
			int distance = this.calclulateDistance(new Double(x_coordinate),
					new Double(y_coordinate),
					new Double(restaurantpojo.getX_coordinate()), new Double(
							restaurantpojo.getY_coordinate()));
			restaurantpojo.setDistance(new Integer(distance).toString());
		}

		return restaurantList;
	}

	/**
	 * 构造餐厅集合对象的JSON对象TEXT.
	 * 
	 * @return
	 */
	public String buildRestaurantListJSONText(List restauratnList) {
		logger.debug("构造餐厅集合对象的JSON对象TEXT.");
		JSONObject root = new JSONObject();
		JSONArray restaurantArray = new JSONArray();
		try {
			for (int i = 0; i < restauratnList.size(); i++) {
				RestaurantPojo restaurantpojo = (RestaurantPojo) restauratnList
						.get(i);
				JSONObject restaurantobj = new JSONObject();

				restaurantobj.put("id", restaurantpojo.getId());
				restaurantobj.put("name", restaurantpojo.getName());
				restaurantobj.put("distance", restaurantpojo.getDistance());
				restaurantobj.put("time", restaurantpojo.getArrivetime());
				restaurantobj.put("sendprice", restaurantpojo.getSend_price());
				restaurantobj.put("picture_url",
						restaurantpojo.getPicture_url());
				restaurantobj.put("isclosed", restaurantpojo.getIsclosed());
				restaurantArray.put(restaurantobj);

			}
			root.put("result", restaurantArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		return root.toString();
	}

	/**
	 * 构造食物集合对象的JSON对象TEXT.
	 * 
	 * @param restauratnList
	 * @return
	 */
	public String buildFoodListJSONText(List recommendFoodlist) {
		logger.debug("构造餐厅集合对象的JSON对象TEXT.");
		JSONObject root = new JSONObject();
		JSONArray foodArray = new JSONArray();
		try {
			for (int i = 0; i < recommendFoodlist.size(); i++) {
				FoodPojo foodpojo = (FoodPojo) recommendFoodlist.get(i);
				JSONObject foodobj = new JSONObject();
				foodobj.put("foodid", foodpojo.getFoodid());
				foodobj.put("picture_url", foodpojo.getPicture_url());
				foodobj.put("foodname", foodpojo.getFoodname());
				foodobj.put("price", foodpojo.getPrice());
				foodobj.put("restaurantid", foodpojo.getRestaurant_id());
				foodobj.put("restaurantname", foodpojo.getRestaurantname());
				foodobj.put("food_isclosed", foodpojo.getFood_isclosed());
				foodArray.put(foodobj);
			}
			root.put("result", foodArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		return root.toString();
	}

	/**
	 * 获取餐厅的总页数.
	 * 
	 * @param areaid
	 * @return
	 */
	public int getAreaRestaurantTotalPageNum(String areaid) {
		int totalAreaRestaurantNum = dao.getRestaurantTotalNum(areaid);
		return (totalAreaRestaurantNum - 1) / Constant.RESTAURANT_PAGENUM + 1;
	}

	/**
	 * 获取区域的热门餐厅.
	 * 
	 * @param areaid
	 * @return
	 */
	public List getAreaHotRestaurant(String areaid) {
		return dao.getHotRestaurantsInfo(areaid);
	}

	/**
	 * 获取食物集合对象
	 * 
	 * @param restaurantid
	 * @return List [0]-食物类别集合；[1]-分类食物对象集合集合;[2]-食物類別對象集合
	 */
	public List getFood(String restaurantid) {
		List resultList = dao.getfood(new Integer(restaurantid).intValue());
		List sortedFoodList = new ArrayList();// 集合中是分类后的食物集合.
		List categoryList = new ArrayList(); // 集合中是类别名称字符串.
		List categoryPojoList = new ArrayList(); // 集合中是类别名称字符串.
		// 得到所有食物类别
		for (int i = 0; i < resultList.size(); i++) {
			FoodPojo foodpojo = (FoodPojo) resultList.get(i);
			if (categoryList.indexOf(foodpojo.getFoodcategory_name()) == -1) {
				categoryList.add(foodpojo.getFoodcategory_name());
				FoodCategoryPojo foodcategorypojo = new FoodCategoryPojo();
				foodcategorypojo.setId(foodpojo.getFoodcategory_id());
				foodcategorypojo.setName(foodpojo.getFoodcategory_name());
				foodcategorypojo.setDescription(foodpojo
						.getFoodcategory_description());
				categoryPojoList.add(foodcategorypojo);
			}
		}
		// 将食物集合进行重组 一个类别食物 一个集合.
		for (int j = 0; j < categoryList.size(); j++) {
			List tmpList = new ArrayList();
			String categoryname = (String) categoryList.get(j);
			for (int z = 0; z < resultList.size(); z++) {
				FoodPojo foodpojo = (FoodPojo) resultList.get(z);
				if (foodpojo.getFoodcategory_name().equals(categoryname))
					tmpList.add(foodpojo);
			}
			sortedFoodList.add(tmpList);
		}

		List foodrelatedresultList = new ArrayList();// [0]-食物类别集合；[1]-分类食物对象集合集合;
														// [2]-食物類別對象集合
		foodrelatedresultList.add(categoryList);
		foodrelatedresultList.add(sortedFoodList);
		foodrelatedresultList.add(categoryPojoList);
		return foodrelatedresultList;
	}

	/**
	 * 获取餐馆信息
	 * 
	 * @param restaurantid
	 * @return 餐馆对象
	 */
	public RestaurantPojo getRestaurant(String restaurantid) {
		return dao.getRestaurantInfo(new Integer(restaurantid).intValue());
	}

	/**
	 * 获取热门餐馆自构造字符串.配套页面格式要求.
	 * 
	 * @return
	 */
	public String getHotrestaurantStr(List hotrestaurantList, String locationstr) {
		String hotRestaurantsValue = "";
		for (int i = 0; i < hotrestaurantList.size(); i++) {

			RestaurantPojo restaurantObj = (RestaurantPojo) hotrestaurantList
					.get(i);
			String restaurantname = restaurantObj.getName();
			String pictureURL = restaurantObj.getPicture_url();
			String hangonTitle = restaurantObj.getName() + "(外卖)";
			String actionurl = "restaurantmenuinitaction.action?restaurantid="
					+ restaurantObj.getId() + "&locationstr=" + locationstr; // 待定.
			String splitmark = "@||@"; // 分隔符合.
			String singleHotRestaurantValue = restaurantname + splitmark
					+ pictureURL + splitmark + hangonTitle + splitmark
					+ actionurl;
			String linkmark = "*||*";
			hotRestaurantsValue = hotRestaurantsValue + linkmark
					+ singleHotRestaurantValue;
		}
		if (!hotRestaurantsValue.equals(""))
			hotRestaurantsValue = hotRestaurantsValue.substring(4);
		return hotRestaurantsValue;
	}

	/**
	 * 计算2点位置之间的距离.
	 * 
	 * @param sX
	 * @param sY
	 * @param eX
	 * @param eY
	 * @return 2点位置之间的距离
	 */
	public int calclulateDistance(double sX, double sY, double eX, double eY) {
		double lat[] = { sX, eX };
		double lng[] = { sY, sY };
		int R = 6378137; // 地球半径
		double dLat = (lat[1] - lat[0]) * Math.PI / 180;
		double dLng = (lng[1] - lng[0]) * Math.PI / 180;
		double dLat1 = lat[0] * Math.PI / 180;
		double dLat2 = lat[1] * Math.PI / 180;
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(dLat1)
				* Math.cos(dLat1) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;
		return (int) Math.round(d);
	}

	// -------------below is new ecmeal service

	/**
	 * 获取某区域在线菜单
	 * 
	 * @param areaid
	 * @return null- no menu; otherwise- there are some menus.
	 * 
	 */
	public List<List> getAreaFoodMenu(String areaid) {
		logger.debug("getting online menu...");
		List<List> restaurantsFoodList = new ArrayList();

		// [2]-食物類別對象集合
		List<FoodPojo> foodList = null;
		List<String> restaurantIdList = dao.getRestaurantsInfo(areaid);

		if (restaurantIdList.size() > 0) {
			StringBuffer residBuffer = new StringBuffer();
			for (String resid : restaurantIdList) {
				residBuffer.append(resid);
				residBuffer.append(",");
			}
			residBuffer.deleteCharAt(residBuffer.length() - 1);
			String resids = residBuffer.toString();
			foodList = dao.getfood(resids);

			for (String tmpresid : restaurantIdList) {
				List foodrelatedresultList = new ArrayList();// [0]-食物类别集合；[1]-分类食物对象集合集合;
				List<List> sortedFoodList = new ArrayList();// 分类后的食物集合.
				List<String> categoryList = new ArrayList(); // 集合中是类别名称字符串.
				List<FoodCategoryPojo> categoryPojoList = new ArrayList(); // 集合中是类别名称字符串.
				// 得到单家餐厅的所有食物类别
				for (int i = 0; i < foodList.size(); i++) {
					FoodPojo foodpojo = (FoodPojo) foodList.get(i);
					if (tmpresid.equals(foodpojo.getRestaurant_id())) {
						if (categoryList.indexOf(foodpojo
								.getFoodcategory_name()) == -1) {
							categoryList.add(foodpojo.getFoodcategory_name());
							FoodCategoryPojo foodcategorypojo = new FoodCategoryPojo();
							foodcategorypojo.setId(foodpojo
									.getFoodcategory_id());
							foodcategorypojo.setName(foodpojo
									.getFoodcategory_name());
							foodcategorypojo.setDescription(foodpojo
									.getFoodcategory_description());
							categoryPojoList.add(foodcategorypojo);
						}
					}
				}
				// 将食物集合进行重组 一个类别食物 一个集合.
				for (int j = 0; j < categoryList.size(); j++) {
					List<FoodPojo> tmpList = new ArrayList();
					String categoryname = (String) categoryList.get(j);
					for (int z = 0; z < foodList.size(); z++) {
						FoodPojo foodpojo = (FoodPojo) foodList.get(z);
						if (tmpresid.equals(foodpojo.getRestaurant_id())) {
							if (foodpojo.getFoodcategory_name().equals(
									categoryname))
								tmpList.add(foodpojo);
						}

					}
					sortedFoodList.add(tmpList);
				}

				foodrelatedresultList.add(categoryList);
				foodrelatedresultList.add(sortedFoodList);
				foodrelatedresultList.add(categoryPojoList);
				restaurantsFoodList.add(foodrelatedresultList);

				for (int i = 0; i < sortedFoodList.size(); i++) {
					List foodpojoList = (List) sortedFoodList.get(i);
					for (int j = 0; j < foodpojoList.size(); j++) {
						FoodPojo foodpojo = (FoodPojo) foodpojoList.get(j);
						System.out.println(foodpojo.getFoodname() + ", ");

					}

				}

			}

			return restaurantsFoodList;

		} else
			return restaurantsFoodList;
	}
}
