package br.com.thallyta.algafood.models.adapters;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("Exception Adapter Response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogExceptionAdapter {

    @ApiModelProperty(example="404")
    private Integer status;

    @ApiModelProperty(example = "Not Found")
    private String title;

    @ApiModelProperty(example = "Não foi encontrado recurso com o id informado")
    private String message;

    @ApiModelProperty(example = "NotFoundException")
    private String className;

    @ApiModelProperty(value = "Informa os campos que estão com erro de tipo de dados ou formatação")
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
