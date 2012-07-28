package controller.order;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.OrderRelatedLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 判断是否达到起送价格
 * 
 * @author 胡圣朗
 * @createtime 下午07:41:46
 * 
 */
public class CheckOrderSendprice extends ActionSupport {

	private static Logger logger = Logger.getLogger(CheckOrderSendprice.class
			.getName()); // 日志对象;

	private OrderRelatedLogic logic = new OrderRelatedLogic();// 逻辑操作类.

	// 改为从COOKIE中直接获取.
	private String orderstr;// 订单信息字符串. 格式：foodid@|@price@|@foodname@|@amount
							// @|@食物个属性间分隔； @*|*@各个食物对象间的分隔;
	// 输入输出参数
	private String restaurantids;// 餐厅ID.
	private String restaurantsStr;
	private List restaurantFoodList; // JSP页面获取此对象；多個餐館已定食物对象列表.
	private List restaurantNameList;
	private List orderSummoeyList;

	public String execute() throws Exception {
	/*	logger.info("判断是否达到起送价格");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse responses = ServletActionContext.getResponse();

		// 从cookie中获取我的餐车ITEMS信息.
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals("dstCart")) { // 获取餐车ITEMS信息字符串
				orderstr = URLDecoder.decode(cookie.getValue(), "UTF-8");
			}
			if (cookie.getName().equals("dstRestName")) {
				restaurantsStr = URLDecoder.decode(cookie.getValue(), "UTF-8");
			}
		}

		// 获取已定食物对象列表
		if (orderstr != null && !orderstr.equals("")) {
			restaurantids = logic.getRestaurantids(orderstr);
			List resultListArray[] = logic.wraptoOrderFoodlist(orderstr,
					restaurantsStr);
			restaurantNameList = resultListArray[0];
			restaurantFoodList = resultListArray[1];
			orderSummoeyList = logic.getOrderSumMoney(restaurantFoodList);

			// 如果订单都达到起送价格就进入订单确认界面
			if (logic.checkisSatisfySendprice(restaurantids, orderSummoeyList)) {
				responses.getWriter().print("1"); // 满足
			} else {
				responses.getWriter().print("0"); // 不满足
			}
		} else {
			responses.getWriter().print("2"); // 用戶未選擇外賣，提升用戶選擇外賣後並且購起送價格才能生成訂單
		}*/
		return this.NONE;
	}

}
