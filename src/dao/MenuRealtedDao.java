package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import pojo.MenuItemPojo;

/**
 * 菜单相关DAO(提供菜单查询，新增等操作)（弃用）
 * 
 * @author 胡圣朗
 * 
 */
public class MenuRealtedDao extends DaoSupport {
	private static Logger logger = Logger.getLogger(MenuRealtedDao.class
			.getName()); // 日志对象;

	/**
	 * 获取推荐食物数据项.
	 * 
	 * @return 菜单对象集合
	 */
	public List getFoodInfo() {
		logger.debug("获取启用的菜单项.");
		List menuList = new ArrayList();
		Connection con = getConnection();
		String sql = "SELECT * FROM FOOD WHERE IS_RECOMMEND=1";
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			set = pres.executeQuery();
			while (set.next()) {
				MenuItemPojo menuitem = new MenuItemPojo();
				menuitem.setPicture_url(set.getString("picture_url"));
				menuitem.setDescription(set.getString("description"));
				menuitem.setDiliver_time(set.getString("diliver_time"));
				menuitem.setPrice(set.getString("price"));
				menuitem.setFoodid(set.getString("foodid"));
				menuitem.setFoodname(set.getString("foodname"));
				menuList.add(menuitem);
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
		return menuList;
	}

}
