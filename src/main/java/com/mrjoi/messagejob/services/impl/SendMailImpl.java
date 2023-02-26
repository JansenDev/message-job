package com.mrjoi.messagejob.services.impl;

import com.mrjoi.messagejob.model.Reserva;
import com.mrjoi.messagejob.services.Sendmail;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class SendMailImpl implements Sendmail {

    public Boolean Send(String to, Reserva reserva) {

        // Sender's email ID needs to be mentioned
        String from = "seguragjj250697@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Setup mail server
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("seguragjj250697@gmail.com", "rfbznyfoptjenkcg");
            }
        });
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Boleta de compra mr Joy");

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("Gracias por vivir la esperiencia Mr Joy con nosotros 1--" + 1);
            messageBodyPart1.setContent("<h1 style='color:blue;'>Toma tu boleta kgada</h1>" +
                    "<img src='https://iili.io/HMLqXJS.png' />", "text/html");
//            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
//            String filename = "F:\\JanPc\\Desktop\\GFGsheet.xlsx";//change accordingly
//            DataSource source = new FileDataSource(filename);
//            messageBodyPart2.setDataHandler(new DataHandler(source));
//            messageBodyPart2.setFileName("filename");
            message.setReplyTo(InternetAddress.parse("seguragjj25.2@gmail.com"));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
//            multipart.addBodyPart(messageBodyPart2);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Sent message successfully to " + to + "....");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            System.out.println(mex.getMessage());
            System.out.println("Error");

            return false;
        }

    }

}
