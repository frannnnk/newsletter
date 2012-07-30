package hk.franks.newsletter.controller.account;

import hk.franks.newsletter.logic.AccountRelateLogic;
import hk.franks.newsletter.logic.mail.MailFuncion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class RegisterAction extends ActionSupport {

	private Logger logger = Logger.getLogger(this.getClass()); // 日志对象;
	private AccountRelateLogic logic = new AccountRelateLogic(); // 逻辑操作类.

	private String reguserid = "";
	private String regnickname = "";
	private String regpassword = "";

	private boolean validate_check_flag;// 验证标志;true-验证成功，false-验证失败;

	private String infomation = ""; // 返回JSP页面信息;
	private String redirectpath;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		String result = "";// 返回结果页面
		if (validate_check_flag == true) {// 验证成功
			logger.info("开始注册！regnickname=" + regnickname);
			boolean flag = logic.account_register(reguserid, regnickname,
					regpassword);
			if (flag == true) {
				logger.info("注册成功！");
				HttpSession session = request.getSession();
				redirectpath = (String) session.getAttribute("redirectpath");
				if (redirectpath == null || redirectpath.equals("")) {
					result = SUCCESS;
				} else {
					result = "redirect";
				}
				// 登录成功,在SESSION中给于授权.
				String nickname = logic.getNickname(reguserid);
				session.setAttribute("authorization", true);
				session.setAttribute("userid", reguserid);
				session.setAttribute("nickname", regnickname);
			} else {
				logger.info("注册失败！");
				infomation = "服务器出现异常，请稍后注册";
				result = ERROR;
			}
		} else { // 输入验证失败.
			result = INPUT;
		}
		return result;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		logger.info("注册信息验证！");
		// 判断账户是否已经存在.
		validate_check_flag = logic.checkAccountIsExist(reguserid);
		if (validate_check_flag == false)
			infomation = "此用户名已经存在";
	}

	public String getReguserid() {
		return reguserid;
	}

	public void setReguserid(String reguserid) {
		this.reguserid = reguserid;
	}

	public String getRegnickname() {
		return regnickname;
	}

	public void setRegnickname(String regnickname) {
		this.regnickname = regnickname;
	}

	public String getRegpassword() {
		return regpassword;
	}

	public void setRegpassword(String regpassword) {
		this.regpassword = regpassword;
	}

	public String getInfomation() {
		return infomation;
	}

	public String getRedirectpath() {
		return redirectpath;
	}

	public void setRedirectpath(String redirectpath) {
		this.redirectpath = redirectpath;
	}

	public void setInfomation(String infomation) {
		this.infomation = infomation;
	}

	// 下面是属性GETTER和SETTER方法.

}
