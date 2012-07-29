package hk.franks.newsletter.controller.account;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 登出系统
 * 
 * @author 胡圣朗
 * 
 */
public class LogoutAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(LogoutAction.class
			.getName()); // 日志对象;

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		session.invalidate();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) { // 退出登录删除此COOKIE.
			for (Cookie coo : cookies) {
				String cookiename = coo.getName();
				if (cookiename.equals("bigcantenn_user")) {
					Cookie cookie = new Cookie("bigcantenn_user",null);
					cookie.setMaxAge(0);
					cookie.setPath("/"); 
					response.addCookie(cookie);
				}
			}

		}
		return SUCCESS;
	}

}
