package br.dev.diego.springbootessentials.handler;

import br.dev.diego.springbootessentials.service.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> handlerBadRequestException(BadRequestException badRequestException) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(status.value());
        err.setTitle("Bad Request Exception, check the documentation");
        err.setDetails(badRequestException.getMessage());
        err.setDeveloperMessage(badRequestException.getClass().getName());
        return ResponseEntity.status(status).body(err);
    }

}
