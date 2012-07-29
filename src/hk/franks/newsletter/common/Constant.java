package hk.franks.newsletter.common;

/**
 * 常量文件
 * @author 胡圣朗
 *
 */
public interface Constant {

	int RESTAURANT_PAGENUM=4; //每页显示的餐厅数;
	int FOOD_PAGENUM=4; //每页显示的推荐外卖数;
	int ADVICE_PAGENUM=12;//每页显示建议反馈条数.
	
	//邮件系统账户密码.
	String EMAILADDRESS="noreply@ecmeal.hk"; //系统邮件 用户名
	String PASSWORD="canteen123"; //系统邮件密码
	
	int MONEYLIMIT=120;//电话确认订单总金额限额 超过此额需要电话确认.
	
	String DNS="http://www.ecmeal.hk/canteen/";  //邮件验证有用到。
	
	String URL="http://www.ecmeal.hk";  //邮件验证有用到。
	
	int NOTICENUM = 4;//公告一次取的条数;
	
	
	//关于积分的规定
	int ENROLLCONFIRM_BONUS=10;//注册+邮件确认 获取10个积分；
	int ORDER_BONUS = 5;  //一次成功订单5个积分；
	int REVIEW_BONUS = 2; //一次订单点评2个积分;
	
	//首頁播報相关.
	int REVIEW_BROADCAST_NUM = 12;//一次取的评论播报的条数.
	int ORDER_BROADCAST_NUM=12;//一次取的订单播报的条数.
	
	int INVITATIONQUOTA=10; //好友邀请初始限额.
	
	//订单管理每页显示订单记录数
	int ORDERMANAGERECORDNUM=12;

	//我的订单每页显示的记录数
	int TODAYORDERRECORDNUM=6;
	int HISTORYRDERRECORDNUM=12;
	
	//我的评论的每页记录数
	int MYREVIEWRECORDNUM=6;
	
	//送達時間 儘快
	String FASTEST="儘快";
	
}
