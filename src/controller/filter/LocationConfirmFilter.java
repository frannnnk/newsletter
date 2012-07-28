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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 位置确认过滤器， 避免在没有指定位置情况下进入需要位置信息页面.
 * 
 * @author 胡圣朗
 * 
 */
public class LocationConfirmFilter implements Filter {
	private static Logger logger = Logger.getLogger(LocationConfirmFilter.class
			.getName()); // 日志对象;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info("位置确认过滤器");
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String locationstr = request.getParameter("locationstr");// 如果有帶這個參數的可以跳過此攔截.
		if (locationstr == null || locationstr.equals("")) { 
			String path = request.getContextPath();
			HttpSession session = request.getSession();
			String areaid = (String) session.getAttribute("areaid");
			if (areaid == null || areaid.equals("-1")) { // 位置未被指定.
				response.sendRedirect(path + "/index.jsp");
			} else {
				chain.doFilter(arg0, arg1);
			}
		}else
			chain.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
