package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

/**
 * 邮件相关DAO
 * 
 * @author 胡圣朗
 * 
 */
public class MailRelatedDao extends DaoSupport {
	private static Logger logger = Logger.getLogger(MailRelatedDao.class
			.getName()); // 日志对象;

	/**
	 * 插入邮件订阅表
	 * 
	 * @param userid
	 * @param mailAddress
	 * @param time
	 */
	public boolean insertEmailSubscription(String userid, String mailAddress,
			String time) {
		logger.debug("插入邮件订阅表userid=" + userid + " mailAddress=" + mailAddress);
		Connection con = getConnection();
		String sql = "INSERT INTO MAILSUBSCRIPTION(ID, USERID, EMAILADDRESS, TIME) VALUES(?,?,?,?)";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			int keyNum = getKey("mailsubscription");
			pres = con.prepareStatement(sql);
			pres.setInt(1, keyNum);
			pres.setString(2, userid);
			pres.setString(3, mailAddress);
			pres.setString(4, time);
			pres.execute();
			flag = true;
			logger.debug("插入邮件订阅表成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			flag = false;
			logger.debug("插入邮件订阅表失败");
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
	 * 获取已经被注册的邮件的数量.
	 * 
	 * @param emailAddress
	 * @return
	 */
	public int getNumOfSubscripted(String emailAddress) {
		logger.debug("检测邮箱地址是否已经被注册  emailAddress=" + emailAddress);
		String sql = "SELECT COUNT(*) TOTALNUM FROM MAILSUBSCRIPTION  WHERE EMAILADDRESS=?";
		int totalNum = 0;
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, emailAddress);
			set = pres.executeQuery();
			if(set.next()){
				totalNum = set.getInt("TOTALNUM");
			}
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
}
