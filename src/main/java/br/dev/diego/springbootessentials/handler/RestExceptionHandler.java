package br.dev.diego.springbootessentials.handler;

import br.dev.diego.springbootessentials.service.exception.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException badRequestException) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        BadRequestExceptionDetails err = new BadRequestExceptionDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(status.value());
        err.setTitle("Bad Request Exception, check the documentation");
        err.setDetails(badRequestException.getMessage());
        err.setDeveloperMessage(badRequestException.getClass().getName());
        return ResponseEntity.status(status).body(err);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ValidationExceptionDetails err = new ValidationExceptionDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(status.value());
        err.setTitle("Bad Request Exception, invalid fields");
        err.setDeveloperMessage(ex.getClass().getName());

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String fieldsName = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        err.setFieldName(fieldsName);
        err.setFieldMessage(fieldsMessage);
        err.setDetails("Check field(s) error: " + err.getFieldName());

        return ResponseEntity.status(status).body(err);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        StandardError err = new StandardError();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(status.value());
        err.setTitle(ex.getCause().getMessage());
        err.setDetails(ex.getMessage());
        err.setDeveloperMessage(ex.getClass().getName());

        return new ResponseEntity<>(err, headers, status);
    }

}
