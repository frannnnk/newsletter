package controller.personal;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import logic.OrderRelatedLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 订单查询
 * 
 * @author 胡圣朗
 * 
 */
public class MyOrderSearchAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(MyOrderSearchAction.class
			.getName()); // 日志对象;

	private OrderRelatedLogic logic = new OrderRelatedLogic();// 逻辑操作类.

	// 输入参数
	private int type=1; // today-1; history-2;
	private String requestpagenum="1";// 请求的页面页码数.

	// 输出参数
	private List todayOrderList;// 当日订单列表.
	private List historyOrderList;// 历史订单列表.

	private int currentpagenum;
	private int totalPagenum; // 总页数

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		String accountid = (String) request.getSession().getAttribute("userid");
		String path = this.NONE;
		int todayType = 1;
		int historyType = 2;
		currentpagenum=new Integer(requestpagenum);
		if (type == todayType) {
			todayOrderList = logic.searchOrder(todayType, accountid,
					new Integer(requestpagenum));
			totalPagenum = logic.getTotalOrderPageNum(todayType, accountid);
			path = "today";
		} else if (type == historyType) {
			historyOrderList = logic.searchOrder(historyType, accountid,
					new Integer(requestpagenum));
			totalPagenum = logic.getTotalOrderPageNum(historyType, accountid);
			path = "history";
		}
		return path;
	}

	public List getTodayOrderList() {
		return todayOrderList;
	}

	public void setTodayOrderList(List todayOrderList) {
		this.todayOrderList = todayOrderList;
	}

	public List getHistoryOrderList() {
		return historyOrderList;
	}

	public void setHistoryOrderList(List historyOrderList) {
		this.historyOrderList = historyOrderList;
	}

	public int getType() {
		return type;
	}

	public int getCurrentpagenum() {
		return currentpagenum;
	}

	public void setCurrentpagenum(int currentpagenum) {
		this.currentpagenum = currentpagenum;
	}

	public int getTotalPagenum() {
		return totalPagenum;
	}

	public void setTotalPagenum(int totalPagenum) {
		this.totalPagenum = totalPagenum;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRequestpagenum() {
		return requestpagenum;
	}

	public void setRequestpagenum(String requestpagenum) {
		this.requestpagenum = requestpagenum;
	}

}
