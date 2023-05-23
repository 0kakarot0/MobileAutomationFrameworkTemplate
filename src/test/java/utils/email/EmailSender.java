package utils.email;

import io.appium.java_client.android.AndroidDriver;
import utils.fileReader.PropertiesFileReader;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.log4j.Logger;


// This class is responsible for sending emails.
public class EmailSender {
    private static final Logger logger = Logger.getLogger(EmailSender.class);

    private final String recipient = "abc@gmail.com ";

    private PropertiesFileReader fileReader;
    public EmailSender(AndroidDriver driver) {
        fileReader = new PropertiesFileReader();
    }

    public void sendEmail(String subject, String body, File attachment) {
        logger.info("Preparing to send email with subject: " + subject);
        Properties properties = new Properties();
        properties.put("mail.smtp.host", fileReader.getHost());
        properties.put("mail.smtp.port", fileReader.getPort());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fileReader.getEmail(), fileReader.getEmailPassword());
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

