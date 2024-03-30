package dev.pollito.microservicea.exception;

import dev.pollito.microserviceb.models.Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MicroserviceBException extends RuntimeException {
  private final transient Error error;
}
