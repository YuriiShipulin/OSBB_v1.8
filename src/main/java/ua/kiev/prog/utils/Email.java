package ua.kiev.prog.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Email {

    public boolean sendMail(String[] to, String code) {
        String host = "smtp.gmail.com";
        String port = "465";
        String msg = "Приглашение на сайт ОСББ. Код для регистрации: " + code;
        String subject = "Приглашение на сайт ОСББ";
        Properties props = System.getProperties();
        props.put("mail.smtp.from", "projectjavamvc@gmail.com");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.user", "GthdsqGhjtrn");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("projectjavamvc@gmail.com", "GthdsqGhjtrn");
            }
        });
        MimeMessage miMsg = new MimeMessage(session);
        try {
            miMsg.setFrom(new InternetAddress("projectjavamvc@gmail.com"));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            for (int i = 0; i < toAddress.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            for (int i = 0; i < toAddress.length; i++) {
                miMsg.addRecipient(Message.RecipientType.TO, toAddress[i]);

            }
            miMsg.setSubject(subject);
            miMsg.setText(msg);
            Transport transp = session.getTransport("smtp");
            transp.connect(host, "projectjavamvc@gmail.com", "GthdsqGhjtrn");
            transp.sendMessage(miMsg, miMsg.getAllRecipients());
            transp.close();
            return true;
        } catch (AddressException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }
    public boolean sendMail(String to, String code) {
        String host = "smtp.gmail.com";
        String port = "465";
        String msg = "Приглашение на сайт ОСББ. Код для регистрации: " + code;
        String subject = "Приглашение на сайт ОСББ";
        Properties props = System.getProperties();
        props.put("mail.smtp.from", "projectjavamvc@gmail.com");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.user", "GthdsqGhjtrn");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("projectjavamvc@gmail.com", "GthdsqGhjtrn");
            }
        });
        MimeMessage miMsg = new MimeMessage(session);
        try {
            miMsg.setFrom(new InternetAddress("projectjavamvc@gmail.com"));
            InternetAddress toAddress = new InternetAddress(to);
            miMsg.addRecipient(Message.RecipientType.TO, toAddress);
            miMsg.setSubject(subject);
            miMsg.setText(msg);
            Transport transp = session.getTransport("smtp");
            transp.connect(host, "projectjavamvc@gmail.com", "GthdsqGhjtrn");
            transp.sendMessage(miMsg, miMsg.getAllRecipients());
            transp.close();
            return true;
        } catch (AddressException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
