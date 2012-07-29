package hk.franks.newsletter.controller.main;

import hk.franks.newsletter.logic.CoreInfoDisplayLogic;
import hk.franks.newsletter.pojo.FoodCategoryPojo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * online restaurant action
 * 
 * @author Joe Hu
 * @date 2012-2-29
 * @version 1.0
 */
public class OnlineRestauAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(OnlineRestauAction.class
			.getName()); // 日志对象;
	private CoreInfoDisplayLogic logic = new CoreInfoDisplayLogic(); // 逻辑操作类.

	// below is outter paramters

	int numberoftab; // 餐牌的數量。

	List<String> categoryNameList1;
	List<FoodCategoryPojo> categoryPojoList1;
	List<List> SortFoodListCollection1;

	List<String> categoryNameList2;
	List<FoodCategoryPojo> categoryPojoList2;
	List<List> SortFoodListCollection2;

	List<String> categoryNameList3;
	List<FoodCategoryPojo> categoryPojoList3;
	List<List> SortFoodListCollection3;

	List<String> categoryNameList4;
	List<FoodCategoryPojo> categoryPojoList4;
	List<List> SortFoodListCollection4;

	public String execute() throws Exception {
		logger.info("首页数据初始化");
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		if (session.containsKey("areaid")) {
			String areaid = (String) session.get("areaid");
			List<List> restaurantsFoodList = logic.getAreaFoodMenu(areaid);
			numberoftab = restaurantsFoodList.size();
			for (int i = 0; i < restaurantsFoodList.size(); i++) {
				List resultList = (List) restaurantsFoodList.get(i);
				if (i == 0) {
					categoryNameList1 = (List) resultList.get(0);
					SortFoodListCollection1 = (List) resultList.get(1);
					categoryPojoList1 = (List) resultList.get(2);
				} else if (i == 1) {
					categoryNameList2 = (List) resultList.get(0);
					SortFoodListCollection2 = (List) resultList.get(1);
					categoryPojoList2 = (List) resultList.get(2);
				} else if (i == 2) {
					categoryNameList3 = (List) resultList.get(0);
					SortFoodListCollection3 = (List) resultList.get(1);
					categoryPojoList3 = (List) resultList.get(2);
				} else if (i == 3) {
					categoryNameList4 = (List) resultList.get(0);
					SortFoodListCollection4 = (List) resultList.get(1);
					categoryPojoList4 = (List) resultList.get(2);
				}
			}

			return this.SUCCESS;
		} else
			return this.INPUT;
	}

	// below is getter and setter method.

	public List<String> getCategoryNameList1() {
		return categoryNameList1;
	}

	public void setCategoryNameList1(List<String> categoryNameList1) {
		this.categoryNameList1 = categoryNameList1;
	}

	public List<FoodCategoryPojo> getCategoryPojoList1() {
		return categoryPojoList1;
	}

	public void setCategoryPojoList1(List<FoodCategoryPojo> categoryPojoList1) {
		this.categoryPojoList1 = categoryPojoList1;
	}

	public List<List> getSortFoodListCollection1() {
		return SortFoodListCollection1;
	}

	public void setSortFoodListCollection1(List<List> sortFoodListCollection1) {
		SortFoodListCollection1 = sortFoodListCollection1;
	}

	public List<String> getCategoryNameList2() {
		return categoryNameList2;
	}

	public void setCategoryNameList2(List<String> categoryNameList2) {
		this.categoryNameList2 = categoryNameList2;
	}

	public List<FoodCategoryPojo> getCategoryPojoList2() {
		return categoryPojoList2;
	}

	public void setCategoryPojoList2(List<FoodCategoryPojo> categoryPojoList2) {
		this.categoryPojoList2 = categoryPojoList2;
	}

	public List<List> getSortFoodListCollection2() {
		return SortFoodListCollection2;
	}

	public void setSortFoodListCollection2(List<List> sortFoodListCollection2) {
		SortFoodListCollection2 = sortFoodListCollection2;
	}

	public List<String> getCategoryNameList3() {
		return categoryNameList3;
	}

	public void setCategoryNameList3(List<String> categoryNameList3) {
		this.categoryNameList3 = categoryNameList3;
	}

	public List<FoodCategoryPojo> getCategoryPojoList3() {
		return categoryPojoList3;
	}

	public void setCategoryPojoList3(List<FoodCategoryPojo> categoryPojoList3) {
		this.categoryPojoList3 = categoryPojoList3;
	}

	public List<List> getSortFoodListCollection3() {
		return SortFoodListCollection3;
	}

	public void setSortFoodListCollection3(List<List> sortFoodListCollection3) {
		SortFoodListCollection3 = sortFoodListCollection3;
	}

	public List<String> getCategoryNameList4() {
		return categoryNameList4;
	}

	public void setCategoryNameList4(List<String> categoryNameList4) {
		this.categoryNameList4 = categoryNameList4;
	}

	public List<FoodCategoryPojo> getCategoryPojoList4() {
		return categoryPojoList4;
	}

	public void setCategoryPojoList4(List<FoodCategoryPojo> categoryPojoList4) {
		this.categoryPojoList4 = categoryPojoList4;
	}

	public List<List> getSortFoodListCollection4() {
		return SortFoodListCollection4;
	}

	public void setSortFoodListCollection4(List<List> sortFoodListCollection4) {
		SortFoodListCollection4 = sortFoodListCollection4;
	}

	public int getNumberoftab() {
		return numberoftab;
	}

	public void setNumberoftab(int numberoftab) {
		this.numberoftab = numberoftab;
	}

}
