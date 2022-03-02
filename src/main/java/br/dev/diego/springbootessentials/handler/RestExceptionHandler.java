package br.dev.diego.springbootessentials.handler;

import br.dev.diego.springbootessentials.service.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException badRequestException) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        BadRequestExceptionDetails err = new BadRequestExceptionDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(status.value());
        err.setTitle("Bad Request Exception, check the documentation");
        err.setDetails(badRequestException.getMessage());
        err.setDeveloperMessage(badRequestException.getClass().getName());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationExceptionDetails err = new ValidationExceptionDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(status.value());
        err.setTitle("Bad Request Exception, invalid fields");
        err.setDeveloperMessage(methodArgumentNotValidException.getClass().getName());

        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        String fieldsName = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        err.setFieldName(fieldsName);
        err.setFieldMessage(fieldsMessage);
        err.setDetails("Check field(s) error: " + err.getFieldName());

        return ResponseEntity.status(status).body(err);
    }


}
