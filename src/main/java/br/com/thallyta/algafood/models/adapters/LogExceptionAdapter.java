package br.com.thallyta.algafood.models.adapters;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;


import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogExceptionAdapter {

    @Autowired
    private MessageSource messageSource;

    private Integer status;
    private String type;
    private String title;
    private String message;
    private String className;
    private String trace;
    private List<LogExceptionFieldsAdapter> fields;

    public LogExceptionAdapter(HttpStatus httpStatus, Exception exception) {
        this.status = httpStatus.value();
        this.setTitle(httpStatus.getReasonPhrase());
        this.setStatus(httpStatus.value());
        this.setMessage(exception.getMessage());
        this.setClassName(exception.getClass().getSimpleName());
        this.setTrace(Arrays.toString(exception.getStackTrace()));
    }

    public LogExceptionAdapter(HttpStatus httpStatus, Exception exception, String message) {
        this.status = httpStatus.value();
        this.setTitle(httpStatus.getReasonPhrase());
        this.setStatus(httpStatus.value());
        this.message = message;
        this.setClassName(exception.getClass().getSimpleName());
        this.setTrace(Arrays.toString(exception.getStackTrace()));
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
