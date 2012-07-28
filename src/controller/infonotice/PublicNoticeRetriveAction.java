package controller.infonotice;

import javax.servlet.http.HttpServletResponse;

import logic.InfoNoticeLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import controller.mail.MailSubscriptionAction;

/**
 * 公告信息获取控制类
 * 
 * @author 胡圣朗
 * @createtime 下午03:02:51
 * 
 */
public class PublicNoticeRetriveAction extends ActionSupport {
	private static Logger logger = Logger
			.getLogger(PublicNoticeRetriveAction.class.getName()); // 日志对象;

	private InfoNoticeLogic logic = new InfoNoticeLogic(); // 逻辑操作类.

	public String execute() throws Exception {
		logger.info("公告信息获取控制类");
		HttpServletResponse response = ServletActionContext.getResponse();
		String publicJsonText = logic.getPublicnoticeJsonText();
		response.getWriter().print(publicJsonText);
		return this.NONE;
	}

}
