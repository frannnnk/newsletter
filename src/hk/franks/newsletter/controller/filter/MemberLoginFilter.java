package hk.franks.newsletter.controller.filter;

import hk.franks.newsletter.controller.utils.CommonUtil;
import hk.franks.newsletter.controller.utils.ConstantUtil;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UsersModel;

import org.apache.log4j.Logger;

public class MemberLoginFilter implements Filter {
	private static Logger logger = Logger
			.getLogger(MemberLoginFilter.class.getName()); // 日志对象;

	public void destroy() {

	}

	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse reponse = (HttpServletResponse) sresponse;

		String path = request.getRequestURI();
		
		
		UsersModel user = (UsersModel)request.getSession().getAttribute(ConstantUtil.SESSION_LOGIN_USER);
		
		if (!CommonUtil.isExNull(user)) {
			chain.doFilter(srequest, sresponse);
		} else {
			logger.info("User not login, direct to login page.");
			request.getSession().setAttribute(ConstantUtil.FILTER_REDIRECT_PATH, path);
			((HttpServletResponse) sresponse).sendRedirect(request
					.getContextPath() + "/login.jsp?m=100");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
