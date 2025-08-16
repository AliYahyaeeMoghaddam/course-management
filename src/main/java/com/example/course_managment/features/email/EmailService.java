package com.example.course_managment.features.email;

import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    private Session session;
    private EmailConfig lastConfig;

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        refreshSessionIfNeeded();

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(lastConfig.getServerAddress()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }

    private void refreshSessionIfNeeded(){
        EmailConfig currentConfig = EmailConfigManager.load();

        if(session == null || !currentConfig.equals(lastConfig)){
            System.out.println("Settings have changed!\nWaiting to create a new session...");
            this.session = buildSession(currentConfig);
            this.lastConfig = currentConfig;
        }
    }

    private Session buildSession(EmailConfig currentConfig) {
        Properties props = new Properties();
        props.put("mail.smtp.host",currentConfig.getServerAddress());
        props.put("mail.smtp.port",String.valueOf(currentConfig.getPort()));
        props.put("mail.smtp.auth","true");

        if("TLS".equalsIgnoreCase(currentConfig.getSecurityType()))
            props.put("mail.smtp.starttls.enable","true");
        else if("SSL".equalsIgnoreCase(currentConfig.getSecurityType())){
            props.put("mail.smtp.socketFactory.port",String.valueOf(currentConfig.getPort()));
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        }

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(currentConfig.getUsername(), currentConfig.getPassword());
            }
        });
    }

}
