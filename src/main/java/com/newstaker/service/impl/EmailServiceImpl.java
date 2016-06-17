package com.newstaker.service.impl;


import com.newstaker.domain.NewsMessage;
import com.newstaker.service.EmailService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by kimeshkov on 17.06.2016.
 */

@Service
public class EmailServiceImpl implements EmailService {
    private static final Log log = LogFactory.getLog(EmailServiceImpl.class);

    private static final String GOOGLE_EMAIL = "news.finder.test@gmail.com";
    private static final String GOOGLE_EMAIL_PASS = "Happy1234567";
    private static final String SEND_TO = "vkanaev@1cbit.ru";

    @Override
    public void sendNewsMessage(NewsMessage newsMessage) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(GOOGLE_EMAIL, GOOGLE_EMAIL_PASS);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(GOOGLE_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(SEND_TO));
            message.setSubject("News Message");
            message.setText(newsMessage.getTitle() + " " + newsMessage.getLink());

            Transport.send(message);

        } catch (MessagingException e) {
            log.error(e);
        }
    }
}
