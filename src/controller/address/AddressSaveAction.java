package controller.address;

import javax.servlet.http.HttpServletRequest;

import logic.AddressRelatedLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class AddressSaveAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(AddressSaveAction.class
			.getName()); // 日志对象;
	private AddressRelatedLogic logic = new AddressRelatedLogic(); // 逻辑操作类.

	private String areceiver;// 收货人
	private String alocation;// 大区域
	private String astreet;// 地址
	private String aphone;// 电话
	private String aisdefault;// 是否默认

	public String execute() throws Exception {
		logger.info("保存地址");
		HttpServletRequest request = ServletActionContext.getRequest();

		Object auth = request.getSession().getAttribute("authorization");
		if ((auth != null) && (((Boolean) auth) == true)) { // SESSION认证还在
			String userid = (String) request.getSession()
					.getAttribute("userid");
			boolean flag = logic.saveAddress(userid, areceiver,
					alocation+"，"+astreet, aphone, aisdefault);
			if (flag == false) {
				return ERROR;
			} else
				return SUCCESS;
		} else {
			return INPUT;
		}

	}

	public String getAreceiver() {
		return areceiver;
	}

	public void setAreceiver(String areceiver) {
		this.areceiver = areceiver;
	}

	public String getAlocation() {
		return alocation;
	}

	public void setAlocation(String alocation) {
		this.alocation = alocation;
	}



	public String getAstreet() {
		return astreet;
	}

	public void setAstreet(String astreet) {
		this.astreet = astreet;
	}

	public String getAphone() {
		return aphone;
	}

	public void setAphone(String aphone) {
		this.aphone = aphone;
	}

	public String getAisdefault() {
		return aisdefault;
	}

	public void setAisdefault(String aisdefault) {
		this.aisdefault = aisdefault;
	}

}
