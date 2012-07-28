package controller.backmanagement;

import logic.backmanagement.ManagementLogic;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 食物信息修改
 * @author 胡圣朗
 * @createtime 下午01:27:33
 *
 */
public class FoodUpdateManagementAction extends ActionSupport {
	private static Logger logger = Logger
			.getLogger(FoodUpdateManagementAction.class.getName()); // 日志对象;
	private ManagementLogic logic = new ManagementLogic();


	public String execute() throws Exception {
		
		return this.NONE;
	}
}
