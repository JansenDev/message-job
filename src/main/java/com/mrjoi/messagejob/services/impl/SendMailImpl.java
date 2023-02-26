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
            messageBodyPart1.setText("Gracias por vivir la esperiencia Mr Joy con nosotros.");
            messageBodyPart1.setContent("<body>\n" +
                    "    <div style=\"width:600px; margin: auto; font-family:Arial, Helvetica, sans-serif\">\n" +
                    "        <div >\n" +
                    "            <h2 style=\"text-align: center;\">Boleta de venta electronica</h2>\n" +
                    "        </div>\n" +
                    "        <div  style=\"text-align: center;\">\n" +
                    "            <img style=\"height: 78px; width: 176px;\"src=\"https://iili.io/HMLqXJS.png\" />\n" +
                    "            <p style=\"margin-top: 15px;\">Ate 15494</p>\n" +
                    "            <p style=\"margin-top: -10px;\">Local Puruchuco</p>\n" +
                    "        </div>\n" +
                    "        <hr style=\"min-width: 600px;height: 1px; margin: 1rem 0;color: inherit;background-color: currentColor;border: 0;\">\n" +
                    "        <div  style=\"padding-bottom: 1em; display: flex;\">\n" +
                    "            <div style=\"padding-right: 80px;\">\n" +
                    "                <h5 style=\"font-weight: 900;;font-size: 0.8em;\">Cliente</h5>\n" +
                    "                <p style=\"font-size: 15px;\">Arian Manuel Garcia Reynoso</p>\n" +
                    "                <p style=\"font-size: 15px;\">Fecha Reserva: 26/02/2023 10:00 am</p>\n" +
                    "            </div>\n" +
                    "            <div style=\"padding-right: 40px;\">\n" +
                    "                <h5 style=\"font-weight: 900;;font-size: 0.8em;\">N° de boleta:</h5>\n" +
                    "                <h5 style=\"font-weight: 900;;font-size: 0.8em;\">Fecha registro:</h5>\n" +
                    "                <h5 style=\"font-weight: 900;;font-size: 0.8em;\">Fecha vencimiento:</h5>\n" +
                    "            </div>\n" +
                    "            <div>\n" +
                    "                <h5 style=\"font-weight: 900;;font-size: 0.8em;\">103</h5>\n" +
                    "                <p>10/02/2023</p>\n" +
                    "                <p>26/02/2023</p>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div style=\"padding-bottom: 40px;display: grid;grid-template-rows: minmax(220px,auto);\">\n" +
                    "            <table style=\"table-layout: fixed;border-collapse: collapse;\">\n" +
                    "        \n" +
                    "                <thead style=\"border-top: solid 3px #000; border-bottom: 3px solid #000; height: 65px;\">\n" +
                    "                <tr>\n" +
                    "                    <th style=\"padding-top: 30px;\">Cant. Personas</th>\n" +
                    "                    <th style=\"padding-top: 30px;\">Descripcion</th>\n" +
                    "                    <th style=\"padding-top: 30px;\">Precio Unitario</th>\n" +
                    "                    <th style=\"padding-top: 30px;\">Importe</th>\n" +
                    "                </tr>\n" +
                    "                </thead>\n" +
                    "                <tbody>\n" +
                    "                <tr style=\"height:40px;\">\n" +
                    "                    <td style=\"text-align: center\">1</td>\n" +
                    "                    <td style=\"text-align: center\">Reserva de cumpleaños</td>\n" +
                    "                    <td style=\"text-align: right\">0.00</td>\n" +
                    "                    <td style=\"text-align: right\">0.00</td>\n" +
                    "                </tr>\n" +
                    "                </tbody>\n" +
                    "                <tfoot>\n" +
                    "                    <tr style=\"height:30px;\">\n" +
                    "                        <th></th>\n" +
                    "                        <th></th>\n" +
                    "                        <th style=\"text-align: right\">V. venta</th>\n" +
                    "                        <th style=\"text-align: right\">0.00</th>\n" +
                    "                    </tr>\n" +
                    "                    <tr style=\"height:30px;\">\n" +
                    "                        <th></th>\n" +
                    "                        <th></th>\n" +
                    "                        <th style=\"text-align: right\">IGV 18%</th>\n" +
                    "                        <th style=\"text-align: right\">0.00</th>\n" +
                    "                    </tr>\n" +
                    "                    <tr style=\"height:30px;\">\n" +
                    "                        <th></th>\n" +
                    "                        <th></th>\n" +
                    "                        <th style=\"text-align: right\">Total </th>\n" +
                    "                        <th style=\"text-align: right\">0.00</th>\n" +
                    "                    </tr>\n" +
                    "                </tfoot>\n" +
                    "            </table>\n" +
                    "        </div> \n" +
                    "        <div  style=\"border-top: solid 2px #000; padding-top: 20px;\">\n" +
                    "            <div style=\"display:flex\">\n" +
                    "                <div style=\"padding-right: 3em;\">\n" +
                    "                    <h4>Condiciones y formas de pago</h4>\n" +
                    "                </div>\n" +
                    "                <div>\n" +
                    "                    <img style=\"height: 40px;width: 40px;\"src=\"https://www.mrjoy.com.ec/wp-content/uploads/2022/03/favicon1.png\"/>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "\n" +
                    "            <p></p>\n" +
                    "            <p>\n" +
                    "            Banco Banreserva\n" +
                    "            <br />\n" +
                    "            IBAN: DO XX 0075 XXXX XX XX XXXX XXXX\n" +
                    "            <br />\n" +
                    "            Código SWIFT: BPDODOSXXXX\n" +
                    "            </p>\n" +
                    "        </div>\n" +
                    "</body>", "text/html");
//            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
//            String filename = "F:\\JanPc\\Desktop\\GFGsheet.xlsx";//change accordingly
//            DataSource source = new FileDataSource(filename);
//            messageBodyPart2.setDataHandler(new DataHandler(source));
//            messageBodyPart2.setFileName("filename");

//            message.setReplyTo(InternetAddress.parse("seguragjj25.2@gmail.com"));

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
