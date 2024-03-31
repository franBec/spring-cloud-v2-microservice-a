package dev.pollito.microservicea.util;

import dev.pollito.microservicea.models.Error;
import io.opentelemetry.api.trace.Span;
import java.time.OffsetDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ErrorResponseBuilder {

  private ErrorResponseBuilder() {}

  public static ResponseEntity<Error> buildErrorResponse(
      HttpStatus status, Exception e, String errorMessage) {
    return ResponseEntity.status(status)
        .body(
            new Error()
                .error(e.getClass().getSimpleName())
                .message(errorMessage)
                .path(getCurrentRequestPath())
                .timestamp(OffsetDateTime.now())
                .trace(Span.current().getSpanContext().getTraceId()));
  }

  private static String getCurrentRequestPath() {
    ServletRequestAttributes attr =
        (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    return attr.getRequest().getRequestURI();
  }
}
