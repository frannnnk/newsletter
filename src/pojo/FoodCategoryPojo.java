package pojo;

/**
 * 食物类别
 * 
 * @author 胡圣朗
 * 
 */
public class FoodCategoryPojo {

	private String id;
	private String name;
	private String restaurantid;
	private String description;
	private String rank;
	private String flag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(String restaurantid) {
		this.restaurantid = restaurantid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

}
