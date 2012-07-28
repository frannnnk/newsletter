package controller.backmanagement;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.OrderRelatedLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 订单管理控制类(包括：订单查询【包括模糊查询】，订单明显查询，订单状态更改【如：确认，派送完成，作废等】，)
 * 
 * @author 胡圣朗
 * 
 */
public class OrdermanagementAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(OrdermanagementAction.class
			.getName()); // 日志对象;

	private OrderRelatedLogic logic = new OrderRelatedLogic();// 逻辑操作类.

	// 输入参数
	private int type; // 1-查询;2-改变状态;
	private int orderstate; // 订单状态1-
							// 待确定；2-确认（准备派送）；3-派送完成；4-派送失败（可能由于顾客地址异常）；5-作废（取消订单，但数据库中不删除）
	private String orderid;// 订单ID， 用户更改订单状态 。

	// 分页输入参数
	private int requestpagenum;// 要请求的页码.

	// 输出参数
	private List orderList;// 订单列表.
	private String message;

	// 分页输出参数
	private int currentpagenum;// 当前页码
	private int totalpagenum;// 总页数

	public String execute() throws Exception {
		logger.info("订单管理操作");
		String path = this.SUCCESS;
		if (type == 2) {// 改变状态 //Ajax方式操作.
			int changeState = orderstate;
			boolean flag = logic.changeOrderState(orderid, new Integer(
					changeState).toString());
			// 操作後導向訂單管理界面.
			if (changeState == 3 || changeState == 4) {
				orderstate = 2;
			} else if (changeState == 5 || changeState == 2) {
				orderstate = 1;
			}
			String accountid = logic.getUseridByOrderid(orderid);
			logic.changeCreditValue(accountid, changeState);
			path = this.NONE;
			HttpServletResponse response = ServletActionContext.getResponse();
			response.getWriter().print(1);
		} else {// 订单管理查询
			orderList = logic.searchOrderBystate(orderstate, requestpagenum);
			totalpagenum = logic.getOrderTotalpageBystate(orderstate);
			currentpagenum = requestpagenum;

			if (orderstate == 1) {
				message = "待确认";
			} else if (orderstate == 2) {
				message = "已确认";
			} else if (orderstate == 3) {
				message = "派送完成";
			} else if (orderstate == 4) {
				message = "派送失败";
			} else if (orderstate == 5) {
				message = "作废";
			}
		}
		return path;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getOrderstate() {
		return orderstate;
	}

	public void setOrderstate(int orderstate) {
		this.orderstate = orderstate;
	}

	public List getOrderList() {
		return orderList;
	}

	public int getRequestpagenum() {
		return requestpagenum;
	}

	public void setRequestpagenum(int requestpagenum) {
		this.requestpagenum = requestpagenum;
	}

	public int getCurrentpagenum() {
		return currentpagenum;
	}

	public void setCurrentpagenum(int currentpagenum) {
		this.currentpagenum = currentpagenum;
	}

	public int getTotalpagenum() {
		return totalpagenum;
	}

	public void setTotalpagenum(int totalpagenum) {
		this.totalpagenum = totalpagenum;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setOrderList(List orderList) {
		this.orderList = orderList;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

}
