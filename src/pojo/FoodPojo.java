package pojo;

import common.CommonFunction;

/**
 * 外卖食物对象类（弃用）
 * 
 * @author 胡圣朗
 * 
 */
public class FoodPojo {

	private String foodid;
	private String foodname;
	private String price;
	private String diliver_time;
	private String description;
	private String picture_url;
	private String flag;
	private String restaurant_id;
	private String restaurantname; // 所属餐厅名称.
	private String is_recommend;
	private String foodcategory_name; // 食物类别名
	private String foodcategory_id; // 食物类别ID
	private String foodcategory_description;

	private String food_isclosed;// 1-打烊;0-未打烊 该外卖所在的餐厅是否打烊，如果是，该外卖不能被用户点。

	public String getRestaurantname() {
		return restaurantname;
	}

	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}

	public String getFoodcategory_name() {
		return foodcategory_name;
	}

	public void setFoodcategory_name(String foodcategory_name) {
		this.foodcategory_name = foodcategory_name;
	}

	public String getFoodcategory_id() {
		return foodcategory_id;
	}

	public String getFoodcategory_description() {
		return foodcategory_description;
	}

	public void setFoodcategory_description(String foodcategory_description) {
		this.foodcategory_description = foodcategory_description;
	}

	public void setFoodcategory_id(String foodcategory_id) {
		this.foodcategory_id = foodcategory_id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getIs_recommend() {
		return is_recommend;
	}

	public void setIs_recommend(String is_recommend) {
		this.is_recommend = is_recommend;
	}

	public String getFoodid() {
		return foodid;
	}

	public void setFoodid(String foodid) {
		this.foodid = foodid;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiliver_time() {
		return diliver_time;
	}

	public void setDiliver_time(String diliver_time) {
		this.diliver_time = diliver_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	public String getFood_isclosed() {
		return food_isclosed;
	}

	public void setFood_isclosed(String shophour) {
		String shopehours[] = shophour.split("-");
		String closeTime = shopehours[1];
		if (CommonFunction.isCurrentTimeBeforTime(closeTime))
			this.food_isclosed = "0";
		else
			this.food_isclosed = "1";
	}

}
