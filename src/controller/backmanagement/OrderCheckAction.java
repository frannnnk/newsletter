package controller.backmanagement;

import javax.servlet.http.HttpServletResponse;

import logic.OrderRelatedLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * this is a ajax action
 * 
 * @author Joe Hu
 * @date 2012-4-16
 * @version 1.0
 */
public class OrderCheckAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(OrdermanagementAction.class
			.getName()); // 日志对象;

	private OrderRelatedLogic logic = new OrderRelatedLogic();// 逻辑操作类.

	public String execute() throws Exception {
		logger.info("Order check action!");
		String amount = logic.checkNewOrderCount();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.getWriter().write(amount);
		logger.info("还有"+amount+"张订单!");
		return null;
	}
}
