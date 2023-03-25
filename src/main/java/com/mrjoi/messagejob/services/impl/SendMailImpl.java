package com.mrjoi.messagejob.services.impl;

import com.lowagie.text.DocumentException;
import com.mrjoi.messagejob.model.Reserva;
import com.mrjoi.messagejob.services.Sendmail;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

@Service
public class SendMailImpl implements Sendmail {
    public Boolean Send(String voucherTemplate, String to) {

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
                return new PasswordAuthentication("mr.joy.lima@gmail.com", "aovoxarnmwjdqurx");
            }
        });
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Boleta de compra mr Joy");

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("Gracias por vivir la esperiencia Mr Joy con nosotros.");
            messageBodyPart1.setContent(voucherTemplate, "text/html");


            // Crear un objeto ITextRenderer
            ITextRenderer renderer = new ITextRenderer();
            // Asignar el contenido HTML al ITextRenderer
            renderer.setDocumentFromString("<html><body>"+voucherTemplate+"</body></html>");
            // Renderizar el contenido y guardar el resultado en un objeto ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            renderer.layout();
            renderer.createPDF(outputStream);
            // Crear un objeto DataSource a partir del objeto ByteArrayOutputStream
            DataSource dataSource = new ByteArrayDataSource(outputStream.toByteArray(), "application/pdf");
            // Crear un objeto DataHandler a partir del objeto DataSource
//            DataHandler dataHandler = new DataHandler(dataSource);


            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
//            String filename = "F:\\JanPc\\Desktop\\GFGsheet.xlsx";//change accordingly
//            DataSource source = new FileDataSource(filename);
            messageBodyPart2.setDataHandler(new DataHandler(dataSource));
            messageBodyPart2.setFileName("contrato.pdf");

//            message.setReplyTo(InternetAddress.parse("seguragjj25.2@gmail.com"));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Sent voucher successfully to " + to + "....");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            System.out.println(mex.getMessage());
            System.out.println("Error");

            return false;
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

    }

}
