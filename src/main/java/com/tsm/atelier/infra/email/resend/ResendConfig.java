package com.tsm.atelier.infra.email.resend;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(ResendProperties.class)
@RequiredArgsConstructor
public class ResendConfig {

  private final ResendProperties resendProperties;

  @Bean
  public RestClient resendRestClient() {
    return RestClient.builder()
        .baseUrl("https://api.resend.com")
        .defaultHeader("Authorization", "Bearer " + resendProperties.apiKey())
        .defaultHeader("Content-Type", "application/json")
        .build();
  }
}
