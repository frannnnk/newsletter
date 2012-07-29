package hk.franks.newsletter.controller.infonotice;

import hk.franks.newsletter.controller.mail.MailSubscriptionAction;
import hk.franks.newsletter.logic.InfoNoticeLogic;

import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 评论订单播报ACTION
 * 
 * @author 胡圣朗
 * @createtime 下午02:47:22
 * 
 */
public class ReviewOrderBroadcastAction extends ActionSupport {
	private static Logger logger = Logger
			.getLogger(ReviewOrderBroadcastAction.class.getName()); // 日志对象;

	private InfoNoticeLogic logic = new InfoNoticeLogic(); // 逻辑操作类.

	// 输入参数;
	int type; // 1-获取评论播报；2-获取订单播报.

	public String execute() throws Exception {
		logger.info(" 评论订单播报控制类");
		HttpServletResponse response = ServletActionContext.getResponse();
		String resultJSONText = null;
		
		if (type == 1)
			resultJSONText = logic.getReviewBroadcastJsonText();
		else if (type == 2)
			resultJSONText = logic.getOrderBroadcastJsonText();
		
		response.getWriter().print(resultJSONText);
		return this.NONE;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
