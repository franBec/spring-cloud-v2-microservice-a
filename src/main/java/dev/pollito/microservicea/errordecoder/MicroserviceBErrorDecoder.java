package dev.pollito.microservicea.errordecoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pollito.microservicea.exception.MicroserviceBException;
import dev.pollito.microserviceb.models.Error;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.io.InputStream;

public class MicroserviceBErrorDecoder implements ErrorDecoder {
  @Override
  public Exception decode(String s, Response response) {
    try (InputStream body = response.body().asInputStream()) {
      return new MicroserviceBException(new ObjectMapper().readValue(body, Error.class));
    } catch (IOException e) {
      return new Default().decode(s, response);
    }
  }
}
