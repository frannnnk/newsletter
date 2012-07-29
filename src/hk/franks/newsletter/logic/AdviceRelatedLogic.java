package hk.franks.newsletter.logic;

import hk.franks.newsletter.common.CommonFunction;
import hk.franks.newsletter.common.Constant;
import hk.franks.newsletter.dao.AdviceRelateDao;
import hk.franks.newsletter.pojo.AdvicePojo;
import hk.franks.newsletter.pojo.AdviceResponsePojo;

import java.util.List;

import org.apache.log4j.Logger;




/**
 * 建议反馈逻辑类
 * 
 * @author 胡圣朗
 * 
 */
public class AdviceRelatedLogic {
	private static Logger logger = Logger.getLogger(AdviceRelatedLogic.class
			.getName()); // 日志对象;
	private AdviceRelateDao dao = new AdviceRelateDao();

	/**
	 * 保存用户建议反馈.
	 */
	public boolean saveAdvice(String userid, String content) {
		logger.debug("保存用户建议反馈.");
		String time = CommonFunction.getcurrentTimetext();
		return dao.insertAdvice(userid, content, time);
	}

	/**
	 * 保存客服反馈回复
	 * 
	 * @return
	 */
	public boolean saveAdviceResponse(String adviceid, String responseContent) {
		logger.debug("保存客服反馈回复");
		String time = CommonFunction.getcurrentTimetext();
		return dao.insertAdviceResponse(adviceid, responseContent, time);
	}

	/**
	 * 查询建议反馈包括反馈
	 * 
	 * @param requestPageNum
	 * @return
	 */
	public List SearchAdvice(int requestPageNum) {
		int startindex = requestPageNum * Constant.ADVICE_PAGENUM
				- requestPageNum * Constant.ADVICE_PAGENUM;
		// 获取建议对象
		List adviceList = dao.searchAdvice(startindex, Constant.ADVICE_PAGENUM);
		// 获取建议反馈ID 构建字符串.
		StringBuffer adviceIds = new StringBuffer();
		for (int i = 0; i < adviceList.size(); i++) {
			AdvicePojo advicePojo = (AdvicePojo) adviceList.get(i);
			adviceIds.append(advicePojo.getId() + ",");
		}
		if (adviceIds.length() > 0)
			adviceIds.deleteCharAt(adviceIds.length() - 1);
		// 获取建议反馈集合对象
		List adviceResponseList = dao
				.searchAdviceResponse(adviceIds.toString());
		adviceList = packAdviceAndResponse(adviceList, adviceResponseList);
		return adviceList;

	}

	/**
	 * 包装意见反馈 和 意见回复
	 * 
	 * @param adviceList
	 * @param adviceResponseList
	 */
	public List packAdviceAndResponse(List adviceList, List adviceResponseList) {
		for (int i = 0; i < adviceList.size(); i++) {
			AdvicePojo advicePojo = (AdvicePojo) adviceList.get(i);
			for (int j = 0; j < adviceResponseList.size(); j++) {
				AdviceResponsePojo adviceResponsePojo = (AdviceResponsePojo) adviceResponseList
						.get(j);
				if (advicePojo.getId().equals(adviceResponsePojo.getAdviceid())) {
					advicePojo.getAdviceResponseList().add(adviceResponsePojo);
				}
			}
		}
		return adviceList;
	}

	/**
	 * 获取建议反馈总页数
	 * 
	 * @return
	 */
	public int getTotalPageNum() {
		int totalnum = dao.searchTotalNumAdvice();
		return totalnum / Constant.ADVICE_PAGENUM + 1;
	}

	/**
	 * 增加餐厅被邀请加盟数量
	 * 
	 * @param restaurantid
	 * @return
	 */
	public boolean addRestaurantAskCoop(String restaurantid) {
		boolean resultflag = false;
		String currentdate = CommonFunction.getcurrentDatetext();
		boolean flag = dao.isRestaurantAskCoopBydate(currentdate,restaurantid);
		if (flag) {
			resultflag = dao.addRestaurantAskCoop(restaurantid, currentdate);
		} else {
			resultflag = dao.insertRestaurantAskCoop(new Integer(restaurantid),
					currentdate);
		}
		return resultflag;
	}

	/**
	 * 获取被要求餐厅的记录数
	 * 
	 * @return 昨日的记录数;全部记录数
	 */
	public String getRestaurantAskCoopRecord(String restaurantid) {
		String yesterdayDate = CommonFunction.getDate(-1);
		return dao.getRestaurantAskCoopRecord(restaurantid, yesterdayDate);
	}
}
