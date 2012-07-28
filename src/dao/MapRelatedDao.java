package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import pojo.DeliveryScopePojo;

/**
 * 地图操作相关DAO类
 * 
 * @author 胡圣朗
 * 
 */
public class MapRelatedDao extends DaoSupport {
	private static Logger logger = Logger.getLogger(MapRelatedDao.class
			.getName()); // 日志对象;

	/**
	 * 根据坐标查询区域對象 如果未查到 返回null；
	 * 
	 * @param x_coordinate
	 * @param y_coordinate
	 * @return
	 */
	public DeliveryScopePojo queryDeliveryScopeByCoordinate(
			String x_coordinate, String y_coordinate) {
		logger.debug("查询位置区域编号");
		DeliveryScopePojo deliveryPojo = null;
		String sql = "SELECT DISTINCT D.* FROM DELIVERY_SCOPE D,RESTAURANT_DELIVERY_RELATION R WHERE D.COORDINATE_MAX_X>? "
				+ "AND D.COORDINATE_MIN_X<? AND D.COORDINATE_MAX_Y>? "
				+ "AND D.COORDINATE_MIN_Y<? AND D.ID=R.SCOPE_ID";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		int number = -1;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, x_coordinate);
			pres.setString(2, x_coordinate);
			pres.setString(3, y_coordinate);
			pres.setString(4, y_coordinate);
			set = pres.executeQuery();
			if (set.next()) {
				deliveryPojo = new DeliveryScopePojo();
				deliveryPojo.setCoordinate1_x(set.getString("coordinate1_x"));
				deliveryPojo.setCoordinate1_y(set.getString("coordinate1_y"));
				deliveryPojo.setCoordinate2_x(set.getString("coordinate2_x"));
				deliveryPojo.setCoordinate2_y(set.getString("coordinate2_y"));
				deliveryPojo.setCoordinate3_x(set.getString("coordinate3_x"));
				deliveryPojo.setCoordinate3_y(set.getString("coordinate3_y"));
				deliveryPojo.setCoordinate4_x(set.getString("coordinate4_x"));
				deliveryPojo.setCoordinate4_y(set.getString("coordinate4_y"));
				deliveryPojo.setCoordinate_max_x(set
						.getString("coordinate_max_x"));
				deliveryPojo.setCoordinate_max_y(set
						.getString("coordinate_max_y"));
				deliveryPojo.setCoordinate_min_x(set
						.getString("coordinate_min_x"));
				deliveryPojo.setCoordinate_min_y(set
						.getString("coordinate_min_y"));
				deliveryPojo.setId(set.getString("id"));
				deliveryPojo.setDescription(set.getString("description"));
				deliveryPojo.setNote(set.getString("note"));
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
		return deliveryPojo;
	}
}
