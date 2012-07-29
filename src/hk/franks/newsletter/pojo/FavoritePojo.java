package hk.franks.newsletter.pojo;

/**
 * 我的收藏表
 * 
 * @author 胡圣朗
 * 
 */
public class FavoritePojo {
	private String id;
	private String time;
	private String userid;
	private String restaurantid;
	private String restaurantName;
	private String locationstr;

	public String getLocationstr() {
		return locationstr;
	}

	public void setLocationstr(String locationstr) {
		this.locationstr = locationstr;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(String restaurantid) {
		this.restaurantid = restaurantid;
	}

}
