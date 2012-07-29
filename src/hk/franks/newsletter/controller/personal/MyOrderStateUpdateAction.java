package hk.franks.newsletter.controller.personal;

import hk.franks.newsletter.logic.OrderRelatedLogic;
import hk.franks.newsletter.logic.ReviewFavoriteBonusLogic;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用戶订单狀態更改控制類
 * 
 * @author 胡圣朗
 * 
 */
public class MyOrderStateUpdateAction extends ActionSupport {

	private static Logger logger = Logger
			.getLogger(MyOrderStateUpdateAction.class.getName()); // 日志对象;

	private OrderRelatedLogic logic = new OrderRelatedLogic();// 逻辑操作类.

	// 输入参数
	private String changeState; // 要更改的狀態 3-派送完成;5-作废（取消订单）
	private String orderid;
	private String deleteorderid;// 需要隐藏的ORDERID.

	private int type; // today-1; history-2;

	// 输出参数
	private String message;

	public String execute() throws Exception {
		logger.info("用戶订单狀態更改控制類");
		String path = this.NONE;
		if (type == 1)
			path = "today";
		else
			path = "history";
		if (deleteorderid == null) { // 说明只是更改订单状态
			if (changeState.equals("5")) {
				if (logic.CanCancleOrnot(orderid)) {
					logic.changeOrderState(orderid, changeState);
				} else {// 这是说明产生了冲突，订单实际状态已经是确认，用户不能取消改订单了。
					message = "您的訂單已經被確認，外賣正在派送途中,不能執行取消訂單操作。如需詢問，請撥打客戶熱線。";
					// 将冲突信息保存到服务端，告知客服人员.
					String CONFLICTTYPE = "5";// 5-由取消订单导致的冲突.
					logic.orderConflictInfoSave(orderid, CONFLICTTYPE);
					path = this.ERROR;
				}
			} else {
				logic.changeOrderState(orderid, changeState);
			}
			// 更改用戶誠信值.
			HttpServletRequest request = ServletActionContext.getRequest();
			String accountid = (String) request.getSession().getAttribute(
					"userid");
			if (accountid == null || accountid.equals(""))
				accountid = logic.getUseridByOrderid(orderid);
			logic.changeCreditValue(accountid, new Integer(changeState));
		} else if (deleteorderid != null) {// 说明更改显示状态
			int NOTSHOWN = 2; // 隐藏状态值
			logic.setOrderNotshown(NOTSHOWN, deleteorderid);
		}

		return path;
	}

	public String getChangeState() {
		return changeState;
	}

	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getDeleteorderid() {
		return deleteorderid;
	}

	public void setDeleteorderid(String deleteorderid) {
		this.deleteorderid = deleteorderid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
