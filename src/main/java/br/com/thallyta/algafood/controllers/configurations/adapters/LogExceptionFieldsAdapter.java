package br.com.thallyta.algafood.controllers.configurations.adapters;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogExceptionFieldsAdapter {
    private String name;
    private String userMessage;
}
