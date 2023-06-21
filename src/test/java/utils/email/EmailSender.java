package utils.email;

import io.appium.java_client.android.AndroidDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.fileReader.PropertiesFileReader;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;


// This class is responsible for sending emails.
public class EmailSender {
    private static final Logger logger = LogManager.getLogger(EmailSender.class);

    // You need to change this to the email address on which you want to send email
    private final String recipient = "abc@gmail.com";

    private PropertiesFileReader fileReader;

    public EmailSender(ThreadLocal<AndroidDriver> driver) {
        fileReader = new PropertiesFileReader(driver);
    }

    public void sendEmail(String subject, String body, File attachment) {
        logger.info("Preparing to send email with subject: " + subject);
        Properties properties = new Properties();
        String host = fileReader.getHost();
        String port = fileReader.getPort();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");


        String email = fileReader.getEmail();
        String pass = fileReader.getEmailPassword();
       /* Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pass);
            }
        });*/

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {//
                return new PasswordAuthentication(email, pass);
            }

        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fileReader.getEmail()));
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
            logger.info("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

