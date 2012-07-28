package logic.map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pojo.DeliveryScopePojo;
import pojo.myPoint;
import dao.MapRelatedDao;

/**
 * 地图逻辑类
 * 
 * @author 胡圣朗
 * 
 */
public class MapRelatedLogic {

	private static Logger logger = Logger.getLogger(MapRelatedLogic.class
			.getName()); // 日志对象;

	private MapRelatedDao dao = new MapRelatedDao();// 数据库操作类.

	/**
	 * 检测位置是否开通外卖业务
	 * 
	 * @param x_coordinate
	 * @param y_coordinate
	 * @return -1-未开通；>0-开通
	 */
	public DeliveryScopePojo checkScopeAvaliable(String x_coordinate, String y_coordinate) {
		logger.debug("检测位置是否开通外卖业务");
		StringBuffer resultScopeids = new StringBuffer();
		List delidList = new ArrayList();
		return dao.queryDeliveryScopeByCoordinate(x_coordinate,
				y_coordinate);
		
	}

	/**
	 * 谷歌地图 坐标位置解析.根据坐标获取位置字符串.
	 * 
	 * @return string[] 0-区域如红磡 1-具体位置如海滨南岸.
	 */
	public String[] googleMapGeoCoding(String x_coordinate, String y_coordinate) {
		logger.debug("解析坐标位置根据坐标获取位置名称");
		String urlStr = "http://maps.google.com/maps/api/"
				+ "geocode/json?language=zh-TW&latlng=" + x_coordinate + ","
				+ y_coordinate + "&sensor=false";
		StringBuilder tempStr = new StringBuilder();
		try {
			URL url = new URL(urlStr);
			HttpURLConnection url_con = (HttpURLConnection) url
					.openConnection();
			url_con.setRequestMethod("POST");
			url_con.setDoOutput(true);

			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();
			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,
					"utf-8"));

			while (rd.read() != -1) {
				tempStr.append(rd.readLine());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		String[] location = new String[2];
		location[0] = geocodeParseForArea(new String(tempStr));
		location[1] = geocodeParseForLoaction(new String(tempStr));

		return location;
	}

