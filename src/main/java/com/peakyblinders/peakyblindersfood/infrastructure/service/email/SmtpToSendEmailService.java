package com.peakyblinders.peakyblindersfood.infrastructure.service.email;

import com.peakyblinders.peakyblindersfood.core.email.EmailProperties;
import com.peakyblinders.peakyblindersfood.domain.services.SendingEmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SmtpToSendEmailService implements SendingEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;


    @Override
    public void toSend(Message message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailProperties.getSender());
            helper.setTo(message.getRecipient().toArray(new String[0]));
            helper.setSubject(message.getSubject());
            helper.setText(message.getBody(),true);
        } catch (Exception e){
            throw  new EmailException("Não foi possivel enviar e-mail", e);
        }

    }
}

