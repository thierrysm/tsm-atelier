package com.tsm.atelier.config;

import java.net.URI;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;
import software.amazon.awssdk.services.s3.model.PutBucketPolicyRequest;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageConfig {

  @Bean
  public S3Client s3Client(StorageProperties properties) {
    S3Client client =
        S3Client.builder()
            .endpointOverride(URI.create(properties.endpoint()))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(properties.accessKey(), properties.secretKey())))
            .region(Region.US_EAST_1)
            .forcePathStyle(true)
            .build();

    try {
      client.headBucket(HeadBucketRequest.builder().bucket(properties.bucket()).build());
    } catch (NoSuchBucketException e) {
      client.createBucket(CreateBucketRequest.builder().bucket(properties.bucket()).build());
    }

    String policy =
        """
        {
          "Version": "2012-10-17",
          "Statement": [
            {
              "Effect": "Allow",
              "Principal": "*",
              "Action": "s3:GetObject",
              "Resource": "arn:aws:s3:::%s/*"
            }
          ]
        }
        """
            .formatted(properties.bucket());

    client.putBucketPolicy(
        PutBucketPolicyRequest.builder().bucket(properties.bucket()).policy(policy).build());

    return client;
  }
}
