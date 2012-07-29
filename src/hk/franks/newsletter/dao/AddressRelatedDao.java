package hk.franks.newsletter.dao;

import hk.franks.newsletter.logic.AddressRelatedLogic;
import hk.franks.newsletter.pojo.AddressInfoPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;


/**
 * 地址管理相关DAO
 * 
 * @author 胡圣朗
 * 
 */
public class AddressRelatedDao extends DaoSupport {
	private static Logger logger = Logger.getLogger(AddressRelatedDao.class
			.getName()); // 日志对象;

	/**
	 * 获取用户地址信息
	 * 
	 * @param userid
	 *            用户ID
	 * @return 用户地址信息对象集合
	 */
	public List getaddressinfo(String userid) {
		logger.debug("获取用户地址信息 userid="+userid);
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
		return addressinfoList;
	}

	/**
	 * 保存地址
	 * 
	 * @param userid
	 * @param receivername
	 * @param location //区域 比如红磡
	 * @param street   //具体的位置 比如海滨南岸2座4C
	 * @param phone
	 * @return 地址ID；-1则保存失败.
	 */
	public int saveAddress(String userid, String address) {
		logger.debug("保存用户地址信息");
		Connection con = getConnection();
		String sql = "INSERT INTO ADDRESSINFO(addressinfoid,userid,address,isdefault) VALUES(?,?,?,0)";
		PreparedStatement pres = null;
		boolean flag = true;
		int keynum = -1;
		try {
			keynum = getKey("addressinfo");
			pres = con.prepareStatement(sql);
			pres.setInt(1, keynum);
			pres.setString(2, userid);
			pres.setString(3, address);
			pres.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			keynum = -1;
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
				keynum = -1;
			}
		}
		return keynum;
	}

	/**
	 * 设置默认地址
	 * @param userid
	 * @param addressid
	 * @return 是否成功
	 */
	public boolean setdefaultaddress(String userid,int addressid) {
		logger.debug("设置默认地址");
		Connection con = getConnection();
		String sql = "UPDATE ADDRESSINFO SET ISDEFAULT=0 WHERE USERID=?";
		String setdefaultsql = "UPDATE ADDRESSINFO SET ISDEFAULT=1 WHERE ADDRESSINFOID=?";
		PreparedStatement pres = null;
		boolean flag = true;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			pres.execute();
			closeJDBCResource(pres);
			pres = con.prepareStatement(setdefaultsql);
			pres.setInt(1, addressid);
			pres.execute();
		} catch (Exception e) {
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
	 * 设置默认地址
	 * 
	 * @param addressid
	 * @return 是否成功
	 */
	public boolean deleteAddress(int addressid) {
		logger.debug("删除地址");
		Connection con = getConnection();
		String sql = "DELETE FROM ADDRESSINFO WHERE ADDRESSINFOID=?";
		PreparedStatement pres = null;
		boolean flag = true;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, addressid);
			pres.execute();
		} catch (Exception e) {
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
	 * 更新地址
	 * 
	 * @param addressid
	 * @param addressText
	 * @return 是否成功
	 */
	public boolean updateAddress(int addressid, String addressText) {
		logger.debug(" 更新地址addressid="+addressid+" addressText="+addressText);
		Connection con = getConnection();
		String sql = "UPDATE ADDRESSINFO SET ADDRESS=? WHERE ADDRESSINFOID=?";
		PreparedStatement pres = null;
		boolean flag = true;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, addressText);
			pres.setInt(2, addressid);
			pres.execute();
		} catch (Exception e) {
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
	
}
