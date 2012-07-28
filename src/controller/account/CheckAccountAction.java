package controller.account;

import javax.servlet.http.HttpServletResponse;

import logic.AccountRelateLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class CheckAccountAction extends ActionSupport{
	
	private static Logger logger = Logger.getLogger(CheckAccountAction.class
			.getName()); // 日志对象;
	private AccountRelateLogic logic = new AccountRelateLogic(); // 逻辑操作类.
	
	private String userId;

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		logger.info("检测用户是否已存在");
		boolean flag=logic.checkAccountIsExist(userId);
		String result =null; //0-不存在，1-存在
		if(flag){
			logger.info("用户不存在可以注册");
			result="0";
		}else{
			logger.info("用户存在");
			result="1";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.getWriter().write(result);
		return null;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
