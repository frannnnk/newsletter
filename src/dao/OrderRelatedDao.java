package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import pojo.AddressInfoPojo;
import pojo.OrderDetailPojo;
import pojo.OrderFormDetailPojo;
import pojo.OrderPojo;
import pojo.RestaurantPojo;

/**
 * 订单相关DAO
 * 
 * @author 胡圣朗
 * 
 */
public class OrderRelatedDao extends DaoSupport {

	private static Logger logger = Logger.getLogger(OrderRelatedDao.class
			.getName()); // 日志对象;

	/**
	 * 获取用户地址信息
	 * 
	 * @param userid
	 *            用户ID
	 * @return 用户地址信息对象集合
	 */
	public List getaddressinfo(String userid) {
		logger.debug("获取用户地址信息 userid=" + userid);
		List addressinfoList = new ArrayList();
		Connection con = getConnection();
		String sql = "SELECT * FROM ADDRESSINFO WHERE USERID=?";
		PreparedStatement pres = null;
		ResultSet set = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			set = pres.executeQuery();
			while (set.next()) {
				AddressInfoPojo addresspojo = new AddressInfoPojo();
				addresspojo.setUserid(set.getString("userid"));
				addresspojo.setAddressinfoid(set.getString("addressinfoid"));
				addresspojo.setAddressText(set.getString("address"));
				addresspojo.setIsdefault(set.getString("isdefault"));
				addressinfoList.add(addresspojo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
		return addressinfoList;
	}

	/**
	 * 保存到订单(保存到2张表，订单基本信息表和订单明细表)
	 * 
	 * @param createtime
	 *            订单创建时间；服务器当前时间
	 * @param sum
	 *            订单总额
	 * @param address
	 *            送货地址
	 * @param comments
	 *            备注
	 * @param paymentmethod
	 *            支付方式 1-线下支付;2-账户余额支付；3-网上支付;
	 * @param accountid
	 *            用户ID
	 * @param dilivertime
	 *            预计送抵时间
	 * @param fooddeailList
	 *            订单食物清单
	 * 
	 * @return 是否保存成功
	 */
	private OrderPojo saveorder(String restaurantid, String createtime,
			String sum, String address, String comments, String paymentmethod,
			String accountid, String dilivertime, List fooddeailList,
			String locationstr, Connection con) {
		boolean flag = true;
		OrderPojo orderPojo = new OrderPojo(); // 订单对象.
		try {
			int orderid = this.saveorderbasicinfo(restaurantid, createtime,
					sum, address, comments, paymentmethod, accountid,
					dilivertime, locationstr, con);
			orderPojo.setOrderid(new Integer(orderid).toString());
			orderPojo.setAddress(address);
			orderPojo.setSummoney(sum);
			orderPojo.setPaymethod(paymentmethod);

			this.saveorderfooddetailforbatch(fooddeailList,
					new Integer(orderid).toString(), con);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			flag = false;
			orderPojo = null;
		}
		return orderPojo;
	}

	/**
	 * 保存所有订单 因为可能存在分单 所以这么处理
	 * 
	 * @param restaurantids
	 *            一个餐馆一张订单， 这个是餐馆ID数组。
	 * @param createtime
	 * @param pricesums
	 *            每一张订单小计金额 数组。
	 * @param address
	 * @param comments
	 * @param paymentmethod
	 * @param accountid
	 * @param dilivertime
	 * @param allFoodObjList
	 * @param locationstr
	 *            用于在没有指定位置的情况下 从我的收藏下面可以进入餐厅 并且给于上次位置记录. 所有餐馆的
	 *            食物对象集合，List里面保存的是 foodobjList.
	 */
	public List saveAllOrder(String[] restaurantids, String createtime,
			String[] pricesums, String address, String comment,
			String paymentmethod, String accountid, String deliveryTime,
			List allFoodObjList, String locationstr) {
		logger.debug("保存订单包含拆单操作如果有 accountid=" + accountid);
		List orderpojoList = new ArrayList();
		OrderPojo orderPojo = null;
		Connection con = getConnection();
		// 以下操作为一个事务。
		try {
			con.setAutoCommit(false);
			int size = restaurantids.length;
			for (int i = 0; i < size; i++) {
				orderPojo = this.saveorder(restaurantids[i], createtime,
						pricesums[i], address, comment, paymentmethod,
						accountid, deliveryTime, (List) allFoodObjList.get(i),
						locationstr, con);
				if (orderPojo == null) {
					break;
				}
				orderpojoList.add(orderPojo);
			}
			if (orderPojo != null) {
				con.commit();
			} else {
				orderpojoList = null;
			}
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				logger.error(e1.getMessage());
			}
			logger.error(e.getMessage());
		} finally {
			try {
				con.setAutoCommit(true);
				con.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}

		return orderpojoList;
	}

	/**
	 * 保存到订单基本信息
	 * 
	 * @param createtime
	 *            订单创建时间；服务器当前时间
	 * @param sum
	 *            订单总额
	 * @param address
	 *            送货地址
	 * @param comments
	 *            备注
	 * @param paymentmethod
	 *            支付方式 1-线下支付;2-账户余额支付；3-网上支付;
	 * @param accountid
	 *            用户ID
	 * @param dilivertime
	 *            预计送抵时间
	 * @return 该订单ID.
	 */
	private int saveorderbasicinfo(String restaurantid, String createtime,
			String sum, String address, String comments, String paymentmethod,
			String accountid, String dilivertime, String locationstr,
			Connection con) throws SQLException {
		logger.debug("保存到订单基本信息 ");
		int orderID = getKey("ORDERFORM");
		String sql = "INSERT INTO ORDERFORM(DILIVERTIME,ACCOUNTID,ORDERSTATE,PAYMENTSTATE,"
				+ "PAYMENTMETHOD, COMMENTS, ADDRESS, SUM, CREATETIME, ORDERID, RESTAURANTID, LOCATIONSTR) VALUE(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pres = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, dilivertime);
			pres.setString(2, accountid);
			pres.setString(3, "1");// 订单状态1- 待确定；2-已确定未完成；3-成功送達；4-送達失敗；5-作廢
			pres.setString(4, "1");// 支付状态1-未支付；2-已支付
			pres.setString(5, paymentmethod);// 支付方式 1-线下支付;2-账户余额支付；3-网上支付;
			pres.setString(6, comments);
			pres.setString(7, address);
			pres.setString(8, sum);
			pres.setString(9, createtime);
			pres.setInt(10, orderID);
			pres.setString(11, restaurantid);
			pres.setString(12, locationstr);
			pres.execute();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw e;
		} finally {// 关闭资源.
			try {
				pres.close();
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
		return orderID;
	}

	/**
	 * 保存到订单明细表
	 * 
	 * @param fooddeailList
	 * @param orderid
	 * @param con
	 */
	private void saveorderfooddetailforbatch(List fooddeailList,
			String orderid, Connection con) throws SQLException {
		logger.debug("保存到订单详细信息 ");
		for (int i = 0; i < fooddeailList.size(); i++) {
			OrderFormDetailPojo pojo = (OrderFormDetailPojo) fooddeailList
					.get(i);
			saveorderfooddetail(pojo.getFoodid(), pojo.getAmount(),
					pojo.getPrice(), orderid, con);
		}
	}

	/**
	 * 保存到订单明细表
	 * 
	 * @param foodid
	 * @param amount
	 * @param orderprice
	 *            订购此外卖的价格.
	 * @param orderid
	 * @param con
	 * @throws SQLException
	 */
	private void saveorderfooddetail(String foodid, String amount,
			String orderprice, String orderid, Connection con)
			throws SQLException {
		logger.debug("保存到订单明细信息表");
		int orderDetailID = getKey("ORDERFORMDETAIL");
		String sql = "INSERT INTO ORDERFORMDETAIL(ORDERID,AMOUNT,FOODID,"
				+ "ORDERFORMDETAILID, ORDERPRICE ) VALUE(?,?,?,?,?)";
		PreparedStatement pres = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, orderid);
			pres.setString(2, amount);
			pres.setString(3, foodid);
			pres.setInt(4, orderDetailID);
			pres.setString(5, orderprice);
			pres.execute();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw e;
		} finally {// 关闭资源.
			closeJDBCResource(pres);
		}
	}

	/**
	 * 保存所有订单 因为可能存在分单 所以这么处理(此方法已弃用)
	 * 
	 * @param restaurantids
	 *            一个餐馆一张订单， 这个是餐馆ID数组。
	 * @param createtime
	 * @param pricesums
	 *            每一张订单小计金额 数组。
	 * @param address
	 * @param comments
	 * @param paymentmethod
	 * @param accountid
	 * @param dilivertime
	 * @param allFoodObjList
	 * @param locationstr
	 *            用于在没有指定位置的情况下 从我的收藏下面可以进入餐厅 并且给于上次位置记录. 所有餐馆的
	 *            食物对象集合，List里面保存的是 foodobjList.
	 */
	/*
	 * public List saveAsllOrder(String[] restaurantids, String createtime,
	 * String[] pricesums, String address, List commentsList, String
	 * paymentmethod, String accountid, List deliveryTimeList, List
	 * allFoodObjList, String locationstr) {
	 * logger.debug("保存订单包含拆单操作如果有 accountid=" + accountid); List orderpojoList
	 * = new ArrayList(); OrderPojo orderPojo = null; Connection con =
	 * getConnection(); // 以下操作为一个事务。 try { con.setAutoCommit(false); int size =
	 * restaurantids.length; for (int i = 0; i < size; i++) { orderPojo =
	 * this.saveorder(restaurantids[i], createtime, pricesums[i], address,
	 * (String)commentsList.get(i), paymentmethod, accountid, (String)
	 * deliveryTimeList.get(i), (List) allFoodObjList.get(i), locationstr, con);
	 * if (orderPojo == null) { break; } orderpojoList.add(orderPojo); } if
	 * (orderPojo != null) { con.commit(); } else { orderpojoList = null; } }
	 * catch (SQLException e) { try { con.rollback(); } catch (SQLException e1)
	 * { // TODO Auto-generated catch block logger.error(e1.getMessage()); }
	 * logger.error(e.getMessage()); } finally { try { con.setAutoCommit(true);
	 * closeJDBCResource(con); } catch (SQLException e) {
	 * logger.error(e.getMessage()); } }
	 * 
	 * return orderpojoList; }
	 */

	/**
	 * 查询订单 (如果是查询当日订单，fromdate=today; todate=tommorrow; 如果是查询历史订单
	 * 即今天以前的订单，fromtedate=2011-10-1;todate=today) 日期的格式：yyyy-mm-dd
	 * 
	 * @param accountid
	 * @param fromdate
	 *            开始时间
	 * @param todate
	 *            结束时间
	 * @return
	 */
	public List searchOrder(String accountid, String fromdate, String todate,
			int startindex, int recordnum) {
		logger.debug("查询订单 accountid=" + accountid + " fromdate=" + fromdate
				+ " todate=" + todate);
		String sql = "SELECT O.* FROM ORDERFORM O  "
				+ "WHERE ACCOUNTID=? AND UNIX_TIMESTAMP(CREATETIME)> UNIX_TIMESTAMP(?) "
				+ "AND UNIX_TIMESTAMP(CREATETIME)< UNIX_TIMESTAMP(?) AND ISSHOW=1 ORDER BY ORDERID DESC  LIMIT ?,? ";
		List OrderList = new ArrayList();
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, accountid);
			pres.setString(2, fromdate);
			pres.setString(3, todate);
			pres.setInt(4, startindex);
			pres.setInt(5, recordnum);
			set = pres.executeQuery();
			while (set.next()) {
				OrderPojo orderpojo = new OrderPojo();
				orderpojo.setOrderid(set.getString("orderid"));
				orderpojo.setOrderstate(set.getString("orderstate"));
				orderpojo.setPaymethod(set.getString("paymentmethod"));
				orderpojo.setCreatetime(set.getString("createtime"));
				orderpojo.setSummoney(set.getString("sum"));
				orderpojo.setLocationstr(set.getString("locationstr"));
				OrderList.add(orderpojo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());

		} finally {// 关闭资源.
			closeJDBCResource(set);
			closeJDBCResource(pres);
			closeJDBCResource(con);
		}
		return OrderList;
	}

