package controller.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.AdviceRelatedLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户提交建议ACTION
 * 
 * @author 胡圣朗
 * 
 */
public class AdviceOfferAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(AdviceOfferAction.class
			.getName()); // 日志对象;
	private AdviceRelatedLogic logic = new AdviceRelatedLogic();

	// 输入参数;
	private String content; // 反馈内容

	public String execute() throws Exception {
		logger.info("用户提交建议ACTION content="+content);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String userid = (String) request.getSession().getAttribute("userid");
		if(userid==null)
			userid="none"; //遊客
		logic.saveAdvice(userid, content);
		String reponsemessage = null;
		return this.SUCCESS;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
