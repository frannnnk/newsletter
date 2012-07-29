package hk.franks.newsletter.dao;

import hk.franks.newsletter.common.CommonFunction;
import hk.franks.newsletter.controller.utils.CommonUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import model.UsersModel;

import org.apache.log4j.Logger;


/**
 * 账户相关DAO（提供新增用户记录，查询用户表等操作）
 * 
 * @author 胡圣朗
 * 
 */
public class AccountRelateDao extends DaoSupport {
	
	private Logger logger = Logger.getLogger(this.getClass()); // 日志对象;
	
	
	
	
	
	
	
	public UsersModel validateUser(String userid, String encryptedPwd) {
		logger.debug("Dao now checking user with id: "+ userid);
		
		Connection con = getConnection();
		String sql = "SELECT * FROM USERS WHERE EMAIL=? AND PASSWORD=? ";
		
		PreparedStatement pres = null;
		ResultSet rs = null;
		
		UsersModel resultModel = null;
		
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			pres.setString(2, encryptedPwd);
			
			rs = pres.executeQuery();
			
			if (rs.next()){
				resultModel = new UsersModel();
				resultModel.setEmail(rs.getString("EMAIL"));
				resultModel.setUserId(rs.getInt("USER_ID"));
				resultModel.setLastLogin(rs.getDate("LAST_LOGIN"));
				resultModel.setStatus(rs.getString("STATUS"));
			}
			
			
			if (!CommonUtil.isExNull(resultModel)) {
				logger.debug("Login Succeed");
			} else {
				logger.debug("Login Failed. User with Email:"+userid+" is not exist or password does not match our record.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			try {
				closeJDBCResource(rs);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		
		return resultModel;
	}
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 新增账户
	 * 
	 * @param userid
	 *            用户ID 就是EMAIL
	 * @param nickname
	 *            用户昵称
	 * @param password
	 *            用户密码（这里是加密后的密码）
	 * @return 成功标志 true-成功；false-失败;
	 */
	public boolean insertAccount(String userid, String nickname,
			String password, String createTime) {
		logger.debug("插入账户表信息");
		Connection con = getConnection();
		String sql = "INSERT INTO ACCOUNT(USERID, CREATETIME, PASSWORD, NICKNAME, CREDITVALUE, ISCONFIRM) VALUES(?,?,?,?,0,0)";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			pres.setString(2, createTime);
			pres.setString(3, password);
			pres.setString(4, nickname);
			pres.execute();
			flag = true;
			logger.debug("插入账户表信息成功");
		} catch (Exception e) {
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
	 * 查询账户名是否存在.
	 * 
	 * @param userid
	 *            用户ID
	 * @return
	 */
	public boolean checkAccount(String userid) {
		logger.debug("查询账户名是否存在");
		Connection con = getConnection();
		String sql = "SELECT COUNT(USERID) FROM ACCOUNT WHERE USERID=?";
		PreparedStatement pres = null;
		ResultSet set = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			set = pres.executeQuery();
			set.next();
			int accountNum = set.getInt(1);
			if (accountNum == 0) {
				flag = true;
				logger.debug("账户已经不存在");
			} else {
				flag = false;
				logger.debug("账户已经存在");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			flag = false;
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
	 * insert last login time
	 */
	public void insertLogintimeRecord(String userid, Timestamp currenttimestamp){
		logger.debug("update last login time");
		Connection con = getConnection();
		String sql = "INSERT INTO LOGINRECORD(USERID, LOGINTIME) VALUES(?,?)";
		PreparedStatement pres = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			pres.setTimestamp(2, currenttimestamp);
			pres.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
	
	
	
	public boolean updateLastLoginDate(String userEmail){
		Connection con = getConnection();
		PreparedStatement pres = null;
		String sql = "UPDATE USERS SET LAST_LOGIN = SYSDATE() WHERE EMAIL = ? ";
		
		boolean succeed = false;
		
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userEmail);
			succeed = pres.executeUpdate()>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		
		return succeed;
	}
	
	/**
	 * 获取用户的昵称.
	 * 
	 * @param userid
	 *            用户ID.
	 * @return 用户昵称
	 */
	public String getnickname(String userid) {
		logger.debug("获取用户昵称");
		Connection con = getConnection();
		String sql = "SELECT NICKNAME FROM ACCOUNT  WHERE USERID=?";
		PreparedStatement pres = null;
		ResultSet set = null;
		String nickname = "";
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			set = pres.executeQuery();
			set.next();
			nickname = set.getString(1);
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
		return nickname;
	}

	/**
	 * 更新用戶密碼
	 * 
	 * @param userid
	 * @param encryPassword
	 *            加密後的密碼.
	 * @return
	 */
	public boolean updatePassword(String userid, String encryPassword) {
		logger.debug("更新用戶密碼");
		Connection con = getConnection();
		String sql = "UPDATE ACCOUNT SET PASSWORD=? WHERE USERID=?";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, encryPassword);
			pres.setString(2, userid);
			pres.execute();
			flag = true;
			logger.debug("插入账户表信息成功");
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
			}
		}
		return flag;
	}

	/**
	 * 查询账户是否已经验证过.
	 * 
	 * @param userid
	 *            用户ID
	 * @return true-验证过了 ;false-没有被验证过
	 */
	public boolean checkIsConfirmed(String userid) {
		logger.debug("查询账户名是否存在");
		Connection con = getConnection();
		String sql = "SELECT COUNT(USERID) FROM ACCOUNT WHERE USERID=? AND ISCONFIRM=1";
		PreparedStatement pres = null;
		ResultSet set = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			set = pres.executeQuery();
			if (set.next()) {
				int accountNum = set.getInt(1);
				if (accountNum > 0) {
					flag = true;
					logger.debug("账户已被验证过");
				} else {
					flag = false;
					logger.debug("账户未被被验证过");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	 * 验证用户.
	 * 
	 * @param userid
	 *            用户ID
	 * @return true-验证成功 ;false-没有被验证成功
	 */
	public boolean verifyAccount(String userid) {
		logger.debug("查询账户名是否存在");
		Connection con = getConnection();
		String sql = "UPDATE ACCOUNT SET ISCONFIRM=1 WHERE USERID=?";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
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
	 * 插入邀请限额表
	 * 
	 * @param userid
	 * @param quota
	 * @return
	 */
	public boolean insertInvitationQuota(String userid, int quota) {
		logger.debug("插入邀请限额表 userid=" + userid + " quota=" + quota);
		Connection con = getConnection();
		String sql = "INSERT INTO INVITATIONQUOTA VALUES(?,?)";
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			pres.setInt(2, quota);
			pres.execute();
			flag = true;
		} catch (Exception e) {
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
}
