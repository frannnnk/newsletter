package hk.franks.newsletter.controller.utils;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Random;
import java.util.StringTokenizer;




public class CommonUtil {
	
	public static String StringToWebUnicode(String input) {  
			String str = "";  
			for (char c : input.toCharArray()) {  
				str += "&#" + (int) c + ";";  
				}  
			        return str;  
	}  
	
	public static String string2Unicode(String s) {
		    try {
		      StringBuffer out = new StringBuffer("");
		      byte[] bytes = s.getBytes("unicode");
		      for (int i = 2; i < bytes.length - 1; i += 2) {
		    	  
		        out.append("&#x");
		        String str = Integer.toHexString(bytes[i + 1] & 0xff);
		        for (int j = str.length(); j < 2; j++) {
		          out.append("0");
		        }
		        String str1 = Integer.toHexString(bytes[i] & 0xff);
		        out.append(str);
		        out.append(str1);
		        out.append("; ");
		      }
		      return out.toString().toLowerCase();
		    }
		    catch (UnsupportedEncodingException e) {
		      e.printStackTrace();
		      return null;
		    }
		  } 
		
	
	public static String buildRandomKey(int pwd_len) {
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0',
				'1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString().toUpperCase();

	}
	
	public static void Debug(String msg){
		System.out.println("##DEBUG:"+" "+TimeUtil.getDay(new Date(), "yyyy-MM-dd HH:mm:ss.SSS")+" "+msg);
	}
	public static Double roundDouble(double val, int precision) {  
        Double ret = null;  
        try {  
            double factor = Math.pow(10, precision);  
            ret = Math.floor(val * factor + 0.5) / factor;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return ret;  
    }  
	
	public static boolean isNull(Object obj) {
		if (obj == null || "".equals(obj.toString())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isExNull(Object obj) {
		if (isNull(obj)) {
			return true;
		}

		if (obj instanceof String) {
			String str = (String) obj;
			if ("".equals(str.trim())) {
				return true;
			}
		} else if (obj instanceof Collection) {
			Collection c = (Collection) obj;
			if (c.isEmpty()) {
				return true;
			}
		} else if (obj instanceof Map) {
			Map map = (Map) obj;
			if (map.isEmpty()) {
				return true;
			}
		} else if (obj instanceof Iterator) {
			Iterator iter = (Iterator) obj;
			if (!iter.hasNext()) {
				return true;
			}
		} else if (obj instanceof Enumeration) {
			Enumeration em = (Enumeration) obj;
			if (!em.hasMoreElements()) {
				return true;
			}
		} else if (obj.getClass().isArray()) {
			Object[] objArr = (Object[]) obj;
			if (objArr.length <= 0) {
				return true;
			}
		} else {
			if ("".equals(obj.toString().trim())) {
				return true;
			}
		}
		return false;
	}
	
	public static String filteLastThing(String src, String seperateChar){
		
		if (CommonUtil.isExNull(src)) {
			return src;
		}
		
		if (!src.endsWith(seperateChar)) {
			return src;
		}
		
		while (src.endsWith(seperateChar)) {
			src = src.substring(0,src.length()-1);
		}
		
		return src;
		
		
		
		
		
	}
	

	
public static String getCircleCount(Date startDate){
		
		int diff = TimeUtil.getDateDif(startDate, new Date());
		//1天后，2天后，5天后，8天后，14天后, 20天后
		if (diff == 0) {
			return "1";
		} else if (diff == 1) {
			return "2";
		} else if (diff == 2) {
			return "3";
		} else if (diff == 5) {
			return "4";
		} else if (diff == 8) {
			return "5";
		} else if (diff == 14) {
			return "6";
		} else if (diff == 20) {
			return "7";
		}  else {
			return "N/A";
		}		
		
	}

public static String getCircleCount(Date startDate, Date toDate){
	
	int diff = TimeUtil.getDateDif(startDate, toDate);
	//1天后，2天后，5天后，8天后，14天后, 20天后
	if (diff == 0) {
		return "1";
	} else if (diff == 1) {
		return "2";
	} else if (diff == 2) {
		return "3";
	} else if (diff == 5) {
		return "4";
	} else if (diff == 8) {
		return "5";
	} else if (diff == 14) {
		return "6";
	} else if (diff == 20) {
		return "7";
	}  else {
		return "N/A";
	}		
	
}
		
	public static void getCaller() { 
	    int i; 
	    StackTraceElement stack[] = (new Throwable()).getStackTrace(); 
	    for (i=0; i < stack.length; i++) { 
	      StackTraceElement ste=stack[i]; 
	   //   System.out.println(ste.getClassName()+"."+ste.getMethodName()+"(...)"); 
	      
	      String wording = "";
	      String wording1 = "";
	      
	      if (i==0) {
	    	  wording = "first,";
	    	  wording1= "i was invoked by";
	      } else if (i == stack.length-1) {
	    	  wording = "lastly,";
	    	  wording1="end up at";
	      } else {
	    	  wording = "then,";
	    	  wording1="trace up, invoked by";
	      }
	      
	      System.out.println(wording+wording1+"method " + ste.getMethodName() + " located at "+ste.getFileName()+" Line "+ste.getLineNumber()+",which is the "+(i+1)+" time of the invoking circle.");

	    } 
	    System.out.println();
	}

	public static String[] splitStringToStringArray(String str, String token) {
		if (isExNull(str)) {
			return null;
		}
		if (isExNull(token)) {
			return new String[] { str };
		} else {
			StringTokenizer stk = new StringTokenizer(str, token);
			String[] sa = new String[stk.countTokens()];
			int i = 0;
			while (stk.hasMoreTokens()) {
				sa[i++] = stk.nextToken();
			}
			return sa;
		}
	}
	
	public static Object nvl(Object o1, Object o2 ) {
		if ( o1 == null) {
			return o2;
		} else {
			return o1;
		}
	}

	// i = 1 odd number ;i = 0 even number.
	public static String removeSpace(String var, int i) {
		int radix = (i == 1) ? 1 : 2;

		if (!isExNull(var)) {
			StringBuffer temp = new StringBuffer();
			for (int j = 0; j < var.length() / 2 + 1; j++) {
				temp.append(var.substring(radix + j * 2 - 1, radix + j * 2));
			}
			return temp.toString();
		}
		return null;
	}

	public static String translateSymbol(String var, String symbol1,
			String symbol2) {
		var = translateSymbol(var, symbol1);
		var = translateSymbol(var, symbol2);
		return var;
	}

	public static String translateSymbol(String var, String symbol) {
		if (isExNull(var))
			return null;
		if ("^^".equals(symbol))
			return var.replaceAll(symbol, "\t");
		else if ("@@".equals(symbol))
			return var.replaceAll(symbol, " ");
		else
			return var;
	}


	
	public static void main(String[] args) {
       
	}

	/**
	 * Sort List by ascending order
	 * @param selectItemList
	 * @return
	 */
	public static List sortListByAscending(List selectItemList) {
		
		if (CommonUtil.isExNull(selectItemList)) {
			return new ArrayList();
		}
		
		return null;
		
		/*

		try {

			List list = new ArrayList();
			Map map = new HashMap();

			for (int i = 0; i < selectItemList.size(); i++) {
				SelectItem selectItem = (SelectItem) selectItemList.get(i);
				if (!CommonUtil.isExNull(selectItem)) {
					String lable = selectItem.getLabel();
					if (!CommonUtil.isExNull(lable)) {

						char[] chars = lable.trim().toCharArray();
						char c = chars[0];
						String id = StringUtils.buildRandomKey(10);
						list.add(new MyChar(c, id));
						map.put(id, selectItem);
					} else {
						char c = 0;
						String id = StringUtils.buildRandomKey(10);
						list.add(new MyChar(c, id));
						map.put(id, selectItem);
					}

				}

			}

			if (!CommonUtil.isExNull(list)) {
				Collections.sort(list);
				List resultList = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					MyChar myChar = (MyChar)list.get(i);
					String id = myChar.getId();
					SelectItem selectItem = (SelectItem)map.get(id);
					resultList.add(selectItem);
				}
				
				return resultList;

			} else {
				return new ArrayList();
			}

		} catch (Exception e) {
			return new ArrayList();
		}

	*/
		
	}
	
	

	public static String subStr(String var, int beginIndex, int endIndex) {
		if (!isExNull(var)) {
			// System.out.println("var.length = " + var.length());
			// System.out.println("var content = " + var);
			if (var.length() <= 2)
				return null;
			else
				return var.substring(beginIndex, endIndex).trim();
		}
		return null;
	}

	public static String moneyDigitProcessor(String originalDigit) {
		Double d = null;
		try {
			d = Double.parseDouble(originalDigit);
		} catch (Exception e) {
			return "0.00";
		}
		DecimalFormat df = new DecimalFormat("#,###,###.##");
		String str1 = df.format(d);
		if (str1.indexOf(".") == -1) {
			str1 += ".00";
		} else if (str1.split("\\.")[1].length() != 2) {
			str1 += "0";
		}

		return str1;

	}


	private static void superPrint(String content) {
		for (int i = 0; i < 11; i++) {
			System.out.println();
			if (i == 5) {
				System.out
						.println("=================================SUPPER PRINT BEGIN======================================");
				System.out.println();
				System.out.println(content);
				System.out.println();
				System.out
						.println("==================================SUPPER PRINT END=======================================");
			}
		}
	}

	public static String getNewRandomPassword() {
		int i = (int) (Math.random() * Math.pow(10, 6));

		char chArr[] = new char[6];
		Arrays.fill(chArr, '0');
		return new DecimalFormat(String.valueOf(chArr)).format(i);
	}

	public static String getDateFormatStr(Date date) {
		SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
		if (date == null) {
			return "";
		} else {
			return formate.format(date);
		}

	}

	/**
	 * This method will judge if it's necessary to close the connection and
	 * session. if auto commit was set to false, which means the current process
	 * is running in a transaction train, I will not close the connection.
	 * 
	 * However if auto commit was set to false, I will close the connection and
	 * roll back the current transaction as default.
	 * 
	 * @param java.sql.Connection;
	 */
	public static void connExceptionHandler(Connection conn) {
		if (CommonUtil.isExNull(conn)) {
			return;
		}
		try {
			if (conn.getAutoCommit()) {
				//conn.rollback();
				//2009-07-15 Franky
				conn.close();
			} else {
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * I will judge if it's necessary to close the connection and session. if
	 * auto commit was set to false, which means the current process is running
	 * in a transaction train, I will not close the connection.
	 * 
	 * However if auto commit was set to false, I will close the connection and
	 * roll back the current transaction as default.
	 * 
	 * @param java.sql.Connection;PreparedStatement
	 *            stmt
	 */
	public static void connExceptionHandler(Connection conn,
			PreparedStatement stmt) {
		if (!CommonUtil.isExNull(stmt)) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (CommonUtil.isExNull(conn)) {
			return;
		}
		try {
			if (conn.getAutoCommit()) {
				//conn.rollback();
				//2009-07-15 Franky
				conn.close();
			} else {
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

class MyChar implements Comparable {
	private char ch;
	private String id;

	public MyChar(char ch, String id) {
		this.ch = ch;
		this.id = id;
	}

	public String toString() {
		return Character.toString(this.ch);
	}

	public int compareTo(Object other) {
		if (other instanceof MyChar) {
			if (Character.toUpperCase(this.ch) == Character
					.toUpperCase(((MyChar) other).ch)) {
				return this.ch - ((MyChar) other).ch;
			} else {
				return Character.toUpperCase(this.ch)
						- Character.toUpperCase(((MyChar) other).ch);
			}
		}
		return 1;
	}

	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		this.ch = ch;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
