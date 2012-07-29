package hk.franks.newsletter.dao.backmanagement;

import hk.franks.newsletter.dao.DaoSupport;
import hk.franks.newsletter.pojo.DeliverScopePojo;
import hk.franks.newsletter.pojo.FoodCategoryPojo;
import hk.franks.newsletter.pojo.FoodPojo;
import hk.franks.newsletter.pojo.RestaurantDeliverScopePojo;
import hk.franks.newsletter.pojo.RestaurantPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * 
 * @author 胡圣朗
 * 
 */
public class ManagementDao extends DaoSupport {
	private static Logger logger = Logger.getLogger(ManagementDao.class
			.getName()); // 日志对象;

	/**
	 * 获取派送位置范围列表
	 */
	public List getdeliverscope() {
		logger.debug("获取派送范围列表");
		List resultList = new ArrayList();
		Connection con = getConnection();
		String sql = "SELECT * FROM DELIVERY_SCOPE";
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			set = pres.executeQuery();
			while (set.next()) {
				DeliverScopePojo deliverscopepojo = new DeliverScopePojo();
				deliverscopepojo.setId(set.getString("id"));
				deliverscopepojo.setDescription(set.getString("description"));
				resultList.add(deliverscopepojo);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				set.close();
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return resultList;
	}

	/**
	 * 获取派送位置范围列表
	 */
	public List getRestaurantdeliverscope(String resid) {
		logger.debug("获取派送范围列表");
		List resultList = new ArrayList();
		Connection con = getConnection();
		String sql = "SELECT D.*,S.DESCRIPTION, S.NOTE FROM RESTAURANT_DELIVERY_RELATION D, DELIVERY_SCOPE S  "
				+ "WHERE D.RESTAURANT_ID=? AND D.SCOPE_ID=S.ID  ORDER BY D.SCOPE_ID";
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, resid);
			set = pres.executeQuery();
			while (set.next()) {
				RestaurantDeliverScopePojo deliverscopepojo = new RestaurantDeliverScopePojo();
				deliverscopepojo.setId(set.getString("scope_id"));
				deliverscopepojo.setDescription(set.getString("description"));
				deliverscopepojo.setNote(set.getString("note"));
				deliverscopepojo.setAvailableflag(set
						.getString("availabilityflag"));
				resultList.add(deliverscopepojo);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				set.close();
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return resultList;
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
	 * @return 餐廳ID
	 */
	public int saveRestaurant(String name, String send_price,
			String description, String leaguetime, String arrivetime,
			String shophour, String address, String x_coor, String y_coor,
			String phone, String email, String fax) {

		logger.debug("保存餐館信息");
		Connection con = getConnection();
		String sql = "INSERT INTO RESTAURANT(ID,NAME, SEND_PRICE,DESCRIPTION,LEAGUETIME,ARRIVETIME,"
				+ "SHOPHOUR,ADDRESS,PICTURE_URL,FLAG,ISHOT,X_COORDINATE,Y_COORDINATE, PHONE,EMAIL, DILIVERYFEE, FAX, ISCOORPERATION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pres = null;
		int restaurantid = -1;
		try {
			pres = con.prepareStatement(sql);
			restaurantid = getKey("restaurant");
			pres.setInt(1, restaurantid);
			pres.setString(2, name);
			pres.setString(3, send_price);
			pres.setString(4, description);
			pres.setString(5, leaguetime);
			pres.setString(6, arrivetime);
			pres.setString(7, shophour);
			pres.setString(8, address);
			pres.setString(9, "/canteen/picture/restaurant/" + restaurantid
					+ ".JPG");
			pres.setInt(10, 1);
			pres.setInt(11, 0);
			pres.setString(12, x_coor);
			pres.setString(13, y_coor);
			pres.setString(14, phone);
			pres.setString(15, email);
			pres.setString(16, "0");// 外卖费用默认0
			pres.setString(17, fax);// 传值
			pres.setInt(18, 0);// 是否加盟默认无
			pres.execute();
			logger.debug("插入账户表信息成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			restaurantid = -1;
		} finally {// 关闭资源.
			try {
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return restaurantid;
	}

	/**
	 * 批量保存保存餐廳配送範圍.
	 * 
	 * @param restaurantid
	 * @param scopeidList
	 * @return
	 */
	public boolean saveRestaurantDeliverScope(int restaurantid, List scopeidList) {
		logger.debug("批量保存保存餐廳配送範圍.");
		boolean flag = false;
		for (int i = 0; i < scopeidList.size(); i++) {
			String scopeidstr = ((String) scopeidList.get(i)).trim();
			flag = saveRestaurantDeliverScope(restaurantid, new Integer(
					scopeidstr).intValue());

		}
		return flag;
	}

	/**
	 * 單個保存餐廳配送範圍.
	 * 
	 * @param restaurantid
	 * @param scopeid
	 * @return
	 */
	private boolean saveRestaurantDeliverScope(int restaurantid, int scopeid) {
		logger.debug("保存餐館配送範圍信息");
		Connection con = getConnection();
		String sql = "INSERT INTO RESTAURANT_DELIVERY_RELATION(SCOPE_ID,RESTAURANT_ID,AVAILABILITYFLAG) VALUES (?,?,?)";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, scopeid);
			pres.setInt(2, restaurantid);
			pres.setInt(3, 1); // 默认是1 启用.
			pres.execute();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 获取所有餐馆信息
	 * 
	 * @return
	 */
	public List getrestaurant() {
		logger.debug("获取所有餐馆信息");
		List resultList = new ArrayList();
		Connection con = getConnection();
		String sql = "SELECT * FROM RESTAURANT";
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			set = pres.executeQuery();
			while (set.next()) {
				RestaurantPojo restaurantpojo = new RestaurantPojo();
				restaurantpojo.setId(set.getString("id"));
				restaurantpojo.setName(set.getString("name"));
				restaurantpojo.setSend_price(set.getString("send_price"));
				restaurantpojo.setArrivetime(set.getString("arrivetime"));
				restaurantpojo.setShophour(set.getString("shophour"));
				restaurantpojo.setDescription(set.getString("description"));
				restaurantpojo.setDiliveryfee(set.getString("diliveryfee"));
				restaurantpojo.setIshot(set.getString("ishot"));
				restaurantpojo.setFlag(set.getString("flag"));
				restaurantpojo
						.setIscooperation(set.getString("iscoorperation"));
				restaurantpojo.setRank(set.getString("orderindex"));
				resultList.add(restaurantpojo);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return resultList;
	}

	/**
	 * 保存食物类别
	 * 
	 * @param restaurantid
	 * @param foodcategoryname
	 *            食物类别名称
	 * @return 是否保存成功
	 */
	public boolean savefoodcategory(int restaurantid, String foodcategoryname,
			String fcdescription) {
		logger.debug("保存保存食物类别");
		Connection con = getConnection();
		String sql = "INSERT INTO FOODCATEGORY(ID,NAME,RESTAURANT_ID,DESCRIPTION, RANK) VALUES (?,?,?,?,?)";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			int id = this.getKey("foodcategory");
			pres.setInt(1, id);
			pres.setString(2, foodcategoryname);
			pres.setInt(3, restaurantid);
			pres.setString(4, fcdescription);
			pres.setString(5, "1");
			pres.execute();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 获取餐馆食物类别
	 * 
	 * @param restaurantid
	 *            餐馆id
	 */
	public List getfoodcategory(int restaurantid) {
		logger.debug("获取餐馆食物类别");
		List resultList = new ArrayList();
		Connection con = getConnection();
		String sql = "SELECT * FROM FOODCATEGORY WHERE RESTAURANT_ID=?";
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, restaurantid);
			set = pres.executeQuery();
			while (set.next()) {
				FoodCategoryPojo foodcategorypojo = new FoodCategoryPojo();
				foodcategorypojo.setId(set.getString("id"));
				foodcategorypojo.setName(set.getString("name"));
				foodcategorypojo
						.setRestaurantid(set.getString("restaurant_id"));
				foodcategorypojo.setDescription(set.getString("description"));
				foodcategorypojo.setRank(set.getString("rank"));
				foodcategorypojo.setFlag(set.getString("flag"));
				resultList.add(foodcategorypojo);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return resultList;
	}

	/**
	 * 保存配送范围表
	 * 
	 * @return
	 */
	public boolean savediliverScope(String description,
			String specificdescription, String[] point1_coordinate,
			String[] point2_coordinate, String[] point3_coordinate,
			String[] point4_coordinate, String x_max_coordinate,
			String x_min_coordinate, String y_max_coordinate,
			String y_min_coordinate) {

		logger.debug("保存配送范围表");
		Connection con = getConnection();
		String sql = "INSERT INTO DELIVERY_SCOPE(ID, DESCRIPTION, NOTE, COORDINATE1_X, COORDINATE1_Y,"
				+ "COORDINATE2_X, COORDINATE2_Y,COORDINATE3_X, COORDINATE3_Y,COORDINATE4_X, COORDINATE4_Y,"
				+ "COORDINATE_MAX_X, COORDINATE_MIN_X, COORDINATE_MAX_Y, COORDINATE_MIN_Y, AVAILABILITYFLAG) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			int id = this.getKey("delivery_scope");
			pres.setInt(1, id);
			pres.setString(2, description);
			pres.setString(3, specificdescription);
			pres.setString(4, point1_coordinate[0]);
			pres.setString(5, point1_coordinate[1]);
			pres.setString(6, point2_coordinate[0]);
			pres.setString(7, point2_coordinate[1]);
			pres.setString(8, point3_coordinate[0]);
			pres.setString(9, point3_coordinate[1]);
			pres.setString(10, point4_coordinate[0]);
			pres.setString(11, point4_coordinate[1]);
			pres.setString(12, x_max_coordinate);
			pres.setString(13, x_min_coordinate);
			pres.setString(14, y_max_coordinate);
			pres.setString(15, y_min_coordinate);
			pres.setInt(16, 1);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 保存食物外卖信息
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
		logger.debug("保存食物外卖信息");
		Connection con = getConnection();
		String sql = "INSERT INTO FOOD(FOODID, FOODNAME, PRICE, DESCRIPTION, "
				+ "FLAG, PICTURE_URL, IS_RECOMMEND, RESTAURANT_ID, FOODCATEGORY_ID) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			int foodid = this.getKey("food");
			pres.setInt(1, foodid);
			pres.setString(2, foodname);
			pres.setString(3, price);
			pres.setString(4, description);
			pres.setString(5, "1");// flag;1-展示，0-不展示;默认展示1
			pres.setString(6, "/canteen/picture/food/" + foodid + ".JPG");// 前缀+图片名.
			pres.setString(7, is_recommend);
			pres.setString(8, restaurantid);
			pres.setString(9, foodcategory_id);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 修改餐厅信息
	 * 
	 * @param resid
	 * @param sendprice
	 * @param arrivetime
	 * @param diliveryfee
	 * @param shophour
	 * @param ishot
	 * @param flag
	 * @return
	 */
	public boolean updateRestaurantinfo(String resid, String sendprice,
			String arrivetime, String diliveryfee, String shophour,
			String ishot, String availableflag, String description,
			String iscoorperation, String rank) {
		logger.debug("修改餐厅基本信息");
		Connection con = getConnection();
		String sql = "UPDATE RESTAURANT SET SEND_PRICE=? , "
				+ "ARRIVETIME=? , DILIVERYFEE=? , SHOPHOUR=? , "
				+ "ISHOT=? , FLAG=?, DESCRIPTION=?, ISCOORPERATION=?, ORDERINDEX=? WHERE ID=?";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, sendprice);
			pres.setString(2, arrivetime);
			pres.setString(3, diliveryfee);
			pres.setString(4, shophour);
			pres.setString(5, ishot);
			pres.setString(6, availableflag);
			pres.setString(7, description);
			pres.setString(8, iscoorperation);
			pres.setString(9, rank);
			pres.setString(10, resid);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return flag;
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
		String sql = "SELECT C.NAME FOODCATEGORY_NAME, F.* "
				+ "FROM FOOD F, FOODCATEGORY C WHERE "
				+ "F.FOODCATEGORY_ID=C.ID AND F.RESTAURANT_ID=? "
				+ " ORDER BY FOODID DESC";
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
				foodList.add(foodpojo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				set.close();
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}

		return foodList;
	}

	/**
	 * 修改食物信息
	 * 
	 * @param foodid
	 * @param foodprice
	 * @param is_recommend
	 * @param availableflag
	 * @param foodcategory_id
	 * @return
	 */
	public boolean updatefoodInfo(String foodid, String foodprice,
			String is_recommend, String availableflag, String foodcategory_id,
			String foodname) {
		logger.debug("修改食物信息 foodid=" + foodid);
		Connection con = getConnection();
		String sql = "UPDATE FOOD SET PRICE=? , "
				+ "IS_RECOMMEND=? , FLAG=? , FOODCATEGORY_ID=?, FOODNAME=? WHERE FOODID=?";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, foodprice);
			pres.setString(2, is_recommend);
			pres.setString(3, availableflag);
			pres.setString(4, foodcategory_id);
			pres.setString(5, foodname);
			pres.setString(6, foodid);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 更新餐厅派送范围可用标志.
	 * 
	 * @param restaurantid
	 * @param scopeid
	 * @param availflag
	 * @return
	 */
	public boolean updateResDeliScopeAvail(String restaurantid, String scopeid,
			String availflag) {
		logger.debug("更新餐厅派送范围可用标志.");
		Connection con = getConnection();
		String sql = "UPDATE RESTAURANT_DELIVERY_RELATION SET AVAILABILITYFLAG=? WHERE SCOPE_ID=? AND RESTAURANT_ID=?";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, availflag);
			pres.setString(2, scopeid);
			pres.setString(3, restaurantid);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 刪除餐厅派送范围.
	 * 
	 * @param restaurantid
	 * @param scopeid
	 * @param availflag
	 * @return
	 */
	public boolean deleteDeliveryScope(String restaurantid, String scopeid) {
		logger.debug("更新餐厅派送范围可用标志.");
		Connection con = getConnection();
		String sql = "DELETE FROM RESTAURANT_DELIVERY_RELATION WHERE SCOPE_ID=? AND RESTAURANT_ID=?";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, scopeid);
			pres.setString(2, restaurantid);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 检查管理员账户
	 * 
	 * @param admaccountid
	 * @param password
	 * @return
	 */
	public boolean getAdmAccountNum(String admaccountid, String password) {
		logger.debug("检查管理员账户 admaccountid=" + admaccountid);
		boolean resultflag = false;
		List foodList = new ArrayList();
		String sql = "SELECT COUNT(*) TOTALNUM FROM ADMACCOUNT WHERE ID=? AND PASSWORD=?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		int recordnum = 0;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, admaccountid);
			pres.setString(2, password);
			set = pres.executeQuery();
			if (set.next()) {
				recordnum = set.getInt("TOTALNUM");
			}
			if (recordnum > 0) {
				resultflag = true;
			} else {
				resultflag = false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				set.close();
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}

		return resultflag;
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
	public boolean updatefoodCategoryInfo(String categoryid,
			String categoryname, String description, String rank, String categoryflag) {
		logger.debug("修改食物信息 categoryid=" + categoryid);
		Connection con = getConnection();
		String sql = "UPDATE FOODCATEGORY SET NAME=?, DESCRIPTION=?, RANK=?, FLAG =? WHERE ID=?";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, categoryname);
			pres.setString(2, description);
			pres.setString(3, rank);
			pres.setString(4, categoryflag);
			pres.setString(5, categoryid);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 删除食物类别同时删除食物.
	 * 
	 * @param categoryid
	 * @return
	 */
	public boolean deleteFoodcategory(String categoryid) {
		logger.debug("修改食物信息 categoryid=" + categoryid);
		Connection con = getConnection();
		String deleteCategorysql = "DELETE FROM FOODCATEGORY WHERE ID=?";
		String deleteFoodsql = "DELETE FROM FOOD WHERE FOODCATEGORY_ID=?";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			con.setAutoCommit(false);
			pres = con.prepareStatement(deleteCategorysql);
			pres.setString(1, categoryid);
			pres.execute();
			pres.close();
			pres = con.prepareStatement(deleteFoodsql);
			pres.setString(1, categoryid);
			pres.execute();
			con.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				con.setAutoCommit(true);
				pres.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return flag;
	}
}
