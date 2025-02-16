package br.com.thallyta.algafood.core.event;

import br.com.thallyta.algafood.models.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestConfirmedEvent {

    private Request request;
}
