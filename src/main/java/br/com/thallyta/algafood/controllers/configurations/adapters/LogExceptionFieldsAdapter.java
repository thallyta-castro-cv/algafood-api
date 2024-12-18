package br.com.thallyta.algafood.controllers.configurations.adapters;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "Fields Details")
public class LogExceptionFieldsAdapter {
    private String name;
    private String userMessage;
}
