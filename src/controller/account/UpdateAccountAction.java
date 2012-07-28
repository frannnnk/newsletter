package controller.account;

import javax.servlet.http.HttpServletRequest;

import logic.AccountRelateLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 更新账户信息 如密码，昵称.
 * 
 * @author 胡圣朗
 * 
 */
public class UpdateAccountAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(UpdateAccountAction.class
			.getName()); // 日志对象;
	private AccountRelateLogic logic = new AccountRelateLogic(); // 逻辑操作类.

	// 输入参数.
	private String oldpassword="";
	private String newpassword="";
	private String confrimnewpassword="";

	// 输出参数.
	private String message;

	public String execute() throws Exception {
		logger.info("更新账户信息");
		if(newpassword.equals(confrimnewpassword)){
		HttpServletRequest request = ServletActionContext.getRequest();
		String userid = (String) request.getSession().getAttribute("userid");
		boolean flag = logic.loginValidate(userid, oldpassword);
		if (flag) {// 验证成功
			message = "更新成功";
			logic.updatePasswrod(userid, newpassword);
		} else {
			message = "原始密碼輸入不正確";
		}
		}else{
			message = "2次输入新密碼不一致";
		}

		return SUCCESS;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getConfrimnewpassword() {
		return confrimnewpassword;
	}

	public void setConfrimnewpassword(String confrimnewpassword) {
		this.confrimnewpassword = confrimnewpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
