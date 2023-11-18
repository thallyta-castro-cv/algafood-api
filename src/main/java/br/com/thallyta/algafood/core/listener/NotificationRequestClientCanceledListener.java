package br.com.thallyta.algafood.core.listener;

import br.com.thallyta.algafood.core.event.RequestConfirmedEvent;
import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.services.transactional_email.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificationRequestClientCanceledListener {

    @Autowired
    private SendEmailService sendEmailService;

    @TransactionalEventListener
    public void toRequestCanceled(RequestConfirmedEvent event) {
        Request request = event.getRequest();
        sendTransactionalCanceledEmail(request);
    }

    private void sendTransactionalCanceledEmail(Request request) {
        var message = SendEmailService.Message.builder()
                .subject(request.getRestaurant().getName() + " - " + "Seu pedido foi cancelado!")
                .body("request-canceled.html")
                .variable("request", request)
                .recipient(request.getClient().getEmail())
                .build();

        sendEmailService.send(message);
    }
}
