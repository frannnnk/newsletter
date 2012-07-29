package hk.franks.newsletter.pojo;

import hk.franks.newsletter.common.CommonFunction;

import java.util.Calendar;


/**
 * 餐厅对象类
 * 
 * @author 胡圣朗
 * 
 */
public class RestaurantPojo {

	private String id;
	private String name;
	private String send_price; // 起送价
	private String description;
	private String leaguetime; // 加盟时间
	private String arrivetime; // 预计送餐所需时间
	private String shophour; // 营业时间
	private String address; // 餐馆地址
	private String picture_url;
	private String flag; // 餐馆是否启用
	private String ishot;// 是否热门餐厅
	private String phone;// 餐厅电话

	private String x_coordinate;
	private String y_coordinate;
	private String distance; // 距离当前用户所在位置的距离.
	private String diliveryfee;// 外賣配送費.

	private String iscooperation;// 是否是加盟， 未加盟不能网上下单 只提供餐厅电话。

	private String isclosed;// 是否已经打烊 1-打烊了;0-没有打烊.
	
	private String rank; //排序

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIshot() {
		return ishot;
	}

	public void setIshot(String ishot) {
		this.ishot = ishot;
	}

	public String getDiliveryfee() {
		return diliveryfee;
	}

	public String getIscooperation() {
		return iscooperation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setIscooperation(String iscooperation) {
		this.iscooperation = iscooperation;
	}

	public void setDiliveryfee(String diliveryfee) {
		this.diliveryfee = diliveryfee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSend_price() {
		return send_price;
	}

	public void setSend_price(String send_price) {
		this.send_price = send_price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLeaguetime() {
		return leaguetime;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public void setLeaguetime(String leaguetime) {
		this.leaguetime = leaguetime;
	}

	public String getArrivetime() {
		return arrivetime;
	}

	public void setArrivetime(String arrivetime) {
		this.arrivetime = arrivetime;
	}

	public String getShophour() {
		return shophour;
	}

	public void setShophour(String shophour) {
		this.shophour = shophour;
		String shopehours[] = shophour.split("-");
		String closeTime = shopehours[1];
		if (CommonFunction.isCurrentTimeBeforTime(closeTime))
			this.setIsclosed("0");
		else
			this.setIsclosed("1");

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getX_coordinate() {
		return x_coordinate;
	}

	public void setX_coordinate(String x_coordinate) {
		this.x_coordinate = x_coordinate;
	}

	public String getY_coordinate() {
		return y_coordinate;
	}

	public void setY_coordinate(String y_coordinate) {
		this.y_coordinate = y_coordinate;
	}

	public String getIsclosed() {
		return isclosed;
	}

	public void setIsclosed(String isclosed) {
		this.isclosed = isclosed;
	}

}
