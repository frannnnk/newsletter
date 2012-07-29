package hk.franks.newsletter.logic;

import hk.franks.newsletter.dao.AddressRelatedDao;

import java.util.List;

import org.apache.log4j.Logger;


/**
 * 地址管理相关逻辑类
 * 
 * @author 胡圣朗
 * 
 */
public class AddressRelatedLogic {
	private static Logger logger = Logger.getLogger(AddressRelatedLogic.class
			.getName()); // 日志对象;

	private AddressRelatedDao dao = new AddressRelatedDao();// 数据库操作类.

	/**
	 * 获取地址
	 * 
	 * @param userid
	 *            用户ID.
	 * @return
	 */
	public List getAddress(String userid) {
		logger.debug("获取地址");
		return dao.getaddressinfo(userid);
	}

	/**
	 * 保存地址
	 * 
	 * @param userid
	 * @param receivername
	 * @param area
	 *            //所在区域 比如红磡
	 * @param street
	 *            //具体位置 比如海滨南岸.
	 * @param phone
	 * @param isdefault
	 *            on-要设为默认；其他-不设为默认
	 * @return
	 */
	public boolean saveAddress(String userid, String receivername,
			String aspecifialaddress, String phone, String isdefault) {
		logger.debug("保存地址");
		boolean flag = false;
		int addressid;
		String address = receivername + "| " + aspecifialaddress
				+ "| " + phone;
		addressid = dao.saveAddress(userid, address);
		if (isdefault.equals("on") && addressid != -1) {// 有设置默认值，调用默认设置方法.此调用不用和保存地址在一个事务中，换句话说就是即使默认保存失败也不会影响地址的保存。
			flag = true;
			dao.setdefaultaddress(userid, addressid);
		}
		return flag;
	}

	/**
	 * 设置默认地址
	 * 
	 * @param addressid
	 * @return 是否成功
	 */
	public boolean setDeaultAddress(String userid, int addressid) {
		logger.debug("设置默认地址");
		boolean flag;
		flag = dao.setdefaultaddress(userid, addressid);
		return flag;
	}

	/**
	 * 删除地址ID
	 * 
	 * @param addressid
	 *            地址ID.
	 * @return 是否成功
	 */
	public boolean deleteAddress(int addressid) {
		logger.debug("删除地址");
		boolean flag;
		flag = dao.deleteAddress(addressid);
		return flag;
	}

	/**
	 * 更新地址
	 * 
	 * @param addressid
	 * @param addressText
	 * @return
	 */
	public boolean updateAddress(int addressid, String addressText) {
		logger.debug("更新地址");
		boolean flag;
		flag = dao.updateAddress(addressid, addressText);
		return flag;
	}

}
