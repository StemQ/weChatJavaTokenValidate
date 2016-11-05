package com.dqiang.demo;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author StemQ
 * @version v1.0
 * Blog:http://blog.csdn.net/stemq
 * Web:www.dqiang.com
 */
public class weChatJavaTokenValidate extends HttpServlet {
	
	private static final long serialVersionUID = -6761982938477193120L;
    /* 例如
     * URL(服务器地址) http://weixin.xxxx.com/weChatJavaTokenValidate/wechatToken
     * Token(令牌) tokenChat
     * */
    private String TOKEN = "tokenChat"; //根据实际情况自己定义token与基本配置/填写服务器配置Token(令牌)相同
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		
		String[] str = { TOKEN, timestamp, nonce };
		// 字典序排序
		Arrays.sort(str); 
		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
		String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
		// 确认请求来至微信
		if (digest.equals(signature)) {
			response.getWriter().print(echostr);
		}
	}

}
