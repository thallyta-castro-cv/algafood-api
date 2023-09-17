package br.com.thallyta.algafood.models.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum RequestStatus {

    CRIADO("CRIADO"),
    CONFIRMADO("CONFIRMADO", CRIADO),
    ENTREGUE("ENTREGUE", CONFIRMADO),
    CANCELADO("CANCELADO", CRIADO);

    @Getter
    private final String statusValue;

    @Getter
    private final List<RequestStatus> previousStatus;

    private RequestStatus(String statusValue, RequestStatus... previousStatus) {
        this.statusValue = statusValue;
        this.previousStatus = Arrays.asList(previousStatus);
    }

    public boolean doesNotChangeStatusTo(RequestStatus newStatus) {
        return !newStatus.previousStatus.contains(this);
    }

}
