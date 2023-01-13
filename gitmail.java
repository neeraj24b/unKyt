/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class gitmail {
    public static void main(String[] args) {
        gitmail gitt = new gitmail();
        try {
            String f = "test@abc.test";
            String t = "test@abc.test";
            String s = "test@abc.test";
            String m = "test@abc.test";
            gitt.ml(f, t, s, m);
        } catch (MessagingException ex) {
            Logger.getLogger(gitmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ml(String f, String t, String s, String m) throws AddressException, MessagingException {
        if (!t.isEmpty() && !s.isEmpty() && !m.isEmpty()) {
            Properties props = new Properties();
            props.put("mail.smtp.host", "localhost");
            props.put("mail.smtp.socketFactory.port", "25");
            props.put("mail.debug", "true");
            props.put("mail.smtp.port", "22225");

            // creates a new session with an authenticator
            Session session = Session.getInstance(props);
            try {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(f));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(t));
                msg.setSubject(s);
                msg.setSentDate(new Date());
                msg.setContent(m, "text/html");
                Transport.send(msg);
                System.out.println("sent");
            } catch (MessagingException ex) {
            }
        } else {
            System.out.println("sent");
        }
    }
}
