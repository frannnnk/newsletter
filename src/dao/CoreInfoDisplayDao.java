package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import pojo.FoodPojo;
import pojo.RestaurantPojo;

/**
 * 餐厅相关信息展示核心操作逻辑操作相关数据库操作相关
 * 
 * @author 胡圣朗
 * 
 */
public class CoreInfoDisplayDao extends DaoSupport {
	private static Logger logger = Logger.getLogger(CoreInfoDisplayDao.class
			.getName()); // 日志对象;

	/**
	 * 获取所在区域餐厅总数
	 * 
	 * @param areaid
	 *            区域ID
	 * @return 区域餐馆总数
	 */
	public int getRestaurantTotalNum(String areaid) {
		logger.debug("获取区域餐厅总数 根据区域ID=" + areaid);
		String sql = "SELECT COUNT(*) TOTLANUM FROM RESTAURANT R,"
				+ "RESTAURANT_DELIVERY_RELATION D WHERE "
				+ "R.ID=D.RESTAURANT_ID AND SCOPE_ID IN(" + areaid + ")";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		int totalnum = 0;
		try {
			pres = con.prepareStatement(sql);
			set = pres.executeQuery();
			set.next();
			totalnum = set.getInt("TOTLANUM");
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return totalnum;
	}

	/**
	 * 获取餐厅数据 根据区域ID.
	 * 
	 * @param areaid
	 * @return 餐厅数据对象集合.
	 */
	public List getRestaurantsInfo(String areaid, int startindex, int recordnum) {
		logger.debug("获取餐厅数据 根据区域ID=" + areaid);
		List restaurantList = new ArrayList();
		String sql = "SELECT * FROM RESTAURANT R,"
				+ "RESTAURANT_DELIVERY_RELATION D WHERE "
				+ "R.ID=D.RESTAURANT_ID AND D.AVAILABILITYFLAG=1 AND SCOPE_ID IN("
				+ areaid + ") ORDER BY R.ARRIVETIME,R.ID LIMIT ?,?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, startindex);
			pres.setInt(2, recordnum);
			set = pres.executeQuery();
			while (set.next()) {
				RestaurantPojo restaurantpojo = new RestaurantPojo();
				restaurantpojo.setId(set.getString("id"));
				restaurantpojo.setName(set.getString("name"));
				restaurantpojo.setSend_price(set.getString("send_price"));
				restaurantpojo.setDescription(set.getString("description"));
				restaurantpojo.setLeaguetime(set.getString("leaguetime"));
				restaurantpojo.setArrivetime(set.getString("arrivetime"));
				restaurantpojo.setShophour(set.getString("shophour"));
				restaurantpojo.setAddress(set.getString("address"));
				restaurantpojo.setPicture_url(set.getString("picture_url"));
				restaurantpojo.setFlag(set.getString("flag"));
				restaurantpojo.setX_coordinate(set.getString("x_coordinate"));
				restaurantpojo.setY_coordinate(set.getString("y_coordinate"));
				restaurantpojo.setDiliveryfee(set.getString("diliveryfee"));
				restaurantList.add(restaurantpojo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return restaurantList;
	}

	/**
	 * 获取热门餐厅数据 根据区域ID.
	 * 
	 * @param areaid
	 * @return 餐厅数据对象集合.
	 */
	public List getHotRestaurantsInfo(String areaid) {
		logger.debug("获取热门餐厅数据 根据区域ID=" + areaid);

		List restaurantList = new ArrayList();
		String sql = "SELECT DISTINCT ID,NAME,SEND_PRICE,DESCRIPTION,LEAGUETIME,ARRIVETIME,SHOPHOUR,ADDRESS,PICTURE_URL,FLAG FROM RESTAURANT R,"
				+ "RESTAURANT_DELIVERY_RELATION D WHERE "
				+ "R.ID=D.RESTAURANT_ID AND SCOPE_ID IN ("
				+ areaid
				+ ") AND ISHOT=1 LIMIT 0,12";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);

			set = pres.executeQuery();
			while (set.next()) {
				RestaurantPojo restaurantpojo = new RestaurantPojo();
				restaurantpojo.setId(set.getString("id"));
				restaurantpojo.setName(set.getString("name"));
				restaurantpojo.setSend_price(set.getString("send_price"));
				restaurantpojo.setDescription(set.getString("description"));
				restaurantpojo.setLeaguetime(set.getString("leaguetime"));
				restaurantpojo.setArrivetime(set.getString("arrivetime"));
				restaurantpojo.setShophour(set.getString("shophour"));
				restaurantpojo.setAddress(set.getString("address"));
				restaurantpojo.setPicture_url(set.getString("picture_url"));
				restaurantpojo.setFlag(set.getString("flag"));

				restaurantList.add(restaurantpojo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return restaurantList;
	}

	/**
	 * 获取某区域所有餐厅ID
	 * 
	 * @param areaid
	 *            区域ID.
	 * @return 餐厅ID集合.
	 */
	public List getRestaurantIds(String areaid) {
		logger.debug("获取区域餐厅ID 根据区域ID=" + areaid);

		List restaurantIdList = new ArrayList();
		String sql = "SELECT * FROM RESTAURANT R,"
				+ "RESTAURANT_DELIVERY_RELATION D WHERE "
				+ "R.ID=D.RESTAURANT_ID AND D.AVAILABILITYFLAG=1 AND SCOPE_ID IN ("
				+ areaid + ")";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			set = pres.executeQuery();
			while (set.next()) {
				restaurantIdList.add(set.getString("id"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return restaurantIdList;
	}

	/**
	 * 获取推荐食物根据餐厅ID
	 * 
	 * @param restaurantidList
	 *            多个餐厅ID计划
	 * @param requestnum
	 *            请求页面
	 * @return 推荐食物集合
	 */
	public List getrecommendFood(List restaurantidList, int startindex,
			int numperpage) {
		logger.debug("获取推荐食物根据餐厅ID");
		List recommendFoodList = new ArrayList();
		if (restaurantidList.size() > 0) {
			StringBuffer paramsStr = new StringBuffer();
			for (int i = 0; i < restaurantidList.size(); i++) {
				paramsStr.append(restaurantidList.get(i) + ",");
			}
			paramsStr.deleteCharAt(paramsStr.length() - 1);
			String sql = "SELECT F.*,R.NAME RESTAURANTNAME, R.SHOPHOUR FROM FOOD F, RESTAURANT R WHERE F.RESTAURANT_ID=R.ID AND RESTAURANT_ID IN ("
					+ paramsStr.toString()
					+ ") AND IS_RECOMMEND=1  ORDER BY FOODID LIMIT ?,?";
			Connection con = getConnection();
			PreparedStatement pres = null;
			ResultSet set = null;
			try {
				pres = con.prepareStatement(sql);
				pres.setInt(1, startindex);
				pres.setInt(2, numperpage);
				set = pres.executeQuery();

				while (set.next()) {
					FoodPojo foodpojo = new FoodPojo();
					foodpojo.setFoodid(set.getString("FOODID"));
					foodpojo.setFoodname(set.getString("FOODNAME"));
					foodpojo.setPrice(set.getString("PRICE"));
					foodpojo.setDescription(set.getString("DESCRIPTION"));
					foodpojo.setPicture_url(set.getString("PICTURE_URL"));
					foodpojo.setFlag(set.getString("FLAG"));
					foodpojo.setRestaurant_id(set.getString("RESTAURANT_ID"));
					foodpojo.setIs_recommend(set.getString("IS_RECOMMEND"));
					foodpojo.setRestaurantname(set.getString("RESTAURANTNAME"));
					foodpojo.setFood_isclosed(set.getString("SHOPHOUR"));
					recommendFoodList.add(foodpojo);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			} finally {// 关闭资源.
				try {
					closeJDBCResource(set);
					closeJDBCResource(pres);
					closeJDBCResource(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		}
		return recommendFoodList;
	}

	/**
	 * 获取某区域推荐外卖的总个数
	 * 
	 * @param restaurantidList
	 * @return
	 */
	public int getrecommendFoodTotalRecordNum(List restaurantidList) {
		logger.debug("获取推荐食物根据餐厅ID");
		List recommendFoodList = new ArrayList();
		int totalrecordNum = 0;
		if (restaurantidList.size() > 0) {
			StringBuffer paramsStr = new StringBuffer();
			for (int i = 0; i < restaurantidList.size(); i++) {
				paramsStr.append(restaurantidList.get(i) + ",");
			}
			paramsStr.deleteCharAt(paramsStr.length() - 1);
			String sql = "SELECT COUNT(*) NUM FROM  FOOD F WHERE "
					+ "RESTAURANT_ID IN (" + paramsStr.toString()
					+ ") AND IS_RECOMMEND=1";
			Connection con = getConnection();
			PreparedStatement pres = null;
			ResultSet set = null;
			try {
				pres = con.prepareStatement(sql);
				set = pres.executeQuery();
				set.next();
				totalrecordNum = set.getInt("NUM");

			} catch (Exception e) {
				logger.error(e.getMessage());
			} finally {// 关闭资源.
				try {
					closeJDBCResource(set);
					closeJDBCResource(pres);
					closeJDBCResource(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());

				}
			}

		}
		return totalrecordNum;
	}

	/**
	 * 获取食物集合
	 * 
	 * @param restaurantid
	 *            餐馆id
	 * @return 推荐食物集合
	 */
	public List getfood(int restaurantid) {
		logger.debug("获取食物集合根据餐厅ID");
		List foodList = new ArrayList();
		String sql = "SELECT C.NAME FOODCATEGORY_NAME, C.DESCRIPTION CATEGORYDESCRIPTION, F.* "
				+ "FROM FOOD F, FOODCATEGORY C WHERE "
				+ "F.FOODCATEGORY_ID=C.ID AND F.RESTAURANT_ID=? "
				+ "AND F.FLAG=1 ORDER BY RANK,FOODCATEGORY_ID,FOODID";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, restaurantid);
			set = pres.executeQuery();
			while (set.next()) {
				FoodPojo foodpojo = new FoodPojo();
				foodpojo.setFoodid(set.getString("foodid"));
				foodpojo.setFoodname(set.getString("foodname"));
				foodpojo.setPrice(set.getString("price"));
				foodpojo.setDescription(set.getString("description"));
				foodpojo.setPicture_url(set.getString("picture_url"));
				foodpojo.setFlag(set.getString("flag"));
				foodpojo.setRestaurant_id(set.getString("restaurant_id"));
				foodpojo.setIs_recommend(set.getString("is_recommend"));
				foodpojo.setFoodcategory_name(set
						.getString("foodcategory_name"));
				foodpojo.setFoodcategory_id(set.getString("foodcategory_id"));
				foodpojo.setFoodcategory_description(set
						.getString("CATEGORYDESCRIPTION"));
				foodList.add(foodpojo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}

		return foodList;
	}

	/**
	 * 获取餐馆信息
	 * 
	 * @param restaurantid
	 * @return 餐馆对象
	 */
	public RestaurantPojo getRestaurantInfo(int restaurantid) {
		logger.debug("获取餐厅信息根据餐馆ID");
		RestaurantPojo restaurantPojo = new RestaurantPojo();
		String sql = "SELECT * FROM RESTAURANT WHERE ID=?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, restaurantid);
			set = pres.executeQuery();
			if (set.next()) {
				restaurantPojo.setId(set.getString("id"));
				restaurantPojo.setName(set.getString("name"));
				restaurantPojo.setSend_price(set.getString("send_price"));
				restaurantPojo.setDescription(set.getString("description"));
				restaurantPojo.setLeaguetime(set.getString("leaguetime"));
				restaurantPojo.setArrivetime(set.getString("arrivetime"));
				restaurantPojo.setShophour(set.getString("shophour"));
				restaurantPojo.setAddress(set.getString("address"));
				restaurantPojo.setPicture_url(set.getString("picture_url"));
				restaurantPojo.setFlag(set.getString("flag"));
				restaurantPojo.setX_coordinate(set.getString("x_coordinate"));
				restaurantPojo.setY_coordinate(set.getString("y_coordinate"));
				restaurantPojo.setDiliveryfee(set.getString("diliveryfee"));
				restaurantPojo
						.setIscooperation(set.getString("iscoorperation"));
				restaurantPojo.setPhone(set.getString("phone"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}

		return restaurantPojo;
	}

	// -------------below is new ecmeal service
	/**
	 * 获取餐馆信息
	 * 
	 * @param restaurantid
	 * @return 餐馆对象
	 */
	public RestaurantPojo getRestaurantInfoFormanagement(int restaurantid) {
		logger.debug("获取餐厅信息根据餐馆ID");
		RestaurantPojo restaurantPojo = new RestaurantPojo();
		String sql = "SELECT * FROM RESTAURANT WHERE ID=?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, restaurantid);
			set = pres.executeQuery();
			if (set.next()) {
				restaurantPojo.setId(set.getString("id"));
				restaurantPojo.setName(set.getString("name"));
				restaurantPojo.setSend_price(set.getString("send_price"));
				restaurantPojo.setDescription(set.getString("description"));
				restaurantPojo.setLeaguetime(set.getString("leaguetime"));
				restaurantPojo.setArrivetime(set.getString("arrivetime"));
				restaurantPojo.setShophour(set.getString("shophour"));
				restaurantPojo.setAddress(set.getString("address"));
				restaurantPojo.setPicture_url(set.getString("picture_url"));
				restaurantPojo.setFlag(set.getString("flag"));
				restaurantPojo.setX_coordinate(set.getString("x_coordinate"));
				restaurantPojo.setY_coordinate(set.getString("y_coordinate"));
				restaurantPojo.setDiliveryfee(set.getString("diliveryfee"));
				restaurantPojo
						.setIscooperation(set.getString("iscoorperation"));
				restaurantPojo.setPhone(set.getString("phone"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}

		return restaurantPojo;
	}
	/**
	 * 获取餐厅数据 根据区域ID.
	 * 
	 * @param areaid
	 * @return 餐厅数据对象集合.
	 */
	public List<String> getRestaurantsInfo(String areaid) {
		logger.debug("获取所有餐厅ID 根据区域ID=" + areaid);
		List<String> restaurantIdList = new ArrayList();
		String sql = "SELECT ID FROM RESTAURANT R,"
				+ "RESTAURANT_DELIVERY_RELATION D WHERE "
				+ "R.ID=D.RESTAURANT_ID AND D.AVAILABILITYFLAG=1 AND R.FLAG=1 AND SCOPE_ID IN("
				+ areaid + ") ORDER BY R.ORDERINDEX DESC";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			set = pres.executeQuery();
			while (set.next()) {
				restaurantIdList.add(set.getString("id"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				if (set != null)
					closeJDBCResource(set);
				if (pres != null)
					closeJDBCResource(pres);
				if (con != null)
					closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return restaurantIdList;
	}

	/**
	 * 获取食物集合根据餐厅ID
	 * 
	 * @param restaurantids
	 *            餐馆id r1,r2,r3,r4
	 * @return 推荐食物集合
	 */
	public List<FoodPojo> getfood(String restaurantids) {
		logger.debug("获取所有指定餐厅食物对象");
		List<FoodPojo> foodList = new ArrayList();
		String sql = "SELECT C.NAME FOODCATEGORY_NAME, C.DESCRIPTION CATEGORYDESCRIPTION,  " +
				"F.FOODID, F.FOODNAME, F.PRICE, F.DESCRIPTION, F.IS_RECOMMEND, F.PICTURE_URL," +
				" F.FLAG, F.RESTAURANT_ID, F.FOODCATEGORY_ID, F.RESTAURANT_ID"
				+ " FROM FOOD F, FOODCATEGORY C WHERE "
				+ "F.FOODCATEGORY_ID=C.ID AND F.RESTAURANT_ID IN ("+restaurantids+") "
				+ "AND F.FLAG=1 AND C.FLAG=1 ORDER BY RANK,FOODCATEGORY_ID,FOODID";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			set = pres.executeQuery();
			int num=0;
			while (set.next()) {
				FoodPojo foodpojo = new FoodPojo();
				foodpojo.setFoodid(set.getString("FOODID"));
				foodpojo.setFoodname(set.getString("FOODNAME"));
				foodpojo.setPrice(set.getString("PRICE"));
				foodpojo.setDescription(set.getString("DESCRIPTION"));
				foodpojo.setPicture_url(set.getString("PICTURE_URL"));
				foodpojo.setFlag(set.getString("FLAG"));
				foodpojo.setRestaurant_id(set.getString("RESTAURANT_ID"));
				foodpojo.setIs_recommend(set.getString("IS_RECOMMEND"));
				foodpojo.setFoodcategory_id(set.getString("FOODCATEGORY_ID"));
				foodpojo.setFoodcategory_name(set
						.getString("FOODCATEGORY_NAME"));
				foodpojo.setFoodcategory_id(set.getString("FOODCATEGORY_ID"));
				foodpojo.setFoodcategory_description(set
						.getString("CATEGORYDESCRIPTION"));
				foodpojo.setRestaurant_id(set
						.getString("RESTAURANT_ID"));
				foodList.add(foodpojo);		
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}

		return foodList;
	}
}
