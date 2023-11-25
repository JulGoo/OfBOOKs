package Service.User;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Config.Crypto;
import Config.GmailConn;
import Service.CommandHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmailService implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		return sendEmail(email);
	}

	public String sendEmail(String UserEmail) {
		String authKey = Crypto.makeAuthKey();
		String from = "202244026@itc.ac.kr";
		String subject = "OfBOOKs 이메일 인증";
		String content = "";
		content += "<h2>OfBOOKs - 이메일 인증번호</h2>";
		content += "아래 코드를 입력하세요.<br>";
		content += "<hr>";
		content += "인증번호 : ";
		content += authKey;
		

		// 지메일 연결 설정
		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.fallback", "false");
		p.put("mail.smtp.ssl.protocols", "TLSv1.2");
		p.put("mail.smtp.ssl.enable", "true");

		try {
			Authenticator auth = new GmailConn();
			Session session = Session.getInstance(p, auth);

			MimeMessage msg = new MimeMessage(session);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(UserEmail);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html;charset=UTF-8");

			// 메일 전송
			Thread thread = new Thread() {
				public void run() {
					try {
						Transport.send(msg);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			};
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return authKey;
	}
}
