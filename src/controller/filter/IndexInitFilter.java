package controller.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.CoreInfoDisplayLogic;
import logic.map.MapRelatedLogic;

import org.apache.log4j.Logger;

/**
 * 首页数据初始化过滤器
 * 
 * @author 胡圣朗
 * 
 */
public class IndexInitFilter implements Filter {

	private static Logger logger = Logger.getLogger(IndexInitFilter.class
			.getName()); // 日志对象;
	private MapRelatedLogic maplogic = new MapRelatedLogic();
	private CoreInfoDisplayLogic coreIndoDisplaylogic = new CoreInfoDisplayLogic();

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info("首页数据初始化");
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		Cookie[] cookies = request.getCookies();
		pageCookieRelatedDataInit(cookies, request);
		chain.doFilter(arg0, arg1);

	}

	/**
	 * 页面和cookie相关的数据初始化
	 * 
	 * @throws UnsupportedEncodingException
	 */
	private void pageCookieRelatedDataInit(Cookie[] cookies,
			HttpServletRequest request) throws UnsupportedEncodingException {
		logger.info("过滤器初始化首页相关数据");
		String[] cooks = null;
		ArrayList<String> locations = new ArrayList();
		String[] localInfo = null;
		ArrayList<String> x_cor = new ArrayList();
		ArrayList<String> y_cor = new ArrayList();

		List hotRestaurantList = null;
		String hotRestauratStr = null;
		if (cookies != null) {
			for (Cookie coo : cookies) {
				String domainname = coo.getName();
				if (domainname.equals("historylocation")) {
					String cookieValue = coo.getValue();
					cookieValue = URLDecoder.decode(cookieValue, "utf-8");
					localInfo = cookieValue.split("==");
				}
			}
		}
		try {
			// 获取最近一次使用的位置 热门餐厅推荐列表.
			if (localInfo != null) {
				// 地址1
				String localinfo1 = localInfo[0];
				String localinfo1array[] = localinfo1.split("@");
				locations.add(localinfo1array[0]);
				x_cor.add(localinfo1array[1]);
				y_cor.add(localinfo1array[2]);
				// 地址2
				if (localInfo.length > 1) {
					String localinfo2 = localInfo[1];
					String localinfo2array[] = localinfo2.split("@");
					locations.add(localinfo2array[0]);
					x_cor.add(localinfo2array[1]);
					y_cor.add(localinfo2array[2]);
				}

				// 把历史位置放入request对象中.
				if (locations.size() > 0) {
					request.setAttribute("historylocation1", locations.get(0));
					request.setAttribute("x1_cor", x_cor.get(0));
					request.setAttribute("y1_cor", y_cor.get(0));
				}
				if (locations.size() > 1) {
					request.setAttribute("historylocation2", locations.get(1));
					request.setAttribute("x2_cor", x_cor.get(1));
					request.setAttribute("y2_cor", y_cor.get(1));
				}
				request.setAttribute("flag", "true"); // flag is true that
														// stands
														// for there is at least
														// one
														// historylocation.
			} else {
				request.setAttribute("flag", "false");
			}
		} catch (Exception e) {
			request.setAttribute("flag", "false");
			logger.error(e.getMessage());
			logger.error("localInfo:= " + localInfo);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
