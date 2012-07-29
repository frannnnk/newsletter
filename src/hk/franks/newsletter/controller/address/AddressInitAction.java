package hk.franks.newsletter.controller.address;

import hk.franks.newsletter.logic.AddressRelatedLogic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 收获地址初始化
 * 
 * @author 胡圣朗
 * 
 */
public class AddressInitAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(AddressInitAction.class
			.getName()); // 日志对象;
	private AddressRelatedLogic logic = new AddressRelatedLogic(); //逻辑操作类.

	//输出参数
	List addressinfoList; //用户以保存地址列表.
	
	
	@Override
	public String execute() throws Exception {
		logger.info("初始化地址管理界面");
		HttpServletRequest request = ServletActionContext.getRequest();
		String userid = (String) request.getSession()
		.getAttribute("userid");
		addressinfoList = logic.getAddress(userid);
		request.setAttribute("addressinfoList", addressinfoList);

		return SUCCESS;
	}


	public List getAddressinfoList() {
		return addressinfoList;
	}


	public void setAddressinfoList(List addressinfoList) {
		this.addressinfoList = addressinfoList;
	}
	
}
