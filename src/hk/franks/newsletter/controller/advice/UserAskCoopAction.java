package hk.franks.newsletter.controller.advice;

import hk.franks.newsletter.logic.AdviceRelatedLogic;

import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户邀请餐厅加盟接口
 * @author 胡圣朗
 * @createtime 下午08:59:58
 *
 */
public class UserAskCoopAction  extends ActionSupport {
	private static Logger logger = Logger.getLogger(UserAskCoopAction.class
			.getName()); // 日志对象;
	private AdviceRelatedLogic logic = new AdviceRelatedLogic();

	private String restaurantid;
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		logic.addRestaurantAskCoop(restaurantid);
		String resultstr=logic.getRestaurantAskCoopRecord(restaurantid);//格式：“昨日的记录数”;"全部记录数"
		response.getWriter().print(resultstr);
		return this.NONE;
	}

	public String getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(String restaurantid) {
		this.restaurantid = restaurantid;
	}
	
	
	


}
