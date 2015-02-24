/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compsciproject;

/**
 *
 * @author HarrisR
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;

public class JavaMail {    
    String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());




    String d_email = "ibcoachtest123@gmail.com",
            d_password = "harrisonhs", //your email password
            d_host = "smtp.gmail.com",
            d_port = "465",
            m_to = "rickyh8198@msn.com", // Target email address
            m_subject = "Testing",
            m_text = "Yee";
    private Message msg;
    boolean sent;
    private Object dateNow;
   
    
   /* public JavaMail() {
        Properties props = new Properties();
        props.put("mail.smtp.user", d_email);
        props.put("mail.smtp.host", d_host);
        props.put("mail.smtp.port", d_port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.debug", "true");
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
    }*/public void sendMail(String user, String password, String[] recipients, String message){
    boolean sent = false;
    for(int i=0; i<recipients.length; i++){
        try {
            d_email = user;
            d_password = password; //your email password
            m_text = message;
            m_to = recipients[i];
            Properties props = new Properties();
            props.put("mail.smtp.user", d_email);
            props.put("mail.smtp.host", d_host);
            props.put("mail.smtp.port", d_port);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            //props.put("mail.smtp.debug", "true");
            props.put("mail.smtp.socketFactory.port", d_port);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            MimeMessage mesg = new MimeMessage(session);
            mesg.setText(message);
            mesg.setSubject("workout from your coach on " + date);
            mesg.setFrom(new InternetAddress(user));
            mesg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
            Transport.send(mesg);
            System.out.println("sent");
            sent=true;
            
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Invalid email or password");
              sent=false;
        }
       
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