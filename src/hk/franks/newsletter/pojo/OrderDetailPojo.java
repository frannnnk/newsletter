package hk.franks.newsletter.pojo;

public class OrderDetailPojo {

	private String orderdetailid;
	private String orderid;
	private String foodid;
	private String foodname;
	private String foodamount;
	private String foodorderprice;
	private String resname;
	private String resid;
	private String resphone;

	public String getOrderdetailid() {
		return orderdetailid;
	}

	public void setOrderdetailid(String orderdetailid) {
		this.orderdetailid = orderdetailid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
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

	public String getResname() {
		return resname;
	}

	public void setResname(String resname) {
		this.resname = resname;
	}

	public String getResid() {
		return resid;
	}

	public void setResid(String resid) {
		this.resid = resid;
	}

	public String getResphone() {
		return resphone;
	}

	public void setResphone(String resphone) {
		this.resphone = resphone;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public String getFoodamount() {
		return foodamount;
	}

	public void setFoodamount(String foodamount) {
		this.foodamount = foodamount;
	}

	public String getFoodorderprice() {
		return foodorderprice;
	}

	public void setFoodorderprice(String foodorderprice) {
		this.foodorderprice = foodorderprice;
	}

}
