package dev.pollito.microservicea.controller;

import dev.pollito.microservicea.api.DefaultApi;
import dev.pollito.microservicea.service.MicroserviceBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DefaultController implements DefaultApi {
  private final MicroserviceBService microserviceBService;

  @Override
  public ResponseEntity<String> helloWorld() {
    return ResponseEntity.ok(
        "Hello World from Microservice A " + microserviceBService.helloWorld());
  }
}
