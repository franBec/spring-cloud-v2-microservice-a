package dev.pollito.microservicea.controller.advice;

import static dev.pollito.microservicea.util.ErrorResponseBuilder.buildErrorResponse;

import dev.pollito.microservicea.controller.DefaultController;
import dev.pollito.microservicea.exception.MicroserviceBException;
import dev.pollito.microservicea.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = DefaultController.class)
public class DefaultControllerAdvice {
  @ExceptionHandler(MicroserviceBException.class)
  public static ResponseEntity<Error> handle(MicroserviceBException e) {
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getError().getMessage());
  }
}
