package hk.franks.newsletter.dao;

import hk.franks.newsletter.pojo.PublicNoticePojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * 
 * 去公告 播报 评论等信息DAO类
 * @author 胡圣朗
 */
public class InfoNoticeDao extends DaoSupport{
	private static Logger logger = Logger.getLogger(InfoNoticeDao.class
			.getName()); // 日志对象;
	
	/**
	 * 取最新的limitNum条公告
	 * @param limitNum
	 * @return
	 */
	public List getPublicnotice(int limitNum){
		logger.debug(" 取最新的"+limitNum+"条公告");
		String sql = "SELECT * FROM PUBLICNOTICE ORDER BY ID DESC LIMIT 0,?";
		List resultList = new ArrayList();
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, limitNum);
			set = pres.executeQuery();
			while(set.next()){
				PublicNoticePojo publicNoticePojo = new PublicNoticePojo();
				publicNoticePojo.setId(set.getString("ID"));
				publicNoticePojo.setSubject(set.getString("SUBJECT"));
				publicNoticePojo.setTime(set.getString("TIME"));
				publicNoticePojo.setWebname(set.getString("WEBNAME"));
				resultList.add(publicNoticePojo);
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
		return resultList;
	}
}
