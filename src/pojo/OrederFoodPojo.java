package pojo;

/**
 * 订单食物POJO
 * 
 * @author 胡圣朗
 * 
 */
public class OrederFoodPojo {

	String foodid;
	String foodname;
	String price; // 单价
	String amount;
	String moneysum; // 小计= 单价*数量
	String resId; // restaurant id.

	public String getMoneysum() {
		return moneysum;
	}

	public void setMoneysum(String moneysum) {
		this.moneysum = moneysum;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

}
