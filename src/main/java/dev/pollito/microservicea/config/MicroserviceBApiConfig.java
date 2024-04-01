package dev.pollito.microservicea.config;

import dev.pollito.microservicea.errordecoder.MicroserviceBErrorDecoder;
import dev.pollito.microservicea.properties.MicroserviceBProperties;
import dev.pollito.microserviceb.api.HelloWorldApi;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.micrometer.MicrometerCapability;
import feign.micrometer.MicrometerObservationCapability;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans(
    value = {
      @ComponentScan(
          basePackages = {
            "dev.pollito.microserviceb.api",
          })
    })
@RequiredArgsConstructor
public class MicroserviceBApiConfig {
  private final MicroserviceBProperties microserviceBProperties;
  private final MeterRegistry meterRegistry;
  private final ObservationRegistry observationRegistry;

  @Bean
  public HelloWorldApi microServiceBApi() {
    return Feign.builder()
        .client(new OkHttpClient())
        .encoder(new GsonEncoder())
        .decoder(new GsonDecoder())
        .errorDecoder(new MicroserviceBErrorDecoder())
        .logger(new Slf4jLogger(HelloWorldApi.class))
        .logLevel(Logger.Level.FULL)
        .addCapability(new MicrometerObservationCapability(observationRegistry))
        .addCapability(new MicrometerCapability(meterRegistry))
        .target(HelloWorldApi.class, microserviceBProperties.getBaseUrl());
  }
}
