package br.com.thallyta.algafood.core.configurations;

import br.com.thallyta.algafood.core.exceptions.BadRequestException;
import br.com.thallyta.algafood.core.exceptions.EntityExceptionInUse;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.core.exceptions.ValidateMessageException;
import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.adapters.LogExceptionFieldsAdapter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private static final HttpStatus httpStatusNotFound = HttpStatus.NOT_FOUND;
    private static final HttpStatus httpStatusBadRequest = HttpStatus.BAD_REQUEST;
    private static final HttpStatus httpStatusConflict = HttpStatus.CONFLICT;
    private static final HttpStatus httpInternalError = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final HttpStatus httpAccessDenied = HttpStatus.FORBIDDEN;

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception, WebRequest request) {
        LogExceptionAdapter error = new LogExceptionAdapter(httpStatusNotFound, exception);
        log.error(String.valueOf(error));
        return handleExceptionInternal(exception, error, new HttpHeaders(), httpStatusNotFound, request);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException exception, WebRequest request) {
        LogExceptionAdapter error = new LogExceptionAdapter(httpStatusBadRequest, exception);
        log.error(String.valueOf(error));
        return handleExceptionInternal(exception, error, new HttpHeaders(), httpStatusBadRequest, request);
    }

    @ExceptionHandler({EntityExceptionInUse.class})
    public ResponseEntity<Object> handleConflictException(EntityExceptionInUse exception, WebRequest request) {
        LogExceptionAdapter error = new LogExceptionAdapter(httpStatusConflict, exception);
        log.error(String.valueOf(error));
        return handleExceptionInternal(exception, error, new HttpHeaders(), httpStatusConflict, request);
    }

    @ExceptionHandler({ValidateMessageException.class})
    public ResponseEntity<Object> handleValidateMessageException(ValidateMessageException exception, WebRequest request) {
        LogExceptionAdapter error = new LogExceptionAdapter(httpStatusNotFound, exception);
        log.error(String.valueOf(error));
        return handleExceptionInternal(exception, error, new HttpHeaders(), httpStatusNotFound, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(Exception exception, WebRequest request) {
        String message = "Você não possui permissão para executar essa operação.";
        LogExceptionAdapter error = new LogExceptionAdapter(httpAccessDenied, exception, message);
        log.error(String.valueOf(error));
        return handleExceptionInternal(exception, error, new HttpHeaders(), httpAccessDenied, request);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGeneralException(Exception exception, WebRequest request) {
        String message = "Ocorreu um erro interno inesperado no sistema. "
                + "Tente novamente e se o problema persistir, entre em contato "
                + "com o administrador do sistema.";
        LogExceptionAdapter error = new LogExceptionAdapter(httpInternalError, exception, message);
        log.error(String.valueOf(error));
        return handleExceptionInternal(exception, error, new HttpHeaders(), httpInternalError, request);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(@NotNull HttpMediaTypeNotAcceptableException ex,
                                                                               @NotNull HttpHeaders headers, @NotNull HttpStatus status,
                                                                               @NotNull WebRequest request) {
        return ResponseEntity.status(status).headers(headers).build();
    }


    @Override
    protected @NotNull ResponseEntity<Object> handleHttpMessageNotReadable(@NotNull HttpMessageNotReadableException exception,
                                                                           @NotNull HttpHeaders headers, @NotNull HttpStatus status,
                                                                           @NotNull WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(exception);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof UnrecognizedPropertyException){
            return handleUnrecognizedPropertyException((UnrecognizedPropertyException) rootCause, headers, status, request);
        }

        String message = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
        LogExceptionAdapter error = new LogExceptionAdapter(httpStatusBadRequest, exception, message);
        log.error(String.valueOf(error));
        return handleExceptionInternal(exception, error, headers, status, request);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleTypeMismatch(@NotNull TypeMismatchException exception,
                                                                 @NotNull HttpHeaders headers, @NotNull HttpStatus status,
                                                                 @NotNull WebRequest request) {

        if (exception instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch(
                    (MethodArgumentTypeMismatchException) exception, headers, status, request);
        }

        return super.handleTypeMismatch(exception, headers, status, request);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception,
                                                                            @NotNull HttpHeaders headers, @NotNull HttpStatus status,
                                                                            @NotNull WebRequest request) {

        String message = String.format("O recurso %s, que você tentou acessar, é inexistente.",
                exception.getRequestURL());

        LogExceptionAdapter error = new LogExceptionAdapter(httpStatusNotFound, exception, message);
        log.error(String.valueOf(error));
        return handleExceptionInternal(exception, error, headers, status, request);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleBindException(@NotNull BindException exception,
                                                                  @NotNull HttpHeaders headers, @NotNull HttpStatus status,
                                                                  @NotNull WebRequest request) {

        return handleValidationInternal(exception, headers, status, request, exception.getBindingResult());
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(@NotNull MethodArgumentNotValidException exception,
                                                                           @NotNull HttpHeaders headers, @NotNull HttpStatus status,
                                                                           @NotNull WebRequest request) {

        return handleValidationInternal(exception, headers, status, request, exception.getBindingResult());
    }

    private ResponseEntity<Object> handleValidationInternal(@NotNull Exception exception,
                                                            @NotNull HttpHeaders headers, @NotNull HttpStatus status,
                                                            @NotNull WebRequest request, BindingResult bindingResult) {
        String message = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<LogExceptionFieldsAdapter> problemFields = bindingResult.getAllErrors().stream()
                .map(objectError -> {

                    String fieldMessage = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    String name = objectError.getObjectName();

                    if(objectError instanceof FieldError){
                        name = ((FieldError) objectError).getField();
                    }

                    return LogExceptionFieldsAdapter.builder()
                            .name(name)
                            .userMessage(fieldMessage)
                            .build();
                }).collect(Collectors.toList());


        LogExceptionAdapter error = new LogExceptionAdapter(httpStatusBadRequest, exception, message, problemFields);
        log.error(String.valueOf(error));
        return handleExceptionInternal(exception, error, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException exception,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = exception.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));

        String message = String.format("A propriedade '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                path, exception.getValue(), exception.getTargetType().getSimpleName());

        LogExceptionAdapter error = new LogExceptionAdapter(httpStatusBadRequest, exception, message);
        log.error(String.valueOf(error));
        return handleExceptionInternal(exception, error, headers, status, request);
    }

    private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException exception,
                                                                       HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = exception.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));

        String message = String.format("A propriedade '%s' não existe."
                        +" Corrija ou remova essa propriedade e tente novamente.", path);

        LogExceptionAdapter error = new LogExceptionAdapter(httpStatusBadRequest, exception, message);
        log.error(String.valueOf(error));
        return handleExceptionInternal(exception, error, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception,
                                                                    HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = "O parâmetro de URL '" + exception.getName()
                + "' recebeu o valor '" + exception.getValue() + "', que é de um tipo inválido. Corrija e informe um valor compatível com o tipo "
                + Objects.requireNonNull(exception.getRequiredType()).getSimpleName() + ".";

        LogExceptionAdapter error = new LogExceptionAdapter(httpStatusBadRequest, exception, message);
        log.error(String.valueOf(error));
        return handleExceptionInternal(exception, error, headers, status, request);
    }
    
}
