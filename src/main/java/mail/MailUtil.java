package mail;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HttpClientStarter;

/**
 * https://gist.github.com/cyyjs/5378883
 * http://www.jspxcms.com/documentation/351.html
 */
public class MailUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpClientStarter.class);

    public static void main(String[] args) {

        Email email = getEmail("simple");
        configAndSendMail(email, "commons mail6", "read from resources6", "wander669@163.com");
        System.out.println("email has been sent.");

    }

    private static String hostName;//邮件服务器域名
    private static String userName;//个人邮箱用户名
    private static String password;//邮件服务器提供商的授权码，不是邮箱账号的密码
    private static String from;//发送邮箱地址, 必须和userName相同

    static {
        /**
         * 从配置文件获取邮件服务器，发送邮箱地址，用户名和密码
         */
        Properties pro = new Properties();
        try {
            pro.load(MailUtil.class.getClassLoader().getResourceAsStream(
                    "email.properties"));
            hostName = pro.getProperty("hostName");//接受邮件服务器地址
            userName = pro.getProperty("userName");//发件人用户名
            password = pro.getProperty("password");//发件人密码
            from = userName;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    public static void configAndSendMail(Email email, String subject, String msg, String addto) {
        email.setHostName(hostName);//设置发件人的邮件服务器
        email.setAuthentication(userName, password);//设置发件人的用户名和密码
        email.setCharset("UTF-8");//设置编码格式
        email.setSSLOnConnect(true);
        try {
            email.setFrom(from)//设置发件人地址
                    .setSubject(subject)//设置邮件主题
                    .setMsg(msg)//设置邮件内容
                    .addTo(addto)//收件人地址
                    .send();//发送
        } catch (EmailException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

    public static Email getEmail(String type) {
        if ("simple".equals(type)) {
            return new SimpleEmail();
        } else if ("html".equals(type.toLowerCase())) {
            return new HtmlEmail();
        } else {
            return new MultiPartEmail();
        }
    }

}
