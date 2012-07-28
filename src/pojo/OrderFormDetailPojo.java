package pojo;

/**
 * 订单明细简单对象类
 * 
 * @author 胡圣朗
 * 
 */
public class OrderFormDetailPojo {

	private String foodid;
	private String amount;
	private String price; // 下单时食物单价;

	public String getFoodid() {
		return foodid;
	}

	public void setFoodid(String foodid) {
		this.foodid = foodid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
