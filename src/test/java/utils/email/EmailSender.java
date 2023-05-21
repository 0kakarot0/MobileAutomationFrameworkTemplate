package utils;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    private String host;
    private int port;
    private String username;
    private String password;

    public EmailSender(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void sendEmail(String recipient, String subject, String body, File attachment) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);

            // Create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Create the multipart message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Add the attachment if provided
            if (attachment != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.setDataHandler(new DataHandler(new FileDataSource(attachment)));
                attachmentBodyPart.setFileName(attachment.getName());
                multipart.addBodyPart(attachmentBodyPart);
            }

            // Set the content of the message to the multipart
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

