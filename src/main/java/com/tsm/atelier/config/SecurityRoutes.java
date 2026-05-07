package com.tsm.atelier.config;

public class SecurityRoutes {
  private SecurityRoutes() {}

  public static final String[] PUBLIC_POST = {
    "/api/v1/auth/login",
    "/api/v1/auth/register",
    "/api/v1/auth/refresh",
    "/api/v1/auth/logout",
    "/api/v1/auth/forgot-password",
    "/api/v1/auth/reset-password",
    "/api/v1/auth/resend-verification",
    "/api/v1/webhooks/stripe"
  };

  public static final String[] PUBLIC_GET = {
    "/api/v1/auth/verify",
    "/api/v1/products/**",
    "/api/v1/collections/**",
    "/api/v1/media-assets/**",
    "/v3/api-docs/**",
    "/swagger-ui/**",
    "/swagger-ui.html",
    "/actuator/health",
    "/actuator/info",
    "/actuator/metrics"
  };
}
