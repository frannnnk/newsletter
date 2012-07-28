package controller.address;

import logic.AddressRelatedLogic;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 删除地址
 * 
 * @author 胡圣朗
 * 
 */
public class AddressDeleteAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(AddressSaveAction.class
			.getName()); // 日志对象;
	private AddressRelatedLogic logic = new AddressRelatedLogic(); // 逻辑操作类.

	private String addressid;// 地址ID.

	@Override
	public String execute() throws Exception {
		logger.info("删除地址,ID为"+addressid);
		logic.deleteAddress(new Integer(addressid));
		return SUCCESS;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}
	
	
}
