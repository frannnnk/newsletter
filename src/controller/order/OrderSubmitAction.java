package controller.order;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.AddressRelatedLogic;
import logic.OrderRelatedLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import pojo.OrderPojo;

import com.opensymphony.xwork2.ActionSupport;
import common.Constant;

/**
 * 订单提交控制类
 * 
 * @author 胡圣朗
 * 
 */
public class OrderSubmitAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(OrderSubmitAction.class
			.getName()); // 日志对象;
	private OrderRelatedLogic logic = new OrderRelatedLogic();// 逻辑操作类.
	private AddressRelatedLogic addresslogic = new AddressRelatedLogic(); // 地址相关逻辑操作类.

	private String diliveryaddress; // 如果传入为 "newadd" 说明是新增地址.
	private String addressid;
	// 输入参数
	private String foodids;// 多个食物ID
	private String amount; // 多个食物的数量;
	private String prices; // 多个食物的单价;

	private String pricesum;// 每家餐厅的小计金额.
	private String restaurantids;// 餐厅ID.
	private String diliveryaddressText;// 格式addressid@#@diliveryaddress
	private String dilivertime; // 要求送达时间
	private String paymentmethod; // 支付方式 1-线下支付(目前只支持);2-账户余额支付；3-网上支付;

	// 新增地址参数.
	private String totalmoney; // 总金额；
	private String aspecifialaddress; // 具体派送位置
	private String aphone;// 电话
	private String aisdefault = "on";// 是否默认 目前总是默认
	private String comments;// 备注
	private List orderpojoList;

	private String mailhostAddress; // 用户登录邮箱地址 。

	public String execute() throws Exception {
		logger.info("提交订单");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		String accountid = (String) request.getSession().getAttribute("userid");
		String areceiver = (String) request.getSession().getAttribute("nickname");
		String locationstr = (String) request.getSession().getAttribute(
				"location");// Session中保存用户位置字符串。
		if (diliveryaddressText.equals("addrNew")) { // 使用新地址操作.首先保存地址 然后保存订单.
			addresslogic.saveAddress(accountid, areceiver, aspecifialaddress,
					aphone, aisdefault);// 地址操作成功与否不影响订单操作.
			// 构造配送地址
			String newdiliveryaddress = logic.buildDiliveryAddress(areceiver,
					aspecifialaddress, aphone);
			orderpojoList = logic.submitOrderincludeSplit(foodids, amount,
					prices, pricesum, newdiliveryaddress, dilivertime,
					paymentmethod, comments, accountid, restaurantids,
					locationstr);
			//发送收到订单邮件给用户
			logic.sendOrderReceiveMail(accountid, areceiver);
		} else {
			String tmp[] = diliveryaddressText.split("@#@");
			addressid = tmp[0];
			diliveryaddress = tmp[1];

			orderpojoList = logic.submitOrderincludeSplit(foodids, amount,
					prices, pricesum, diliveryaddress, dilivertime,
					paymentmethod, comments, accountid, restaurantids,
					locationstr);
			addresslogic.setDeaultAddress(accountid, new Integer(addressid));
			//发送收到订单邮件给用户
			logic.sendOrderReceiveMail(accountid, areceiver);

			
		}

		if (orderpojoList != null && orderpojoList.size() > 0) {
			mailhostAddress = logic.getUserEmailHost(accountid);
			// 清空购物车COOKIE
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("dstCart")) { // 获取餐车ITEMS信息字符串
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				if (cookie.getName().equals("dstRestName")) {
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}

			return SUCCESS;
		} else
			return "fail";
	}

	public String getFoodids() {
		return foodids;
	}

	public void setFoodids(String foodids) {
		this.foodids = foodids;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public AddressRelatedLogic getAddresslogic() {
		return addresslogic;
	}

	public String getComments() {
		return comments;
	}

	public String getMailhostAddress() {
		return mailhostAddress;
	}

	public void setMailhostAddress(String mailhostAddress) {
		this.mailhostAddress = mailhostAddress;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setAddresslogic(AddressRelatedLogic addresslogic) {
		this.addresslogic = addresslogic;
	}

	public String getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(String totalmoney) {
		this.totalmoney = totalmoney;
	}

	public String getDiliveryaddress() {
		return diliveryaddress;
	}

	public void setDiliveryaddress(String diliveryaddress) {
		this.diliveryaddress = diliveryaddress;
	}

	public String getDiliveryaddressText() {
		return diliveryaddressText;
	}

	public void setDiliveryaddressText(String diliveryaddressText) {
		this.diliveryaddressText = diliveryaddressText;
	}

	public String getAspecifialaddress() {
		return aspecifialaddress;
	}

	public void setAspecifialaddress(String aspecifialaddress) {
		this.aspecifialaddress = aspecifialaddress;
	}

	public String getDilivertime() {
		return dilivertime;
	}

	public void setDilivertime(String dilivertime) {
		if (Constant.FASTEST.equals(dilivertime))
			this.dilivertime = "0";
		else
			this.dilivertime = dilivertime;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public String getPricesum() {
		return pricesum;
	}

	public void setPricesum(String pricesum) {
		this.pricesum = pricesum;
	}

	public String getAphone() {
		return aphone;
	}

	public void setAphone(String aphone) {
		this.aphone = aphone;
	}

	public String getAisdefault() {
		return aisdefault;
	}

	public void setAisdefault(String aisdefault) {
		this.aisdefault = aisdefault;
	}

	public String getRestaurantids() {
		return restaurantids;
	}

	public void setRestaurantids(String restaurantids) {
		this.restaurantids = restaurantids;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}

	public List getOrderpojoList() {
		return orderpojoList;
	}

	public void setOrderpojoList(List orderpojoList) {
		this.orderpojoList = orderpojoList;
	}

	public String getPrices() {
		return prices;
	}

	public void setPrices(String prices) {
		this.prices = prices;
	}

}
