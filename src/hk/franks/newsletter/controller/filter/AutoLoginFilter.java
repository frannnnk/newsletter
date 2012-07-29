package hk.franks.newsletter.controller.filter;

import hk.franks.newsletter.logic.AccountRelateLogic;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;

/**
 * 自动登录
 * 
 * @author 胡圣朗
 * 
 */
public class AutoLoginFilter implements Filter {

	private AccountRelateLogic logic = new AccountRelateLogic(); // 逻辑操作类.
	private static Logger logger = Logger.getLogger(AutoLoginFilter.class
			.getName()); // 日志对象;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		Object authObj = session.getAttribute("authorization");
		if (authObj == null||(((Boolean)authObj)==false)) {
			logger.info("自动登录");
			Cookie[] cookies = request.getCookies();
			String[] cooks = null;
			String userid = null;
			String password = null;
			if (cookies != null) {
				for (Cookie coo : cookies) {
					String domainname = coo.getName();
					if (domainname.equals("bigcantenn_user")) {
						String cookievalue = coo.getValue();
						cooks = cookievalue.split("==");
						if (cooks.length == 2) {
							userid = cooks[0];
							password = cooks[1];
						}
						boolean flag = logic.loginValidatebyEncryptpassword(
								userid, password);
						if (flag) {
							logger.info("Login success");
							String nickname = logic.getNickname(userid);
							session.setAttribute("authorization", true);
							session.setAttribute("userid", userid);
							session.setAttribute("nickname", nickname);
						}
					}
				}
			}
		} else {
			logger.info("已经登录");
		}
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
