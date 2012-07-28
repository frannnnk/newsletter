package logic;

import java.io.UnsupportedEncodingException;
import java.util.List;

import logic.encryption.EncryptFunction;
import logic.mail.MailFuncion;

import org.apache.log4j.Logger;

import common.CommonFunction;
import common.Constant;

import dao.ReviewFavoriteBonusDao;

/**
 * 用户中心操作逻辑类.(提供 评论操作，收藏操作, 积分操作,好友推荐)
 * 
 * @author 胡圣朗
 * 
 */
public class ReviewFavoriteBonusLogic {
	private static Logger logger = Logger
			.getLogger(ReviewFavoriteBonusLogic.class.getName()); // 日志对象;
	private ReviewFavoriteBonusDao dao = new ReviewFavoriteBonusDao(); // 数据库操作对象.
	private MailFuncion mail = new MailFuncion(); // 邮件服务

	/**
	 * 获取用户收藏
	 */
	public List getFavorite(String userid) {
		logger.debug("获取用户收藏 userid=" + userid);
		return dao.getFavorite(userid);
	}

	/**
	 * 保存用户收藏.
	 * 
	 * @param userid
	 * @param restaurantid
	 * @return 0-保存收藏;1-已经收藏;2-收藏失败
	 */
	public int saveFavorite(String userid, String restaurantid,
			String locationstr) {
		logger.debug("保存用户收藏. s userid=" + userid + "  restaurantid="
				+ restaurantid);
		String time = CommonFunction.getcurrentTimetext();
		int result = 2;
		int totalnum = dao.checkFavoriteIsExisted(userid, restaurantid);
		if (totalnum == 0) {
			boolean flag = dao.saveFavorite(userid, restaurantid, time,
					locationstr);
			if (flag)
				result = 0;
			else
				result = 2;
		}else{
			result = 1;
		}
		return result;
	}

	/**
	 * 获取用户评论
	 * 
	 * @param userid
	 * @return
	 */
	public List getReview(String userid, int requestPageNum) {
		logger.debug("获取用户评论 userid=" + userid);
		int startindex=(requestPageNum-1)*Constant.MYREVIEWRECORDNUM;
		int viewRecordNum = Constant.MYREVIEWRECORDNUM;
		return dao.getReview(userid, startindex, viewRecordNum);
	}
	/**
	 * 获取用户评论总页数
	 * @param userid
	 * @return
	 */
	public int getReviewTotalPages(String userid){
		logger.debug("获取用户评论 的总页数.....");
		int totalnum = dao.getReviewTotalNum(userid);
		return totalnum/Constant.MYREVIEWRECORDNUM+1;
	}

	/**
	 * 保存用户评论
	 * 
	 * @param userid
	 * @param restaurantid
	 * @param content
	 * @return
	 */
	public boolean saveReview(String userid, String content, String orderid,
			String score) {
		logger.debug("保存用户评论 userid=" + userid + "  orderid=" + orderid
				+ " content=" + content);
		String time = CommonFunction.getcurrentTimetext();
		return dao.saveReview(content, userid, time, orderid, score);
	}

	/**
	 * 删除评论
	 * 
	 * @param reviewid
	 * @return
	 */
	public boolean deleteReview(String reviewid) {
		return dao.deleteReview(reviewid);
	}

	/**
	 * 删除用户收藏
	 * 
	 * @param favoriteid
	 * @return
	 */
	public boolean deleteFavorite(String favoriteid) {
		return dao.deleteFavorite(favoriteid);
	}

	/**
	 * 根据用户所做操作加积分
	 * 
	 * @param type
	 *            1-邮件确认成功；2-完成一张订单；3-评论.
	 * @throws UnsupportedEncodingException
	 * @throws NumberFormatException
	 */
	public boolean setBonus(String userid, int type) {
		logger.debug("根据用户所做操作加积分 userid=" + userid + " type=" + type);
		boolean flag = false;
		try {
			String originalEncryptBonus = dao.getBonus(userid);
			int originalBonus = 0;
			if (originalEncryptBonus == null || originalEncryptBonus.equals("")) {
				originalBonus = 0;
			} else {
				originalBonus = new Integer(
						EncryptFunction.decode(originalEncryptBonus));
			}
			int tmpbonus = 0;
			if (type == 1) { // 邮件确认成功
				tmpbonus = originalBonus + Constant.ENROLLCONFIRM_BONUS;
			} else if (type == 2) { // 完成一张订单
				tmpbonus = originalBonus + Constant.ORDER_BONUS;
			} else if (type == 3) {// 评论
				tmpbonus = originalBonus + Constant.REVIEW_BONUS;
			}
			String encryptBonus = EncryptFunction.encode(new Integer(tmpbonus)
					.toString());
			flag = dao.setBonus(userid, encryptBonus);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return flag;
	}

	/**
	 * 查询我的好友邀请
	 * 
	 * @return 好友邀请对象列表
	 */
	public List searchMyinvitation(String userid) {
		logger.debug("查询我的好友邀请userid=" + userid);
		return dao.getMyinvitation(userid);
	}

	/**
	 * 获取用户所剩限额
	 * 
	 * @param userid
	 */
	public int getInvitationQuota(String userid) {
		logger.debug("获取用户所剩限额 userid=" + userid);
		return dao.getInvitationQuota(userid);
	}

	/**
	 * 
	 * @param nickname
	 * @param userid
	 * @param friendemail
	 * @return
	 */
	public boolean recommendFirend(String nickname, String userid,
			String friendemail) {
		logger.debug("推荐好友userid=" + userid);
		boolean flag = true;
		if (!isInvitationMailExis(userid, friendemail)) {
			flag = dao.insertInvitationRecord(userid, friendemail,
					CommonFunction.getcurrentTimetext());
		}
		if (flag) { // 发邮件给好友
			this.sendAccountConfirmEmail(nickname, userid, friendemail);
		}
		return flag;
	}

	/**
	 * 自动发送邮箱地址确认邮件.
	 * 
	 * @param nickname
	 * @param userid
	 * @param nickname
	 * @param path
	 */
	private void sendAccountConfirmEmail(String nickname, String userid,
			String friendemail) {
		logger.debug("自动发送邮箱地址确认邮件.");
		String encrypedUserid = EncryptFunction.encode(userid); // 用户ID加密
		// 简单加密算法.
		String url = Constant.DNS + "account/login.jsp?type=2";

		String addresses = friendemail;
		String cc = userid;
		String subject = "易食飯-好友邀请";
		String contentHTML = "<html><body>親愛的您好：<br><br>您的好友" + nickname
				+ "["+cc+"]邀請您來易食飯！易食飯系香港外賣訂餐網站，讓我們一起告別午餐的煩惱。<br>" + "<a href=\"" + url
				+ "\" target=\"_blank\">點擊註冊</a>" +
						"<br><br><b><font color=gray>易食飯團隊<br>ecmeal.hk © Copyright 2012</b></font></body></html>";
		logger.info(url);
		mail.mailSenderForCustom(addresses, subject, contentHTML);
	}

	/**
	 * 此邮件地址是否已经邀请过
	 * 
	 * @param friendmail
	 * @return true-已经邀请过 false-没有被邀请过
	 */
	public boolean isInvitationMailExis(String userid, String friendmail) {
		int totalnum = dao.getInvitationRecordNum(userid, friendmail);
		if (totalnum > 0)
			return true;
		else
			return false;
	}
}
