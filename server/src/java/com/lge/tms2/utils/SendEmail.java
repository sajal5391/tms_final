/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author ramesh.nagarajan
 */
public class SendEmail {
    
    
    public void sendEmail(String toAddress,String subject, String message)  {
        toAddress = Util.removeUnwantedGerritStr(toAddress);
        sendEmail("lgekrhqmh01.lge.com","25","","",toAddress.split(","),subject,message);
    }
    
    public void sendEmail(String host, String port,
            final String userName, final String password, String[] toAddresses, String subject, String message)  {
        if(toAddresses != null) {
            InternetAddress[] addresses = new InternetAddress[toAddresses.length];
            for(int i =0; i < toAddresses.length; i++) {
                try {
                    addresses[i] = new InternetAddress(toAddresses[i]);
                } catch (AddressException ex) {
                    Util.Log("AddressException: " + ex.getMessage());
                }
            }
            sendEmail(host, port, userName, password, addresses, subject, message);
            
        }else{
            Util.Log("No Emails");
        }
    }
    
    public void sendEmail(String host, String port,
            final String userName, final String password, InternetAddress[] toAddresses, String subject, String message)  {

        if(message != null && (message.contains("[") || message.contains("]"))) {
            message = Util.removeSquareBrStr(message);
        }

        try {
            // sets SMTP server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // creates a new session with an authenticator
            Authenticator auth;
            auth = new Authenticator() {

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
            };

            Session session = Session.getInstance(properties, auth);

            // creates a new e-mail message
            Message msg = new MimeMessage(session);

            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress("noreply-PermalinkTracker@lge.com"));
            msg.setReplyTo(new InternetAddress[] {new InternetAddress("noreply-PermalinkTracker@lge.com")});
            msg.setSentDate(new Date());

            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            System.out.println("message: " + message);

            // creates multi-part
            Multipart multipart = new MimeMultipart();
            messageBodyPart.setContent(message, "text/plain; charset=utf-8");
            if (message != null) {
                multipart.addBodyPart(messageBodyPart);
            }

            // sets the multi-part as e-mail's content
            msg.setContent(multipart);

            // sends the e-mail
            Transport.send(msg);
            Util.Log("Email Send success");
            
            //Clear the Objects
            messageBodyPart = null;
            multipart = null;
            msg = null;
            
        } catch ( AddressException ex) {
            Util.Log("Email Send failed address");
        } catch (MessagingException ex){
            Util.Log("Email Send failed message");
        }


    }
}
