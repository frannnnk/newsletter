package hk.franks.newsletter.controller.account;

import hk.franks.newsletter.common.CommonFunction;
import hk.franks.newsletter.logic.AccountRelateLogic;
import hk.franks.newsletter.logic.encryption.MD5;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录控制类
 * 
 * @author 胡圣朗
 * 
 */
public class LoginAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(RegisterAction.class
			.getName()); // 日志对象;
	private AccountRelateLogic logic = new AccountRelateLogic(); // 逻辑操作类.

	//输入参数
	private String userid = "";
	private String password = "";
	private String verifycode = "";
	private boolean verifycodeflag;
	private String autologin; // 是否自動登錄； on-自动登录;
	//输出参数
	private String logininformation = "";
	private String redirectpath;
	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	
		String page;
		logger.info("开始登录");
		if (verifycodeflag == true) {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpSession session = request.getSession();
			redirectpath = (String) session.getAttribute("redirectpath");
			boolean flag = logic.loginValidate(userid, password);
			if (flag == true) { // 登录成功
				logger.info("登录成功");

				// 登录成功,在SESSION中给于授权.
				String nickname = logic.getNickname(userid);
				
				logger.info("登录获取NICKNE="+nickname);
				
				session.setAttribute("authorization", true);
				session.setAttribute("userid", userid);
				session.setAttribute("nickname", nickname);

				if (autologin != null && autologin.equals("on")) { // 如果用户要求自动登录
																	// 就写入COOKIE.
					// 写入cookie.
					String encryptPassword = MD5.MD5_Encryption(password);
					String cookieStr = userid + "==" + encryptPassword;
					Cookie cookie = new Cookie("bigcantenn_user", cookieStr);
					cookie.setMaxAge(365 * 24 * 3600);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				// 判断重定向到那个URL。
				if (redirectpath == null || redirectpath.equals("")) {
					page = SUCCESS;
				} else {
					page = "redirect";
				}
			} else { // 登录失败
				logininformation = "用戶名或者密碼輸入不正確！";
				logger.info("开始失败");
				page = INPUT;
				// 登录失败累加一次.
				Object attrObj = session.getAttribute("wrongloginNum");
				if (attrObj != null) {
					Integer wrongloginNum = (Integer) attrObj;
					wrongloginNum = wrongloginNum + 1;
					session.setAttribute("wrongloginNum", wrongloginNum);
				} else { // 第一次登录失败.
					session.setAttribute("wrongloginNum", 1);
				}
			}
		} else {
			logininformation = "驗證碼輸入不正確！";
			page = INPUT;
		}
		return page;
	}

	

	@Override
	public void validate() {
		// 效验验证码是否正确.只有2次输入失败才需要效验验证码.
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Object attrObj = session.getAttribute("wrongloginNum");
		if (attrObj != null) {
			Integer wrongloginNum = (Integer) attrObj;
			if (wrongloginNum >= 2) {
				String piccode = (String) session.getAttribute("piccode");
				verifycode = verifycode.trim();
				verifycode = verifycode.toUpperCase();
				if (verifycode.equals(piccode)) {
					verifycodeflag = true;
				} else {
					verifycodeflag = false;
				}
			} else {
				verifycodeflag = true;
			}
		} else {
			verifycodeflag = true;
		}
	}

	public String getUserid() {
		return CommonFunction.toLowerCase(userid);
	}

	public void setUserid(String userid) {
		this.userid = CommonFunction.toLowerCase(userid);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRedirectpath() {
		return redirectpath;
	}

	public void setRedirectpath(String redirectpath) {
		this.redirectpath = redirectpath;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getAutologin() {
		return autologin;
	}

	public void setAutologin(String autologin) {
		this.autologin = autologin;
	}

	public String getLogininformation() {
		return logininformation;
	}

	public void setLogininformation(String logininformation) {
		this.logininformation = logininformation;
	}

}
