package hk.franks.newsletter.controller.filter;

import hk.franks.newsletter.logic.mail.MailFuncion;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;

/**
 * Icon.png过滤器
 * 
 * @author Frank
 * @createtime 20111127 20:42:39
 * 
 * We are surprised to notice that when search in google we found many domain which points to our server. 
 * To fix this, we need to check the domain name when user request our website.
 * 
 */
public class IconImageFilter implements Filter {
	private static Logger logger = Logger
	.getLogger(IconImageFilter.class.getName()); // 日志对象;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();		
		

		
		String enableRdir = session.getServletContext().getInitParameter("enableIconImageRedirect");
		
		if ("true".equals(enableRdir)) {
			String paramValue = session.getServletContext().getInitParameter("iconsImageLocation");
			response.sendRedirect(paramValue);
		} else {
			chain.doFilter(req, res);
		}
	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
