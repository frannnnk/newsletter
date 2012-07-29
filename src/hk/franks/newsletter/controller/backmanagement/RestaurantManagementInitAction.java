package hk.franks.newsletter.controller.backmanagement;

import hk.franks.newsletter.logic.backmanagement.ManagementLogic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 餐馆管理控制类(用于后台管理)
 * 
 * @author 胡圣朗
 * 
 */
public class RestaurantManagementInitAction extends ActionSupport {
	private static Logger logger = Logger
			.getLogger(RestaurantManagementInitAction.class.getName()); // 日志对象;
	private ManagementLogic logic = new ManagementLogic();

	public String execute() throws Exception {
		logger.info("餐馆管理");
		String message;
		List list = logic.getDeliverScopeList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("deliverscopelist", list);
		return this.SUCCESS;
	}
}
