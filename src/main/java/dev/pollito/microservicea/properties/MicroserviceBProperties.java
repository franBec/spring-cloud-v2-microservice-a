package dev.pollito.microservicea.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "microservice-b")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MicroserviceBProperties {
  String baseUrl;
}
