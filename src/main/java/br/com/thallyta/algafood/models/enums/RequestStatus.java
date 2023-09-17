package br.com.thallyta.algafood.models.enums;

public enum RequestStatus {
    CREATED("Criado"),
    CONFIRMED("Confirmado"),
    DELIVERED("Entregue"),
    CANCELED("Cancelado");

    private final String statusValue;

    private RequestStatus(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public static RequestStatus fromString(String statusString) {
        for (RequestStatus status : RequestStatus.values()) {
            if (status.statusValue.equalsIgnoreCase(statusString)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + statusString);
    }

}
