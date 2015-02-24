/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;


/**
 *
 * @author rickyharris
 */
public class JavaMail {
     String d_email = "ibcoachtest123@gmail.com",
            d_password = "harrisonhs", //your email password
            d_host = "smtp.gmail.com",
            d_port = "465",
            m_to = "rickyh8198@msn.com", // Target email address
            m_subject = "Testing",
            m_text = "Yee";
    
    public JavaMail() {
        Properties props = new Properties();
        props.put("mail.smtp.user", d_email);
        props.put("mail.smtp.host", d_host);
        props.put("mail.smtp.port", d_port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", d_port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);     
            MimeMessage msg = new MimeMessage(session);
            msg.setText(m_text);
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress(d_email));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
            Transport.send(msg);
            System.out.println("sent");
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
   
    public static void main(String[] args) {
        JavaMail blah = new JavaMail();
    }
  
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(d_email, d_password);
        }
    }
}
