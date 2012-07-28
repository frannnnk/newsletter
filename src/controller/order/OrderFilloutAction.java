package controller.order;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import logic.OrderRelatedLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 填写订单控制类
 * 
 * @author 胡圣朗
 * 
 */
public class OrderFilloutAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(OrderFilloutAction.class
			.getName()); // 日志对象;

	private OrderRelatedLogic logic = new OrderRelatedLogic();// 逻辑操作类.

	// 改为从COOKIE中直接获取.
	private String orderstr;// 订单信息字符串. 格式：foodid@|@price@|@foodname@|@amount
							// @|@食物个属性间分隔； @*|*@各个食物对象间的分隔;
	private String restaurantsStr;
	// 输入输出参数
	private String restaurantids;// 餐厅ID.

	// 输出参数
	private String foodids; // 这个也要再次输出 一個餐館不同外賣用逗号隔开，不同餐館 用分號隔開;
	private String amount; // 数量; 这个也要再次输出 一個餐館不同外賣用逗号隔开，不同餐館 用分號隔開;
	private String prices; // 每个外卖的单价.
	private String pricesum;// 每一個餐館的小計金額;

	private List restaurantFoodList;

	private List orderSummoeyList;
	private String totalmoney;
	private boolean isnewAddress; // 是否为新地址.
	private String message;

	private List thefastDeliveryTimeList;// 最早派送時間列表.
	private List addressinfoList;
	private int restaurantnum;

	private int isAlter = 0; // 1-有;0-无;
	private int ismulti = 0; // 0-no,1-yes; is order more than one?
	private int resnum = 1; // the number of restaurant

	public String execute() throws Exception {
		logger.info("填写订单");
		String path = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		isnewAddress = true;
		// 从cookie中获取我的餐车ITEMS信息.
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals("dstCart")) { // 获取餐车ITEMS信息字符串
				orderstr = URLDecoder.decode(cookie.getValue(), "UTF-8");
			}

		}
		if (!(orderstr == null || "".equals(orderstr))) {
			// 获取已定食物对象列表
			List[] resultList = logic.wraptoOrderFoodlist(orderstr);
			restaurantFoodList = resultList[1];
			orderSummoeyList = logic.getOrderSumMoney(restaurantFoodList);
			// if ordersummoneylist is more than one, then there are more than
			// one order.
			if (orderSummoeyList.size() > 1) {
				ismulti = 1;
				resnum = orderSummoeyList.size();
			}
			totalmoney = logic.getTotalMoney(restaurantFoodList);
			restaurantids = logic.getRestaurantids(orderstr); // restaurants id.

			String result[] = logic
					.buildFoodidsAndAmountsText(restaurantFoodList);

			foodids = result[0];
			amount = result[1];
			prices = result[2];
			pricesum = logic.getpriceSumStr(orderSummoeyList);

			// 获取送餐时间范围列表，如果是一次订多加餐厅的，派送时间范围取最小的那家餐厅为准.
			thefastDeliveryTimeList = logic
					.getDeliveryTimeList();

			// 如果是登录用户获取用户物流信息.
			Object auth = request.getSession().getAttribute("authorization");
			if (auth != null) {
				boolean authFlag = (Boolean) auth;
				String userid = (String) request.getSession().getAttribute(
						"userid");
				if ((authFlag == true) && (userid != null)) { // 如果登录获取用户物流信息.
					this.addressinfoList = logic.getAddress(userid);
					if (addressinfoList.size() > 0) {
						isnewAddress = false;
					}
				}
			}
			path = this.SUCCESS;
		} else
			path = this.INPUT;
		return path;
	}

	public int getRestaurantnum() {
		return restaurantnum;
	}

	public int getIsAlter() {
		return isAlter;
	}

	public void setIsAlter(int isAlter) {
		this.isAlter = isAlter;
	}

	public void setRestaurantnum(int restaurantnum) {
		this.restaurantnum = restaurantnum;
	}

	public void setFoodids(String foodids) {
		this.foodids = foodids;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List getThefastDeliveryTimeList() {
		return thefastDeliveryTimeList;
	}

	public void setThefastDeliveryTimeList(List thefastDeliveryTimeList) {
		this.thefastDeliveryTimeList = thefastDeliveryTimeList;
	}

	public boolean isIsnewAddress() {
		return isnewAddress;
	}

	public List getOrderSummoeyList() {
		return orderSummoeyList;
	}

	public void setOrderSummoeyList(List orderSummoeyList) {
		this.orderSummoeyList = orderSummoeyList;
	}

	public void setIsnewAddress(boolean isnewAddress) {
		this.isnewAddress = isnewAddress;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(String totalmoney) {
		this.totalmoney = totalmoney;
	}

	public String getRestaurantids() {
		return restaurantids;
	}

	public void setRestaurantids(String restaurantids) {
		this.restaurantids = restaurantids;
	}

	public String getPricesum() {
		return pricesum;
	}

	public void setPricesum(String pricesum) {
		this.pricesum = pricesum;
	}

	public String getPrices() {
		return prices;
	}

	public void setPrices(String prices) {
		this.prices = prices;
	}

	public String getOrderstr() {
		return orderstr;
	}

	public void setOrderstr(String orderstr) {
		this.orderstr = orderstr;
	}

	public String getRestaurantsStr() {
		return restaurantsStr;
	}

	public void setRestaurantsStr(String restaurantsStr) {
		this.restaurantsStr = restaurantsStr;
	}

	public List getAddressinfoList() {
		return addressinfoList;
	}

	public void setAddressinfoList(List addressinfoList) {
		this.addressinfoList = addressinfoList;
	}

	public String getFoodids() {
		return foodids;
	}

	public List getRestaurantFoodList() {
		return restaurantFoodList;
	}

	public void setRestaurantFoodList(List restaurantFoodList) {
		this.restaurantFoodList = restaurantFoodList;
	}

	public int getIsmulti() {
		return ismulti;
	}

	public void setIsmulti(int ismulti) {
		this.ismulti = ismulti;
	}

	public int getResnum() {
		return resnum;
	}

	public void setResnum(int resnum) {
		this.resnum = resnum;
	}

}
