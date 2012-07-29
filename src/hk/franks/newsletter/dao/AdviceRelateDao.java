package hk.franks.newsletter.dao;

import hk.franks.newsletter.pojo.AdvicePojo;
import hk.franks.newsletter.pojo.AdviceResponsePojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * 建议反馈DAO
 * 
 * @author 胡圣朗
 * 
 */
public class AdviceRelateDao extends DaoSupport {
	private static Logger logger = Logger.getLogger(AdviceRelateDao.class
			.getName()); // 日志对象;

	/**
	 * 保存用户建议
	 * 
	 * @param userid
	 * @param content
	 * @param time
	 */
	public boolean insertAdvice(String userid, String content, String time) {
		logger.debug(" 保存用户建议 userid=" + userid + " content=" + content
				+ " time=" + time);
		Connection con = getConnection();
		String sql = "INSERT INTO ADVICE_COMPLAIN(ID, USERID, CONTENT, TIME) VALUES(?,?,?,?)";
		PreparedStatement pres = null;
		boolean flag = true;
		int keynum = -1;
		try {
			keynum = getKey("advice_complain");
			pres = con.prepareStatement(sql);
			pres.setInt(1, keynum);
			pres.setString(2, userid);
			pres.setString(3, content);
			pres.setString(4, time);
			pres.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			flag = false;
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * 保存客服建议反馈
	 * 
	 * @param adviceid
	 * @param responsecontent
	 * @param time
	 */
	public boolean insertAdviceResponse(String adviceid,
			String responsecontent, String time) {
		logger.debug(" 保存用户建议 adviceid=" + adviceid + " responsecontent="
				+ responsecontent + " time=" + time);
		Connection con = getConnection();
		String sql = "INSERT INTO ADVICERESPONSE(ID, ADVICEID, RESPONSECONTENT, TIME) VALUES(?,?,?,?)";
		PreparedStatement pres = null;
		boolean flag = true;
		int keynum = -1;
		try {
			keynum = getKey("adviceresponse");
			pres = con.prepareStatement(sql);
			pres.setInt(1, keynum);
			pres.setString(2, adviceid);
			pres.setString(3, responsecontent);
			pres.setString(4, time);
			pres.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			flag = false;
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * 获取建议反馈表总记录数
	 * 
	 * @return
	 */
	public int searchTotalNumAdvice() {
		logger.debug("获取建议反馈表总记录数");
		String sql = "SELECT COUNT(*)ALLNUM FROM ADVICE_COMPLAIN";
		int totalNum = 0;
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			set = pres.executeQuery();
			if (set.next())
				totalNum = set.getInt("ALLNUM");
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
		return totalNum;
	}

	/**
	 * 查询用户建议反馈记录.
	 * 
	 * @param startindex
	 * @param recordnum
	 */
	public List searchAdvice(int startindex, int recordnum) {
		logger.debug("查询用户建议反馈记录.");
		List AdviceList = new ArrayList();
		String sql = "SELECT A.*,U.NICKNAME FROM ADVICE_COMPLAIN A, ACCOUNT U WHERE A.USERID=U.USERID ORDER BY ID DESC LIMIT ?,?";
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
				AdvicePojo advicepojo = new AdvicePojo();
				advicepojo.setId(set.getString("id"));
				advicepojo.setUserid(set.getString("userid"));
				advicepojo.setContent(set.getString("content"));
				advicepojo.setUsername(set.getString("nickname"));
				advicepojo.setTime(set.getString("time"));
				AdviceList.add(advicepojo);
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
		return AdviceList;
	}

	/**
	 * 获取建议反馈
	 * 
	 * @param adviceidsStr
	 * @return
	 */
	public List searchAdviceResponse(String adviceidsStr) {
		logger.debug("获取建议反馈");
		List AdviceResponseList = new ArrayList();
		String sql = "SELECT * FROM ADVICERESPONSE WHERE ADVICEID IN("
				+ adviceidsStr + ")";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			set = pres.executeQuery();
			while (set.next()) {
				AdviceResponsePojo adviceResponsepojo = new AdviceResponsePojo();
				adviceResponsepojo.setId(set.getString("id"));
				adviceResponsepojo.setAdviceid(set.getString("adviceid"));
				adviceResponsepojo.setContent(set.getString("responsecontent"));
				adviceResponsepojo.setTime(set.getString("time"));
				AdviceResponseList.add(adviceResponsepojo);
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
		return AdviceResponseList;
	}

	/**
	 * 增加餐厅被邀请加盟数量
	 * 
	 * @param restaurantid
	 * @return
	 */
	public boolean addRestaurantAskCoop(String restaurantid, String currentdate) {
		logger.debug("增加餐厅被邀请加盟数量");
		Connection con = getConnection();
		String sql = "UPDATE RESTAURANTBEASKEDCOOP SET ASKNUM=ASKNUM+1 WHERE RESTAURANTID=? AND DATE=?";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, restaurantid);
			pres.setString(2, currentdate);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

	/**
	 * 插入餐厅被邀请加盟数量
	 * 
	 * @param restaurantid
	 * @return
	 */
	public boolean insertRestaurantAskCoop(int restaurantid, String date) {
		logger.debug("保存餐館配送範圍信息");
		Connection con = getConnection();
		String sql = "INSERT INTO RESTAURANTBEASKEDCOOP(RESTAURANTID,ASKNUM, DATE) VALUES(?,1,?)";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, restaurantid);
			pres.setString(2, date);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

	/**
	 * 是否某天的商家被要求合作的记录存在
	 * 
	 * @param date
	 * @return
	 */
	public boolean isRestaurantAskCoopBydate(String date, String resid) {
		logger.debug("是否某天的商家被要求合作的记录存在");
		List AdviceResponseList = new ArrayList();
		String sql = "SELECT Count(*) TOTALNUM FROM RESTAURANTBEASKEDCOOP WHERE DATE=? AND RESTAURANTID=?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		boolean flag = false;
		int recordnum = 0;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, date);
			pres.setString(2, resid);
			set = pres.executeQuery();
			if (set.next()) {
				recordnum = set.getInt("TOTALNUM");
			}
			if (recordnum > 0)
				flag = true;
			else
				flag = false;
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
		return flag;
	}

	/**
	 * 获取被要求餐厅的记录数
	 * 
	 * @return 昨日的记录数;全部记录数
	 */
	public String getRestaurantAskCoopRecord(String restaurantid, String date) {
		logger.debug("是否某天的商家被要求合作的记录存在");
		StringBuffer resultStr = new StringBuffer();
		int totalRecordNum = 0;
		int yesterdayRecordNum = 0;
		List AdviceResponseList = new ArrayList();
		String sqlTotal = "SELECT * FROM restaurantbeaskedcoop WHERE RESTAURANTID=?";
		String sqlyesterday = "SELECT * FROM restaurantbeaskedcoop WHERE RESTAURANTID=? AND DATE=?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;

		try {
			// 获取某餐厅全部的记录
			pres = con.prepareStatement(sqlTotal);
			pres.setString(1, restaurantid);
			set = pres.executeQuery();
			while (set.next()) {
				totalRecordNum = totalRecordNum + set.getInt("ASKNUM");
			}
			closeJDBCResource(set);
			closeJDBCResource(pres);
			// 获取某餐厅昨天的记录
			pres = con.prepareStatement(sqlyesterday);
			pres.setString(1, restaurantid);
			pres.setString(2, date);
			set = pres.executeQuery();
			if (set.next()) {
				yesterdayRecordNum = set.getInt("ASKNUM");
			}
			resultStr.append(yesterdayRecordNum);
			resultStr.append(";");
			resultStr.append(totalRecordNum);
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
		return resultStr.toString();
	}
}
