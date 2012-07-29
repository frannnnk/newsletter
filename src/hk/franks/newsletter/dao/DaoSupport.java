package hk.franks.newsletter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * DAO支持对象，是所有DAO的超类，用于提供获取链接的公共操作.
 * 
 * @author 胡圣朗
 * 
 */
public class DaoSupport {
	private static Logger logger = Logger.getLogger(DaoSupport.class.getName()); // 日志对象;
	private Context ctx;
	private DataSource ds;

	public DaoSupport() {
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ecmeal");
		} catch (NamingException e) {
			logger.error(e.getMessage()+"--java:comp/env/jdbc/ecmeal");
		}
	}

	/**
	 * @throws 获取连接对象.
	 * @return
	 * @throws
	 */
	public Connection getConnection() {
		logger.debug("获取DAO链接对象");
		Connection con = null;
		try {
			con = ds.getConnection();
			logger.debug("获取DAO链接对象成功!" + con.toString());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return con;
	}
	
	/**
	 * close jdbc resource, this method can server to close connection resource,
	 * preparestatement resource and resultset resource.
	 * 
	 * @param resource. it can be Connection, Preparestatment, ResultSet.
	 * 
	 */
	public void closeJDBCResource(Object resource) {
		if (resource != null) {
			logger.debug("closing JDBC Resource...");
			Class objclass = resource.getClass();
			try {
				System.out.println(objclass.getName());
				java.lang.reflect.Method method = objclass.getMethod("close",
						null);
				method.invoke(resource, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("some error occured when closing JDBC resource...");
				logger.error(e.getMessage());
			}
		} else {
			logger.debug("JDBC resource has been closed...");
		}
	}

	/**
	 * 获取某表的主键ID
	 * 
	 * @param tablename
	 *            表名
	 * @return
	 * @throws SQLException
	 */
	public int getKey(String tablename) throws SQLException {
		logger.debug("获取" + tablename + "表主键");
		int key = 0;
		Connection con = getConnection();
		String sql = "SELECT KEYNUM FROM KEYTABLE WHERE TABLENAME=?";
		String updatesql = "UPDATE KEYTABLE SET KEYNUM=KEYNUM+1 WHERE TABLENAME=?";
		PreparedStatement pres = null;
		ResultSet set = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, tablename);
			set = pres.executeQuery();
			set.next();
			key = set.getInt(1);
			pres.close();
			set.close();
			// 取数之后加1；
			pres = con.prepareStatement(updatesql);
			pres.setString(1, tablename);
			pres.execute();
			pres.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			throw e;
		} finally {// 关闭资源.
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
				throw e;
			}
		}
		return key;
	}
}
