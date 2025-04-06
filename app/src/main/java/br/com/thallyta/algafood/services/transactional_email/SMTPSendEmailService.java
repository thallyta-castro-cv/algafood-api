package br.com.thallyta.algafood.services.transactional_email;

import br.com.thallyta.algafood.core.email.EmailProperties;
import br.com.thallyta.algafood.core.exceptions.EmailException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import jakarta.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class SMTPSendEmailService implements SendEmailService{

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    EmailProperties emailProperties;

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public void send(Message message) {

        try{
            String body = templateProcessing(message);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject(message.getSubject());
            helper.setText(body, true);
            helper.setFrom("thallyta180136319@gmail.com");
            helper.setTo(message.getRecipients().toArray(new String[0]));
            mailSender.send(mimeMessage);
        }catch(Exception e){
            throw new EmailException("Não foi possível enviar o e-mail", e);
        }

    }

    private String templateProcessing(Message message) {
        try {
            Template template = freemarkerConfig.getTemplate(message.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
        } catch (IOException | TemplateException e) {
            throw new EmailException("Não foi possível montar o template do email", e);
        }
    }
}
