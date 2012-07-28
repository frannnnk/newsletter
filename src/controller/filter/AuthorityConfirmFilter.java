package controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 我的易食飯的权限过滤器
 * 
 * @author 胡圣朗
 * 
 */
public class AuthorityConfirmFilter implements Filter {
	private static Logger logger = Logger
			.getLogger(AuthorityConfirmFilter.class.getName()); // 日志对象;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info("易食飯用户操作权限");
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse reponse = (HttpServletResponse) sresponse;

		String path = request.getRequestURI();
		String tmppatharray[] = path.split("canteen/");
		if(tmppatharray.length>1)
			path=tmppatharray[1];
		Object authorization = request.getSession().getAttribute(
				"authorization");
		if ((authorization != null) && (((Boolean) authorization) == true)) {
			chain.doFilter(srequest, sresponse);
			logger.info("权限效验合法");
		} else {
			logger.info("权限效验不合法,请先登录!");
			request.getSession().setAttribute("redirectpath", path);
			((HttpServletResponse) sresponse).sendRedirect(request
					.getContextPath() + "/account/login.jsp");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
