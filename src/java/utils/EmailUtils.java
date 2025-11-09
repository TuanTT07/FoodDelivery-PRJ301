package utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Thư viện: https://mvnrepository.com/artifact/com.sun.mail/javax.mail Tao mật
 * khẩu ứng dụng: https://myaccount.google.com/apppasswords
 */
public class EmailUtils {

    // Thông tin tài khoản email dùng để gửi (thay đổi thông tin này)
    private static final String EMAIL_USERNAME = "tuantranffm7@gmail.com";
    private static final String EMAIL_PASSWORD = "zaycdnhukakzrygj";

    // Cấu hình SMTP server
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587"; //TSL  --SSL 465

    /**
     * Gửi email thông báo đăng ký thành công
     *
     * @param toEmail Địa chỉ email người nhận
     * @param fullName Tên đầy đủ của người dùng
     * @param userID ID người dùng
     * @return true nếu gửi email thành công, false nếu có lỗi
     */
    public static boolean sendRegistrationEmail(String toEmail, String fullName, String userID) {
        try {
            // Thiết lập các thuộc tính
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);

            // Tạo phiên xác thực
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
                }
            });

            // Tạo message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Welcome to Our Website - Registration Successful");

            // Xây dựng nội dung HTML email
            String htmlContent = createRegistrationEmailContent(fullName, userID);
            message.setContent(htmlContent, "text/html; charset=utf-8");

            // Gửi email
            Transport.send(message);

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Xây dựng nội dung HTML cho email đăng ký thành công
     *
     * @param fullName Tên đầy đủ người dùng
     * @param userID ID người dùng
     * @return Chuỗi HTML hoàn chỉnh cho nội dung email
     */
    private static String createRegistrationEmailContent(String fullName, String userID) {
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Registration Successful</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            line-height: 1.6;\n"
                + "            color: #333;\n"
                + "            margin: 0;\n"
                + "            padding: 0;\n"
                + "        }\n"
                + "        .container {\n"
                + "            max-width: 600px;\n"
                + "            margin: 0 auto;\n"
                + "            padding: 20px;\n"
                + "            background-color: #f9f9f9;\n"
                + "        }\n"
                + "        .header {\n"
                + "            background-color: #4a90e2;\n"
                + "            color: white;\n"
                + "            padding: 20px;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        .content {\n"
                + "            padding: 20px;\n"
                + "            background-color: white;\n"
                + "            border-radius: 5px;\n"
                + "        }\n"
                + "        .button {\n"
                + "            display: inline-block;\n"
                + "            padding: 10px 20px;\n"
                + "            background-color: #4a90e2;\n"
                + "            color: white;\n"
                + "            text-decoration: none;\n"
                + "            border-radius: 5px;\n"
                + "            margin: 20px 0;\n"
                + "        }\n"
                + "        .footer {\n"
                + "            text-align: center;\n"
                + "            margin-top: 20px;\n"
                + "            font-size: 12px;\n"
                + "            color: #666;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <div class=\"header\">\n"
                + "            <h1>Welcome to Our Website!</h1>\n"
                + "        </div>\n"
                + "        <div class=\"content\">\n"
                + "            <h2>Hello, " + fullName + "!</h2>\n"
                + "            <p>Thank you for registering with our website. Your account has been successfully created.</p>\n"
                + "            <p><strong>Your login information:</strong></p>\n"
                + "            <p>Username: <strong>" + userID + "</strong></p>\n"
                + "            <p>You can now login to your account and start exploring our services.</p>\n"
                + "            <a href=\"http://yourwebsite.com/login\" class=\"button\">Login to Your Account</a>\n"
                + "            <p>If you have any questions or need assistance, please don't hesitate to contact our support team.</p>\n"
                + "            <p>Best regards,<br>The Team</p>\n"
                + "        </div>\n"
                + "        <div class=\"footer\">\n"
                + "            <p>This is an automated message, please do not reply to this email.</p>\n"
                + "            <p>&copy; 2025 Your Company. All rights reserved.</p>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
    }

    /**
     * Gửi email xác thực đăng ký với token xác thực
     *
     * @param toEmail Địa chỉ email người nhận
     * @param fullName Tên đầy đủ của người dùng
     * @param token Token xác thực
     * @return true nếu gửi email thành công, false nếu có lỗi
     */
    public static boolean sendVerificationEmail(String toEmail, String fullName, String verificationLink) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Account Verification Required");

            String htmlContent = createVerificationEmailContent(fullName, verificationLink);
            message.setContent(htmlContent, "text/html; charset=utf-8");

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String createVerificationEmailContent(String fullName, String verificationLink) {
        return "<!DOCTYPE html>"
                + "<html><head><meta charset='UTF-8'><title>Verify Your Account</title></head><body>"
                + "<h2>Hello, " + fullName + "!</h2>"
                + "<p>Thank you for registering. Please verify your email by clicking the button below:</p>"
                + "<a href='" + verificationLink + "' style='display:inline-block;padding:10px 20px;"
                + "background-color:#4a90e2;color:white;text-decoration:none;border-radius:5px;'>Verify Your Account</a>"
                + "<p>If the button doesn't work, copy and paste this link into your browser:</p>"
                + "<p><a href='" + verificationLink + "'>" + verificationLink + "</a></p>"
                + "<p>This link expires in 24 hours.</p>"
                + "<p>Best regards,<br>Team</p>"
                + "</body></html>";
    }

    public static void main(String[] args) {
        sendRegistrationEmail("lenhattung@gmail.com", "LNT", "LNT");
    }
}
