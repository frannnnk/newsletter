package controller.mail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.mail.MailFuncion;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 邮件订阅ACTION (Ajax操作)
 * 
 * @author 胡圣朗
 * 
 */
public class MailSubscriptionAction extends ActionSupport {
	private static Logger logger = Logger
			.getLogger(MailSubscriptionAction.class.getName()); // 日志对象;
	private MailFuncion logic = new MailFuncion(); // 逻辑操作类.

	// 输入参数
	private String emailAddress;

	public String execute() throws Exception {
		logger.info("用户邮件订阅");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String accountid = (String) request.getSession().getAttribute("userid");
		// state 状态值 1-邮件订阅成功; 0-邮件已经被订阅，无需再订阅；2-邮件订阅异常.
		int state = logic.mailSubscript(accountid, emailAddress);
		response.getWriter().print(state); //回写到客户端.
		return this.NONE;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
