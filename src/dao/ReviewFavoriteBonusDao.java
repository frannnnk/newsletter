package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import pojo.FavoritePojo;
import pojo.FriendsInvitationPojo;
import pojo.ReviewPojo;

/**
 * 收藏评论相关操作DAO
 * 
 * @author 胡圣朗
 * 
 */
public class ReviewFavoriteBonusDao extends DaoSupport {
	private static Logger logger = Logger.getLogger(AddressRelatedDao.class
			.getName()); // 日志对象;

	/**
	 * 检测收藏是否已经存在
	 * 
	 * @param userid
	 * @param restaurantid
	 * @return 0-不存在; >0存在
	 */
	public int checkFavoriteIsExisted(String userid, String restaurantid) {
		logger.debug("保存我的收藏 userid=" + userid + " restaurantid="
				+ restaurantid);
		String sql = "SELECT COUNT(*)TOTALNUM FROM FAVORITE WHERE USERID=? AND  RESTAURANTID=?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		int totalnum = 0 ;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			pres.setString(2, restaurantid);
			set = pres.executeQuery();
			if(set.next())
				totalnum = set.getInt("TOTALNUM");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {// 关闭资源.
			try {
				closeJDBCResource(set);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e.getMessage());
			}
		}
		return totalnum;
	}

	/**
	 * 保存我的收藏
	 * 
	 * @param userid
	 * @param restaurantid
	 *            收藏的餐馆ID.
	 * @param time
	 *            收藏时间
	 * @return
	 */
	public boolean saveFavorite(String userid, String restaurantid,
			String time, String locationstr) {
		logger.debug("保存我的收藏 userid=" + userid + " restaurantid="
				+ restaurantid + " time=" + time);
		Connection con = getConnection();
		PreparedStatement pres = null;
		String sql = "INSERT INTO FAVORITE(ID, TIME, USERID, RESTAURANTID, LOCATIONSTR) VALUES(?,?,?,?,?);";
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			int id = this.getKey("favorite");
			pres.setInt(1, id);
			pres.setString(2, time);
			pres.setString(3, userid);
			pres.setString(4, restaurantid);
			pres.setString(5, locationstr);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 获取用户评论
	 * 
	 * @param userid
	 * @return
	 */
	public List getReview(String userid, int startindex, int recordnum) {
		List reviewList = new ArrayList();
		logger.debug("获取用户评论 UserID=" + userid);
		String sql = "SELECT V.* FROM REVIEW V, ORDERFORM O WHERE V.ORDERID=O.ORDERID " +
				"AND USERID=? ORDER BY V.ID  DESC LIMIT ?,?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		int totalnum = 0;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			pres.setInt(2, startindex);
			pres.setInt(3, recordnum);
			set = pres.executeQuery();
			while (set.next()) {
				ReviewPojo reviewpojo = new ReviewPojo();
				reviewpojo.setId(set.getString("ID"));
				reviewpojo.setContent(set.getString("CONTENT"));
				reviewpojo.setTime(set.getString("TIME"));
				reviewpojo.setUserid(set.getString("USERID"));
				reviewpojo.setOrderid(set.getString("ORDERID"));
				reviewpojo.setScore(set.getString("SCORE"));
				reviewList.add(reviewpojo);
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
				logger.debug(e.getMessage());
			}
		}

		return reviewList;
	}
	/**
	 * 获取用户的总评论数
	 * @param userid
	 * @return
	 */
	public int getReviewTotalNum(String userid) {
		logger.debug("获取用户评论的总数");
		String sql = "SELECT COUNT(ID) TOTALNUM FROM REVIEW V WHERE USERID=?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		int totalnum = 0;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			set = pres.executeQuery();
			if (set.next()) {
				totalnum = set.getInt("TOTALNUM");
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
				logger.debug(e.getMessage());
			}
		}

		return totalnum;
	}

	/**
	 * 保存我的评论
	 * 
	 * @param reviewcontent
	 * @param userid
	 * @param orderid
	 *            订单ID.
	 * @param time
	 *            收藏时间
	 * @param orderid
	 * @param score
	 *            1-好评;2-中评;3-差评
	 * 
	 * @return
	 */
	public boolean saveReview(String reviewcontent, String userid, String time,
			String orderid, String score) {
		logger.debug("保存我的评论 reviewcontent=" + reviewcontent + " userid="
				+ userid + " time=" + time);
		Connection con = getConnection();
		PreparedStatement pres = null;
		String sql = "INSERT INTO REVIEW(ID, CONTENT,TIME, USERID, ORDERID, SCORE) VALUES(?,?,?,?,?,?);";
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			int id = this.getKey("favorite");
			pres.setInt(1, id);
			pres.setString(2, reviewcontent);
			pres.setString(3, time);
			pres.setString(4, userid);
			pres.setString(5, orderid);
			pres.setString(6, score);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 获取我的收藏
	 * 
	 * @param userid
	 * @return
	 */
	public List getFavorite(String userid) {
		List favoriteList = new ArrayList();
		logger.debug("获取用户收藏 UserID=" + userid);
		String sql = "SELECT F.*,R.NAME FROM FAVORITE F, RESTAURANT R WHERE R.ID=F.RESTAURANTID AND USERID=?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		int totalnum = 0;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			set = pres.executeQuery();
			while (set.next()) {
				FavoritePojo favoritePojo = new FavoritePojo();
				favoritePojo.setId(set.getString("id"));
				favoritePojo.setTime(set.getString("time"));
				favoritePojo.setUserid(set.getString("userid"));
				favoritePojo.setRestaurantid(set.getString("restaurantid"));
				favoritePojo.setRestaurantName(set.getString("name"));
				favoritePojo.setLocationstr(set.getString("locationstr"));
				favoriteList.add(favoritePojo);
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
				logger.debug(e.getMessage());
			}
		}

		return favoriteList;
	}

	/**
	 * 删除我的收藏
	 * 
	 * @param favoriteID
	 * @return
	 */
	public boolean deleteFavorite(String favoriteID) {
		logger.debug("删除我的收藏  favoriteID=" + favoriteID);
		String sql = "DELETE FROM FAVORITE WHERE ID=?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, favoriteID);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 删除我的评论
	 * 
	 * @param reviewId
	 * @return
	 */
	public boolean deleteReview(String reviewId) {
		logger.debug("删除我的评论  reviewIds=" + reviewId);
		String sql = "DELETE FROM REVIEW WHERE ID=?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		boolean flag = false;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, reviewId);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e.getMessage());
			}
		}
		return flag;
	}

	/**
	 * 获取用户的加密积分字符串
	 * 
	 * @param userid
	 * @return
	 */
	public String getBonus(String userid) {
		logger.debug("获取用户的加密积分字符串 UserID=" + userid);
		String encryptedBonus = null;
		String sql = "SELECT BONUS FROM ACCOUNT WHERE USERID=?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			set = pres.executeQuery();
			if (set.next()) {
				encryptedBonus = set.getString("BONUS");
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
				logger.debug(e.getMessage());
			}
		}

		return encryptedBonus;
	}

	/**
	 * 设置用户积分 积分要加密
	 * 
	 * @param userid
	 * @return
	 */
	public boolean setBonus(String userid, String encryptedBonus) {
		logger.debug("设置用户积分 积分要加密 UserID=" + userid);
		boolean flag = false;
		String sql = "UPDATE ACCOUNT SET BONUS=? WHERE USERID=?";
		Connection con = getConnection();
		PreparedStatement pres = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, encryptedBonus);
			pres.setString(2, userid);
			pres.execute();
			flag = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e.getMessage());
			}
		}

		return flag;
	}

	/**
	 * 获取最新的N条评论数据
	 * 
	 * @return 数据集合
	 */
	public List getReview(int recordNum) {
		logger.debug("获取最新的N条数据 N=" + recordNum);
		String sql = "SELECT R.*,T.NAME FROM "
				+ "(SELECT * FROM REVIEW ORDER BY ID DESC LIMIT 0,?) R,"
				+ " ORDERFORM O, RESTAURANT T WHERE "
				+ "R.ORDERID=O.ORDERID AND O.RESTAURANTID=T.ID";
		List resultList = new ArrayList();
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setInt(1, recordNum);
			set = pres.executeQuery();
			while (set.next()) {
				ReviewPojo reviewpojo = new ReviewPojo();
				reviewpojo.setId(set.getString("ID"));
				reviewpojo.setContent(set.getString("CONTENT"));
				reviewpojo.setTime(set.getString("TIME"));
				reviewpojo.setUserid(set.getString("USERID"));
				reviewpojo.setOrderid(set.getString("ORDERID"));
				reviewpojo.setScore(set.getString("SCORE"));
				reviewpojo.setRestaurantname(set.getString("NAME"));
				resultList.add(reviewpojo);
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
				logger.debug(e.getMessage());
			}
		}
		return resultList;
	}

	/**
	 * 获取我的好友推荐列表
	 * 
	 * @param userid
	 * @return
	 */
	public List getMyinvitation(String userid) {
		logger.debug("获取我的好友推荐列表 userid=" + userid);
		String sql = "SELECT * FROM INVITATIONRECORD WHERE USERID=?";
		List resultList = new ArrayList();
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			set = pres.executeQuery();
			while (set.next()) {
				FriendsInvitationPojo friendInvitationPojo = new FriendsInvitationPojo();
				friendInvitationPojo.setId(set.getString("ID"));
				friendInvitationPojo.setUserid(set.getString("USERID"));
				friendInvitationPojo.setFriendemail(set
						.getString("FRIENDEMAIL"));
				friendInvitationPojo.setTime(set.getString("TIME"));
				friendInvitationPojo.setState(set.getString("STATE"));
				resultList.add(friendInvitationPojo);
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
				logger.debug(e.getMessage());
			}
		}
		return resultList;
	}

	/**
	 * 获取剩下限额
	 * 
	 * @param userid
	 * @return -1获取异常，>=0正常
	 */
	public int getInvitationQuota(String userid) {
		logger.debug("获取剩下限额 userid=" + userid);
		String sql = "SELECT * FROM INVITATIONQUOTA WHERE USERID=?";
		int quota = -1;
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			set = pres.executeQuery();
			if (set.next()) {
				quota = set.getInt("QUOTA");
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
				logger.debug(e.getMessage());
			}
		}
		return quota;
	}

	/**
	 * 插入好友推荐记录表
	 * 
	 * @param userid
	 * @param friendemail
	 * @param time
	 * @return
	 */
	public boolean insertInvitationRecord(String userid, String friendemail,
			String time) {
		logger.debug("保存我的收藏 userid=" + userid + " friendemail=" + friendemail
				+ " time=" + time);
		Connection con = getConnection();
		PreparedStatement pres = null;
		String InsertInvitationsql = "INSERT INTO INVITATIONRECORD(ID, USERID, FRIENDEMAIL, TIME, STATE) VALUES(?,?,?,?,?);";
		String updateInvitationSQL = "UPDATE INVITATIONQUOTA SET QUOTA=QUOTA-1 WHERE USERID=?";
		boolean flag = false;
		try {
			con.setAutoCommit(false);
			// 插入邀请记录表
			pres = con.prepareStatement(InsertInvitationsql);
			int id = this.getKey("invitationrecord");
			pres.setInt(1, id);
			pres.setString(2, userid);
			pres.setString(3, friendemail);
			pres.setString(4, time);
			pres.setInt(5, 0); // 默认是0-邀请已发出.
			pres.execute();

			// 更新限額表.
			pres = con.prepareStatement(updateInvitationSQL);
			pres.setString(1, userid);
			pres.execute();
			con.commit();
			flag = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {// 关闭资源.
			try {
				con.setAutoCommit(true);
				closeJDBCResource(pres);
				closeJDBCResource(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e.getMessage());

			}
		}
		return flag;
	}

	/**
	 * 获取好友邀请表记录条数
	 * 
	 * @param userid
	 * @param friendmail
	 */
	public int getInvitationRecordNum(String userid, String friendmail) {
		logger.debug("获取好友邀请表记录条数 userid=" + userid + " friendmail="
				+ friendmail);
		String sql = "SELECT COUNT(*) TOTALNUM FROM INVITATIONRECORD WHERE USERID=? AND FRIENDEMAIL=?";
		int resultnum = 0;
		Connection con = getConnection();
		PreparedStatement pres = null;
		ResultSet set = null;
		try {
			pres = con.prepareStatement(sql);
			pres.setString(1, userid);
			pres.setString(2, friendmail);
			set = pres.executeQuery();
			if (set.next()) {
				resultnum = set.getInt("TOTALNUM");
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
				logger.debug(e.getMessage());
			}
		}
		return resultnum;
	}

}
