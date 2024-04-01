package dev.pollito.microservicea.service.impl;

import dev.pollito.microservicea.service.MicroserviceBService;
import dev.pollito.microserviceb.api.HelloWorldApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MicroserviceBServiceImpl implements MicroserviceBService {
  private final HelloWorldApi helloWorldApi;

  @Override
  public String helloWorld() {
    return helloWorldApi.helloWorld().getContent();
  }
}
