package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.services.transactional_email.SMTPSendEmailService;
import br.com.thallyta.algafood.services.transactional_email.SendEmailService;
import br.com.thallyta.algafood.services.transactional_email.SendEmailService.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ChangeStatusRequestService {

    @Autowired
    private RequestService requestService;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private SMTPSendEmailService smtpSendEmailService;

    @Transactional
    public void confirmRequest(String code){
        Request request = requestService.findOrFail(code);
        request.confirmRequest();

        var message = Message.builder()
                .subject(request.getRestaurant().getName() + " - " + "Sua solicitação foi confirmada")
                .body("request-confirmed.html")
                .variable("request", request)
                .recipient(request.getClient().getEmail())
                .build();

        sendEmailService.send(message);
    }

    @Transactional
    public void cancelRequest(String code) {
        Request request = requestService.findOrFail(code);
        request.cancelRequest();
    }

    @Transactional
    public void deliverRequest(String code) {
        Request request = requestService.findOrFail(code);
        request.deliverRequest();
    }
}
