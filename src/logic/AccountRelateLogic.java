package logic;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import logic.encryption.EncryptFunction;
import logic.encryption.MD5;
import logic.mail.MailFuncion;

import org.apache.log4j.Logger;

import common.CommonFunction;
import common.Constant;

import dao.AccountRelateDao;

/**
 * 账户相关逻辑类（提供账户注册，登录等操作）
 * 
 * @author 胡圣朗
 */
public class AccountRelateLogic {
	private static Logger logger = Logger.getLogger(AccountRelateLogic.class
			.getName()); // 日志对象;
	private AccountRelateDao dao = new AccountRelateDao(); // 数据库操作对象.
	private MailFuncion mail = new MailFuncion();
	private ReviewFavoriteBonusLogic bonuslogic = new ReviewFavoriteBonusLogic();

	/**
	 * 账户注册操作
	 * 
	 * @param userid
	 *            用户ID 就是EMAIL
	 * @param nickname
	 *            用户昵称
	 * @param password
	 *            用户密码（这里是未加密的密码）
	 * @return
	 */
	public boolean account_register(String userid, String nickname,
			String password) {
		logger.debug("账户注册操作");
		String encrypedPassword = MD5.MD5_Encryption(password);// 密码加密;
		String createTime = CommonFunction.getcurrentTimetext();
		userid = CommonFunction.toLowerCase(userid);// 大写转小写.
		boolean flag = dao.insertAccount(userid, nickname, encrypedPassword,
				createTime);
		if (flag) {
			// 自动发送邮箱地址确认邮件.
			sendAccountConfirmEmail(userid, nickname);
			// 用户好友邀请限额表插入初始数据.
			dao.insertInvitationQuota(userid, Constant.INVITATIONQUOTA);
		}
		return flag;
	}

	/**
	 * 自动发送邮箱地址确认邮件.
	 * 
	 * @param userid
	 * @param nickname
	 */
	private void sendAccountConfirmEmail(String userid, String nickname) {
		logger.debug("自动发送邮箱地址确认邮件.");
		String encrypedUserid = EncryptFunction.encode(userid); // 用户ID加密
		// 简单加密算法.
		String url = Constant.URL + "/verifyaccount.action?code="
				+ encrypedUserid;

		String addresses = userid;
		String subject = "易食飯註冊確認郵件";
		String contentHTML = "<html><body><b>親愛的&nbsp;"
				+ nickname
				+ "</b><br><br>感謝您註冊並使用易食飯！請點擊以下鏈接對註冊時填寫的EMAIL進行確認驗證：<br>"
				+ "<a href=\""
				+ url
				+ "\" target=\"_blank\">"
				+ url
				+ "</a><br><br>如你點擊上述激活鏈接後沒有彈出頁面窗口，易食飯建議你：復制激活地址，打開一個瀏覽器窗口，把地址粘貼到地址欄按鍵盤回車鍵，手動激活賬戶完成註冊，多謝!" +
						"" +
						"<br><br><b><font color=gray>易食飯團隊<br>ecmeal.hk © Copyright 2012</b></font></body></html>";
		logger.info(url);
		mail.mailSenderForCustom(addresses, subject, contentHTML);
	}

	/**
	 * 检验账户是否已经存在
	 * 
	 * @param userid
	 * @return true-账户不存在；false-账户已存在 。
	 */
	public boolean checkAccountIsExist(String userid) {
		logger.debug("检测账户是否存在");
		return dao.checkAccount(userid);
	}

	/**
	 * 登录效验
	 * 
	 * @param userid
	 *            用户的EMAIL地址
	 * @param password
	 *            未加密的密码.
	 * @return
	 */
	public boolean loginValidate(String userid, String password) {
		logger.debug("用户登录效验");
		boolean flag = false;
		try {
			String encrypedPassword = MD5.MD5_Encryption(password);// 密码加密;
			flag = dao.validateUser(userid, encrypedPassword);
			if(flag==true){
				Timestamp currenttimestamp = CommonFunction.getCurrentTimestamp();
				dao.insertLogintimeRecord(userid, currenttimestamp);
			}
				
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return flag;
	}

	/**
	 * 登录效验
	 * 
	 * @param userid
	 *            用户的EMAIL地址
	 * @param encryptpassword
	 *            加密后的密码.
	 * @return
	 */
	public boolean loginValidatebyEncryptpassword(String userid,
			String encryptpassword) {
		logger.debug("用户登录效验");
		boolean flag = false;
		try {
			flag = dao.validateUser(userid, encryptpassword);
			if(flag==true){
				Timestamp currenttimestamp = CommonFunction.getCurrentTimestamp();
				dao.insertLogintimeRecord(userid, currenttimestamp);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			flag = false;
		}
		return flag;
	}

	/**
	 * 获取用户的昵称.
	 * 
	 * @param userid
	 *            用户ID.
	 * @return 用户昵称
	 */
	public String getNickname(String userid) {
		logger.debug("获取用户昵称");
		return dao.getnickname(userid);
	}

	/**
	 * 更新用戶密碼
	 * 
	 * @return
	 */
	public boolean updatePasswrod(String userid, String password) {
		logger.debug("更新用戶密碼 userid=" + userid);
		String encrypedPassword = MD5.MD5_Encryption(password);// 密码加密;
		return dao.updatePassword(userid, encrypedPassword);
	}

	/**
	 * 确认账户。
	 * 
	 * @param encryptedUserid
	 *            加密后的用户ID.
	 * @return 0-已经验证过；1-验证成功;2-验证失败.
	 */
	public int verifyAccount(String encryptedUserid) {
		int state = 0;
		String userid = "";
		boolean flag = false;
		try {
			userid = EncryptFunction.decode(encryptedUserid);
			logger.debug("确认账户 userid=" + userid);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		flag = dao.checkAccount(userid);
		if (flag) { // 验证失败.
			state = 2;
		} else {
			if (dao.checkIsConfirmed(userid))
				state = 0;
			else {
				if (dao.verifyAccount(userid)) { // 验证成功送积分.
					state = 1;
					bonuslogic.setBonus(userid, 1);
				} else
					state = 2;
			}
		}
		return state;
	}

}
