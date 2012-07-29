package hk.franks.newsletter.pojo;

/**
 * 地址信息对象简单对象类
 * 
 * @author 胡圣朗
 * 
 */
public class AddressInfoPojo {

	private String userid;
	private String addressinfoid;
	private String phone;
	private String street;
	private String location;
	private String receivername;
	private String isdefault;// 1-是；0-不是
	
	private String addressText;//显示的全部地址相关字符串.

	public String getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}

	public String getAddressText() {
		return addressText;
	}

	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAddressinfoid() {
		return addressinfoid;
	}

	public void setAddressinfoid(String addressinfoid) {
		this.addressinfoid = addressinfoid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getReceivername() {
		return receivername;
	}

	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}
	
}