	/**
	 * 获取总订单数
	 * 
	 * @param accountid
	 * @param fromdate
	 * @param todate
	 * @return
	 */
	public int getMyorderTotalRecords(String accountid, String fromdate,
			String todate) {
		logger.debug("查询订单 accountid=" + accountid + " fromdate=" + fromdate
				+ " todate=" + todate);
		String sql = "SELECT COUNT(*) TOTALRECORD FROM ORDERFORM O, RESTAURANT R  "
				+ "WHERE ACCOUNTID=? AND UNIX_TIMESTAMP(CREATETIME)> UNIX_TIMESTAMP(?) "
				+ "AND UNIX_TIMESTAMP(CREATETIME)< UNIX_TIMESTAMP(?) AND O.RESTAURANTID=R.ID AND ISSHOW=1";
		int totalrecordnum = 0;
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, accountid);
			pres.setString(2, fromdate);
			pres.setString(3, todate);
			set = pres.executeQuery();
			if (set.next()) {
				totalrecordnum = set.getInt("TOTALRECORD");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());

		} finally {// 关闭资源.
			closeJDBCResource(set);
			closeJDBCResource(pres);
			closeJDBCResource(con);
		}
		return totalrecordnum;
	}

	/**
	 * 查询订单明细.
	 * 
	 * @param orderid
	 * @return
	 */
	public List searchOrderDetailByaccount(String accountid, String fromdate,
			String todate, int startindex, int recordnum) {
		logger.debug("查询订单明细. accountid=" + accountid);
		String sql = "SELECT D.*,F.FOODNAME FROM ORDERFORMDETAIL D,FOOD F "
				+ "WHERE ORDERID IN (SELECT ORDERID FROM ORDERFORM WHERE ACCOUNTID=? AND UNIX_TIMESTAMP(CREATETIME)> UNIX_TIMESTAMP(?) "
				+ "AND UNIX_TIMESTAMP(CREATETIME)< UNIX_TIMESTAMP(?) AND ISSHOW=1) AND D.FOODID=F.FOODID";
		List orderDetailList = new ArrayList();
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, accountid);
			pres.setString(2, fromdate);
			pres.setString(3, todate);
			set = pres.executeQuery();
			while (set.next()) {
				OrderDetailPojo orderdetailpojo = new OrderDetailPojo();
				orderdetailpojo.setOrderdetailid(set
						.getString("ORDERFORMDETAILID"));
				orderdetailpojo.setOrderid(set.getString("ORDERID"));
				orderdetailpojo.setFoodid(set.getString("FOODID"));
				orderdetailpojo.setFoodname(set.getString("FOODNAME"));
				orderdetailpojo.setFoodamount(set.getString("AMOUNT"));
				orderdetailpojo.setFoodorderprice(set.getString("ORDERPRICE"));
				orderDetailList.add(orderdetailpojo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());

		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
		return orderDetailList;
	}

	/**
	 * 查询订单明细.
	 * 
	 * @param orderid
	 * @return
	 */
	public List searchOrderDetail(String orderids) {
		logger.debug("查询订单明细. orderids=" + orderids);
		String sql = "SELECT D.*, F.FOODNAME, R.NAME RESNAME,R.ID RESID, R.PHONE RESPHONE"
				+ " FROM ORDERFORMDETAIL D, ORDERFORM O, FOOD F, RESTAURANT R WHERE "
				+ "D.ORDERID=O.ORDERID AND F.RESTAURANT_ID=R.ID AND F.FOODID=D.FOODID AND D.ORDERID IN ("
				+ orderids + ")";
		List orderDetailList = new ArrayList();
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			set = pres.executeQuery();
			while (set.next()) {
				OrderDetailPojo orderdetailpojo = new OrderDetailPojo();
				orderdetailpojo.setOrderdetailid(set
						.getString("ORDERFORMDETAILID"));
				orderdetailpojo.setOrderid(set.getString("ORDERID"));
				orderdetailpojo.setFoodid(set.getString("FOODID"));
				orderdetailpojo.setFoodname(set.getString("FOODNAME"));
				orderdetailpojo.setFoodamount(set.getString("AMOUNT"));
				orderdetailpojo.setFoodorderprice(set.getString("ORDERPRICE"));
				orderdetailpojo.setResid(set.getString("RESID"));
				orderdetailpojo.setResname(set.getString("RESNAME"));
				orderdetailpojo.setResphone(set.getString("RESPHONE"));
				orderDetailList.add(orderdetailpojo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());

		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
		return orderDetailList;
	}

	/**
	 * 更改订单状态.
	 * 
	 * @param orderid
	 * @param state
	 *            状态【订单状态1- 待确定；2-已 确认（准备派送）；3-派送完成；
	 *            4-派送失败（可能由于顾客地址异常）；5-作废（取消订单，但数据库中不删除）】
	 * @return
	 */
	public boolean updateOrderState(String orderid, String state) {
		logger.debug("更改订单状态. orderid=" + orderid + " state=" + state);
		String sql = "UPDATE ORDERFORM SET ORDERSTATE=? WHERE ORDERID=?";
		Connection con = this.getConnection();
		PreparedStatement pres = null;
		boolean flag = true;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, state);
			pres.setString(2, orderid);
			pres.execute();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			flag = false;
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param orderid
	 * @param state
	 *            1-显示状态；2-不显示状态；
	 * @return
	 */
	public boolean updateOrderIsShowState(String orderid, int state) {
		logger.debug("更改订单显示状态. orderid=" + orderid + " state=" + state);
		String sql = "UPDATE ORDERFORM SET ISSHOW=? WHERE ORDERID=?";
		Connection con = this.getConnection();
		PreparedStatement pres = null;
		boolean flag = true;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, state);
			pres.setString(2, orderid);
			pres.execute();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			flag = false;
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 获取当前订单状态 。
	 * 
	 * @param orderid
	 * @return 订单状态1- 待确定；2-确认（准备派送）；3-派送完成；
	 *         4-派送失败（可能由于顾客地址异常）；5-作废（取消订单，但数据库中不删除）
	 */
	public int getOrderState(String orderid) {
		logger.debug("获取当前订单状态 。 orderid=" + orderid);
		String sql = "SELECT ORDERSTATE FROM ORDERFORM WHERE ORDERID=?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		int orderstate = 0;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, orderid);
			set = pres.executeQuery();
			if (set.next())
				orderstate = set.getInt("ORDERSTATE");
		} catch (SQLException e) {
			logger.error(e.getMessage());

		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
		return orderstate;
	}

	/**
	 * 保存订单冲突
	 * 
	 * @param orderid
	 * @param conflictType
	 *            5-由取消订单导致的冲突.
	 * @param time
	 * @return
	 */
	public boolean saveorderConflictInfo(String orderid, String conflictType,
			String time) {
		logger.debug("保存订单冲突 orderid=" + orderid);
		String sql = "INSERT INTO ORDERCONFLICT(ID,TIME,ORDERID,TYPE) VALUES(?,?,?,?)";
		Connection con = this.getConnection();
		PreparedStatement pres = null;
		boolean flag = true;
		try {
			pres = con.prepareStatement(sql);
			int keynum = this.getKey("orderconflict");
			pres.setInt(1, keynum);
			pres.setString(2, time);
			pres.setString(3, orderid);
			pres.setString(4, conflictType);
			pres.execute();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			flag = false;
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 按订单状态查询订单 (用于订单管理)
	 * 
	 * @param state
	 *            订单状态1-待确定；2-确认（准备派送）；3-派送完成； 4-派送失败（可能由于顾客地址异常）；
	 *            5-作废（取消订单，但数据库中不删除）
	 * @param state
	 * @param startindex
	 * @param recordnum
	 * @return
	 */
	public List searchOrder(int state, int startindex, int recordnum) {
		logger.debug("按订单状态查询订单 状态值 =" + state);
		String sql = "SELECT O.*,A.CREDITVALUE FROM ORDERFORM O, ACCOUNT A "
				+ "WHERE ORDERSTATE=? AND A.USERID=O.ACCOUNTID ORDER BY DILIVERTIME,ORDERID LIMIT ?,?";
		List OrderList = new ArrayList();
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, state);
			pres.setInt(2, startindex);
			pres.setInt(3, recordnum);
			set = pres.executeQuery();
			while (set.next()) {
				OrderPojo orderpojo = new OrderPojo();
				orderpojo.setOrderid(set.getString("ORDERID"));
				orderpojo.setOrderstate(set.getString("ORDERSTATE"));
				orderpojo.setPaymethod(set.getString("PAYMENTMETHOD"));
				orderpojo.setCreatetime(set.getString("CREATETIME"));
				orderpojo.setSummoney(set.getString("SUM"));
				orderpojo.setAddress(set.getString("ADDRESS"));
				orderpojo.setOrderComments(set.getString("COMMENTS"));
				orderpojo.setCreditValue(set.getString("CREDITVALUE"));
				orderpojo.setDilivertime(set.getString("DILIVERTIME"));

				// 获取用户电话.
				OrderList.add(orderpojo);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());

		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
		return OrderList;
	}

	/**
	 * 获取某状态下的订单总数，用于分页.(订单管理)
	 * 
	 * @param state
	 * @return
	 */
	public int getTotalOrderRecordnum(int state) {
		logger.debug("获取某状态下的订单总数，用于分页.(订单管理)");
		String sql = "SELECT COUNT(ORDERID) TOTALRECORDNUM FROM ORDERFORM WHERE ORDERSTATE=?";
		int totalrecordnum = -1;
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, state);
			set = pres.executeQuery();
			if (set.next()) {
				totalrecordnum = set.getInt("TOTALRECORDNUM");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());

		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
		return totalrecordnum;
	}

	/**
	 * 设置用户诚信值.
	 * 
	 * @param accountid
	 * @param creditvalue
	 *            0-低;1-高
	 * @return
	 */
	public boolean setCreditValue(String accountid, int creditvalue) {
		logger.debug("设置用户诚信值.accountid=" + accountid);
		String sql = "UPDATE ACCOUNT SET CREDITVALUE=?  WHERE USERID=?";
		Connection con = this.getConnection();
		PreparedStatement pres = null;
		boolean flag = true;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, creditvalue);
			pres.setString(2, accountid);
			pres.execute();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			flag = false;
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 获取用户ID
	 * 
	 * @param orderid
	 * @return
	 */
	public String getUseridByorderid(int orderid) {
		logger.debug("获取用户ID orderid=" + orderid);
		String userid = null;
		Connection con = getConnection();
		String sql = "SELECT ACCOUNTID FROM ORDERFORM WHERE ORDERID=?";
		PreparedStatement pres = null;
		ResultSet set = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, orderid);
			set = pres.executeQuery();
			if (set.next()) {
				userid = set.getString("ACCOUNTID");
			}
		} catch (Exception e) {

			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
		return userid;
	}

	/**
	 * 查询最新的N张订单 用户订单播报.
	 * 
	 * @param recordNum
	 */
	public List searchLastestOrder(int recordNum) {
		logger.debug("查询最新的N张订单 用户订单播报. N =" + recordNum);
		String sql = "SELECT  A.NICKNAME, R.NAME ,O.*  FROM ORDERFORM O, RESTAURANT R, ACCOUNT A "
				+ "WHERE O.RESTAURANTID=R.ID AND A.USERID=O.ACCOUNTID ORDER BY ORDERID DESC LIMIT 0,?";
		List OrderList = new ArrayList();
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, recordNum);
			set = pres.executeQuery();
			while (set.next()) {
				OrderPojo orderBroadCastpojo = new OrderPojo();
				orderBroadCastpojo.setOrderid(set.getString("ORDERID"));
				orderBroadCastpojo.setCreatetime(set.getString("CREATETIME"));
				orderBroadCastpojo.setRestaurantid(set
						.getString("RESTAURANTID"));
				orderBroadCastpojo.setRestaurantname(set.getString("NAME"));
				orderBroadCastpojo.setSummoney(set.getString("SUM"));
				orderBroadCastpojo.setAddress(set.getString("ADDRESS"));
				orderBroadCastpojo.setUsernickname(set.getString("A.NICKNAME"));
				OrderList.add(orderBroadCastpojo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());

		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
		return OrderList;
	}

	/**
	 * 获取多家餐厅外卖派送时间范围
	 * 
	 * @param residstr
	 *            format: resid,resid,resid
	 * @return 0-外卖派送时间范围集合；1-送餐时间集合;2-RESID集合
	 */
	public List getDeliveryTimeScope(String residstr) {
		logger.debug("获取多家餐厅外卖派送时间范围. resids =" + residstr);
		String sql = "SELECT ID, SHOPHOUR, ARRIVETIME FROM RESTAURANT WHERE ID IN("
				+ residstr + ")";
		List resultList = new ArrayList();
		List timeScopeList = new ArrayList();
		List arrivetimeList = new ArrayList();
		List residList = new ArrayList();
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			set = pres.executeQuery();
			while (set.next()) {
				timeScopeList.add(set.getString("SHOPHOUR"));
				arrivetimeList.add(set.getString("ARRIVETIME"));
				residList.add(set.getString("ID"));
			}
			resultList.add(timeScopeList);
			resultList.add(arrivetimeList);
			resultList.add(residList);
		} catch (SQLException e) {
			logger.error(e.getMessage());

		} finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
		return resultList;
	}

	/**
	 * 获取餐馆信息
	 * 
	 * @param restaurantid
	 * @return 餐馆对象
	 */
	public List getRestaurants(String restaurantids) {
		logger.debug("获取餐厅信息根据餐馆ID");
		List resutList = new ArrayList();
		String sql = "SELECT * FROM RESTAURANT WHERE ID IN(" + restaurantids
				+ ") AND FLAG=1";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			set = pres.executeQuery();
			while (set.next()) {
				RestaurantPojo restaurantPojo = new RestaurantPojo();
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
				resutList.add(restaurantPojo);
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

		return resutList;
	}

	/**
	 * check new order count;
	 * 
	 * @return
	 */
	public int checkNewOrderCount() {
		logger.debug("获取餐厅信息根据餐馆ID");
		String sql = "SELECT COUNT(ORDERID) AMOUNT FROM ORDERFORM WHERE ORDERSTATE=1";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		int amount = -1;
		try {
			pres = con.prepareStatement(sql);
			set = pres.executeQuery();
			if (set.next()) {
				amount = set.getInt("AMOUNT");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			amount = -1;
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

		return amount;
	}

	/**
	 * 设置所有已确认的订单状态为派送完成
	 */
	public boolean updateOrderTofinish() {
		logger.debug("设置所有已确认的订单状态为派送完成");
		String sql = "update orderform set orderstate=3 where orderid in(select orderid from (select orderid from orderform  where orderstate=2) as a)";
		Connection con = getConnection();
		PreparedStatement pres = null;
		boolean flag = true;
		try {
			pres = con.prepareStatement(sql);
			pres.executeUpdate();
		} catch (Exception e) {
			flag = false;
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return flag;
	}

}
