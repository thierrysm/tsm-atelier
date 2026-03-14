package com.tsm.atelier.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.storage.minio")
public record StorageProperties(
    String endpoint, String accessKey, String secretKey, String bucket, String baseUrl) {}
