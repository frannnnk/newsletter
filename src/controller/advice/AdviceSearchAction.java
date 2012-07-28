package controller.advice;

import java.util.List;

import logic.AdviceRelatedLogic;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 查询建议反馈
 * 
 * @author 胡圣朗
 * 
 */
public class AdviceSearchAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(AdviceResponseAction.class
			.getName()); // 日志对象;
	private AdviceRelatedLogic logic = new AdviceRelatedLogic();

	// 输入.
	private int requestPagenum; // 请求页码;
	// 输入输出参数.
	private int totalPagenum; // 总页数;
	private List adviceList;

	public String execute() throws Exception {
		logger.info("查询建议反馈");
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

}