	/**
	 * 解析JSON位置字符串 获取详细位置字符串。
	 * 
	 * @param geocodeJsonStr
	 * @return 街道位置字符串比如 海滨南岸2座
	 */
	private String geocodeParseForLoaction(String geocodeJsonStr) {
		logger.debug("解析坐标位置JSON对象");
		String location = ""; // 区域
		try {
			JSONObject resultJsonObj = new JSONObject("{" + geocodeJsonStr
					+ "}");
			JSONArray geoArray = resultJsonObj.getJSONArray("results");
			JSONObject addressobj = geoArray.getJSONObject(0);
			String formatted_address = addressobj
					.getString("formatted_address");
			location = formatted_address.split("香港")[1];

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return location;
	}

	/**
	 * 解析JSON区域位置字符串 获取详细位置字符串。
	 * 
	 * @param geocodeJsonStr
	 * @return 区域位置字符串比如 红磡
	 */
	private String geocodeParseForArea(String geocodeJsonStr) {
		logger.debug("解析区域位置JSON对象");
		String areaname = ""; // 区域
		try {
			JSONObject resultJsonObj = new JSONObject("{" + geocodeJsonStr
					+ "}");
			JSONArray geoArray = resultJsonObj.getJSONArray("results");
			JSONObject geoObj = geoArray.getJSONObject(0);
			JSONArray addressarray = geoObj.getJSONArray("address_components");
			for (int i = 0; i < addressarray.length(); i++) {
				JSONObject addressobj = addressarray.getJSONObject(i);
				JSONArray typearray = addressobj.getJSONArray("types");
				String type = (typearray.getString(0).trim());
				if (type.equals("locality")) {
					areaname = addressobj.getString("long_name");
					break;
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return areaname;
	}

	/**
	 * 根据位置信息获取坐标
	 * 
	 * @param locationstr
	 * @return 街道位置字符串 String[] 0-X坐标；1-Y坐标.
	 */
	public String[] googleMapGeodeCoding(String locationstr) {
		logger.debug("解析位置根据位置名称获取坐标" + locationstr);
		try {
			locationstr = URLEncoder.encode(locationstr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		String urlStr = "http://maps.google.com/maps/api/geocode/json?language=zh-TW&address="
				+ locationstr + "&sensor=false";
		StringBuilder tempStr = new StringBuilder();
		try {
			URL url = new URL(urlStr);

			HttpURLConnection url_con = (HttpURLConnection) url
					.openConnection();
			url_con.setRequestMethod("POST");
			url_con.setDoOutput(true);

			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();
			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,
					"utf-8"));

			while (rd.read() != -1) {
				tempStr.append(rd.readLine());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {

		}

		return geocodeParseForCoordinate(new String(tempStr));
	}

	/**
	 * 解析JSON位置字符串 获取位置字符串。
	 * 
	 * @param geocodeJsonStr
	 * @return 街道位置字符串 String[] 0-X坐标；1-Y坐标.
	 */
	private String[] geocodeParseForCoordinate(String geocodeJsonStr) {
		logger.debug("解析坐标位置JSON对象");
		String location = "";
		String geoCoordinate[] = new String[2];
		try {
			JSONObject resultJsonObj = new JSONObject("{" + geocodeJsonStr
					+ "}");
			JSONArray geoArray = resultJsonObj.getJSONArray("results");
			JSONObject geoObj = geoArray.getJSONObject(0);
			JSONObject geometryObj = geoObj.getJSONObject("geometry");
			JSONObject locationObj = geometryObj.getJSONObject("location");
			String lat = locationObj.getString("lat");
			String lng = locationObj.getString("lng");
			geoCoordinate[0] = lat;
			geoCoordinate[1] = lng;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		return geoCoordinate;
	}

	/**
	 * 构造历史位置cookie.用户所使用过的地址存入cookie中.只保存最近2此用户使用过的地址.
	 * 
	 * @param cookies
	 *            服务端获取的cookie数组
	 * @param location
	 *            位置字符串
	 * @return 构建好的新cookie.
	 */
	public Cookie buildHistoryLocationCookies(Cookie cookies[], String location, String x_coordinate, String y_coordinate) {
		String cookieStr = "";
		String cookiedvalue = "";
		if (cookies != null) {
			for (Cookie coo : cookies) {
				String cookiename = coo.getName();
				if (cookiename.equals("historylocation")) {
					cookiedvalue = coo.getValue();
					try {
						cookiedvalue = URLDecoder.decode(cookiedvalue, "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						logger.error(e.getMessage());
					}
				}
			}
		}
		// cookie中让2个历史地址，格式为 location1==location2;location1是最近的历史位置.
		String locstr = location+"@"+x_coordinate+"@"+y_coordinate;
		if (!cookiedvalue.equals("")) {
			String cookievaluearray[] = cookiedvalue.split("==");
			if (!locstr.equals(cookievaluearray[0]))
				cookieStr = locstr + "==" + cookievaluearray[0];
			else
				cookieStr = cookiedvalue;

		} else {
			cookieStr = locstr;
		}

		Cookie newcookie = null;
		try {
			newcookie = new Cookie("historylocation", URLEncoder.encode(
					cookieStr, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		newcookie.setMaxAge(365 * 24 * 3600);
		newcookie.setPath("/");
		return newcookie;
	}

	/**
	 * 功能：判断点是否在多边形内 方法：求解通过该点的水平线与多边形各边的交点 结论：单边交点为奇数，成立! 参数：
	 * 返回1表示肯定在多边形内；-1肯定不在多边形内；0表示在多边形的边上； myPoint p 指定的某个点 myPoint[] points
	 * 多边形的各个顶点坐标（首末点可以不一致） int nCount 多边形定点的个数
	 * 
	 * @param p
	 *            目标点P
	 * @param points
	 *            多边形点
	 * @return 1和0在 -1不在
	 */
	private int checkIfPointWithin(myPoint p, myPoint points[]) {
		int nCount = points.length;
		boolean isBeside = false;// 记录是否在多边形的边上

		double maXX;
		double maXY;
		double minX;
		double minY;
		if (nCount > 0) {
			maXX = points[0].X;
			minX = points[0].X;
			maXY = points[0].Y;
			minY = points[0].Y;

			for (int j = 1; j < nCount; j++) {
				if (points[j].X >= maXX)
					maXX = points[j].X;
				else if (points[j].X <= minX)
					minX = points[j].X;

				if (points[j].Y >= maXY)
					maXY = points[j].Y;
				else if (points[j].Y <= minY)
					minY = points[j].Y;
			}

			if ((p.X > maXX) || (p.X < minX) || (p.Y > maXY) || (p.Y < minY))
				return -1;
		}

		int nCross = 0;

		for (int i = 0; i < nCount; i++) {
			myPoint p1 = points[i];
			myPoint p2 = points[(i + 1) % nCount];

			// 求解 Y=p.Y 与 p1p2 的交点

			if (p1.Y == p2.Y) // p1p2 与 Y=p0.Y平行
			{
				if (p.Y == p1.Y && p.X >= min(p1.X, p2.X)
						&& p.X <= maX(p1.X, p2.X)) {
					isBeside = true;
					continue;
				}
			}

			if (p.Y < min(p1.Y, p2.Y) || p.Y > maX(p1.Y, p2.Y)) // 交点在p1p2延长线上
				continue;

			// 求交点的 X 坐标
			// --------------------------------------------------------------
			double X = (double) (p.Y - p1.Y) * (double) (p2.X - p1.X)
					/ (double) (p2.Y - p1.Y) + p1.X;

			if (X > p.X)
				nCross++; // 只统计单边交点
			else if (X == p.X)
				isBeside = true;
		}

		// 判断P点是否有和其他点平行， 如果有N个nCross就减N；
		for (int i = 0; i < nCount; i++) {
			myPoint pn = points[i];
			if (p.Y == pn.Y)
				nCross--;
		}

		if (isBeside)
			return 0;// 多边形边上
		else if (nCross % 2 == 1)// 单边交点为偶数，点在多边形之外 ---
			return 1;// 多边形内

		return -1;// 多边形外

	}

	private double min(double X, double Y) {
		if (X > Y)
			return Y;
		else
			return X;
	}

	private double maX(double X, double Y) {
		if (X > Y)
			return X;
		else
			return Y;
	}

}
