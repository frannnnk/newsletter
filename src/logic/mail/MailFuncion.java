package logic.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import common.CommonFunction;
import common.Constant;

import dao.MailRelatedDao;

/**
 * 邮件功能
 * 
 * @author 胡圣朗
 * 
 */
public class MailFuncion {
	private static Logger logger = Logger
			.getLogger(MailFuncion.class.getName()); // 日志对象;
	private MailRelatedDao dao = new MailRelatedDao();

	/**
	 * gmail sender with text
	 */
	public void mailSenderForcommon(String addresses, String title,
			String contentText) {
		logger.info("text mail is senting...");
		try {
			final String host = "smtp.gmail.com";
			String tmpfrom = null;
			String tmppass = null;

			tmpfrom = Constant.EMAILADDRESS;
			tmppass = Constant.PASSWORD;

			final String from = tmpfrom;
			final String pass = tmppass;
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true"); // 在本行添加
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			String[] to = addresses.split(",");
			Session session = Session.getDefaultInstance(props, null);
			final MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			// 获取地址的array
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}
			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			message.setSubject(title);
			message.setText(contentText);
			final Transport transport = session.getTransport("smtp");

			new Thread(new Runnable() {
				public void run() {
					try {
						transport.connect(host, from, pass);
						transport.sendMessage(message,
								message.getAllRecipients());
					} catch (Exception e) {
						logger.debug(e.getMessage());
					}
				}
			}).start();

			transport.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * gmail sender with text
	 */
	public void mailSenderForCustom(String addresses, String title,
			String contentHTML) {
		logger.info("html mail is senting...");
		final String host = "smtp.gmail.com";

		String tmpfrom = null;
		String tmppass = null;

		tmpfrom = Constant.EMAILADDRESS;
		tmppass = Constant.PASSWORD;

		final String from = tmpfrom;
		final String pass = tmppass;
		// 创建一个属性对象
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true"); // 在本行添加
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		try {
			// 创建一个session对象
			Session mailSession = Session.getDefaultInstance(props);
			mailSession.setDebug(true);
			// 创建一个Message对象
			final Message message = new MimeMessage(mailSession);
			// 指定发件人邮箱
			message.setFrom(new InternetAddress(from));

			String[] to = addresses.split(",");
			InternetAddress[] toAddress = new InternetAddress[to.length];
			// 指定收件人邮箱
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}
			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			// 指定邮箱主题
			message.setSubject(title);
			// 指定邮箱内容及ContentType和编码方式
			message.setContent(contentHTML, "text/html;charset=utf-8");
			// 指定邮件发送日期
			message.setSentDate(new Date());
			// 指定邮件优先级 1:紧急 3:普通 5:缓慢
			message.setHeader("X-Priority", "3");
			message.saveChanges();
			// 创建一个Transport对象
			final Transport transport = mailSession.getTransport("smtp");
			// 发送邮件
			new Thread(new Runnable() {
				public void run() {
					try {
						// 连接SMTP服务器
						transport.connect(host, from, pass);
						transport.sendMessage(message,
								message.getAllRecipients());
					} catch (Exception e) {
						logger.debug(e.getMessage());
					}
				}
			}).start();
			transport.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 邮件订阅
	 * 
	 * @param emailAddress
	 * @return state 状态值 1-邮件订阅成功; 0-邮件已经被订阅，无需再订阅；2-邮件订阅异常.
	 */
	public int mailSubscript(String userid, String emailAddress) {
		logger.debug("用户邮件订阅 emailAddress=" + emailAddress);
		int state = 0; // 状态
		boolean flag = false;
		if (dao.getNumOfSubscripted(emailAddress) == 0) { // 此邮箱未被订阅，可以订阅
			flag = dao.insertEmailSubscription(userid, emailAddress,
					CommonFunction.getcurrentTimetext());
			if (flag) {
				state = 1;
				// 订阅成功后 自动发邮件确认
				mailSenderForcommon(emailAddress, "恭喜，您已經成功訂閱易食飯郵件!",
						"恭喜，您已經成功訂閱易食飯郵件!");
			} else
				state = 2;
		} else {
			state = 0;
		}
		return state;
	}
}
