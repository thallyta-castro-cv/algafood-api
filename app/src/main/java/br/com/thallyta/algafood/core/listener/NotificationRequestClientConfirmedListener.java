package br.com.thallyta.algafood.core.listener;

import br.com.thallyta.algafood.core.event.RequestConfirmedEvent;
import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.services.transactional_email.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificationRequestClientConfirmedListener {

    @Autowired
    private SendEmailService sendEmailService;

    @TransactionalEventListener
    public void toRequestConfirm(RequestConfirmedEvent event) {
        Request request = event.getRequest();
        sendTransactionalConfirmEmail(request);
    }

    private void sendTransactionalConfirmEmail(Request request) {
        var message = SendEmailService.Message.builder()
                .subject(request.getRestaurant().getName() + " - " + "Sua solicitação foi confirmada")
                .body("request-confirmed.html")
                .variable("request", request)
                .recipient(request.getClient().getEmail())
                .build();

        sendEmailService.send(message);
    }
}
