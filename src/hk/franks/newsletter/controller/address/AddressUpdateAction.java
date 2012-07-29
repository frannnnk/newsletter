package hk.franks.newsletter.controller.address;

import hk.franks.newsletter.logic.AddressRelatedLogic;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class AddressUpdateAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(AddressUpdateAction.class
			.getName()); // 日志对象;
	private AddressRelatedLogic logic = new AddressRelatedLogic(); // 逻辑操作类.

	// 输入参数
	private int addressid;
	private String addressText;

	public String execute() throws Exception {
		logger.info("更改地址");
		boolean flag = logic.updateAddress(addressid, addressText);
		if (flag)
			return SUCCESS;
		else
			return INPUT;
	}

	public int getAddressid() {
		return addressid;
	}

	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}

	public String getAddressText() {
		return addressText;
	}

	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}

}
