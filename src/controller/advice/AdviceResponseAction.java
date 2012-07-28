package controller.advice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.AdviceRelatedLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 客服反馈ACTION
 * 
 * @author 胡圣朗
 * 
 */
public class AdviceResponseAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(AdviceResponseAction.class
			.getName()); // 日志对象;
	private AdviceRelatedLogic logic = new AdviceRelatedLogic();

	// 输入参数;
	private String responsecontent; // 客服回复内容
	private String adviceid;
	private int requestPagenum; // 请求页码;
	private int type;// 0-管理员查询;1-先回复在查询
	// 输入输出参数.
	private int totalPagenum; // 总页数;
	private List adviceList;

	public String execute() throws Exception {
		logger.info("客服反馈ACTION");
		//回复
		if (type == 1)
			logic.saveAdviceResponse(adviceid, responsecontent);
		// 查询
		adviceList = logic.SearchAdvice(requestPagenum);
		totalPagenum = logic.getTotalPageNum();
		return this.SUCCESS;
	}

	public int getRequestPagenum() {
		return requestPagenum;
	}

	public void setRequestPagenum(int requestPagenum) {
		this.requestPagenum = requestPagenum;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTotalPagenum() {
		return totalPagenum;
	}

	public void setTotalPagenum(int totalPagenum) {
		this.totalPagenum = totalPagenum;
	}

	public List getAdviceList() {
		return adviceList;
	}

	public void setAdviceList(List adviceList) {
		this.adviceList = adviceList;
	}

	public String getResponsecontent() {
		return responsecontent;
	}

	public void setResponsecontent(String responsecontent) {
		this.responsecontent = responsecontent;
	}

	public String getAdviceid() {
		return adviceid;
	}

	public void setAdviceid(String adviceid) {
		this.adviceid = adviceid;
	}

}
