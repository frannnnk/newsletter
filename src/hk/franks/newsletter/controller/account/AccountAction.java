package hk.franks.newsletter.controller.account;

import java.util.Date;

import hk.franks.newsletter.common.CommonFunction;
import hk.franks.newsletter.controller.utils.CommonUtil;
import hk.franks.newsletter.controller.utils.ConstantUtil;
import hk.franks.newsletter.logic.AccountRelateLogic;
import hk.franks.newsletter.logic.encryption.MD5;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.AjaxReturnMessageModel;
import model.UsersModel;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录控制类
 * 
 * @author 胡圣朗
 * 
 */
public class AccountAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(RegisterAction.class
			.getName()); // 日志对象;
	private AccountRelateLogic logic = new AccountRelateLogic(); // 逻辑操作类.
	

	//输入参数
	private String userid = "";
	private String encryptedPassword = "";
	
	//输出参数
	private String logininformation = "";
	private String redirectpath;
	
	private String action;
	

	@Override
	public String execute() throws Exception {
		

		// Login, also get redirect page
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		
		
		redirectpath = request.getParameter("return");
		action = request.getParameter("action");
		
		if ("login".equalsIgnoreCase(action)) {
			
			AjaxReturnMessageModel returnMsg = new AjaxReturnMessageModel();
			returnMsg.setAction("login");
			
			//do login logic
			logger.debug("Ready to execute Login logic.");
			
			userid = request.getParameter("userid");
			encryptedPassword = request.getParameter("encryptedPassword");
			
			logger.debug("Processing User Login: "+userid+" "+encryptedPassword);
			
			UsersModel user = logic.loginValidate(userid, encryptedPassword);
			
			if (CommonUtil.isExNull(user)) {
				returnMsg.setResult(ConstantUtil.RESULT_FAILED);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute(ConstantUtil.SESSION_LOGIN_USER, user);
				returnMsg.setResult(ConstantUtil.RESULT_SUCCEED);
			}
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String json = gson.toJson(returnMsg);
			
			response.getWriter().print(json);
			
			return null;
		
		} else if("register".equalsIgnoreCase(action)) {
			
			
			String areaOfInterest = request.getParameter("aoi");
			String email = request.getParameter("email");
			String language = request.getParameter("language");
			String location = request.getParameter("location");
			String screenName = request.getParameter("screenname");
			String gender = request.getParameter("gender");
			
			
			// check email
			
			if (!logic.checkAccountIsExist(email)) {
				logger.debug("Account "+email +" already exist.");
				
				AjaxReturnMessageModel returnMsg = new AjaxReturnMessageModel();
				returnMsg.setAction("register");
				returnMsg.setResult(ConstantUtil.RESULT_FAILED);
				returnMsg.setMessage("Email '"+email+"' was already taken, please choose another email.");
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				String json = gson.toJson(returnMsg);
				response.getWriter().print(json);
				
				return null;
			}
			
			// save db
			
			UsersModel user = new UsersModel();
			user.setAreaOfInterest(areaOfInterest);
			user.setEmail(email);
			user.setCreateDate(new Date());
			user.setModifyDate(new Date());
			//users.setLastLogin(new Date());
			user.setStatus(ConstantUtil.USER_STATUS_PENDING_APPROVAL);
			user.setScreenName(screenName);
			user.setLanguage(language);
			user.setLocation(location);
			user.setGender(gender);
			user.setUserRole(ConstantUtil.USER_ROLE_COMMON_USER);
			user.setPassword("N/A");
			user.setCreateUser("SELF");
			
			if ( logic.register(user) ) {
				logger.debug("User register succeed.");
				
				
				AjaxReturnMessageModel returnMsg = new AjaxReturnMessageModel();
				returnMsg.setAction("register");
				returnMsg.setResult(ConstantUtil.RESULT_SUCCEED);
				returnMsg.setMessage("Thank you.");
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				String json = gson.toJson(returnMsg);
				response.getWriter().print(json);
				return null;
				
				
				
			} else {
				logger.debug("User register failed.");
				
				
				AjaxReturnMessageModel returnMsg = new AjaxReturnMessageModel();
				returnMsg.setAction("register");
				returnMsg.setResult(ConstantUtil.RESULT_FAILED);
				returnMsg.setMessage("System Busy, Please try again later.");
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				String json = gson.toJson(returnMsg);
				response.getWriter().print(json);
				return null;
			}
			
		}else {
			return null;
		}
		
	}

	

	public String getLogininformation() {
		return logininformation;
	}

	public void setLogininformation(String logininformation) {
		this.logininformation = logininformation;
	}

}
