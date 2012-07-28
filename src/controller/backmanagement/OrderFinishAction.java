package controller.backmanagement;

import javax.servlet.http.HttpServletResponse;

import logic.OrderRelatedLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * make all order state finished.
 * 
 * @author Joe Hu
 * @date 2012-4-20
 * @version 1.0
 */
public class OrderFinishAction {

	private static Logger logger = Logger.getLogger(OrderFinishAction.class
			.getName()); // 日志对象;

	private OrderRelatedLogic logic = new OrderRelatedLogic();// 逻辑操作类.

	public String execute() throws Exception {
		logger.info("OrderFinishAction()");
		boolean flag = logic.finishOrder();
		String result = "-1";// 成功;
		if (flag)
			result = "0";// 成功;
		else
			result = "1";// 失败
		HttpServletResponse response = ServletActionContext.getResponse();
		response.getWriter().write(result);
		return null;
	}
}
