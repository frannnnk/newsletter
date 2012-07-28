package controller.account;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 验证码生成类
 * 
 * @author 胡圣朗
 * 
 */
public class VerifyCodeImageAction extends ActionSupport {

	public String execute() throws Exception {
		BufferedImage img = new BufferedImage(68, 22,
				BufferedImage.TYPE_INT_RGB);
		// 得到该图片的绘图对象
		Graphics g = img.getGraphics();
		Random r = new Random();
		// 设定背景色
		g.setColor(Color.WHITE); 
		g.fillRect(0, 0, 68, 22);// 向图片中输出数字和字母
		StringBuffer sb = new StringBuffer();
		char[] ch = "0123456789".toCharArray();
		int index, len = ch.length;
		
		for (int i = 0; i < 4; i++) {
			index = r.nextInt(len);
			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			
			g.setFont(new Font("Comic Sans MS",Font.BOLD,18));//设定字体
			g.drawString("" + ch[index], (i * 15) + 3, 18);// 写什么数字，在图片的什么位置画
			sb.append(ch[index]);
		}
		for(int i = 0; i < 23; i++) 
		{
		int x = r.nextInt(68); 
		int y = r.nextInt(22); 
		int xl = r.nextInt(12); 
		int yl = r.nextInt(12); 
		g.drawLine(x, y, x + xl, y + yl); 
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.getSession().setAttribute("piccode", sb.toString());
		ImageIO.write(img, "JPG", response.getOutputStream());
		return NONE;
	}
}
