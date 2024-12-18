package br.com.thallyta.algafood.controllers.configurations.adapters;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name= "Problem Details")
public class LogExceptionAdapter {

    @Schema(example= "400")
    private Integer status;

    @Schema(example= "Recurso não encontrado")
    private String title;

    @Schema(example= "Um ou mais campos inválidos. Faça o preenchimento correto e tente novamente")
    private String message;

    @Schema(example= "NotFoundException")
    private String className;

    @Schema(description= "Lista de objetos ou campos que geraram o erro")
    private List<LogExceptionFieldsAdapter> fields;

    public LogExceptionAdapter(HttpStatus httpStatus, Exception exception) {
        this.status = httpStatus.value();
        this.setTitle(httpStatus.getReasonPhrase());
        this.setStatus(httpStatus.value());
        this.setMessage(exception.getMessage());
        this.setClassName(exception.getClass().getSimpleName());
    }

    public LogExceptionAdapter(HttpStatus httpStatus, Exception exception, String message) {
        this.status = httpStatus.value();
        this.setTitle(httpStatus.getReasonPhrase());
        this.setStatus(httpStatus.value());
        this.message = message;
        this.setClassName(exception.getClass().getSimpleName());
    }

    public LogExceptionAdapter(HttpStatus httpStatus, Exception exception, String message, List<LogExceptionFieldsAdapter> problemFields) {
        this.status = httpStatus.value();
        this.setTitle(httpStatus.getReasonPhrase());
        this.setStatus(httpStatus.value());
        this.setFields(problemFields);
        this.message = message;
        this.setClassName(exception.getClass().getSimpleName());
    }

}
