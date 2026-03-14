package com.tsm.atelier.shared;

import com.tsm.atelier.config.StorageProperties;
import com.tsm.atelier.exception.BusinessException;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class StorageService {

  private final S3Client s3Client;
  private final StorageProperties properties;

  public String upload(MultipartFile file, String folder) {
    try {
      String fileName = folder + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

      s3Client.putObject(
          PutObjectRequest.builder()
              .bucket(properties.bucket())
              .key(fileName)
              .contentType(file.getContentType())
              .build(),
          RequestBody.fromBytes(file.getBytes()));

      return properties.baseUrl() + "/" + fileName;

    } catch (IOException ex) {
      throw new BusinessException("Failed to upload file: " + ex.getMessage());
    }
  }

  public void delete(String url) {
    String key = url.replace(properties.baseUrl() + "/", "");
    s3Client.deleteObject(
        DeleteObjectRequest.builder().bucket(properties.bucket()).key(key).build());
  }
}
