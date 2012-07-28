package logic.encryption;

import java.security.MessageDigest;

import logic.AccountRelateLogic;

import org.apache.log4j.Logger;

/**
 * MD5加密类.
 * @author 胡圣朗
 *
 */
public class MD5 {
	private static Logger logger = Logger.getLogger(MD5.class
			.getName()); // 日志对象;
	/**
	 * MD5不可逆加密算法
	 * 
	 * @param s
	 *            要加密的字符串
	 * @return 加密后的字符串，如果是null则加密失败.
	 */
	public final static String MD5_Encryption(String s) {
		logger.debug("加密开始");
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			logger.debug("加密成功结束");
			return new String(str);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

}
