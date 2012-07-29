package hk.franks.newsletter.pojo;

import hk.franks.newsletter.common.Constant;

import java.util.ArrayList;
import java.util.List;


/**
 * 订单基本信息POJO
 * 
 * @author 胡圣朗
 * 
 */
public class OrderPojo {

	private String orderid;
	private String summoney;
	private String address;
	private String paymethod; // 支付方式 1-线下支付;2-账户余额支付；3-网上支付;
	private String paymethodText;// 支付方式文字显示 .
	private String restaurantname;
	private String orderstate;// 订单状态; 订单状态1-
								// 待确定；2-确认（准备派送）；3-派送完成；4-派送失败（可能由于顾客地址异常）；5-作废（取消订单，但数据库中不删除）
	private String orderstateText;// 订单状态文字显示.
	private String restaurantid;
	private String createtime;// 下单时间.
	private String orderdetailText = "";// 订单明细字符串.
	private String orderComments;

	private String restaurantPhone;
	private String restaurantEmail;

	private String creditValue; // 0-客服电话确认；1-自动邮件确认
	private String confirmMethod;// 确认方式，有creditValue的set方法生成.
	private String userphone; // 用户电话

	private String usernickname;// 用户昵称.
	private int bonus = 5; // 积分.
	private String broadcastFoodnameText; // 食物名称.

	private String locationstr;// 用户下订单所在位置字符串.

	private List<OrderDetailPojo> orderDetailList = new ArrayList();
	
	private String dilivertime;

	public String getLocationstr() {
		return locationstr;
	}

	public void setLocationstr(String locationstr) {
		this.locationstr = locationstr;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getOrderdetailText() {
		return orderdetailText;
	}

	public List getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public String getUsernickname() {
		return usernickname;
	}

	public void setUsernickname(String usernickname) {
		this.usernickname = usernickname;
	}

	public String getRestaurantPhone() {
		return restaurantPhone;
	}

	public String getConfirmMethod() {
		return confirmMethod;
	}

	public void setConfirmMethod(String confirmMethod) {
		this.confirmMethod = confirmMethod;
	}

	public String getOrderComments() {
		return orderComments;
	}

	public void setOrderComments(String orderComments) {
		this.orderComments = orderComments;
	}

	public void setRestaurantPhone(String restaurantPhone) {
		this.restaurantPhone = restaurantPhone;
	}

	public String getRestaurantEmail() {
		return restaurantEmail;
	}

	public void setRestaurantEmail(String restaurantEmail) {
		this.restaurantEmail = restaurantEmail;
	}

	public void setOrderdetailText(String orderdetailText) {
		this.orderdetailText = orderdetailText;
	}

	public String getPaymethodText() {
		return paymethodText;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public void setPaymethodText(String paymethodText) {
		this.paymethodText = paymethodText;
	}

	public String getOrderstateText() {
		return orderstateText;
	}

	public void setOrderstateText(String orderstateText) {
		this.orderstateText = orderstateText;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
		if (paymethod.trim().equals("1")) {// 支付方式 1-线下支付;2-账户余额支付；3-网上支付;
			this.paymethodText = "餐到付款";
		} else if (paymethod.trim().equals("2")) {
			this.paymethodText = "账户余额支付";
		} else if (paymethod.trim().equals("3")) {
			this.paymethodText = "网上支付";
		}
	}

	public String getOrderid() {
		return orderid;
	}

	public String getBroadcastFoodnameText() {
		return broadcastFoodnameText;
	}

	public void setBroadcastFoodnameText(String broadcastFoodnameText) {
		this.broadcastFoodnameText = broadcastFoodnameText;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getSummoney() {
		return summoney;
	}

	public void setSummoney(String summoney) {
		this.summoney = summoney;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRestaurantname() {
		return restaurantname;
	}

	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}

	public String getOrderstate() {
		return orderstate;
	}

	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
		if (orderstate.equals("1")) {// 订单状态; 订单状态1- 處理中；2-製作派送中；3-已經送達；4-未送達；5-取消訂單
			this.orderstateText = "處理中";
		} else if (orderstate.trim().equals("2")) {
			this.orderstateText = "製作派送中";
		} else if (orderstate.trim().equals("3")) {
			this.orderstateText = "成功送達";
		} else if (orderstate.trim().equals("4")) {
			this.orderstateText = "送達失敗";
		} else if (orderstate.trim().equals("5")) {
			this.orderstateText = "已取消";
		} else if (orderstate.trim().equals("6")) {
			this.orderstateText = "已評價";
		}
	}

	public String getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(String restaurantid) {
		this.restaurantid = restaurantid;
	}

	public boolean equals(Object obj) {
		OrderPojo orderObj = (OrderPojo) obj;
		if (orderObj.getOrderid().equals(this.orderid))
			return true;
		else
			return false;
	}

	public String getCreditValue() {
		return creditValue;
	}

	public void setCreditValue(String creditValue) {
		this.creditValue = creditValue;
		if (creditValue.equals("0")) {
			this.setConfirmMethod("電話確認");
		} else if (creditValue.equals("1")) {
			if (new Double(this.getSummoney()) > Constant.MONEYLIMIT) {
				this.setConfirmMethod("電話確認");
				this.creditValue = "0";
			} else
				this.setConfirmMethod("系統郵件確認");
		}
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getDilivertime() {
		return dilivertime;
	}
	public void setDilivertime(String dilivertime) {
		if ("0".equals(dilivertime))
			this.dilivertime = Constant.FASTEST;
		else
			this.dilivertime = dilivertime;
	}

}
