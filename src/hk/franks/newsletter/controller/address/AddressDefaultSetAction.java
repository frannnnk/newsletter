package hk.franks.newsletter.controller.address;

import hk.franks.newsletter.logic.AddressRelatedLogic;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 设置为默认地址
 * 
 * @author 胡圣朗
 * 
 */
public class AddressDefaultSetAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(AddressSaveAction.class
			.getName()); // 日志对象;
	private AddressRelatedLogic logic = new AddressRelatedLogic(); // 逻辑操作类.

	private String addressid;// 要设为默认的地址ID。

	@Override
	public String execute() throws Exception {
		logger.info("设置默认地址");
		HttpServletRequest request = ServletActionContext.getRequest();
		String accountid = (String) request.getSession().getAttribute("userid");
		logic.setDeaultAddress(accountid,new Integer(addressid));
		return SUCCESS;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}
	
}
